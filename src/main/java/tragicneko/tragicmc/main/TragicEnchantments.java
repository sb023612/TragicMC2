package tragicneko.tragicmc.main;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraftforge.common.util.EnumHelper;
import tragicneko.tragicmc.enchantment.EnchantmentAgility;
import tragicneko.tragicmc.enchantment.EnchantmentArmorExtra;
import tragicneko.tragicmc.enchantment.EnchantmentCombustion;
import tragicneko.tragicmc.enchantment.EnchantmentDamageBoost;
import tragicneko.tragicmc.enchantment.EnchantmentMultiply;
import tragicneko.tragicmc.enchantment.EnchantmentRuneWalker;
import tragicneko.tragicmc.enchantment.EnchantmentWeaponExtra;

public class TragicEnchantments {
	
	public static Enchantment Decay, Absolve, Slay, Vampirism, Leech, Consume, Distract, Combustion, Multiply, Agility;
	public static Enchantment Ignition, Paralysis, Toxicity, Elasticity, DeathTouch;

	public static void load() 
	{
		if (TragicNewConfig.allowDecay) Decay = (new EnchantmentDamageBoost(TragicNewConfig.idDecay, 8, 0));
		if (TragicNewConfig.allowAbsolve) Absolve = (new EnchantmentDamageBoost(TragicNewConfig.idAbsolve, 1, 1));
		if (TragicNewConfig.allowSlay) Slay = (new EnchantmentDamageBoost(TragicNewConfig.idSlay, 8, 2));
		if (TragicNewConfig.allowVampirism) Vampirism = (new EnchantmentWeaponExtra(TragicNewConfig.idVampirism, 1, 0));
		if (TragicNewConfig.allowLeech) Leech = (new EnchantmentWeaponExtra(TragicNewConfig.idLeech, 1, 1));
		if (TragicNewConfig.allowConsume) Consume = (new EnchantmentWeaponExtra(TragicNewConfig.idConsume, 1, 2));
		if (TragicNewConfig.allowDistract) Distract = (new EnchantmentWeaponExtra(TragicNewConfig.idDistract, 10, 3));
		if (TragicNewConfig.allowCombustion) Combustion = (new EnchantmentCombustion(TragicNewConfig.idCombustion, 1, EnumEnchantmentType.digger));
		if (TragicNewConfig.allowAgility) Agility = (new EnchantmentAgility(TragicNewConfig.idAgility, 2, EnumEnchantmentType.armor));
		if (TragicNewConfig.allowMultiply) Multiply = (new EnchantmentMultiply(TragicNewConfig.idMultiply, 1, EnumEnchantmentType.bow));

		if (TragicNewConfig.allowIgnition)Ignition = (new EnchantmentArmorExtra(TragicNewConfig.idIgnition, 6, 0));
		if (TragicNewConfig.allowParalysis)Paralysis = (new EnchantmentArmorExtra(TragicNewConfig.idParalysis, 1, 1));
		if (TragicNewConfig.allowToxicity)Toxicity = (new EnchantmentArmorExtra(TragicNewConfig.idToxicity, 4, 2));
		if (TragicNewConfig.allowElasticity)Elasticity = (new EnchantmentArmorExtra(TragicNewConfig.idElasticity, 10, 3));
		if (TragicNewConfig.allowDeathTouch)DeathTouch = (new EnchantmentArmorExtra(TragicNewConfig.idDeathTouch, 1, 4));

		
	}
}
