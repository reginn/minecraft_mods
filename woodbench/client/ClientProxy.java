package rgn.mods.woodbench.client;

import net.minecraft.world.World;
import rgn.mods.woodbench.CommonProxy;
import rgn.mods.woodbench.EntityDummy;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy
{
	@Override
	public int getBlockNewRenderType()
	{
		return RenderingRegistry.getNextAvailableRenderId();
	}

	@Override
	public void registerRenderers()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityDummy.class, new RenderDummy());
		RenderingRegistry.registerBlockHandler(new WoodBenchBlockRenderingHandler());
	}

	@Override
	public World getClientWorld()
	{
		return FMLClientHandler.instance().getClient().theWorld;
	}
}