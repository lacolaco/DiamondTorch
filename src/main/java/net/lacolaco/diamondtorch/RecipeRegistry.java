package net.lacolaco.diamondtorch;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RecipeRegistry {
    private static RecipeRegistry _instance;

    private RecipeRegistry() {
    }

    public static RecipeRegistry instance() {
        if (_instance == null) {
            _instance = new RecipeRegistry();
        }

        return _instance;
    }

    public void addRecipe() {
        this.addMakeRecipes();
        this.addUpgradeRecipes();
        this.addRepairRecipes();
    }

    private void addMakeRecipes() {
        GameRegistry.addShapelessRecipe(new ItemStack(DiamondTorchMod.instance.diamondTorches[0], 1),
                Blocks.torch, Items.diamond, Items.glowstone_dust);
        // Repair
        GameRegistry.addShapelessRecipe(new ItemStack(DiamondTorchMod.instance.diamondTorches[0], 1),
                DiamondTorchMod.instance.brokenDiamondTorch, Items.glowstone_dust);
    }

    private void addUpgradeRecipes() {
        for (int i = 0; i < 4; ++i) {
            GameRegistry.addShapelessRecipe(new ItemStack(DiamondTorchMod.instance.diamondTorches[i + 1], 1),
                    DiamondTorchMod.instance.diamondTorches[i], DiamondTorchMod.instance.diamondTorches[i],
                    DiamondTorchMod.instance.diamondTorches[i], DiamondTorchMod.instance.diamondTorches[i]);
        }

    }

    private void addRepairRecipes() {
        Item[] material = new Item[]{
                Items.glowstone_dust,
                Items.blaze_rod,
                Items.ender_pearl,
                Items.ghast_tear
        };

        for (int i = 0; i < 4; ++i) {
            GameRegistry.addShapelessRecipe(new ItemStack(DiamondTorchMod.instance.diamondTorches[i], 1),
                    new ItemStack(DiamondTorchMod.instance.diamondTorches[i], 1, -1), material[i]);
        }

    }
}
