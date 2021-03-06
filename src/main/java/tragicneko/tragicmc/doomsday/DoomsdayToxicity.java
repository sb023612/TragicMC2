package tragicneko.tragicmc.doomsday;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import tragicneko.tragicmc.entity.projectile.EntityPoisonBarb;
import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayToxicity extends Doomsday {
	
	private List<Entity> list = new ArrayList();

	public DoomsdayToxicity(int id, int cd, int reqDoom) {
		super(id, cd, reqDoom);
	}
	
	@Override
	public void doInitialEffects(PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
		double d = 12.0;

		if (crucMoment)
		{
			d = 16.0;
		}

		list = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(d, d, d));
		
		if (list.size() > 0)
		{
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "You have used Toxicity!"));

			if (crucMoment)
			{
				player.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Crucial Moment!"));
			}
		}
		else
		{
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.ITALIC + "No entities in range..."));
		}
	}

	@Override
	public void useDoomsday(PropertyDoom doom, EntityPlayer player, boolean crucMoment)
	{
		for (int i = 0; i < list.size(); i++)
		{
			if (list.get(i) instanceof EntityLivingBase)
			{
				EntityLivingBase entity = (EntityLivingBase) list.get(i);

				for (int j = 0; j < 4; j ++)
				{
					double d0 = entity.posX - player.posX + rand.nextDouble() - rand.nextDouble(); 
					double d1 = entity.posY - player.posY;
					double d2 = entity.posZ - player.posZ + rand.nextDouble() - rand.nextDouble();

					EntityPoisonBarb fireball = new EntityPoisonBarb(player.worldObj, player, d0, d1, d2);
					fireball.setPosition(player.posX + (d0 * 0.115), player.posY + 0.6D, player.posZ + (d2 * 0.115));
					player.worldObj.spawnEntityInWorld(fireball);
				}
			}
		}		
	}

	@Override
	public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {
		player.addPotionEffect(new PotionEffect(Potion.poison.id, 120, 0));
	}

}
