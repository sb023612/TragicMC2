package tragicneko.tragicmc.worldgen;

import java.util.Random;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.util.ReportedException;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenJungle;
import net.minecraft.world.biome.BiomeGenPlains;
import net.minecraft.world.biome.BiomeGenTaiga;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenDeadBush;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.blocks.BlockTragicFlower;
import tragicneko.tragicmc.dimension.TragicWorldProvider;
import tragicneko.tragicmc.main.TragicBiomes;
import tragicneko.tragicmc.main.TragicBlocks;
import tragicneko.tragicmc.main.TragicNewConfig;
import tragicneko.tragicmc.worldgen.biome.BiomeGenAshenHills;
import tragicneko.tragicmc.worldgen.biome.BiomeGenDecayingWasteland;
import tragicneko.tragicmc.worldgen.biome.TragicBiome;

import com.google.common.collect.Sets;

import cpw.mods.fml.common.IWorldGenerator;

public class FlowerWorldGen implements IWorldGenerator {

	public static Set allowedBiomes = Sets.newHashSet(new BiomeGenBase[]{BiomeGenBase.forest, BiomeGenBase.forestHills, BiomeGenBase.birchForest, BiomeGenBase.birchForestHills,
			BiomeGenBase.jungle, BiomeGenBase.jungleHills, BiomeGenBase.plains, BiomeGenBase.taiga, BiomeGenBase.taigaHills, BiomeGenBase.roofedForest});

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {

		if (world.provider.dimensionId == 0 && !TragicNewConfig.allowExtraOverworldFlowers)
		{
			return;
		}

		if (world.provider.dimensionId != 0 && !(world.provider instanceof TragicWorldProvider))
		{
			return;
		}

		int Xcoord = (chunkX * 16);
		int Zcoord = (chunkZ * 16);
		int Ycoord = world.getTopSolidOrLiquidBlock(Xcoord, Zcoord);
		BiomeGenBase biome = world.getBiomeGenForCoords(Xcoord, Zcoord);
		BlockTragicFlower flower = (BlockTragicFlower) TragicBlocks.TragicFlower;
		boolean bushType = random.nextBoolean();

		if (!allowedBiomes.contains(biome))
		{
			return;
		}

		int meta = random.nextInt(16);

		boolean flag = !(biome instanceof BiomeGenJungle);
		boolean flag2 = !(biome instanceof BiomeGenTaiga); 
		boolean flag3 = !(biome instanceof BiomeGenPlains);
		boolean flag4 = biome != BiomeGenBase.roofedForest;
		boolean flag5 = TragicBiomes.paintedBiomes.contains(biome) && world.provider instanceof TragicWorldProvider;

		if (world.provider.dimensionId == 0) //discriminator based flower generation for the overworld
		{
			if (world.getWorldInfo().getTerrainType() == WorldType.FLAT) return;

			boolean[] discrim = new boolean[16];

			for (int meow = 0; meow < discrim.length; meow++)
			{
				discrim[meow] = true;
			}

			discrim[14] = random.nextInt(50) == 0; //this is the stapelia, it's rare

			if (flag)
			{
				discrim[12] = false;
				discrim[4] = false;
				discrim[5] = false;
			}

			if (flag2)
			{
				discrim[13] = false;
			}

			if (flag3)
			{
				discrim[8] = false;
			}

			if (flag4)
			{
				discrim[6] = false;
				discrim[7] = false;
				discrim[15] = false;
			}

			boolean value = discrim[meta];

			while (!value)
			{
				meta = random.nextInt(16);
				value = discrim[meta];
			}

			for (int i = 0; i < biome.theBiomeDecorator.flowersPerChunk * 4; i++)
			{
				Xcoord += random.nextInt(8) - random.nextInt(8);
				Zcoord += random.nextInt(8) - random.nextInt(8);
				Ycoord += random.nextInt(2) - random.nextInt(2);

				if (world.isAirBlock(Xcoord, Ycoord, Zcoord) && (!world.provider.hasNoSky || Ycoord < 255) && flower.canBlockStay(world, Xcoord, Ycoord, Zcoord))
				{
					world.setBlock(Xcoord, Ycoord, Zcoord, flower, meta, 2);
				}
			}
		}
		else
		{

			if (!(biome instanceof TragicBiome)) return;
			TragicBiome trBiome = (TragicBiome) biome;

			if (flag5) meta = 4;

			for (int i = 0; i < trBiome.getFlowersFromBiomeType(); i++)
			{
				Xcoord += random.nextInt(8) - random.nextInt(8);
				Zcoord += random.nextInt(8) - random.nextInt(8);
				Ycoord += random.nextInt(2) - random.nextInt(2);

				if (world.isAirBlock(Xcoord, Ycoord, Zcoord) && (!world.provider.hasNoSky || Ycoord < 255) && flower.canBlockStay(world, Xcoord, Ycoord, Zcoord))
				{
					world.setBlock(Xcoord, Ycoord, Zcoord, flower, meta, 2);
				}
			}

			Block bush = bushType ? TragicBlocks.AshenBush : TragicBlocks.DeadBush;

			if (trBiome instanceof BiomeGenAshenHills)
			{
				for (int i = 0; i < trBiome.getBushesFromBiomeType(); i++)
				{
					Xcoord += random.nextInt(8) - random.nextInt(8);
					Zcoord += random.nextInt(8) - random.nextInt(8);
					Ycoord += random.nextInt(2) - random.nextInt(2);

					new WorldGenDeadBush(bush).generate(world, random, Xcoord, Ycoord, Zcoord);
				}
			}
			else if (trBiome instanceof BiomeGenDecayingWasteland)
			{
				for (int i = 0; i < trBiome.getBushesFromBiomeType(); i++)
				{
					Xcoord += random.nextInt(8) - random.nextInt(8);
					Zcoord += random.nextInt(8) - random.nextInt(8);
					Ycoord += random.nextInt(2) - random.nextInt(2);

					new WorldGenDeadBush(TragicBlocks.DeadBush).generate(world, random, Xcoord, Ycoord, Zcoord);
				}
			}

		}
	}

}
