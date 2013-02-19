package rgn.mods.elventools.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

public class BlockEbonyLog extends Block
{
	public BlockEbonyLog(int blockId, int terrainId)
	{
		super(blockId, terrainId, Material.wood);
		this.setHardness(3.0F);
		this.setStepSound(soundWoodFootstep);
		this.setRequiresSelfNotify();
	}

	@Override
	public String getTextureFile()
	{
		return "/rgn/sprites/elventools/blocks.png";
	}

	public int getBlockTextureFromSide(int side)
	{
		if (side == 1 || side == 0)
		{
			return this.blockIndexInTexture + 1;
		}
		else
		{
			return this.blockIndexInTexture;
		}
	}

	public void breakBlock(World world, int x, int y, int z, int par5, int par6)
	{
		int sustainableRange = 4;
		int unsustainableRange = sustainableRange + 1;

		if (world.checkChunksExist( x - unsustainableRange, y - unsustainableRange, z - unsustainableRange,
									x + unsustainableRange, y + unsustainableRange, z + unsustainableRange))
		{
			for (int i = -sustainableRange; i <= sustainableRange; ++i)
			{
				for (int j = -sustainableRange; j <= sustainableRange; ++j)
				{
					for (int k = -sustainableRange; k <= sustainableRange; ++k)
					{
						int ebonyLeavesBlockId = world.getBlockId(x + i, y + j, z + k);

						if (Block.blocksList[ebonyLeavesBlockId] != null)
						{
							Block.blocksList[ebonyLeavesBlockId].beginLeavesDecay(world, x + i, y + j, z + k);
						}
					}
				}
			}
		}
	}

	@Override
	public boolean canSustainLeaves(World world, int x, int y, int z)
	{
		return true;
	}

	@Override
	public boolean isWood(World world, int x, int y, int z)
	{
		return true;
	}

}