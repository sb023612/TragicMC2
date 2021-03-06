package tragicneko.tragicmc.items.special;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;

public class ItemBleedingObsidianOrb extends Item {

	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
	{
		par2List.add("When Right-Clicked, will teleport you");
		par2List.add("to your last spawn point in your current");
		par2List.add("dimension or to the overworld if no spawn point");
	}

	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		if (!par2World.isRemote)
		{
			int dim = par2World.provider.dimensionId;
			ChunkCoordinates cc = par3EntityPlayer.getBedLocation(dim);

			if (cc != null)
			{
				par3EntityPlayer.setPositionAndUpdate(cc.posX, cc.posY, cc.posZ);
			}
			else
			{
				if (dim != 0)
				{
					par3EntityPlayer.travelToDimension(0);
				}

				ChunkCoordinates cc2 = par3EntityPlayer.getBedLocation(0);

				if (cc2 != null)
				{
					par3EntityPlayer.setPositionAndUpdate(cc2.posX, cc2.posY, cc2.posZ);
				}
				else
				{
					ChunkCoordinates cc3 = par2World.getSpawnPoint();
					par3EntityPlayer.setPositionAndUpdate(cc3.posX, par2World.getTopSolidOrLiquidBlock(cc3.posX, cc3.posZ), cc3.posZ);
				}
			}
			
			par1ItemStack.stackSize--;
		}
		return par1ItemStack;
	}
}
