package rgn.mods.lamp;

import java.util.Map;

import com.google.common.collect.Maps;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockLight extends Block
{
	public BlockLight(int blockId)
	{
		super(blockId, Material.air);
		this.setLightValue(1.0F);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void func_94332_a(IconRegister par1IconRegister)
	{
		this.field_94336_cN = null;
	}

	@Override
	public int getRenderType()
	{
		return -1;
	}

	@Override
	public int getMobilityFlag()
	{
		return 2;
	}

	@Override
	public boolean isCollidable()
	{
		return false;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public void dropBlockAsItemWithChance(World world, int x, int y, int z, int meta, float probability, int par7)
	{
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k)
	{
		return null;
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int neighborBlockId)
	{
		int meta = world.getBlockMetadata(x, y, z) & 3;

		if (!this.canBlockStay(world, x, y, z))
		{
			world.func_94575_c(x, y, z, 0);
		}
		else if ((meta == 3 || meta == 2) && world.isAirBlock(x, y - 1, z))
		{
			this.setBlockLight(world, x, y, z);
		}

	}
	@Override
	public boolean canBlockStay(World world, int x, int y, int z)
	{
		return world.getBlockId(x, y + 1, z) == Lamp.blockLight.blockID || this.isLightSource(world, x, y, z);
	}

	private void setBlockLight(World world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z) & 3;
		for (; world.isAirBlock(x, y - 1, z) & y >= 0; y--)
		{
			world.setBlockMetadataWithNotify(x, y - 1, z, Lamp.blockLight.blockID, 8 + meta);
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

	}

}