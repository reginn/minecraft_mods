package rgn.mods.dwarventools.core;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import cpw.mods.fml.common.network.IGuiHandler;

import rgn.mods.dwarventools.client.GuiInfernalFurnace;
import rgn.mods.dwarventools.config.Config;
import rgn.mods.dwarventools.inventory.ContainerInfernalFurnace;
import rgn.mods.dwarventools.tileentity.TileEntityInfernalFurnace;

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

	public void spawnCustomParticle(World world, EntityPlayer player, Entity entity, int particleId)
	{
	}

	// implements IGuiHandler
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntityInfernalFurnace tileentity = (TileEntityInfernalFurnace)world.getBlockTileEntity(x, y, z);
		if (tileentity != null && ID == Config.guiIdInfernalFurnace)
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
		if (tileentity != null && ID == Config.guiIdInfernalFurnace)
		{
			return (new ContainerInfernalFurnace(player.inventory, tileentity));
		}
		else
		{
			return null;
		}
	}
}