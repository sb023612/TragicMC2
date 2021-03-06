package tragicneko.tragicmc.items.weapons;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.main.TragicEnchantments;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.properties.PropertyDoom;
import tragicneko.tragicmc.util.WorldHelper;

public class WeaponTitan extends EpicWeapon {
	
	private final Lore[] uniqueLores = new Lore[] {new Lore("You are an ant to me, mortal!", EnumRarity.epic), new Lore("Thank the Gods!", EnumRarity.uncommon), new Lore("Puny God.", EnumRarity.epic),
			new Lore("God-like.", EnumRarity.uncommon), new Lore("A God-like aura.", EnumRarity.uncommon), new Lore("Such a Mortal."), new Lore("God-like abilities!", EnumRarity.rare),
			new Lore("Poseidon has nothing on me.", EnumRarity.rare), new Lore("I am a God!", EnumRarity.epic), new Lore("Almost God-like!", EnumRarity.rare),
			new Lore("Mortal tendencies", EnumRarity.uncommon), new Lore("Filthy Mortal"), new Lore("I'm having an old friend for dinner!", EnumRarity.rare),
			new Lore("So epic!", EnumRarity.uncommon), new Lore("As spectacular as Aphrodite!", EnumRarity.epic), new Lore("Faster than Hermes!", EnumRarity.rare)};

	public WeaponTitan(Doomsday dday) {
		super(dday);
		this.lores = uniqueLores;
		this.rareEnchants = new Enchantment[] {Enchantment.unbreaking, TragicEnchantments.Reach};
		this.rareLevels = new int[] {5, 3};
		this.epicEnchants = new Enchantment[] {Enchantment.unbreaking, TragicEnchantments.Reach, Enchantment.knockback};
		this.epicLevels = new int[] {10, 3, 1};
	}

	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
	{
		if (player.worldObj.isRemote || !(entity instanceof EntityLivingBase)) return super.onLeftClickEntity(stack, player, entity);
		
		if (itemRand.nextInt(6) != 0) return super.onLeftClickEntity(stack, player, entity);

		PropertyDoom doom = PropertyDoom.get(player);

		if (doom != null && doom.getCurrentDoom() >= 10)
		{
			for (int i = 0; i < 3; i++)
			{
				player.worldObj.addWeatherEffect(new EntityLightningBolt(player.worldObj, entity.posX + itemRand.nextDouble() - itemRand.nextDouble(), entity.posY,
						entity.posZ + itemRand.nextDouble() - itemRand.nextDouble()));
			}

			player.worldObj.createExplosion(player, entity.posX, entity.posY, entity.posZ, itemRand.nextFloat() * 3.0F, WorldHelper.getMobGriefing(player.worldObj));

			if (!player.capabilities.isCreativeMode)
			{
				doom.increaseDoom(-10);
			}

			this.cooldown = 40;
		}
		return super.onLeftClickEntity(stack, player, entity);
	}

	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		PropertyDoom doom = PropertyDoom.get(par3EntityPlayer);

		if (doom == null || !TragicNewConfig.allowNonDoomsdayAbilities) return par1ItemStack;

		MovingObjectPosition mop = this.getMOPFromPlayer(par3EntityPlayer);

		if (mop == null)
		{
			return par1ItemStack;
		}

		if (cooldown == 0 && !par2World.isRemote && doom.getCurrentDoom() >= 20)
		{
			double d4 = mop.hitVec.xCoord - par3EntityPlayer.posX;
			double d5 = mop.hitVec.yCoord - (par3EntityPlayer.posY + (double)(par3EntityPlayer.height / 2.0F));
			double d6 = mop.hitVec.zCoord - par3EntityPlayer.posZ;

			double d7 = MathHelper.sqrt_double(d4 * d4 + d5 * d5 + d6 * d6);

			if (d7 >= 3.0D)
			{
				par2World.addWeatherEffect(new EntityLightningBolt(par2World, par3EntityPlayer.posX + ((0.95D * d4)),
						par2World.getTopSolidOrLiquidBlock((int) (par3EntityPlayer.posX + (0.95D * d4)), (int) (par3EntityPlayer.posZ + (0.95D * d6))),
						par3EntityPlayer.posZ + (0.95D * d6)));

				if (d7 >= 8.0D)
				{
					par2World.addWeatherEffect(new EntityLightningBolt(par2World, par3EntityPlayer.posX + ((0.75D * d4)),
							par2World.getTopSolidOrLiquidBlock((int) (par3EntityPlayer.posX + (0.75D * d4)), (int) (par3EntityPlayer.posZ + (0.75D * d6))),
							par3EntityPlayer.posZ + (0.75D * d6)));

					if (d7 >= 13.0D)
					{
						par2World.addWeatherEffect(new EntityLightningBolt(par2World, par3EntityPlayer.posX + ((0.6D * d4)),
								par2World.getTopSolidOrLiquidBlock((int) (par3EntityPlayer.posX + (0.6D * d4)), (int) (par3EntityPlayer.posZ + (0.6D * d6))),
								par3EntityPlayer.posZ + (0.6D * d6)));

						if (d7 >= 18.0D)
						{
							par2World.addWeatherEffect(new EntityLightningBolt(par2World, par3EntityPlayer.posX + ((0.4D * d4)),
									par2World.getTopSolidOrLiquidBlock((int) (par3EntityPlayer.posX + (0.4D * d4)), (int) (par3EntityPlayer.posZ + (0.4D * d6))),
									par3EntityPlayer.posZ + (0.4D * d6)));

							if (d7 >= 21.0D)
							{
								par2World.addWeatherEffect(new EntityLightningBolt(par2World, par3EntityPlayer.posX + ((0.2D * d4)),
										par2World.getTopSolidOrLiquidBlock((int) (par3EntityPlayer.posX + (0.2D * d4)), (int) (par3EntityPlayer.posZ + (0.2D * d6))),
										par3EntityPlayer.posZ + (0.2D * d6)));
								
								if (d7 >= 24.0D)
								{
									par2World.addWeatherEffect(new EntityLightningBolt(par2World, par3EntityPlayer.posX + ((0.1D * d4)),
											par2World.getTopSolidOrLiquidBlock((int) (par3EntityPlayer.posX + (0.1D * d4)), (int) (par3EntityPlayer.posZ + (0.1D * d6))),
											par3EntityPlayer.posZ + (0.1D * d6)));
								}
							}
						}
					}
				}
				
				if (!par3EntityPlayer.capabilities.isCreativeMode) doom.increaseCooldown(-20);
				cooldown = 40;
			}
		}
		return par1ItemStack;
	}

}
