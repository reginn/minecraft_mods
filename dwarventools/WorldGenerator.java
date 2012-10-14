package rgn.mods.dwarventools;

import java.util.Random;

import net.minecraft.src.*;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGenerator implements IWorldGenerator
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
		BiomeGenBase biomeGenBase = world.getWorldChunkManager().getBiomeGenAt(chunkX, chunkZ);
		
		int ratio = 1;
		if (biomeGenBase instanceof BiomeGenHills)
		{
			ratio = 2;
		}
		
		for (int i = 0; i < 20 * ratio; i++)
		{
			int x = (chunkX - (chunkX % 16)) + random.nextInt(16);
			int y = random.nextInt(40) + 10;
			int z = (chunkZ - (chunkZ % 16)) + random.nextInt(16);
			(new WorldGenMinable(DwarvenTools.blockDwarvenOre.blockID, 0, 4)).generate(world, random, x, y, z);
		}
		
		for (int i = 0; i < 4 * ratio; i++)
		{
			int x = (chunkX - (chunkX % 16)) + random.nextInt(16);
			int y = random.nextInt(10) + 10;
			int z = (chunkZ - (chunkZ % 16)) + random.nextInt(16);
			(new WorldGenMinable(DwarvenTools.blockDwarvenOre.blockID, 1, 4)).generate(world, random, x, y, z);
		}
		
		for (int i = 0; i < 5; i++)
		{
			int x = (chunkX - (chunkX % 16)) + random.nextInt(16);
			int y = random.nextInt(40);
			int z = (chunkZ - (chunkZ % 16)) + random.nextInt(16);
			(new WorldGenDwarvenNetherDungeons()).generate(world, random, x, y, z);
		}
		
		
		for (int i = 0; i < 5; i++)
		{
			int x = (chunkX - (chunkX % 16)) + random.nextInt(16);
			int y = random.nextInt(60);
			int z = (chunkZ - (chunkZ % 16)) + random.nextInt(16);
			(new WorldGenDwarvenStrongholdDungeons()).generate(world, random, x, y, z);
		}
	}
	
	private void generateNether(World world, Random random, int chunkX, int chunkZ)
	{
	}
}