package rgn.mods.decorations;

import java.util.Random;
import java.util.List;

import net.minecraft.src.*;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

public class BlockColoredSapling extends BlockFlower
{
	public static final String[] types = 
		{
			"cherry", "whitecherry", "maple", "yellow"
		};
		
	public BlockColoredSapling(int blockId, int terrainId)
	{
		super(blockId, terrainId);
		this.setBlockBounds(0.1F, 0.0F, 0.1F, 0.9F, 0.8F, 0.9F);
		this.setHardness(0.0F);
		this.setStepSound(soundGrassFootstep);
		this.setRequiresSelfNotify();
	}
	
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
	{
		for (int i = 0; i < types.length; ++i)
		{
			par3List.add(new ItemStack(par1, 1, i));
		}
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
					world.setBlockMetadataWithNotify(x, y, z, metadata | 8);
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
		int metadata          = world.getBlockMetadata(x, y, z);
		Object worldGenObject = new WorldGenColoredTree(true, metadata);
		
		world.setBlock(x, y, z, 0);
		
		if (!((WorldGenerator)worldGenObject).generate(world, random, x, y, z))
		{
			world.setBlockAndMetadata(x, y, z, this.blockID, metadata);
		}
	}
}