package rgn.mods.elventools;

import java.util.ArrayList;

import java.util.Random;

import net.minecraft.src.*;

public class BlockEbonySapling extends BlockFlower
{
	public BlockEbonySapling(int blockId, int terrainId)
	{
		super(blockId, terrainId);
		this.setBlockBounds(0.1F, 0.0F, 0.1F, 0.9F, 0.8F, 0.9F);
		this.setHardness(0.0F);
		this.setStepSound(soundGrassFootstep);
		this.setRequiresSelfNotify();
		this.setCreativeTab(Config.tabElvenTools);
	}
	
	@Override
	public String getTextureFile()
	{
		return "/rgn/sprites/elventools/blocks.png";
	}
	
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
					world.setBlockMetadataWithNotify(x, y, z, metadata | 8);
				}
				else
				{
					this.growTree(world, x, y, z, random);
				}
			}
		}
	}
	
	public int getBlockTextureFromSideAndMetadata(int side, int metadata)
	{
		return this.blockIndexInTexture;
	}
	
	public void growTree(World world, int x, int y, int z, Random random)
	{
		int metadata = world.getBlockMetadata(x, y, z);
		Object worldGenObject = new WorldGenEbonyTree(true);
		int var8 = 0;
		int var9 = 0;
		
		world.setBlock(x, y, z, 0);
		
		if (!((WorldGenerator)worldGenObject).generate(world, random, x + var8, y, z + var9))
		{
			world.setBlockAndMetadata(x, y, z, this.blockID, metadata);
		}
	}

}
