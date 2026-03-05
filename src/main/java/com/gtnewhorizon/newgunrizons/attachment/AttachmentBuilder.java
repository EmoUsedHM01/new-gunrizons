package com.gtnewhorizon.newgunrizons.attachment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

import net.minecraft.client.model.ModelBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gtnewhorizon.newgunrizons.client.render.CustomRenderer;
import com.gtnewhorizon.newgunrizons.client.render.RenderContext;
import com.gtnewhorizon.newgunrizons.client.render.StaticModelRenderer;
import com.gtnewhorizon.newgunrizons.config.ModContext;
import com.gtnewhorizon.newgunrizons.crafting.CraftingComplexity;
import com.gtnewhorizon.newgunrizons.crafting.OptionsMetadata;
import com.gtnewhorizon.newgunrizons.items.ItemAttachment;
import com.gtnewhorizon.newgunrizons.util.Pair;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import lombok.Getter;

/**
 * Builder for constructing {@link ItemAttachment} instances with their associated models,
 * renderers, crafting recipes, and positioning callbacks.
 * <p>
 * Subclassed by {@code ItemScope.Builder}, {@code ItemMagazine.Builder}, and
 * {@code ItemBullet.Builder} to create specialized attachment types.
 */
public class AttachmentBuilder {

    private static final Logger logger = LogManager.getLogger(AttachmentBuilder.class);

    // ==================== Identity ====================

    private String name;
    @Getter
    private String modId;

    // ==================== Visual appearance ====================

    /** Primary model for this attachment. */
    @Getter
    private ModelBase model;

    /** Texture for the primary model. */
    @Getter
    private String textureName;

    /** Additional model+texture pairs rendered alongside the primary model. */
    private final List<Pair<ModelBase, String>> texturedModels = new ArrayList<>();

    /** Optional HUD crosshair texture name. */
    private String crosshair;

    /** Optional renderer executed after the main attachment render pass. */
    private CustomRenderer postRenderer;

    /** When true, this attachment gets its own {@link NamedPart} render slot. */
    private boolean isRenderablePart;

    // ==================== Item registration ====================

    private CreativeTabs tab;
    private AttachmentCategory category;
    private int maxStackSize = 1;
    private Function<ItemStack, String> informationProvider;

    // ==================== Crafting ====================

    private CraftingComplexity craftingComplexity;
    private Object[] craftingMaterials;
    private int craftingCount = 1;
    /** Explicit shaped recipe; takes priority over auto-generated recipe. */
    private Object[] craftingRecipe;

    // ==================== Equip/unequip handlers ====================

    /**
     * Called when this attachment is equipped onto a weapon.
     * Accessed directly by subclass builders (e.g. ItemScope.Builder).
     */
    protected ItemAttachment.AttachmentHandler applyHandler;

    /**
     * Called when this attachment is removed from a weapon.
     * Accessed directly by subclass builders (e.g. ItemScope.Builder).
     */
    protected ItemAttachment.AttachmentHandler removeHandler;

    // ==================== Compatible sub-attachments ====================

    private final Map<ItemAttachment, CompatibleAttachment> compatibleAttachments = new HashMap<>();

    // ==================== Client-side render positioning ====================
    // These callbacks define GL transforms for rendering this attachment in different contexts.
    // They are passed through to StaticModelRenderer during build().

    /** Item-level: dropped entity on ground. */
    private Consumer<ItemStack> entityPositioning;
    /** Item-level: inventory slot. */
    private Consumer<ItemStack> inventoryPositioning;
    /** Item-level: third-person equipped view. */
    private BiConsumer<EntityPlayer, ItemStack> thirdPersonPositioning;
    /** Item-level: first-person equipped view. */
    private BiConsumer<EntityPlayer, ItemStack> firstPersonPositioning;

    /** Model-level: first-person per-model transforms. */
    private BiConsumer<ModelBase, ItemStack> firstPersonModelPositioning;
    /** Model-level: third-person per-model transforms. */
    private BiConsumer<ModelBase, ItemStack> thirdPersonModelPositioning;
    /** Model-level: inventory per-model transforms. */
    private BiConsumer<ModelBase, ItemStack> inventoryModelPositioning;
    /** Model-level: dropped entity per-model transforms. */
    private BiConsumer<ModelBase, ItemStack> entityModelPositioning;

    /** Hand positioning: left arm in first-person view. */
    private Consumer<RenderContext> firstPersonLeftHandPositioning;
    /** Hand positioning: right arm in first-person view. */
    private Consumer<RenderContext> firstPersonRightHandPositioning;

    // ==================== Builder methods: Identity ====================

    public AttachmentBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public AttachmentBuilder withModId(String modId) {
        this.modId = modId;
        return this;
    }

    public AttachmentBuilder withCategory(AttachmentCategory category) {
        this.category = category;
        return this;
    }

    public AttachmentBuilder withCreativeTab(CreativeTabs tab) {
        this.tab = tab;
        return this;
    }

    // ==================== Builder methods: Appearance ====================

    public AttachmentBuilder withModel(ModelBase model) {
        this.model = model;
        return this;
    }

    public AttachmentBuilder withModel(ModelBase model, String textureName) {
        this.texturedModels.add(new Pair<>(model, textureName.toLowerCase()));
        return this;
    }

    public AttachmentBuilder withTextureName(String textureName) {
        this.textureName = textureName.toLowerCase();
        return this;
    }

    public AttachmentBuilder withCrosshair(String crosshair) {
        this.crosshair = crosshair.toLowerCase();
        return this;
    }

    public AttachmentBuilder withPostRender(CustomRenderer postRenderer) {
        this.postRenderer = postRenderer;
        return this;
    }

    public AttachmentBuilder withRenderablePart() {
        this.isRenderablePart = true;
        return this;
    }

    // ==================== Builder methods: Item properties ====================

    public AttachmentBuilder withMaxStackSize(int maxStackSize) {
        this.maxStackSize = maxStackSize;
        return this;
    }

    public AttachmentBuilder withInformationProvider(Function<ItemStack, String> informationProvider) {
        this.informationProvider = informationProvider;
        return this;
    }

    // ==================== Builder methods: Equip/unequip handlers ====================

    public AttachmentBuilder withApply(ItemAttachment.AttachmentHandler applyHandler) {
        this.applyHandler = applyHandler;
        return this;
    }

    public AttachmentBuilder withRemove(ItemAttachment.AttachmentHandler removeHandler) {
        this.removeHandler = removeHandler;
        return this;
    }

    // ==================== Builder methods: Crafting ====================

    public AttachmentBuilder withCrafting(CraftingComplexity craftingComplexity, Object... craftingMaterials) {
        return this.withCrafting(1, craftingComplexity, craftingMaterials);
    }

    public AttachmentBuilder withCrafting(int craftingCount, CraftingComplexity craftingComplexity,
        Object... craftingMaterials) {
        if (craftingComplexity == null) {
            throw new IllegalArgumentException("Crafting complexity not set");
        }
        if (craftingMaterials.length < 2) {
            throw new IllegalArgumentException("2 or more materials required for crafting");
        }
        if (craftingCount == 0) {
            throw new IllegalArgumentException("Invalid item count");
        }
        this.craftingComplexity = craftingComplexity;
        this.craftingMaterials = craftingMaterials;
        this.craftingCount = craftingCount;
        return this;
    }

    public AttachmentBuilder withCraftingRecipe(Object... craftingRecipe) {
        this.craftingRecipe = craftingRecipe;
        return this;
    }

    // ==================== Builder methods: Compatible attachments ====================

    public AttachmentBuilder withCompatibleAttachment(ItemAttachment attachment, Consumer<ModelBase> positioner) {
        this.compatibleAttachments.put(attachment, new CompatibleAttachment(attachment, positioner));
        return this;
    }

    // ==================== Builder methods: Client-side positioning ====================

    public AttachmentBuilder withEntityPositioning(Consumer<ItemStack> entityPositioning) {
        this.entityPositioning = entityPositioning;
        return this;
    }

    public AttachmentBuilder withInventoryPositioning(Consumer<ItemStack> inventoryPositioning) {
        this.inventoryPositioning = inventoryPositioning;
        return this;
    }

    public AttachmentBuilder withThirdPersonPositioning(BiConsumer<EntityPlayer, ItemStack> thirdPersonPositioning) {
        this.thirdPersonPositioning = thirdPersonPositioning;
        return this;
    }

    public AttachmentBuilder withFirstPersonPositioning(BiConsumer<EntityPlayer, ItemStack> firstPersonPositioning) {
        this.firstPersonPositioning = firstPersonPositioning;
        return this;
    }

    public AttachmentBuilder withFirstPersonModelPositioning(
        BiConsumer<ModelBase, ItemStack> firstPersonModelPositioning) {
        this.firstPersonModelPositioning = firstPersonModelPositioning;
        return this;
    }

    public AttachmentBuilder withThirdPersonModelPositioning(
        BiConsumer<ModelBase, ItemStack> thirdPersonModelPositioning) {
        this.thirdPersonModelPositioning = thirdPersonModelPositioning;
        return this;
    }

    public AttachmentBuilder withInventoryModelPositioning(BiConsumer<ModelBase, ItemStack> inventoryModelPositioning) {
        this.inventoryModelPositioning = inventoryModelPositioning;
        return this;
    }

    public AttachmentBuilder withEntityModelPositioning(BiConsumer<ModelBase, ItemStack> entityModelPositioning) {
        this.entityModelPositioning = entityModelPositioning;
        return this;
    }

    public AttachmentBuilder withFirstPersonHandPositioning(Consumer<RenderContext> leftHand,
        Consumer<RenderContext> rightHand) {
        this.firstPersonLeftHandPositioning = leftHand;
        this.firstPersonRightHandPositioning = rightHand;
        return this;
    }

    // ==================== Build ====================

    /**
     * Creates and fully configures the {@link ItemAttachment}, including model registration,
     * renderer setup, and crafting recipe registration.
     */
    public ItemAttachment build(ModContext modContext) {
        ItemAttachment attachment = createAttachment(modContext);

        configureAttachment(attachment);
        registerModels(attachment);
        registerCompatibleAttachments(attachment);
        registerRenderer(attachment, modContext);
        registerRecipe(attachment, modContext);

        return attachment;
    }

    /** Convenience overload that casts the result to a specific attachment subtype. */
    public <V extends ItemAttachment> V build(ModContext modContext, Class<V> target) {
        return target.cast(this.build(modContext));
    }

    /**
     * Factory method for creating the attachment item. Overridden by subclass builders
     * (ItemScope.Builder, ItemMagazine.Builder, ItemBullet.Builder) to create their
     * specific subtypes.
     */
    protected ItemAttachment createAttachment(ModContext modContext) {
        return new ItemAttachment(this.modId, this.category, this.crosshair);
    }

    // ==================== Build internals ====================

    private void configureAttachment(ItemAttachment attachment) {
        attachment.setUnlocalizedName(this.modId + "_" + this.name);
        attachment.setCreativeTab(this.tab);
        attachment.setMaxStackSize(this.maxStackSize);
        attachment.setName(this.name);
        attachment.setPostRenderer(this.postRenderer);
        attachment.setApplyHandler(this.applyHandler);
        attachment.setRemoveHandler(this.removeHandler);

        if (attachment.getInformationProvider() == null) {
            attachment.setInformationProvider(this.informationProvider);
        }

        if (this.isRenderablePart) {
            attachment.setRenderablePart(new NamedPart(this.name != null ? this.name : "unknown"));
        }
    }

    private void registerModels(ItemAttachment attachment) {
        if (this.model != null) {
            attachment.addModel(this.model, ensurePngExtension(this.textureName));
        }
        for (Pair<ModelBase, String> tm : this.texturedModels) {
            attachment.addModel(tm.getU(), ensurePngExtension(tm.getV()));
        }
    }

    private void registerCompatibleAttachments(ItemAttachment attachment) {
        for (CompatibleAttachment ca : this.compatibleAttachments.values()) {
            attachment.addCompatibleAttachment(ca);
        }
    }

    private void registerRenderer(ItemAttachment attachment, ModContext modContext) {
        boolean hasModels = this.model != null || !this.texturedModels.isEmpty();
        if (!hasModels) {
            return;
        }

        Object renderer = null;
        if (FMLCommonHandler.instance()
            .getSide() == Side.CLIENT) {
            renderer = new StaticModelRenderer.Builder().withEntityPositioning(this.entityPositioning)
                .withFirstPersonPositioning(this.firstPersonPositioning)
                .withThirdPersonPositioning(this.thirdPersonPositioning)
                .withInventoryPositioning(this.inventoryPositioning)
                .withEntityModelPositioning(this.entityModelPositioning)
                .withFirstPersonModelPositioning(this.firstPersonModelPositioning)
                .withThirdPersonModelPositioning(this.thirdPersonModelPositioning)
                .withInventoryModelPositioning(this.inventoryModelPositioning)
                .withFirstPersonHandPositioning(
                    this.firstPersonLeftHandPositioning,
                    this.firstPersonRightHandPositioning)
                .withModContext(modContext)
                .withModId(this.modId)
                .build();
        }

        modContext.registerRenderableItem(this.name, attachment, renderer);
    }

    private void registerRecipe(ItemAttachment attachment, ModContext modContext) {
        if (this.craftingRecipe != null && this.craftingRecipe.length >= 2) {
            modContext.getRecipeManager()
                .registerShapedRecipe(attachment, this.craftingRecipe);
            return;
        }

        if (this.craftingComplexity != null) {
            registerAutoGeneratedRecipe(attachment, modContext);
            return;
        }

        if (isPlayerFacingCategory(attachment.getCategory())) {
            logger.warn("No recipe defined for attachment '{}'", this.name);
        }
    }

    private void registerAutoGeneratedRecipe(ItemAttachment attachment, ModContext modContext) {
        OptionsMetadata optionsMetadata = new OptionsMetadata.OptionMetadataBuilder().withSlotCount(9)
            .build(this.craftingComplexity, Arrays.copyOf(this.craftingMaterials, this.craftingMaterials.length));

        List<Object> shape = modContext.getRecipeManager()
            .createShapedRecipe(attachment, this.name, optionsMetadata);

        ItemStack result = new ItemStack(attachment);
        result.stackSize = this.craftingCount;

        if (optionsMetadata.hasOres()) {
            GameRegistry.addRecipe(new ShapedOreRecipe(result, shape.toArray()).setMirrored(false));
        } else {
            GameRegistry.addShapedRecipe(result, shape.toArray());
        }
    }

    // ==================== Utilities ====================

    /**
     * Returns true for attachment categories that the player directly interacts with
     * and therefore should have a crafting recipe.
     */
    private static boolean isPlayerFacingCategory(AttachmentCategory category) {
        return category == AttachmentCategory.GRIP || category == AttachmentCategory.SCOPE
            || category == AttachmentCategory.MAGAZINE
            || category == AttachmentCategory.BULLET
            || category == AttachmentCategory.SILENCER;
    }

    static String ensurePngExtension(String filename) {
        if (filename == null || filename.endsWith(".png")) {
            return filename;
        }
        return filename + ".png";
    }

    protected static String stripFileExtension(String filename) {
        if (filename.endsWith(".png")) {
            return filename.substring(0, filename.length() - ".png".length());
        }
        return filename;
    }
}
