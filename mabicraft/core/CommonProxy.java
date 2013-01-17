package rgn.mods.mabicraft.core;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import rgn.mods.mabicraft.cook.ContainerCookware;
import rgn.mods.mabicraft.cook.client.GuiCookware;
import rgn.mods.mabicraft.enchant.ContainerBonfire;
import rgn.mods.mabicraft.enchant.ContainerEnchanter;
import rgn.mods.mabicraft.enchant.client.GuiBonfire;
import rgn.mods.mabicraft.enchant.client.GuiEnchanter;
import cpw.mods.fml.common.network.IGuiHandler;

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
			return new GuiCookware(player, world, x, y, z);
		}
		else if (ID == EnumGuiID.COOKING_OVEN.ordinal())
		{
			return new GuiCookware(player, world, x, y, z);
		}
		else if (ID == EnumGuiID.COOKING_POT.ordinal())
		{
			return new GuiCookware(player, world, x, y, z);
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
			return new ContainerCookware(player, world, x, y, z);
		}
		else if (ID == EnumGuiID.COOKING_OVEN.ordinal())
		{
			return new ContainerCookware(player, world, x, y, z);
		}
		else if (ID == EnumGuiID.COOKING_POT.ordinal())
		{
			return new ContainerCookware(player, world, x, y, z);
		}
		return null;
	}

}