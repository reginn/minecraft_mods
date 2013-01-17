package rgn.mods.lamp.client;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraftforge.client.MinecraftForgeClient;

import rgn.mods.lamp.CommonProxy;

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
		MinecraftForgeClient.preloadTexture("/rgn/sprites/lamp/blocks.png");
	}

	@Override
	public void registerRenderers()
	{
		RenderingRegistry.registerBlockHandler(new LampRenderingHandler());
	}

}