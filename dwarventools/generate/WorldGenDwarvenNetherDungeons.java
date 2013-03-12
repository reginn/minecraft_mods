package rgn.mods.dwarventools.generate;

import net.minecraft.block.Block;
import net.minecraft.world.World;

public class WorldGenDwarvenNetherDungeons extends WorldGenDungeonsBase
{
	public WorldGenDwarvenNetherDungeons()
	{
		super("Blaze", 2, 2);
	}

	@Override
	protected void setRandomBlock(World world, int x, int y, int z)
	{
		world.func_94575_c(x, y, z, Block.slowSand.blockID);
	}

	@Override
	protected void setBaseBlock(World world, int x, int y, int z)
	{
		world.func_94575_c(x, y, z, Block.netherrack.blockID);
	}
}
