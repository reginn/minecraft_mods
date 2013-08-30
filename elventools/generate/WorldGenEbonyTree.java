package rgn.mods.elventools.generate;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import cpw.mods.fml.common.FMLLog;

import rgn.mods.elventools.block.ElvenBlock;

public class WorldGenEbonyTree extends WorldGenerator
{
	private int logHeight;

	public WorldGenEbonyTree(boolean par1)
	{
		super(par1);
	}

	private boolean isGeneratableTree(World world, int x, int y, int z)
	{
		boolean isGen = true;

		for (int j = y; j <= y + 1 + this.logHeight; ++j)
		{
			int checkedRange = j == y ? 0 : (j >= y + 1 + this.logHeight - 2 ? 2 : 1);

			for (int i = x - checkedRange; i <= x + checkedRange && isGen; ++i)
			{
				for (int k = z - checkedRange; k <= z + checkedRange && isGen; ++k)
				{
					if (isRegularY(j))
					{
						int blockId = world.getBlockId(i, j, k);

						Block block = Block.blocksList[blockId];

						if (blockId != 0 && (block != null && !block.isLeaves(world, i, j, k)))
						{
							isGen = false;
						}
					}
					else
					{
						isGen = false;
					}
				}
			}
		}

		return isGen;
	}

	private boolean isRegularY(int y)
	{
		return y >= 0 && y < 256;
	}

	private boolean isGeneratableY(int y)
	{
		return this.logHeight != 0 && (y >= 1 && y + this.logHeight + 1 <= 256);
	}

	private void setTree(World world, int x, int y, int z)
	{
		int blockId;

		for (int woodHeight = 0; woodHeight < this.logHeight; ++woodHeight)
		{
			blockId = world.getBlockId(x, y + woodHeight, z);

			Block block = Block.blocksList[blockId];

			if (blockId == 0 || block == null || block.isLeaves(world, x, y + woodHeight, z))
			{
				this.setBlockAndMetadata(world, x, y + woodHeight, z, ElvenBlock.blockEbonyLog.blockID, 0);
			}
		}
	}

	private void setLeaves(World world, int x, int y, int z)
	{
		for (int leaveHeight = y - 3 + this.logHeight; leaveHeight <= y + this.logHeight; ++leaveHeight)
		{
			int leaveNum = leaveHeight - (y + this.logHeight);
			int leaveRange = 1 - leaveNum / 2;

			for (int i = x - leaveRange; i <= x + leaveRange; ++i)
			{
				int var13 = i - x;

				for (int k = z - leaveRange; k <= z + leaveRange; ++k)
				{
					int var15 = k - z;

					Block block = Block.blocksList[world.getBlockId(i, leaveHeight, k)];

					if ((Math.abs(var13) != leaveRange || Math.abs(var15) != leaveRange || world.rand.nextInt(2) != 0 && leaveNum != 0)
					&& (block == null || block.canBeReplacedByLeaves(world, i, leaveHeight, k)))
					{
						this.setBlockAndMetadata(world, i, leaveHeight, k, ElvenBlock.blockEbonyLeaves.blockID, 0);
					}
				}
			}
		}
	}

	public boolean generate(World world, Random random, int x, int y, int z)
	{
		this.logHeight = random.nextInt(3) + 5;

		if (this.isGeneratableY(y))
		{
			FMLLog.info("hoge");
			if (this.isGeneratableTree(world, x, y, z))
			{
				FMLLog.info("piyo");
				int blockId = world.getBlockId(x, y - 1, z);

				if ((blockId == Block.grass.blockID || blockId == Block.dirt.blockID) && y < 256 - this.logHeight - 1)
				{
					this.setBlock(world, x, y - 1, z, Block.dirt.blockID);
					this.setLeaves(world, x, y, z);
					this.setTree(world, x, y, z);
					return true;
				}
			}
		}
		return false;
	}
}
