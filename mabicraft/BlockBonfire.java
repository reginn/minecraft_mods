package rgn.mods.mabicraft;

import java.util.ArrayList;
import java.util.Random;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

import net.minecraft.src.*;

public class BlockBonfire extends Block
{
	private Random random;
	
	public BlockBonfire(int blockId, int terrainId)
	{
		super(blockId, Material.rock);
		this.blockIndexInTexture = terrainId;
		this.setLightValue(1.0F);
		this.random = new Random();
	}
	
	@Override
	public int getRenderType()
	{
		return MabiCraft.bonfireRenderType;
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}
	
	@Override
	public void onBlockAdded(World world, int x, int y, int z)
	{
		super.onBlockAdded(world, x, y, z);
		world.markBlockNeedsUpdate(x, y, z);
	}
	
	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z)
	{
		int blockid = world.getBlockId(x, y - 1, z);
		
		return Block.blocksList[blockid] != null && Block.blocksList[blockid].canPlaceTorchOnTop(world, x, y - 1, z);
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int par6, float par7, float par8, float par9)
	{
		if (world.isRemote)
		{
			return true;
		}
		else
		{
			entityPlayer.openGui(MabiCraft.instance, MabiCraft.guiIdBonfire, world, x, y, z);
			return true;
		}
		
	}
	
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random random)
	{
		if (random.nextInt(24) == 0)
		{
			world.playSound((double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F),
							"fire.fire", 1.0F + random.nextFloat(), random.nextFloat() * 0.7F + 0.3F);
		}
		double X = (double)((float)x + random.nextFloat());
		double Y = (double)((float)y + random.nextFloat() * 0.5F + 0.5F);
		double Z = (double)((float)z + random.nextFloat());
		
		world.spawnParticle("largesmoke", X, Y, Z, 0.0D, 0.0D, 0.0D);
	}
}