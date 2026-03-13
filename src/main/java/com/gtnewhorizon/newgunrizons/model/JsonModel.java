package com.gtnewhorizon.newgunrizons.model;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.PositionTextureVertex;
import net.minecraft.client.model.TexturedQuad;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Loads a Bedrock geometry (.geo.json) model and creates ModelRenderer instances,
 * providing a drop-in replacement for hand-coded Java model classes.
 * <p>
 * Bedrock geometry uses Y-up. Minecraft 1.7.10 ModelRenderer uses Y-down.
 * Coordinate conversion (Y negation) is handled automatically.
 * <p>
 * Supported: bones (pivot, rotation, parent hierarchy), cubes (origin, size, box UV, per-face UV,
 * mirror, inflate, per-cube rotation).
 * <p>
 * Usage:
 *
 * <pre>
 *   // Loads /assets/newgunrizons/models/weapon/ak12.geo.json from classpath
 *   new JsonModel("weapon/ak12")
 *
 *   // In a weapon factory, replaces: new AK12()
 *   new WeaponRenderer.Builder().withModel(new JsonModel("weapon/ak12"))
 * </pre>
 */
public class JsonModel extends ModelWithAttachments {

    private static final float DEG_TO_RAD = (float) (Math.PI / 180.0);

    private final String modelPath;
    private final List<ModelRenderer> rootRenderers = new ArrayList<>();
    private final Map<String, ModelRenderer> boneMap = new LinkedHashMap<>();
    private final Map<String, float[]> restRotations = new LinkedHashMap<>();
    private final Map<String, float[]> restPositions = new LinkedHashMap<>();

    /**
     * Check if a ModelBase is a JsonModel with the given path.
     * Replaces {@code model instanceof SomeModelClass} checks.
     *
     * @param model the model to check
     * @param path  the model path (e.g. "sight/ak47iron")
     * @return true if model is a JsonModel loaded from that path
     */
    public static boolean is(ModelBase model, String path) {
        return model instanceof JsonModel && ((JsonModel) model).modelPath.equals(path);
    }

    /**
     * Returns the model path this JsonModel was loaded from.
     */
    public String getModelPath() {
        return modelPath;
    }

    /**
     * Returns the ModelRenderer for a bone by name, or null if not found.
     */
    public ModelRenderer getBone(String name) {
        return boneMap.get(name);
    }

    /**
     * Returns the rest-pose rotation for a bone: [rotateAngleX, rotateAngleY, rotateAngleZ] in radians.
     */
    public float[] getRestRotation(String name) {
        return restRotations.get(name);
    }

    /**
     * Returns the rest-pose position for a bone: [rotationPointX, rotationPointY, rotationPointZ].
     */
    public float[] getRestPosition(String name) {
        return restPositions.get(name);
    }

    /**
     * Resets all bones to their geometry-defined rest pose transforms.
     */
    public void resetBonesToRestPose() {
        for (Map.Entry<String, ModelRenderer> entry : boneMap.entrySet()) {
            ModelRenderer renderer = entry.getValue();
            float[] rot = restRotations.get(entry.getKey());
            float[] pos = restPositions.get(entry.getKey());
            if (rot != null) {
                renderer.rotateAngleX = rot[0];
                renderer.rotateAngleY = rot[1];
                renderer.rotateAngleZ = rot[2];
            }
            if (pos != null) {
                renderer.rotationPointX = pos[0];
                renderer.rotationPointY = pos[1];
                renderer.rotationPointZ = pos[2];
            }
        }
    }

    /**
     * Load a Bedrock geometry model from the mod's resources.
     *
     * @param modelPath path relative to assets/newgunrizons/models/, without .geo.json extension
     */
    public JsonModel(String modelPath) {
        this("newgunrizons", modelPath);
    }

    /**
     * Load a Bedrock geometry model from any mod's resources.
     *
     * @param domain    resource domain (mod id)
     * @param modelPath path relative to assets/{domain}/models/, without .geo.json extension
     */
    public JsonModel(String domain, String modelPath) {
        this.modelPath = modelPath;
        String resourcePath = "/assets/" + domain + "/models/" + modelPath + ".geo.json";
        InputStream is = JsonModel.class.getResourceAsStream(resourcePath);
        if (is == null) {
            throw new RuntimeException("Model not found on classpath: " + resourcePath);
        }
        try {
            JsonObject root = new JsonParser().parse(new InputStreamReader(is, StandardCharsets.UTF_8))
                .getAsJsonObject();
            parseGeometry(root);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse model: " + resourcePath, e);
        } finally {
            try {
                is.close();
            } catch (Exception ignored) {}
        }
    }

    private void parseGeometry(JsonObject root) {
        JsonArray geometries = root.getAsJsonArray("minecraft:geometry");
        if (geometries == null || geometries.size() == 0) {
            throw new RuntimeException("No minecraft:geometry array found in .geo.json");
        }

        JsonObject geo = geometries.get(0)
            .getAsJsonObject();
        JsonObject desc = geo.getAsJsonObject("description");

        if (desc != null) {
            if (desc.has("texture_width")) {
                this.textureWidth = desc.get("texture_width")
                    .getAsInt();
            }
            if (desc.has("texture_height")) {
                this.textureHeight = desc.get("texture_height")
                    .getAsInt();
            }
        }

        JsonArray bones = geo.getAsJsonArray("bones");
        if (bones == null) return;

        // First pass: create ModelRenderers, track parents and absolute pivots
        Map<String, ModelRenderer> rendererMap = new LinkedHashMap<>();
        Map<String, String> parentMap = new LinkedHashMap<>();
        Map<String, float[]> absolutePivots = new LinkedHashMap<>();

        for (JsonElement boneElem : bones) {
            JsonObject bone = boneElem.getAsJsonObject();
            String name = bone.get("name")
                .getAsString();

            float[] pivot = getFloatArray(bone, "pivot", new float[] { 0, 0, 0 });
            float[] rotation = getFloatArray(bone, "rotation", new float[] { 0, 0, 0 });

            absolutePivots.put(name, pivot);

            // Convert pivot: Bedrock (Y-up) → Java (Y-down)
            float javaPivotX = pivot[0];
            float javaPivotY = -pivot[1];
            float javaPivotZ = pivot[2];

            ModelRenderer renderer = new ModelRenderer(this, name);
            renderer.setTextureSize(this.textureWidth, this.textureHeight);
            renderer.setRotationPoint(javaPivotX, javaPivotY, javaPivotZ);

            // Convert rotation: degrees → radians, negate X and Y for coordinate flip
            renderer.rotateAngleX = -rotation[0] * DEG_TO_RAD;
            renderer.rotateAngleY = -rotation[1] * DEG_TO_RAD;
            renderer.rotateAngleZ = rotation[2] * DEG_TO_RAD;

            // Parse cubes
            if (bone.has("cubes")) {
                JsonArray cubes = bone.getAsJsonArray("cubes");
                for (JsonElement cubeElem : cubes) {
                    addCube(renderer, cubeElem.getAsJsonObject(), pivot);
                }
            }

            rendererMap.put(name, renderer);

            if (bone.has("parent")) {
                parentMap.put(
                    name,
                    bone.get("parent")
                        .getAsString());
            }
        }

        // Second pass: set up parent-child hierarchy
        for (Map.Entry<String, String> entry : parentMap.entrySet()) {
            String childName = entry.getKey();
            String parentName = entry.getValue();

            ModelRenderer child = rendererMap.get(childName);
            ModelRenderer parent = rendererMap.get(parentName);

            if (parent != null && child != null) {
                // Convert child rotationPoint from absolute to parent-relative.
                // Note: this is exact when the parent has no rotation. When the parent
                // is rotated, the position will be approximate (the inverse rotation
                // transform is not applied). For most weapon models this is fine since
                // parent bones rarely have rotation.
                child.rotationPointX -= parent.rotationPointX;
                child.rotationPointY -= parent.rotationPointY;
                child.rotationPointZ -= parent.rotationPointZ;

                parent.addChild(child);
            }
        }

        // Collect root bones (those with no parent) and store bone map + rest poses
        for (Map.Entry<String, ModelRenderer> entry : rendererMap.entrySet()) {
            String name = entry.getKey();
            ModelRenderer renderer = entry.getValue();
            boneMap.put(name, renderer);
            restRotations.put(
                name,
                new float[] { renderer.rotateAngleX, renderer.rotateAngleY, renderer.rotateAngleZ });
            restPositions.put(
                name,
                new float[] { renderer.rotationPointX, renderer.rotationPointY, renderer.rotationPointZ });
            if (!parentMap.containsKey(name)) {
                rootRenderers.add(renderer);
            }
        }
    }

    private void addCube(ModelRenderer renderer, JsonObject cube, float[] bonePivot) {
        float[] origin = getFloatArray(cube, "origin", new float[] { 0, 0, 0 });
        float[] size = getFloatArray(cube, "size", new float[] { 1, 1, 1 });
        float inflate = cube.has("inflate") ? cube.get("inflate")
            .getAsFloat() : 0.0f;
        boolean mirror = cube.has("mirror") && cube.get("mirror")
            .getAsBoolean();

        // Per-cube rotation: create a child ModelRenderer at the cube's pivot.
        float[] effectivePivot = bonePivot;
        ModelRenderer target = renderer;

        if (cube.has("rotation")) {
            float[] cubePivot = getFloatArray(cube, "pivot", bonePivot);
            float[] cubeRotation = getFloatArray(cube, "rotation", new float[] { 0, 0, 0 });

            ModelRenderer child = new ModelRenderer(this);
            child.setTextureSize(this.textureWidth, this.textureHeight);
            child.setRotationPoint(
                cubePivot[0] - bonePivot[0],
                -(cubePivot[1] - bonePivot[1]),
                cubePivot[2] - bonePivot[2]);
            child.rotateAngleX = -cubeRotation[0] * DEG_TO_RAD;
            child.rotateAngleY = -cubeRotation[1] * DEG_TO_RAD;
            child.rotateAngleZ = cubeRotation[2] * DEG_TO_RAD;

            renderer.addChild(child);
            target = child;
            effectivePivot = cubePivot;
        }

        // Convert cube origin from absolute Bedrock coords to offset relative to pivot.
        // Bedrock: origin is the min corner (Y-up). Java: offset is relative to rotationPoint (Y-down).
        float offX = origin[0] - effectivePivot[0];
        float offY = effectivePivot[1] - origin[1] - size[1];
        float offZ = origin[2] - effectivePivot[2];

        if (cube.has("uv")) {
            JsonElement uvElem = cube.get("uv");
            if (uvElem.isJsonObject()) {
                // Per-face UV (Bedrock format: each face has its own uv + uv_size)
                target.mirror = mirror;
                target.cubeList.add(
                    new PerFaceUVBox(
                        target, offX, offY, offZ,
                        size[0], size[1], size[2], inflate,
                        uvElem.getAsJsonObject(),
                        this.textureWidth, this.textureHeight));
            } else if (uvElem.isJsonArray()) {
                // Box UV: [u, v] array
                JsonArray uv = uvElem.getAsJsonArray();
                int texU = Math.round(
                    uv.get(0)
                        .getAsFloat());
                int texV = Math.round(
                    uv.get(1)
                        .getAsFloat());
                target.mirror = mirror;
                target.setTextureOffset(texU, texV);
                target.addBox(offX, offY, offZ,
                    Math.round(size[0]), Math.round(size[1]), Math.round(size[2]), inflate);
            }
        }
    }

    private static float[] getFloatArray(JsonObject obj, String key, float[] defaultValue) {
        if (!obj.has(key)) return defaultValue;
        JsonArray arr = obj.getAsJsonArray(key);
        float[] result = new float[arr.size()];
        for (int i = 0; i < arr.size(); i++) {
            result[i] = arr.get(i)
                .getAsFloat();
        }
        return result;
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        for (ModelRenderer rootRenderer : rootRenderers) {
            rootRenderer.render(f5);
        }
    }

    /**
     * A ModelBox subclass that supports per-face UV mapping from Bedrock geometry format.
     * Each face has its own {@code "uv"} and {@code "uv_size"} instead of a single box UV offset.
     */
    private static class PerFaceUVBox extends ModelBox {

        private final TexturedQuad[] faceQuads;

        PerFaceUVBox(ModelRenderer renderer, float offX, float offY, float offZ,
            float sizeX, float sizeY, float sizeZ, float inflate,
            JsonObject uvObj, float texW, float texH) {
            super(renderer, 0, 0, offX, offY, offZ,
                Math.round(sizeX), Math.round(sizeY), Math.round(sizeZ), inflate);

            // Compute float-precision corners with inflate
            float x1 = offX - inflate;
            float y1 = offY - inflate;
            float z1 = offZ - inflate;
            float x2 = offX + sizeX + inflate;
            float y2 = offY + sizeY + inflate;
            float z2 = offZ + sizeZ + inflate;

            if (renderer.mirror) {
                float tmp = x2;
                x2 = x1;
                x1 = tmp;
            }

            // 8 vertices matching MC's ModelBox layout
            PositionTextureVertex v0 = new PositionTextureVertex(x1, y1, z1, 0, 0);
            PositionTextureVertex v1 = new PositionTextureVertex(x2, y1, z1, 0, 0);
            PositionTextureVertex v2 = new PositionTextureVertex(x2, y2, z1, 0, 0);
            PositionTextureVertex v3 = new PositionTextureVertex(x1, y2, z1, 0, 0);
            PositionTextureVertex v4 = new PositionTextureVertex(x1, y1, z2, 0, 0);
            PositionTextureVertex v5 = new PositionTextureVertex(x2, y1, z2, 0, 0);
            PositionTextureVertex v6 = new PositionTextureVertex(x2, y2, z2, 0, 0);
            PositionTextureVertex v7 = new PositionTextureVertex(x1, y2, z2, 0, 0);

            // Face order matches MC's ModelBox: east[0], west[1], up[2], down[3], north[4], south[5]
            faceQuads = new TexturedQuad[6];
            faceQuads[0] = makeFaceQuad(uvObj, "east", texW, texH, v5, v1, v2, v6);
            faceQuads[1] = makeFaceQuad(uvObj, "west", texW, texH, v0, v4, v7, v3);
            faceQuads[2] = makeFaceQuad(uvObj, "up", texW, texH, v5, v4, v0, v1);
            faceQuads[3] = makeFaceQuad(uvObj, "down", texW, texH, v2, v3, v7, v6);
            faceQuads[4] = makeFaceQuad(uvObj, "north", texW, texH, v1, v0, v3, v2);
            faceQuads[5] = makeFaceQuad(uvObj, "south", texW, texH, v4, v5, v6, v7);

            if (renderer.mirror) {
                for (TexturedQuad q : faceQuads) {
                    if (q != null) {
                        q.flipFace();
                    }
                }
            }
        }

        private static TexturedQuad makeFaceQuad(JsonObject uvObj, String face,
            float texW, float texH,
            PositionTextureVertex a, PositionTextureVertex b,
            PositionTextureVertex c, PositionTextureVertex d) {
            if (!uvObj.has(face)) {
                return null;
            }
            JsonObject faceObj = uvObj.getAsJsonObject(face);
            JsonArray uvArr = faceObj.getAsJsonArray("uv");
            JsonArray sizeArr = faceObj.getAsJsonArray("uv_size");

            float u = uvArr.get(0)
                .getAsFloat();
            float v = uvArr.get(1)
                .getAsFloat();
            float w = sizeArr.get(0)
                .getAsFloat();
            float h = sizeArr.get(1)
                .getAsFloat();

            float u1 = u / texW;
            float v1 = v / texH;
            float u2 = (u + w) / texW;
            float v2 = (v + h) / texH;

            // UV assignment matches MC's TexturedQuad pattern:
            // vert[0] → (u2, v1), vert[1] → (u1, v1), vert[2] → (u1, v2), vert[3] → (u2, v2)
            return new TexturedQuad(new PositionTextureVertex[] {
                a.setTexturePosition(u2, v1),
                b.setTexturePosition(u1, v1),
                c.setTexturePosition(u1, v2),
                d.setTexturePosition(u2, v2)
            });
        }

        @Override
        @SideOnly(Side.CLIENT)
        public void render(Tessellator tess, float scale) {
            for (TexturedQuad quad : faceQuads) {
                if (quad != null) {
                    quad.draw(tess, scale);
                }
            }
        }
    }
}
