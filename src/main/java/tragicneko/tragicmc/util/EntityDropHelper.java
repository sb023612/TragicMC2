package tragicneko.tragicmc.util;

import static tragicneko.tragicmc.TragicMC.rand;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import tragicneko.tragicmc.entity.boss.EntityApis;
import tragicneko.tragicmc.entity.boss.EntityDeathReaper;
import tragicneko.tragicmc.entity.boss.EntityGreaterStin;
import tragicneko.tragicmc.entity.boss.EntityJarra;
import tragicneko.tragicmc.entity.boss.EntityKitsune;
import tragicneko.tragicmc.entity.boss.EntityKragul;
import tragicneko.tragicmc.entity.boss.EntityMagmox;
import tragicneko.tragicmc.entity.boss.EntityMegaCryse;
import tragicneko.tragicmc.entity.boss.EntityPolaris;
import tragicneko.tragicmc.entity.boss.EntityStinKing;
import tragicneko.tragicmc.entity.boss.EntityStinQueen;
import tragicneko.tragicmc.entity.boss.EntityTimeController;
import tragicneko.tragicmc.entity.boss.EntityYeti;
import tragicneko.tragicmc.entity.mob.EntityAbomination;
import tragicneko.tragicmc.entity.mob.EntityCryse;
import tragicneko.tragicmc.entity.mob.EntityGragul;
import tragicneko.tragicmc.entity.mob.EntityInkling;
import tragicneko.tragicmc.entity.mob.EntityJabba;
import tragicneko.tragicmc.entity.mob.EntityMinotaur;
import tragicneko.tragicmc.entity.mob.EntityNorVox;
import tragicneko.tragicmc.entity.mob.EntityPirah;
import tragicneko.tragicmc.entity.mob.EntityPlague;
import tragicneko.tragicmc.entity.mob.EntityPumpkinhead;
import tragicneko.tragicmc.entity.mob.EntityRagr;
import tragicneko.tragicmc.entity.mob.EntityStin;
import tragicneko.tragicmc.entity.mob.EntityStinBaby;
import tragicneko.tragicmc.entity.mob.EntityTox;
import tragicneko.tragicmc.entity.mob.EntityTragicNeko;
import tragicneko.tragicmc.entity.mob.EntityWisp;
import tragicneko.tragicmc.main.TragicBlocks;
import tragicneko.tragicmc.main.TragicItems;

public class EntityDropHelper {
	/**
	 * Hashmap that links an entity class to an itemstack array
	 */
	public static Map<Class, ItemStack[]> entityLootDrops = new HashMap();

	/**
	 * Hashmap that links an entity class to an itemstack array, these are the less common drops or in the case of bosses, it's their important drop
	 */
	public static Map<Class, ItemStack[]> entityRareDrops = new HashMap();

	static
	{
		//Normal Mob Drops
		entityLootDrops.put(EntityJabba.class, new ItemStack[] {new ItemStack(Items.magma_cream), new ItemStack(TragicItems.Ash)});
		entityRareDrops.put(EntityJabba.class, new ItemStack[] {new ItemStack(TragicItems.FireOrb), new ItemStack(TragicItems.RedMercury)});
		
		entityLootDrops.put(EntityPlague.class, new ItemStack[] {new ItemStack(TragicItems.Ash)});
		entityRareDrops.put(EntityPlague.class, new ItemStack[] {new ItemStack(TragicItems.DarkParticles)});
		
		entityLootDrops.put(EntityGragul.class, new ItemStack[] {new ItemStack(TragicItems.Ash), new ItemStack(Items.clay_ball), new ItemStack(Items.coal)});
		
		entityLootDrops.put(EntityMinotaur.class, new ItemStack[] {new ItemStack(Items.leather), new ItemStack(Items.beef), new ItemStack(TragicItems.Horn)});
		entityRareDrops.put(EntityMinotaur.class, new ItemStack[] {new ItemStack(TragicItems.Horn), new ItemStack(TragicItems.ToughLeather)});
		
		entityLootDrops.put(EntityInkling.class,  new ItemStack[] {new ItemStack(TragicItems.DarkParticles), new ItemStack(TragicItems.Ash)});
		entityRareDrops.put(EntityInkling.class, new ItemStack[] {new ItemStack(TragicItems.DarkParticles)});
		
		entityLootDrops.put(EntityRagr.class, new ItemStack[] {new ItemStack(TragicItems.CrushedIce), new ItemStack(Items.snowball), new ItemStack(Items.fish, 1, rand.nextInt(4))});
		entityRareDrops.put(EntityRagr.class, new ItemStack[] {new ItemStack(TragicItems.IceOrb), new ItemStack(TragicItems.Sushi)});
		
		entityLootDrops.put(EntityPumpkinhead.class, new ItemStack[] {new ItemStack(Items.pumpkin_seeds), new ItemStack(Items.pumpkin_pie), new ItemStack(Blocks.pumpkin)});
		entityRareDrops.put(EntityPumpkinhead.class, new ItemStack[] {new ItemStack(Blocks.lit_pumpkin), new ItemStack(TragicBlocks.Candle)});
		
		entityLootDrops.put(EntityTragicNeko.class, new ItemStack[] {new ItemStack(Items.iron_ingot), new ItemStack(Items.gunpowder)});
		entityRareDrops.put(EntityTragicNeko.class, new ItemStack[] {new ItemStack(Items.golden_apple, 1, 1), new ItemStack(TragicItems.NekoLauncher), new ItemStack(Items.diamond)});
		
		entityLootDrops.put(EntityTox.class, new ItemStack[] {new ItemStack(TragicItems.Spore), new ItemStack(TragicItems.Sap), new ItemStack(Items.wheat_seeds),
			new ItemStack(Blocks.vine), new ItemStack(TragicItems.Thorns)});
		entityRareDrops.put(EntityTox.class, new ItemStack[] {new ItemStack(TragicItems.ExoticFruit), new ItemStack(TragicItems.NastyFruit), new ItemStack(Items.slime_ball)});
		
		entityLootDrops.put(EntityCryse.class, new ItemStack[] {new ItemStack(TragicItems.CrushedIce), new ItemStack(Items.snowball)});
		entityRareDrops.put(EntityCryse.class, new ItemStack[] {new ItemStack(TragicItems.IceOrb)});
		
		entityLootDrops.put(EntityPirah.class, new ItemStack[] {new ItemStack(Items.fish, 1, rand.nextInt(3))});
		entityRareDrops.put(EntityPirah.class, new ItemStack[] {new ItemStack(TragicItems.LifeWater)});
		
		entityLootDrops.put(EntityNorVox.class, new ItemStack[] {new ItemStack(Blocks.mossy_cobblestone), new ItemStack(Blocks.stone), new ItemStack(TragicItems.Ectoplasm)});
		entityRareDrops.put(EntityNorVox.class, new ItemStack[] {new ItemStack(TragicItems.ObsidianOrb), new ItemStack(Items.emerald), new ItemStack(Items.diamond),
			new ItemStack(TragicItems.Tungsten), new ItemStack(TragicItems.RedMercury)});
		
		entityRareDrops.put(EntityStin.class, new ItemStack[] {new ItemStack(TragicItems.DarkParticles), new ItemStack(Items.ender_pearl)});
		
		entityRareDrops.put(EntityStinBaby.class, new ItemStack[] {new ItemStack(TragicItems.DarkParticles)});
		
		entityRareDrops.put(EntityWisp.class, new ItemStack[] {new ItemStack(TragicItems.WispParticles)});
		
		entityLootDrops.put(EntityAbomination.class, new ItemStack[] {new ItemStack(TragicItems.CrushedIce), new ItemStack(Items.fish), new ItemStack(Items.snowball)});
		entityRareDrops.put(EntityAbomination.class, new ItemStack[] {new ItemStack(TragicItems.IcyFur), new ItemStack(TragicItems.IceOrb)});
		
		//Mini-Boss drops
		entityLootDrops.put(EntityJarra.class, new ItemStack[] {new ItemStack(TragicItems.Thorns), new ItemStack(TragicItems.Spore)});
		entityRareDrops.put(EntityJarra.class, new ItemStack[] {new ItemStack(TragicItems.Spore), new ItemStack(TragicItems.Ectoplasm)});
		
		entityLootDrops.put(EntityKragul.class, new ItemStack[] {new ItemStack(TragicItems.Ash), new ItemStack(Items.redstone), new ItemStack(Blocks.redstone_block)});
		entityRareDrops.put(EntityKragul.class, new ItemStack[] {new ItemStack(TragicItems.GravityOrb)});
		
		entityLootDrops.put(EntityMagmox.class, new ItemStack[] {new ItemStack(Items.magma_cream), new ItemStack(TragicItems.Spore), new ItemStack(Items.nether_wart),
			new ItemStack(TragicItems.Thorns), new ItemStack(Blocks.vine)});
		entityRareDrops.put(EntityMagmox.class, new ItemStack[] {new ItemStack(Items.blaze_powder), new ItemStack(TragicItems.GooeyFruit)});
		
		entityLootDrops.put(EntityMegaCryse.class, new ItemStack[] {new ItemStack(TragicItems.CrushedIce), new ItemStack(TragicItems.IceOrb)});
		entityRareDrops.put(EntityMegaCryse.class, new ItemStack[] {new ItemStack(TragicItems.IceOrb)});
		
		entityRareDrops.put(EntityGreaterStin.class, new ItemStack[] {new ItemStack(TragicItems.ObsidianOrb), new ItemStack(TragicItems.DarkIngot),
			new ItemStack(TragicItems.StinHorn)});
		
		entityLootDrops.put(EntityStinKing.class, new ItemStack[] {new ItemStack(TragicItems.DarkIngot), new ItemStack(TragicItems.DarkParticles), new ItemStack(TragicItems.DarkBoots),
			new ItemStack(TragicItems.DarkHelm), new ItemStack(TragicItems.DarkPlate), new ItemStack(TragicItems.DarkLegs)});
		entityRareDrops.put(EntityStinKing.class, new ItemStack[] {new ItemStack(TragicItems.StinHorn)});
		
		entityLootDrops.put(EntityStinQueen.class, new ItemStack[] {new ItemStack(TragicItems.DarkIngot), new ItemStack(TragicItems.DarkParticles), new ItemStack(TragicItems.DarkBoots),
			new ItemStack(TragicItems.DarkHelm), new ItemStack(TragicItems.DarkPlate), new ItemStack(TragicItems.DarkLegs)});
		entityRareDrops.put(EntityStinQueen.class, new ItemStack[] {new ItemStack(TragicItems.StinHorn)});
		
		//Boss drops, the common drop is treated as the random drops and the rare is treated as the main drop in these cases
		entityLootDrops.put(EntityApis.class, new ItemStack[] {new ItemStack(TragicItems.LightParticles), new ItemStack(TragicItems.Horn), new ItemStack(TragicItems.LightIngot),
			new ItemStack(TragicItems.LightHelm), new ItemStack(TragicItems.LightPlate), new ItemStack(TragicItems.LightLegs), new ItemStack(TragicItems.LightBoots)});
		entityRareDrops.put(EntityApis.class, new ItemStack[] {new ItemStack(TragicItems.LightParticles)});
		
		entityLootDrops.put(EntityDeathReaper.class, new ItemStack[] {new ItemStack(TragicItems.BoneMarrow), new ItemStack(TragicItems.Ash), new ItemStack(Items.bone),
			new ItemStack(TragicItems.DarkParticles), new ItemStack(TragicItems.FireOrb), new ItemStack(Items.blaze_rod)});
		entityRareDrops.put(EntityDeathReaper.class, new ItemStack[] {new ItemStack(TragicItems.ReaperSkull)});
		
		entityLootDrops.put(EntityKitsune.class, new ItemStack[] {new ItemStack(TragicItems.KitsuneTail), new ItemStack(TragicItems.FireOrb), new ItemStack(Items.blaze_powder), 
			new ItemStack(Items.fire_charge)});
		entityRareDrops.put(EntityKitsune.class, new ItemStack[] {new ItemStack(TragicItems.KitsuneTail)});
		
		entityLootDrops.put(EntityPolaris.class, new ItemStack[] {new ItemStack(TragicBlocks.Quicksand, 1), new ItemStack(TragicItems.StarPieces), new ItemStack(Items.glowstone_dust)});
		entityRareDrops.put(EntityPolaris.class, new ItemStack[] {new ItemStack(TragicItems.StarPieces)});
		
		entityLootDrops.put(EntityTimeController.class, new ItemStack[] {new ItemStack(TragicItems.TimeEssence), new ItemStack(Items.clock),
			new ItemStack(Items.diamond), new ItemStack(Items.emerald)});
		entityRareDrops.put(EntityTimeController.class, new ItemStack[] {new ItemStack(TragicItems.TimeEssence)});
		
		entityLootDrops.put(EntityYeti.class, new ItemStack[] {new ItemStack(TragicItems.YetiClaw), new ItemStack(TragicItems.IceOrb), new ItemStack(TragicItems.CrushedIce),
			new ItemStack(Items.fish), new ItemStack(TragicItems.IcyFur)});
		entityRareDrops.put(EntityYeti.class, new ItemStack[] {new ItemStack(TragicItems.YetiClaw)});
	}

	/**
	 * Returns a random drop, with flag deciding if true for rare drops or false for common drops
	 * @param clazz
	 * @param flag
	 * @return
	 */
	public static ItemStack getRandomDropFromEntity(Class clazz, boolean flag)
	{
		if (flag)
		{
			return getRareDropFromEntity(clazz);
		}

		return getCommonDropFromEntity(clazz);
	}
	
	/**
	 * Returns a random drop with a random chance to be rare or common
	 * @param clazz
	 * @return
	 */
	public static ItemStack getRandomDropFromEntity(Class clazz)
	{
		return getRandomDropFromEntity(clazz, rand.nextBoolean());
	}

	/**
	 * Gets a random rare drop
	 * @param clazz
	 * @return
	 */
	public static ItemStack getRareDropFromEntity(Class clazz)
	{
		return getLootArrayFromEntity(clazz)[rand.nextInt(getLootArrayFromEntity(clazz).length)];
	}

	/**
	 * Gets a random common drop
	 * @param clazz
	 * @return
	 */
	public static ItemStack getCommonDropFromEntity(Class clazz)
	{
		return getRareLootArrayFromEntity(clazz)[rand.nextInt(getRareLootArrayFromEntity(clazz).length)];
	}

	/**
	 * Gets the ItemStack array mapped to the inputted class, for other randomization methods
	 * @param clazz
	 * @return
	 */
	public static ItemStack[] getLootArrayFromEntity(Class clazz)
	{
		ItemStack[] array = null;
		try {
			array = (ItemStack[]) entityLootDrops.get(clazz);
			return array;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.err.println("[ERROR]No mapping for common loot drop from entity with class of " + clazz);
			return array;
		}
	}
	
	/**
	 * Gets a specific ItemStack from the inputted class's mapping
	 * @param clazz
	 * @param i
	 * @return
	 */
	public static ItemStack getCommonItemStackFromSpecificSlot(Class clazz, int i)
	{
		return getLootArrayFromEntity(clazz)[i];
	}
	
	public static ItemStack getRareItemStackFromSpecificSlot(Class clazz, int i)
	{
		return getRareLootArrayFromEntity(clazz)[i];
	}
	
	/**
	 * Gets the rare ItemStack array mapped to the inputted class, for other methods of randomization
	 * @param clazz
	 * @return
	 */
	public static ItemStack[] getRareLootArrayFromEntity(Class clazz)
	{
		ItemStack[] array;
		try {
			array = (ItemStack[]) entityRareDrops.get(clazz);
			return array;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.err.println("[ERROR]No mapping for rare loot drop from entity with class of " + clazz);
			return null;
		}
	}
}
