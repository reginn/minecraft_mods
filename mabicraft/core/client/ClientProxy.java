package rgn.mods.mabicraft.core.client;

import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import rgn.mods.mabicraft.cook.client.CookwareRenderingHandler;
import rgn.mods.mabicraft.core.CommonProxy;
import rgn.mods.mabicraft.enchant.client.BonfireRenderingHandler;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

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
		RenderingRegistry.registerBlockHandler(new CookwareRenderingHandler());
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