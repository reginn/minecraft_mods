package rgn.mods.dwarventools;

import java.util.Random;

import net.minecraft.src.*;
import cpw.mods.fml.common.IWorldGenerator;

public class DwarvenWorldGenerator implements IWorldGenerator
{
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		this.generateSurface(world, random, chunkX << 4, chunkZ << 4);
	}
	
	private void generateSurface(World world, Random random, int chunkX, int chunkZ)
	{
		BiomeGenBase biomeGenBase = world.getWorldChunkManager().getBiomeGenAt(chunkX, chunkZ);
		
		int ratio = 1;
		if (biomeGenBase instanceof BiomeGenHills)
		{
			ratio = 3;
		}
		
		if (biomeGenBase instanceof BiomeGenDesert)
		{
			ratio = 2;
		}
		
		for (int i = 0; i < 20 * ratio; i++)
		{
			int x = (chunkX - (chunkX % 16)) + random.nextInt(16);
			int y = random.nextInt(40) + 10;
			int z = (chunkZ - (chunkZ % 16)) + random.nextInt(16);
			(new WorldGenMinable(DwarvenBlock.blockDwarvenOre.blockID, 0, 4)).generate(world, random, x, y, z);
		}
		
		for (int i = 0; i < 5 * ratio; i++)
		{
			int x = (chunkX - (chunkX % 16)) + random.nextInt(16);
			int y = random.nextInt(10) + 10;
			int z = (chunkZ - (chunkZ % 16)) + random.nextInt(16);
			(new WorldGenMinable(DwarvenBlock.blockDwarvenOre.blockID, 1, 4)).generate(world, random, x, y, z);
		}
		
		for (int i = 0; i < 2; i++)
		{
			int x = (chunkX - (chunkX % 16)) + random.nextInt(16);
			int y = random.nextInt(40);
			int z = (chunkZ - (chunkZ % 16)) + random.nextInt(16);
			(new WorldGenDwarvenNetherDungeons()).generate(world, random, x, y, z);
		}
		
		
		for (int i = 0; i < 2; i++)
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