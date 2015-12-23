package net.lacolaco.diamondtorch.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;

public class ChatUtils {

    public static void AddChatForClient(EntityPlayer player, String text) {
        if (!player.worldObj.isRemote) {
            player.addChatComponentMessage(new ChatComponentText(text));
        }
    }
}
