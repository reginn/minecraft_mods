package rgn.mods.elventools.block;

import java.util.Random;

import net.minecraft.block.BlockFlower;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import rgn.mods.elventools.generate.WorldGenEbonyTree;

public class BlockEbonySapling extends BlockFlower
{
	public BlockEbonySapling(int blockId)
	{
		super(blockId);
		this.setBlockBounds(0.1F, 0.0F, 0.1F, 0.9F, 0.8F, 0.9F);
		this.setHardness(0.0F);
		this.setStepSound(soundGrassFootstep);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void func_94332_a(IconRegister par1IconRegister)
	{
		this.field_94336_cN = par1IconRegister.func_94245_a("rgn/elventools:ebonySapling");
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random random)
	{
		if (!world.isRemote)
		{
			super.updateTick(world, x, y, z, random);

			if (world.getBlockLightValue(x, y + 1, z) >= 9 && random.nextInt(7) == 0)
			{
				int metadata = world.getBlockMetadata(x, y, z);

				if ((metadata & 8) == 0)
				{
					world.setBlockMetadataWithNotify(x, y, z, metadata | 8, 3);
				}
				else
				{
					this.growTree(world, x, y, z, random);
				}
			}
		}
	}

	public void growTree(World world, int x, int y, int z, Random random)
	{
		int metadata = world.getBlockMetadata(x, y, z);
		Object worldGenObject = new WorldGenEbonyTree(true);
		int var8 = 0;
		int var9 = 0;

		world.func_94571_i(x, y, z);

		if (!((WorldGenerator)worldGenObject).generate(world, random, x + var8, y, z + var9))
		{
			world.setBlockAndMetadataWithNotify(x, y, z, this.blockID, metadata, 3);
		}
	}

	public void func_96477_c(World world, int x, int y, int z, Random random)
	{
		int metadata = world.getBlockMetadata(x, y, z);

		if ((metadata & 8) == 0)
		{
			world.setBlockMetadataWithNotify(x, y, z, metadata | 8, 4);
		}
		else
		{
			this.growTree(world, x, y, z, random);
		}
	}

}
