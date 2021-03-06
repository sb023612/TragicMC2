package tragicneko.tragicmc.dimension;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.util.WeightedRandom;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerVoronoiZoom;
import net.minecraft.world.gen.layer.IntCache;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import tragicneko.tragicmc.main.TragicBiomes;

public class TragicBiomeGenLayer extends GenLayer {

	private static List<BiomeEntry> decayingBiomes = new ArrayList<BiomeEntry>();
	private static List<BiomeEntry> paintedBiomes = new ArrayList<BiomeEntry>();
	private static List<BiomeEntry> ashenBiomes = new ArrayList<BiomeEntry>();
	private static List<BiomeEntry> starlitBiomes = new ArrayList<BiomeEntry>();
	private static List<BiomeEntry> taintedBiomes = new ArrayList<BiomeEntry>();

	private static List[] biomeSets = new ArrayList[4];

	public TragicBiomeGenLayer(long seed, GenLayer genlayer)
	{
		this(seed);
		this.parent = genlayer;
	}

	public TragicBiomeGenLayer(long seed)
	{
		super(seed);

		decayingBiomes.add(new BiomeEntry(TragicBiomes.DecayingWasteland, 20));
		decayingBiomes.add(new BiomeEntry(TragicBiomes.DecayingHills, 15));
		decayingBiomes.add(new BiomeEntry(TragicBiomes.DecayingMountains, 10));
		decayingBiomes.add(new BiomeEntry(TragicBiomes.DecayingValley, 15));

		paintedBiomes.add(new BiomeEntry(TragicBiomes.PaintedForest, 30));
		paintedBiomes.add(new BiomeEntry(TragicBiomes.PaintedHills, 20));
		paintedBiomes.add(new BiomeEntry(TragicBiomes.PaintedPlains, 10));
		paintedBiomes.add(new BiomeEntry(TragicBiomes.PaintedClearing, 5));

		ashenBiomes.add(new BiomeEntry(TragicBiomes.AshenBadlands, 20));
		ashenBiomes.add(new BiomeEntry(TragicBiomes.AshenHills, 10));
		ashenBiomes.add(new BiomeEntry(TragicBiomes.AshenMountains, 5));
		
		starlitBiomes.add(new BiomeEntry(TragicBiomes.StarlitPrarie, 25));
		starlitBiomes.add(new BiomeEntry(TragicBiomes.StarlitPlateaus, 15));
		starlitBiomes.add(new BiomeEntry(TragicBiomes.StarlitLowlands, 10));
		starlitBiomes.add(new BiomeEntry(TragicBiomes.StarlitCliffs, 5));
		
		taintedBiomes.add(new BiomeEntry(TragicBiomes.TaintedArchipelago, 20));
		taintedBiomes.add(new BiomeEntry(TragicBiomes.TaintedSpikes, 15));
		taintedBiomes.add(new BiomeEntry(TragicBiomes.TaintedIsles, 5));
		taintedBiomes.add(new BiomeEntry(TragicBiomes.TaintedRises, 15));
		taintedBiomes.add(new BiomeEntry(TragicBiomes.TaintedScarlands, 25));

		biomeSets[0] = decayingBiomes;
		biomeSets[1] = paintedBiomes;
		biomeSets[2] = ashenBiomes;
		biomeSets[3] = starlitBiomes;
	}

	@Override
	public int[] getInts(int x, int z, int width, int depth)
	{
		int[] dest = IntCache.getIntCache(width * depth);

		for (int dz = 0; dz < depth; dz++)
		{
			for (int dx = 0; dx < width; dx++)
			{
				this.initChunkSeed(dx + x, dz + z);
				List<BiomeEntry> biomes = biomeSets[this.nextInt(biomeSets.length)];

				dest[dx + dz * width] = ((BiomeEntry)WeightedRandom.getItem(biomes, (int)(this.nextLong(WeightedRandom.getTotalWeight(biomes) / 10) * 10))).biome.biomeID;
			}
		}

		return dest;
	}
}
