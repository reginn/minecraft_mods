package rgn.mods.dwarventools.client;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;

import net.minecraftforge.client.MinecraftForgeClient;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import rgn.mods.dwarventools.core.CommonProxy;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy
{
	private DwarvenEffectRenderer effectRenderer;

	public ClientProxy()
	{
		this.effectRenderer = new DwarvenEffectRenderer();
	}

	@Override
	public int addArmor(String armor)
	{
		return RenderingRegistry.addNewArmourRendererPrefix(armor);
	}

	@Override
	public void registerTextures()
	{
		//MinecraftForgeClient.preloadTexture("/rgn/sprites/dwarventools/items.png");
		//MinecraftForgeClient.preloadTexture("/rgn/sprites/dwarventools/blocks.png");
	}

	@Override
	public World getClientWorld()
	{
		return FMLClientHandler.instance().getClient().theWorld;
	}

	@Override
	public void spawnCustomParticle(World world, Entity entity, int particleId)
	{
		this.effectRenderer.spawnCustomParticle(world, entity, particleId);
	}
}