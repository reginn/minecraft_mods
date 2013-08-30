package rgn.mods.elventools.generate;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenForest;
import net.minecraft.world.chunk.IChunkProvider;

import cpw.mods.fml.common.IWorldGenerator;

public class ElvenWorldGenerator implements IWorldGenerator
{
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		if (world.getWorldInfo().getVanillaDimension() == 0 || world.getWorldInfo().getVanillaDimension() > 1)
		{
			this.generateSurface(world, random, chunkX << 4, chunkZ << 4);
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

	private void generateEnd(World world, Random random, int chunkX, int chunkZ)
	{
	}
}