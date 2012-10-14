package rgn.mods.mabicraft;

import java.util.ArrayList;
import java.util.Random;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

import net.minecraft.src.*;

public class BlockEnchanter extends Block
{
	private Random random;
	
	public BlockEnchanter(int blockId, int terrainId)
	{
		super(blockId, Material.rock);
		this.blockIndexInTexture = terrainId;
		this.setLightValue(1.0F);
		this.setCreativeTab(CreativeTabs.tabDeco);
		this.random = new Random();
	}
	
	@Override
	public void addCreativeItems(ArrayList itemList)
	{
		itemList.add(new ItemStack(this));
	}
	
	@Override
	public int getRenderType()
	{
		return 0;//MabiCraft.enchanterRenderType;
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
	public int getBlockTextureFromSide(int par1)
	{
		if (par1 == 0)
		{
			return 4;
		}
		else if (par1 == 1)
		{
			return 166;
		}
		else
		{
			return 35;
		}
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
			entityPlayer.openGui(MabiCraft.instance, MabiCraft.guiIdEnchanter, world, x, y, z);
			return true;
		}
		
	}
	
}