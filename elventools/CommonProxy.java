package rgn.mods.elventools;

import net.minecraft.src.*;

import cpw.mods.fml.common.*;
import cpw.mods.fml.common.network.*;

import net.minecraftforge.common.DimensionManager;

import rgn.mods.elventools.client.*;

public class CommonProxy implements IGuiHandler
{
	public void registerTextures()
	{
	}
	
	public void registerRenderers()
	{
	}
	
	public void registerTileEntitySpecialRenderer()
	{
	}
	
	public World getClientWorld()
	{
		return null;
	}
	
	public World getServerWorld()
	{
		return DimensionManager.getWorld(0);
	}
	
	public World getCurrentWorld()
	{
		return getServerWorld();
	}
	
	// implements IGuiHandler
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return null;
	}
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return null;
	}
}