package rgn.mods.elventools;

import java.util.Random;

import net.minecraft.src.*;
import cpw.mods.fml.common.IWorldGenerator;

public class ElvenWorldGenerator implements IWorldGenerator
{
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		switch (world.provider.worldType)
		{
			case -1 :
				this.generateNether(world, random, chunkX << 4, chunkZ << 4);
				break;
			
			case 0 :
				this.generateSurface(world, random, chunkX << 4, chunkZ << 4);
				break;
			
			default :
			
		}
		
	}
	
	private void generateSurface(World world, Random random, int chunkX, int chunkZ)
	{
		BiomeGenBase biomegenbase = world.getWorldChunkManager().getBiomeGenAt(chunkX, chunkZ);
		WorldGenEbonyTree worldGenEbonyTree = new WorldGenEbonyTree(true);
		
		if (biomegenbase instanceof BiomeGenForest)
		{
			int x = chunkX + random.nextInt(16) + 8;
			int z = chunkZ + random.nextInt(16) + 8;
			int y = world.getHeightValue(x, z);
			worldGenEbonyTree.setScale(1.0D, 1.0D, 1.0D);
			worldGenEbonyTree.generate(world, random, x, y, z);
		}
	}
	
	private void generateNether(World world, Random random, int chunkX, int chunkZ)
	{
	}
}