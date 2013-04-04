package rgn.mods.rum.core;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import cpw.mods.fml.common.network.IGuiHandler;

import rgn.mods.rum.inventory.ContainerLockedChest;
import rgn.mods.rum.tileentity.TileEntityLockedChest;

public class CommonProxy implements IGuiHandler
{
	public int getUniqueRenderType()
	{
		return -1;
	}

	public void registerRenderer()
	{
	}

	public World getClientWorld()
	{
		return null;
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		if (tileEntity != null && tileEntity instanceof TileEntityLockedChest && ID == 0)
		{
			return new ContainerLockedChest(player.inventory, (TileEntityLockedChest)tileEntity);
		}
		else
		{
			return null;
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return null;
	}
}
