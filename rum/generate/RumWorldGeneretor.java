package rgn.mods.rum.generate;

import java.util.Random;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

import net.minecraftforge.common.ForgeDirection;

import cpw.mods.fml.common.IWorldGenerator;

import rgn.mods.rum.block.RumBlock;
import rgn.mods.rum.tileentity.TileEntityLockedChest;

public class RumWorldGeneretor implements IWorldGenerator
{
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		this.generateLockedChest(world, random, chunkX << 4, chunkZ << 4);
	}

	private void generateLockedChest(World world, Random random, int chunkX, int chunkZ)
	{
		if (random.nextInt(100) < 5)
		{
			int x = chunkX + random.nextInt(16);
			int z = chunkZ + random.nextInt(16);
			int y = world.getHeightValue(x, z);

			if (world.isAirBlock(x, y, z) && !world.isAirBlock(x, y - 1, z))
			{
				generateChest(world, x, y, z, random);
			}
		}
	}

	protected void generateChest(World world, int x, int y, int z, Random random)
	{
		int[] face = new int[]{2, 5, 3, 4};
		if (!world.isBlockSolidOnSide(x, y - 1, z, ForgeDirection.DOWN))
		{
			return ;
		}
		world.setBlock(x, y, z, RumBlock.blockLockedChest.blockID, face[random.nextInt(4)], 2);
		TileEntityLockedChest tileEntityLockedChest = (TileEntityLockedChest)world.getBlockTileEntity(x, y, z);

		if (tileEntityLockedChest != null)
		{
			for (int itemNum = 0; itemNum < LockedChestInfo.lockedChestContents.getMax(); ++itemNum)
			{
				ItemStack chestInItem = LockedChestInfo.lockedChestContents.getOneItem(random);

				if (chestInItem != null)
				{
					tileEntityLockedChest.setInventorySlotContents(random.nextInt(tileEntityLockedChest.getSizeInventory()), chestInItem);
				}
			}
		}
	}

}
