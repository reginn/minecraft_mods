package rgn.mods.mabicraft.core;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import cpw.mods.fml.common.network.IGuiHandler;

import rgn.mods.mabicraft.client.gui.GuiBonfire;
import rgn.mods.mabicraft.client.gui.GuiCookware;
import rgn.mods.mabicraft.client.gui.GuiEnchanter;
import rgn.mods.mabicraft.inventory.ContainerBonfire;
import rgn.mods.mabicraft.inventory.ContainerCookware;
import rgn.mods.mabicraft.inventory.ContainerEnchanter;
import rgn.mods.mabicraft.inventory.EnumGuiID;

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
		else if (ID == EnumGuiID.COOKING_TABLE.ordinal())
		{
			return new GuiCookware(player, world, x, y, z, 0);
		}
		else if (ID == EnumGuiID.COOKING_POT.ordinal())
		{
			return new GuiCookware(player, world, x, y, z, 1);
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
		else if (ID == EnumGuiID.COOKING_TABLE.ordinal())
		{
			return new ContainerCookware(player, world, x, y, z, 0);
		}
		else if (ID == EnumGuiID.COOKING_POT.ordinal())
		{
			return new ContainerCookware(player, world, x, y, z, 1);
		}
		return null;
	}

}