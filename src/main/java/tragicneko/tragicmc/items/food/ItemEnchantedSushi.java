package tragicneko.tragicmc.items.food;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.main.TragicPotions;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemEnchantedSushi extends ItemFood {

	public ItemEnchantedSushi(int p_i45340_1_, boolean p_i45340_2_) {
		super(p_i45340_1_, p_i45340_2_);
		if (TragicNewConfig.allowImmunity)
		{
			this.setPotionEffect(TragicPotions.Immunity.id, 120, 0, 1.0F);
		}
		this.setAlwaysEdible();
	}
	
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack stack)
	{
		return EnumRarity.epic;
	}
	
	@Override
	public int getMaxItemUseDuration(ItemStack stack)
	{
		return 16;
	}

	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack)
	{
		return true;
	}

	public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player)
	{
		player.addPotionEffect(new PotionEffect(Potion.field_76444_x.id, 2400, 4));
		player.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 1200, 1));
		player.addPotionEffect(new PotionEffect(Potion.field_76443_y.id, 2400, 1));
		player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 2400, 2));
		if (TragicNewConfig.allowClarity) player.addPotionEffect(new PotionEffect(TragicPotions.Clarity.id, 2400, 1));
		if (TragicNewConfig.allowInvulnerability) player.addPotionEffect(new PotionEffect(TragicPotions.Invulnerability.id, 60));
		
		return super.onEaten(stack, world, player);
	}

}
