package net.lacolaco.diamondtorch;

import net.lacolaco.diamondtorch.items.BrokenDiamondTorch;
import net.lacolaco.diamondtorch.items.DiamondTorch;
import net.lacolaco.diamondtorch.packet.PacketHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = DiamondTorchMod.MODID, name = "Diamond Torch", version = DiamondTorchMod.VERSION)
public class DiamondTorchMod {
    public static final String MODID = "diamondtorch";
    public static final String VERSION = "1.0";

    @Mod.Instance("diamondtorch")
    public static DiamondTorchMod instance;

    @SidedProxy(
            clientSide = "net.lacolaco.diamondtorch.client.ClientProxy",
            serverSide = "net.lacolaco.diamondtorch.CommonProxy"
    )
    public static CommonProxy proxy;

    public Item[] diamondTorches = new Item[5];
    public Item brokenDiamondTorch;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        itemSetup();
        RecipeRegistry.instance().addRecipe();
        FMLCommonHandler.instance().bus().register(new KeyBindings());
        PacketHandler.init();
        proxy.registerKeyBindings();
        proxy.registerTextures();
    }

    private void itemSetup() {
        for (int i = 0; i < 5; i++) {
            String rawName = String.format("item%1$s", i + 1);
            Item torch = new DiamondTorch(i).setUnlocalizedName(rawName);
            GameRegistry.registerItem(torch, rawName);
            this.diamondTorches[i] = torch;
        }

        this.brokenDiamondTorch = new BrokenDiamondTorch().setUnlocalizedName("item0");
        GameRegistry.registerItem(this.brokenDiamondTorch, "item0");
    }

    public void onKeyPressed(int keyCode, EntityPlayer player) {
        if (keyCode == KeyBindings.torchCollect.getKeyCode()) {
            Item currentItem = player.getCurrentEquippedItem().getItem();
            if (currentItem instanceof DiamondTorch) {
                DiamondTorch item = (DiamondTorch) currentItem;

                item.collectTorches(player.getCurrentEquippedItem(), player.worldObj, player);
            }
        }
    }
}
