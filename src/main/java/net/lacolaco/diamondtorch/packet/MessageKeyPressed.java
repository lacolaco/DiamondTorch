package net.lacolaco.diamondtorch.packet;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class MessageKeyPressed implements IMessage {

    public int keyCode;

    public MessageKeyPressed() {
    }

    public MessageKeyPressed(int keyCode) {
        this.keyCode = keyCode;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.keyCode = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.keyCode);
    }
}
