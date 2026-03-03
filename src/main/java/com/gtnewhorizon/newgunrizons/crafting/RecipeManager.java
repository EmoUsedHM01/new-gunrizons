package com.gtnewhorizon.newgunrizons.crafting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.registry.GameRegistry;

public class RecipeManager {

    private static final Logger logger = LogManager.getLogger(RecipeManager.class);
    private RecipeGenerator recipeGenerator = new RecipeGenerator();
    private Map<Item, List<Object>> recipes = new HashMap<>();

    public List<Object> createShapedRecipe(Item item, String name, OptionsMetadata optionsMetadata) {
        List<Object> recipe = this.recipeGenerator.createShapedRecipe(name, optionsMetadata);
        if (this.recipes.put(item, recipe) != null) {
            logger.warn("Duplicate recipe registered for item {}", new Object[] { item });
        }

        return recipe;
    }

    public List<Object> registerShapedRecipe(Item item, Object... recipe) {
        return this.registerShapedRecipe(new ItemStack(item), recipe);
    }

    public List<Object> registerShapedRecipe(ItemStack itemStack, Object... recipe) {
        List<Object> recipeAslist = new ArrayList<>(recipe.length);
        boolean hasOres = false;
        for (Object option : recipe) {
            if (option instanceof String) {
                hasOres = true;
            }
            recipeAslist.add(option);
        }

        if (hasOres) {
            GameRegistry.addRecipe(new ShapedOreRecipe(itemStack, recipeAslist.toArray()).setMirrored(false));
        } else {
            GameRegistry.addShapedRecipe(itemStack, recipeAslist.toArray());
        }

        if (this.recipes.put(itemStack.getItem(), recipeAslist) != null) {
            logger.warn("Duplicate recipe registered for item {}", new Object[] { itemStack.getItem() });
        }

        return recipeAslist;
    }

    public List<Object> getRecipe(Item item) {
        return this.recipes.get(item);
    }
}
