package rgn.mods.elventools.client;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraftforge.client.MinecraftForgeClient;

import rgn.mods.elventools.core.CommonProxy;
import rgn.mods.elventools.entity.EntityEbonyBoat;
import rgn.mods.elventools.entity.EntityRopeArrow;
import rgn.mods.elventools.entity.EntityTorchArrow;

@SideOnly(Side.CLIENT)
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
		/*
		MinecraftForgeClient.registerItemRenderer(ElvenItem.itemLeatherLongbow.shiftedIndex,   new EnhancedBowRenderer());
		MinecraftForgeClient.registerItemRenderer(ElvenItem.itemCompositeLongbow.shiftedIndex, new EnhancedBowRenderer());
		MinecraftForgeClient.registerItemRenderer(ElvenItem.itemEnhancedLongbow.shiftedIndex,  new EnhancedBowRenderer());
		*/
	}

}