package rgn.mods.dawnbreaker.client;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import rgn.mods.dawnbreaker.CommonProxy;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy
{
	@Override
	public void regiseterTextures()
	{
		//MinecraftForgeClient.preloadTexture("/rgn/sprites/dawnbreaker/items.png");
	}
}