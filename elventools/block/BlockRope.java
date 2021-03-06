package rgn.mods.elventools.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class BlockRope extends Block
{
	public BlockRope(int blockId)
	{
		super(blockId, Material.vine);
		this.setBlockBounds(0.4F, 0.0F, 0.4F, 0.6F, 1.0F, 0.6F);
	}

	@Override
	public int getMobilityFlag()
	{
		return 2;
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
	public int quantityDropped(Random par1Random)
	{
		return 0;
	}

	@Override
	public int idDropped(int par1, Random par2Random, int par3)
	{
		return 0;
	}

	@Override
	public void dropBlockAsItemWithChance(World world, int x, int y, int z, int meta, float probability, int par7)
	{
	}

	@Override
	public boolean isLadder(World world, int x, int y, int z, EntityLivingBase entity)
	{
		return true;
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z)
	{
		return world.getBlockId(x, y + 1, z) == ElvenBlock.blockRope.blockID || world.getBlockId(x, y + 1, z) == ElvenBlock.blockRopeEstablisher.blockID || !world.isAirBlock(x, y + 1, z);
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int neighborBlockId)
	{
		if (!this.canBlockStay(world, x, y, z))
		{
			world.setBlockToAir(x, y, z);
		}
		else if (world.isAirBlock(x, y - 1, z))
		{
			this.setBlockRope(world, x, y, z);
		}
	}

	private void setBlockRope(World world, int x, int y, int z)
	{
		for (; world.isAirBlock(x, y - 1, z) & y >= 0; y--)
		{
			world.setBlock(x, y - 1, z, ElvenBlock.blockRope.blockID);
		}
	}
}