package rgn.mods.lamp;

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
		
		if (meta == 0 || meta == 1)
		{
			return this.isLightSource(world, x, y, z);
		}
		
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
	
	private boolean isLightSource(World world, int x, int y, int z)
	{
		int  dir = world.getBlockMetadata(x, y, z) >>> 2;
		int dx = dir == 0 ? 1 : 0;
		int dz = dir == 1 ? 1 : 0;
		int dy = dir == 2 ? 1 : 0;
		
		return world.getBlockId(x - dx, y - dy, z - dz) == Lamp.blockLamp.blockID || world.getBlockId(x + dx, y + dy, z + dz) == Lamp.blockLamp.blockID;
	}
	
}