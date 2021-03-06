package tragicneko.tragicmc.blocks;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import tragicneko.tragicmc.main.TragicBlocks;
import tragicneko.tragicmc.main.TragicTabs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockQuicksand extends BlockFalling
{

	private String[] variantNames = new String[]{"Quicksand", "Mud", "NetherDrudge"};

	private IIcon[] iconArray = new IIcon[variantNames.length];

	public BlockQuicksand()
	{
		super(Material.sand);
		this.setCreativeTab(TragicTabs.Survival);
		this.setBlockName("tragicmc.quicksand");
		this.setHardness(25.0F);
		this.setResistance(10.0F);
		this.setHarvestLevel("shovel", 3);
		this.setStepSound(soundTypeSand);
	}
	
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		if (meta >= this.iconArray.length)
		{
			meta = this.iconArray.length - 1;
		}
		return this.iconArray[meta];
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister)
	{
		for (int i = 0; i < this.variantNames.length; i++)
		{
			this.iconArray[i] = par1IconRegister.registerIcon("tragicmc:" + this.variantNames[i] + "_lowRes");
		}
	}
	
	public int damageDropped(int par1)
	{
		return par1;
	}
	
	public void getSubBlocks(Item par1, CreativeTabs par2, List par3)
	{
		for (int i = 0; i < this.variantNames.length; i++)
		{
			par3.add(new ItemStack(par1, 1, i));
		}
	}

	/**
	 * Triggered whenever an entity collides with this block (enters into the block). Args: world, x, y, z, entity
	 */
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		if (entity instanceof EntityFallingBlock && ((EntityFallingBlock)entity).func_145805_f() != this)
		{
			entity.setDead();
			return;
		}

		entity.setInWeb();

		if (world.getBlockMetadata(x, y, z) == 1)
		{
			if (entity.motionX >= -0.2 && entity.motionX <= 0.2 || entity.motionZ >= -0.2 && entity.motionZ <= 0.2)
			{
				entity.motionY = -0.25;
			}
			else
			{
				entity.motionY = -0.05;
			}
		}

	}

	public void onEntityWalking(World world, int x, int y, int z, Entity entity)
	{
		entity.motionX *= 0.1;
		entity.motionZ *= 0.1;
	}

	public void onFallenUpon(World world, int x, int y, int z, Entity entity, float distance)
	{
		entity.setInWeb();
	}

	public boolean canHarvestBlock(EntityPlayer player, int meta)
	{
		return true;
	}

	/**
	 * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
	 * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
	 */
	public boolean isOpaqueCube()
	{
		return false;
	}

	/**
	 * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
	 * cleared to be reused)
	 */
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		return null;
	}

	/**
	 * The type of render function that is called for this block
	 */
	public int getRenderType()
	{
		return 0;
	}

	/**
	 * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
	 */
	public boolean renderAsNormalBlock()
	{
		return true;
	}

	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
	{
		return super.getItemDropped(p_149650_1_, p_149650_2_, p_149650_3_);
	}

	/**
	 * Return true if a player with Silk Touch can harvest this block directly, and not its normal drops.
	 */
	protected boolean canSilkHarvest()
	{
		return true;
	}

	public boolean canCreatureSpawn(EnumCreatureType type, IBlockAccess world, int x, int y, int z)
	{
		return false;
	}
}