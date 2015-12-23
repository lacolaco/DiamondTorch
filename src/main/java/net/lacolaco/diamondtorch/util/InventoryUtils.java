package net.lacolaco.diamondtorch.util;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;

public class InventoryUtils {
    public static int getItemCountInInventory(ItemStack target, InventoryPlayer inventory) {
        int count = 0;
        if (target != null && inventory != null) {
            for (int i = 0; i < inventory.getSizeInventory(); i++) {
                ItemStack stack = inventory.getStackInSlot(i);
                if (stack != null && stack.isItemEqual(target)) {
                    count += stack.stackSize;
                }
            }
        }
        return count;
    }
}
