package com.gtnewhorizon.newgunrizons.grenade;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;

import com.gtnewhorizon.newgunrizons.attachment.CompatibleAttachment;
import com.gtnewhorizon.newgunrizons.attachment.DefaultPart;
import com.gtnewhorizon.newgunrizons.attachment.ItemAttachment;
import com.gtnewhorizon.newgunrizons.attachment.Part;
import com.gtnewhorizon.newgunrizons.client.animation.MultipartPositioning;
import com.gtnewhorizon.newgunrizons.client.animation.MultipartRenderStateManager;
import com.gtnewhorizon.newgunrizons.client.animation.MultipartTransition;
import com.gtnewhorizon.newgunrizons.client.animation.MultipartTransitionProvider;
import com.gtnewhorizon.newgunrizons.client.animation.Transition;
import com.gtnewhorizon.newgunrizons.client.render.CustomRenderer;
import com.gtnewhorizon.newgunrizons.client.render.RenderContext;
import com.gtnewhorizon.newgunrizons.client.render.TransformType;
import com.gtnewhorizon.newgunrizons.client.shader.Framebuffers;
import com.gtnewhorizon.newgunrizons.config.ClientModContext;
import com.gtnewhorizon.newgunrizons.state.RenderableState;
import com.gtnewhorizon.newgunrizons.util.Tuple;
import com.gtnewhorizon.newgunrizons.weapon.PlayerItemInstance;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lombok.Getter;
import lombok.Setter;

public class GrenadeRenderer implements IItemRenderer {

    private static final Logger logger = LogManager.getLogger(GrenadeRenderer.class);

    private static final float DEFAULT_RANDOMIZING_RATE = 0.33F;
    private static final float DEFAULT_NORMAL_RANDOMIZING_AMPLITUDE = 0.06F;
    private static final int DEFAULT_ANIMATION_DURATION = 70;

    private final GrenadeRenderer.Builder builder;
    private final Map<GrenadeRenderer.StateManagerKey, MultipartRenderStateManager> firstPersonStateManagers;
    private final MultipartTransitionProvider weaponTransitionProvider;
    @Setter
    protected ClientModContext clientModContext;

    private GrenadeRenderer(GrenadeRenderer.Builder builder) {
        this.builder = builder;
        this.firstPersonStateManagers = new HashMap<>();
        this.weaponTransitionProvider = new GrenadeRenderer.WeaponPositionProvider();
    }

    protected long getTotalThrowingDuration() {
        return this.builder.totalThrowingDuration;
    }

    protected ClientModContext getClientModContext() {
        return this.clientModContext;
    }

    protected StateDescriptor getStateDescriptor(EntityLivingBase player, ItemStack itemStack) {
        float amplitude = this.builder.normalRandomizingAmplitude;
        float rate = this.builder.normalRandomizingRate;
        RenderableState currentState = null;
        PlayerItemInstance<?> playerItemInstance = this.clientModContext.getPlayerItemInstanceRegistry()
            .getItemInstance(player, itemStack);
        PlayerGrenadeInstance playerGrenadeInstance = null;
        if (playerItemInstance != null && playerItemInstance instanceof PlayerGrenadeInstance
            && playerItemInstance.getItem() == itemStack.getItem()) {
            playerGrenadeInstance = (PlayerGrenadeInstance) playerItemInstance;
        } else {
            logger.error(
                "Invalid or mismatching item. Player item instance: {}. Item stack: {}",
                playerItemInstance,
                itemStack);
        }

        if (playerGrenadeInstance != null) {
            AsyncGrenadeState asyncWeaponState = this.getNextNonExpiredState(playerGrenadeInstance);
            switch (asyncWeaponState.getState()) {
                case SAFETY_PING_OFF:
                    currentState = RenderableState.SAFETY_PIN_OFF;
                    break;
                case STRIKER_LEVER_RELEASED:
                    currentState = RenderableState.STRIKER_LEVEL_OFF;
                    break;
                case THROWING:
                    currentState = RenderableState.THROWING;
                    break;
                case THROWN:
                    currentState = RenderableState.THROWN;
                    break;
                default:
                    if (player.isSprinting() && this.builder.firstPersonPositioningRunning != null) {
                        currentState = RenderableState.RUNNING;
                    }
            }

            logger.trace("Rendering state {} created from {}", currentState, asyncWeaponState.getState());
        }

        if (currentState == null) {
            currentState = RenderableState.NORMAL;
        }

        GrenadeRenderer.StateManagerKey key = new GrenadeRenderer.StateManagerKey(
            player,
            playerGrenadeInstance != null ? playerGrenadeInstance.getItemInventoryIndex() : -1);
        MultipartRenderStateManager stateManager = this.firstPersonStateManagers.get(key);
        if (stateManager == null) {
            stateManager = new MultipartRenderStateManager(currentState, this.weaponTransitionProvider);
            this.firstPersonStateManagers.put(key, stateManager);
        } else {
            stateManager.setState(currentState, true, currentState == RenderableState.THROWING);
        }

        return new StateDescriptor(playerGrenadeInstance, stateManager, rate, amplitude);
    }

    private AsyncGrenadeState getNextNonExpiredState(PlayerGrenadeInstance playerWeaponState) {
        AsyncGrenadeState asyncWeaponState = null;

        while ((asyncWeaponState = playerWeaponState.nextHistoryState()) != null
            && System.currentTimeMillis() > asyncWeaponState.getTimestamp() + asyncWeaponState.getDuration()) {}

        return asyncWeaponState;
    }

    private Consumer<RenderContext> createWeaponPartPositionFunction(Transition t) {
        if (t == null) {
            return (context) -> {};
        } else {
            Consumer<RenderContext> weaponPositionFunction = t.getItemPositioning();
            if (Transition.isAnchored((weaponPositionFunction))) {
                return MultipartTransition.anchoredPosition();
            } else {
                return weaponPositionFunction != null ? weaponPositionFunction::accept : (context) -> {};
            }
        }
    }

    private Consumer<RenderContext> createWeaponPartPositionFunction(Consumer<RenderContext> weaponPositionFunction) {
        if (Transition.isAnchored((weaponPositionFunction))) {
            return MultipartTransition.anchoredPosition();
        } else {
            return weaponPositionFunction != null ? weaponPositionFunction::accept : (context) -> {};
        }
    }

    private List<MultipartTransition> getComplexTransition(List<Transition> wt, List<Transition> lht,
        List<Transition> rht, LinkedHashMap<Part, List<Transition>> custom) {
        List<MultipartTransition> result = new ArrayList<>();

        for (int i = 0; i < wt.size(); ++i) {
            Transition p = wt.get(i);
            Transition l = lht.get(i);
            Transition r = rht.get(i);
            MultipartTransition t = (new MultipartTransition(p.getDuration(), p.getPause()))
                .withPartPositionFunction(Part.MAIN_ITEM, p.getAttachedTo(), this.createWeaponPartPositionFunction(p))
                .withPartPositionFunction(Part.LEFT_HAND, l.getAttachedTo(), this.createWeaponPartPositionFunction(l))
                .withPartPositionFunction(Part.RIGHT_HAND, r.getAttachedTo(), this.createWeaponPartPositionFunction(r));

            Entry e;
            Transition partTransition;
            for (Iterator var13 = custom.entrySet()
                .iterator(); var13.hasNext(); t.withPartPositionFunction(
                    (Part) e.getKey(),
                    partTransition.getAttachedTo(),
                    this.createWeaponPartPositionFunction(partTransition))) {
                e = (Entry) var13.next();
                List<Transition> partTransitions = (List) e.getValue();
                partTransition = null;
                if (partTransitions != null && partTransitions.size() > i) {
                    partTransition = partTransitions.get(i);
                } else {
                    logger.warn("Transition not defined for part {}", custom);
                }
            }

            result.add(t);
        }

        return result;
    }

    private List<MultipartTransition> getSimpleTransition(Consumer<RenderContext> w, Consumer<RenderContext> lh,
        Consumer<RenderContext> rh, LinkedHashMap<Part, GrenadeRenderer.SimplePositioning> custom, int duration) {
        MultipartTransition mt = (new MultipartTransition(duration, 0L))
            .withPartPositionFunction(Part.MAIN_ITEM, null, this.createWeaponPartPositionFunction(w))
            .withPartPositionFunction(Part.LEFT_HAND, null, this.createWeaponPartPositionFunction(lh))
            .withPartPositionFunction(Part.RIGHT_HAND, null, this.createWeaponPartPositionFunction(rh));
        custom.forEach(
            (part, position) -> {
                mt.withPartPositionFunction(
                    part,
                    position.attachedTo,
                    this.createWeaponPartPositionFunction(position.positioning));
            });
        return Collections.singletonList(mt);
    }

    public void renderItem(ItemStack weaponItemStack, RenderContext renderContext,
        MultipartPositioning.Positioner positioner) {
        if (this.builder.getTextureName() != null) {
            Minecraft.getMinecraft().renderEngine.bindTexture(
                new ResourceLocation(this.builder.getModId() + ":textures/models/" + this.builder.getTextureName()));
        } else {
            String textureName;
            ItemGrenade weapon = (ItemGrenade) weaponItemStack.getItem();
            textureName = weapon.getTextureName();

            Minecraft.getMinecraft().renderEngine
                .bindTexture(new ResourceLocation(this.builder.getModId() + ":textures/models/" + textureName));
        }

        this.builder.getModel()
            .render(
                null,
                renderContext.getLimbSwing(),
                renderContext.getFlimbSwingAmount(),
                renderContext.getAgeInTicks(),
                renderContext.getNetHeadYaw(),
                renderContext.getHeadPitch(),
                renderContext.getScale());
        PlayerItemInstance<?> itemInstance = renderContext.getPlayerItemInstance();
        if (itemInstance instanceof PlayerGrenadeInstance grenadeInstance) {
            List<CompatibleAttachment> attachments = grenadeInstance.getActiveAttachments();
            this.renderAttachments(positioner, renderContext, attachments);
        }
    }

    public void renderAttachments(MultipartPositioning.Positioner positioner, RenderContext renderContext,
        List<CompatibleAttachment> attachments) {
        for (CompatibleAttachment attachment : attachments) {
            if (attachment != null) {
                this.renderCompatibleAttachment(attachment, positioner, renderContext);
            }
        }

    }

    private void renderCompatibleAttachment(CompatibleAttachment compatibleAttachment,
        MultipartPositioning.Positioner positioner, RenderContext renderContext) {
        GL11.glPushMatrix();
        GL11.glPushAttrib(8193);

        ItemAttachment itemAttachment = compatibleAttachment.getAttachment();
        if (positioner != null) {
            if (itemAttachment instanceof Part) {
                positioner.position((Part) itemAttachment, renderContext);
            } else if (itemAttachment.getRenderablePart() != null) {
                positioner.position(itemAttachment.getRenderablePart(), renderContext);
            }
        }

        for (Tuple<ModelBase, String> modelBaseStringTuple : compatibleAttachment.getAttachment()
            .getTexturedModels()) {
            Tuple<ModelBase, String> texturedModel = modelBaseStringTuple;
            Minecraft.getMinecraft().renderEngine.bindTexture(
                new ResourceLocation(this.builder.getModId() + ":textures/models/" + texturedModel.getV()));
            GL11.glPushMatrix();
            GL11.glPushAttrib(8193);
            if (compatibleAttachment.getModelPositioning() != null) {
                compatibleAttachment.getModelPositioning()
                    .accept(texturedModel.getU());
            }

            texturedModel.getU()
                .render(
                    renderContext.getPlayer(),
                    renderContext.getLimbSwing(),
                    renderContext.getFlimbSwingAmount(),
                    renderContext.getAgeInTicks(),
                    renderContext.getNetHeadYaw(),
                    renderContext.getHeadPitch(),
                    renderContext.getScale());
            GL11.glPopAttrib();
            GL11.glPopMatrix();
        }

        CustomRenderer postRenderer = compatibleAttachment.getAttachment()
            .getPostRenderer();
        if (postRenderer != null) {
            GL11.glPushMatrix();
            GL11.glPushAttrib(8193);
            postRenderer.render(renderContext);
            GL11.glPopAttrib();
            GL11.glPopMatrix();
        }

        for (CompatibleAttachment attachment : itemAttachment.getAttachments()) {
            CompatibleAttachment childAttachment = attachment;
            this.renderCompatibleAttachment(childAttachment, positioner, renderContext);
        }

        GL11.glPopAttrib();
        GL11.glPopMatrix();
    }

    public String getTextureName() {
        return this.builder.getTextureName();
    }

    public ModelBase getModel() {
        return this.builder.getModel();
    }

    public Supplier<Float> getXRotationCenterOffset() {
        return this.builder.xCenterOffset;
    }

    public Supplier<Float> getYRotationCenterOffset() {
        return this.builder.yCenterOffset;
    }

    public Supplier<Float> getZRotationCenterOffset() {
        return this.builder.zCenterOffset;
    }

    public Runnable getThrownEntityPositioning() {
        return this.builder.thrownEntityPositioning;
    }

    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return true;
    }

    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return true;
    }

    @SideOnly(Side.CLIENT)
    public void renderItem(ItemRenderType type, ItemStack weaponItemStack, Object... data) {
        int currentTextureId = 0;
        if (type == ItemRenderType.INVENTORY) {
            currentTextureId = Framebuffers.getCurrentTexture();
        }

        GL11.glPushMatrix();
        GL11.glScaled(-1.0D, -1.0D, 1.0D);
        Object player;
        if (data.length > 1 && data[1] instanceof EntityPlayer) {
            player = data[1];
        } else {
            player = Minecraft.getMinecraft().thePlayer;
        }

        RenderContext renderContext = new RenderContext(
            this.getClientModContext(),
            (EntityLivingBase) player,
            weaponItemStack);
        renderContext.setAgeInTicks(-0.4F);
        renderContext.setScale(0.08F);
        renderContext.setTransformType(TransformType.fromItemRenderType(type));
        MultipartPositioning.Positioner positioner = null;
        switch (type) {
            case ENTITY:
                this.builder.getEntityPositioning()
                    .accept(weaponItemStack);
                break;
            case INVENTORY:
                this.builder.getInventoryPositioning()
                    .accept(weaponItemStack);
                break;
            case EQUIPPED:
                this.builder.getThirdPersonPositioning()
                    .accept(renderContext);
                break;
            case EQUIPPED_FIRST_PERSON:
                StateDescriptor stateDescriptor = this.getStateDescriptor((EntityLivingBase) player, weaponItemStack);
                renderContext.setPlayerItemInstance(stateDescriptor.instance);
                MultipartPositioning multipartPositioning = stateDescriptor.stateManager.nextPositioning();
                renderContext.setTransitionProgress(multipartPositioning.getProgress());
                renderContext.setFromState(multipartPositioning.getFromState(RenderableState.class));
                renderContext.setToState(multipartPositioning.getToState(RenderableState.class));
                positioner = multipartPositioning.getPositioner();
                renderContext.capturePartPosition(Part.NONE);
                this.renderLeftArm((EntityPlayer) player, renderContext, positioner);
                positioner.applySway(stateDescriptor.rate, stateDescriptor.amplitude);
                this.renderRightArm((EntityPlayer) player, renderContext, positioner);
                positioner.position(Part.MAIN_ITEM, renderContext);

                renderContext.capturePartPosition(Part.MAIN_ITEM);
        }

        this.renderItem(weaponItemStack, renderContext, positioner);
        GL11.glPopMatrix();
        if (currentTextureId != 0) {
            Framebuffers.bindTexture(currentTextureId);
        }

    }

    private void renderRightArm(EntityPlayer player, RenderContext renderContext,
        MultipartPositioning.Positioner positioner) {
        RenderPlayer render = (RenderPlayer) RenderManager.instance.getEntityRenderObject(player);
        Minecraft.getMinecraft()
            .getTextureManager()
            .bindTexture(((AbstractClientPlayer) player).getLocationSkin());
        GL11.glPushMatrix();
        GL11.glScaled(1.0D, 1.0D, 1.0D);
        GL11.glScaled(1.0D, 1.0D, 1.0D);
        GL11.glTranslatef(-0.25F, 0.0F, 0.2F);
        GL11.glRotatef(5.0F, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(25.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
        positioner.position(Part.RIGHT_HAND, renderContext);

        renderContext.capturePartPosition(Part.RIGHT_HAND);
        GL11.glColor3f(1.0F, 1.0F, 1.0F);
        render.modelBipedMain.onGround = 0.0F;
        render.modelBipedMain.setRotationAngles(0.0F, 0.3F, 0.0F, 0.0F, 0.0F, 0.0625F, player);
        render.modelBipedMain.bipedRightArm.render(0.0625F);
        GL11.glPopMatrix();
    }

    private void renderLeftArm(EntityPlayer player, RenderContext renderContext,
        MultipartPositioning.Positioner positioner) {
        RenderPlayer render = (RenderPlayer) RenderManager.instance.getEntityRenderObject(player);
        Minecraft.getMinecraft()
            .getTextureManager()
            .bindTexture(((AbstractClientPlayer) player).getLocationSkin());
        GL11.glPushMatrix();
        GL11.glScaled(1.0D, 1.0D, 1.0D);
        GL11.glTranslatef(0.0F, -1.0F, 0.0F);
        GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
        positioner.position(Part.LEFT_HAND, renderContext);

        renderContext.capturePartPosition(Part.LEFT_HAND);
        GL11.glColor3f(1.0F, 1.0F, 1.0F);
        render.modelBipedMain.onGround = 0.0F;
        render.modelBipedMain.setRotationAngles(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, player);
        render.modelBipedMain.bipedLeftArm.render(0.0625F);
        GL11.glPopMatrix();
    }

    private class WeaponPositionProvider implements MultipartTransitionProvider {

        private WeaponPositionProvider() {}

        public List<MultipartTransition> getPositioning(RenderableState state) {
            return switch (state) {
                case SAFETY_PIN_OFF -> GrenadeRenderer.this.getComplexTransition(
                    GrenadeRenderer.this.builder.firstPersonPositioningSafetyPinOff,
                    GrenadeRenderer.this.builder.firstPersonLeftHandPositioningSafetyPinOff,
                    GrenadeRenderer.this.builder.firstPersonRightHandPositioningSafetyPinOff,
                    GrenadeRenderer.this.builder.firstPersonCustomPositioningSafetyPinOff);
                case STRIKER_LEVEL_OFF -> GrenadeRenderer.this.getSimpleTransition(
                    GrenadeRenderer.this.builder.firstPersonPositioningStrikerLeverOff,
                    GrenadeRenderer.this.builder.firstPersonLeftHandPositioningStrikerLeverOff,
                    GrenadeRenderer.this.builder.firstPersonRightHandPositioningStrikerLeverOff,
                    GrenadeRenderer.this.builder.firstPersonCustomPositioningStrikerLeverOff,
                    GrenadeRenderer.this.builder.animationDuration);
                case THROWING -> GrenadeRenderer.this.getComplexTransition(
                    GrenadeRenderer.this.builder.firstPersonPositioningThrowing,
                    GrenadeRenderer.this.builder.firstPersonLeftHandPositioningThrowing,
                    GrenadeRenderer.this.builder.firstPersonRightHandPositioningThrowing,
                    GrenadeRenderer.this.builder.firstPersonCustomPositioningThrowing);
                case THROWN -> GrenadeRenderer.this.getSimpleTransition(
                    GrenadeRenderer.this.builder.firstPersonPositioningThrown,
                    GrenadeRenderer.this.builder.firstPersonLeftHandPositioningThrown,
                    GrenadeRenderer.this.builder.firstPersonRightHandPositioningThrown,
                    GrenadeRenderer.this.builder.firstPersonCustomPositioningThrown,
                    GrenadeRenderer.this.builder.animationDuration);
                default -> GrenadeRenderer.this.getSimpleTransition(
                    GrenadeRenderer.this.builder.firstPersonPositioning,
                    GrenadeRenderer.this.builder.firstPersonLeftHandPositioning,
                    GrenadeRenderer.this.builder.firstPersonRightHandPositioning,
                    GrenadeRenderer.this.builder.firstPersonCustomPositioning,
                    GrenadeRenderer.this.builder.animationDuration);
            };
        }
    }

    private static class StateManagerKey {

        EntityLivingBase player;
        int slot = -1;

        public StateManagerKey(EntityLivingBase player, int slot) {
            this.player = player;
            this.slot = slot;
        }

        public int hashCode() {
            int result = 31 + (this.player == null ? 0 : this.player.hashCode());
            result = 31 * result + this.slot;
            return result;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            } else if (obj == null) {
                return false;
            } else if (this.getClass() != obj.getClass()) {
                return false;
            } else {
                GrenadeRenderer.StateManagerKey other = (GrenadeRenderer.StateManagerKey) obj;
                if (this.player == null) {
                    if (other.player != null) {
                        return false;
                    }
                } else if (!this.player.equals(other.player)) {
                    return false;
                }

                return this.slot == other.slot;
            }
        }
    }

    public static class Builder {

        @Getter
        private ModelBase model;
        @Getter
        private String textureName;
        @Getter
        private Consumer<ItemStack> entityPositioning;
        private Runnable thrownEntityPositioning = () -> {};
        @Getter
        private Consumer<ItemStack> inventoryPositioning;
        @Getter
        private Consumer<RenderContext> thirdPersonPositioning;
        private Consumer<RenderContext> firstPersonPositioning;
        private Consumer<RenderContext> firstPersonLeftHandPositioning;
        private Consumer<RenderContext> firstPersonRightHandPositioning;
        private final LinkedHashMap<Part, GrenadeRenderer.SimplePositioning> firstPersonCustomPositioning = new LinkedHashMap<>();
        private Consumer<RenderContext> firstPersonPositioningRunning;
        private Consumer<RenderContext> firstPersonLeftHandPositioningRunning;
        private Consumer<RenderContext> firstPersonRightHandPositioningRunning;
        private final LinkedHashMap<Part, Consumer<RenderContext>> firstPersonCustomPositioningRunning = new LinkedHashMap<>();
        private List<Transition> firstPersonPositioningSafetyPinOff;
        private List<Transition> firstPersonLeftHandPositioningSafetyPinOff;
        private List<Transition> firstPersonRightHandPositioningSafetyPinOff;
        private final LinkedHashMap<Part, List<Transition>> firstPersonCustomPositioningSafetyPinOff = new LinkedHashMap<>();
        private Consumer<RenderContext> firstPersonPositioningStrikerLeverOff;
        private Consumer<RenderContext> firstPersonLeftHandPositioningStrikerLeverOff;
        private Consumer<RenderContext> firstPersonRightHandPositioningStrikerLeverOff;
        private final LinkedHashMap<Part, GrenadeRenderer.SimplePositioning> firstPersonCustomPositioningStrikerLeverOff = new LinkedHashMap<>();
        private List<Transition> firstPersonPositioningThrowing;
        private List<Transition> firstPersonLeftHandPositioningThrowing;
        private List<Transition> firstPersonRightHandPositioningThrowing;
        private final LinkedHashMap<Part, List<Transition>> firstPersonCustomPositioningThrowing = new LinkedHashMap<>();
        private Consumer<RenderContext> firstPersonPositioningThrown;
        private Consumer<RenderContext> firstPersonLeftHandPositioningThrown;
        private Consumer<RenderContext> firstPersonRightHandPositioningThrown;
        private final LinkedHashMap<Part, GrenadeRenderer.SimplePositioning> firstPersonCustomPositioningThrown = new LinkedHashMap<>();
        private long totalTakingPinOffDuration;
        private long totalThrowingDuration;
        @Getter
        private String modId;
        private float normalRandomizingRate = DEFAULT_RANDOMIZING_RATE;
        private final float normalRandomizingAmplitude = DEFAULT_NORMAL_RANDOMIZING_AMPLITUDE;
        public int animationDuration = DEFAULT_ANIMATION_DURATION;
        private Supplier<Float> xCenterOffset = () -> 0.0F;
        private Supplier<Float> yCenterOffset = () -> 0.0F;
        private Supplier<Float> zCenterOffset = () -> 0.0F;

        public GrenadeRenderer.Builder withModId(String modId) {
            this.modId = modId;
            return this;
        }

        public GrenadeRenderer.Builder withModel(ModelBase model) {
            this.model = model;
            return this;
        }

        public GrenadeRenderer.Builder withAnimationDuration(int animationDuration) {
            this.animationDuration = animationDuration;
            return this;
        }

        public GrenadeRenderer.Builder withNormalRandomizingRate(float normalRandomizingRate) {
            this.normalRandomizingRate = normalRandomizingRate;
            return this;
        }

        public GrenadeRenderer.Builder withTextureName(String textureName) {
            this.textureName = textureName + ".png";
            return this;
        }

        public GrenadeRenderer.Builder withEntityPositioning(Consumer<ItemStack> entityPositioning) {
            this.entityPositioning = entityPositioning;
            return this;
        }

        public GrenadeRenderer.Builder withThrownEntityPositioning(Runnable throwEntityPositioning) {
            this.thrownEntityPositioning = throwEntityPositioning;
            return this;
        }

        public GrenadeRenderer.Builder withInventoryPositioning(Consumer<ItemStack> inventoryPositioning) {
            this.inventoryPositioning = inventoryPositioning;
            return this;
        }

        public GrenadeRenderer.Builder withThirdPersonPositioning(Consumer<RenderContext> thirdPersonPositioning) {
            this.thirdPersonPositioning = thirdPersonPositioning;
            return this;
        }

        public GrenadeRenderer.Builder withFirstPersonPositioning(Consumer<RenderContext> firstPersonPositioning) {
            this.firstPersonPositioning = firstPersonPositioning;
            return this;
        }

        public GrenadeRenderer.Builder withFirstPersonHandPositioning(Consumer<RenderContext> leftHand,
            Consumer<RenderContext> rightHand) {
            this.firstPersonLeftHandPositioning = leftHand;
            this.firstPersonRightHandPositioning = rightHand;
            return this;
        }

        public GrenadeRenderer.Builder withFirstPersonCustomPositioning(Part part, Part attachedTo,
            Consumer<RenderContext> positioning) {
            if (part instanceof DefaultPart) {
                throw new IllegalArgumentException("Part " + part + " is not custom");
            } else if (this.firstPersonCustomPositioning
                .put(part, new GrenadeRenderer.SimplePositioning(attachedTo, positioning)) != null) {
                    throw new IllegalArgumentException("Part " + part + " already added");
                } else {
                    return this;
                }
        }

        public GrenadeRenderer.Builder withFirstPersonPositioningRunning(
            Consumer<RenderContext> firstPersonPositioningRunning) {
            this.firstPersonPositioningRunning = firstPersonPositioningRunning;
            return this;
        }

        public GrenadeRenderer.Builder withFirstPersonHandPositioningRunning(Consumer<RenderContext> leftHand,
            Consumer<RenderContext> rightHand) {
            this.firstPersonLeftHandPositioningRunning = leftHand;
            this.firstPersonRightHandPositioningRunning = rightHand;
            return this;
        }

        public GrenadeRenderer.Builder withFirstPersonCustomPositioningRunning(Part part,
            Consumer<RenderContext> positioning) {
            if (part instanceof DefaultPart) {
                throw new IllegalArgumentException("Part " + part + " is not custom");
            } else if (this.firstPersonCustomPositioningRunning.put(part, positioning) != null) {
                throw new IllegalArgumentException("Part " + part + " already added");
            } else {
                return this;
            }
        }

        public GrenadeRenderer.Builder withFirstPersonPositioningThrown(
            Consumer<RenderContext> firstPersonPositioningThrown) {
            this.firstPersonPositioningThrown = firstPersonPositioningThrown;
            return this;
        }

        public GrenadeRenderer.Builder withFirstPersonHandPositioningThrown(Consumer<RenderContext> leftHand,
            Consumer<RenderContext> rightHand) {
            this.firstPersonLeftHandPositioningThrown = leftHand;
            this.firstPersonRightHandPositioningThrown = rightHand;
            return this;
        }

        public GrenadeRenderer.Builder withFirstPersonCustomPositioningThrown(Part part, Part attachedTo,
            Consumer<RenderContext> positioning) {
            if (part instanceof DefaultPart) {
                throw new IllegalArgumentException("Part " + part + " is not custom");
            } else if (this.firstPersonCustomPositioningThrown
                .put(part, new GrenadeRenderer.SimplePositioning(attachedTo, positioning)) != null) {
                    throw new IllegalArgumentException("Part " + part + " already added");
                } else {
                    return this;
                }
        }

        @SafeVarargs
        public final GrenadeRenderer.Builder withFirstPersonPositioningSafetyPinOff(Transition... transitions) {
            this.firstPersonPositioningSafetyPinOff = Arrays.asList(transitions);
            return this;
        }

        @SafeVarargs
        public final GrenadeRenderer.Builder withFirstPersonPositioningThrowing(Transition... transitions) {
            this.firstPersonPositioningThrowing = Arrays.asList(transitions);
            return this;
        }

        public GrenadeRenderer.Builder withFirstPersonPositioningStrikerLeverOff(
            Consumer<RenderContext> firstPersonPositioningStrikerLeverOff) {
            this.firstPersonPositioningStrikerLeverOff = firstPersonPositioningStrikerLeverOff;
            return this;
        }

        @SafeVarargs
        public final GrenadeRenderer.Builder withFirstPersonLeftHandPositioningSafetyPinOff(Transition... transitions) {
            this.firstPersonLeftHandPositioningSafetyPinOff = Arrays.asList(transitions);
            return this;
        }

        @SafeVarargs
        public final GrenadeRenderer.Builder withFirstPersonLeftHandPositioningThrowing(Transition... transitions) {
            this.firstPersonLeftHandPositioningThrowing = Arrays.asList(transitions);
            return this;
        }

        @SafeVarargs
        public final GrenadeRenderer.Builder withFirstPersonRightHandPositioningThrowing(Transition... transitions) {
            this.firstPersonRightHandPositioningThrowing = Arrays.asList(transitions);
            return this;
        }

        @SafeVarargs
        public final GrenadeRenderer.Builder withFirstPersonRightHandPositioningSafetyPinOff(
            Transition... transitions) {
            this.firstPersonRightHandPositioningSafetyPinOff = Arrays.asList(transitions);
            return this;
        }

        public GrenadeRenderer.Builder withFirstPersonHandPositioningStrikerLevelOff(Consumer<RenderContext> leftHand,
            Consumer<RenderContext> rightHand) {
            this.firstPersonLeftHandPositioningStrikerLeverOff = leftHand;
            this.firstPersonRightHandPositioningStrikerLeverOff = rightHand;
            return this;
        }

        public GrenadeRenderer.Builder withFirstPersonCustomPositioningStrikerLeverOff(Part part, Part attachedTo,
            Consumer<RenderContext> positioning) {
            if (part instanceof DefaultPart) {
                throw new IllegalArgumentException("Part " + part + " is not custom");
            } else if (this.firstPersonCustomPositioningStrikerLeverOff
                .put(part, new GrenadeRenderer.SimplePositioning(attachedTo, positioning)) != null) {
                    throw new IllegalArgumentException("Part " + part + " already added");
                } else {
                    return this;
                }
        }

        @SafeVarargs
        public final GrenadeRenderer.Builder withFirstPersonCustomPositioningSafetyPinOff(Part part,
            Transition... transitions) {
            if (part instanceof DefaultPart) {
                throw new IllegalArgumentException("Part " + part + " is not custom");
            } else {
                this.firstPersonCustomPositioningSafetyPinOff.put(part, Arrays.asList(transitions));
                return this;
            }
        }

        @SafeVarargs
        public final GrenadeRenderer.Builder withFirstPersonCustomPositioningThrowing(Part part,
            Transition... transitions) {
            if (part instanceof DefaultPart) {
                throw new IllegalArgumentException("Part " + part + " is not custom");
            } else {
                this.firstPersonCustomPositioningThrowing.put(part, Arrays.asList(transitions));
                return this;
            }
        }

        public GrenadeRenderer.Builder withEntityRotationCenterOffsets(Supplier<Float> xCenterOffset,
            Supplier<Float> yCenterOffset, Supplier<Float> zCenterOffset) {
            this.xCenterOffset = xCenterOffset;
            this.yCenterOffset = yCenterOffset;
            this.zCenterOffset = zCenterOffset;
            return this;
        }

        public GrenadeRenderer build() {
            if (!(FMLCommonHandler.instance()
                .getSide() == Side.CLIENT)) {
                return null;
            } else if (this.modId == null) {
                throw new IllegalStateException("ModId is not set");
            } else {
                if (this.inventoryPositioning == null) {
                    this.inventoryPositioning = (itemStack) -> { GL11.glTranslatef(0.0F, 0.12F, 0.0F); };
                }

                if (this.entityPositioning == null) {
                    this.entityPositioning = (itemStack) -> {};
                }

                GrenadeRenderer renderer = new GrenadeRenderer(this);
                if (this.firstPersonPositioning == null) {
                    this.firstPersonPositioning = (renderContext) -> {};
                }

                if (this.firstPersonPositioningSafetyPinOff == null) {
                    this.firstPersonPositioningSafetyPinOff = Collections
                        .singletonList(new Transition(this.firstPersonPositioning, this.animationDuration));
                }

                if (this.firstPersonPositioningThrowing == null) {
                    this.firstPersonPositioningThrowing = Collections
                        .singletonList(new Transition(this.firstPersonPositioning, this.animationDuration));
                }

                Iterator var2;
                Transition t;
                for (var2 = this.firstPersonPositioningSafetyPinOff.iterator(); var2
                    .hasNext(); this.totalTakingPinOffDuration += t.getPause()) {
                    t = (Transition) var2.next();
                    this.totalTakingPinOffDuration += t.getDuration();
                }

                for (var2 = this.firstPersonPositioningThrowing.iterator(); var2
                    .hasNext(); this.totalThrowingDuration += t.getPause()) {
                    t = (Transition) var2.next();
                    this.totalThrowingDuration += t.getDuration();
                }

                if (this.firstPersonPositioningRunning == null) {
                    this.firstPersonPositioningRunning = this.firstPersonPositioning;
                }

                if (this.firstPersonPositioningStrikerLeverOff == null) {
                    if (this.firstPersonPositioningSafetyPinOff != null
                        && !this.firstPersonPositioningSafetyPinOff.isEmpty()) {
                        this.firstPersonPositioningStrikerLeverOff = this.firstPersonPositioningSafetyPinOff
                            .get(this.firstPersonPositioningSafetyPinOff.size() - 1)
                            .getItemPositioning();
                    }

                    if (this.firstPersonPositioningStrikerLeverOff == null) {
                        this.firstPersonPositioningStrikerLeverOff = this.firstPersonPositioning;
                    }
                }

                if (this.firstPersonPositioningThrown == null) {
                    if (this.firstPersonPositioningThrowing != null && !this.firstPersonPositioningThrowing.isEmpty()) {
                        this.firstPersonPositioningThrown = this.firstPersonPositioningThrowing
                            .get(this.firstPersonPositioningThrowing.size() - 1)
                            .getItemPositioning();
                    }

                    if (this.firstPersonPositioningThrown == null) {
                        this.firstPersonPositioningThrown = this.firstPersonPositioning;
                    }
                }

                if (this.thirdPersonPositioning == null) {
                    this.thirdPersonPositioning = (context) -> {
                        GL11.glTranslatef(-0.4F, 0.2F, 0.4F);
                        GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(70.0F, 1.0F, 0.0F, 0.0F);
                    };
                }

                if (this.firstPersonLeftHandPositioning == null) {
                    this.firstPersonLeftHandPositioning = (context) -> {};
                }

                if (this.firstPersonLeftHandPositioningSafetyPinOff == null) {
                    this.firstPersonLeftHandPositioningSafetyPinOff = this.firstPersonPositioningSafetyPinOff.stream()
                        .map((tx) -> new Transition((c) -> {}, 0L))
                        .collect(Collectors.toList());
                }

                if (this.firstPersonLeftHandPositioningThrowing == null) {
                    this.firstPersonLeftHandPositioningThrowing = this.firstPersonPositioningThrowing.stream()
                        .map((tx) -> new Transition((c) -> {}, 0L))
                        .collect(Collectors.toList());
                }

                if (this.firstPersonRightHandPositioningThrowing == null) {
                    this.firstPersonRightHandPositioningThrowing = this.firstPersonPositioningThrowing.stream()
                        .map((tx) -> new Transition((c) -> {}, 0L))
                        .collect(Collectors.toList());
                }

                if (this.firstPersonLeftHandPositioningRunning == null) {
                    this.firstPersonLeftHandPositioningRunning = this.firstPersonLeftHandPositioning;
                }

                if (this.firstPersonLeftHandPositioningStrikerLeverOff == null) {
                    if (this.firstPersonLeftHandPositioningSafetyPinOff != null
                        && !this.firstPersonLeftHandPositioningSafetyPinOff.isEmpty()) {
                        this.firstPersonLeftHandPositioningStrikerLeverOff = this.firstPersonLeftHandPositioningSafetyPinOff
                            .get(this.firstPersonLeftHandPositioningSafetyPinOff.size() - 1)
                            .getItemPositioning();
                    }

                    if (this.firstPersonLeftHandPositioningStrikerLeverOff == null) {
                        this.firstPersonLeftHandPositioningStrikerLeverOff = this.firstPersonLeftHandPositioning;
                    }
                }

                if (this.firstPersonLeftHandPositioningThrown == null) {
                    if (this.firstPersonLeftHandPositioningThrowing != null
                        && !this.firstPersonLeftHandPositioningThrowing.isEmpty()) {
                        this.firstPersonLeftHandPositioningThrown = this.firstPersonLeftHandPositioningThrowing
                            .get(this.firstPersonLeftHandPositioningThrowing.size() - 1)
                            .getItemPositioning();
                    }

                    if (this.firstPersonLeftHandPositioningThrown == null) {
                        this.firstPersonLeftHandPositioningThrown = this.firstPersonLeftHandPositioning;
                    }
                }

                if (this.firstPersonRightHandPositioning == null) {
                    this.firstPersonRightHandPositioning = (context) -> {};
                }

                if (this.firstPersonRightHandPositioningSafetyPinOff == null) {
                    this.firstPersonRightHandPositioningSafetyPinOff = this.firstPersonPositioningSafetyPinOff.stream()
                        .map((tx) -> new Transition((c) -> {}, 0L))
                        .collect(Collectors.toList());
                }

                if (this.firstPersonRightHandPositioningThrowing == null) {
                    this.firstPersonRightHandPositioningThrowing = this.firstPersonPositioningThrowing.stream()
                        .map((tx) -> new Transition((c) -> {}, 0L))
                        .collect(Collectors.toList());
                }

                if (this.firstPersonRightHandPositioningRunning == null) {
                    this.firstPersonRightHandPositioningRunning = this.firstPersonRightHandPositioning;
                }

                if (this.firstPersonRightHandPositioningStrikerLeverOff == null) {
                    if (this.firstPersonRightHandPositioningSafetyPinOff != null
                        && !this.firstPersonRightHandPositioningSafetyPinOff.isEmpty()) {
                        this.firstPersonRightHandPositioningStrikerLeverOff = this.firstPersonRightHandPositioningSafetyPinOff
                            .get(this.firstPersonRightHandPositioningSafetyPinOff.size() - 1)
                            .getItemPositioning();
                    }

                    if (this.firstPersonRightHandPositioningStrikerLeverOff == null) {
                        this.firstPersonRightHandPositioningStrikerLeverOff = this.firstPersonRightHandPositioning;
                    }
                }

                if (this.firstPersonRightHandPositioningThrown == null) {
                    if (this.firstPersonRightHandPositioningThrowing != null
                        && !this.firstPersonRightHandPositioningThrowing.isEmpty()) {
                        this.firstPersonRightHandPositioningThrown = this.firstPersonRightHandPositioningThrowing
                            .get(this.firstPersonRightHandPositioningThrowing.size() - 1)
                            .getItemPositioning();
                    }

                    if (this.firstPersonRightHandPositioningThrown == null) {
                        this.firstPersonRightHandPositioningThrown = this.firstPersonRightHandPositioning;
                    }
                }

                this.firstPersonCustomPositioningSafetyPinOff.forEach((p, tx) -> {
                    if (tx.size() != this.firstPersonPositioningSafetyPinOff.size()) {
                        throw new IllegalStateException(
                            "Custom reloading transition number mismatch. Expected "
                                + this.firstPersonPositioningSafetyPinOff.size()
                                + ", actual: "
                                + tx.size());
                    }
                });
                this.firstPersonCustomPositioningThrowing.forEach((p, tx) -> {
                    if (tx.size() != this.firstPersonPositioningThrowing.size()) {
                        throw new IllegalStateException(
                            "Custom reloading transition number mismatch. Expected "
                                + this.firstPersonPositioningThrowing.size()
                                + ", actual: "
                                + tx.size());
                    }
                });
                if (!this.firstPersonCustomPositioning.isEmpty()
                    && this.firstPersonCustomPositioningStrikerLeverOff.isEmpty()) {
                    this.firstPersonCustomPositioning.forEach((part, pos) -> {
                        this.firstPersonCustomPositioningStrikerLeverOff
                            .put(part, new GrenadeRenderer.SimplePositioning(null, pos.positioning));
                    });
                }

                if (!this.firstPersonCustomPositioning.isEmpty() && this.firstPersonCustomPositioningThrown.isEmpty()) {
                    this.firstPersonCustomPositioning.forEach(
                        (part, pos) -> {
                            this.firstPersonCustomPositioningThrown
                                .put(part, new GrenadeRenderer.SimplePositioning(null, pos.positioning));
                        });
                }

                return renderer;
            }
        }

    }

    private static class SimplePositioning {

        Part attachedTo;
        Consumer<RenderContext> positioning;

        SimplePositioning(Part attachedTo, Consumer<RenderContext> positioning) {
            this.attachedTo = attachedTo;
            this.positioning = positioning;
        }
    }

    protected static class StateDescriptor {

        protected MultipartRenderStateManager stateManager;
        protected float rate;
        protected float amplitude = 0.04F;
        private final PlayerGrenadeInstance instance;

        public StateDescriptor(PlayerGrenadeInstance instance, MultipartRenderStateManager stateManager, float rate,
            float amplitude) {
            this.instance = instance;
            this.stateManager = stateManager;
            this.rate = rate;
            this.amplitude = amplitude;
        }
    }
}
