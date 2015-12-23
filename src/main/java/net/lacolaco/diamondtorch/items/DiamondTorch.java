package net.lacolaco.diamondtorch.items;

import net.lacolaco.diamondtorch.DiamondTorchMod;
import net.lacolaco.diamondtorch.util.ChatUtils;
import net.lacolaco.diamondtorch.util.InventoryUtils;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class DiamondTorch extends Item {
    private static final int[] capacityList = new int[]{256, 1024, 4096, 16384, 0};

    public int grade;

    public DiamondTorch(int grade) {
        this.grade = grade;
        setMaxStackSize(1);
        setMaxDamage(capacityList[grade]);
        setCreativeTab(CreativeTabs.tabMisc);
        setNoRepair();
        setFull3D();
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (this.isInfinite()) {
            return this.placeTorch(stack, player, world, pos, side, hitX, hitY, hitZ);
        } else if (this.placeTorch(stack, player, world, pos, side, hitX, hitY, hitZ)) {
            int currentDamage = stack.getItemDamage();
            currentDamage++;
            if (currentDamage >= stack.getMaxDamage()) {
                world.playSoundToNearExcept(player, "random.break", 0.5F, 1.2F / (new Random().nextFloat() * 0.4F + 1.2F));
                player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
                player.inventory.addItemStackToInventory(new ItemStack(DiamondTorchMod.instance.brokenDiamondTorch));
                ChatUtils.AddChatForClient(player, "Diamond Torch has broken");
            }

            stack.setItemDamage(currentDamage);
            if (stack.getMaxDamage() - currentDamage <= 5 && stack.getMaxDamage() - currentDamage > 0) {
                ChatUtils.AddChatForClient(player, "Warning! : Diamond Torch will get broken soon");
            }
            return true;
        } else {
            return false;
        }
    }

    private boolean isInfinite() {
        return this.grade == 4;
    }

    private boolean placeTorch(ItemStack itemstack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ) {
        return Item.getItemFromBlock(Blocks.torch).onItemUse(itemstack.copy(), player, world, pos, side, hitX, hitY, hitZ);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List tooltip, boolean advanced) {
        if (this.isInfinite()) {
            tooltip.add(" Can place torch Infinitly");
        } else {
            tooltip.add(" Can place torch " + (stack.getMaxDamage() - stack.getItemDamage()) + " times");
        }
    }

    public void collectTorches(ItemStack itemstack, World world, EntityPlayer player) {
        Item torchItem = Item.getItemFromBlock(Blocks.torch);
        if (!world.isRemote) {
            InventoryPlayer inventory = player.inventory;
            if (itemstack.getItem() instanceof DiamondTorch) {
                int countInInventory = InventoryUtils.getItemCountInInventory(new ItemStack(torchItem), inventory);

                for (int i = 0; i < countInInventory; i++) {
                    // filled
                    if (itemstack.getItemDamage() == 0) {
                        if (!this.isInfinite()) {
                            return;
                        }

                        inventory.consumeInventoryItem(torchItem);
                    } else {
                        // charge
                        itemstack.setItemDamage(itemstack.getItemDamage() - 1);
                        inventory.consumeInventoryItem(torchItem);
                    }
                }
            }
            ChatUtils.AddChatForClient(player, "DiamondTorch: Finished Torch Collect");
        }
    }
}
