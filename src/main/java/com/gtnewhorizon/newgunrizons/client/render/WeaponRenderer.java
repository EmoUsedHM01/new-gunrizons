package com.gtnewhorizon.newgunrizons.client.render;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.shader.Framebuffer;
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
import com.gtnewhorizon.newgunrizons.attachment.ModelWithAttachments;
import com.gtnewhorizon.newgunrizons.attachment.Part;
import com.gtnewhorizon.newgunrizons.client.animation.MultipartPositioning;
import com.gtnewhorizon.newgunrizons.client.animation.MultipartRenderStateManager;
import com.gtnewhorizon.newgunrizons.client.animation.MultipartTransition;
import com.gtnewhorizon.newgunrizons.client.animation.MultipartTransitionProvider;
import com.gtnewhorizon.newgunrizons.client.animation.Transition;
import com.gtnewhorizon.newgunrizons.client.shader.Framebuffers;
import com.gtnewhorizon.newgunrizons.config.ClientModContext;
import com.gtnewhorizon.newgunrizons.state.RenderableState;
import com.gtnewhorizon.newgunrizons.util.Tuple;
import com.gtnewhorizon.newgunrizons.weapon.AsyncWeaponState;
import com.gtnewhorizon.newgunrizons.weapon.PlayerItemInstance;
import com.gtnewhorizon.newgunrizons.weapon.PlayerWeaponInstance;
import com.gtnewhorizon.newgunrizons.weapon.Weapon;
import com.gtnewhorizon.newgunrizons.weapon.WeaponState;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class WeaponRenderer implements IItemRenderer {

    private static final Logger logger = LogManager.getLogger(WeaponRenderer.class);
    private static final float DEFAULT_RANDOMIZING_RATE = 0.33F;
    private static final float DEFAULT_RANDOMIZING_FIRING_RATE = 20.0F;
    private static final float DEFAULT_RANDOMIZING_ZOOM_RATE = 0.25F;
    private static final float DEFAULT_NORMAL_RANDOMIZING_AMPLITUDE = 0.06F;
    private static final float DEFAULT_ZOOM_RANDOMIZING_AMPLITUDE = 0.005F;
    private static final float DEFAULT_FIRING_RANDOMIZING_AMPLITUDE = 0.03F;
    private static final int DEFAULT_ANIMATION_DURATION = 250;
    private static final int DEFAULT_RECOIL_ANIMATION_DURATION = 100;
    private static final int DEFAULT_SHOOTING_ANIMATION_DURATION = 100;
    private static final int DEFAULT_ITERATION_COMPLETED_ANIMATION_DURATION = 100;
    private static final int DEFAULT_PREPARE_FIRST_LOAD_ITERATION_ANIMATION_DURATION = 100;
    private static final int DEFAULT_ALL_LOAD_ITERATION_ANIMATIONS_COMPLETED_DURATION = 100;
    private static final int INVENTORY_TEXTURE_WIDTH = 256;
    private static final int INVENTORY_TEXTURE_HEIGHT = 256;
    private final WeaponRenderer.Builder builder;
    private final Map<EntityLivingBase, MultipartRenderStateManager> firstPersonStateManagers;
    private final MultipartTransitionProvider weaponTransitionProvider;
    protected ClientModContext clientModContext;

    private WeaponRenderer(WeaponRenderer.Builder builder) {
        this.builder = builder;
        this.firstPersonStateManagers = new HashMap<>();
        this.weaponTransitionProvider = new WeaponRenderer.WeaponPositionProvider();
    }

    public long getTotalReloadingDuration() {
        return this.builder.totalReloadingDuration;
    }

    public long getTotalUnloadingDuration() {
        return this.builder.totalUnloadingDuration;
    }

    protected ClientModContext getClientModContext() {
        return this.clientModContext;
    }

    public void setClientModContext(ClientModContext clientModContext) {
        this.clientModContext = clientModContext;
    }

    protected StateDescriptor getStateDescriptor(EntityLivingBase player, ItemStack itemStack) {
        float amplitude = this.builder.normalRandomizingAmplitude;
        float rate = this.builder.normalRandomizingRate;
        RenderableState currentState = null;
        PlayerItemInstance<?> playerItemInstance = this.clientModContext.getPlayerItemInstanceRegistry()
            .getItemInstance(player, itemStack);
        PlayerWeaponInstance playerWeaponInstance = null;
        if (playerItemInstance != null && playerItemInstance instanceof PlayerWeaponInstance
            && playerItemInstance.getItem() == itemStack.getItem()) {
            playerWeaponInstance = (PlayerWeaponInstance) playerItemInstance;
        } else {
            logger.error(
                "Invalid or mismatching item. Player item instance: {}. Item stack: {}",
                playerItemInstance,
                itemStack);
        }

        if (playerWeaponInstance != null) {
            AsyncWeaponState asyncWeaponState = this.getNextNonExpiredState(playerWeaponInstance);
            switch (asyncWeaponState.getState()) {
                case RECOILED:
                    if (playerWeaponInstance.isAutomaticModeEnabled() && !this.hasRecoilPositioning()) {
                        if (playerWeaponInstance.isAimed()) {
                            currentState = RenderableState.ZOOMING;
                            rate = this.builder.firingRandomizingRate;
                            amplitude = this.builder.zoomRandomizingAmplitude;
                        } else {
                            currentState = RenderableState.NORMAL;
                            rate = this.builder.firingRandomizingRate;
                            amplitude = this.builder.firingRandomizingAmplitude;
                        }
                    } else if (playerWeaponInstance.isAimed()) {
                        currentState = RenderableState.ZOOMING_RECOILED;
                        amplitude = this.builder.zoomRandomizingAmplitude;
                    } else {
                        currentState = RenderableState.RECOILED;
                    }
                    break;
                case PAUSED:
                    if (playerWeaponInstance.isAutomaticModeEnabled() && !this.hasRecoilPositioning()) {
                        boolean isLongPaused = (float) (System.currentTimeMillis() - asyncWeaponState.getTimestamp())
                            > 50.0F / playerWeaponInstance.getFireRate() && asyncWeaponState.isInfinite();
                        if (playerWeaponInstance.isAimed()) {
                            currentState = RenderableState.ZOOMING;
                            if (!isLongPaused) {
                                rate = this.builder.firingRandomizingRate;
                            }

                            amplitude = this.builder.zoomRandomizingAmplitude;
                        } else {
                            currentState = RenderableState.NORMAL;
                            if (!isLongPaused) {
                                rate = this.builder.firingRandomizingRate;
                                amplitude = this.builder.firingRandomizingAmplitude;
                            }
                        }
                    } else if (playerWeaponInstance.isAimed()) {
                        currentState = RenderableState.ZOOMING_SHOOTING;
                        amplitude = this.builder.zoomRandomizingAmplitude;
                    } else {
                        currentState = RenderableState.SHOOTING;
                    }
                    break;
                case UNLOAD_PREPARING:
                case UNLOAD_REQUESTED:
                case UNLOAD:
                    currentState = RenderableState.UNLOADING;
                    break;
                case LOAD:
                    currentState = RenderableState.RELOADING;
                    break;
                case LOAD_ITERATION:
                    currentState = RenderableState.LOAD_ITERATION;
                    break;
                case LOAD_ITERATION_COMPLETED:
                    currentState = RenderableState.LOAD_ITERATION_COMPLETED;
                    break;
                case ALL_LOAD_ITERATIONS_COMPLETED:
                    currentState = RenderableState.ALL_LOAD_ITERATIONS_COMPLETED;
                    break;
                case EJECTING:
                    currentState = RenderableState.EJECT_SPENT_ROUND;
                    break;
                case MODIFYING:
                case MODIFYING_REQUESTED:
                case NEXT_ATTACHMENT:
                case NEXT_ATTACHMENT_REQUESTED:
                    currentState = RenderableState.MODIFYING;
                    break;
                default:
                    if (player.isSprinting() && this.builder.firstPersonPositioningRunning != null) {
                        currentState = RenderableState.RUNNING;
                    } else if (playerWeaponInstance.isAimed()) {
                        currentState = RenderableState.ZOOMING;
                        rate = this.builder.zoomRandomizingRate;
                        amplitude = this.builder.zoomRandomizingAmplitude;
                    }
            }
        }

        if (currentState == null) {
            currentState = RenderableState.NORMAL;
        }

        MultipartRenderStateManager stateManager = this.firstPersonStateManagers.get(player);
        if (stateManager == null) {
            stateManager = new MultipartRenderStateManager(currentState, this.weaponTransitionProvider);
            this.firstPersonStateManagers.put(player, stateManager);
        } else {
            stateManager.setState(
                currentState,
                true,
                currentState == RenderableState.SHOOTING || currentState == RenderableState.ZOOMING_SHOOTING
                    || currentState == RenderableState.RUNNING
                    || currentState == RenderableState.ZOOMING);
        }

        return new StateDescriptor(playerWeaponInstance, stateManager, rate, amplitude);
    }

    private AsyncWeaponState getNextNonExpiredState(PlayerWeaponInstance playerWeaponState) {
        AsyncWeaponState asyncWeaponState = null;

        while ((asyncWeaponState = playerWeaponState.nextHistoryState()) != null) {
            if (System.currentTimeMillis() < asyncWeaponState.getTimestamp() + asyncWeaponState.getDuration()
                && (asyncWeaponState.getState() != WeaponState.FIRING
                    || !this.hasRecoilPositioning() && playerWeaponState.isAutomaticModeEnabled())) {
                break;
            }
        }

        return asyncWeaponState;
    }

    private Consumer<RenderContext> createWeaponPartPositionFunction(Transition t) {
        if (t == null) {
            return (context) -> {};
        } else {
            Consumer<RenderContext> weaponPositionFunction = t.getItemPositioning();
            return weaponPositionFunction != null ? weaponPositionFunction::accept : (context) -> {};
        }
    }

    private Consumer<RenderContext> createWeaponPartPositionFunction(Consumer<RenderContext> weaponPositionFunction) {
        return weaponPositionFunction != null ? weaponPositionFunction::accept : (context) -> {};
    }

    private List<MultipartTransition> getComplexTransition(List<Transition> wt, List<Transition> lht,
        List<Transition> rht, LinkedHashMap<Part, List<Transition>> custom) {
        List<MultipartTransition> result = new ArrayList<>();

        for (int i = 0; i < wt.size(); ++i) {
            Transition p = wt.get(i);
            Transition l = lht.get(i);
            Transition r = rht.get(i);
            MultipartTransition t = (new MultipartTransition(p.getDuration(), p.getPause()))
                .withPartPositionFunction(Part.MAIN_ITEM, this.createWeaponPartPositionFunction(p))
                .withPartPositionFunction(Part.LEFT_HAND, this.createWeaponPartPositionFunction(l))
                .withPartPositionFunction(Part.RIGHT_HAND, this.createWeaponPartPositionFunction(r));

            Entry e;
            Transition partTransition;
            for (Iterator var13 = custom.entrySet()
                .iterator(); var13.hasNext(); t.withPartPositionFunction(
                    (Part) e.getKey(),
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
        Consumer<RenderContext> rh, LinkedHashMap<Part, Consumer<RenderContext>> custom, int duration) {
        MultipartTransition mt = (new MultipartTransition(duration, 0L))
            .withPartPositionFunction(Part.MAIN_ITEM, this.createWeaponPartPositionFunction(w))
            .withPartPositionFunction(Part.LEFT_HAND, this.createWeaponPartPositionFunction(lh))
            .withPartPositionFunction(Part.RIGHT_HAND, this.createWeaponPartPositionFunction(rh));
        custom.forEach(
            (part, position) -> {
                mt.withPartPositionFunction(part, this.createWeaponPartPositionFunction(position));
            });
        return Collections.singletonList(mt);
    }

    public void renderItem(ItemStack weaponItemStack, RenderContext renderContext,
        MultipartPositioning.Positioner positioner) {
        List<CompatibleAttachment> attachments = null;
        if (this.builder.getModel() instanceof ModelWithAttachments) {
            attachments = ((Weapon) weaponItemStack.getItem())
                .getActiveAttachments(renderContext.getPlayer(), weaponItemStack);
        }

        if (this.builder.getTextureName() != null) {
            Minecraft.getMinecraft().renderEngine.bindTexture(
                new ResourceLocation(this.builder.getModId() + ":textures/models/" + this.builder.getTextureName()));
        } else {
            Weapon weapon = (Weapon) weaponItemStack.getItem();
            String textureName = weapon.getTextureName();

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
        if (attachments != null) {
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
        if (compatibleAttachment.getPositioning() != null) {
            compatibleAttachment.getPositioning()
                .accept(renderContext.getPlayer(), renderContext.getWeapon());
        }

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

    public boolean hasRecoilPositioning() {
        return this.builder.hasRecoilPositioningDefined;
    }

    public long getTotalLoadIterationDuration() {
        return this.builder.totalLoadIterationDuration;
    }

    public long getPrepareFirstLoadIterationAnimationDuration() {
        return this.builder.prepareFirstLoadIterationAnimationDuration;
    }

    public long getAllLoadIterationAnimationsCompletedDuration() {
        return this.builder.allLoadIterationAnimationsCompletedDuration;
    }

    private class WeaponPositionProvider implements MultipartTransitionProvider {

        private WeaponPositionProvider() {}

        public List<MultipartTransition> getPositioning(RenderableState state) {
            return switch (state) {
                case MODIFYING -> WeaponRenderer.this.getSimpleTransition(
                    WeaponRenderer.this.builder.firstPersonPositioningModifying,
                    WeaponRenderer.this.builder.firstPersonLeftHandPositioningModifying,
                    WeaponRenderer.this.builder.firstPersonRightHandPositioningModifying,
                    WeaponRenderer.this.builder.firstPersonCustomPositioning,
                    250);
                case RUNNING -> WeaponRenderer.this.getSimpleTransition(
                    WeaponRenderer.this.builder.firstPersonPositioningRunning,
                    WeaponRenderer.this.builder.firstPersonLeftHandPositioningRunning,
                    WeaponRenderer.this.builder.firstPersonRightHandPositioningRunning,
                    WeaponRenderer.this.builder.firstPersonCustomPositioning,
                    250);
                case UNLOADING -> WeaponRenderer.this.getComplexTransition(
                    WeaponRenderer.this.builder.firstPersonPositioningUnloading,
                    WeaponRenderer.this.builder.firstPersonLeftHandPositioningUnloading,
                    WeaponRenderer.this.builder.firstPersonRightHandPositioningUnloading,
                    WeaponRenderer.this.builder.firstPersonCustomPositioningUnloading);
                case RELOADING -> WeaponRenderer.this.getComplexTransition(
                    WeaponRenderer.this.builder.firstPersonPositioningReloading,
                    WeaponRenderer.this.builder.firstPersonLeftHandPositioningReloading,
                    WeaponRenderer.this.builder.firstPersonRightHandPositioningReloading,
                    WeaponRenderer.this.builder.firstPersonCustomPositioningReloading);
                case LOAD_ITERATION -> WeaponRenderer.this.getComplexTransition(
                    WeaponRenderer.this.builder.firstPersonPositioningLoadIteration,
                    WeaponRenderer.this.builder.firstPersonLeftHandPositioningLoadIteration,
                    WeaponRenderer.this.builder.firstPersonRightHandPositioningLoadIteration,
                    WeaponRenderer.this.builder.firstPersonCustomPositioningLoadIteration);
                case LOAD_ITERATION_COMPLETED -> WeaponRenderer.this.getSimpleTransition(
                    WeaponRenderer.this.builder.firstPersonPositioningLoadIterationCompleted,
                    WeaponRenderer.this.builder.firstPersonLeftHandPositioningLoadIterationCompleted,
                    WeaponRenderer.this.builder.firstPersonRightHandPositioningLoadIterationCompleted,
                    WeaponRenderer.this.builder.firstPersonCustomPositioningLoadIterationCompleted,
                    WeaponRenderer.this.builder.loadIterationCompletedAnimationDuration);
                case ALL_LOAD_ITERATIONS_COMPLETED -> WeaponRenderer.this.getComplexTransition(
                    WeaponRenderer.this.builder.firstPersonPositioningAllLoadIterationsCompleted,
                    WeaponRenderer.this.builder.firstPersonLeftHandPositioningAllLoadIterationsCompleted,
                    WeaponRenderer.this.builder.firstPersonRightHandPositioningAllLoadIterationsCompleted,
                    WeaponRenderer.this.builder.firstPersonCustomPositioningLoadIterationsCompleted);
                case RECOILED -> WeaponRenderer.this.getSimpleTransition(
                    WeaponRenderer.this.builder.firstPersonPositioningRecoiled,
                    WeaponRenderer.this.builder.firstPersonLeftHandPositioningRecoiled,
                    WeaponRenderer.this.builder.firstPersonRightHandPositioningRecoiled,
                    WeaponRenderer.this.builder.firstPersonCustomPositioningRecoiled,
                    WeaponRenderer.this.builder.recoilAnimationDuration);
                case SHOOTING -> WeaponRenderer.this.getSimpleTransition(
                    WeaponRenderer.this.builder.firstPersonPositioningShooting,
                    WeaponRenderer.this.builder.firstPersonLeftHandPositioningShooting,
                    WeaponRenderer.this.builder.firstPersonRightHandPositioningShooting,
                    WeaponRenderer.this.builder.firstPersonCustomPositioning,
                    WeaponRenderer.this.builder.shootingAnimationDuration);
                case EJECT_SPENT_ROUND -> WeaponRenderer.this.getComplexTransition(
                    WeaponRenderer.this.builder.firstPersonPositioningEjectSpentRound,
                    WeaponRenderer.this.builder.firstPersonLeftHandPositioningEjectSpentRound,
                    WeaponRenderer.this.builder.firstPersonRightHandPositioningEjectSpentRound,
                    WeaponRenderer.this.builder.firstPersonCustomPositioningEjectSpentRound);
                case NORMAL -> WeaponRenderer.this.getSimpleTransition(
                    WeaponRenderer.this.builder.firstPersonPositioning,
                    WeaponRenderer.this.builder.firstPersonLeftHandPositioning,
                    WeaponRenderer.this.builder.firstPersonRightHandPositioning,
                    WeaponRenderer.this.builder.firstPersonCustomPositioning,
                    250);
                case ZOOMING -> WeaponRenderer.this.getSimpleTransition(
                    WeaponRenderer.this.builder.firstPersonPositioningZooming,
                    WeaponRenderer.this.builder.firstPersonLeftHandPositioningZooming,
                    WeaponRenderer.this.builder.firstPersonRightHandPositioningZooming,
                    WeaponRenderer.this.builder.firstPersonCustomPositioning,
                    250);
                case ZOOMING_SHOOTING -> WeaponRenderer.this.getSimpleTransition(
                    WeaponRenderer.this.builder.firstPersonPositioningZoomingShooting,
                    WeaponRenderer.this.builder.firstPersonLeftHandPositioningZooming,
                    WeaponRenderer.this.builder.firstPersonRightHandPositioningZooming,
                    WeaponRenderer.this.builder.firstPersonCustomPositioningZoomingShooting,
                    60);
                case ZOOMING_RECOILED -> WeaponRenderer.this.getSimpleTransition(
                    WeaponRenderer.this.builder.firstPersonPositioningZoomingRecoiled,
                    WeaponRenderer.this.builder.firstPersonLeftHandPositioningZooming,
                    WeaponRenderer.this.builder.firstPersonRightHandPositioningZooming,
                    WeaponRenderer.this.builder.firstPersonCustomPositioningZoomingRecoiled,
                    60);
                default -> null;
            };
        }

    }

    public static class Builder {

        private final Random random = new Random();
        private ModelBase model;
        private String textureName;
        private float weaponProximity;
        private float yOffsetZoom;
        private float xOffsetZoom = 0.69F;
        private Consumer<ItemStack> entityPositioning;
        private Consumer<ItemStack> inventoryPositioning;
        private Consumer<RenderContext> thirdPersonPositioning;
        private Consumer<RenderContext> firstPersonPositioning;
        private Consumer<RenderContext> firstPersonPositioningZooming;
        private Consumer<RenderContext> firstPersonPositioningRunning;
        private Consumer<RenderContext> firstPersonPositioningModifying;
        private Consumer<RenderContext> firstPersonPositioningRecoiled;
        private Consumer<RenderContext> firstPersonPositioningShooting;
        private Consumer<RenderContext> firstPersonPositioningZoomingRecoiled;
        private Consumer<RenderContext> firstPersonPositioningZoomingShooting;
        private Consumer<RenderContext> firstPersonPositioningLoadIterationCompleted;
        private Consumer<RenderContext> firstPersonLeftHandPositioning;
        private Consumer<RenderContext> firstPersonLeftHandPositioningZooming;
        private Consumer<RenderContext> firstPersonLeftHandPositioningRunning;
        private Consumer<RenderContext> firstPersonLeftHandPositioningModifying;
        private Consumer<RenderContext> firstPersonLeftHandPositioningRecoiled;
        private Consumer<RenderContext> firstPersonLeftHandPositioningShooting;
        private Consumer<RenderContext> firstPersonLeftHandPositioningLoadIterationCompleted;
        private Consumer<RenderContext> firstPersonRightHandPositioning;
        private Consumer<RenderContext> firstPersonRightHandPositioningZooming;
        private Consumer<RenderContext> firstPersonRightHandPositioningRunning;
        private Consumer<RenderContext> firstPersonRightHandPositioningModifying;
        private Consumer<RenderContext> firstPersonRightHandPositioningRecoiled;
        private Consumer<RenderContext> firstPersonRightHandPositioningShooting;
        private Consumer<RenderContext> firstPersonRightHandPositioningLoadIterationCompleted;
        private List<Transition> firstPersonPositioningReloading;
        private List<Transition> firstPersonLeftHandPositioningReloading;
        private List<Transition> firstPersonRightHandPositioningReloading;
        private List<Transition> firstPersonPositioningUnloading;
        private List<Transition> firstPersonLeftHandPositioningUnloading;
        private List<Transition> firstPersonRightHandPositioningUnloading;
        private List<Transition> firstPersonPositioningLoadIteration;
        private List<Transition> firstPersonLeftHandPositioningLoadIteration;
        private List<Transition> firstPersonRightHandPositioningLoadIteration;
        private List<Transition> firstPersonPositioningAllLoadIterationsCompleted;
        private List<Transition> firstPersonLeftHandPositioningAllLoadIterationsCompleted;
        private List<Transition> firstPersonRightHandPositioningAllLoadIterationsCompleted;
        private long totalReloadingDuration;
        private long totalUnloadingDuration;
        private long totalLoadIterationDuration;
        private String modId;
        private int recoilAnimationDuration = 100;
        private int shootingAnimationDuration = 100;
        private final int loadIterationCompletedAnimationDuration = 100;
        private int prepareFirstLoadIterationAnimationDuration = 100;
        private int allLoadIterationAnimationsCompletedDuration = 100;
        private float normalRandomizingRate = 0.33F;
        private float firingRandomizingRate = 20.0F;
        private float zoomRandomizingRate = 0.25F;
        private final float normalRandomizingAmplitude = 0.06F;
        private float zoomRandomizingAmplitude = 0.005F;
        private float firingRandomizingAmplitude = 0.03F;
        private final LinkedHashMap<Part, Consumer<RenderContext>> firstPersonCustomPositioning = new LinkedHashMap<>();
        private final LinkedHashMap<Part, List<Transition>> firstPersonCustomPositioningUnloading = new LinkedHashMap<>();
        private final LinkedHashMap<Part, List<Transition>> firstPersonCustomPositioningReloading = new LinkedHashMap<>();
        private final LinkedHashMap<Part, List<Transition>> firstPersonCustomPositioningLoadIteration = new LinkedHashMap<>();
        private final LinkedHashMap<Part, List<Transition>> firstPersonCustomPositioningLoadIterationsCompleted = new LinkedHashMap<>();
        private final LinkedHashMap<Part, Consumer<RenderContext>> firstPersonCustomPositioningRecoiled = new LinkedHashMap<>();
        private final LinkedHashMap<Part, Consumer<RenderContext>> firstPersonCustomPositioningZoomingRecoiled = new LinkedHashMap<>();
        private final LinkedHashMap<Part, Consumer<RenderContext>> firstPersonCustomPositioningZoomingShooting = new LinkedHashMap<>();
        private final LinkedHashMap<Part, Consumer<RenderContext>> firstPersonCustomPositioningLoadIterationCompleted = new LinkedHashMap<>();
        private List<Transition> firstPersonPositioningEjectSpentRound;
        private List<Transition> firstPersonLeftHandPositioningEjectSpentRound;
        private List<Transition> firstPersonRightHandPositioningEjectSpentRound;
        private final LinkedHashMap<Part, List<Transition>> firstPersonCustomPositioningEjectSpentRound = new LinkedHashMap<>();
        private boolean hasRecoilPositioningDefined;

        public WeaponRenderer.Builder withModId(String modId) {
            this.modId = modId;
            return this;
        }

        public WeaponRenderer.Builder withModel(ModelBase model) {
            this.model = model;
            return this;
        }

        public WeaponRenderer.Builder withShootingAnimationDuration(int shootingAnimationDuration) {
            this.shootingAnimationDuration = shootingAnimationDuration;
            return this;
        }

        public WeaponRenderer.Builder withRecoilAnimationDuration(int recoilAnimationDuration) {
            this.recoilAnimationDuration = recoilAnimationDuration;
            return this;
        }

        public WeaponRenderer.Builder withPrepareFirstLoadIterationAnimationDuration(
            int prepareFirstLoadIterationAnimationDuration) {
            this.prepareFirstLoadIterationAnimationDuration = prepareFirstLoadIterationAnimationDuration;
            return this;
        }

        public WeaponRenderer.Builder withAllLoadIterationAnimationsCompletedDuration(
            int allLoadIterationAnimationsCompletedDuration) {
            this.allLoadIterationAnimationsCompletedDuration = allLoadIterationAnimationsCompletedDuration;
            return this;
        }

        public WeaponRenderer.Builder withNormalRandomizingRate(float normalRandomizingRate) {
            this.normalRandomizingRate = normalRandomizingRate;
            return this;
        }

        public WeaponRenderer.Builder withZoomRandomizingRate(float zoomRandomizingRate) {
            this.zoomRandomizingRate = zoomRandomizingRate;
            return this;
        }

        public WeaponRenderer.Builder withFiringRandomizingRate(float firingRandomizingRate) {
            this.firingRandomizingRate = firingRandomizingRate;
            return this;
        }

        public WeaponRenderer.Builder withFiringRandomizingAmplitude(float firingRandomizingAmplitude) {
            this.firingRandomizingAmplitude = firingRandomizingAmplitude;
            return this;
        }

        public WeaponRenderer.Builder withNormalRandomizingAmplitude(float firingRandomizingRate) {
            this.firingRandomizingRate = firingRandomizingRate;
            return this;
        }

        public WeaponRenderer.Builder withZoomRandomizingAmplitude(float zoomRandomizingAmplitude) {
            this.zoomRandomizingAmplitude = zoomRandomizingAmplitude;
            return this;
        }

        public WeaponRenderer.Builder withTextureName(String textureName) {
            this.textureName = textureName + ".png";
            return this;
        }

        public WeaponRenderer.Builder withWeaponProximity(float weaponProximity) {
            this.weaponProximity = weaponProximity;
            return this;
        }

        public WeaponRenderer.Builder withYOffsetZoom(float yOffsetZoom) {
            this.yOffsetZoom = yOffsetZoom;
            return this;
        }

        public WeaponRenderer.Builder withXOffsetZoom(float xOffsetZoom) {
            this.xOffsetZoom = xOffsetZoom;
            return this;
        }

        public WeaponRenderer.Builder withEntityPositioning(Consumer<ItemStack> entityPositioning) {
            this.entityPositioning = entityPositioning;
            return this;
        }

        public WeaponRenderer.Builder withInventoryPositioning(Consumer<ItemStack> inventoryPositioning) {
            this.inventoryPositioning = inventoryPositioning;
            return this;
        }

        public WeaponRenderer.Builder withThirdPersonPositioning(Consumer<RenderContext> thirdPersonPositioning) {
            this.thirdPersonPositioning = thirdPersonPositioning;
            return this;
        }

        public WeaponRenderer.Builder withFirstPersonPositioning(Consumer<RenderContext> firstPersonPositioning) {
            this.firstPersonPositioning = firstPersonPositioning;
            return this;
        }

        public WeaponRenderer.Builder withFirstPersonPositioningRunning(
            Consumer<RenderContext> firstPersonPositioningRunning) {
            this.firstPersonPositioningRunning = firstPersonPositioningRunning;
            return this;
        }

        public WeaponRenderer.Builder withFirstPersonPositioningZooming(
            Consumer<RenderContext> firstPersonPositioningZooming) {
            this.firstPersonPositioningZooming = firstPersonPositioningZooming;
            return this;
        }

        public WeaponRenderer.Builder withFirstPersonPositioningRecoiled(
            Consumer<RenderContext> firstPersonPositioningRecoiled) {
            this.hasRecoilPositioningDefined = true;
            this.firstPersonPositioningRecoiled = firstPersonPositioningRecoiled;
            return this;
        }

        public WeaponRenderer.Builder withFirstPersonPositioningShooting(
            Consumer<RenderContext> firstPersonPositioningShooting) {
            this.firstPersonPositioningShooting = firstPersonPositioningShooting;
            return this;
        }

        public WeaponRenderer.Builder withFirstPersonPositioningZoomingRecoiled(
            Consumer<RenderContext> firstPersonPositioningZoomingRecoiled) {
            this.firstPersonPositioningZoomingRecoiled = firstPersonPositioningZoomingRecoiled;
            return this;
        }

        public WeaponRenderer.Builder withFirstPersonPositioningZoomingShooting(
            Consumer<RenderContext> firstPersonPositioningZoomingShooting) {
            this.firstPersonPositioningZoomingShooting = firstPersonPositioningZoomingShooting;
            return this;
        }

        public WeaponRenderer.Builder withFirstPersonPositioningLoadIterationCompleted(
            Consumer<RenderContext> firstPersonPositioningLoadIterationCompleted) {
            this.firstPersonPositioningLoadIterationCompleted = firstPersonPositioningLoadIterationCompleted;
            return this;
        }

        @SafeVarargs
        public final WeaponRenderer.Builder withFirstPersonPositioningReloading(Transition... transitions) {
            this.firstPersonPositioningReloading = Arrays.asList(transitions);
            return this;
        }

        @SafeVarargs
        public final WeaponRenderer.Builder withFirstPersonPositioningUnloading(Transition... transitions) {
            this.firstPersonPositioningUnloading = Arrays.asList(transitions);
            return this;
        }

        @SafeVarargs
        public final WeaponRenderer.Builder withFirstPersonPositioningLoadIteration(Transition... transitions) {
            this.firstPersonPositioningLoadIteration = Arrays.asList(transitions);
            return this;
        }

        @SafeVarargs
        public final WeaponRenderer.Builder withFirstPersonPositioningAllLoadIterationsCompleted(
            Transition... transitions) {
            this.firstPersonPositioningAllLoadIterationsCompleted = Arrays.asList(transitions);
            return this;
        }

        @SafeVarargs
        public final WeaponRenderer.Builder withFirstPersonPositioningEjectSpentRound(Transition... transitions) {
            this.firstPersonPositioningEjectSpentRound = Arrays.asList(transitions);
            return this;
        }

        public WeaponRenderer.Builder withFirstPersonPositioningModifying(
            Consumer<RenderContext> firstPersonPositioningModifying) {
            this.firstPersonPositioningModifying = firstPersonPositioningModifying;
            return this;
        }

        public WeaponRenderer.Builder withFirstPersonHandPositioning(Consumer<RenderContext> leftHand,
            Consumer<RenderContext> rightHand) {
            this.firstPersonLeftHandPositioning = leftHand;
            this.firstPersonRightHandPositioning = rightHand;
            return this;
        }

        public WeaponRenderer.Builder withFirstPersonHandPositioningRunning(Consumer<RenderContext> leftHand,
            Consumer<RenderContext> rightHand) {
            this.firstPersonLeftHandPositioningRunning = leftHand;
            this.firstPersonRightHandPositioningRunning = rightHand;
            return this;
        }

        public WeaponRenderer.Builder withFirstPersonHandPositioningZooming(Consumer<RenderContext> leftHand,
            Consumer<RenderContext> rightHand) {
            this.firstPersonLeftHandPositioningZooming = leftHand;
            this.firstPersonRightHandPositioningZooming = rightHand;
            return this;
        }

        public WeaponRenderer.Builder withFirstPersonHandPositioningRecoiled(Consumer<RenderContext> leftHand,
            Consumer<RenderContext> rightHand) {
            this.firstPersonLeftHandPositioningRecoiled = leftHand;
            this.firstPersonRightHandPositioningRecoiled = rightHand;
            return this;
        }

        public WeaponRenderer.Builder withFirstPersonHandPositioningShooting(Consumer<RenderContext> leftHand,
            Consumer<RenderContext> rightHand) {
            this.firstPersonLeftHandPositioningShooting = leftHand;
            this.firstPersonRightHandPositioningShooting = rightHand;
            return this;
        }

        public WeaponRenderer.Builder withFirstPersonHandPositioningLoadIterationCompleted(
            Consumer<RenderContext> leftHand, Consumer<RenderContext> rightHand) {
            this.firstPersonLeftHandPositioningLoadIterationCompleted = leftHand;
            this.firstPersonRightHandPositioningLoadIterationCompleted = rightHand;
            return this;
        }

        @SafeVarargs
        public final WeaponRenderer.Builder withFirstPersonLeftHandPositioningReloading(Transition... transitions) {
            this.firstPersonLeftHandPositioningReloading = Arrays.asList(transitions);
            return this;
        }

        @SafeVarargs
        public final WeaponRenderer.Builder withFirstPersonLeftHandPositioningEjectSpentRound(
            Transition... transitions) {
            this.firstPersonLeftHandPositioningEjectSpentRound = Arrays.asList(transitions);
            return this;
        }

        @SafeVarargs
        public final WeaponRenderer.Builder withFirstPersonLeftHandPositioningUnloading(Transition... transitions) {
            this.firstPersonLeftHandPositioningUnloading = Arrays.asList(transitions);
            return this;
        }

        @SafeVarargs
        public final WeaponRenderer.Builder withFirstPersonLeftHandPositioningLoadIteration(Transition... transitions) {
            this.firstPersonLeftHandPositioningLoadIteration = Arrays.asList(transitions);
            return this;
        }

        @SafeVarargs
        public final WeaponRenderer.Builder withFirstPersonLeftHandPositioningAllLoadIterationsCompleted(
            Transition... transitions) {
            this.firstPersonLeftHandPositioningAllLoadIterationsCompleted = Arrays.asList(transitions);
            return this;
        }

        @SafeVarargs
        public final WeaponRenderer.Builder withFirstPersonRightHandPositioningReloading(Transition... transitions) {
            this.firstPersonRightHandPositioningReloading = Arrays.asList(transitions);
            return this;
        }

        @SafeVarargs
        public final WeaponRenderer.Builder withFirstPersonRightHandPositioningUnloading(Transition... transitions) {
            this.firstPersonRightHandPositioningUnloading = Arrays.asList(transitions);
            return this;
        }

        @SafeVarargs
        public final WeaponRenderer.Builder withFirstPersonRightHandPositioningEjectSpentRound(
            Transition... transitions) {
            this.firstPersonRightHandPositioningEjectSpentRound = Arrays.asList(transitions);
            return this;
        }

        @SafeVarargs
        public final WeaponRenderer.Builder withFirstPersonRightHandPositioningLoadIteration(
            Transition... transitions) {
            this.firstPersonRightHandPositioningLoadIteration = Arrays.asList(transitions);
            return this;
        }

        @SafeVarargs
        public final WeaponRenderer.Builder withFirstPersonRightHandPositioningAllLoadIterationsCompleted(
            Transition... transitions) {
            this.firstPersonRightHandPositioningAllLoadIterationsCompleted = Arrays.asList(transitions);
            return this;
        }

        public WeaponRenderer.Builder withFirstPersonHandPositioningModifying(Consumer<RenderContext> leftHand,
            Consumer<RenderContext> rightHand) {
            this.firstPersonLeftHandPositioningModifying = leftHand;
            this.firstPersonRightHandPositioningModifying = rightHand;
            return this;
        }

        public WeaponRenderer.Builder withFirstPersonCustomPositioning(Part part, Consumer<RenderContext> positioning) {
            if (part instanceof DefaultPart) {
                throw new IllegalArgumentException("Part " + part + " is not custom");
            } else if (this.firstPersonCustomPositioning.put(part, positioning) != null) {
                throw new IllegalArgumentException("Part " + part + " already added");
            } else {
                return this;
            }
        }

        public WeaponRenderer.Builder withFirstPersonPositioningCustomRecoiled(Part part,
            Consumer<RenderContext> positioning) {
            if (part instanceof DefaultPart) {
                throw new IllegalArgumentException("Part " + part + " is not custom");
            } else if (this.firstPersonCustomPositioningRecoiled.put(part, positioning) != null) {
                throw new IllegalArgumentException("Part " + part + " already added");
            } else {
                return this;
            }
        }

        public WeaponRenderer.Builder withFirstPersonPositioningCustomZoomingShooting(Part part,
            Consumer<RenderContext> positioning) {
            if (part instanceof DefaultPart) {
                throw new IllegalArgumentException("Part " + part + " is not custom");
            } else if (this.firstPersonCustomPositioningZoomingShooting.put(part, positioning) != null) {
                throw new IllegalArgumentException("Part " + part + " already added");
            } else {
                return this;
            }
        }

        public WeaponRenderer.Builder withFirstPersonPositioningCustomZoomingRecoiled(Part part,
            Consumer<RenderContext> positioning) {
            if (part instanceof DefaultPart) {
                throw new IllegalArgumentException("Part " + part + " is not custom");
            } else if (this.firstPersonCustomPositioningZoomingRecoiled.put(part, positioning) != null) {
                throw new IllegalArgumentException("Part " + part + " already added");
            } else {
                return this;
            }
        }

        @SafeVarargs
        public final WeaponRenderer.Builder withFirstPersonCustomPositioningReloading(Part part,
            Transition... transitions) {
            if (part instanceof DefaultPart) {
                throw new IllegalArgumentException("Part " + part + " is not custom");
            } else {
                this.firstPersonCustomPositioningReloading.put(part, Arrays.asList(transitions));
                return this;
            }
        }

        public WeaponRenderer.Builder withFirstPersonCustomPositioningLoadIterationCompleted(Part part,
            Consumer<RenderContext> positioning) {
            if (part instanceof DefaultPart) {
                throw new IllegalArgumentException("Part " + part + " is not custom");
            } else if (this.firstPersonCustomPositioningLoadIterationCompleted.put(part, positioning) != null) {
                throw new IllegalArgumentException("Part " + part + " already added");
            } else {
                return this;
            }
        }

        @SafeVarargs
        public final WeaponRenderer.Builder withFirstPersonCustomPositioningUnloading(Part part,
            Transition... transitions) {
            if (part instanceof DefaultPart) {
                throw new IllegalArgumentException("Part " + part + " is not custom");
            } else {
                this.firstPersonCustomPositioningUnloading.put(part, Arrays.asList(transitions));
                return this;
            }
        }

        @SafeVarargs
        public final WeaponRenderer.Builder withFirstPersonCustomPositioningEjectSpentRound(Part part,
            Transition... transitions) {
            if (part instanceof DefaultPart) {
                throw new IllegalArgumentException("Part " + part + " is not custom");
            } else {
                this.firstPersonCustomPositioningEjectSpentRound.put(part, Arrays.asList(transitions));
                return this;
            }
        }

        @SafeVarargs
        public final WeaponRenderer.Builder withFirstPersonCustomPositioningLoadIteration(Part part,
            Transition... transitions) {
            if (part instanceof DefaultPart) {
                throw new IllegalArgumentException("Part " + part + " is not custom");
            } else {
                this.firstPersonCustomPositioningLoadIteration.put(part, Arrays.asList(transitions));
                return this;
            }
        }

        @SafeVarargs
        public final WeaponRenderer.Builder withFirstPersonCustomPositioningAllLoadIterationsCompleted(Part part,
            Transition... transitions) {
            if (part instanceof DefaultPart) {
                throw new IllegalArgumentException("Part " + part + " is not custom");
            } else {
                this.firstPersonCustomPositioningLoadIterationsCompleted.put(part, Arrays.asList(transitions));
                return this;
            }
        }

        public WeaponRenderer build() {
            if (FMLCommonHandler.instance()
                .getSide() != Side.CLIENT) {
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

                WeaponRenderer renderer = new WeaponRenderer(this);
                if (this.firstPersonPositioning == null) {
                    this.firstPersonPositioning = (renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        if (renderer.getClientModContext() != null) {
                            PlayerWeaponInstance instance = renderer.getClientModContext()
                                .getMainHeldWeapon();
                            if (instance != null && instance.isAimed()) {
                                GL11.glTranslatef(this.xOffsetZoom, this.yOffsetZoom, this.weaponProximity);
                            } else {
                                GL11.glTranslatef(0.0F, -1.2F, 0.0F);
                            }
                        }

                    };
                }

                if (this.firstPersonPositioningZooming == null) {
                    this.firstPersonPositioningZooming = this.firstPersonPositioning;
                }

                if (this.firstPersonPositioningReloading == null) {
                    this.firstPersonPositioningReloading = Collections
                        .singletonList(new Transition(this.firstPersonPositioning, 250L));
                }

                if (this.firstPersonPositioningLoadIteration == null) {
                    this.firstPersonPositioningLoadIteration = Collections
                        .singletonList(new Transition(this.firstPersonPositioning, 250L));
                }

                Iterator var2;
                Transition t;
                for (var2 = this.firstPersonPositioningReloading.iterator(); var2
                    .hasNext(); this.totalReloadingDuration += t.getPause()) {
                    t = (Transition) var2.next();
                    this.totalReloadingDuration += t.getDuration();
                }

                for (var2 = this.firstPersonPositioningLoadIteration.iterator(); var2
                    .hasNext(); this.totalLoadIterationDuration += t.getPause()) {
                    t = (Transition) var2.next();
                    this.totalLoadIterationDuration += t.getDuration();
                }

                if (this.firstPersonPositioningUnloading == null) {
                    this.firstPersonPositioningUnloading = Collections
                        .singletonList(new Transition(this.firstPersonPositioning, 250L));
                }

                for (var2 = this.firstPersonPositioningUnloading.iterator(); var2
                    .hasNext(); this.totalUnloadingDuration += t.getPause()) {
                    t = (Transition) var2.next();
                    this.totalUnloadingDuration += t.getDuration();
                }

                if (this.firstPersonPositioningLoadIteration == null) {
                    this.firstPersonPositioningLoadIteration = Collections
                        .singletonList(new Transition(this.firstPersonPositioning, 250L));
                }

                if (this.firstPersonPositioningAllLoadIterationsCompleted == null) {
                    this.firstPersonPositioningAllLoadIterationsCompleted = Collections
                        .singletonList(new Transition(this.firstPersonPositioning, 250L));
                }

                if (this.firstPersonPositioningRecoiled == null) {
                    this.firstPersonPositioningRecoiled = this.firstPersonPositioning;
                } else {
                    Consumer<RenderContext> firstPersonPositioningRecoiledOrig = this.firstPersonPositioningRecoiled;
                    this.firstPersonPositioningRecoiled = (renderContext) -> {
                        float maxAngle = 1.5F;
                        float xRotation = this.random.nextFloat() * maxAngle;
                        float yRotation = this.random.nextFloat() * maxAngle;
                        float zRotation = this.random.nextFloat() * maxAngle;
                        GL11.glRotatef(xRotation, 1.0F, 0.0F, 0.0F);
                        GL11.glRotatef(yRotation, 0.0F, 1.0F, 0.0F);
                        GL11.glRotatef(zRotation, 0.0F, 0.0F, 1.0F);
                        float amplitude = 0.0F;
                        float xRandomOffset = this.random.nextFloat() * amplitude;
                        float yRandomOffset = this.random.nextFloat() * amplitude;
                        float zRandomOffset = this.random.nextFloat() * amplitude;
                        GL11.glTranslatef(xRandomOffset, yRandomOffset, zRandomOffset);
                        firstPersonPositioningRecoiledOrig.accept(renderContext);
                    };
                }

                if (this.firstPersonPositioningRunning == null) {
                    this.firstPersonPositioningRunning = this.firstPersonPositioning;
                }

                if (this.firstPersonPositioningModifying == null) {
                    this.firstPersonPositioningModifying = this.firstPersonPositioning;
                }

                if (this.firstPersonPositioningShooting == null) {
                    this.firstPersonPositioningShooting = this.firstPersonPositioning;
                }

                if (this.firstPersonPositioningZoomingRecoiled == null) {
                    this.firstPersonPositioningZoomingRecoiled = this.firstPersonPositioningZooming;
                }

                if (this.firstPersonPositioningZoomingShooting == null) {
                    this.firstPersonPositioningZoomingShooting = this.firstPersonPositioningZooming;
                }

                if (this.firstPersonPositioningLoadIterationCompleted == null) {
                    this.firstPersonPositioningLoadIterationCompleted = this.firstPersonPositioning;
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

                if (this.firstPersonLeftHandPositioningReloading == null) {
                    this.firstPersonLeftHandPositioningReloading = this.firstPersonPositioningReloading.stream()
                        .map((tx) -> new Transition((c) -> {}, 0L))
                        .collect(Collectors.toList());
                }

                if (this.firstPersonLeftHandPositioningUnloading == null) {
                    this.firstPersonLeftHandPositioningUnloading = this.firstPersonPositioningUnloading.stream()
                        .map((tx) -> { return new Transition((c) -> {}, 0L); })
                        .collect(Collectors.toList());
                }

                if (this.firstPersonLeftHandPositioningLoadIteration == null) {
                    this.firstPersonLeftHandPositioningLoadIteration = this.firstPersonPositioningReloading.stream()
                        .map((tx) -> { return new Transition((c) -> {}, 0L); })
                        .collect(Collectors.toList());
                }

                if (this.firstPersonLeftHandPositioningAllLoadIterationsCompleted == null) {
                    this.firstPersonLeftHandPositioningAllLoadIterationsCompleted = this.firstPersonPositioningReloading
                        .stream()
                        .map((tx) -> { return new Transition((c) -> {}, 0L); })
                        .collect(Collectors.toList());
                }

                if (this.firstPersonLeftHandPositioningRecoiled == null) {
                    this.firstPersonLeftHandPositioningRecoiled = this.firstPersonLeftHandPositioning;
                }

                if (this.firstPersonLeftHandPositioningShooting == null) {
                    this.firstPersonLeftHandPositioningShooting = this.firstPersonLeftHandPositioning;
                }

                if (this.firstPersonLeftHandPositioningZooming == null) {
                    this.firstPersonLeftHandPositioningZooming = this.firstPersonLeftHandPositioning;
                }

                if (this.firstPersonLeftHandPositioningRunning == null) {
                    this.firstPersonLeftHandPositioningRunning = this.firstPersonLeftHandPositioning;
                }

                if (this.firstPersonLeftHandPositioningModifying == null) {
                    this.firstPersonLeftHandPositioningModifying = this.firstPersonLeftHandPositioning;
                }

                if (this.firstPersonLeftHandPositioningLoadIterationCompleted == null) {
                    this.firstPersonLeftHandPositioningLoadIterationCompleted = this.firstPersonLeftHandPositioning;
                }

                if (this.firstPersonRightHandPositioning == null) {
                    this.firstPersonRightHandPositioning = (context) -> {};
                }

                if (this.firstPersonRightHandPositioningReloading == null) {
                    this.firstPersonRightHandPositioningReloading = this.firstPersonPositioningReloading.stream()
                        .map((tx) -> { return new Transition((c) -> {}, 0L); })
                        .collect(Collectors.toList());
                }

                if (this.firstPersonRightHandPositioningUnloading == null) {
                    this.firstPersonRightHandPositioningUnloading = this.firstPersonPositioningUnloading.stream()
                        .map((tx) -> { return new Transition((c) -> {}, 0L); })
                        .collect(Collectors.toList());
                }

                if (this.firstPersonRightHandPositioningLoadIteration == null) {
                    this.firstPersonRightHandPositioningLoadIteration = this.firstPersonPositioningReloading.stream()
                        .map((tx) -> { return new Transition((c) -> {}, 0L); })
                        .collect(Collectors.toList());
                }

                if (this.firstPersonRightHandPositioningAllLoadIterationsCompleted == null) {
                    this.firstPersonRightHandPositioningAllLoadIterationsCompleted = this.firstPersonPositioningReloading
                        .stream()
                        .map((tx) -> { return new Transition((c) -> {}, 0L); })
                        .collect(Collectors.toList());
                }

                if (this.firstPersonRightHandPositioningRecoiled == null) {
                    this.firstPersonRightHandPositioningRecoiled = this.firstPersonRightHandPositioning;
                }

                if (this.firstPersonRightHandPositioningShooting == null) {
                    this.firstPersonRightHandPositioningShooting = this.firstPersonRightHandPositioning;
                }

                if (this.firstPersonRightHandPositioningZooming == null) {
                    this.firstPersonRightHandPositioningZooming = this.firstPersonRightHandPositioning;
                }

                if (this.firstPersonRightHandPositioningRunning == null) {
                    this.firstPersonRightHandPositioningRunning = this.firstPersonRightHandPositioning;
                }

                if (this.firstPersonRightHandPositioningModifying == null) {
                    this.firstPersonRightHandPositioningModifying = this.firstPersonRightHandPositioning;
                }

                if (this.firstPersonRightHandPositioningLoadIterationCompleted == null) {
                    this.firstPersonRightHandPositioningLoadIterationCompleted = this.firstPersonLeftHandPositioning;
                }

                if (!this.firstPersonCustomPositioning.isEmpty()
                    && this.firstPersonCustomPositioningRecoiled.isEmpty()) {
                    this.firstPersonCustomPositioning
                        .forEach((part, pos) -> { this.firstPersonCustomPositioningRecoiled.put(part, pos); });
                }

                if (!this.firstPersonCustomPositioning.isEmpty()
                    && this.firstPersonCustomPositioningZoomingRecoiled.isEmpty()) {
                    this.firstPersonCustomPositioning
                        .forEach((part, pos) -> { this.firstPersonCustomPositioningZoomingRecoiled.put(part, pos); });
                }

                if (!this.firstPersonCustomPositioning.isEmpty()
                    && this.firstPersonCustomPositioningZoomingShooting.isEmpty()) {
                    this.firstPersonCustomPositioning
                        .forEach((part, pos) -> { this.firstPersonCustomPositioningZoomingShooting.put(part, pos); });
                }

                if (!this.firstPersonCustomPositioning.isEmpty()
                    && this.firstPersonCustomPositioningLoadIterationCompleted.isEmpty()) {
                    this.firstPersonCustomPositioning.forEach(
                        (part, pos) -> { this.firstPersonCustomPositioningLoadIterationCompleted.put(part, pos); });
                }

                this.firstPersonCustomPositioningReloading.forEach((p, tx) -> {
                    if (tx.size() != this.firstPersonPositioningReloading.size()) {
                        throw new IllegalStateException(
                            "Custom reloading transition number mismatch. Expected "
                                + this.firstPersonPositioningReloading.size()
                                + ", actual: "
                                + tx.size());
                    }
                });
                this.firstPersonCustomPositioningUnloading.forEach((p, tx) -> {
                    if (tx.size() != this.firstPersonPositioningUnloading.size()) {
                        throw new IllegalStateException(
                            "Custom unloading transition number mismatch. Expected "
                                + this.firstPersonPositioningUnloading.size()
                                + ", actual: "
                                + tx.size());
                    }
                });
                this.firstPersonCustomPositioningLoadIteration.forEach((p, tx) -> {
                    if (tx.size() != this.firstPersonPositioningLoadIteration.size()) {
                        throw new IllegalStateException(
                            "Custom reloading transition number mismatch. Expected "
                                + this.firstPersonPositioningLoadIteration.size()
                                + ", actual: "
                                + tx.size());
                    }
                });
                this.firstPersonCustomPositioningLoadIterationsCompleted.forEach((p, tx) -> {
                    if (tx.size() != this.firstPersonPositioningAllLoadIterationsCompleted.size()) {
                        throw new IllegalStateException(
                            "Custom reloading transition number mismatch. Expected "
                                + this.firstPersonPositioningAllLoadIterationsCompleted.size()
                                + ", actual: "
                                + tx.size());
                    }
                });
                return renderer;
            }
        }

        public Consumer<ItemStack> getEntityPositioning() {
            return this.entityPositioning;
        }

        public Consumer<ItemStack> getInventoryPositioning() {
            return this.inventoryPositioning;
        }

        public Consumer<RenderContext> getThirdPersonPositioning() {
            return this.thirdPersonPositioning;
        }

        public String getTextureName() {
            return this.textureName;
        }

        public ModelBase getModel() {
            return this.model;
        }

        public String getModId() {
            return this.modId;
        }
    }

    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return true;
    }

    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return true;
    }

    @SideOnly(Side.CLIENT)
    public void renderItem(ItemRenderType type, ItemStack weaponItemStack, Object... data) {
        GL11.glPushMatrix();
        int originalFramebufferId = -1;
        Framebuffer framebuffer = null;
        Integer inventoryTexture = null;
        boolean inventoryTextureInitializationPhaseOn = false;
        Minecraft mc = Minecraft.getMinecraft();
        ScaledResolution scaledresolution = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
        int currentTextureId = 0;
        if (type == ItemRenderType.INVENTORY) {
            currentTextureId = Framebuffers.getCurrentTexture();
            inventoryTexture = this.getClientModContext()
                .getInventoryTextureMap()
                .get(this);
            if (inventoryTexture == null) {
                inventoryTextureInitializationPhaseOn = true;
                originalFramebufferId = Framebuffers.getCurrentFramebuffer();
                Framebuffers.unbindFramebuffer();
                framebuffer = new Framebuffer(INVENTORY_TEXTURE_WIDTH, INVENTORY_TEXTURE_HEIGHT, true);
                inventoryTexture = framebuffer.framebufferTexture;
                this.getClientModContext()
                    .getInventoryTextureMap()
                    .put(this, inventoryTexture);
                framebuffer.bindFramebuffer(true);
                this.setupInventoryRendering(INVENTORY_TEXTURE_WIDTH, INVENTORY_TEXTURE_HEIGHT);
                GL11.glScalef(130.0F, 130.0F, 130.0F);
                GL11.glScalef(1.0F, 1.0F, 1.0F);
                GL11.glRotatef(25.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
                GL11.glTranslatef(1.449999F, 1.399999F, 0.0F);
            }
        }

        GL11.glScaled(-1.0D, -1.0D, 1.0D);
        Object player;
        if (data.length > 1 && data[1] instanceof EntityLivingBase) {
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
                positioner.applySway(stateDescriptor.rate, stateDescriptor.amplitude);
                positioner.position(Part.MAIN_ITEM, renderContext);

                renderLeftArm((EntityPlayer) player, renderContext, positioner);
                renderRightArm((EntityPlayer) player, renderContext, positioner);
        }

        if (type != ItemRenderType.INVENTORY || inventoryTextureInitializationPhaseOn) {
            this.renderItem(weaponItemStack, renderContext, positioner);
        }

        if (type == ItemRenderType.INVENTORY && inventoryTextureInitializationPhaseOn) {
            framebuffer.unbindFramebuffer();
            framebuffer.framebufferTexture = -1;
            framebuffer.deleteFramebuffer();
            this.restoreInventoryRendering(scaledresolution);
        }

        GL11.glPopMatrix();
        if (originalFramebufferId >= 0) {
            Framebuffers.bindFramebuffer(
                originalFramebufferId,
                true,
                mc.getFramebuffer().framebufferWidth,
                mc.getFramebuffer().framebufferHeight);
        }

        if (type == ItemRenderType.INVENTORY) {
            this.renderCachedInventoryTexture(inventoryTexture);
            if (currentTextureId != 0) {
                Framebuffers.bindTexture(currentTextureId);
            }
        }

    }

    private void setupInventoryRendering(double projectionWidth, double projectionHeight) {
        GL11.glClear(256);
        GL11.glMatrixMode(5889);
        GL11.glLoadIdentity();
        GL11.glOrtho(0.0D, projectionWidth, projectionHeight, 0.0D, 1000.0D, 3000.0D);
        GL11.glMatrixMode(5888);
        GL11.glLoadIdentity();
        GL11.glTranslatef(0.0F, 0.0F, -2000.0F);
    }

    private void restoreInventoryRendering(ScaledResolution scaledresolution) {
        GL11.glMatrixMode(5889);
        GL11.glLoadIdentity();
        GL11.glOrtho(
            0.0D,
            scaledresolution.getScaledWidth_double(),
            scaledresolution.getScaledHeight_double(),
            0.0D,
            1000.0D,
            3000.0D);
        GL11.glMatrixMode(5888);
    }

    private void renderCachedInventoryTexture(Integer inventoryTexture) {
        GL11.glPushMatrix();
        GL11.glPushAttrib(8192);
        GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-210.0F, 1.0F, 0.0F, 0.0F);
        GL11.glScalef(1.0F, 1.0F, -1.0F);
        GL11.glTranslatef(-0.8F, -0.8F, -1.0F);
        GL11.glScalef(0.006F, 0.006F, 0.006F);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glAlphaFunc(516, 0.003921569F);
        GL11.glBindTexture(3553, inventoryTexture);
        drawTexturedQuadFit(0.0D, 0.0D, 256.0D, 256.0D, 0.0D);
        GL11.glPopAttrib();
        GL11.glPopMatrix();
    }

    public void drawTexturedModalRect(int x, int y, int u, int v, int width, int height) {
        int zLevel = 50;
        float f = 0.00390625F;
        float f1 = 0.00390625F;
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(x, y + height, zLevel, (float) (u) * f, (float) (v + height) * f1);
        tessellator.addVertexWithUV(x + width, y + height, zLevel, (float) (u + width) * f, (float) (v + height) * f1);
        tessellator.addVertexWithUV(x + width, y, zLevel, (float) (u + width) * f, (float) (v) * f1);
        tessellator.addVertexWithUV(x, y, zLevel, (float) (u) * f, (float) (v) * f1);
        tessellator.draw();
    }

    private static void drawTexturedQuadFit(double x, double y, double width, double height, double zLevel) {
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(x + 0.0D, y + height, zLevel, 0.0F, 1.0F);
        tessellator.addVertexWithUV(x + width, y + height, zLevel, 1.0F, 1.0F);
        tessellator.addVertexWithUV(x + width, y + 0.0D, zLevel, 1.0F, 0.0F);
        tessellator.addVertexWithUV(x + 0.0D, y + 0.0D, zLevel, 0.0F, 0.0F);
        tessellator.draw();
    }

    static void renderRightArm(EntityPlayer player, RenderContext renderContext,
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

        GL11.glColor3f(1.0F, 1.0F, 1.0F);
        render.modelBipedMain.onGround = 0.0F;
        render.modelBipedMain.setRotationAngles(0.0F, 0.3F, 0.0F, 0.0F, 0.0F, 0.0625F, player);
        render.modelBipedMain.bipedRightArm.render(0.0625F);
        GL11.glPopMatrix();
    }

    static void renderLeftArm(EntityPlayer player, RenderContext renderContext,
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

        GL11.glColor3f(1.0F, 1.0F, 1.0F);
        render.modelBipedMain.onGround = 0.0F;
        render.modelBipedMain.setRotationAngles(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, player);
        render.modelBipedMain.bipedLeftArm.render(0.0625F);
        GL11.glPopMatrix();
    }

    protected static class StateDescriptor {

        protected MultipartRenderStateManager stateManager;
        protected float rate;
        protected float amplitude = 0.04F;
        private final PlayerWeaponInstance instance;

        public StateDescriptor(PlayerWeaponInstance instance, MultipartRenderStateManager stateManager, float rate,
            float amplitude) {
            this.instance = instance;
            this.stateManager = stateManager;
            this.rate = rate;
            this.amplitude = amplitude;
        }
    }
}
