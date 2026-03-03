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

import com.gtnewhorizon.newgunrizons.client.render.CustomRenderer;
import com.gtnewhorizon.newgunrizons.client.render.RenderContext;
import com.gtnewhorizon.newgunrizons.client.render.StaticModelSourceRenderer;
import com.gtnewhorizon.newgunrizons.config.ModContext;
import com.gtnewhorizon.newgunrizons.crafting.CraftingComplexity;
import com.gtnewhorizon.newgunrizons.crafting.OptionsMetadata;
import com.gtnewhorizon.newgunrizons.util.Tuple;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import lombok.Getter;

public class AttachmentBuilder {

    protected String name;
    @Getter
    protected String modId;
    @Getter
    protected ModelBase model;
    @Getter
    protected String textureName;
    protected Consumer<ItemStack> entityPositioning;
    protected Consumer<ItemStack> inventoryPositioning;
    protected BiConsumer<EntityPlayer, ItemStack> thirdPersonPositioning;
    protected BiConsumer<EntityPlayer, ItemStack> firstPersonPositioning;
    protected BiConsumer<ModelBase, ItemStack> firstPersonModelPositioning;
    protected BiConsumer<ModelBase, ItemStack> thirdPersonModelPositioning;
    protected BiConsumer<ModelBase, ItemStack> inventoryModelPositioning;
    protected BiConsumer<ModelBase, ItemStack> entityModelPositioning;
    protected Consumer<RenderContext> firstPersonLeftHandPositioning;
    protected Consumer<RenderContext> firstPersonRightHandPositioning;
    protected CreativeTabs tab;
    protected AttachmentCategory attachmentCategory;
    protected ItemAttachment.ApplyHandler apply;
    protected ItemAttachment.ApplyHandler remove;
    protected ItemAttachment.ApplyHandler2 apply2;
    protected ItemAttachment.ApplyHandler2 remove2;
    private String crosshair;
    private CustomRenderer postRenderer;
    private final List<Tuple<ModelBase, String>> texturedModels = new ArrayList<>();
    private boolean isRenderablePart;
    private int maxStackSize = 1;
    private Function<ItemStack, String> informationProvider;
    private CraftingComplexity craftingComplexity;
    private Object[] craftingMaterials;
    Map<ItemAttachment, CompatibleAttachment> compatibleAttachments = new HashMap<>();
    private int craftingCount = 1;
    private Object[] craftingRecipe;

    public AttachmentBuilder withCategory(AttachmentCategory attachmentCategory) {
        this.attachmentCategory = attachmentCategory;
        return this;
    }

    public AttachmentBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public AttachmentBuilder withCreativeTab(CreativeTabs tab) {
        this.tab = tab;
        return this;
    }

    public AttachmentBuilder withModId(String modId) {
        this.modId = modId;
        return this;
    }

    public AttachmentBuilder withCompatibleAttachment(ItemAttachment attachment, Consumer<ModelBase> positioner) {
        this.compatibleAttachments.put(attachment, new CompatibleAttachment(attachment, positioner));
        return this;
    }

    public AttachmentBuilder withModel(ModelBase model) {
        this.model = model;
        return this;
    }

    public AttachmentBuilder withTextureName(String textureName) {
        this.textureName = textureName.toLowerCase();
        return this;
    }

    public AttachmentBuilder withMaxStackSize(int maxStackSize) {
        this.maxStackSize = maxStackSize;
        return this;
    }

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

    public AttachmentBuilder withEntityModelPositioning(BiConsumer<ModelBase, ItemStack> entityModelPositioning) {
        this.entityModelPositioning = entityModelPositioning;
        return this;
    }

    public AttachmentBuilder withInventoryModelPositioning(BiConsumer<ModelBase, ItemStack> inventoryModelPositioning) {
        this.inventoryModelPositioning = inventoryModelPositioning;
        return this;
    }

    public AttachmentBuilder withThirdPersonModelPositioning(
        BiConsumer<ModelBase, ItemStack> thirdPersonModelPositioning) {
        this.thirdPersonModelPositioning = thirdPersonModelPositioning;
        return this;
    }

    public AttachmentBuilder withFirstPersonHandPositioning(Consumer<RenderContext> leftHand,
        Consumer<RenderContext> rightHand) {
        this.firstPersonLeftHandPositioning = leftHand;
        this.firstPersonRightHandPositioning = rightHand;
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

    public AttachmentBuilder withModel(ModelBase model, String textureName) {
        this.texturedModels.add(new Tuple<>(model, textureName.toLowerCase()));
        return this;
    }

    public AttachmentBuilder withRenderablePart() {
        this.isRenderablePart = true;
        return this;
    }

    public AttachmentBuilder withApply(ItemAttachment.ApplyHandler2 apply) {
        this.apply2 = apply;
        return this;
    }

    public AttachmentBuilder withRemove(ItemAttachment.ApplyHandler2 remove) {
        this.remove2 = remove;
        return this;
    }

    public AttachmentBuilder withCrafting(CraftingComplexity craftingComplexity, Object... craftingMaterials) {
        return this.withCrafting(1, craftingComplexity, craftingMaterials);
    }

    public AttachmentBuilder withInformationProvider(Function<ItemStack, String> informationProvider) {
        this.informationProvider = informationProvider;
        return this;
    }

    public AttachmentBuilder withCrafting(int craftingCount, CraftingComplexity craftingComplexity,
        Object... craftingMaterials) {
        if (craftingComplexity == null) {
            throw new IllegalArgumentException("Crafting complexity not set");
        } else if (craftingMaterials.length < 2) {
            throw new IllegalArgumentException("2 or more materials required for crafting");
        } else if (craftingCount == 0) {
            throw new IllegalArgumentException("Invalid item count");
        } else {
            this.craftingComplexity = craftingComplexity;
            this.craftingMaterials = craftingMaterials;
            this.craftingCount = craftingCount;
            return this;
        }
    }

    public AttachmentBuilder withCraftingRecipe(Object... craftingRecipe) {
        this.craftingRecipe = craftingRecipe;
        return this;
    }

    protected ItemAttachment createAttachment(ModContext modContext) {
        return new ItemAttachment(this.getModId(), this.attachmentCategory, this.crosshair, this.apply, this.remove);
    }

    public ItemAttachment build(ModContext modContext) {
        ItemAttachment attachment = this.createAttachment(modContext);
        attachment.setUnlocalizedName(this.getModId() + "_" + this.name);
        attachment.setCreativeTab(this.tab);
        attachment.setPostRenderer(this.postRenderer);
        attachment.setName(this.name);
        attachment.apply2 = this.apply2;
        attachment.remove2 = this.remove2;
        attachment.maxStackSize = this.maxStackSize;
        if (attachment.getInformationProvider() == null) {
            attachment.setInformationProvider(this.informationProvider);
        }

        if (this.getTextureName() != null) {
            attachment.setTextureName(this.getModId() + ":" + stripFileExtension(this.getTextureName()));
        }

        if (this.isRenderablePart) {
            attachment.setRenderablePart(new Part() {

                public String toString() {
                    return AttachmentBuilder.this.name != null ? "Part [" + AttachmentBuilder.this.name + "]"
                        : super.toString();
                }
            });
        }

        if (this.getModel() != null) {
            attachment.addModel(this.getModel(), addFileExtension(this.getTextureName()));
        }

        this.texturedModels.forEach((tm) -> attachment.addModel(tm.getU(), addFileExtension(tm.getV())));
        this.compatibleAttachments.values()
            .forEach(attachment::addCompatibleAttachment);
        if (this.getModel() != null || !this.texturedModels.isEmpty()) {
            modContext.registerRenderableItem(
                this.name,
                attachment,
                FMLCommonHandler.instance()
                    .getSide() == Side.CLIENT ? this.registerRenderer(modContext) : null);
        }

        if (this.craftingRecipe != null && this.craftingRecipe.length >= 2) {
            modContext.getRecipeManager()
                .registerShapedRecipe(attachment, this.craftingRecipe);
        } else if (this.craftingComplexity != null) {
            OptionsMetadata optionsMetadata = (new OptionsMetadata.OptionMetadataBuilder()).withSlotCount(9)
                .build(this.craftingComplexity, Arrays.copyOf(this.craftingMaterials, this.craftingMaterials.length));
            List<Object> shape = modContext.getRecipeManager()
                .createShapedRecipe(attachment, this.name, optionsMetadata);
            ItemStack itemStack = new ItemStack(attachment);
            itemStack.stackSize = this.craftingCount;
            if (optionsMetadata.hasOres()) {
                GameRegistry.addRecipe(new ShapedOreRecipe(itemStack, shape.toArray()).setMirrored(false));
            } else {
                GameRegistry.addShapedRecipe(itemStack, shape.toArray());
            }
        } else if (attachment.getCategory() == AttachmentCategory.GRIP
            || attachment.getCategory() == AttachmentCategory.SCOPE
            || attachment.getCategory() == AttachmentCategory.MAGAZINE
            || attachment.getCategory() == AttachmentCategory.BULLET
            || attachment.getCategory() == AttachmentCategory.SILENCER) {
                System.err.println("!!!No recipe defined for attachment " + this.name);
            }

        return attachment;
    }

    private Object registerRenderer(ModContext modContext) {
        return (new StaticModelSourceRenderer.Builder()).withEntityPositioning(this.entityPositioning)
            .withFirstPersonPositioning(this.firstPersonPositioning)
            .withThirdPersonPositioning(this.thirdPersonPositioning)
            .withInventoryPositioning(this.inventoryPositioning)
            .withEntityModelPositioning(this.entityModelPositioning)
            .withFirstPersonModelPositioning(this.firstPersonModelPositioning)
            .withThirdPersonModelPositioning(this.thirdPersonModelPositioning)
            .withInventoryModelPositioning(this.inventoryModelPositioning)
            .withFirstPersonHandPositioning(this.firstPersonLeftHandPositioning, this.firstPersonRightHandPositioning)
            .withModContext(modContext)
            .withModId(this.getModId())
            .build();
    }

    static String addFileExtension(String s) {
        return s != null && !s.endsWith(".png") ? s + ".png" : s;
    }

    protected static String stripFileExtension(String str) {
        return str.endsWith(".png") ? str.substring(0, str.length() - ".png".length()) : str;
    }

    public <V extends ItemAttachment> V build(ModContext modContext, Class<V> target) {
        return target.cast(this.build(modContext));
    }

}
