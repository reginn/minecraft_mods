package rgn.mods.lamp;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import net.minecraft.src.*;

public class BlockLamp extends Block
{
	public BlockLamp(int blockId, int terrainId)
	{
		super(blockId, terrainId, Material.glass);
		this.setLightValue(1.0F);
		this.setCreativeTab(CreativeTabs.tabDeco);
	}
	
	@Override
	public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List list)
	{
		for (int i = 0; i < 4; i++)
		{
			list.add(new ItemStack(blockID, 1, i));
		}
	}
	
	public int getMobilityFlag()
	{
		return 2;
	}
	
	@Override
	public int getRenderType()
	{
		return Lamp.lampRenderType;
	}
	
	@Override
	protected int damageDropped(int i)
	{
		return i;
	}
	
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		return null;
	}
	
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	public boolean renderAsNormalBlock()
	{
		return false;
	}
	
	public void randomDisplayTick(World world, int x, int y, int z, Random random)
	{
		int meta = world.getBlockMetadata(x, y, z);
		
		double dx = (double)((float)x + 0.5F);
		double dy = (double)((float)y + 0.65F);
		double dz = (double)((float)z + 0.5F);
		
		if (meta == 0)
		{
			world.spawnParticle("smoke", dx, dy, dz, 0.0D, 0.0D, 0.0D);
			world.spawnParticle("flame", dx, dy, dz, 0.0D, 0.0D, 0.0D);
		}
	}
	
	public void onBlockAdded(World world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);
		
		switch (meta)
		{
			case 0 :
				this.setBlockLightWithLantern(world, x, y, z);
				break;
			
			case 1 :
				this.setBlockLightWithGlowLantern(world, x, y, z);
				break;
			
			case 2 :
				this.setBlockLightWithGoldLantern(world, x, y, z);
				break;
			
			case 3 :
				this.setBlockLightWithDiamondLantern(world, x, y, z);
				break;
			
			default :
		}
	}
	
	public void onNeighborBlockChange(World world, int x, int y, int z, int neighborBlockId)
	{
		int meta = world.getBlockMetadata(x, y, z);
		
		switch (meta)
		{
			case 0 :
				this.setBlockLightWithLantern(world, x, y, z);
				break;
			
			case 1 :
				this.setBlockLightWithGlowLantern(world, x, y, z);
				break;
			
			case 2 :
				this.setBlockLightWithGoldLantern(world, x, y, z);
				break;
			
			case 3 :
				this.setBlockLightWithDiamondLantern(world, x, y, z);
				break;
			
			default :
		}
	
	}
	
	private void setBlockLightWithLantern(World world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);
		
		if (world.isAirBlock(x - 1, y, z))
		{
			world.setBlockWithNotify(x - 1, y, z, Lamp.blockLight.blockID);
			world.setBlockMetadataWithNotify(x - 1, y, z, (0 << 2) + meta);
		}
		if (world.isAirBlock(x + 1, y, z))
		{
			world.setBlockWithNotify(x + 1, y, z, Lamp.blockLight.blockID);
			world.setBlockMetadataWithNotify(x + 1, y, z, (0 << 2) + meta);
		}
		
		if (world.isAirBlock(x, y, z - 1))
		{
			world.setBlockWithNotify(x, y, z - 1, Lamp.blockLight.blockID);
			world.setBlockMetadataWithNotify(x, y, z - 1, (1 << 2) + meta);
		}
		if (world.isAirBlock(x, y, z + 1))
		{
			world.setBlockWithNotify(x, y, z + 1, Lamp.blockLight.blockID);
			world.setBlockMetadataWithNotify(x, y, z + 1, (1 << 2) + meta);
		}
	}	
	
	private void setBlockLightWithGlowLantern(World world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);
		
		if (world.isAirBlock(x - 1, y, z))
		{
			world.setBlockWithNotify(x - 1, y, z, Lamp.blockLight.blockID);
			world.setBlockMetadataWithNotify(x - 1, y, z, (0 << 2) + meta);
		}
		if (world.isAirBlock(x + 1, y, z))
		{
			world.setBlockWithNotify(x + 1, y, z, Lamp.blockLight.blockID);
			world.setBlockMetadataWithNotify(x + 1, y, z, (0 << 2) + meta);
		}
		
		if (world.isAirBlock(x, y, z - 1))
		{
			world.setBlockWithNotify(x, y, z - 1, Lamp.blockLight.blockID);
			world.setBlockMetadataWithNotify(x, y, z - 1, (1 << 2) + meta);
		}
		if (world.isAirBlock(x, y, z + 1))
		{
			world.setBlockWithNotify(x, y, z + 1, Lamp.blockLight.blockID);
			world.setBlockMetadataWithNotify(x, y, z + 1, (1 << 2) + meta);
		}
		
		if (world.isAirBlock(x, y - 1, z) && y - 1 >= 0)
		{
			world.setBlockWithNotify(x, y - 1, z, Lamp.blockLight.blockID);
			world.setBlockMetadataWithNotify(x, y - 1, z, (2 << 2) + meta);
		}
		if (world.isAirBlock(x, y + 1, z) && y + 1 <= world.getHeight())
		{
			world.setBlockWithNotify(x, y + 1, z, Lamp.blockLight.blockID);
			world.setBlockMetadataWithNotify(x, y + 1, z, (2 << 2) + meta);
		}
	}
	
	private void setBlockLightWithGoldLantern(World world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);
		if (world.isAirBlock(x, y - 1, z) && y - 1 >= 0)
		{
			for (; world.isAirBlock(x, y - 1, z) && y - 1 >= 0; y--)
			{
				world.setBlockWithNotify(x, y - 1, z, Lamp.blockLight.blockID);
				world.setBlockMetadataWithNotify(x, y - 1, z, (2 << 2) + meta);
			}
		}
		
		if (world.isAirBlock(x, y + 1, z) && y + 1 <= world.getHeight())
		{
			world.setBlockWithNotify(x, y + 1, z, Lamp.blockLight.blockID);
			world.setBlockMetadataWithNotify(x, y + 1, z, (2 << 2) + meta);
		}
	}
	
	private void setBlockLightWithDiamondLantern(World world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);
		
		if (world.isAirBlock(x - 1, y, z))
		{
			world.setBlockWithNotify(x - 1, y, z, Lamp.blockLight.blockID);
			world.setBlockMetadataWithNotify(x - 1, y, z, (0 << 2) + meta);
			for (; world.isAirBlock(x - 1, y - 1, z) && y - 1 >= 0; y--)
			{
				world.setBlockWithNotify(x - 1, y - 1, z, Lamp.blockLight.blockID);
				world.setBlockMetadataWithNotify(x - 1, y - 1, z, (2 << 2) + meta);
			}
		}
		if (world.isAirBlock(x + 1, y, z))
		{
			world.setBlockWithNotify(x + 1, y, z, Lamp.blockLight.blockID);
			world.setBlockMetadataWithNotify(x + 1, y, z, (0 << 2) + meta);
			for (; world.isAirBlock(x + 1, y - 1, z) && y - 1 >= 0; y--)
			{
				world.setBlockWithNotify(x + 1, y - 1, z, Lamp.blockLight.blockID);
				world.setBlockMetadataWithNotify(x + 1, y - 1, z, (2 << 2) + meta);
			}
		}
		
		if (world.isAirBlock(x, y, z - 1))
		{
			world.setBlockWithNotify(x, y, z - 1, Lamp.blockLight.blockID);
			world.setBlockMetadataWithNotify(x, y, z - 1, (1 << 2) + meta);
			for (; world.isAirBlock(x, y - 1, z - 1) && y - 1 >= 0; y--)
			{
				world.setBlockWithNotify(x, y - 1, z - 1, Lamp.blockLight.blockID);
				world.setBlockMetadataWithNotify(x, y - 1, z - 1, (2 << 2) + meta);
			}
		}
		if (world.isAirBlock(x, y, z + 1))
		{
			world.setBlockWithNotify(x, y, z + 1, Lamp.blockLight.blockID);
			world.setBlockMetadataWithNotify(x, y, z + 1, (1 << 2) + meta);
			for (; world.isAirBlock(x, y - 1, z + 1) && y - 1 >= 0; y--)
			{
				world.setBlockWithNotify(x, y - 1, z + 1, Lamp.blockLight.blockID);
				world.setBlockMetadataWithNotify(x, y - 1, z + 1, (2 << 2) + meta);
			}
		}
		
		if (world.isAirBlock(x, y - 1, z) && y - 1 >= 0)
		{
			for (; world.isAirBlock(x, y - 1, z) && y - 1 >= 0; y--)
			{
				world.setBlockWithNotify(x, y - 1, z, Lamp.blockLight.blockID);
				world.setBlockMetadataWithNotify(x, y - 1, z, (2 << 2) + meta);
			}
		}
		
		if (world.isAirBlock(x, y + 1, z) && y + 1 <= world.getHeight())
		{
			world.setBlockWithNotify(x, y + 1, z, Lamp.blockLight.blockID);
			world.setBlockMetadataWithNotify(x, y + 1, z, (2 << 2) + meta);
		}
	}
	

}