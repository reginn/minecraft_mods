package rgn.mods.dwarventools.generate;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenHills;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

import cpw.mods.fml.common.IWorldGenerator;

import rgn.mods.dwarventools.block.DwarvenBlock;

public class DwarvenWorldGenerator implements IWorldGenerator
{
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{

		switch (world.provider.dimensionId)
		{
			case -1 :
				this.generateNether(world, random, chunkX << 4, chunkZ << 4);
				break;
			case 1 :
				// this.generateEnd(world. random, chunkX << 4, chunkZ << 4);
				break;
			default :
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
		{
			int x = (chunkX - (chunkX % 16)) + random.nextInt(16);
			int y = random.nextInt(60) + 32;
			int z = (chunkZ - (chunkZ % 16)) + random.nextInt(16);
			(new WorldGenDwarvenQuartzDungeons()).generate(world, random, x, y, z);
		}
	}

	private void generateEnd(World world, Random random, int chunkX, int chunkZ)
	{
	}
}