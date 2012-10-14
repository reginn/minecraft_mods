package rgn.mods.woodbench.client;

import net.minecraft.src.*;

import cpw.mods.fml.client.FMLClientHandler;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

import cpw.mods.fml.client.registry.RenderingRegistry;

import rgn.mods.woodbench.CommonProxy;
import rgn.mods.woodbench.EntityDummy;

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