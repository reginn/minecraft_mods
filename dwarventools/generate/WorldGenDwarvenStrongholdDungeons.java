package rgn.mods.dwarventools.generate;

import java.util.Random;

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
		world.setBlockWithNotify(x, y, z, Block.stoneBrick.blockID);
		world.setBlockMetadataWithNotify(x, y, z, world.rand.nextInt(3));
	}
	
	protected void setBaseBlock(World world, int x, int y, int z)
	{
		world.setBlockWithNotify(x, y, z, Block.stoneBrick.blockID);
		world.setBlockMetadataWithNotify(x, y, z, world.rand.nextInt(3));
	}
	
}
