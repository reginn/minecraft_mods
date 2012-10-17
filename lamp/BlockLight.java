package rgn.mods.lamp;

import java.util.Set;
import java.util.Map;

import com.google.common.collect.Sets;
import com.google.common.collect.Maps;

import net.minecraft.src.*;

public class BlockLight extends Block
{
	public BlockLight(int blockId, int terrainId)
	{
		super(blockId, terrainId, Material.air);
		this.setLightValue(1.0F);
	}
	
	public int getMobilityFlag()
	{
		return 2;
	}
	
	public String getTextureFile()
	{
		return "/rgn/sprites/lamp/blocks.png";
	}
	
	public boolean isCollidable()
	{
		return false;
	}
	
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	public void dropBlockAsItemWithChance(World world, int x, int y, int z, int meta, float probability, int par7)
	{
		// NOOP
	}
	
	public boolean getIsBlockSolid(IBlockAccess iblockaccess, int i, int j, int k, int l)
	{
		return false;
	}
	
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k)
	{
		return null;
	}
		
	public void onNeighborBlockChange(World world, int x, int y, int z, int neighborBlockId)
	{
		int meta = world.getBlockMetadata(x, y, z) & 3;
		
		if (!this.canBlockStay(world, x, y, z))
		{
			world.setBlockWithNotify(x, y, z, 0);
		}
		else if ((meta == 3 || meta == 2) && world.isAirBlock(x, y - 1, z))
		{
			this.setBlockLight(world, x, y, z);
		}
		
	}
	
	public boolean canBlockStay(World world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z)   & 3;
		/*
		if (meta == 0 || meta == 1)
		{
			return this.isLightSource(world, x, y, z);
		}
		*/
		return world.getBlockId(x, y + 1, z) == Lamp.blockLight.blockID || this.isLightSource(world, x, y, z);
		
	}
	
	
	private void setBlockLight(World world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z) & 3;
		for (; world.isAirBlock(x, y - 1, z) & y >= 0; y--)
		{
			world.setBlockWithNotify(x, y - 1, z, Lamp.blockLight.blockID);
			world.setBlockMetadataWithNotify(x, y - 1, z, (2 << 2) + meta);
		}
	}
	
	public class Coord
	{
		int x;
		int y;
		int z;
		
		public Coord(int i, int j, int k)
		{
			x = i;
			y = j;
			z = k;
		}
	}
	
	private boolean isLightSource(World world, int x, int y, int z)
	{
		int  dir = world.getBlockMetadata(x, y, z) >>> 2;
		int dx = dir == 0 ? 1 : 0;
		int dz = dir == 1 ? 1 : 0;
		int dy = dir == 2 ? 1 : 0;
		
		//return world.getBlockId(x - dx, y - dy, z - dz) == Lamp.blockLamp.blockID || world.getBlockId(x + dx, y + dy, z + dz) == Lamp.blockLamp.blockID;
		
		Map<Integer, Coord> map = Maps.newHashMap();
		
		int meta = world.getBlockMetadata(x, y, z) & 3;
		
		if (meta == 0)
		{
			map.put(new Integer(world.getBlockId(x - 1, y, z)), new Coord(x - 1, y, z));
			map.put(new Integer(world.getBlockId(x + 1, y, z)), new Coord(x + 1, y, z));
			
			map.put(new Integer(world.getBlockId(x, y, z - 1)), new Coord(x, y, z - 1));
			map.put(new Integer(world.getBlockId(x, y, z + 1)), new Coord(x, y, z + 1));
		}
		else if (meta == 1)
		{
			map.put(new Integer(world.getBlockId(x - 1, y, z)), new Coord(x - 1, y, z));
			map.put(new Integer(world.getBlockId(x + 1, y, z)), new Coord(x + 1, y, z));
			
			map.put(new Integer(world.getBlockId(x, y, z - 1)), new Coord(x, y, z - 1));
			map.put(new Integer(world.getBlockId(x, y, z + 1)), new Coord(x, y, z + 1));
			map.put(new Integer(world.getBlockId(x, y + 1, z)), new Coord(x, y + 1, z));
			map.put(new Integer(world.getBlockId(x, y - 1, z)), new Coord(x, y - 1, z));
		}
		else if (meta == 2)
		{
			map.put(new Integer(world.getBlockId(x, y + 1, z)), new Coord(x, y + 1, z));
		}
		else if (meta == 3)
		{
			map.put(new Integer(world.getBlockId(x - 1, y, z)), new Coord(x - 1, y, z));
			map.put(new Integer(world.getBlockId(x + 1, y, z)), new Coord(x + 1, y, z));
			
			map.put(new Integer(world.getBlockId(x, y, z - 1)), new Coord(x, y, z - 1));
			map.put(new Integer(world.getBlockId(x, y, z + 1)), new Coord(x, y, z + 1));
			map.put(new Integer(world.getBlockId(x, y + 1, z)), new Coord(x, y + 1, z));
			map.put(new Integer(world.getBlockId(x, y - 1, z)), new Coord(x, y - 1, z));
		}
		
		if (map.containsKey(Lamp.blockLamp.blockID))
		{
			Coord coord = map.get(Lamp.blockLamp.blockID);	
			int lampMeta = world.getBlockMetadata(coord.x, coord.y, coord.z) & 3;
			
			return meta == lampMeta;
		}
		return false;
		
		/*
		Set<Integer> set = Sets.newHashSet();
		
		for (int i = -1; i < 2; i++)
		{
			for (int j = -1; j < 2; j++)
			{
				for (int k = -1; k < 2; k++)
				{
					set.add(new Integer(world.getBlockId(x + i, y + j, z + k)));
				}
			}
		}
		
		return set.contains(Lamp.blockLamp.blockID);
		*/
	}
	
}