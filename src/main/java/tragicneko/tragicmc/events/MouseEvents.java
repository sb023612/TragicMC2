package tragicneko.tragicmc.events;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.client.event.MouseEvent;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.blocks.BlockGenericLeaves;
import tragicneko.tragicmc.entity.boss.EntityPart;
import tragicneko.tragicmc.entity.boss.IMultiPart;
import tragicneko.tragicmc.items.weapons.EpicWeapon;
import tragicneko.tragicmc.main.TragicEnchantments;
import tragicneko.tragicmc.network.MessageAttack;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class MouseEvents {

	public final Minecraft mc;

	public MouseEvents(Minecraft mc)
	{
		this.mc = mc;
	}

	@SubscribeEvent
	public void onMouseInput(MouseEvent event)
	{
		BlockGenericLeaves.fancyGraphics = Minecraft.getMinecraft().isFancyGraphicsEnabled();

		if (event.buttonstate && event.button == 0)
		{
			if (mc.thePlayer == null) return;

			ItemStack stack = mc.thePlayer.getCurrentEquippedItem();
			World world = mc.theWorld;
			EntityClientPlayerMP player = mc.thePlayer;

			float f = 1.0F;
			float f1 = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * f;
			float f2 = player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * f;
			double d0 = player.prevPosX + (player.posX - player.prevPosX) * (double)f;
			double d1 = player.prevPosY + (player.posY - player.prevPosY) * (double)f + (double)(player.worldObj.isRemote ? player.getEyeHeight() - player.getDefaultEyeHeight() : player.getEyeHeight()); // isRemote check to revert changes to ray trace position due to adding the eye height clientside and player yOffset differences
			double d2 = player.prevPosZ + (player.posZ - player.prevPosZ) * (double)f;
			Vec3 vec3 = Vec3.createVectorHelper(d0, d1, d2);
			float f3 = MathHelper.cos(-f2 * 0.017453292F - (float)Math.PI);
			float f4 = MathHelper.sin(-f2 * 0.017453292F - (float)Math.PI);
			float f5 = -MathHelper.cos(-f1 * 0.017453292F);
			float f6 = MathHelper.sin(-f1 * 0.017453292F);
			float f7 = f4 * f5;
			float f8 = f3 * f5;
			boolean flag = false;
			double limit = player.capabilities.isCreativeMode ? 2.5D : 1.5D;
			double enchantLimit = limit + EnchantmentHelper.getEnchantmentLevel(TragicEnchantments.Reach.effectId, stack) * 1.5D;

			for (double d = 0.0D; d <= enchantLimit; d += 0.5D)
			{
				Vec3 vec31 = vec3.addVector((double)f7 * d, (double)f6 * d, (double)f8 * d);
				AxisAlignedBB bb = AxisAlignedBB.getBoundingBox(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D).offset(vec31.xCoord, vec31.yCoord, vec31.zCoord).expand(1.35D, 1.35D, 1.35D);
				List<Entity> list = world.getEntitiesWithinAABBExcludingEntity(player, bb);
				Entity entity;

				for (int i = 0; i < list.size(); i++)
				{
					entity = list.get(i);
					if (d <= limit) 
					{
						if (entity instanceof EntityPart || entity instanceof IMultiPart) TragicMC.net.sendToServer(new MessageAttack(entity));
						flag = true;
						break;
					}

					if (!(entity instanceof EntityItem) && !(entity instanceof EntityXPOrb) && !(entity instanceof EntityArrow) && entity != player)
					{
						TragicMC.net.sendToServer(new MessageAttack(entity));
						flag = true;
						break;
					}
				} 

				if (flag) break;
			}

		}
	}
}
