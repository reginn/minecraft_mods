package rgn.mods.dwarventools.generate;

import net.minecraft.block.Block;
import net.minecraft.world.World;

public class WorldGenDwarvenQuartzDungeons extends WorldGenDungeonsBase
{
	public WorldGenDwarvenQuartzDungeons()
	{
		super("Skeleton", 2, 2);
	}

	@Override
	protected void setRandomBlock(World world, int x, int y, int z)
	{
		world.setBlock(x, y, z, Block.blockNetherQuartz.blockID, 1, 2);
	}

	@Override
	protected void setBaseBlock(World world, int x, int y, int z)
	{
		world.setBlock(x, y, z, Block.blockNetherQuartz.blockID);
	}
}
