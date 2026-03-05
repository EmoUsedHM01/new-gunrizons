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
import com.gtnewhorizon.newgunrizons.attachment.Part;
import com.gtnewhorizon.newgunrizons.attachment.StandardPart;
import com.gtnewhorizon.newgunrizons.client.animation.MultipartPositioning;
import com.gtnewhorizon.newgunrizons.client.animation.MultipartRenderStateManager;
import com.gtnewhorizon.newgunrizons.client.animation.MultipartTransition;
import com.gtnewhorizon.newgunrizons.client.animation.MultipartTransitionProvider;
import com.gtnewhorizon.newgunrizons.client.animation.Transition;
import com.gtnewhorizon.newgunrizons.config.ClientModContext;
import com.gtnewhorizon.newgunrizons.items.ItemAttachment;
import com.gtnewhorizon.newgunrizons.items.ItemWeapon;
import com.gtnewhorizon.newgunrizons.items.instances.ItemInstance;
import com.gtnewhorizon.newgunrizons.items.instances.ItemWeaponInstance;
import com.gtnewhorizon.newgunrizons.model.ModelWithAttachments;
import com.gtnewhorizon.newgunrizons.state.RenderableState;
import com.gtnewhorizon.newgunrizons.util.Pair;
import com.gtnewhorizon.newgunrizons.weapon.WeaponState;
import com.gtnewhorizon.newgunrizons.weapon.WeaponStateTimed;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lombok.Getter;
import lombok.Setter;

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
    private final Map<EntityLivingBase, MultipartRenderStateManager> firstPersonStateManagers;
    private final MultipartTransitionProvider weaponTransitionProvider;
    @Setter
    protected ClientModContext clientModContext;

    // Fields copied from Builder
    private final ModelBase model;
    private final String textureName;
    private final String modId;
    private final Consumer<ItemStack> entityPositioning;
    private final Consumer<ItemStack> inventoryPositioning;
    private final Consumer<RenderContext> thirdPersonPositioning;
    private final Consumer<RenderContext> firstPersonPositioning;
    private final Consumer<RenderContext> firstPersonPositioningZooming;
    private final Consumer<RenderContext> firstPersonPositioningRunning;
    private final Consumer<RenderContext> firstPersonPositioningModifying;
    private final Consumer<RenderContext> firstPersonPositioningRecoiled;
    private final Consumer<RenderContext> firstPersonPositioningShooting;
    private final Consumer<RenderContext> firstPersonPositioningZoomingRecoiled;
    private final Consumer<RenderContext> firstPersonPositioningZoomingShooting;
    private final Consumer<RenderContext> firstPersonPositioningLoadIterationCompleted;
    private final Consumer<RenderContext> firstPersonLeftHandPositioning;
    private final Consumer<RenderContext> firstPersonLeftHandPositioningZooming;
    private final Consumer<RenderContext> firstPersonLeftHandPositioningRunning;
    private final Consumer<RenderContext> firstPersonLeftHandPositioningModifying;
    private final Consumer<RenderContext> firstPersonLeftHandPositioningRecoiled;
    private final Consumer<RenderContext> firstPersonLeftHandPositioningShooting;
    private final Consumer<RenderContext> firstPersonLeftHandPositioningLoadIterationCompleted;
    private final Consumer<RenderContext> firstPersonRightHandPositioning;
    private final Consumer<RenderContext> firstPersonRightHandPositioningZooming;
    private final Consumer<RenderContext> firstPersonRightHandPositioningRunning;
    private final Consumer<RenderContext> firstPersonRightHandPositioningModifying;
    private final Consumer<RenderContext> firstPersonRightHandPositioningRecoiled;
    private final Consumer<RenderContext> firstPersonRightHandPositioningShooting;
    private final Consumer<RenderContext> firstPersonRightHandPositioningLoadIterationCompleted;
    private final List<Transition> firstPersonPositioningReloading;
    private final List<Transition> firstPersonLeftHandPositioningReloading;
    private final List<Transition> firstPersonRightHandPositioningReloading;
    private final List<Transition> firstPersonPositioningUnloading;
    private final List<Transition> firstPersonLeftHandPositioningUnloading;
    private final List<Transition> firstPersonRightHandPositioningUnloading;
    private final List<Transition> firstPersonPositioningLoadIteration;
    private final List<Transition> firstPersonLeftHandPositioningLoadIteration;
    private final List<Transition> firstPersonRightHandPositioningLoadIteration;
    private final List<Transition> firstPersonPositioningAllLoadIterationsCompleted;
    private final List<Transition> firstPersonLeftHandPositioningAllLoadIterationsCompleted;
    private final List<Transition> firstPersonRightHandPositioningAllLoadIterationsCompleted;
    private final List<Transition> firstPersonPositioningEjectSpentRound;
    private final List<Transition> firstPersonLeftHandPositioningEjectSpentRound;
    private final List<Transition> firstPersonRightHandPositioningEjectSpentRound;
    private final long totalReloadingDuration;
    private final long totalUnloadingDuration;
    private final long totalLoadIterationDuration;
    private final int recoilAnimationDuration;
    private final int shootingAnimationDuration;
    private final int loadIterationCompletedAnimationDuration;
    private final int prepareFirstLoadIterationAnimationDuration;
    private final int allLoadIterationAnimationsCompletedDuration;
    private final float normalRandomizingRate;
    private final float firingRandomizingRate;
    private final float zoomRandomizingRate;
    private final float normalRandomizingAmplitude;
    private final float zoomRandomizingAmplitude;
    private final float firingRandomizingAmplitude;
    private final LinkedHashMap<Part, Consumer<RenderContext>> firstPersonCustomPositioning;
    private final LinkedHashMap<Part, List<Transition>> firstPersonCustomPositioningUnloading;
    private final LinkedHashMap<Part, List<Transition>> firstPersonCustomPositioningReloading;
    private final LinkedHashMap<Part, List<Transition>> firstPersonCustomPositioningLoadIteration;
    private final LinkedHashMap<Part, List<Transition>> firstPersonCustomPositioningLoadIterationsCompleted;
    private final LinkedHashMap<Part, Consumer<RenderContext>> firstPersonCustomPositioningRecoiled;
    private final LinkedHashMap<Part, Consumer<RenderContext>> firstPersonCustomPositioningZoomingRecoiled;
    private final LinkedHashMap<Part, Consumer<RenderContext>> firstPersonCustomPositioningZoomingShooting;
    private final LinkedHashMap<Part, Consumer<RenderContext>> firstPersonCustomPositioningLoadIterationCompleted;
    private final LinkedHashMap<Part, List<Transition>> firstPersonCustomPositioningEjectSpentRound;
    private final boolean hasRecoilPositioningDefined;

    private WeaponRenderer(WeaponRenderer.Builder builder) {
        this.firstPersonStateManagers = new HashMap<>();
        this.weaponTransitionProvider = new WeaponRenderer.WeaponPositionProvider();

        // Copy fields from builder (use getters for @Getter-annotated fields)
        this.model = builder.getModel();
        this.textureName = builder.getTextureName();
        this.modId = builder.getModId();
        this.entityPositioning = builder.getEntityPositioning();
        this.inventoryPositioning = builder.getInventoryPositioning();
        this.thirdPersonPositioning = builder.getThirdPersonPositioning();
        this.firstPersonPositioning = builder.firstPersonPositioning;
        this.firstPersonPositioningZooming = builder.firstPersonPositioningZooming;
        this.firstPersonPositioningRunning = builder.firstPersonPositioningRunning;
        this.firstPersonPositioningModifying = builder.firstPersonPositioningModifying;
        this.firstPersonPositioningRecoiled = builder.firstPersonPositioningRecoiled;
        this.firstPersonPositioningShooting = builder.firstPersonPositioningShooting;
        this.firstPersonPositioningZoomingRecoiled = builder.firstPersonPositioningZoomingRecoiled;
        this.firstPersonPositioningZoomingShooting = builder.firstPersonPositioningZoomingShooting;
        this.firstPersonPositioningLoadIterationCompleted = builder.firstPersonPositioningLoadIterationCompleted;
        this.firstPersonLeftHandPositioning = builder.firstPersonLeftHandPositioning;
        this.firstPersonLeftHandPositioningZooming = builder.firstPersonLeftHandPositioningZooming;
        this.firstPersonLeftHandPositioningRunning = builder.firstPersonLeftHandPositioningRunning;
        this.firstPersonLeftHandPositioningModifying = builder.firstPersonLeftHandPositioningModifying;
        this.firstPersonLeftHandPositioningRecoiled = builder.firstPersonLeftHandPositioningRecoiled;
        this.firstPersonLeftHandPositioningShooting = builder.firstPersonLeftHandPositioningShooting;
        this.firstPersonLeftHandPositioningLoadIterationCompleted = builder.firstPersonLeftHandPositioningLoadIterationCompleted;
        this.firstPersonRightHandPositioning = builder.firstPersonRightHandPositioning;
        this.firstPersonRightHandPositioningZooming = builder.firstPersonRightHandPositioningZooming;
        this.firstPersonRightHandPositioningRunning = builder.firstPersonRightHandPositioningRunning;
        this.firstPersonRightHandPositioningModifying = builder.firstPersonRightHandPositioningModifying;
        this.firstPersonRightHandPositioningRecoiled = builder.firstPersonRightHandPositioningRecoiled;
        this.firstPersonRightHandPositioningShooting = builder.firstPersonRightHandPositioningShooting;
        this.firstPersonRightHandPositioningLoadIterationCompleted = builder.firstPersonRightHandPositioningLoadIterationCompleted;
        this.firstPersonPositioningReloading = builder.firstPersonPositioningReloading;
        this.firstPersonLeftHandPositioningReloading = builder.firstPersonLeftHandPositioningReloading;
        this.firstPersonRightHandPositioningReloading = builder.firstPersonRightHandPositioningReloading;
        this.firstPersonPositioningUnloading = builder.firstPersonPositioningUnloading;
        this.firstPersonLeftHandPositioningUnloading = builder.firstPersonLeftHandPositioningUnloading;
        this.firstPersonRightHandPositioningUnloading = builder.firstPersonRightHandPositioningUnloading;
        this.firstPersonPositioningLoadIteration = builder.firstPersonPositioningLoadIteration;
        this.firstPersonLeftHandPositioningLoadIteration = builder.firstPersonLeftHandPositioningLoadIteration;
        this.firstPersonRightHandPositioningLoadIteration = builder.firstPersonRightHandPositioningLoadIteration;
        this.firstPersonPositioningAllLoadIterationsCompleted = builder.firstPersonPositioningAllLoadIterationsCompleted;
        this.firstPersonLeftHandPositioningAllLoadIterationsCompleted = builder.firstPersonLeftHandPositioningAllLoadIterationsCompleted;
        this.firstPersonRightHandPositioningAllLoadIterationsCompleted = builder.firstPersonRightHandPositioningAllLoadIterationsCompleted;
        this.firstPersonPositioningEjectSpentRound = builder.firstPersonPositioningEjectSpentRound;
        this.firstPersonLeftHandPositioningEjectSpentRound = builder.firstPersonLeftHandPositioningEjectSpentRound;
        this.firstPersonRightHandPositioningEjectSpentRound = builder.firstPersonRightHandPositioningEjectSpentRound;
        this.totalReloadingDuration = builder.totalReloadingDuration;
        this.totalUnloadingDuration = builder.totalUnloadingDuration;
        this.totalLoadIterationDuration = builder.totalLoadIterationDuration;
        this.recoilAnimationDuration = builder.recoilAnimationDuration;
        this.shootingAnimationDuration = builder.shootingAnimationDuration;
        this.loadIterationCompletedAnimationDuration = builder.loadIterationCompletedAnimationDuration;
        this.prepareFirstLoadIterationAnimationDuration = builder.prepareFirstLoadIterationAnimationDuration;
        this.allLoadIterationAnimationsCompletedDuration = builder.allLoadIterationAnimationsCompletedDuration;
        this.normalRandomizingRate = builder.normalRandomizingRate;
        this.firingRandomizingRate = builder.firingRandomizingRate;
        this.zoomRandomizingRate = builder.zoomRandomizingRate;
        this.normalRandomizingAmplitude = builder.normalRandomizingAmplitude;
        this.zoomRandomizingAmplitude = builder.zoomRandomizingAmplitude;
        this.firingRandomizingAmplitude = builder.firingRandomizingAmplitude;
        this.firstPersonCustomPositioning = builder.firstPersonCustomPositioning;
        this.firstPersonCustomPositioningUnloading = builder.firstPersonCustomPositioningUnloading;
        this.firstPersonCustomPositioningReloading = builder.firstPersonCustomPositioningReloading;
        this.firstPersonCustomPositioningLoadIteration = builder.firstPersonCustomPositioningLoadIteration;
        this.firstPersonCustomPositioningLoadIterationsCompleted = builder.firstPersonCustomPositioningLoadIterationsCompleted;
        this.firstPersonCustomPositioningRecoiled = builder.firstPersonCustomPositioningRecoiled;
        this.firstPersonCustomPositioningZoomingRecoiled = builder.firstPersonCustomPositioningZoomingRecoiled;
        this.firstPersonCustomPositioningZoomingShooting = builder.firstPersonCustomPositioningZoomingShooting;
        this.firstPersonCustomPositioningLoadIterationCompleted = builder.firstPersonCustomPositioningLoadIterationCompleted;
        this.firstPersonCustomPositioningEjectSpentRound = builder.firstPersonCustomPositioningEjectSpentRound;
        this.hasRecoilPositioningDefined = builder.hasRecoilPositioningDefined;
    }

    public long getTotalReloadingDuration() {
        return this.totalReloadingDuration;
    }

    public long getTotalUnloadingDuration() {
        return this.totalUnloadingDuration;
    }

    protected ClientModContext getClientModContext() {
        return this.clientModContext;
    }

    protected StateDescriptor getStateDescriptor(EntityLivingBase player, ItemStack itemStack) {
        float amplitude = this.normalRandomizingAmplitude;
        float rate = this.normalRandomizingRate;
        RenderableState currentState = null;
        ItemInstance<?> itemInstance = this.clientModContext.getItemInstanceRegistry()
            .getItemInstance(player, itemStack);
        ItemWeaponInstance itemWeaponInstance = null;
        if (itemInstance != null && itemInstance instanceof ItemWeaponInstance
            && itemInstance.getItem() == itemStack.getItem()) {
            itemWeaponInstance = (ItemWeaponInstance) itemInstance;
        } else {
            logger.error(
                "Invalid or mismatching item. Player item instance: {}. Item stack: {}",
                itemInstance,
                itemStack);
        }

        if (itemWeaponInstance != null) {
            WeaponStateTimed weaponStateTimed = this.getNextNonExpiredState(itemWeaponInstance);
            switch (weaponStateTimed.getState()) {
                case RECOILED:
                    if (itemWeaponInstance.isAutomaticModeEnabled() && !this.hasRecoilPositioning()) {
                        if (itemWeaponInstance.isAimed()) {
                            currentState = RenderableState.ZOOMING;
                            rate = this.firingRandomizingRate;
                            amplitude = this.zoomRandomizingAmplitude;
                        } else {
                            currentState = RenderableState.NORMAL;
                            rate = this.firingRandomizingRate;
                            amplitude = this.firingRandomizingAmplitude;
                        }
                    } else if (itemWeaponInstance.isAimed()) {
                        currentState = RenderableState.ZOOMING_RECOILED;
                        amplitude = this.zoomRandomizingAmplitude;
                    } else {
                        currentState = RenderableState.RECOILED;
                    }
                    break;
                case PAUSED:
                    if (itemWeaponInstance.isAutomaticModeEnabled() && !this.hasRecoilPositioning()) {
                        boolean isLongPaused = (float) (System.currentTimeMillis() - weaponStateTimed.getTimestamp())
                            > 50.0F / itemWeaponInstance.getFireRate() && weaponStateTimed.isInfinite();
                        if (itemWeaponInstance.isAimed()) {
                            currentState = RenderableState.ZOOMING;
                            if (!isLongPaused) {
                                rate = this.firingRandomizingRate;
                            }

                            amplitude = this.zoomRandomizingAmplitude;
                        } else {
                            currentState = RenderableState.NORMAL;
                            if (!isLongPaused) {
                                rate = this.firingRandomizingRate;
                                amplitude = this.firingRandomizingAmplitude;
                            }
                        }
                    } else if (itemWeaponInstance.isAimed()) {
                        currentState = RenderableState.ZOOMING_SHOOTING;
                        amplitude = this.zoomRandomizingAmplitude;
                    } else {
                        currentState = RenderableState.SHOOTING;
                    }
                    break;
                case UNLOAD_PREPARING:
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
                case NEXT_ATTACHMENT:
                    currentState = RenderableState.MODIFYING;
                    break;
                default:
                    if (player.isSprinting() && this.firstPersonPositioningRunning != null) {
                        currentState = RenderableState.RUNNING;
                    } else if (itemWeaponInstance.isAimed()) {
                        currentState = RenderableState.ZOOMING;
                        rate = this.zoomRandomizingRate;
                        amplitude = this.zoomRandomizingAmplitude;
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

        return new StateDescriptor(itemWeaponInstance, stateManager, rate, amplitude);
    }

    private WeaponStateTimed getNextNonExpiredState(ItemWeaponInstance playerWeaponState) {
        WeaponStateTimed weaponStateTimed;
        while ((weaponStateTimed = playerWeaponState.nextHistoryState()) != null) {
            if (System.currentTimeMillis() < weaponStateTimed.getTimestamp() + weaponStateTimed.getDuration()
                && (weaponStateTimed.getState() != WeaponState.FIRING
                    || !this.hasRecoilPositioning() && playerWeaponState.isAutomaticModeEnabled())) {
                break;
            }
        }

        return weaponStateTimed;
    }

    private Consumer<RenderContext> createWeaponPartPositionFunction(Transition t) {
        if (t == null) {
            return (context) -> {};
        } else {
            Consumer<RenderContext> weaponPositionFunction = t.getItemPositioning();
            return weaponPositionFunction != null ? weaponPositionFunction : (context) -> {};
        }
    }

    private Consumer<RenderContext> createWeaponPartPositionFunction(Consumer<RenderContext> weaponPositionFunction) {
        return weaponPositionFunction != null ? weaponPositionFunction : (context) -> {};
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
        if (this.model instanceof ModelWithAttachments) {
            attachments = ((ItemWeapon) weaponItemStack.getItem())
                .getActiveAttachments(renderContext.getPlayer(), weaponItemStack);
        }

        if (this.textureName != null) {
            Minecraft.getMinecraft().renderEngine
                .bindTexture(new ResourceLocation(this.modId + ":textures/models/" + this.textureName));
        } else {
            ItemWeapon weapon = (ItemWeapon) weaponItemStack.getItem();
            String textureName = weapon.getTextureName();

            Minecraft.getMinecraft().renderEngine
                .bindTexture(new ResourceLocation(this.modId + ":textures/models/" + textureName));
        }

        this.model.render(
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

        for (Pair<ModelBase, String> texturedModel : compatibleAttachment.getAttachment()
            .getTexturedModels()) {
            Minecraft.getMinecraft().renderEngine
                .bindTexture(new ResourceLocation(this.modId + ":textures/models/" + texturedModel.getV()));
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
            renderCompatibleAttachment(attachment, positioner, renderContext);
        }

        GL11.glPopAttrib();
        GL11.glPopMatrix();
    }

    public boolean hasRecoilPositioning() {
        return this.hasRecoilPositioningDefined;
    }

    public long getTotalLoadIterationDuration() {
        return this.totalLoadIterationDuration;
    }

    public long getPrepareFirstLoadIterationAnimationDuration() {
        return this.prepareFirstLoadIterationAnimationDuration;
    }

    public long getAllLoadIterationAnimationsCompletedDuration() {
        return this.allLoadIterationAnimationsCompletedDuration;
    }

    private class WeaponPositionProvider implements MultipartTransitionProvider {

        private WeaponPositionProvider() {}

        public List<MultipartTransition> getPositioning(RenderableState state) {
            return switch (state) {
                case MODIFYING -> WeaponRenderer.this.getSimpleTransition(
                    WeaponRenderer.this.firstPersonPositioningModifying,
                    WeaponRenderer.this.firstPersonLeftHandPositioningModifying,
                    WeaponRenderer.this.firstPersonRightHandPositioningModifying,
                    WeaponRenderer.this.firstPersonCustomPositioning,
                    250);
                case RUNNING -> WeaponRenderer.this.getSimpleTransition(
                    WeaponRenderer.this.firstPersonPositioningRunning,
                    WeaponRenderer.this.firstPersonLeftHandPositioningRunning,
                    WeaponRenderer.this.firstPersonRightHandPositioningRunning,
                    WeaponRenderer.this.firstPersonCustomPositioning,
                    250);
                case UNLOADING -> WeaponRenderer.this.getComplexTransition(
                    WeaponRenderer.this.firstPersonPositioningUnloading,
                    WeaponRenderer.this.firstPersonLeftHandPositioningUnloading,
                    WeaponRenderer.this.firstPersonRightHandPositioningUnloading,
                    WeaponRenderer.this.firstPersonCustomPositioningUnloading);
                case RELOADING -> WeaponRenderer.this.getComplexTransition(
                    WeaponRenderer.this.firstPersonPositioningReloading,
                    WeaponRenderer.this.firstPersonLeftHandPositioningReloading,
                    WeaponRenderer.this.firstPersonRightHandPositioningReloading,
                    WeaponRenderer.this.firstPersonCustomPositioningReloading);
                case LOAD_ITERATION -> WeaponRenderer.this.getComplexTransition(
                    WeaponRenderer.this.firstPersonPositioningLoadIteration,
                    WeaponRenderer.this.firstPersonLeftHandPositioningLoadIteration,
                    WeaponRenderer.this.firstPersonRightHandPositioningLoadIteration,
                    WeaponRenderer.this.firstPersonCustomPositioningLoadIteration);
                case LOAD_ITERATION_COMPLETED -> WeaponRenderer.this.getSimpleTransition(
                    WeaponRenderer.this.firstPersonPositioningLoadIterationCompleted,
                    WeaponRenderer.this.firstPersonLeftHandPositioningLoadIterationCompleted,
                    WeaponRenderer.this.firstPersonRightHandPositioningLoadIterationCompleted,
                    WeaponRenderer.this.firstPersonCustomPositioningLoadIterationCompleted,
                    WeaponRenderer.this.loadIterationCompletedAnimationDuration);
                case ALL_LOAD_ITERATIONS_COMPLETED -> WeaponRenderer.this.getComplexTransition(
                    WeaponRenderer.this.firstPersonPositioningAllLoadIterationsCompleted,
                    WeaponRenderer.this.firstPersonLeftHandPositioningAllLoadIterationsCompleted,
                    WeaponRenderer.this.firstPersonRightHandPositioningAllLoadIterationsCompleted,
                    WeaponRenderer.this.firstPersonCustomPositioningLoadIterationsCompleted);
                case RECOILED -> WeaponRenderer.this.getSimpleTransition(
                    WeaponRenderer.this.firstPersonPositioningRecoiled,
                    WeaponRenderer.this.firstPersonLeftHandPositioningRecoiled,
                    WeaponRenderer.this.firstPersonRightHandPositioningRecoiled,
                    WeaponRenderer.this.firstPersonCustomPositioningRecoiled,
                    WeaponRenderer.this.recoilAnimationDuration);
                case SHOOTING -> WeaponRenderer.this.getSimpleTransition(
                    WeaponRenderer.this.firstPersonPositioningShooting,
                    WeaponRenderer.this.firstPersonLeftHandPositioningShooting,
                    WeaponRenderer.this.firstPersonRightHandPositioningShooting,
                    WeaponRenderer.this.firstPersonCustomPositioning,
                    WeaponRenderer.this.shootingAnimationDuration);
                case EJECT_SPENT_ROUND -> WeaponRenderer.this.getComplexTransition(
                    WeaponRenderer.this.firstPersonPositioningEjectSpentRound,
                    WeaponRenderer.this.firstPersonLeftHandPositioningEjectSpentRound,
                    WeaponRenderer.this.firstPersonRightHandPositioningEjectSpentRound,
                    WeaponRenderer.this.firstPersonCustomPositioningEjectSpentRound);
                case NORMAL -> WeaponRenderer.this.getSimpleTransition(
                    WeaponRenderer.this.firstPersonPositioning,
                    WeaponRenderer.this.firstPersonLeftHandPositioning,
                    WeaponRenderer.this.firstPersonRightHandPositioning,
                    WeaponRenderer.this.firstPersonCustomPositioning,
                    250);
                case ZOOMING -> WeaponRenderer.this.getSimpleTransition(
                    WeaponRenderer.this.firstPersonPositioningZooming,
                    WeaponRenderer.this.firstPersonLeftHandPositioningZooming,
                    WeaponRenderer.this.firstPersonRightHandPositioningZooming,
                    WeaponRenderer.this.firstPersonCustomPositioning,
                    250);
                case ZOOMING_SHOOTING -> WeaponRenderer.this.getSimpleTransition(
                    WeaponRenderer.this.firstPersonPositioningZoomingShooting,
                    WeaponRenderer.this.firstPersonLeftHandPositioningZooming,
                    WeaponRenderer.this.firstPersonRightHandPositioningZooming,
                    WeaponRenderer.this.firstPersonCustomPositioningZoomingShooting,
                    60);
                case ZOOMING_RECOILED -> WeaponRenderer.this.getSimpleTransition(
                    WeaponRenderer.this.firstPersonPositioningZoomingRecoiled,
                    WeaponRenderer.this.firstPersonLeftHandPositioningZooming,
                    WeaponRenderer.this.firstPersonRightHandPositioningZooming,
                    WeaponRenderer.this.firstPersonCustomPositioningZoomingRecoiled,
                    60);
                default -> null;
            };
        }

    }

    public static class Builder {

        private final Random random = new Random();

        @Getter
        private ModelBase model;
        @Getter
        private String textureName;

        private final float xOffsetZoom = 0.69F;

        @Getter
        private Consumer<ItemStack> entityPositioning;
        @Getter
        private Consumer<ItemStack> inventoryPositioning;
        @Getter
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
        @Getter
        private String modId;
        private int recoilAnimationDuration = 100;
        private int shootingAnimationDuration = 100;
        private final int loadIterationCompletedAnimationDuration = 100;
        private int prepareFirstLoadIterationAnimationDuration = 100;
        private int allLoadIterationAnimationsCompletedDuration = 100;

        private final float normalRandomizingRate = 0.33F;
        private final float firingRandomizingRate = 20.0F;
        private final float zoomRandomizingRate = 0.25F;
        private final float normalRandomizingAmplitude = 0.06F;
        private final float zoomRandomizingAmplitude = 0.005F;
        private final float firingRandomizingAmplitude = 0.03F;

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

        public WeaponRenderer.Builder withTextureName(String textureName) {
            this.textureName = textureName + ".png";
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
            if (part instanceof StandardPart) {
                throw new IllegalArgumentException("Part " + part + " is not custom");
            } else if (this.firstPersonCustomPositioning.put(part, positioning) != null) {
                throw new IllegalArgumentException("Part " + part + " already added");
            } else {
                return this;
            }
        }

        public WeaponRenderer.Builder withFirstPersonPositioningCustomRecoiled(Part part,
            Consumer<RenderContext> positioning) {
            if (part instanceof StandardPart) {
                throw new IllegalArgumentException("Part " + part + " is not custom");
            } else if (this.firstPersonCustomPositioningRecoiled.put(part, positioning) != null) {
                throw new IllegalArgumentException("Part " + part + " already added");
            } else {
                return this;
            }
        }

        public WeaponRenderer.Builder withFirstPersonPositioningCustomZoomingShooting(Part part,
            Consumer<RenderContext> positioning) {
            if (part instanceof StandardPart) {
                throw new IllegalArgumentException("Part " + part + " is not custom");
            } else if (this.firstPersonCustomPositioningZoomingShooting.put(part, positioning) != null) {
                throw new IllegalArgumentException("Part " + part + " already added");
            } else {
                return this;
            }
        }

        public WeaponRenderer.Builder withFirstPersonPositioningCustomZoomingRecoiled(Part part,
            Consumer<RenderContext> positioning) {
            if (part instanceof StandardPart) {
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
            if (part instanceof StandardPart) {
                throw new IllegalArgumentException("Part " + part + " is not custom");
            } else {
                this.firstPersonCustomPositioningReloading.put(part, Arrays.asList(transitions));
                return this;
            }
        }

        public WeaponRenderer.Builder withFirstPersonCustomPositioningLoadIterationCompleted(Part part,
            Consumer<RenderContext> positioning) {
            if (part instanceof StandardPart) {
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
            if (part instanceof StandardPart) {
                throw new IllegalArgumentException("Part " + part + " is not custom");
            } else {
                this.firstPersonCustomPositioningUnloading.put(part, Arrays.asList(transitions));
                return this;
            }
        }

        @SafeVarargs
        public final WeaponRenderer.Builder withFirstPersonCustomPositioningEjectSpentRound(Part part,
            Transition... transitions) {
            if (part instanceof StandardPart) {
                throw new IllegalArgumentException("Part " + part + " is not custom");
            } else {
                this.firstPersonCustomPositioningEjectSpentRound.put(part, Arrays.asList(transitions));
                return this;
            }
        }

        @SafeVarargs
        public final WeaponRenderer.Builder withFirstPersonCustomPositioningLoadIteration(Part part,
            Transition... transitions) {
            if (part instanceof StandardPart) {
                throw new IllegalArgumentException("Part " + part + " is not custom");
            } else {
                this.firstPersonCustomPositioningLoadIteration.put(part, Arrays.asList(transitions));
                return this;
            }
        }

        @SafeVarargs
        public final WeaponRenderer.Builder withFirstPersonCustomPositioningAllLoadIterationsCompleted(Part part,
            Transition... transitions) {
            if (part instanceof StandardPart) {
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
            } else {
                if (this.inventoryPositioning == null) {
                    this.inventoryPositioning = (itemStack) -> { GL11.glTranslatef(0.0F, 0.12F, 0.0F); };
                }

                if (this.entityPositioning == null) {
                    this.entityPositioning = (itemStack) -> {};
                }

                final WeaponRenderer[] rendererHolder = new WeaponRenderer[1];
                if (this.firstPersonPositioning == null) {
                    this.firstPersonPositioning = (renderContext) -> {
                        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                        WeaponRenderer r = rendererHolder[0];
                        if (r != null && r.getClientModContext() != null) {
                            ItemWeaponInstance instance = r.getClientModContext()
                                .getMainHeldWeapon();
                            if (instance != null && instance.isAimed()) {
                                GL11.glTranslatef(this.xOffsetZoom, 0, 0);
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
                        .map((tx) -> new Transition((c) -> {}, 0L))
                        .collect(Collectors.toList());
                }

                if (this.firstPersonLeftHandPositioningLoadIteration == null) {
                    this.firstPersonLeftHandPositioningLoadIteration = this.firstPersonPositioningReloading.stream()
                        .map((tx) -> new Transition((c) -> {}, 0L))
                        .collect(Collectors.toList());
                }

                if (this.firstPersonLeftHandPositioningAllLoadIterationsCompleted == null) {
                    this.firstPersonLeftHandPositioningAllLoadIterationsCompleted = this.firstPersonPositioningReloading
                        .stream()
                        .map((tx) -> new Transition((c) -> {}, 0L))
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
                        .map((tx) -> new Transition((c) -> {}, 0L))
                        .collect(Collectors.toList());
                }

                if (this.firstPersonRightHandPositioningUnloading == null) {
                    this.firstPersonRightHandPositioningUnloading = this.firstPersonPositioningUnloading.stream()
                        .map((tx) -> new Transition((c) -> {}, 0L))
                        .collect(Collectors.toList());
                }

                if (this.firstPersonRightHandPositioningLoadIteration == null) {
                    this.firstPersonRightHandPositioningLoadIteration = this.firstPersonPositioningReloading.stream()
                        .map((tx) -> new Transition((c) -> {}, 0L))
                        .collect(Collectors.toList());
                }

                if (this.firstPersonRightHandPositioningAllLoadIterationsCompleted == null) {
                    this.firstPersonRightHandPositioningAllLoadIterationsCompleted = this.firstPersonPositioningReloading
                        .stream()
                        .map((tx) -> new Transition((c) -> {}, 0L))
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
                    this.firstPersonCustomPositioningRecoiled.putAll(this.firstPersonCustomPositioning);
                }

                if (!this.firstPersonCustomPositioning.isEmpty()
                    && this.firstPersonCustomPositioningZoomingRecoiled.isEmpty()) {
                    this.firstPersonCustomPositioningZoomingRecoiled.putAll(this.firstPersonCustomPositioning);
                }

                if (!this.firstPersonCustomPositioning.isEmpty()
                    && this.firstPersonCustomPositioningZoomingShooting.isEmpty()) {
                    this.firstPersonCustomPositioningZoomingShooting.putAll(this.firstPersonCustomPositioning);
                }

                if (!this.firstPersonCustomPositioning.isEmpty()
                    && this.firstPersonCustomPositioningLoadIterationCompleted.isEmpty()) {
                    this.firstPersonCustomPositioningLoadIterationCompleted.putAll(this.firstPersonCustomPositioning);
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
                WeaponRenderer renderer = new WeaponRenderer(this);
                rendererHolder[0] = renderer;
                return renderer;
            }
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
                this.setupInventoryRendering();
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
                this.entityPositioning.accept(weaponItemStack);
                break;
            case INVENTORY:
                this.inventoryPositioning.accept(weaponItemStack);
                break;
            case EQUIPPED:
                this.thirdPersonPositioning.accept(renderContext);
                break;
            case EQUIPPED_FIRST_PERSON:
                StateDescriptor stateDescriptor = this.getStateDescriptor((EntityLivingBase) player, weaponItemStack);
                renderContext.setItemInstance(stateDescriptor.instance);
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

    private void setupInventoryRendering() {
        GL11.glClear(256);
        GL11.glMatrixMode(5889);
        GL11.glLoadIdentity();
        GL11.glOrtho(
            0.0D,
            WeaponRenderer.INVENTORY_TEXTURE_WIDTH,
            WeaponRenderer.INVENTORY_TEXTURE_HEIGHT,
            0.0D,
            1000.0D,
            3000.0D);
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
        drawTexturedQuadFit();
        GL11.glPopAttrib();
        GL11.glPopMatrix();
    }

    private static void drawTexturedQuadFit() {
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(0.0D, 256.0, 0.0, 0.0F, 1.0F);
        tessellator.addVertexWithUV(256.0, 256.0, 0.0, 1.0F, 1.0F);
        tessellator.addVertexWithUV(256.0, 0.0D, 0.0, 1.0F, 0.0F);
        tessellator.addVertexWithUV(0.0D, 0.0D, 0.0, 0.0F, 0.0F);
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
        protected float amplitude;
        private final ItemWeaponInstance instance;

        public StateDescriptor(ItemWeaponInstance instance, MultipartRenderStateManager stateManager, float rate,
            float amplitude) {
            this.instance = instance;
            this.stateManager = stateManager;
            this.rate = rate;
            this.amplitude = amplitude;
        }
    }
}
