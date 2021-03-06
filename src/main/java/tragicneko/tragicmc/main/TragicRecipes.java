package tragicneko.tragicmc.main;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;

public class TragicRecipes {

	public static void load()
	{		
		//Smelting
		GameRegistry.addSmelting(TragicItems.RedMercury, new ItemStack(TragicItems.Quicksilver), 1F);
		GameRegistry.addSmelting(TragicBlocks.MercuryOre, new ItemStack(TragicItems.RedMercury), 2F);
		GameRegistry.addSmelting(TragicBlocks.TungstenOre, new ItemStack(TragicItems.Tungsten), 3.5F);
		GameRegistry.addSmelting(new ItemStack(TragicBlocks.TragicOres, 1, 0), new ItemStack(TragicItems.RedMercury), 2F);

		//Crafting Recipes
		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.QuicksilverIngot, 1), new Object[] {
			" x ",
			"xxx",
			" x ",
			'x', TragicItems.Quicksilver
		});

		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.MercuryDagger, 1), new Object[] {
			" x",
			"y ",
			'x', TragicItems.RedMercury,
			'y', TragicItems.Quicksilver
		});

		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.SkullHelmet, 1),  new Object[] {
			" x ",
			"x x",
			'x', Items.bone
		});

		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.SkullPlate, 1), new Object[] {
			"x x",
			"xxx",
			'x', Items.bone
		});

		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.SkullLegs, 1), new Object[] {
			"xxx",
			"x x",
			'x', Items.bone
		});

		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.SkullBoots, 1), new Object[] {
			"x x",
			"x x",
			'x', Items.bone
		});

		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.Scythe, 1), new Object[] {
			"xxx",
			" x ",
			"x  ",
			'x', Items.bone
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.MobStatue, 1, 0), new Object[] {
			"xyx",
			"yzy",
			"xyx",
			'x', TragicItems.LivingClay,
			'y', TragicItems.LightParticles,
			'z', Blocks.redstone_block
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.MobStatue, 1, 1), new Object[] {
			"xyx",
			"yzy",
			"xyx",
			'x', TragicItems.LivingClay,
			'y', TragicItems.KitsuneTail,
			'z', Blocks.redstone_block
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.MobStatue, 1, 2), new Object[] {
			"xyx",
			"yzy",
			"xyx",
			'x', TragicItems.LivingClay,
			'y', TragicItems.ReaperSkull,
			'z', Blocks.redstone_block
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.MobStatue, 1, 3), new Object[] {
			"xyx",
			"yzy",
			"xyx",
			'x', TragicItems.LivingClay,
			'y', TragicItems.TimeEssence,
			'z', Blocks.redstone_block
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.MobStatue, 1, 4), new Object[] {
			"xyx",
			"yzy",
			"xyx",
			'x', TragicItems.LivingClay,
			'y', TragicItems.EmpariahClaw,
			'z', Blocks.redstone_block
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.MobStatue, 1, 5), new Object[] {
			"xyx",
			"yzy",
			"xyx",
			'x', TragicItems.LivingClay,
			'y', TragicItems.StarPieces,
			'z', Blocks.redstone_block
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.MobStatue, 1, 6), new Object[] {
			"xyx",
			"yzy",
			"xyx",
			'x', TragicItems.LivingClay,
			'y', TragicItems.Spore,
			'z', Blocks.redstone_block
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.MobStatue, 1, 7), new Object[] {
			"xyx",
			"yzy",
			"xyx",
			'x', TragicItems.LivingClay,
			'y', Blocks.hardened_clay,
			'z', Blocks.redstone_block
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.MobStatue, 1, 8), new Object[] {
			"xyx",
			"yzy",
			"xyx",
			'x', TragicItems.LivingClay,
			'y', Items.magma_cream,
			'z', Blocks.redstone_block
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.MobStatue, 1, 9), new Object[] {
			"xyx",
			"yzy",
			"xyx",
			'x', TragicItems.LivingClay,
			'y', TragicItems.CrushedIce,
			'z', Blocks.redstone_block
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.MobStatue, 1, 10), new Object[] {
			"xyx",
			"yzy",
			"xyx",
			'x', TragicItems.LivingClay,
			'y', TragicItems.StinHorn,
			'z', Blocks.redstone_block
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.MobStatue, 1, 11), new Object[] {
			"xyx",
			"yzy",
			"xyx",
			'x', TragicItems.LivingClay,
			'y', TragicItems.DarkParticles,
			'z', Blocks.redstone_block
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.MobStatue, 1, 12), new Object[] {
			"xyx",
			"yzy",
			"xyx",
			'x', TragicItems.LivingClay,
			'y', TragicItems.Ash,
			'z', Blocks.redstone_block
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.MobStatue, 1, 13), new Object[] {
			"xyx",
			"yzy",
			"xyx",
			'x', TragicItems.LivingClay,
			'y', TragicBlocks.StarCrystal,
			'z', Blocks.redstone_block
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.MobStatue, 1, 14), new Object[] {
			"xyx",
			"yzy",
			"xyx",
			'x', TragicItems.LivingClay,
			'y', TragicItems.PureDarkness,
			'z', Blocks.redstone_block
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.MobStatue, 1, 15), new Object[] {
			"xyx",
			"yzy",
			"xyx",
			'x', TragicItems.LivingClay,
			'y', Items.blaze_powder,
			'z', Blocks.redstone_block
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.Talisman, 1), new Object[] {
			" x ",
			"xxx",
			" x ",
			'x', TragicItems.LivingClay
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.SunnyDayTalisman, 1), new Object[] {
			"xyx",
			"zwz",
			"yzy",
			'w', TragicItems.Talisman,
			'x', Blocks.redstone_block,
			'y', TragicItems.LunarPowder,
			'z', TragicItems.FireOrb
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.RainDanceTalisman, 1), new Object[] {
			"xyx",
			"zwz",
			"yzy",
			'w', TragicItems.Talisman,
			'x', Blocks.lapis_block,
			'y', TragicItems.LunarPowder,
			'z', TragicItems.AquaOrb
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.ThunderstormTalisman, 1), new Object[] {
			"xyx",
			"zwz",
			"yzy",
			'w', TragicItems.Talisman,
			'x', Blocks.gold_block,
			'y', TragicItems.LunarPowder,
			'z', TragicItems.LightningOrb
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.TimeManipulatorTalisman, 1), new Object[] {
			"xyx",
			"zwz",
			"yzy",
			'w', TragicItems.Talisman,
			'x', TragicItems.CelestialDiamond,
			'y', TragicItems.LunarPowder,
			'z', TragicItems.GravityOrb
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.SynthesisTalisman, 1), new Object[] {
			"xyx",
			"zwz",
			"yzy",
			'w', TragicItems.Talisman,
			'x', Blocks.emerald_block,
			'y', TragicItems.LunarPowder,
			'z', TragicItems.Spore
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.HydrationTalisman, 1), new Object[] {
			"xyx",
			"zwz",
			"yzy",
			'w', TragicItems.Talisman,
			'x', Blocks.diamond_block,
			'y', TragicItems.LunarPowder,
			'z', TragicItems.LifeWater
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.LightningRodTalisman, 1), new Object[] {
			"xyx",
			"zwz",
			"yzy",
			'w', TragicItems.Talisman,
			'x', Blocks.coal_block,
			'y', TragicItems.LunarPowder,
			'z', TragicItems.Horn
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.MoonlightTalisman, 1), new Object[] {
			"xyx",
			"zwz",
			"yzy",
			'w', TragicItems.Talisman,
			'x', Blocks.iron_block,
			'y', TragicItems.LunarPowder,
			'z', TragicItems.WispParticles
		});

		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.DiamondCharm, 1), new Object[] {
			"xyx",
			"yxy",
			"xyx",
			'x', Items.diamond,
			'y', TragicItems.Tungsten
		});

		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.EmeraldCharm, 1), new Object[] {
			"xyx",
			"yxy",
			"xyx",
			'x', Items.emerald,
			'y', TragicItems.Tungsten
		});

		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.RubyCharm, 1), new Object[] {
			"xyx",
			"yxy",
			"xyx",
			'x', TragicItems.Ruby,
			'y', TragicItems.Tungsten
		});

		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.SapphireCharm, 1), new Object[] {
			"xyx",
			"yxy",
			"xyx",
			'x', TragicItems.Sapphire,
			'y', TragicItems.Tungsten
		});

		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.ObsidianOrb, 6), new Object[] {
			"xxx",
			"xyx",
			"xxx",
			'x', Blocks.obsidian,
			'y', TragicItems.QuicksilverIngot
		});
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(TragicItems.CelestialSteel, 1), new Object[] {
			"celestialSteelDrops", "celestialSteelDrops", "celestialSteelDrops", Items.iron_ingot
		}));
		
		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.CelestialAegis, 1), new Object[] {
			" x ",
			" x ",
			"yxy",
			'x', TragicItems.CelestialSteel,
			'y', TragicItems.CelestialDiamond
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.CelestialLongbow, 1), new Object[] {
			"wyx",
			"yxz",
			"wyx",
			'w', TragicItems.EmpariahClaw,
			'x', TragicItems.CelestialSteel,
			'y', TragicItems.CelestialDiamond,
			'z', TragicItems.WovenSilk
		});
		
		GameRegistry.addShapelessRecipe(new ItemStack(TragicBlocks.BoneBlock, 16), new Object[] {
			TragicItems.ReaperSkull
		});
		
		GameRegistry.addShapelessRecipe(new ItemStack(TragicBlocks.BoneBlock, 16, 1), new Object[] {
			TragicItems.ReaperSkull, Items.rotten_flesh
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(TragicBlocks.StarCrystal, 15), new Object[] {
			"xx",
			"xx",
			'x', TragicItems.StarPieces
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(TragicBlocks.TimeDisruptionCube, 1), new Object[] {
			"xx",
			"xx",
			'x', TragicItems.TimeEssence
		});

		GameRegistry.addShapelessRecipe(new ItemStack(TragicItems.AwakeningStone, 1), new Object[] {
			TragicItems.EmeraldCharm, TragicItems.DiamondCharm, TragicItems.ObsidianOrb,  TragicItems.RubyCharm, TragicItems.SapphireCharm
		});

		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.CryingObsidianOrb, 3), new Object[] {
			"yzy",
			"zxz",
			"yzy",
			'y', new ItemStack(Items.dye, 1, 4),
			'z', Blocks.obsidian,
			'x', TragicItems.ObsidianOrb
		});

		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.BleedingObsidianOrb, 3), new Object[] {
			"yzy",
			"zxz",
			"yzy",
			'y', Items.redstone,
			'z', Blocks.obsidian,
			'x', TragicItems.ObsidianOrb
		});

		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.DyingObsidianOrb, 3), new Object[] {
			"yzy",
			"zxz",
			"yzy",
			'y', Items.emerald,
			'z', Blocks.obsidian,
			'x', TragicItems.ObsidianOrb
		});

		GameRegistry.addShapedRecipe(new ItemStack(Items.coal, 1), new Object[] {
			"xx",
			"xx",
			'x', TragicItems.Ash
		});

		//Skeleton spawn egg
		GameRegistry.addShapelessRecipe(new ItemStack(Items.spawn_egg, 1, 51), new Object[] {
			TragicItems.LifeWater, TragicItems.Ash, Items.bone
		});

		//Creeper spawn egg
		GameRegistry.addShapelessRecipe(new ItemStack(Items.spawn_egg, 1, 50), new Object[] {
			TragicItems.LifeWater, TragicItems.Ash, Items.gunpowder
		});

		//Zombie spawn egg
		GameRegistry.addShapelessRecipe(new ItemStack(Items.spawn_egg, 1, 54), new Object[] {
			TragicItems.LifeWater, TragicItems.Ash, Items.rotten_flesh
		});

		//Slime spawn egg
		GameRegistry.addShapelessRecipe(new ItemStack(Items.spawn_egg, 1, 55), new Object[] {
			TragicItems.LifeWater, TragicItems.Ash, Items.slime_ball
		});

		//Spider spawn egg
		GameRegistry.addShapelessRecipe(new ItemStack(Items.spawn_egg, 1, 52), new Object[] {
			TragicItems.LifeWater, TragicItems.Ash, Items.spider_eye
		});

		//Cow spawn egg
		GameRegistry.addShapelessRecipe(new ItemStack(Items.spawn_egg, 1, 92), new Object[] {
			TragicItems.LifeWater, TragicItems.Ash, Items.leather
		});

		//Pig spawn egg
		GameRegistry.addShapelessRecipe(new ItemStack(Items.spawn_egg, 1, 90), new Object[] {
			TragicItems.LifeWater, TragicItems.Ash, Items.carrot
		});

		//Chicken spawn egg
		GameRegistry.addShapelessRecipe(new ItemStack(Items.spawn_egg, 1, 93), new Object[] {
			TragicItems.LifeWater, TragicItems.Ash, Items.egg
		});

		//Mooshroom spawn egg
		GameRegistry.addShapelessRecipe(new ItemStack(Items.spawn_egg, 1, 96), new Object[] {
			TragicItems.LifeWater, TragicItems.Ash, Blocks.red_mushroom
		});
		GameRegistry.addShapelessRecipe(new ItemStack(Items.spawn_egg, 1, 96), new Object[] {
			TragicItems.LifeWater, TragicItems.Ash, Blocks.brown_mushroom
		});

		//Villager spawn egg
		GameRegistry.addShapelessRecipe(new ItemStack(Items.spawn_egg, 1, 120), new Object[] {
			TragicItems.LifeWater, TragicItems.Ash, Items.emerald
		});

		//Horse spawn egg
		GameRegistry.addShapelessRecipe(new ItemStack(Items.spawn_egg, 1, 100), new Object[] {
			TragicItems.LifeWater, TragicItems.Ash, Items.saddle
		});

		GameRegistry.addShapelessRecipe(new ItemStack(TragicItems.ToughLeather, 1), new Object[] {
			TragicItems.WovenSilk, Items.leather, Items.leather
		}); 

		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.ToughLeather, 1), new Object[] {
			"xxx",
			" x ",
			"xxx",
			'x', Items.rotten_flesh
		});

		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.WovenSilk, 1), new Object[] {
			"xxx",
			'x', Items.string
		});

		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.HuntersCap, 1), new Object[] {
			" x ",
			"x x",
			'x', TragicItems.ToughLeather
		});

		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.HuntersTunic, 1), new Object[] {
			"x x",
			"xxx",
			'x', TragicItems.ToughLeather
		});

		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.HuntersLegs, 1), new Object[] {
			"xxx",
			"x x",
			'x', TragicItems.ToughLeather
		});

		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.HuntersBoots, 1), new Object[] {
			"x x",
			"x x",
			'x', TragicItems.ToughLeather
		});

		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.MercuryHelm, 1), new Object[] {
			"xxx",
			"x x",
			'x', TragicItems.RedMercury
		});

		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.MercuryPlate, 1),  new Object [] {
			"x x",
			"xxx",
			"xxx",
			'x', TragicItems.RedMercury
		});

		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.MercuryLegs, 1), new Object[] {
			"xxx",
			"x x",
			"x x",
			'x', TragicItems.RedMercury
		});

		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.MercuryBoots, 1), new Object[] {
			"x x",
			"x x",
			'x', TragicItems.RedMercury
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.TungstenHelm, 1), new Object[] {
			"xxx",
			"x x",
			'x', TragicItems.Tungsten
		});

		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.TungstenPlate, 1),  new Object [] {
			"x x",
			"xxx",
			"xxx",
			'x', TragicItems.Tungsten
		});

		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.TungstenLegs, 1), new Object[] {
			"xxx",
			"x x",
			"x x",
			'x', TragicItems.Tungsten
		});

		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.TungstenBoots, 1), new Object[] {
			"x x",
			"x x",
			'x', TragicItems.Tungsten
		});

		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.HuntersBow, 1), new Object[] {
			" x",
			"zy",
			" x",
			'x', Items.stick,
			'y', TragicItems.WovenSilk,
			'z', TragicItems.ToughLeather
		});

		GameRegistry.addShapelessRecipe(new ItemStack(TragicItems.LightIngot, 1), new Object[] {
			TragicItems.LightParticles, TragicItems.Tungsten
		});

		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.LightHelm, 1), new Object[] {
			"xxx",
			"x x",
			'x', TragicItems.LightIngot
		});

		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.LightPlate, 1),  new Object [] {
			"x x",
			"xxx",
			"xxx",
			'x', TragicItems.LightIngot
		});

		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.LightLegs, 1), new Object[] {
			"xxx",
			"x x",
			"x x",
			'x', TragicItems.LightIngot
		});

		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.LightBoots, 1), new Object[] {
			"x x",
			"x x",
			'x', TragicItems.LightIngot
		});

		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.BlindingLight, 1), new Object[] {
			"x x",
			"xyx",
			" x ",
			'x', TragicItems.LightIngot,
			'y', Items.diamond
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.EverlastingLight, 1), new Object[] {
			"xyx",
			"yzy",
			"xyx",
			'x', TragicItems.LightParticles,
			'y', Blocks.glass,
			'z', Items.nether_star
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(TragicBlocks.Candle, 3), new Object[]{
			"y",
			"x",
			"x",
			'y', TragicItems.WovenSilk,
			'x', TragicBlocks.Wax,
			'x', TragicBlocks.Wax
		});

		GameRegistry.addShapelessRecipe(new ItemStack(TragicItems.DarkIngot, 1), new Object[] {
			TragicItems.DarkParticles, TragicItems.QuicksilverIngot
		});

		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.DarkHelm, 1), new Object[] {
			"xxx",
			"x x",
			'x', TragicItems.DarkIngot
		});

		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.DarkPlate, 1),  new Object [] {
			"x x",
			"xxx",
			"xxx",
			'x', TragicItems.DarkIngot
		});

		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.DarkLegs, 1), new Object[] {
			"xxx",
			"x x",
			"x x",
			'x', TragicItems.DarkIngot
		});

		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.DarkBoots, 1), new Object[] {
			"x x",
			"x x",
			'x', TragicItems.DarkIngot
		});

		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.PitchBlack, 1), new Object[] {
			"zxz",
			"xyx",
			"zxz",
			'x', TragicItems.DarkIngot,
			'y', TragicItems.ObsidianOrb,
			'z', TragicItems.PureDarkness
		});

		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.GooeyFruit, 1), new Object[] {
			"xxx",
			"xyx",
			"xxx",
			'x', TragicItems.Sap,
			'y', TragicItems.ExoticFruit
		});

		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.NastyFruit, 1), new Object[] {
			"xzx",
			"zyz",
			"xzx",
			'x', TragicItems.BoneMarrow,
			'y', TragicItems.ExoticFruit,
			'z', TragicItems.Ectoplasm
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.SkyFruit, 1), new Object[] {
			"xxx",
			"xyx",
			"xxx",
			'x', TragicItems.WispParticles,
			'y', TragicItems.ExoticFruit
		});

		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.IceCream, 1), new Object[] {
			" x ",
			"xyx",
			'x', TragicItems.CrushedIce,
			'y', Items.bowl
		});

		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.Rice, 1), new Object[] {
			" x ",
			"xyx",
			'x', Items.wheat,
			'y', Items.bowl
		});

		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.BoneMarrow, 1), new Object[] {
			" x ",
			"xyx",
			'x', new ItemStack(Items.dye, 1, 15),
			'y', Items.bowl
		});

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(TragicItems.Sushi, 6), new Object[] {
			"xxx",
			"yyy",
			'x', "fish",
			'y', "cropRice"
		}));
		
		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.GoldenSushi, 3), new Object[] {
			"xxx",
			"xyx",
			"xxx",
			'x', Blocks.gold_block,
			'y', TragicItems.Sushi
		});

		GameRegistry.addShapelessRecipe(new ItemStack(TragicItems.Ectoplasm, 1), new Object[] {
			TragicItems.WispParticles, TragicItems.LifeWater
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(Blocks.ender_chest, 1), new Object[] {
			"xxx",
			"xyx",
			"xxx",
			'x', Blocks.obsidian,
			'y', TragicItems.PureDarkness
		});

		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.LifeWater, 3), new Object[] {
			" x ",
			"xyx",
			" z ",
			'x', Items.ghast_tear,
			'y', Items.enchanted_book,
			'z', Items.glass_bottle
		});

		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.AquaOrb, 1), new Object[] {
			" x ",
			"xyx",
			" x ",
			'x', TragicItems.LifeWater,
			'y', Items.glass_bottle
		});

		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.FireOrb, 1), new Object[] {
			" x ",
			"xyx",
			" x ",
			'x', new ItemStack(TragicItems.Rock, 1, 1),
			'y', Items.glass_bottle
		});

		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.IceOrb, 1), new Object[] {
			" x ",
			"xyx",
			" x ",
			'x', TragicItems.CrushedIce,
			'y', Items.glass_bottle
		});

		GameRegistry.addShapedRecipe(new ItemStack(Blocks.ice, 3), new Object[] {
			"xxx",
			"xxx",
			"xxx",
			'x', TragicItems.CrushedIce
		});

		GameRegistry.addShapelessRecipe(new ItemStack(TragicItems.Glue, 3), new Object[] {
			TragicItems.Sap, Items.magma_cream, Items.milk_bucket, Items.sugar
		});

		GameRegistry.addShapelessRecipe(new ItemStack(TragicItems.SuperGlue, 1), new Object[] {
			TragicItems.Glue, TragicItems.Sap, Items.clay_ball, Items.nether_wart
		});

		GameRegistry.addShapedRecipe(new ItemStack(TragicBlocks.StorageBlock, 1, 3), new Object[] {
			"xxx",
			"xxx",
			"xxx",
			'x', TragicItems.RedMercury
		});

		GameRegistry.addShapelessRecipe(new ItemStack(TragicItems.RedMercury, 9), new Object[] {
			new ItemStack(TragicBlocks.StorageBlock, 1, 3)
		});

		GameRegistry.addShapedRecipe(new ItemStack(TragicBlocks.StorageBlock, 1, 4), new Object[] {
			"xxx",
			"xxx",
			"xxx",
			'x', TragicItems.QuicksilverIngot
		});

		GameRegistry.addShapelessRecipe(new ItemStack(TragicItems.QuicksilverIngot, 9), new Object[] {
			new ItemStack(TragicBlocks.StorageBlock, 1, 4)
		});

		GameRegistry.addShapedRecipe(new ItemStack(TragicBlocks.StorageBlock, 1, 0), new Object[] {
			"xxx",
			"xxx",
			"xxx",
			'x', TragicItems.Ruby
		});

		GameRegistry.addShapelessRecipe(new ItemStack(TragicItems.Ruby, 9), new Object[] {
			new ItemStack(TragicBlocks.StorageBlock, 1, 0)
		});

		GameRegistry.addShapedRecipe(new ItemStack(TragicBlocks.StorageBlock, 1, 1), new Object[] {
			"xxx",
			"xxx",
			"xxx",
			'x', TragicItems.Sapphire
		});

		GameRegistry.addShapelessRecipe(new ItemStack(TragicItems.Sapphire, 9), new Object[] {
			new ItemStack(TragicBlocks.StorageBlock, 1, 1)
		});

		GameRegistry.addShapedRecipe(new ItemStack(TragicBlocks.StorageBlock, 1, 2), new Object[] {
			"xxx",
			"xxx",
			"xxx",
			'x', TragicItems.Tungsten
		});

		GameRegistry.addShapelessRecipe(new ItemStack(TragicItems.Tungsten, 9), new Object[] {
			new ItemStack(TragicBlocks.StorageBlock, 1, 2)
		});

		GameRegistry.addShapedRecipe(new ItemStack(TragicBlocks.PotatoBlock, 1), new Object[] {
			"xxx",
			"xxx",
			"xxx",
			'x', Items.potato
		});

		GameRegistry.addShapedRecipe(new ItemStack(TragicBlocks.CarrotBlock, 1), new Object[] {
			"xxx",
			"xxx",
			"xxx",
			'x', Items.carrot
		});

		GameRegistry.addShapelessRecipe(new ItemStack(TragicBlocks.Wax, 1), new Object[] {
			TragicItems.LifeWater, TragicItems.LifeWater, Items.clay_ball, Items.clay_ball
		});

		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.Rock, 3), new Object[] {
			"xx",
			"xx",
			'x', Blocks.gravel
		});

		GameRegistry.addShapelessRecipe(new ItemStack(TragicItems.Rock, 3, 1), new Object[] {
			TragicItems.Rock, TragicItems.FireOrb
		});

		GameRegistry.addShapedRecipe(new ItemStack(Items.clay_ball, 3), new Object[] {
			" x ",
			"xyx",
			" x ",
			'x', Blocks.gravel,
			'y', TragicItems.LifeWater
		});

		GameRegistry.addShapedRecipe(new ItemStack(Blocks.cobblestone, 1), new Object[] {
			"xxx",
			"xxx",
			"xxx",
			'x', TragicItems.Rock
		});

		GameRegistry.addShapedRecipe(new ItemStack(Blocks.gravel, 1), new Object[] {
			"xx",
			"xx",
			'x', TragicItems.Rock
		});

		GameRegistry.addShapedRecipe(new ItemStack(Blocks.netherrack, 1), new Object[] {
			"xxx",
			"xxx",
			"xxx",
			'x', new ItemStack(TragicItems.Rock, 1, 1)
		});

		GameRegistry.addShapedRecipe(new ItemStack(TragicBlocks.NetherBrickPressurePlate, 1), new Object[] {
			"xx", 'x', Blocks.nether_brick
		});

		GameRegistry.addShapedRecipe(new ItemStack(TragicBlocks.SandstonePressurePlate, 1), new Object[] {
			"xx", 'x', Blocks.sandstone
		});
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(TragicItems.GuiltyThorn, 1), new Object[] {
			"xyx",
			"xyx",
			"wzw",
			'x', TragicItems.Thorns,
			'y', "materialVine",
			'z', TragicItems.QuicksilverIngot,
			'w', TragicItems.Spore
		}));
		
		GameRegistry.addShapedRecipe(new ItemStack(TragicBlocks.GlowVine, 1), new Object[] {
			"xyx",
			"zyz",
			"xyx",
			'x', Items.glowstone_dust,
			'y', Blocks.vine,
			'z', TragicItems.Spore
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.FrozenLightning, 1), new Object[] {
			"xyx",
			"zyz",
			" x ",
			'x', TragicItems.IceOrb,
			'y', TragicItems.LightningOrb,
			'z', TragicItems.IcyFur
		});

		//Amulets
		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.ChickenAmulet, 1), new Object[] {
			"xyx",
			"yzy",
			"xyx",
			'x', TragicItems.Tungsten,
			'y', Items.feather,
			'z', TragicItems.AwakeningStone
		});

		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.PeaceAmulet, 1), new Object[] {
			"xyx",
			"yzy",
			"xyx",
			'x', TragicItems.Tungsten,
			'y', TragicItems.Sushi,
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.ClaymationAmulet, 1), new Object[] {
			"xyx",
			"yzy",
			"xyx",
			'x', TragicItems.Tungsten,
			'y', TragicItems.LivingClay,
			'z', TragicItems.AwakeningStone
		});

		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.CreeperAmulet, 1), new Object[] {
			"xyx",
			"yzy",
			"xyx",
			'x', TragicItems.Tungsten,
			'y', Items.gunpowder,
			'z', TragicItems.AwakeningStone
		}); 

		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.SkeletonAmulet, 1), new Object[] {
			"xyx",
			"yzy",
			"xyx",
			'x', TragicItems.Tungsten,
			'y', TragicItems.BoneMarrow,
			'z', TragicItems.AwakeningStone
		}); 

		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.ZombieAmulet, 1), new Object[] {
			"xyx",
			"yzy",
			"xyx",
			'x', TragicItems.Tungsten,
			'y', Items.rotten_flesh,
			'z', TragicItems.AwakeningStone
		}); 

		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.ApisAmulet, 1), new Object[] {
			"xyx",
			"yzy",
			"xyx",
			'x', TragicItems.Tungsten,
			'y', TragicItems.LightParticles,
			'z', TragicItems.AwakeningStone
		});

		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.KitsuneAmulet, 1), new Object[] {
			"xyx",
			"yzy",
			"xyx",
			'x', TragicItems.Tungsten,
			'y', TragicItems.KitsuneTail,
			'z', TragicItems.AwakeningStone
		});

		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.SunkenAmulet, 1), new Object[] {
			"xyx",
			"yzy",
			"xyx",
			'x', TragicItems.Tungsten,
			'y', TragicItems.AquaOrb,
			'z', TragicItems.AwakeningStone
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.PiercingAmulet, 1), new Object[] {
			"xyx",
			"yzy",
			"xyx",
			'x', TragicItems.Tungsten,
			'y', TragicItems.Thorns,
			'z', TragicItems.AwakeningStone
		});

		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.BlacksmithAmulet, 1), new Object[] {
			"xyx",
			"yzy",
			"xyx",
			'x', TragicItems.Tungsten,
			'y', Items.flint,
			'z', TragicItems.AwakeningStone
		}); 

		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.MartyrAmulet, 1), new Object[] {
			"xyx",
			"yzy",
			"xyx",
			'x', TragicItems.Tungsten,
			'y', Items.ghast_tear,
			'z', TragicItems.AwakeningStone
		}); 

		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.YetiAmulet, 1), new Object[] {
			"xyx",
			"yzy",
			"xyx",
			'x', TragicItems.Tungsten,
			'y', TragicItems.EmpariahClaw,
			'z', TragicItems.AwakeningStone
		});

		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.TimeAmulet, 1), new Object[] {
			"xyx",
			"yzy",
			"xyx",
			'x', TragicItems.Tungsten,
			'y', TragicItems.TimeEssence,
			'z', TragicItems.AwakeningStone
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.IceAmulet, 1), new Object[] {
			"xyx",
			"yzy",
			"xyx",
			'x', TragicItems.Tungsten,
			'y', TragicItems.IceOrb,
			'z', TragicItems.AwakeningStone
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.SnowGolemAmulet, 1), new Object[] {
			"xyx",
			"yzy",
			"xyx",
			'x', TragicItems.Tungsten,
			'y', Items.snowball,
			'z', TragicItems.AwakeningStone
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.IronGolemAmulet, 1), new Object[] {
			"xyx",
			"yzy",
			"xyx",
			'x', TragicItems.Tungsten,
			'y', Blocks.iron_block,
			'z', TragicItems.AwakeningStone
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.EndermanAmulet, 1), new Object[] {
			"xyx",
			"yzy",
			"xyx",
			'x', TragicItems.Tungsten,
			'y', Items.ender_pearl,
			'z', TragicItems.AwakeningStone
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.WitherAmulet, 1), new Object[] {
			"xyx",
			"yzy",
			"xyx",
			'x', TragicItems.Tungsten,
			'y', Items.nether_star,
			'z', TragicItems.AwakeningStone
		});
		
		GameRegistry.addShapelessRecipe(new ItemStack(TragicBlocks.Quicksand, 3, 0), new Object[] {
			Blocks.sand, TragicItems.LifeWater, TragicItems.LivingClay
		});

		GameRegistry.addShapelessRecipe(new ItemStack(TragicBlocks.Quicksand, 3, 1), new Object[] {
			Blocks.dirt, TragicItems.LifeWater, TragicItems.LivingClay
		});
		
		GameRegistry.addShapelessRecipe(new ItemStack(TragicBlocks.Quicksand, 3, 2), new Object[] {
			Blocks.netherrack, TragicItems.LifeWater, TragicItems.LivingClay
		});
		
		GameRegistry.addRecipe(new ShapedOreRecipe(TragicBlocks.Pulsar, new Object[] {
				"xzx",
				"zyz",
				"xzx",
				'x', "cobblestone",
				'y', TragicItems.GravityOrb,
				'z', Items.iron_ingot
		}));

		GameRegistry.addRecipe(new ShapedOreRecipe(TragicBlocks.Magnetar, new Object[] {
			"xzx",
			"xwx",
			"xzx",
			'x', "stone",
			'z', Blocks.iron_block,
			'w', TragicItems.GravityOrb
		}));
		
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.lit_pumpkin, 1), new Object[] {
			TragicBlocks.Candle, Blocks.pumpkin
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.Jack, 1), new Object[] {
			"xxx",
			" xx",
			"x x",
			'x', TragicItems.Tungsten
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.WitheringAxe, 1), new Object[] {
			"xx ",
			" yx",
			"x x",
			'x', TragicItems.DarkIngot,
			'y', Items.nether_star
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.ReaperScythe, 1), new Object[] {
			"xxz",
			" y ",
			"y  ",
			'x', TragicItems.DarkIngot,
			'y', TragicItems.StinHorn,
			'z', TragicItems.ReaperSkull
		});
		
		GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 3, 15), new Object[] {
			TragicItems.Horn
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.MourningStar, 1), new Object[] {
			"xxx",
			"xyx",
			"zxx",
			'x', TragicItems.StinHorn,
			'y', TragicItems.ReaperSkull,
			'z', TragicItems.DarkIngot
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.BeastlyClaws, 1), new Object[] {
			"xxx",
			"xyx",
			"zyz",
			'x', TragicItems.EmpariahClaw,
			'y', TragicItems.Tungsten,
			'z', TragicItems.IcyFur
		});
		
		GameRegistry.addShapelessRecipe(new ItemStack(Items.string, 9), new Object[] {
			TragicItems.IcyFur, Items.shears
		});
		
		GameRegistry.addShapelessRecipe(new ItemStack(TragicItems.ToughLeather, 3), new Object[] {
			TragicItems.IcyFur, TragicItems.WovenSilk, TragicItems.IcyFur
		});
		
		GameRegistry.addShapelessRecipe(new ItemStack(TragicItems.LunarPowder, 1), new Object[] {
			TragicItems.StarPieces, TragicItems.StarPieces, TragicItems.StarPieces, TragicItems.WispParticles
		});
		
		GameRegistry.addShapelessRecipe(new ItemStack(Items.glowstone_dust, 32), new Object[] {
			TragicItems.StarPieces, TragicItems.WispParticles
		});
		
		GameRegistry.addShapelessRecipe(new ItemStack(Items.redstone, 3), new Object[] {
			TragicItems.WispParticles
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(Items.experience_bottle, 3), new Object[] {
			" x ",
			"xyx",
			" x ",
			'x', Blocks.glass,
			'y', TragicItems.WispParticles
		});
		
		GameRegistry.addShapelessRecipe(new ItemStack(TragicItems.DarkParticles, 3), new Object[] {
			TragicItems.StinHorn
		});
		
		GameRegistry.addShapelessRecipe(new ItemStack(TragicBlocks.TimeDisruptionCube, 1), new Object[] {
			TragicItems.LightIngot, TragicItems.LightIngot, TragicItems.DarkIngot, TragicItems.DarkIngot, TragicItems.LunarPowder, TragicItems.AwakeningStone,
			Items.nether_star, new ItemStack(TragicBlocks.StorageBlock, 1, 0), new ItemStack(TragicBlocks.StorageBlock, 1, 1)
		});
		
		GameRegistry.addShapelessRecipe(new ItemStack(TragicItems.CelestialDiamond, 1), new Object[] {
			TragicItems.LifeWater, TragicItems.LunarPowder, TragicItems.TimeEssence, Items.diamond
		});
		
		GameRegistry.addShapelessRecipe(new ItemStack(TragicItems.DimensionalKey), new Object[] {
			TragicItems.DyingObsidianOrb, TragicItems.CryingObsidianOrb, TragicItems.BleedingObsidianOrb, Items.nether_star
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(TragicBlocks.TragicObsidian, 3, 0), new Object[]{
			"xx",
			"xx",
			'x', TragicItems.CryingObsidianOrb
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(TragicBlocks.TragicObsidian, 3, 1), new Object[]{
			"xx",
			"xx",
			'x', TragicItems.BleedingObsidianOrb
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(TragicBlocks.TragicObsidian, 3, 2), new Object[]{
			"xx",
			"xx",
			'x', TragicItems.DyingObsidianOrb
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(TragicBlocks.SmoothNetherrack, 4, 0), new Object[] {
			"xx",
			"xx",
			'x', Blocks.netherrack
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(TragicBlocks.SmoothNetherrack, 4, 2), new Object[] {
			"xx",
			"xx",
			'x', new ItemStack(TragicBlocks.SmoothNetherrack, 1, 0)
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(TragicBlocks.SmoothNetherrack, 4, 1), new Object[] {
			"xx",
			"xx",
			'x', new ItemStack(TragicBlocks.SmoothNetherrack, 1, 2)
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(TragicBlocks.SmoothNetherrack, 3, 3), new Object[] {
			"x x",
			" x ",
			"x x",
			'x', new ItemStack(TragicBlocks.SmoothNetherrack, 1, 1)
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(TragicBlocks.SmoothNetherrack, 4, 4), new Object[]{
			"xxx",
			"xyx",
			"xxx",
			'x', new ItemStack(TragicBlocks.SmoothNetherrack, 1, 0),
			'y', TragicItems.KitsuneTail
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(TragicBlocks.SmoothNetherrack, 4, 5), new Object[]{
			"xxx",
			"xyx",
			"xxx",
			'x', new ItemStack(TragicBlocks.SmoothNetherrack, 1, 0),
			'y', Items.lava_bucket
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(TragicBlocks.DarkenedQuartz, 4, 0), new Object[] {
			"xyx",
			"yxy",
			"xyx",
			'x', Items.quartz,
			'y', TragicItems.DarkParticles
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(TragicBlocks.DarkenedQuartz, 4, 1), new Object[] {
			"xx",
			"xx",
			'x', TragicBlocks.DarkenedQuartz
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(TragicBlocks.DarkenedQuartz, 2, 2), new Object[] {
			"x",
			"x",
			"x",
			'x', new ItemStack(TragicBlocks.DarkenedQuartz, 1, 1)
		});
		
		for (int i = 0; i < 8; i++)
		{
			GameRegistry.addShapedRecipe(new ItemStack(TragicBlocks.DarkStone, 4, i + 7), new Object[]{
				"xx",
				"xx",
				'x', new ItemStack(TragicBlocks.DarkStone, 1, i)
			});
		}
		
		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.GravitySpike, 1), new Object[]{
			"xyx",
			" x ",
			" x ",
			'x', TragicItems.DarkIngot,
			'y', TragicItems.GravityOrb
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(TragicItems.HarmonyBell, 1), new Object[] {
			" y ",
			"xxx",
			"xxx",
			'x', TragicItems.LightParticles,
			'y', TragicItems.KitsuneTail
		});
		
		GameRegistry.addShapelessRecipe(new ItemStack(Items.ender_eye, 1), new Object[] {
			TragicItems.PureDarkness, TragicItems.ObsidianOrb
		});
		
		GameRegistry.addShapelessRecipe(new ItemStack(TragicBlocks.PaintedPlanks, 4), new Object[] {
			TragicBlocks.PaintedWood
		});
		
		GameRegistry.addShapelessRecipe(new ItemStack(TragicBlocks.BleachedPlanks, 4), new Object[] {
			TragicBlocks.BleachedWood
		});
		
		GameRegistry.addShapelessRecipe(new ItemStack(TragicBlocks.AshenPlanks, 4), new Object[] {
			TragicBlocks.AshenWood
		});
		
		//Flower-to-Dye recipes
		GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 1, 12), new Object[] {
			new ItemStack(TragicBlocks.TragicFlower, 1, 0)
		});
		
		GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 1, 9), new Object[] {
			new ItemStack(TragicBlocks.TragicFlower, 1, 1)
		});
		
		GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 1, 1), new Object[] {
			new ItemStack(TragicBlocks.TragicFlower, 1, 2)
		});
		
		GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 1, 7), new Object[] {
			new ItemStack(TragicBlocks.TragicFlower, 1, 3)
		});
		
		GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 1, 12), new Object[] {
			new ItemStack(TragicBlocks.TragicFlower, 1, 4)
		});
		
		GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 1, 1), new Object[] {
			new ItemStack(TragicBlocks.TragicFlower, 1, 5)
		});
		
		GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 1, 9), new Object[] {
			new ItemStack(TragicBlocks.TragicFlower, 1, 6)
		});
		
		GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 1, 1), new Object[] {
			new ItemStack(TragicBlocks.TragicFlower, 1, 7)
		});
		
		GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 1, 12), new Object[] {
			new ItemStack(TragicBlocks.TragicFlower, 1, 8)
		});
		
		GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 1, 5), new Object[] {
			new ItemStack(TragicBlocks.TragicFlower, 1, 9)
		});
		
		GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 1, 9), new Object[] {
			new ItemStack(TragicBlocks.TragicFlower, 1, 10)
		});
		
		GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 1, 7), new Object[] {
			new ItemStack(TragicBlocks.TragicFlower, 1, 11)
		});
		
		GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 1, 14), new Object[] {
			new ItemStack(TragicBlocks.TragicFlower, 1, 12)
		});
		
		GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 1, 6), new Object[] {
			new ItemStack(TragicBlocks.TragicFlower, 1, 13)
		});
		
		GameRegistry.addShapelessRecipe(new ItemStack(Items.glowstone_dust, 1), new Object[] {
			new ItemStack(TragicBlocks.TragicFlower, 1, 14)
		});
		
		GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 1, 4), new Object[] {
			new ItemStack(TragicBlocks.TragicFlower, 1, 15)
		});
		
		//Star Crystal dye recipes
		for (int i = 0; i < 15; i++)
		{
			GameRegistry.addShapedRecipe(new ItemStack(TragicBlocks.StarCrystal, 4, i), new Object[] {
				"xyx",
				"yxy",
				"xyx",
				'x', new ItemStack(Items.dye, 1, i),
				'y', new ItemStack(TragicBlocks.StarCrystal, 1, 15)
			});
		}
		
		//Amulet level-up recipes
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(TragicItems.BlacksmithAmulet2, 1), new Object[]{
			TragicItems.BlacksmithAmulet, TragicItems.BlacksmithAmulet, "oreCharms"
		}));
		
		GameRegistry.addShapelessRecipe(new ItemStack(TragicItems.BlacksmithAmulet2, 1), new Object[] {
			TragicItems.BlacksmithAmulet, TragicItems.AmuletRelease
		});
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(TragicItems.BlacksmithAmulet3, 1), new Object[]{
			TragicItems.BlacksmithAmulet2, TragicItems.BlacksmithAmulet2, "oreCharms"
		}));
		
		GameRegistry.addShapelessRecipe(new ItemStack(TragicItems.BlacksmithAmulet3, 1), new Object[] {
			TragicItems.BlacksmithAmulet2, TragicItems.AmuletRelease
		});
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(TragicItems.CreeperAmulet2, 1), new Object[]{
			TragicItems.CreeperAmulet, TragicItems.CreeperAmulet, "oreCharms"
		}));
		
		GameRegistry.addShapelessRecipe(new ItemStack(TragicItems.CreeperAmulet2, 1), new Object[] {
			TragicItems.CreeperAmulet, TragicItems.AmuletRelease
		});
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(TragicItems.CreeperAmulet3, 1), new Object[]{
			TragicItems.CreeperAmulet2, TragicItems.CreeperAmulet2, "oreCharms"
		}));
		
		GameRegistry.addShapelessRecipe(new ItemStack(TragicItems.CreeperAmulet3, 1), new Object[] {
			TragicItems.CreeperAmulet2, TragicItems.AmuletRelease
		});
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(TragicItems.ClaymationAmulet2, 1), new Object[]{
			TragicItems.ClaymationAmulet, TragicItems.ClaymationAmulet, "oreCharms"
		}));
		
		GameRegistry.addShapelessRecipe(new ItemStack(TragicItems.ClaymationAmulet2, 1), new Object[] {
			TragicItems.ClaymationAmulet, TragicItems.AmuletRelease
		});
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(TragicItems.ClaymationAmulet3, 1), new Object[]{
			TragicItems.ClaymationAmulet2, TragicItems.ClaymationAmulet2, "oreCharms"
		}));
		
		GameRegistry.addShapelessRecipe(new ItemStack(TragicItems.ClaymationAmulet3, 1), new Object[] {
			TragicItems.ClaymationAmulet2, TragicItems.AmuletRelease
		});
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(TragicItems.SkeletonAmulet2, 1), new Object[]{
			TragicItems.SkeletonAmulet, TragicItems.SkeletonAmulet, "oreCharms"
		}));
		
		GameRegistry.addShapelessRecipe(new ItemStack(TragicItems.SkeletonAmulet2, 1), new Object[] {
			TragicItems.SkeletonAmulet, TragicItems.AmuletRelease
		});
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(TragicItems.SkeletonAmulet3, 1), new Object[]{
			TragicItems.SkeletonAmulet2, TragicItems.SkeletonAmulet2, "oreCharms"
		}));
		
		GameRegistry.addShapelessRecipe(new ItemStack(TragicItems.SkeletonAmulet3, 1), new Object[] {
			TragicItems.SkeletonAmulet2, TragicItems.AmuletRelease
		});
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(TragicItems.ChickenAmulet2, 1), new Object[]{
			TragicItems.ChickenAmulet, TragicItems.ChickenAmulet, "oreCharms"
		}));
		
		GameRegistry.addShapelessRecipe(new ItemStack(TragicItems.ChickenAmulet2, 1), new Object[] {
			TragicItems.ChickenAmulet, TragicItems.AmuletRelease
		});
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(TragicItems.ChickenAmulet3, 1), new Object[]{
			TragicItems.ChickenAmulet2, TragicItems.ChickenAmulet2, "oreCharms"
		}));
		
		GameRegistry.addShapelessRecipe(new ItemStack(TragicItems.ChickenAmulet3, 1), new Object[] {
			TragicItems.ChickenAmulet2, TragicItems.AmuletRelease
		});
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(TragicItems.PeaceAmulet2, 1), new Object[]{
			TragicItems.PeaceAmulet, TragicItems.PeaceAmulet, "oreCharms"
		}));
		
		GameRegistry.addShapelessRecipe(new ItemStack(TragicItems.PeaceAmulet2, 1), new Object[] {
			TragicItems.PeaceAmulet, TragicItems.AmuletRelease
		});
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(TragicItems.PeaceAmulet3, 1), new Object[]{
			TragicItems.PeaceAmulet2, TragicItems.PeaceAmulet2, "oreCharms"
		}));
		
		GameRegistry.addShapelessRecipe(new ItemStack(TragicItems.PeaceAmulet3, 1), new Object[] {
			TragicItems.PeaceAmulet2, TragicItems.AmuletRelease
		});
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(TragicItems.YetiAmulet2, 1), new Object[]{
			TragicItems.YetiAmulet, TragicItems.YetiAmulet, "oreCharms"
		}));
		
		GameRegistry.addShapelessRecipe(new ItemStack(TragicItems.YetiAmulet2, 1), new Object[] {
			TragicItems.YetiAmulet, TragicItems.AmuletRelease
		});
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(TragicItems.YetiAmulet3, 1), new Object[]{
			TragicItems.YetiAmulet2, TragicItems.YetiAmulet2, "oreCharms"
		}));
		
		GameRegistry.addShapelessRecipe(new ItemStack(TragicItems.YetiAmulet3, 1), new Object[] {
			TragicItems.YetiAmulet2, TragicItems.AmuletRelease
		});
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(TragicItems.ZombieAmulet2, 1), new Object[]{
			TragicItems.ZombieAmulet, TragicItems.ZombieAmulet, "oreCharms"
		}));
		
		GameRegistry.addShapelessRecipe(new ItemStack(TragicItems.ZombieAmulet2, 1), new Object[] {
			TragicItems.ZombieAmulet, TragicItems.AmuletRelease
		});
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(TragicItems.ZombieAmulet3, 1), new Object[]{
			TragicItems.ZombieAmulet2, TragicItems.ZombieAmulet2, "oreCharms"
		}));
		
		GameRegistry.addShapelessRecipe(new ItemStack(TragicItems.ZombieAmulet3, 1), new Object[] {
			TragicItems.ZombieAmulet2, TragicItems.AmuletRelease
		});
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(TragicItems.IceAmulet2, 1), new Object[]{
			TragicItems.IceAmulet, TragicItems.IceAmulet, "oreCharms"
		}));
		
		GameRegistry.addShapelessRecipe(new ItemStack(TragicItems.IceAmulet2, 1), new Object[] {
			TragicItems.IceAmulet, TragicItems.AmuletRelease
		});
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(TragicItems.IceAmulet3, 1), new Object[]{
			TragicItems.IceAmulet2, TragicItems.ZombieAmulet2, "oreCharms"
		}));
		
		GameRegistry.addShapelessRecipe(new ItemStack(TragicItems.IceAmulet3, 1), new Object[] {
			TragicItems.IceAmulet2, TragicItems.AmuletRelease
		});
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(TragicItems.SnowGolemAmulet2, 1), new Object[]{
			TragicItems.SnowGolemAmulet, TragicItems.SnowGolemAmulet, "oreCharms"
		}));
		
		GameRegistry.addShapelessRecipe(new ItemStack(TragicItems.SnowGolemAmulet2, 1), new Object[] {
			TragicItems.SnowGolemAmulet, TragicItems.AmuletRelease
		});
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(TragicItems.SnowGolemAmulet3, 1), new Object[]{
			TragicItems.SnowGolemAmulet2, TragicItems.SnowGolemAmulet2, "oreCharms"
		}));
		
		GameRegistry.addShapelessRecipe(new ItemStack(TragicItems.SnowGolemAmulet3, 1), new Object[] {
			TragicItems.SnowGolemAmulet3, TragicItems.AmuletRelease
		});
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(TragicItems.IronGolemAmulet2, 1), new Object[]{
			TragicItems.IronGolemAmulet, TragicItems.IronGolemAmulet, "oreCharms"
		}));
		
		GameRegistry.addShapelessRecipe(new ItemStack(TragicItems.IronGolemAmulet2, 1), new Object[] {
			TragicItems.IronGolemAmulet, TragicItems.AmuletRelease
		});
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(TragicItems.IronGolemAmulet3, 1), new Object[]{
			TragicItems.IronGolemAmulet2, TragicItems.IronGolemAmulet2, "oreCharms"
		}));
		
		GameRegistry.addShapelessRecipe(new ItemStack(TragicItems.IronGolemAmulet3, 1), new Object[] {
			TragicItems.IronGolemAmulet2, TragicItems.AmuletRelease
		});
	}
}
