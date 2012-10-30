package rgn.mods.dwarventools.client;

import org.lwjgl.opengl.GL11;

import net.minecraft.src.*;

import net.minecraftforge.client.MinecraftForgeClient;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

import rgn.mods.dwarventools.CommonProxy;

public class ClientProxy extends CommonProxy
{
	@Override
	public int addArmor(String armor)
	{
		return RenderingRegistry.addNewArmourRendererPrefix(armor);
	}
	
	@Override
	public void registerTextures()
	{
		MinecraftForgeClient.preloadTexture("/rgn/sprites/dwarventools/items.png");
		MinecraftForgeClient.preloadTexture("/rgn/sprites/dwarventools/blocks.png");
	}
	
	@Override
	public World getClientWorld()
	{
		return FMLClientHandler.instance().getClient().theWorld;
	}
}