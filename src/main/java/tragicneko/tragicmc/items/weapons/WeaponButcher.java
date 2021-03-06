package tragicneko.tragicmc.items.weapons;

import java.util.UUID;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import tragicneko.tragicmc.doomsday.Doomsday;
import tragicneko.tragicmc.entity.projectile.EntityDarkEnergy;
import tragicneko.tragicmc.items.weapons.TragicWeapon.Lore;
import tragicneko.tragicmc.main.TragicEnchantments;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.properties.PropertyDoom;

public class WeaponButcher extends EpicWeapon {
	
	private final Lore[] uniqueLores = new Lore[] {new Lore("Time to Eat!", EnumRarity.epic), new Lore("Can we eat now?", EnumRarity.uncommon), new Lore("Dinner Time!", EnumRarity.rare),
			new Lore("I'm hungry...", EnumRarity.uncommon), new Lore("That looks delicious!", EnumRarity.uncommon), new Lore("I need food..."), new Lore("My stomach won't stop growling!", EnumRarity.rare),
			new Lore("MMMmmm... donuts...", EnumRarity.rare), new Lore("OMNOMNOMNOM", EnumRarity.epic), new Lore("Anything here is edible, even I am, but that would be cannibalism.", EnumRarity.rare),
			new Lore("Tasty.", EnumRarity.uncommon), new Lore("That was delicious!"), new Lore("I'm having an old friend for dinner!", EnumRarity.rare), new Lore("Bon apetite!", EnumRarity.uncommon),
			new Lore("Just add salt!", EnumRarity.rare), new Lore("Just a pinch of sage!", EnumRarity.uncommon)};

	public WeaponButcher(Doomsday dday) {
		super(dday);
		this.lores = uniqueLores;
		this.rareEnchants = new Enchantment[] {Enchantment.unbreaking, TragicEnchantments.Reach, Enchantment.sharpness};
		this.rareLevels = new int[] {5, 3, 1};
		this.epicEnchants = new Enchantment[] {Enchantment.unbreaking, TragicEnchantments.Reach, Enchantment.sharpness, TragicEnchantments.Slay};
		this.epicLevels = new int[] {10, 5, 3, 3};
	}

	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
	{
		PropertyDoom doom = PropertyDoom.get(player);

		if (!super.onLeftClickEntity(stack, player, entity) && entity instanceof EntityLivingBase && cooldown == 0 && doom != null && doom.getCurrentDoom() >= 1
				&& TragicNewConfig.allowNonDoomsdayAbilities)
		{
			if (itemRand.nextBoolean()) ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.weakness.id, 200, 1));
			cooldown = 10;
		}
		return super.onLeftClickEntity(stack, player, entity);
	} 	

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int numb, boolean flag)
	{
		super.onUpdate(stack, world, entity, numb, flag);
		if (world.isRemote || !(entity instanceof EntityPlayer) || !(TragicNewConfig.allowNonDoomsdayAbilities)) return;
		
		UUID uuidForMod = UUID.fromString("040d7d22-6b19-498b-8216-4316cf39387e");
		AttributeModifier mod = new AttributeModifier(uuidForMod, "butcherModifier", 1.0, 0);
		EntityPlayer player = (EntityPlayer) entity;
		PropertyDoom doom = PropertyDoom.get(player);
		
		player.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).removeModifier(mod);
		
		if (flag && doom != null && doom.getCurrentDoom() > 0)
		{
			player.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).applyModifier(mod);
		}
	}
}
