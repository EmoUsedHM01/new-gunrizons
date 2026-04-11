package com.gtnewhorizon.newgunrizons.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class JsonModel extends ModelWithAttachments {
   private static final float DEG_TO_RAD = (float) (Math.PI / 180.0);
   private final String modelPath;
   private final List<ModelRenderer> rootRenderers = new ArrayList<>();

   public static boolean is(ModelBase model, String path) {
      return model instanceof JsonModel && ((JsonModel)model).modelPath.equals(path);
   }

   public String getModelPath() {
      return this.modelPath;
   }

   public JsonModel(String modelPath) {
      this("newgunrizons", modelPath);
   }

   public JsonModel(String domain, String modelPath) {
      this.modelPath = modelPath;
      String resourcePath = "/assets/" + domain + "/models/" + modelPath + ".geo.json";
      InputStream is = JsonModel.class.getResourceAsStream(resourcePath);
      if (is == null) {
         throw new RuntimeException("Model not found on classpath: " + resourcePath);
      } else {
         try {
            JsonObject root = new JsonParser().parse(new InputStreamReader(is, StandardCharsets.UTF_8)).getAsJsonObject();
            this.parseGeometry(root);
         } catch (RuntimeException var14) {
            throw var14;
         } catch (Exception var15) {
            throw new RuntimeException("Failed to parse model: " + resourcePath, var15);
         } finally {
            try {
               is.close();
            } catch (Exception var13) {
            }
         }
      }
   }

   private void parseGeometry(JsonObject root) {
      JsonArray geometries = root.getAsJsonArray("minecraft:geometry");
      if (geometries != null && geometries.size() != 0) {
         JsonObject geo = geometries.get(0).getAsJsonObject();
         JsonObject desc = geo.getAsJsonObject("description");
         if (desc != null) {
            if (desc.has("texture_width")) {
               this.textureWidth = desc.get("texture_width").getAsInt();
            }

            if (desc.has("texture_height")) {
               this.textureHeight = desc.get("texture_height").getAsInt();
            }
         }

         JsonArray bones = geo.getAsJsonArray("bones");
         if (bones != null) {
            Map<String, ModelRenderer> rendererMap = new LinkedHashMap<>();
            Map<String, String> parentMap = new LinkedHashMap<>();
            Map<String, float[]> absolutePivots = new LinkedHashMap<>();

            for (JsonElement boneElem : bones) {
               JsonObject bone = boneElem.getAsJsonObject();
               String name = bone.get("name").getAsString();
               float[] pivot = getFloatArray(bone, "pivot", new float[]{0.0F, 0.0F, 0.0F});
               float[] rotation = getFloatArray(bone, "rotation", new float[]{0.0F, 0.0F, 0.0F});
               absolutePivots.put(name, pivot);
               float javaPivotX = pivot[0];
               float javaPivotY = -pivot[1];
               float javaPivotZ = pivot[2];
               ModelRenderer renderer = new ModelRenderer(this, name);
               renderer.setTextureSize(this.textureWidth, this.textureHeight);
               renderer.setRotationPoint(javaPivotX, javaPivotY, javaPivotZ);
               renderer.rotateAngleX = -rotation[0] * (float) (Math.PI / 180.0);
               renderer.rotateAngleY = -rotation[1] * (float) (Math.PI / 180.0);
               renderer.rotateAngleZ = rotation[2] * (float) (Math.PI / 180.0);
               if (bone.has("cubes")) {
                  for (JsonElement cubeElem : bone.getAsJsonArray("cubes")) {
                     this.addCube(renderer, cubeElem.getAsJsonObject(), pivot);
                  }
               }

               rendererMap.put(name, renderer);
               if (bone.has("parent")) {
                  parentMap.put(name, bone.get("parent").getAsString());
               }
            }

            for (Entry<String, String> entry : parentMap.entrySet()) {
               String childName = entry.getKey();
               String parentName = entry.getValue();
               ModelRenderer child = rendererMap.get(childName);
               ModelRenderer parent = rendererMap.get(parentName);
               if (parent != null && child != null) {
                  child.rotationPointX = child.rotationPointX - parent.rotationPointX;
                  child.rotationPointY = child.rotationPointY - parent.rotationPointY;
                  child.rotationPointZ = child.rotationPointZ - parent.rotationPointZ;
                  parent.addChild(child);
               }
            }

            for (Entry<String, ModelRenderer> entryx : rendererMap.entrySet()) {
               if (!parentMap.containsKey(entryx.getKey())) {
                  this.rootRenderers.add(entryx.getValue());
               }
            }
         }
      } else {
         throw new RuntimeException("No minecraft:geometry array found in .geo.json");
      }
   }

   private void addCube(ModelRenderer renderer, JsonObject cube, float[] bonePivot) {
      float[] origin = getFloatArray(cube, "origin", new float[]{0.0F, 0.0F, 0.0F});
      float[] size = getFloatArray(cube, "size", new float[]{1.0F, 1.0F, 1.0F});
      float inflate = cube.has("inflate") ? cube.get("inflate").getAsFloat() : 0.0F;
      boolean mirror = cube.has("mirror") && cube.get("mirror").getAsBoolean();
      int texU = 0;
      int texV = 0;
      if (cube.has("uv")) {
         JsonElement uvElem = cube.get("uv");
         if (uvElem.isJsonArray()) {
            JsonArray uv = uvElem.getAsJsonArray();
            texU = Math.round(uv.get(0).getAsFloat());
            texV = Math.round(uv.get(1).getAsFloat());
         }
      }

      float offX = origin[0] - bonePivot[0];
      float offY = bonePivot[1] - origin[1] - size[1];
      float offZ = origin[2] - bonePivot[2];
      int sizeX = Math.round(size[0]);
      int sizeY = Math.round(size[1]);
      int sizeZ = Math.round(size[2]);
      renderer.mirror = mirror;
      renderer.setTextureOffset(texU, texV);
      renderer.addBox(offX, offY, offZ, sizeX, sizeY, sizeZ, inflate);
   }

   private static float[] getFloatArray(JsonObject obj, String key, float[] defaultValue) {
      if (!obj.has(key)) {
         return defaultValue;
      } else {
         JsonArray arr = obj.getAsJsonArray(key);
         float[] result = new float[arr.size()];

         for (int i = 0; i < arr.size(); i++) {
            result[i] = arr.get(i).getAsFloat();
         }

         return result;
      }
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);

      for (ModelRenderer rootRenderer : this.rootRenderers) {
         rootRenderer.render(f5);
      }
   }
}
