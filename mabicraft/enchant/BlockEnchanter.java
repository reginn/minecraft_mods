package rgn.mods.mabicraft.enchant;

import java.util.ArrayList;

import net.minecraft.src.*;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

import rgn.mods.mabicraft.MabiCraft;

import rgn.mods.mabicraft.config.Config;
import rgn.mods.mabicraft.core.EnumGuiID;

public class BlockEnchanter extends Block
{	
	public BlockEnchanter(int blockId, int terrainId)
	{
		super(blockId, Material.wood);
		this.blockIndexInTexture = terrainId;
		this.setCreativeTab(Config.tabMabiCraft);
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
		world.markBlockForUpdate(x, y, z);
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
		if (!world.isRemote)
		{
			entityPlayer.openGui(MabiCraft.instance, EnumGuiID.ENCHANTER.ordinal(), world, x, y, z);
		}
		return true;	
	}
	
}