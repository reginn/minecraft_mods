package rgn.mods.dawnbreaker.client;

import net.minecraft.src.*;

import net.minecraftforge.client.MinecraftForgeClient;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

import rgn.mods.dawnbreaker.CommonProxy;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy
{
	@Override
	public void regiseterTextures()
	{
		MinecraftForgeClient.preloadTexture("/rgn/sprites/dawnbreaker/items.png");
	}
}