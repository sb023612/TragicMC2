package tragicneko.tragicmc.worldgen.schematic;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.main.TragicBlocks;
import tragicneko.tragicmc.main.TragicItems;
import tragicneko.tragicmc.main.TragicNewConfig;

public class SchematicKitsuneDen extends Schematic {

	private static Block fox = TragicBlocks.SmoothNetherrack; //1 is chiseled, 2 is beveled, 3 is sculpted, 5 is molten
	private static Block chest = Blocks.chest;
	private static Block spawner = Blocks.mob_spawner;
	private static Block summon = TragicBlocks.SummonBlock; //5 is the kitsune

	public SchematicKitsuneDen(int variant, World world, Random rand, int x, int y, int z) {
		super(variant, world, rand, x, y, z);
	}

	@Override
	public void generateStructure(int variant, World world, Random rand, int x,
			int y, int z) {
		switch(variant)
		{
		case 0:
			generateWithoutVariation(world, rand, x, y, z);
			break;
		default:
			TragicMC.logError("There was a problem generating a Kitsune Den.");
			break;
		}

	}

	@Override
	public void generateWithoutVariation(World world, Random rand, int x, int y, int z) {

		int dens = rand.nextInt(5); //the extra number of dens to generate in addition to the main one

		for (int y1 = 0; y1 < 8; y1++)
		{
			for (int x1 = -5; x1 < 6; x1++)
			{
				for (int z1 = -5; z1 < 6; z1++)
				{
					if (y1 == 0)
					{
						world.setBlock(x + x1, y + y1, z + z1, fox, 1, 2);
					}
					else
					{
						world.setBlockToAir(x + x1, y + y1, z + z1);
					}
				}
			}
		}

		for (int y1 = -1; y1 < 2; y1++) //sets the middle inset layer of sculpted blocks for the spawner or summon block
		{
			for (int x1 = -2; x1 < 3; x1++)
			{
				for (int z1 = -2; z1 < 3; z1++)
				{
					if (y1 == -1)
					{
						world.setBlock(x + x1, y + y1, z + z1, fox, 3, 2);
					}
					else
					{
						world.setBlockToAir(x + x1, y + y1, z + z1);
					}
				}
			}
		}

		world.setBlock(x + 2, y, z + 2, fox, 3, 2); //sets the blocks on the lower layer to round it out
		world.setBlock(x - 2, y, z + 2, fox, 3, 2);
		world.setBlock(x + 2, y, z - 2, fox, 3, 2);
		world.setBlock(x - 2, y, z - 2, fox, 3, 2);

		world.setBlock(x, y, z, chest, 0, 2);
		this.applyChestContents(world, rand, x, y, z);

		world.setBlock(x, y + 1, z, spawner, 0, 2);

		TileEntity tile = world.getTileEntity(x, y + 1, z);

		if (tile != null && tile instanceof TileEntityMobSpawner) //sets up the spawner to be a Kitsune spawner
		{
			TileEntityMobSpawner spawner = (TileEntityMobSpawner) tile;
			String s = TragicNewConfig.allowKitsune ? "TragicMC.Kitsune" : "Blaze"; //sets it to a blaze spawner if kitsune is disabled
			spawner.func_145881_a().setEntityName(s);
		}

		for (int z1 = -1; z1 < 2; z1++) //sets the extra sculpted blocks on the 0 layer
		{
			world.setBlock(x + 3, y, z + z1, fox, 3, 2);
			world.setBlock(x - 3, y, z + z1, fox, 3, 2);
			world.setBlock(x + z1, y, z + 3, fox, 3, 2);
			world.setBlock(x + z1, y, z - 3, fox, 3, 2);
		}

		for (int z1 = -5; z1 < 6; z1++) //Sets the outer blocks as beveled
		{
			world.setBlock(x + 5, y, z + z1, fox, 2, 2);
			world.setBlock(x - 5, y, z + z1, fox, 2, 2);
			world.setBlock(x + z1, y, z + 5, fox, 2, 2);
			world.setBlock(x + z1, y, z - 5, fox, 2, 2);
		}

		world.setBlock(x + 4, y, z + 3, fox, 2, 2); //finishes setting the outer beveled netherrack
		world.setBlock(x + 4, y, z - 3, fox, 2, 2);
		world.setBlock(x - 4, y, z + 3, fox, 2, 2);
		world.setBlock(x - 4, y, z - 3, fox, 2, 2);
		world.setBlock(x + 3, y, z + 4, fox, 2, 2);
		world.setBlock(x + 3, y, z - 4, fox, 2, 2);
		world.setBlock(x - 3, y, z + 4, fox, 2, 2);
		world.setBlock(x - 3, y, z - 4, fox, 2, 2);


		for (int z1 = -5; z1 < -2; z1++) //Removes the corners
		{
			world.setBlockToAir(x + 5, y, z + z1);
			world.setBlockToAir(x - 5, y, z + z1);
			world.setBlockToAir(x + z1, y, z + 5);
			world.setBlockToAir(x + z1, y, z - 5);
		}

		for (int z1 = 3; z1 < 6; z1++) //Removes the opposite corners
		{
			world.setBlockToAir(x + 5, y, z + z1);
			world.setBlockToAir(x - 5, y, z + z1);
			world.setBlockToAir(x + z1, y, z + 5);
			world.setBlockToAir(x + z1, y, z - 5);
		}

		world.setBlockToAir(x + 4, y, z + 4); //finishes rounding out the corners
		world.setBlockToAir(x - 4, y, z + 4);
		world.setBlockToAir(x + 4, y, z - 4);
		world.setBlockToAir(x - 4, y, z - 4);

		for (int y1 = 1; y1 < 5; y1++)
		{
			world.setBlock(x + 5, y + y1, z + 3, fox, 2, 2); //sets the outer beveled edges
			world.setBlock(x - 5, y + y1, z + 3, fox, 2, 2);
			world.setBlock(x + 5, y + y1, z - 3, fox, 2, 2);
			world.setBlock(x - 5, y + y1, z - 3, fox, 2, 2);
			world.setBlock(x + 3, y + y1, z + 5, fox, 2, 2);
			world.setBlock(x - 3, y + y1, z + 5, fox, 2, 2);
			world.setBlock(x + 3, y + y1, z - 5, fox, 2, 2);
			world.setBlock(x - 3, y + y1, z - 5, fox, 2, 2);

			for (int z1 = -2; z1 < 3; z1++) //sets the large outer smooth netherrack
			{
				world.setBlock(x + 6, y + y1, z + z1, fox);
				world.setBlock(x - 6, y + y1, z + z1, fox);
				world.setBlock(x + z1, y + y1, z + 6, fox);
				world.setBlock(x + z1, y + y1, z - 6, fox);
			}

			world.setBlock(x + 4, y + y1, z + 4, fox); //these are for the spaces in between the columns
			world.setBlock(x + 4, y + y1, z - 4, fox);
			world.setBlock(x - 4, y + y1, z + 4, fox);
			world.setBlock(x - 4, y + y1, z - 4, fox);
		}

		for (int z1 = -2; z1 < 3; z1++) //sets the upper layer of beveled netherrack
		{
			world.setBlock(x + 5, y + 5, z + z1, fox, 2, 2);
			world.setBlock(x - 5, y + 5, z + z1, fox, 2, 2);
			world.setBlock(x + z1, y + 5, z + 5, fox, 2, 2);
			world.setBlock(x + z1, y + 5, z - 5, fox, 2, 2);
		}

		world.setBlock(x + 4, y + 5, z + 3, fox, 2, 2); //finishes setting the upper outer beveled netherrack
		world.setBlock(x + 4, y + 5, z - 3, fox, 2, 2);
		world.setBlock(x - 4, y + 5, z + 3, fox, 2, 2);
		world.setBlock(x - 4, y + 5, z - 3, fox, 2, 2);
		world.setBlock(x + 3, y + 5, z + 4, fox, 2, 2);
		world.setBlock(x + 3, y + 5, z - 4, fox, 2, 2);
		world.setBlock(x - 3, y + 5, z + 4, fox, 2, 2);
		world.setBlock(x - 3, y + 5, z - 4, fox, 2, 2);


		for (int x1 = -2; x1 < 3; x1++) //Sets all of the blocks for the top layer, to be replaced
		{
			for (int z1 = -4; z1 < 5; z1++)
			{
				world.setBlock(x + x1, y + 5, z + z1, fox, 1, 2);
			}
		}

		for (int x1 = -4; x1 < 5; x1++)
		{
			for (int z1 = -2; z1 < 3; z1++)
			{
				world.setBlock(x + x1, y + 5, z + z1, fox, 1, 2);
			}
		}

		world.setBlock(x + 3, y + 5, z + 3, fox, 1, 2); //Fills in the last four blocks to finish the top
		world.setBlock(x - 3, y + 5, z + 3, fox, 1, 2);
		world.setBlock(x + 3, y + 5, z - 3, fox, 1, 2);
		world.setBlock(x - 3, y + 5, z - 3, fox, 1, 2); 

		for (int y1 = 5; y1 < 7; y1++)
		{
			for (int x1 = -2; x1 < 3; x1++)
			{
				for (int z1 = -2; z1 < 3; z1++)
				{
					if (y1 == 5)
					{
						world.setBlockToAir(x + x1, y + y1, z + z1);
					}
					else
					{
						world.setBlock(x + x1, y + y1, z + z1, fox, 3, 2);
					}
				}
			}
		}

		for (int z1 = -1; z1 < 2; z1++) //sets the extra sculpted blocks on the upper layer
		{
			world.setBlock(x + 3, y + 5, z + z1, fox, 3, 2);
			world.setBlock(x - 3, y + 5, z + z1, fox, 3, 2);
			world.setBlock(x + z1, y + 5, z + 3, fox, 3, 2);
			world.setBlock(x + z1, y + 5, z - 3, fox, 3, 2);
		}

		world.setBlock(x + 2, y + 5, z + 2, fox, 3, 2); //sets the blocks on the upper layer to round it out
		world.setBlock(x - 2, y + 5, z + 2, fox, 3, 2);
		world.setBlock(x + 2, y + 5, z - 2, fox, 3, 2);
		world.setBlock(x - 2, y + 5, z - 2, fox, 3, 2);

		world.setBlock(x, y + 6, z, fox, 5, 2); //sets the one light block in the middle on top, lights it up just enough to see but not enough to prevent spawning

		if (dens > 0)
		{
			this.generateVariant(world, rand, x + 11, y, z);

			for (int y1 = 1; y1 < 3; y1++) //generates the doorway to the extra den
			{
				for (int z1 = -1; z1 < 2; z1++)
				{
					world.setBlockToAir(x + 6, y + y1, z + z1);
					world.setBlock(x + 6, y, z + z1, fox, 2, 2);
				}
			}
			world.setBlockToAir(x + 6, y + 3, z);

			if (dens > 1)
			{
				this.generateVariant(world, rand, x - 11, y, z);

				for (int y1 = 1; y1 < 3; y1++) //generates the doorway to the extra den
				{
					for (int z1 = -1; z1 < 2; z1++)
					{
						world.setBlockToAir(x - 6, y + y1, z + z1);
						world.setBlock(x - 6, y, z + z1, fox, 2, 2);
					}
				}
				world.setBlockToAir(x - 6, y + 3, z);

				if (dens > 2)
				{
					this.generateVariant(world, rand, x, y, z + 11);

					for (int y1 = 1; y1 < 3; y1++) //generates the doorway to the extra den
					{
						for (int z1 = -1; z1 < 2; z1++)
						{
							world.setBlockToAir(x + z1, y + y1, z + 6);
							world.setBlock(x + z1, y, z + 6, fox, 2, 2);
						}
					}
					world.setBlockToAir(x, y + 3, z + 6);

					if (dens > 3)
					{
						this.generateVariant(world, rand, x, y, z - 11);

						for (int y1 = 1; y1 < 3; y1++) //generates the doorway to the extra den
						{
							for (int z1 = -1; z1 < 2; z1++)
							{
								world.setBlockToAir(x + z1, y + y1, z - 6);
								world.setBlock(x + z1, y, z - 6, fox, 2, 2);
							}
						}
						world.setBlockToAir(x, y + 3, z - 6);
					}
				}
			}
		}
	}

	@Override
	public void generateVariant(World world, Random rand, int x, int y, int z) {

		for (int y1 = 0; y1 < 6; y1++)
		{
			for (int x1 = -4; x1 < 5; x1++)
			{
				for (int z1 = -4; z1 < 5; z1++)
				{
					if (y1 == 0)
					{
						world.setBlock(x + x1, y + y1, z + z1, fox, 1, 2);
					}
					else
					{
						world.setBlockToAir(x + x1, y + y1, z + z1);
					}
				}
			}
		}

		for (int y1 = -1; y1 < 2; y1++) //sets the middle inset layer of sculpted blocks for the spawner or summon block
		{
			for (int x1 = -1; x1 < 2; x1++)
			{
				for (int z1 = -1; z1 < 2; z1++)
				{
					if (y1 == -1)
					{
						world.setBlock(x + x1, y + y1, z + z1, fox, 3, 2);
					}
					else
					{
						world.setBlockToAir(x + x1, y + y1, z + z1);
					}
				}
			}
		}

		world.setBlock(x + 1, y, z + 1, fox, 3, 2); //sets the blocks on the lower layer to round it out
		world.setBlock(x - 1, y, z + 1, fox, 3, 2);
		world.setBlock(x + 1, y, z - 1, fox, 3, 2);
		world.setBlock(x - 1, y, z - 1, fox, 3, 2);

		world.setBlock(x, y, z, chest, 0, 2);
		this.applyChestContents(world, rand, x, y, z);

		world.setBlock(x, y + 1, z, spawner, 0, 2);

		TileEntity tile = world.getTileEntity(x, y + 1, z);

		if (tile != null && tile instanceof TileEntityMobSpawner) //sets up the spawner to be a Jabba spawner
		{
			TileEntityMobSpawner spawner = (TileEntityMobSpawner) tile;
			String s = TragicNewConfig.allowJabba ? "TragicMC.Jabba" : "Blaze"; //if Jabba is disabled, sets them as blaze spawners instead
			spawner.func_145881_a().setEntityName(s);
		}


		world.setBlock(x + 1, y, z, fox, 3, 2);
		world.setBlock(x - 1, y, z, fox, 3, 2);
		world.setBlock(x, y, z + 1, fox, 3, 2);
		world.setBlock(x, y, z - 1, fox, 3, 2);

		world.setBlock(x + 1, y, z + 1, fox, 3, 2);
		world.setBlock(x - 1, y, z + 1, fox, 3, 2);
		world.setBlock(x + 1, y, z + 1, fox, 3, 2);
		world.setBlock(x + 1, y, z - 1, fox, 3, 2);


		for (int z1 = -1; z1 < 2; z1++) //Sets the outer blocks as beveled
		{
			world.setBlock(x + 4, y, z + z1, fox, 2, 2);
			world.setBlock(x - 4, y, z + z1, fox, 2, 2);
			world.setBlock(x + z1, y, z + 4, fox, 2, 2);
			world.setBlock(x + z1, y, z - 4, fox, 2, 2);
		}


		world.setBlock(x + 3, y, z + 2, fox, 2, 2); //finishes setting the outer beveled netherrack
		world.setBlock(x + 3, y, z - 2, fox, 2, 2);
		world.setBlock(x - 3, y, z + 2, fox, 2, 2);
		world.setBlock(x - 3, y, z - 2, fox, 2, 2);
		world.setBlock(x + 2, y, z + 3, fox, 2, 2);
		world.setBlock(x + 2, y, z - 3, fox, 2, 2);
		world.setBlock(x - 2, y, z + 3, fox, 2, 2);
		world.setBlock(x - 2, y, z - 3, fox, 2, 2);

		for (int y1 = 1; y1 < 5; y1++) //sets up the walls
		{
			world.setBlock(x + 4, y + y1, z + 2, fox, 2, 2); //sets the outer beveled edges
			world.setBlock(x - 4, y + y1, z + 2, fox, 2, 2);
			world.setBlock(x + 4, y + y1, z - 2, fox, 2, 2);
			world.setBlock(x - 4, y + y1, z - 2, fox, 2, 2);
			world.setBlock(x + 2, y + y1, z + 4, fox, 2, 2);
			world.setBlock(x - 2, y + y1, z + 4, fox, 2, 2);
			world.setBlock(x + 2, y + y1, z - 4, fox, 2, 2);
			world.setBlock(x - 2, y + y1, z - 4, fox, 2, 2);

			for (int z1 = -1; z1 < 2; z1++) //sets the large outer smooth netherrack
			{
				world.setBlock(x + 5, y + y1, z + z1, fox);
				world.setBlock(x - 5, y + y1, z + z1, fox);
				world.setBlock(x + z1, y + y1, z + 5, fox);
				world.setBlock(x + z1, y + y1, z - 5, fox);
			}

			world.setBlock(x + 3, y + y1, z + 3, fox); //these are for the spaces in between the columns
			world.setBlock(x + 3, y + y1, z - 3, fox);
			world.setBlock(x - 3, y + y1, z + 3, fox);
			world.setBlock(x - 3, y + y1, z - 3, fox);
		}

		for (int z1 = -1; z1 < 2; z1++) //sets the upper layer of beveled netherrack
		{
			world.setBlock(x + 4, y + 5, z + z1, fox, 2, 2);
			world.setBlock(x - 4, y + 5, z + z1, fox, 2, 2);
			world.setBlock(x + z1, y + 5, z + 4, fox, 2, 2);
			world.setBlock(x + z1, y + 5, z - 4, fox, 2, 2);
		}

		world.setBlock(x + 3, y + 5, z + 2, fox, 2, 2); //finishes setting the upper outer beveled netherrack
		world.setBlock(x + 3, y + 5, z - 2, fox, 2, 2);
		world.setBlock(x - 3, y + 5, z + 2, fox, 2, 2);
		world.setBlock(x - 3, y + 5, z - 2, fox, 2, 2);
		world.setBlock(x + 2, y + 5, z + 3, fox, 2, 2);
		world.setBlock(x + 2, y + 5, z - 3, fox, 2, 2);
		world.setBlock(x - 2, y + 5, z + 3, fox, 2, 2);
		world.setBlock(x - 2, y + 5, z - 3, fox, 2, 2);


		for (int x1 = -1; x1 < 2; x1++) //Sets all of the blocks for the top layer, to be replaced
		{
			for (int z1 = -3; z1 < 4; z1++)
			{
				world.setBlock(x + x1, y + 5, z + z1, fox, 1, 2);
			}
		}

		for (int x1 = -3; x1 < 4; x1++)
		{
			for (int z1 = -1; z1 < 2; z1++)
			{
				world.setBlock(x + x1, y + 5, z + z1, fox, 1, 2);
			}
		}

		world.setBlock(x + 2, y + 5, z + 2, fox, 1, 2); //Fills in the last four blocks to finish the top
		world.setBlock(x - 2, y + 5, z + 2, fox, 1, 2);
		world.setBlock(x + 2, y + 5, z - 2, fox, 1, 2);
		world.setBlock(x - 2, y + 5, z - 2, fox, 1, 2); 

		for (int y1 = 5; y1 < 7; y1++)
		{
			for (int x1 = -1; x1 < 2; x1++)
			{
				for (int z1 = -1; z1 < 2; z1++)
				{
					if (y1 == 5)
					{
						world.setBlockToAir(x + x1, y + y1, z + z1);
					}
					else
					{
						world.setBlock(x + x1, y + y1, z + z1, fox, 3, 2);
					}
				}
			}
		}

		world.setBlock(x + 2, y + 5, z, fox, 3, 2);
		world.setBlock(x - 2, y + 5, z, fox, 3, 2);
		world.setBlock(x, y + 5, z + 2, fox, 3, 2);
		world.setBlock(x, y + 5, z - 2, fox, 3, 2);

		world.setBlock(x + 2, y + 5, z + 1, fox, 3, 2);
		world.setBlock(x - 2, y + 5, z + 1, fox, 3, 2);
		world.setBlock(x + 1, y + 5, z + 2, fox, 3, 2);
		world.setBlock(x + 1, y + 5, z - 2, fox, 3, 2);


		world.setBlock(x + 1, y + 5, z + 1, fox, 3, 2); //sets the blocks on the upper layer to round it out
		world.setBlock(x - 1, y + 5, z + 1, fox, 3, 2);
		world.setBlock(x + 1, y + 5, z - 1, fox, 3, 2);
		world.setBlock(x - 1, y + 5, z - 1, fox, 3, 2);

		world.setBlock(x, y + 6, z, fox, 5, 2); //sets the one light block in the middle on top, lights it up just enough to see but not enough to prevent spawning
	}

	@Override
	public void applyChestContents(World world, Random rand, int x, int y, int z) {
		TileEntity tileentity = world.getTileEntity(x, y, z);

		if (tileentity != null && tileentity instanceof TileEntityChest)
		{
			TileEntityChest chest = (TileEntityChest) tileentity;
			WeightedRandomChestContent.generateChestContents(rand, TragicItems.NetherStructureHook.getItems(rand), chest, TragicItems.NetherStructureHook.getCount(rand));
		}

	}

}
