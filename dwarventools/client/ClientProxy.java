package rgn.mods.dwarventools.client;

import org.lwjgl.opengl.GL11;

import net.minecraft.src.*;
import net.minecraftforge.client.*;

import cpw.mods.fml.client.*;
import cpw.mods.fml.client.registry.*;

import rgn.mods.dwarventools.*;

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
	public void registerRenderers()
	{
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