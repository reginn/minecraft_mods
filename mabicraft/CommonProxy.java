package rgn.mods.mabicraft;

import net.minecraft.src.*;

import cpw.mods.fml.common.network.IGuiHandler;

import rgn.mods.mabicraft.config.*;

import rgn.mods.mabicraft.client.*;
import rgn.mods.mabicraft.enchant.*;


public class CommonProxy implements IGuiHandler
{
	public int getUniqueRenderType()
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
		if (ID == EnumGuiID.BONFIRE.ordinal())
		{
			return new GuiBonfire(player, world, x, y, z);
		}
		else if (ID == EnumGuiID.ENCHANTER.ordinal())
		{
			return new GuiEnchanter(player, world, x, y, z);
		}
		return null;
	}
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		if (ID == EnumGuiID.BONFIRE.ordinal())
		{
			return new ContainerBonfire(player, world, x, y, z);
		}
		else if (ID == EnumGuiID.ENCHANTER.ordinal())
		{
			return new ContainerEnchanter(player, world, x, y, z);
		}
		return null;
	}
	
}