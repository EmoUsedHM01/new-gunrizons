package com.gtnewhorizon.newgunrizons.client.render;

import com.gtnewhorizon.newgunrizons.attachment.CompatibleAttachment;
import com.gtnewhorizon.newgunrizons.attachment.Part;
import com.gtnewhorizon.newgunrizons.attachment.StandardPart;
import com.gtnewhorizon.newgunrizons.client.animation.MultipartPositioning;
import com.gtnewhorizon.newgunrizons.client.animation.MultipartRenderStateManager;
import com.gtnewhorizon.newgunrizons.client.animation.MultipartTransition;
import com.gtnewhorizon.newgunrizons.client.animation.MultipartTransitionProvider;
import com.gtnewhorizon.newgunrizons.client.animation.Transition;
import com.gtnewhorizon.newgunrizons.enchantments.ModEnchantments;
import com.gtnewhorizon.newgunrizons.items.ItemAttachment;
import com.gtnewhorizon.newgunrizons.items.ItemWeapon;
import com.gtnewhorizon.newgunrizons.items.instances.ItemInstance;
import com.gtnewhorizon.newgunrizons.items.instances.ItemInstanceRegistry;
import com.gtnewhorizon.newgunrizons.items.instances.ItemWeaponInstance;
import com.gtnewhorizon.newgunrizons.model.ModelWithAttachments;
import com.gtnewhorizon.newgunrizons.state.RenderableState;
import com.gtnewhorizon.newgunrizons.util.Pair;
import com.gtnewhorizon.newgunrizons.weapon.WeaponState;
import com.gtnewhorizon.newgunrizons.weapon.WeaponStateTimed;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;
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
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
import org.lwjgl.opengl.GL11;

public class WeaponRenderer implements IItemRenderer {
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
   private static final float MIN_ALPHA_THRESHOLD = 0.003921569F;
   private static final int ATTRIB_ENABLE_CURRENT = 8193;
   private final Map<EntityLivingBase, MultipartRenderStateManager> firstPersonStateManagers = new HashMap<>();
   private final MultipartTransitionProvider weaponTransitionProvider = new WeaponRenderer.WeaponPositionProvider();
   private final ModelBase model;
   private final String textureName;
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
   private Integer cachedInventoryTexture;

   private WeaponRenderer(WeaponRenderer.Builder builder) {
      this.model = builder.getModel();
      this.textureName = builder.getTextureName();
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
      this.recoilAnimationDuration = 100;
      this.shootingAnimationDuration = 100;
      this.loadIterationCompletedAnimationDuration = 100;
      this.prepareFirstLoadIterationAnimationDuration = builder.prepareFirstLoadIterationAnimationDuration;
      this.allLoadIterationAnimationsCompletedDuration = builder.allLoadIterationAnimationsCompletedDuration;
      this.normalRandomizingRate = 1.65F;
      this.firingRandomizingRate = 20.0F;
      this.zoomRandomizingRate = 0.25F;
      this.normalRandomizingAmplitude = 0.06F;
      this.zoomRandomizingAmplitude = 0.005F;
      this.firingRandomizingAmplitude = 0.03F;
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

   protected WeaponRenderer.StateDescriptor getStateDescriptor(EntityLivingBase player, ItemStack itemStack) {
      float amplitude = this.normalRandomizingAmplitude;
      float rate = this.normalRandomizingRate;
      float verticalBias = 1.0F;
      float animationSpeedMultiplier = 1.0F;
      RenderableState currentState = null;
      ItemInstance<?> itemInstance = ItemInstanceRegistry.INSTANCE.getItemInstance(player, itemStack);
      ItemWeaponInstance itemWeaponInstance = null;
      if (itemInstance instanceof ItemWeaponInstance && itemInstance.getItem() == itemStack.getItem()) {
         itemWeaponInstance = (ItemWeaponInstance)itemInstance;
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
                  boolean isLongPaused = (float)(System.currentTimeMillis() - weaponStateTimed.getTimestamp()) > 50.0F / itemWeaponInstance.getFireRate()
                     && weaponStateTimed.isInfinite();
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
               animationSpeedMultiplier = getReloadAnimationSpeedMultiplier(itemStack);
               break;
            case LOAD:
               currentState = RenderableState.RELOADING;
               animationSpeedMultiplier = getReloadAnimationSpeedMultiplier(itemStack);
               break;
            case LOAD_ITERATION:
               currentState = RenderableState.LOAD_ITERATION;
               animationSpeedMultiplier = getReloadAnimationSpeedMultiplier(itemStack);
               break;
            case LOAD_ITERATION_COMPLETED:
               currentState = RenderableState.LOAD_ITERATION_COMPLETED;
               animationSpeedMultiplier = getReloadAnimationSpeedMultiplier(itemStack);
               break;
            case ALL_LOAD_ITERATIONS_COMPLETED:
               currentState = RenderableState.ALL_LOAD_ITERATIONS_COMPLETED;
               animationSpeedMultiplier = getReloadAnimationSpeedMultiplier(itemStack);
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
                  rate = 8.0F;
                  verticalBias = 3.0F;
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
            currentState == RenderableState.SHOOTING
               || currentState == RenderableState.ZOOMING_SHOOTING
               || currentState == RenderableState.RUNNING
               || currentState == RenderableState.ZOOMING
               || currentState == RenderableState.NORMAL,
            animationSpeedMultiplier
         );
      }

      return new WeaponRenderer.StateDescriptor(itemWeaponInstance, stateManager, rate, amplitude, verticalBias);
   }

   private static float getReloadAnimationSpeedMultiplier(ItemStack itemStack) {
      int fastHandsLevel = ModEnchantments.getLevel(ModEnchantments.fastHands, itemStack);
      return (float)Math.max(0.2, 1.0 - fastHandsLevel * 0.2);
   }

   private WeaponStateTimed getNextNonExpiredState(ItemWeaponInstance playerWeaponState) {
      WeaponStateTimed weaponStateTimed;
      while (
         (weaponStateTimed = playerWeaponState.nextHistoryState()) != null
            && (
               System.currentTimeMillis() >= weaponStateTimed.getTimestamp() + weaponStateTimed.getDuration()
                  || weaponStateTimed.getState() == WeaponState.FIRING && (this.hasRecoilPositioning() || !playerWeaponState.isAutomaticModeEnabled())
            )
      ) {
      }

      return weaponStateTimed;
   }

   private Consumer<RenderContext> createWeaponPartPositionFunction(Transition t) {
      if (t == null) {
         return context -> {};
      } else {
         Consumer<RenderContext> weaponPositionFunction = t.getItemPositioning();
         return weaponPositionFunction != null ? weaponPositionFunction : context -> {};
      }
   }

   private Consumer<RenderContext> createWeaponPartPositionFunction(Consumer<RenderContext> weaponPositionFunction) {
      return weaponPositionFunction != null ? weaponPositionFunction : context -> {};
   }

   private List<MultipartTransition> getComplexTransition(
      List<Transition> wt, List<Transition> lht, List<Transition> rht, LinkedHashMap<Part, List<Transition>> custom
   ) {
      List<MultipartTransition> result = new ArrayList<>();

      for (int i = 0; i < wt.size(); i++) {
         Transition p = wt.get(i);
         Transition l = lht.get(i);
         Transition r = rht.get(i);
         MultipartTransition t = new MultipartTransition(p.getDuration(), p.getPause())
            .withPartPositionFunction(Part.MAIN_ITEM, this.createWeaponPartPositionFunction(p))
            .withPartPositionFunction(Part.LEFT_HAND, this.createWeaponPartPositionFunction(l))
            .withPartPositionFunction(Part.RIGHT_HAND, this.createWeaponPartPositionFunction(r));

         for (Entry<Part, List<Transition>> e : custom.entrySet()) {
            List<Transition> partTransitions = e.getValue();
            Transition partTransition = null;
            if (partTransitions != null && partTransitions.size() > i) {
               partTransition = partTransitions.get(i);
            }

            t.withPartPositionFunction(e.getKey(), this.createWeaponPartPositionFunction(partTransition));
         }

         result.add(t);
      }

      return result;
   }

   private List<MultipartTransition> getSimpleTransition(
      Consumer<RenderContext> w, Consumer<RenderContext> lh, Consumer<RenderContext> rh, LinkedHashMap<Part, Consumer<RenderContext>> custom, int duration
   ) {
      MultipartTransition mt = new MultipartTransition(duration, 0L)
         .withPartPositionFunction(Part.MAIN_ITEM, this.createWeaponPartPositionFunction(w))
         .withPartPositionFunction(Part.LEFT_HAND, this.createWeaponPartPositionFunction(lh))
         .withPartPositionFunction(Part.RIGHT_HAND, this.createWeaponPartPositionFunction(rh));
      custom.forEach((part, position) -> mt.withPartPositionFunction(part, this.createWeaponPartPositionFunction((Consumer<RenderContext>)position)));
      return Collections.singletonList(mt);
   }

   public void renderItem(ItemStack weaponItemStack, RenderContext renderContext, MultipartPositioning.Positioner positioner) {
      List<CompatibleAttachment> attachments = null;
      if (this.model instanceof ModelWithAttachments) {
         attachments = ((ItemWeapon)weaponItemStack.getItem()).getActiveAttachments(renderContext.getPlayer(), weaponItemStack);
      }

      if (this.textureName != null) {
         Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("newgunrizons:textures/models/" + this.textureName));
      } else {
         ItemWeapon weapon = (ItemWeapon)weaponItemStack.getItem();
         String textureName = weapon.getTextureName();
         Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("newgunrizons:textures/models/" + textureName));
      }

      this.model
         .render(
            null,
            renderContext.getLimbSwing(),
            renderContext.getLimbSwingAmount(),
            renderContext.getAgeInTicks(),
            renderContext.getNetHeadYaw(),
            renderContext.getHeadPitch(),
            renderContext.getScale()
         );
      if (attachments != null) {
         this.renderAttachments(positioner, renderContext, attachments);
      }
   }

   public void renderAttachments(MultipartPositioning.Positioner positioner, RenderContext renderContext, List<CompatibleAttachment> attachments) {
      for (CompatibleAttachment attachment : attachments) {
         if (attachment != null) {
            this.renderCompatibleAttachment(attachment, positioner, renderContext);
         }
      }
   }

   private void renderCompatibleAttachment(CompatibleAttachment compatibleAttachment, MultipartPositioning.Positioner positioner, RenderContext renderContext) {
      GL11.glPushMatrix();
      GL11.glPushAttrib(8193);
      if (compatibleAttachment.getPositioning() != null) {
         compatibleAttachment.getPositioning().accept(renderContext.getPlayer(), renderContext.getWeapon());
      }

      ItemAttachment itemAttachment = compatibleAttachment.getAttachment();
      if (positioner != null) {
         if (itemAttachment instanceof Part) {
            positioner.position((Part)itemAttachment, renderContext);
         } else if (itemAttachment.getRenderablePart() != null) {
            positioner.position(itemAttachment.getRenderablePart(), renderContext);
         }
      }

      for (Pair<ModelBase, String> texturedModel : compatibleAttachment.getAttachment().getTexturedModels()) {
         Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("newgunrizons:textures/models/" + texturedModel.getV()));
         GL11.glPushMatrix();
         GL11.glPushAttrib(8193);
         if (compatibleAttachment.getModelPositioning() != null) {
            compatibleAttachment.getModelPositioning().accept(texturedModel.getU());
         }

         texturedModel.getU()
            .render(
               renderContext.getPlayer(),
               renderContext.getLimbSwing(),
               renderContext.getLimbSwingAmount(),
               renderContext.getAgeInTicks(),
               renderContext.getNetHeadYaw(),
               renderContext.getHeadPitch(),
               renderContext.getScale()
            );
         GL11.glPopAttrib();
         GL11.glPopMatrix();
      }

      CustomRenderer postRenderer = compatibleAttachment.getAttachment().getPostRenderer();
      if (postRenderer != null) {
         GL11.glPushMatrix();
         GL11.glPushAttrib(8193);
         postRenderer.render(renderContext);
         GL11.glPopAttrib();
         GL11.glPopMatrix();
      }

      for (CompatibleAttachment attachment : itemAttachment.getAttachments()) {
         this.renderCompatibleAttachment(attachment, positioner, renderContext);
      }

      GL11.glPopAttrib();
      GL11.glPopMatrix();
   }

   public boolean hasRecoilPositioning() {
      return this.hasRecoilPositioningDefined;
   }

   public long getPrepareFirstLoadIterationAnimationDuration() {
      return this.prepareFirstLoadIterationAnimationDuration;
   }

   public long getAllLoadIterationAnimationsCompletedDuration() {
      return this.allLoadIterationAnimationsCompletedDuration;
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
         inventoryTexture = this.cachedInventoryTexture;
         if (inventoryTexture == null) {
            inventoryTextureInitializationPhaseOn = true;
            originalFramebufferId = Framebuffers.getCurrentFramebuffer();
            Framebuffers.unbindFramebuffer();
            framebuffer = new Framebuffer(256, 256, true);
            inventoryTexture = framebuffer.framebufferTexture;
            this.cachedInventoryTexture = inventoryTexture;
            framebuffer.bindFramebuffer(true);
            this.setupInventoryRendering();
            GL11.glScalef(130.0F, 130.0F, 130.0F);
            GL11.glRotatef(25.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
            GL11.glTranslatef(1.45F, 1.4F, 0.0F);
         }
      }

      GL11.glScaled(-1.0, -1.0, 1.0);
      Object player;
      if (data.length > 1 && data[1] instanceof EntityLivingBase) {
         player = data[1];
      } else {
         player = Minecraft.getMinecraft().thePlayer;
      }

      RenderContext renderContext = new RenderContext((EntityLivingBase)player, weaponItemStack);
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
            WeaponRenderer.StateDescriptor stateDescriptor = this.getStateDescriptor((EntityLivingBase)player, weaponItemStack);
            renderContext.setItemInstance(stateDescriptor.instance);
            MultipartPositioning multipartPositioning = stateDescriptor.stateManager.nextPositioning();
            renderContext.setTransitionProgress(multipartPositioning.getProgress());
            renderContext.setFromState(multipartPositioning.getFromState(RenderableState.class));
            renderContext.setToState(multipartPositioning.getToState(RenderableState.class));
            positioner = multipartPositioning.getPositioner();
            positioner.applySway(stateDescriptor.rate, stateDescriptor.amplitude, stateDescriptor.verticalBias);
            positioner.position(Part.MAIN_ITEM, renderContext);
            renderLeftArm((EntityPlayer)player, renderContext, positioner);
            renderRightArm((EntityPlayer)player, renderContext, positioner);
      }

      if (type != ItemRenderType.INVENTORY || inventoryTextureInitializationPhaseOn) {
         this.renderItem(weaponItemStack, renderContext, positioner);
      }

      if (type == ItemRenderType.EQUIPPED_FIRST_PERSON) {
         MuzzleFlashRenderer.renderIfFiring(renderContext);
      }

      if (type == ItemRenderType.INVENTORY && inventoryTextureInitializationPhaseOn) {
         framebuffer.unbindFramebuffer();
         framebuffer.framebufferTexture = -1;
         framebuffer.deleteFramebuffer();
         this.restoreInventoryRendering(scaledresolution);
      }

      GL11.glPopMatrix();
      if (originalFramebufferId >= 0) {
         Framebuffers.bindFramebuffer(originalFramebufferId, true, mc.getFramebuffer().framebufferWidth, mc.getFramebuffer().framebufferHeight);
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
      GL11.glOrtho(0.0, 256.0, 256.0, 0.0, 1000.0, 3000.0);
      GL11.glMatrixMode(5888);
      GL11.glLoadIdentity();
      GL11.glTranslatef(0.0F, 0.0F, -2000.0F);
   }

   private void restoreInventoryRendering(ScaledResolution scaledresolution) {
      GL11.glMatrixMode(5889);
      GL11.glLoadIdentity();
      GL11.glOrtho(0.0, scaledresolution.getScaledWidth_double(), scaledresolution.getScaledHeight_double(), 0.0, 1000.0, 3000.0);
      GL11.glMatrixMode(5888);
   }

   private void renderCachedInventoryTexture(Integer inventoryTexture) {
      GL11.glPushMatrix();
      GL11.glPushAttrib(524288);
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
      tessellator.addVertexWithUV(0.0, 256.0, 0.0, 0.0, 1.0);
      tessellator.addVertexWithUV(256.0, 256.0, 0.0, 1.0, 1.0);
      tessellator.addVertexWithUV(256.0, 0.0, 0.0, 1.0, 0.0);
      tessellator.addVertexWithUV(0.0, 0.0, 0.0, 0.0, 0.0);
      tessellator.draw();
   }

   static void renderRightArm(EntityPlayer player, RenderContext renderContext, MultipartPositioning.Positioner positioner) {
      RenderPlayer render = (RenderPlayer)RenderManager.instance.getEntityRenderObject(player);
      Minecraft.getMinecraft().getTextureManager().bindTexture(((AbstractClientPlayer)player).getLocationSkin());
      GL11.glPushMatrix();
      GL11.glTranslatef(-0.25F, 0.0F, 0.2F);
      GL11.glRotatef(5.0F, 1.0F, 0.0F, 0.0F);
      GL11.glRotatef(25.0F, 0.0F, 1.0F, 0.0F);
      positioner.position(Part.RIGHT_HAND, renderContext);
      GL11.glColor3f(1.0F, 1.0F, 1.0F);
      render.modelBipedMain.onGround = 0.0F;
      render.modelBipedMain.setRotationAngles(0.0F, 0.3F, 0.0F, 0.0F, 0.0F, 0.0625F, player);
      render.modelBipedMain.bipedRightArm.render(0.0625F);
      GL11.glPopMatrix();
   }

   static void renderLeftArm(EntityPlayer player, RenderContext renderContext, MultipartPositioning.Positioner positioner) {
      RenderPlayer render = (RenderPlayer)RenderManager.instance.getEntityRenderObject(player);
      Minecraft.getMinecraft().getTextureManager().bindTexture(((AbstractClientPlayer)player).getLocationSkin());
      GL11.glPushMatrix();
      GL11.glTranslatef(0.0F, -1.0F, 0.0F);
      GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
      GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
      positioner.position(Part.LEFT_HAND, renderContext);
      GL11.glColor3f(1.0F, 1.0F, 1.0F);
      render.modelBipedMain.onGround = 0.0F;
      render.modelBipedMain.setRotationAngles(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, player);
      render.modelBipedMain.bipedLeftArm.render(0.0625F);
      GL11.glPopMatrix();
   }

   public long getTotalReloadingDuration() {
      return this.totalReloadingDuration;
   }

   public long getTotalUnloadingDuration() {
      return this.totalUnloadingDuration;
   }

   public long getTotalLoadIterationDuration() {
      return this.totalLoadIterationDuration;
   }

   public static class Builder {
      private final Random random = new Random();
      private ModelBase model;
      private String textureName;
      private final float xOffsetZoom = 0.69F;
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
      private final int recoilAnimationDuration = 100;
      private final int shootingAnimationDuration = 100;
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

      public WeaponRenderer.Builder withModel(ModelBase model) {
         this.model = model;
         return this;
      }

      public WeaponRenderer.Builder withPrepareFirstLoadIterationAnimationDuration(int prepareFirstLoadIterationAnimationDuration) {
         this.prepareFirstLoadIterationAnimationDuration = prepareFirstLoadIterationAnimationDuration;
         return this;
      }

      public WeaponRenderer.Builder withAllLoadIterationAnimationsCompletedDuration(int allLoadIterationAnimationsCompletedDuration) {
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

      public WeaponRenderer.Builder withFirstPersonPositioningRunning(Consumer<RenderContext> firstPersonPositioningRunning) {
         this.firstPersonPositioningRunning = firstPersonPositioningRunning;
         return this;
      }

      public WeaponRenderer.Builder withFirstPersonPositioningZooming(Consumer<RenderContext> firstPersonPositioningZooming) {
         this.firstPersonPositioningZooming = firstPersonPositioningZooming;
         return this;
      }

      public WeaponRenderer.Builder withFirstPersonPositioningRecoiled(Consumer<RenderContext> firstPersonPositioningRecoiled) {
         this.hasRecoilPositioningDefined = true;
         this.firstPersonPositioningRecoiled = firstPersonPositioningRecoiled;
         return this;
      }

      public WeaponRenderer.Builder withFirstPersonPositioningZoomingRecoiled(Consumer<RenderContext> firstPersonPositioningZoomingRecoiled) {
         this.firstPersonPositioningZoomingRecoiled = firstPersonPositioningZoomingRecoiled;
         return this;
      }

      public WeaponRenderer.Builder withFirstPersonPositioningLoadIterationCompleted(Consumer<RenderContext> firstPersonPositioningLoadIterationCompleted) {
         this.firstPersonPositioningLoadIterationCompleted = firstPersonPositioningLoadIterationCompleted;
         return this;
      }

      public WeaponRenderer.Builder withFirstPersonPositioningReloading(Transition... transitions) {
         this.firstPersonPositioningReloading = Arrays.asList(transitions);
         return this;
      }

      public WeaponRenderer.Builder withFirstPersonPositioningUnloading(Transition... transitions) {
         this.firstPersonPositioningUnloading = Arrays.asList(transitions);
         return this;
      }

      public WeaponRenderer.Builder withFirstPersonPositioningLoadIteration(Transition... transitions) {
         this.firstPersonPositioningLoadIteration = Arrays.asList(transitions);
         return this;
      }

      public WeaponRenderer.Builder withFirstPersonPositioningAllLoadIterationsCompleted(Transition... transitions) {
         this.firstPersonPositioningAllLoadIterationsCompleted = Arrays.asList(transitions);
         return this;
      }

      public WeaponRenderer.Builder withFirstPersonPositioningEjectSpentRound(Transition... transitions) {
         this.firstPersonPositioningEjectSpentRound = Arrays.asList(transitions);
         return this;
      }

      public WeaponRenderer.Builder withFirstPersonPositioningModifying(Consumer<RenderContext> firstPersonPositioningModifying) {
         this.firstPersonPositioningModifying = firstPersonPositioningModifying;
         return this;
      }

      public WeaponRenderer.Builder withFirstPersonHandPositioning(Consumer<RenderContext> leftHand, Consumer<RenderContext> rightHand) {
         this.firstPersonLeftHandPositioning = leftHand;
         this.firstPersonRightHandPositioning = rightHand;
         return this;
      }

      public WeaponRenderer.Builder withFirstPersonHandPositioningRunning(Consumer<RenderContext> leftHand, Consumer<RenderContext> rightHand) {
         this.firstPersonLeftHandPositioningRunning = leftHand;
         this.firstPersonRightHandPositioningRunning = rightHand;
         return this;
      }

      public WeaponRenderer.Builder withFirstPersonHandPositioningZooming(Consumer<RenderContext> leftHand, Consumer<RenderContext> rightHand) {
         this.firstPersonLeftHandPositioningZooming = leftHand;
         this.firstPersonRightHandPositioningZooming = rightHand;
         return this;
      }

      public WeaponRenderer.Builder withFirstPersonHandPositioningLoadIterationCompleted(Consumer<RenderContext> leftHand, Consumer<RenderContext> rightHand) {
         this.firstPersonLeftHandPositioningLoadIterationCompleted = leftHand;
         this.firstPersonRightHandPositioningLoadIterationCompleted = rightHand;
         return this;
      }

      public WeaponRenderer.Builder withFirstPersonLeftHandPositioningReloading(Transition... transitions) {
         this.firstPersonLeftHandPositioningReloading = Arrays.asList(transitions);
         return this;
      }

      public WeaponRenderer.Builder withFirstPersonLeftHandPositioningEjectSpentRound(Transition... transitions) {
         this.firstPersonLeftHandPositioningEjectSpentRound = Arrays.asList(transitions);
         return this;
      }

      public WeaponRenderer.Builder withFirstPersonLeftHandPositioningUnloading(Transition... transitions) {
         this.firstPersonLeftHandPositioningUnloading = Arrays.asList(transitions);
         return this;
      }

      public WeaponRenderer.Builder withFirstPersonLeftHandPositioningLoadIteration(Transition... transitions) {
         this.firstPersonLeftHandPositioningLoadIteration = Arrays.asList(transitions);
         return this;
      }

      public WeaponRenderer.Builder withFirstPersonLeftHandPositioningAllLoadIterationsCompleted(Transition... transitions) {
         this.firstPersonLeftHandPositioningAllLoadIterationsCompleted = Arrays.asList(transitions);
         return this;
      }

      public WeaponRenderer.Builder withFirstPersonRightHandPositioningReloading(Transition... transitions) {
         this.firstPersonRightHandPositioningReloading = Arrays.asList(transitions);
         return this;
      }

      public WeaponRenderer.Builder withFirstPersonRightHandPositioningUnloading(Transition... transitions) {
         this.firstPersonRightHandPositioningUnloading = Arrays.asList(transitions);
         return this;
      }

      public WeaponRenderer.Builder withFirstPersonRightHandPositioningEjectSpentRound(Transition... transitions) {
         this.firstPersonRightHandPositioningEjectSpentRound = Arrays.asList(transitions);
         return this;
      }

      public WeaponRenderer.Builder withFirstPersonRightHandPositioningLoadIteration(Transition... transitions) {
         this.firstPersonRightHandPositioningLoadIteration = Arrays.asList(transitions);
         return this;
      }

      public WeaponRenderer.Builder withFirstPersonRightHandPositioningAllLoadIterationsCompleted(Transition... transitions) {
         this.firstPersonRightHandPositioningAllLoadIterationsCompleted = Arrays.asList(transitions);
         return this;
      }

      public WeaponRenderer.Builder withFirstPersonHandPositioningModifying(Consumer<RenderContext> leftHand, Consumer<RenderContext> rightHand) {
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

      public WeaponRenderer.Builder withFirstPersonPositioningCustomRecoiled(Part part, Consumer<RenderContext> positioning) {
         if (part instanceof StandardPart) {
            throw new IllegalArgumentException("Part " + part + " is not custom");
         } else if (this.firstPersonCustomPositioningRecoiled.put(part, positioning) != null) {
            throw new IllegalArgumentException("Part " + part + " already added");
         } else {
            return this;
         }
      }

      public WeaponRenderer.Builder withFirstPersonPositioningCustomZoomingRecoiled(Part part, Consumer<RenderContext> positioning) {
         if (part instanceof StandardPart) {
            throw new IllegalArgumentException("Part " + part + " is not custom");
         } else if (this.firstPersonCustomPositioningZoomingRecoiled.put(part, positioning) != null) {
            throw new IllegalArgumentException("Part " + part + " already added");
         } else {
            return this;
         }
      }

      public WeaponRenderer.Builder withFirstPersonCustomPositioningReloading(Part part, Transition... transitions) {
         if (part instanceof StandardPart) {
            throw new IllegalArgumentException("Part " + part + " is not custom");
         } else {
            this.firstPersonCustomPositioningReloading.put(part, Arrays.asList(transitions));
            return this;
         }
      }

      public WeaponRenderer.Builder withFirstPersonCustomPositioningLoadIterationCompleted(Part part, Consumer<RenderContext> positioning) {
         if (part instanceof StandardPart) {
            throw new IllegalArgumentException("Part " + part + " is not custom");
         } else if (this.firstPersonCustomPositioningLoadIterationCompleted.put(part, positioning) != null) {
            throw new IllegalArgumentException("Part " + part + " already added");
         } else {
            return this;
         }
      }

      public WeaponRenderer.Builder withFirstPersonCustomPositioningUnloading(Part part, Transition... transitions) {
         if (part instanceof StandardPart) {
            throw new IllegalArgumentException("Part " + part + " is not custom");
         } else {
            this.firstPersonCustomPositioningUnloading.put(part, Arrays.asList(transitions));
            return this;
         }
      }

      public WeaponRenderer.Builder withFirstPersonCustomPositioningEjectSpentRound(Part part, Transition... transitions) {
         if (part instanceof StandardPart) {
            throw new IllegalArgumentException("Part " + part + " is not custom");
         } else {
            this.firstPersonCustomPositioningEjectSpentRound.put(part, Arrays.asList(transitions));
            return this;
         }
      }

      public WeaponRenderer.Builder withFirstPersonCustomPositioningLoadIteration(Part part, Transition... transitions) {
         if (part instanceof StandardPart) {
            throw new IllegalArgumentException("Part " + part + " is not custom");
         } else {
            this.firstPersonCustomPositioningLoadIteration.put(part, Arrays.asList(transitions));
            return this;
         }
      }

      public WeaponRenderer.Builder withFirstPersonCustomPositioningAllLoadIterationsCompleted(Part part, Transition... transitions) {
         if (part instanceof StandardPart) {
            throw new IllegalArgumentException("Part " + part + " is not custom");
         } else {
            this.firstPersonCustomPositioningLoadIterationsCompleted.put(part, Arrays.asList(transitions));
            return this;
         }
      }

      public WeaponRenderer build() {
         if (FMLCommonHandler.instance().getSide() != Side.CLIENT) {
            return null;
         } else {
            if (this.inventoryPositioning == null) {
               this.inventoryPositioning = itemStack -> GL11.glTranslatef(0.0F, 0.12F, 0.0F);
            }

            if (this.entityPositioning == null) {
               this.entityPositioning = itemStack -> {};
            }

            if (this.firstPersonPositioning == null) {
               this.firstPersonPositioning = renderContext -> {
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  ItemWeaponInstance instance = ItemInstanceRegistry.getMainHeldWeapon();
                  if (instance != null && instance.isAimed()) {
                     GL11.glTranslatef(0.69F, 0.0F, 0.0F);
                  } else {
                     GL11.glTranslatef(0.0F, -1.2F, 0.0F);
                  }
               };
            }

            if (this.firstPersonPositioningZooming == null) {
               this.firstPersonPositioningZooming = this.firstPersonPositioning;
            }

            if (this.firstPersonPositioningReloading == null) {
               this.firstPersonPositioningReloading = Collections.singletonList(new Transition(this.firstPersonPositioning, 250L));
            }

            if (this.firstPersonPositioningLoadIteration == null) {
               this.firstPersonPositioningLoadIteration = Collections.singletonList(new Transition(this.firstPersonPositioning, 250L));
            }

            for (Transition t : this.firstPersonPositioningReloading) {
               this.totalReloadingDuration = this.totalReloadingDuration + t.getDuration() + t.getPause();
            }

            for (Transition t : this.firstPersonPositioningLoadIteration) {
               this.totalLoadIterationDuration = this.totalLoadIterationDuration + t.getDuration() + t.getPause();
            }

            if (this.firstPersonPositioningUnloading == null) {
               this.firstPersonPositioningUnloading = Collections.singletonList(new Transition(this.firstPersonPositioning, 250L));
            }

            for (Transition t : this.firstPersonPositioningUnloading) {
               this.totalUnloadingDuration = this.totalUnloadingDuration + t.getDuration() + t.getPause();
            }

            if (this.firstPersonPositioningAllLoadIterationsCompleted == null) {
               this.firstPersonPositioningAllLoadIterationsCompleted = Collections.singletonList(new Transition(this.firstPersonPositioning, 250L));
            }

            if (this.firstPersonPositioningRecoiled == null) {
               this.firstPersonPositioningRecoiled = this.firstPersonPositioning;
            } else {
               Consumer<RenderContext> firstPersonPositioningRecoiledOrig = this.firstPersonPositioningRecoiled;
               this.firstPersonPositioningRecoiled = renderContext -> {
                  float maxAngle = 1.5F;
                  GL11.glRotatef(this.random.nextFloat() * maxAngle, 1.0F, 0.0F, 0.0F);
                  GL11.glRotatef(this.random.nextFloat() * maxAngle, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(this.random.nextFloat() * maxAngle, 0.0F, 0.0F, 1.0F);
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
               this.thirdPersonPositioning = context -> {
                  GL11.glTranslatef(-0.4F, 0.2F, 0.4F);
                  GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(70.0F, 1.0F, 0.0F, 0.0F);
               };
            }

            if (this.firstPersonLeftHandPositioning == null) {
               this.firstPersonLeftHandPositioning = context -> {};
            }

            if (this.firstPersonLeftHandPositioningReloading == null) {
               this.firstPersonLeftHandPositioningReloading = this.firstPersonPositioningReloading
                  .stream()
                  .map(tx -> new Transition(c -> {}, 0L))
                  .collect(Collectors.toList());
            }

            if (this.firstPersonLeftHandPositioningUnloading == null) {
               this.firstPersonLeftHandPositioningUnloading = this.firstPersonPositioningUnloading
                  .stream()
                  .map(tx -> new Transition(c -> {}, 0L))
                  .collect(Collectors.toList());
            }

            if (this.firstPersonLeftHandPositioningLoadIteration == null) {
               this.firstPersonLeftHandPositioningLoadIteration = this.firstPersonPositioningReloading
                  .stream()
                  .map(tx -> new Transition(c -> {}, 0L))
                  .collect(Collectors.toList());
            }

            if (this.firstPersonLeftHandPositioningAllLoadIterationsCompleted == null) {
               this.firstPersonLeftHandPositioningAllLoadIterationsCompleted = this.firstPersonPositioningReloading
                  .stream()
                  .map(tx -> new Transition(c -> {}, 0L))
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
               this.firstPersonRightHandPositioning = context -> {};
            }

            if (this.firstPersonRightHandPositioningReloading == null) {
               this.firstPersonRightHandPositioningReloading = this.firstPersonPositioningReloading
                  .stream()
                  .map(tx -> new Transition(c -> {}, 0L))
                  .collect(Collectors.toList());
            }

            if (this.firstPersonRightHandPositioningUnloading == null) {
               this.firstPersonRightHandPositioningUnloading = this.firstPersonPositioningUnloading
                  .stream()
                  .map(tx -> new Transition(c -> {}, 0L))
                  .collect(Collectors.toList());
            }

            if (this.firstPersonRightHandPositioningLoadIteration == null) {
               this.firstPersonRightHandPositioningLoadIteration = this.firstPersonPositioningReloading
                  .stream()
                  .map(tx -> new Transition(c -> {}, 0L))
                  .collect(Collectors.toList());
            }

            if (this.firstPersonRightHandPositioningAllLoadIterationsCompleted == null) {
               this.firstPersonRightHandPositioningAllLoadIterationsCompleted = this.firstPersonPositioningReloading
                  .stream()
                  .map(tx -> new Transition(c -> {}, 0L))
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

            if (!this.firstPersonCustomPositioning.isEmpty() && this.firstPersonCustomPositioningRecoiled.isEmpty()) {
               this.firstPersonCustomPositioningRecoiled.putAll(this.firstPersonCustomPositioning);
            }

            if (!this.firstPersonCustomPositioning.isEmpty() && this.firstPersonCustomPositioningZoomingRecoiled.isEmpty()) {
               this.firstPersonCustomPositioningZoomingRecoiled.putAll(this.firstPersonCustomPositioning);
            }

            if (!this.firstPersonCustomPositioning.isEmpty() && this.firstPersonCustomPositioningZoomingShooting.isEmpty()) {
               this.firstPersonCustomPositioningZoomingShooting.putAll(this.firstPersonCustomPositioning);
            }

            if (!this.firstPersonCustomPositioning.isEmpty() && this.firstPersonCustomPositioningLoadIterationCompleted.isEmpty()) {
               this.firstPersonCustomPositioningLoadIterationCompleted.putAll(this.firstPersonCustomPositioning);
            }

            this.firstPersonCustomPositioningReloading
               .forEach(
                  (p, tx) -> {
                     if (tx.size() != this.firstPersonPositioningReloading.size()) {
                        throw new IllegalStateException(
                           "Custom reloading transition number mismatch. Expected " + this.firstPersonPositioningReloading.size() + ", actual: " + tx.size()
                        );
                     }
                  }
               );
            this.firstPersonCustomPositioningUnloading
               .forEach(
                  (p, tx) -> {
                     if (tx.size() != this.firstPersonPositioningUnloading.size()) {
                        throw new IllegalStateException(
                           "Custom unloading transition number mismatch. Expected " + this.firstPersonPositioningUnloading.size() + ", actual: " + tx.size()
                        );
                     }
                  }
               );
            this.firstPersonCustomPositioningLoadIteration
               .forEach(
                  (p, tx) -> {
                     if (tx.size() != this.firstPersonPositioningLoadIteration.size()) {
                        throw new IllegalStateException(
                           "Custom load iteration transition number mismatch. Expected "
                              + this.firstPersonPositioningLoadIteration.size()
                              + ", actual: "
                              + tx.size()
                        );
                     }
                  }
               );
            this.firstPersonCustomPositioningLoadIterationsCompleted
               .forEach(
                  (p, tx) -> {
                     if (tx.size() != this.firstPersonPositioningAllLoadIterationsCompleted.size()) {
                        throw new IllegalStateException(
                           "Custom load iterations completed transition number mismatch. Expected "
                              + this.firstPersonPositioningAllLoadIterationsCompleted.size()
                              + ", actual: "
                              + tx.size()
                        );
                     }
                  }
               );
            return new WeaponRenderer(this);
         }
      }

      public ModelBase getModel() {
         return this.model;
      }

      public String getTextureName() {
         return this.textureName;
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
   }

   protected static class StateDescriptor {
      protected MultipartRenderStateManager stateManager;
      protected float rate;
      protected float amplitude;
      protected float verticalBias;
      private final ItemWeaponInstance instance;

      public StateDescriptor(ItemWeaponInstance instance, MultipartRenderStateManager stateManager, float rate, float amplitude, float verticalBias) {
         this.instance = instance;
         this.stateManager = stateManager;
         this.rate = rate;
         this.amplitude = amplitude;
         this.verticalBias = verticalBias;
      }
   }

   private class WeaponPositionProvider implements MultipartTransitionProvider {
      private WeaponPositionProvider() {
      }

      @Override
      public List<MultipartTransition> getPositioning(RenderableState state) {
         if (state == RenderableState.MODIFYING) {
            return WeaponRenderer.this.getSimpleTransition(
               WeaponRenderer.this.firstPersonPositioningModifying,
               WeaponRenderer.this.firstPersonLeftHandPositioningModifying,
               WeaponRenderer.this.firstPersonRightHandPositioningModifying,
               WeaponRenderer.this.firstPersonCustomPositioning,
               250
            );
         } else if (state == RenderableState.RUNNING) {
            return WeaponRenderer.this.getSimpleTransition(
               WeaponRenderer.this.firstPersonPositioningRunning,
               WeaponRenderer.this.firstPersonLeftHandPositioningRunning,
               WeaponRenderer.this.firstPersonRightHandPositioningRunning,
               WeaponRenderer.this.firstPersonCustomPositioning,
               250
            );
         } else if (state == RenderableState.UNLOADING) {
            return WeaponRenderer.this.getComplexTransition(
               WeaponRenderer.this.firstPersonPositioningUnloading,
               WeaponRenderer.this.firstPersonLeftHandPositioningUnloading,
               WeaponRenderer.this.firstPersonRightHandPositioningUnloading,
               WeaponRenderer.this.firstPersonCustomPositioningUnloading
            );
         } else if (state == RenderableState.RELOADING) {
            return WeaponRenderer.this.getComplexTransition(
               WeaponRenderer.this.firstPersonPositioningReloading,
               WeaponRenderer.this.firstPersonLeftHandPositioningReloading,
               WeaponRenderer.this.firstPersonRightHandPositioningReloading,
               WeaponRenderer.this.firstPersonCustomPositioningReloading
            );
         } else if (state == RenderableState.LOAD_ITERATION) {
            return WeaponRenderer.this.getComplexTransition(
               WeaponRenderer.this.firstPersonPositioningLoadIteration,
               WeaponRenderer.this.firstPersonLeftHandPositioningLoadIteration,
               WeaponRenderer.this.firstPersonRightHandPositioningLoadIteration,
               WeaponRenderer.this.firstPersonCustomPositioningLoadIteration
            );
         } else if (state == RenderableState.ALL_LOAD_ITERATIONS_COMPLETED) {
            return WeaponRenderer.this.getComplexTransition(
               WeaponRenderer.this.firstPersonPositioningAllLoadIterationsCompleted,
               WeaponRenderer.this.firstPersonLeftHandPositioningAllLoadIterationsCompleted,
               WeaponRenderer.this.firstPersonRightHandPositioningAllLoadIterationsCompleted,
               WeaponRenderer.this.firstPersonCustomPositioningLoadIterationsCompleted
            );
         } else if (state == RenderableState.LOAD_ITERATION_COMPLETED) {
            return WeaponRenderer.this.getSimpleTransition(
               WeaponRenderer.this.firstPersonPositioningLoadIterationCompleted,
               WeaponRenderer.this.firstPersonLeftHandPositioningLoadIterationCompleted,
               WeaponRenderer.this.firstPersonRightHandPositioningLoadIterationCompleted,
               WeaponRenderer.this.firstPersonCustomPositioningLoadIterationCompleted,
               WeaponRenderer.this.loadIterationCompletedAnimationDuration
            );
         } else if (state == RenderableState.RECOILED) {
            return WeaponRenderer.this.getSimpleTransition(
               WeaponRenderer.this.firstPersonPositioningRecoiled,
               WeaponRenderer.this.firstPersonLeftHandPositioningRecoiled,
               WeaponRenderer.this.firstPersonRightHandPositioningRecoiled,
               WeaponRenderer.this.firstPersonCustomPositioningRecoiled,
               WeaponRenderer.this.recoilAnimationDuration
            );
         } else if (state == RenderableState.SHOOTING) {
            return WeaponRenderer.this.getSimpleTransition(
               WeaponRenderer.this.firstPersonPositioningShooting,
               WeaponRenderer.this.firstPersonLeftHandPositioningShooting,
               WeaponRenderer.this.firstPersonRightHandPositioningShooting,
               WeaponRenderer.this.firstPersonCustomPositioning,
               WeaponRenderer.this.shootingAnimationDuration
            );
         } else if (state == RenderableState.EJECT_SPENT_ROUND) {
            return WeaponRenderer.this.getComplexTransition(
               WeaponRenderer.this.firstPersonPositioningEjectSpentRound,
               WeaponRenderer.this.firstPersonLeftHandPositioningEjectSpentRound,
               WeaponRenderer.this.firstPersonRightHandPositioningEjectSpentRound,
               WeaponRenderer.this.firstPersonCustomPositioningEjectSpentRound
            );
         } else if (state == RenderableState.NORMAL) {
            return WeaponRenderer.this.getSimpleTransition(
               WeaponRenderer.this.firstPersonPositioning,
               WeaponRenderer.this.firstPersonLeftHandPositioning,
               WeaponRenderer.this.firstPersonRightHandPositioning,
               WeaponRenderer.this.firstPersonCustomPositioning,
               250
            );
         } else if (state == RenderableState.ZOOMING) {
            return WeaponRenderer.this.getSimpleTransition(
               WeaponRenderer.this.firstPersonPositioningZooming,
               WeaponRenderer.this.firstPersonLeftHandPositioningZooming,
               WeaponRenderer.this.firstPersonRightHandPositioningZooming,
               WeaponRenderer.this.firstPersonCustomPositioning,
               250
            );
         } else if (state == RenderableState.ZOOMING_SHOOTING) {
            return WeaponRenderer.this.getSimpleTransition(
               WeaponRenderer.this.firstPersonPositioningZoomingShooting,
               WeaponRenderer.this.firstPersonLeftHandPositioningZooming,
               WeaponRenderer.this.firstPersonRightHandPositioningZooming,
               WeaponRenderer.this.firstPersonCustomPositioningZoomingShooting,
               60
            );
         } else {
            return state == RenderableState.ZOOMING_RECOILED
               ? WeaponRenderer.this.getSimpleTransition(
                  WeaponRenderer.this.firstPersonPositioningZoomingRecoiled,
                  WeaponRenderer.this.firstPersonLeftHandPositioningZooming,
                  WeaponRenderer.this.firstPersonRightHandPositioningZooming,
                  WeaponRenderer.this.firstPersonCustomPositioningZoomingRecoiled,
                  60
               )
               : null;
         }
      }
   }
}
