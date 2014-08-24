package tragicneko.tragicmc.items.weapons;

import java.util.List;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.doomsday.Doomsday.EnumDoomType;
import tragicneko.tragicmc.main.TragicEnchantments;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.properties.PropertyDoom;

public class WeaponCelestialLongbow extends ItemBow {

	public final Doomsday doomsday = Doomsday.Snipe;

	private int cooldown;

	public WeaponCelestialLongbow()
	{
		this.setMaxDamage(1348);
	}

	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
	{
		if (TragicNewConfig.allowDoomsdays && this.doomsday != null)
		{
			PropertyDoom doom = PropertyDoom.get(par2EntityPlayer);

			EnumChatFormatting format = EnumChatFormatting.DARK_AQUA;

			if (doomsday.getDoomsdayType() == EnumDoomType.CRISIS)
			{
				format = EnumChatFormatting.DARK_RED;
			}

			if (doomsday.getDoomsdayType() == EnumDoomType.OVERFLOW)
			{
				format = EnumChatFormatting.GREEN;
			}

			if (doomsday.getDoomsdayType() == EnumDoomType.WORLDSHAPER)
			{
				format = EnumChatFormatting.DARK_PURPLE;
			}

			if (doomsday.getDoomsdayType() == EnumDoomType.ULTIMATE)
			{
				format = EnumChatFormatting.DARK_BLUE;
			}

			par2List.add(format + doomsday.getLocalizedType() + ": " + doomsday.getLocalizedName());

			if (doom != null)
			{
				par2List.add(EnumChatFormatting.GOLD + "Doom Cost: " + doomsday.getScaledDoomRequirement(doom));
			}
		}
	}

	public void onCreated(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) 
	{
		par1ItemStack.addEnchantment(Enchantment.flame, 1);
		par1ItemStack.addEnchantment(Enchantment.punch, 2);
		par1ItemStack.addEnchantment(Enchantment.unbreaking, 10);

		if (TragicNewConfig.allowMultiply)
		{
			par1ItemStack.addEnchantment(TragicEnchantments.Multiply, 1);
		}
	}

	public int getItemEnchantability() 
	{
		return 20;
	}

	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		if (!par3EntityPlayer.isSneaking())
		{
			ArrowNockEvent event = new ArrowNockEvent(par3EntityPlayer, par1ItemStack);
			MinecraftForge.EVENT_BUS.post(event);
			if (event.isCanceled())
			{
				return event.result;
			}

			if (par3EntityPlayer.capabilities.isCreativeMode || par3EntityPlayer.inventory.hasItem(Items.arrow))
			{
				par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
			}
		}
		else
		{
			PropertyDoom doom = PropertyDoom.get(par3EntityPlayer);
			if (doom != null && doom.getCurrentDoom() >= 5 && cooldown == 0)
			{
				float f = 1.0F;
				float f1 = par3EntityPlayer.prevRotationPitch + (par3EntityPlayer.rotationPitch - par3EntityPlayer.prevRotationPitch) * f;
				float f2 = par3EntityPlayer.prevRotationYaw + (par3EntityPlayer.rotationYaw - par3EntityPlayer.prevRotationYaw) * f;
				double d0 = par3EntityPlayer.prevPosX + (par3EntityPlayer.posX - par3EntityPlayer.prevPosX) * (double)f;
				double d1 = par3EntityPlayer.prevPosY + (par3EntityPlayer.posY - par3EntityPlayer.prevPosY) * (double)f + (double)(par3EntityPlayer.worldObj.isRemote ? par3EntityPlayer.getEyeHeight() - par3EntityPlayer.getDefaultEyeHeight() : par3EntityPlayer.getEyeHeight()); // isRemote check to revert changes to ray trace position due to adding the eye height clientside and player yOffset differences
				double d2 = par3EntityPlayer.prevPosZ + (par3EntityPlayer.posZ - par3EntityPlayer.prevPosZ) * (double)f;
				Vec3 vec3 = Vec3.createVectorHelper(d0, d1, d2);
				float f3 = MathHelper.cos(-f2 * 0.017453292F - (float)Math.PI);
				float f4 = MathHelper.sin(-f2 * 0.017453292F - (float)Math.PI);
				float f5 = -MathHelper.cos(-f1 * 0.017453292F);
				float f6 = MathHelper.sin(-f1 * 0.017453292F);
				float f7 = f4 * f5;
				float f8 = f3 * f5;
				double d3 = 50.0D;

				if (par3EntityPlayer instanceof EntityPlayerMP)
				{
					d3 = ((EntityPlayerMP)par3EntityPlayer).theItemInWorldManager.getBlockReachDistance() + 46.0;
				}

				Vec3 vec31 = vec3.addVector((double)f7 * d3, (double)f6 * d3, (double)f8 * d3);

				MovingObjectPosition mop = par3EntityPlayer.worldObj.func_147447_a(vec3, vec31, true, false, true);

				if (mop == null)
				{
					return par1ItemStack;
				}
				if (mop != null && mop.typeOfHit == MovingObjectType.BLOCK)
				{
					double d4 = mop.hitVec.xCoord;
					double d5 = mop.hitVec.yCoord;
					double d6 = mop.hitVec.zCoord;

					par3EntityPlayer.setPositionAndUpdate(d4, d5, d6);

					if (!par3EntityPlayer.capabilities.isCreativeMode)
					{
						doom.increaseDoom(-5);
					}

					this.cooldown = 10;
				}
			}

		}
		return par1ItemStack;
	}

	public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int par4)
	{
		int j = this.getMaxItemUseDuration(par1ItemStack) - par4;

		ArrowLooseEvent event = new ArrowLooseEvent(par3EntityPlayer, par1ItemStack, j);
		MinecraftForge.EVENT_BUS.post(event);
		if (event.isCanceled())
		{
			return;
		}
		j = event.charge;

		boolean flag = par3EntityPlayer.capabilities.isCreativeMode;

		float f = (float)j / 20.0F;
		f = (f * f + f * 2.0F) / 3.0F;

		f *= 1.5F;

		if ((double)f < 0.1D)
		{
			return;
		}

		if (f > 1.0F)
		{
			f = 1.0F;
		}

		EntityArrow entityarrow = new EntityArrow(par2World, par3EntityPlayer, f * 2.0F);

		entityarrow.setDamage(entityarrow.getDamage() + 3.0);
		entityarrow.motionX *= 1.1;
		entityarrow.motionZ *= 1.1;

		if (f == 1.0F)
		{
			entityarrow.setIsCritical(true);
		}

		int k = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, par1ItemStack);

		if (k > 0)
		{
			entityarrow.setDamage(entityarrow.getDamage() + (double)k * 0.5D + 0.5D);
		}

		int l = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, par1ItemStack);

		if (l > 0)
		{
			entityarrow.setKnockbackStrength(l);
		}

		if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, par1ItemStack) > 0)
		{
			entityarrow.setFire(100);
		}

		par1ItemStack.damageItem(1, par3EntityPlayer);
		par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

		entityarrow.canBePickedUp = 2;

		if (!par2World.isRemote)
		{
			par2World.spawnEntityInWorld(entityarrow);
		}

	}

}
