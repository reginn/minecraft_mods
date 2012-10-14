package rgn.mods.mabicraft.client;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

import cpw.mods.fml.common.network.PacketDispatcher;

import cpw.mods.fml.client.*;
import cpw.mods.fml.client.registry.*;

import net.minecraft.src.*;
import net.minecraftforge.client.*;

import rgn.mods.mabicraft.*;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy
{
	@Override
	public int getNewRenderType()
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
		RenderingRegistry.registerBlockHandler(new EnchanterRenderingHandler());
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