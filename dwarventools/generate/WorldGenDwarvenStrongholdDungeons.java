package rgn.mods.dwarventools.generate;

import net.minecraft.block.Block;
import net.minecraft.world.World;

public class WorldGenDwarvenStrongholdDungeons extends WorldGenDungeonsBase
{
	public WorldGenDwarvenStrongholdDungeons()
	{
		super("Enderman", 3, 3);
	}

	protected void setRandomBlock(World world, int x, int y, int z)
	{
		world.setBlock(x, y, z, Block.stoneBrick.blockID, world.rand.nextInt(3), 3);
	}

	protected void setBaseBlock(World world, int x, int y, int z)
	{
		world.setBlock(x, y, z, Block.stoneBrick.blockID, world.rand.nextInt(3), 3);
	}

}
