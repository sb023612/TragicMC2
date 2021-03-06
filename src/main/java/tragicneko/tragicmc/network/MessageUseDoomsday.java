package tragicneko.tragicmc.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import tragicneko.tragicmc.properties.PropertyAmulets;
import tragicneko.tragicmc.properties.PropertyDoom;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;

public class MessageUseDoomsday implements IMessage {
	
	public ItemStack stack;
	
	public MessageUseDoomsday() {}

	public MessageUseDoomsday(ItemStack stack)
	{
		this.stack = stack;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		this.stack = ByteBufUtils.readItemStack(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeItemStack(buf, this.stack);
	}
}
