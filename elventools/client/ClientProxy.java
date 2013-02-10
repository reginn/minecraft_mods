package rgn.mods.elventools.client;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.ForgeHooks;

import rgn.mods.elventools.core.CommonProxy;
import rgn.mods.elventools.entity.EntityEbonyBoat;
import rgn.mods.elventools.entity.EntityRopeArrow;
import rgn.mods.elventools.entity.EntityTorchArrow;
import rgn.mods.elventools.event.BindEvent;
import rgn.mods.elventools.event.EntityPosition;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy
{	
	ElvenEffectRenderer effectRenderer;
	
	public ClientProxy()
	{
		this.effectRenderer = new ElvenEffectRenderer();
	}

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
		RenderingRegistry.registerEntityRenderingHandler(EntityRopeArrow.class,  new RenderElvenArrow());
		RenderingRegistry.registerEntityRenderingHandler(EntityTorchArrow.class, new RenderElvenArrow());

	}
	
	@Override
	public void spawnCustomParticle(World world, EntityPlayer player, Entity entity, int type)
	{
		this.effectRenderer.spawnCustomParticle(world, player, entity, type);
	}
	
	public World getClientWorld()
	{
		return FMLClientHandler.instance().getClient().theWorld;
	}
	
	@Override
	public void setBindInfo(Entity entity)
	{
		BindEvent.bindEntityMap.put(entity.entityId, new EntityPosition(entity.posX, entity.posY, entity.posZ));
	}
}