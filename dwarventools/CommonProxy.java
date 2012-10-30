package rgn.mods.dwarventools;

import net.minecraft.src.*;

import cpw.mods.fml.common.network.IGuiHandler;

import rgn.mods.dwarventools.client.GuiInfernalFurnace;

public class CommonProxy implements IGuiHandler
{
	public int addArmor(String armor)
	{
		return 0;
	}
	
	public void registerTextures()
	{
	}
	
	public World getClientWorld()
	{
		return null;
	}
	
	// implements IGuiHandler
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntityInfernalFurnace tileentity = (TileEntityInfernalFurnace)world.getBlockTileEntity(x, y, z);
		if (tileentity != null && ID == DwarvenTools.guiIdInfernalFurnace)
		{
			return (new GuiInfernalFurnace(player.inventory, tileentity));
		}
		else
		{
			return null;
		}
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntityInfernalFurnace tileentity = (TileEntityInfernalFurnace)world.getBlockTileEntity(x, y, z);
		if (tileentity != null && ID == DwarvenTools.guiIdInfernalFurnace)
		{
			return (new ContainerInfernalFurnace(player.inventory, tileentity));
		}
		else
		{
			return null;
		}
	}
}