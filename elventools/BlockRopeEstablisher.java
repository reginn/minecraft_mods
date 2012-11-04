package rgn.mods.elventools;

import java.util.Random;

import net.minecraft.src.*;

public class BlockRopeEstablisher extends Block
{
	public BlockRopeEstablisher(int blockId, int terrainId)
	{
		super(blockId, terrainId, Material.vine);
		this.setBlockBounds(0.4F, 0.0F, 0.4F, 0.6F, 1.0F, 0.6F);
	}
	
	public int getMobilityFlag()
	{
		return 2;
	}
	
	@Override
	public String getTextureFile()
	{
		return "/rgn/sprites/elventools/blocks.png";
	}
	
	@Override
	public boolean isLadder(World world, int x, int y, int z) 
	{
		return true;
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}
	
	@Override
	public int getRenderType()
	{
		return 1;
	}
	
	@Override
	public int idDropped(int par1, Random par2Random, int par3)
	{
		return ElvenItem.itemRopeEstablisher.shiftedIndex;
	}
	
	@Override
	public void dropBlockAsItemWithChance(World world, int x, int y, int z, int par5, float par6, int par7)
	{
		world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(ElvenItem.itemRopeEstablisher, 1)));
	}
	
	@Override
	public void onBlockAdded(World world, int x, int y, int z)
	{
		if (world.isAirBlock(x, y - 1, z) && y - 1 >= 0)
		{
			for (; world.isAirBlock(x, y - 1, z) && y - 1 >= 0; y--)
			{
				world.setBlockWithNotify(x, y - 1, z, ElvenBlock.blockRope.blockID);
			}
		}
	}
	
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int neighborBlockId)
	{
		if (world.isAirBlock(x, y + 1, z))
		{
			world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(ElvenItem.itemRopeEstablisher, 1)));
			world.setBlockWithNotify(x, y, z, 0);
		}
		else if (world.isAirBlock(x, y - 1, z) && y - 1 >= 0)
		{
			for (; world.isAirBlock(x, y - 1, z) && y - 1 >= 0; y--)
			{
				world.setBlockWithNotify(x, y - 1, z, ElvenBlock.blockRope.blockID);
			}
		}
	}


}