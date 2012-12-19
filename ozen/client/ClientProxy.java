package rgn.mods.ozen.client;

import net.minecraft.world.World;
import rgn.mods.ozen.CommonProxy;
import rgn.mods.ozen.TileEntityOzen;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy
{
	@Override
	public int getUniqueRenderID()
	{
		return RenderingRegistry.getNextAvailableRenderId();
	}

	@Override
	public void registerRenderers()
	{
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityOzen.class, new TileEntityOzenRenderer());
		RenderingRegistry.registerBlockHandler(new OzenRenderingHandler());
	}

	@Override
	public World getClientWorld()
	{
		return FMLClientHandler.instance().getClient().theWorld;
	}
}