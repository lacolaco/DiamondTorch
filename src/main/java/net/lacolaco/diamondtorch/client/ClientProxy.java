package net.lacolaco.diamondtorch.client;


import net.lacolaco.diamondtorch.CommonProxy;
import net.lacolaco.diamondtorch.DiamondTorchMod;
import net.lacolaco.diamondtorch.KeyBindings;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {

    @Override
    public void registerKeyBindings() {
        KeyBindings.init();
    }

    @Override
    public void registerTextures() {
        ModelLoader.setCustomModelResourceLocation(DiamondTorchMod.instance.brokenDiamondTorch, 0,
                new ModelResourceLocation(DiamondTorchMod.MODID + ":" + "item0", "inventory"));

        for (int i = 0; i < 5; i++) {
            String rawName = String.format("item%1$s", i + 1);
            ModelLoader.setCustomModelResourceLocation(DiamondTorchMod.instance.diamondTorches[i], 0,
                    new ModelResourceLocation(DiamondTorchMod.MODID + ":" + rawName, "inventory"));
        }
    }
}