package rgn.mods.dwarventools.generate;

import java.util.Random;

import cpw.mods.fml.common.IWorldGenerator;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenDesert;
import net.minecraft.world.biome.BiomeGenHills;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

import rgn.mods.dwarventools.block.DwarvenBlock;

public class DwarvenWorldGenerator implements IWorldGenerator
{
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		if (world.getWorldInfo().getDimension() == 0 || world.getWorldInfo().getDimension() > 1)
		{
			this.generateSurface(world, random, chunkX << 4, chunkZ << 4);
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

		for (int i = 0; i < 15 * ratio; i++)
		{
			int x = (chunkX - (chunkX % 16)) + random.nextInt(16);
			int y = random.nextInt(40) + 10;
			int z = (chunkZ - (chunkZ % 16)) + random.nextInt(16);
			(new WorldGenMinable(DwarvenBlock.blockDwarvenOre.blockID, 0, 4)).generate(world, random, x, y, z);
		}

		for (int i = 0; i < 4 * ratio; i++)
		{
			int x = (chunkX - (chunkX % 16)) + random.nextInt(16);
			int y = random.nextInt(10) + 15;
			int z = (chunkZ - (chunkZ % 16)) + random.nextInt(16);
			(new WorldGenMinable(DwarvenBlock.blockDwarvenOre.blockID, 1, 4)).generate(world, random, x, y, z);
		}

		{
			int x = (chunkX - (chunkX % 16)) + random.nextInt(16);
			int y = random.nextInt(40);
			int z = (chunkZ - (chunkZ % 16)) + random.nextInt(16);
			(new WorldGenDwarvenNetherDungeons()).generate(world, random, x, y, z);
		}


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
	
	private void generateEnd(World world, Random random, int chunkX, int chunkZ)
	{
	}
}