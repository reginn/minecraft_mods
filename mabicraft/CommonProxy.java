package rgn.mods.mabicraft;

import net.minecraft.src.*;
import cpw.mods.fml.common.network.*;
import cpw.mods.fml.common.*;

import rgn.mods.mabicraft.client.*;

public class CommonProxy implements IGuiHandler
{
	public int getNewRenderType()
	{
		return -1;
	}
	
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
	
	// implements IGuiHandler
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		if (ID == 0)
		{
			return new GuiBonfire(player, world, x, y, z);
		}
		else if (ID == 1)
		{
			return new GuiEnchanter(player, world, x, y, z);
		}
		return null;
	}
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		if (ID == 0)
		{
			return new ContainerBonfire(player, world, x, y, z);
		}
		else if (ID == 1)
		{
			return new ContainerEnchanter(player, world, x, y, z);
		}
		return null;
	}
	
}