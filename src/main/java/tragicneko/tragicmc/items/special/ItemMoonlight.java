package tragicneko.tragicmc.items.special;

import java.util.UUID;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemMoonlight extends ItemTalisman {

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int numb, boolean flag)
	{
		if (world.isRemote || !(entity instanceof EntityPlayer)) return;
		
		UUID uuidForMod = UUID.fromString("7913bbbe-8b78-4e5f-8a7e-1d429e0ef1b6");
		AttributeModifier mod = new AttributeModifier(uuidForMod, "moonlightModifier", 10.0, 0);
		EntityPlayer player = (EntityPlayer) entity;
		
		player.getEntityAttribute(SharedMonsterAttributes.maxHealth).removeModifier(mod);
		
		if (!world.isDaytime() && !world.isRaining() && !world.isThundering())
		{
			player.getEntityAttribute(SharedMonsterAttributes.maxHealth).applyModifier(mod);
			if (player.ticksExisted % 400 == 0) stack.damageItem(1, player);
		}
	}
}
