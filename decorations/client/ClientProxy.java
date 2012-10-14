package rgn.mods.decorations.client;

import net.minecraft.src.*;
import net.minecraftforge.client.MinecraftForgeClient;

import cpw.mods.fml.client.registry.RenderingRegistry;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

import cpw.mods.fml.common.registry.TickRegistry;

import rgn.mods.decorations.CommonProxy;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy
{
	@Override
	public void registerTextures()
	{
		MinecraftForgeClient.preloadTexture("/rgn/sprites/decorations/blocks.png");
	}
	
	@Override
	public void registerRenderers()
	{
	}
}