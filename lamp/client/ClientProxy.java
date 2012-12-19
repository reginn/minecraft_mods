package rgn.mods.lamp.client;

import net.minecraftforge.client.MinecraftForgeClient;
import rgn.mods.lamp.CommonProxy;
import cpw.mods.fml.client.registry.RenderingRegistry;

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