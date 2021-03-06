package tragicneko.tragicmc.items.special;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.BlockLiquid;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenCanopyTree;
import net.minecraft.world.gen.feature.WorldGenForest;
import net.minecraft.world.gen.feature.WorldGenMegaJungle;
import net.minecraft.world.gen.feature.WorldGenMegaPineTree;
import net.minecraft.world.gen.feature.WorldGenSavannaTree;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;
import tragicneko.tragicmc.TragicMC;
import tragicneko.tragicmc.main.TragicBlocks;
import tragicneko.tragicmc.main.TragicItems;
import tragicneko.tragicmc.main.TragicTabs;
import tragicneko.tragicmc.util.WorldHelper;
import tragicneko.tragicmc.worldgen.CustomSpikesWorldGen;
import tragicneko.tragicmc.worldgen.WorldGenAshenTree;
import tragicneko.tragicmc.worldgen.WorldGenBleachedTree;
import tragicneko.tragicmc.worldgen.WorldGenLargePaintedTree;
import tragicneko.tragicmc.worldgen.WorldGenPaintedTree;

public class ItemGenerator extends Item {

	public ItemGenerator()
	{
		super();
		this.setCreativeTab(TragicTabs.Creative);
	}

	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
	{
		par2List.add("Generate some of the WorldGen features!");
		par2List.add("Some of this is really CPU intensive.");
	}

	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) 
	{
		if (world.isRemote) return stack;
		Random random = TragicMC.rand;

		double size;

		int[] coords;
		ArrayList<int[]> list;
		
		double distance = 50.0D;
		float f = 1.0F;
		float f1 = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * f;
		float f2 = player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * f;
		double d0 = player.prevPosX + (player.posX - player.prevPosX) * (double)f;
		double d1 = player.prevPosY + (player.posY - player.prevPosY) * (double)f + (double)(player.worldObj.isRemote ? player.getEyeHeight() - player.getDefaultEyeHeight() : player.getEyeHeight()); // isRemote check to revert changes to ray trace position due to adding the eye height clientside and player yOffset differences
		double d2 = player.prevPosZ + (player.posZ - player.prevPosZ) * (double)f;
		Vec3 vec3 = Vec3.createVectorHelper(d0, d1, d2);
		float f3 = MathHelper.cos(-f2 * 0.017453292F - (float)Math.PI);
		float f4 = MathHelper.sin(-f2 * 0.017453292F - (float)Math.PI);
		float f5 = -MathHelper.cos(-f1 * 0.017453292F);
		float f6 = MathHelper.sin(-f1 * 0.017453292F);
		float f7 = f4 * f5;
		float f8 = f3 * f5;
		double d3 = distance;

		if (player instanceof EntityPlayerMP)
		{
			d3 = ((EntityPlayerMP)player).theItemInWorldManager.getBlockReachDistance() + (d3 - 4.0D);
		}
		Vec3 vec31 = vec3.addVector((double)f7 * d3, (double)f6 * d3, (double)f8 * d3);

		MovingObjectPosition mop = player.worldObj.func_147447_a(vec3, vec31, true, false, true);

		if (mop == null) return stack;

		int Xcoord = mop.blockX;
		int Ycoord = mop.blockY;
		int Zcoord = mop.blockZ;

		if (this == TragicItems.VoidPitGenerator)
		{
			size = 15.0D * random.nextDouble() + 10.0D;

			for (int pow = 0; pow + Ycoord >= 0 && pow + Ycoord <= 256; --pow)
			{
				if (size >= 5.5D)
				{
					list = WorldHelper.getBlocksInCircularRange(world, size * 0.31773D, Xcoord, Ycoord + pow, Zcoord); //makes sure the middle of the pit is clear

					for (int mapping = 0; mapping < list.size(); mapping++)
					{
						coords = list.get(mapping);
						if (random.nextInt(2) != 0) world.setBlock(coords[0], coords[1], coords[2], Blocks.air);
					} 
				}

				list = WorldHelper.getBlocksInCircularRange(world, size * 0.64773D, Xcoord, Ycoord + pow, Zcoord); //gives the pit more of a gradual feel

				for (int mapping = 0; mapping < list.size(); mapping++)
				{
					coords = list.get(mapping);
					if (random.nextInt(2) != 0) world.setBlock(coords[0], coords[1], coords[2], Blocks.air);
				} 

				list = WorldHelper.getBlocksInCircularRange(world, size, Xcoord, Ycoord + pow, Zcoord); //outer part that has the most scattered blocks

				for (int mapping = 0; mapping < list.size(); mapping++)
				{
					coords = list.get(mapping);
					if (random.nextInt(2) != 0) world.setBlock(coords[0], coords[1], coords[2], Blocks.air);
				}
			}

			player.addChatMessage(new ChatComponentText(EnumChatFormatting.ITALIC + "Void pit generated with size of " + size));
		}
		else if (this == TragicItems.SphereGenerator)
		{
			size = 5.0D * random.nextDouble() + 2.5D;
			Block ablock;
			list = WorldHelper.getBlocksInSphericalRange(world, size, Xcoord, Ycoord, Zcoord);

			ablock = Block.getBlockById(random.nextInt(4096));
			int attempts = 0;

			while (!ablock.isOpaqueCube() && !(ablock instanceof BlockBreakable) || ablock.hasTileEntity(0) || ablock instanceof BlockFalling)
			{
				ablock = Block.getBlockById(random.nextInt(4096));
				attempts++;

				if (attempts > 40)
				{
					break;
				}
			} 

			if (!ablock.isOpaqueCube() && !(ablock instanceof BlockBreakable)|| ablock.hasTileEntity(0) || ablock instanceof BlockFalling)
			{
				ablock = Blocks.tnt;
			}

			for (int i = 0; i < list.size(); i++)
			{
				coords = list.get(i);
				world.setBlock(coords[0], coords[1], coords[2], ablock);
			}

			if (!list.isEmpty() && ablock != null)
			{
				String s = ablock.getUnlocalizedName();
				player.addChatMessage(new ChatComponentText(EnumChatFormatting.ITALIC + "Sphere generated with size of " + size + " made of " + StatCollector.translateToLocal(s + ".name")));
			}
		}
		else if (this == TragicItems.SphereEraser)
		{
			list = WorldHelper.getBlocksInSphericalRange(world, 6.5D, Xcoord, Ycoord, Zcoord);

			for (int i = 0; i < list.size(); i++)
			{
				coords = list.get(i);
				world.setBlockToAir(coords[0], coords[1], coords[2]);
			}

			if (!list.isEmpty())
			{
				player.addChatMessage(new ChatComponentText(EnumChatFormatting.ITALIC + "Spherical area erased."));
			}
		}
		else if (this == TragicItems.LiquidRemover)
		{
			list = WorldHelper.getBlocksInSphericalRange(world, 6.5D, Xcoord, Ycoord, Zcoord);

			for (int i = 0; i < list.size(); i++)
			{
				coords = list.get(i);

				if (world.getBlock(coords[0], coords[1], coords[2]) instanceof BlockLiquid)
				{
					world.setBlockToAir(coords[0], coords[1], coords[2]);
				}
			}

			if (!list.isEmpty())
			{
				player.addChatMessage(new ChatComponentText(EnumChatFormatting.ITALIC + "Spherical area of liquid removed."));
			}
		}
		else if (this == TragicItems.TreeGenerator)
		{
			WorldGenerator object;

			switch (random.nextInt(14))
			{
			default:
				object = new WorldGenForest(true, false);
				break;
			case 0:
				object = new WorldGenBigTree(true);
				break;
			case 1:
				object = new WorldGenSavannaTree(true);
				break;
			case 2:
				object = new WorldGenCanopyTree(true);
				break;
			case 3:
				object = new WorldGenMegaJungle(true, 10, 20, 3, 3);
				break;
			case 4:
				object = new WorldGenMegaPineTree(false, random.nextBoolean());
				break;
			case 5:
				object = new WorldGenTaiga2(true);
				break;
			case 6:
				object = new WorldGenTrees(true, 4 + random.nextInt(7), 3, 3, false);
				break;
			case 7:
				object = new WorldGenBleachedTree(true, random.nextBoolean());
				break;
			case 8:
				object = new WorldGenAshenTree(true);
				break;
			case 9:
				object = new WorldGenLargePaintedTree(true, random.nextInt(3) + 4, 10);
				break;
			case 10:
				object = new WorldGenPaintedTree(true, random.nextBoolean());
				break;
			}

			for (int y1 = 1; y1 < 4; y1++)
			{
				for (int z1 = -1; z1 < 2; z1++)
				{
					for (int x1 = -1; x1 < 2; x1++)
					{
						world.setBlockToAir(Xcoord + x1, Ycoord + y1, Zcoord + z1);
					}
				}
			}

			if (object.generate(world, random, Xcoord, Ycoord, Zcoord))
			{
				player.addChatMessage(new ChatComponentText(EnumChatFormatting.ITALIC + "Tree generated successfully"));
			}
			else
			{
				player.addChatMessage(new ChatComponentText(EnumChatFormatting.ITALIC + "Tree generation failed."));
			}

		}
		else if (this == TragicItems.SpikeGenerator)
		{
			size = random.nextDouble() + 1.5D;
			Block spike = random.nextBoolean() ? TragicBlocks.BoneBlock : (random.nextBoolean() ? TragicBlocks.DarkCobblestone : TragicBlocks.DarkStone);
			int meta = spike == TragicBlocks.BoneBlock ? random.nextInt(2) : (spike == TragicBlocks.DarkStone ? 14 : (random.nextBoolean() ? 0 : 2));
			spike = TragicBlocks.DarkStone;
			meta = 14;
			int spikeType = random.nextInt(6);
			boolean flag = false;
			boolean flag2 = false;

			player.addChatMessage(new ChatComponentText(EnumChatFormatting.ITALIC + "Spike of type " + spikeType + " and size of " + size + " generated."));

			for (int y1 = 0; y1 < 256; y1++)
			{
				if (random.nextBoolean())
				{
					size *= 0.96977745D; //reduce the radius of the spike randomly, give at least 2 levels at max radius

					if (random.nextInt(3) == 0 && size >= 0.4888233D) //randomly apply offset to the spike, this sometimes gives it a cool spiral effect
					{
						Xcoord += random.nextInt(2) - random.nextInt(2);
						Zcoord += random.nextInt(2) - random.nextInt(2);
					}

					if (spikeType == 1 && !flag && y1 >= 35 && y1 <= 70 && random.nextBoolean() && size <= 0.774446314D) //Type 1 has chance to thicken at a random spot once
					{
						size *= 2.86333567D;
						flag = true;
					}
				}
				else if (spikeType == 2 && size >= 0.5625292D) //Type 2 has greater chance of offset, making it look more coral-like
				{
					Xcoord += random.nextInt(2) - random.nextInt(2);
					Zcoord += random.nextInt(2) - random.nextInt(2);
				}
				else if (spikeType == 3 && !flag2 && random.nextBoolean() && y1 >= 35 && size <= 1.41115648D && size >= 0.76663601D) //Type 3 has a chance to create "new" smaller spikes near the top
				{
					player.addChatMessage(new ChatComponentText(EnumChatFormatting.ITALIC + "Spike spawned child at Y coord: " + (Ycoord + y1)));
					CustomSpikesWorldGen.generateChildSpike(world, random, size * 1.32977745D, Xcoord + random.nextInt(5) - random.nextInt(5), Ycoord + y1, Zcoord + random.nextInt(5) - random.nextInt(5), spike, meta);
					flag2 = true;
				}
				else if (spikeType == 4 && random.nextBoolean() && y1 >= 25 && size >= 0.76663601D) //Type 4 creates a lot of smaller spikes going up the spike
				{
					player.addChatMessage(new ChatComponentText(EnumChatFormatting.ITALIC + "Spike spawned child at Y coord: " + (Ycoord + y1)));
					CustomSpikesWorldGen.generateChildSpike(world, random, size * 1.12977745D, Xcoord + random.nextInt(5) - random.nextInt(5), Ycoord + y1, Zcoord + random.nextInt(5) - random.nextInt(5), spike, meta);
				}
				else if (spikeType == 5 && random.nextBoolean()) //Type 5 creates huge spikes at the base, and smaller ones near the top
				{
					if (y1 <= 16)
					{
						player.addChatMessage(new ChatComponentText(EnumChatFormatting.ITALIC + "Spike spawned child at Y coord: " + (Ycoord + y1)));
						CustomSpikesWorldGen.generateChildSpike(world, random, size * 1.12977745D, Xcoord + random.nextInt(6) - random.nextInt(6), Ycoord + y1, Zcoord + random.nextInt(6) - random.nextInt(6), spike, meta);
					}
					else if (size >= 0.76663601D)
					{
						player.addChatMessage(new ChatComponentText(EnumChatFormatting.ITALIC + "Spike spawned child at Y coord: " + (Ycoord + y1)));
						CustomSpikesWorldGen.generateChildSpike(world, random, size * 1.13977745D, Xcoord + random.nextInt(5) - random.nextInt(5), Ycoord + y1, Zcoord + random.nextInt(5) - random.nextInt(5), spike, meta);
					}
				}

				if (size < 0.36943755D || Ycoord + y1 > 256) break;

				list = WorldHelper.getBlocksInSphericalRange(world, size, Xcoord, Ycoord + y1, Zcoord);

				for (int j = 0; j < list.size(); j++)
				{
					coords = list.get(j);
					world.setBlock(coords[0], coords[1], coords[2], spike, meta, 2);
				}
			}
		}
		else if (this == TragicItems.StarCrystalGenerator)
		{
			size = 0.35D * random.nextDouble() + 0.75D;
			int meta = random.nextInt(16);
			Block block;

			player.addChatMessage(new ChatComponentText(EnumChatFormatting.ITALIC + "Star Crystal with size of " + size + " generated."));

			for (int y1 = 0; y1 < 12; y1++)
			{
				size *= 0.91377745D;

				if (size < 0.444443755D || Ycoord + y1 > 256) break;

				list = WorldHelper.getBlocksInSphericalRange(world, size, Xcoord, Ycoord + y1 + (size * 0.5D), Zcoord);

				for (int j = 0; j < list.size(); j++)
				{
					coords = list.get(j);
					world.setBlock(coords[0], coords[1], coords[2], TragicBlocks.StarCrystal, meta, 2);
				}
			}
		}
		else if (this == TragicItems.LightningSummoner)
		{
			world.addWeatherEffect(new EntityLightningBolt(world, Xcoord, Ycoord, Zcoord));
		}
		else if (this == TragicItems.ExplosionGenerator)
		{
			world.createExplosion(player, Xcoord, Ycoord, Zcoord, 3.0F + (7.0F * itemRand.nextFloat()), WorldHelper.getMobGriefing(world));
		}

		return stack;
	}
}
