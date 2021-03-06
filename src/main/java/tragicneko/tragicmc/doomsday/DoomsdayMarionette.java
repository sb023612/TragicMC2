package tragicneko.tragicmc.doomsday;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MovingObjectPosition;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.main.TragicPotions;
import tragicneko.tragicmc.properties.PropertyDoom;

public class DoomsdayMarionette extends Doomsday implements IExtendedDoomsday {

	private EntityLivingBase entity;

	public DoomsdayMarionette(int id, int cd, int reqDoom) {
		super(id, cd, reqDoom, EnumDoomType.OVERFLOW);
		this.waitTime = 3;
		this.maxIterations = 100;
	}

	@Override
	public void doInitialEffects(PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
		entity = null;
		List<Entity> list = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(2.0D, 2.0D, 2.0D));

		if (list.isEmpty())
		{
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.ITALIC + "No entity close enough..."));
		}
		else
		{
			for (int i = 0; i < list.size(); i++)
			{
				if (list.get(i) instanceof EntityLivingBase)
				{
					this.entity = (EntityLivingBase) list.get(i);
					break;
				}
			}
			
			if (this.entity != null)
			{
				if (TragicNewConfig.allowSubmission) entity.addPotionEffect(new PotionEffect(TragicPotions.Submission.id, 200, 10));
				
				player.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "You have used Marionette!"));

				if (crucMoment)
				{
					player.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Crucial Moment!"));
				}
			}
			else
			{
				player.addChatMessage(new ChatComponentText(EnumChatFormatting.ITALIC + "No living entities close enough..."));
			}
		}
	}

	@Override
	public void useDoomsday(PropertyDoom doom, EntityPlayer player, boolean crucMoment) {
		if (entity != null && !entity.isDead)
		{
			MovingObjectPosition mop = this.getMOPFromPlayer(player, 5.0D);

			if (mop != null)
			{
				entity.setPositionAndUpdate(mop.hitVec.xCoord, mop.hitVec.yCoord, mop.hitVec.zCoord);
				if (rand.nextInt(8) == 0) entity.attackEntityFrom(DamageSource.causePlayerDamage(player), 1.0F);
			}
		}
		else
		{
			List<Entity> list = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(2.0D, 2.0D, 2.0D));
			for (int i = 0; i < list.size(); i++)
			{
				if (list.get(i) instanceof EntityLivingBase)
				{
					this.entity = (EntityLivingBase) list.get(i);
					break;
				}
			}
		}
	}

	@Override
	public void doBacklashEffect(PropertyDoom doom, EntityPlayer player) {

	}

}
