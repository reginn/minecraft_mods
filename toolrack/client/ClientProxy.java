package rgn.mods.toolrack.client;

import net.minecraft.world.World;
import rgn.mods.toolrack.CommonProxy;
import rgn.mods.toolrack.TileEntityToolrack;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
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
	public void registerRenderer()
	{
		RenderingRegistry.registerBlockHandler(new ToolrackRenderingHandler());
	}

	@Override
	public void registerTileEntitySpecialRenderer()
	{
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityToolrack.class, new TileEntityToolrackRenderer());
	}

	@Override
	public World getClientWorld()
	{
		return FMLClientHandler.instance().getClient().theWorld;
	}

}