package rgn.mods.dawnbreaker.client;

import net.minecraftforge.client.MinecraftForgeClient;
import rgn.mods.dawnbreaker.CommonProxy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy
{
	@Override
	public void regiseterTextures()
	{
		MinecraftForgeClient.preloadTexture("/rgn/sprites/dawnbreaker/items.png");
	}
}