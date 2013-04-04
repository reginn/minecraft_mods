package rgn.mods.rum.client;

import net.minecraft.client.renderer.ChestItemRenderHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

import rgn.mods.rum.client.gui.GuiLockedChest;
import rgn.mods.rum.client.renderer.LockedChestRenderHelper;
import rgn.mods.rum.client.renderer.LockedChestRenderingHandler;
import rgn.mods.rum.client.renderer.TileEntityLockedChestRenderer;
import rgn.mods.rum.core.CommonProxy;
import rgn.mods.rum.tileentity.TileEntityLockedChest;

public class ClientProxy extends CommonProxy
{
	@Override
	public int getUniqueRenderType()
	{
		return RenderingRegistry.getNextAvailableRenderId();
	}

	@Override
	public void registerRenderer()
	{
		ChestItemRenderHelper.instance = new LockedChestRenderHelper();
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLockedChest.class, TileEntityLockedChestRenderer.instance());
		RenderingRegistry.registerBlockHandler(LockedChestRenderingHandler.instance());
	}

	@Override
	public World getClientWorld()
	{
		return FMLClientHandler.instance().getClient().theWorld;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		if (tileEntity != null && tileEntity instanceof TileEntityLockedChest && ID == 0)
		{
			return new GuiLockedChest(player.inventory, (TileEntityLockedChest)tileEntity);
		}
		else
		{
			return null;
		}	}


}
