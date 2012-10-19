package rgn.mods.decorations.client;

import net.minecraft.src.*;
import net.minecraftforge.client.MinecraftForgeClient;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

import rgn.mods.decorations.CommonProxy;
import rgn.mods.decorations.TileEntityOzen;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy
{
	@Override
	public int getUniqueRenderID()
	{
		return RenderingRegistry.getNextAvailableRenderId();
	}
	
	@Override
	public void registerTextures()
	{
		MinecraftForgeClient.preloadTexture("/rgn/sprites/decorations/blocks.png");
	}
	
	@Override
	public void registerRenderers()
	{
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityOzen.class, new TileEntityOzenRenderer());
		RenderingRegistry.registerBlockHandler(new OzenRenderingHandler());
	}
	
	@Override
	public World getClientWorld()
	{
		return FMLClientHandler.instance().getClient().theWorld;
	}
}