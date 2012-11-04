package rgn.mods.elventools.client;

import net.minecraft.src.*;

import net.minecraftforge.client.MinecraftForgeClient;

import cpw.mods.fml.client.registry.RenderingRegistry;

import rgn.mods.elventools.CommonProxy;
import rgn.mods.elventools.ElvenItem;
import rgn.mods.elventools.EntityEbonyBoat;
import rgn.mods.elventools.EntityRopeArrow;
import rgn.mods.elventools.EntityTorchArrow;

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
		RenderingRegistry.registerEntityRenderingHandler(EntityRopeArrow.class,  new RenderRopeArrow());
		RenderingRegistry.registerEntityRenderingHandler(EntityTorchArrow.class, new RenderTorchArrow());
		
		MinecraftForgeClient.registerItemRenderer(ElvenItem.itemLeatherLongbow.shiftedIndex,   new EnhancedBowRenderer());
		MinecraftForgeClient.registerItemRenderer(ElvenItem.itemCompositeLongbow.shiftedIndex, new EnhancedBowRenderer());
		MinecraftForgeClient.registerItemRenderer(ElvenItem.itemEnhancedLongbow.shiftedIndex,  new EnhancedBowRenderer());
		
	}
	
}