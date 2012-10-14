package rgn.mods.elventools.client;

import org.lwjgl.opengl.GL11;

import net.minecraft.src.*;
import net.minecraftforge.client.*;

import cpw.mods.fml.client.*;
import cpw.mods.fml.client.registry.*;

import rgn.mods.elventools.*;

public class ClientProxy extends CommonProxy
{
	@Override
	public void registerTextures()
	{
		MinecraftForgeClient.preloadTexture("/rgn/sprites/elventools/items.png");
		MinecraftForgeClient.preloadTexture("/rgn/sprites/elventools/blocks.png");
	}
	
	@Override
	public void registerRenderers()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityEbonyBoat.class,  new RenderEbonyBoat());
		RenderingRegistry.registerEntityRenderingHandler(EntityTorchArrow.class, new RenderTorchArrow());
		RenderingRegistry.registerEntityRenderingHandler(EntityRopeArrow.class,  new RenderRopeArrow());
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
	
	@Override
	public World getCurrentWorld()
	{
		return getClientWorld();
	}
}