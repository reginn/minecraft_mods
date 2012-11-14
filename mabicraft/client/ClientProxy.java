package rgn.mods.mabicraft.client;

import net.minecraft.src.*;

import net.minecraftforge.client.MinecraftForgeClient;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

import rgn.mods.mabicraft.CommonProxy;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy
{
	@Override
	public int getUniqueRenderType()
	{
		return RenderingRegistry.getNextAvailableRenderId();
	}
	
	@Override
	public void registerTextures()
	{
		MinecraftForgeClient.preloadTexture("/rgn/sprites/mabicraft/items.png");
	}
	
	@Override
	public void registerRenderers()
	{
		RenderingRegistry.registerBlockHandler(new BonfireRenderingHandler());
	}
	
	@Override
	public void registerTileEntitySpecialRenderer()
	{
	}
	
	@Override
	public World getClientWorld()
	{
		return FMLClientHandler.instance().getClient().theWorld;
	}
	
}