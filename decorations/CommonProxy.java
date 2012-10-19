package rgn.mods.decorations;

import net.minecraft.src.*;

import cpw.mods.fml.common.network.IGuiHandler;

import rgn.mods.decorations.client.GuiOzen;

public class CommonProxy implements IGuiHandler
{
	public int getUniqueRenderID()
	{
		return -1;
	}
	
	public void registerTextures()
	{
	}
	
	public void registerRenderers()
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
		TileEntity tileentity = world.getBlockTileEntity(x, y, z);
		if (tileentity != null && tileentity instanceof TileEntityOzen && ID == Decorations.guiIdOzen)
		{
			return new GuiOzen(player.inventory, (TileEntityOzen)tileentity);
		}
		else
		{
			return null;
		}
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity tileentity = world.getBlockTileEntity(x, y, z);
		if (tileentity != null && tileentity instanceof TileEntityOzen && ID == Decorations.guiIdOzen)
		{
			return new ContainerOzen(player.inventory, (TileEntityOzen)tileentity);
		}
		else
		{
			return null;
		}
	}
}