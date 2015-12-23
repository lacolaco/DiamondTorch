package net.lacolaco.diamondtorch;

import net.lacolaco.diamondtorch.packet.MessageKeyPressed;
import net.lacolaco.diamondtorch.packet.PacketHandler;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

public class KeyBindings {

    public static final KeyBinding torchCollect = new KeyBinding("key.diamondtorch.torchCollect", Keyboard.KEY_C, "key.categories.diamondtorch");

    public static void init() {
        ClientRegistry.registerKeyBinding(torchCollect);
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (torchCollect.isPressed()) {
            PacketHandler.INSTANCE.sendToServer(new MessageKeyPressed(torchCollect.getKeyCode()));
        }
    }
}