package rgn.mods.dwarventools.client;

import cpw.mods.fml.client.FMLClientHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.EntityCritFX;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

import net.minecraft.world.World;

public class DwarvenEffectRenderer
{
	public void spawnCustomParticle(World world, EntityPlayer player, Entity entity, int typeId)
	{
		if (typeId < 0)
		{
			return ;
		}
		
		Minecraft mc = FMLClientHandler.instance().getClient();
		EntityFX entityFX = null;
		
		for (int i = 0; i < 16; ++i)
		{
			double dx = (double)(world.rand.nextFloat() * 2.0F - 1.0F);
			double dy = (double)(world.rand.nextFloat() * 2.0F - 1.0F);
			double dz = (double)(world.rand.nextFloat() * 2.0F - 1.0F);
			
			if (dx * dx + dy * dy + dz * dz <= 1.0D)
			{
				double x = entity.posX + dx * (double)entity.width / 4.0D;
				double y = entity.boundingBox.minY + (double)(entity.height / 2.0F) + dy * (double)entity.height / 4.0D;
				double z = entity.posZ + dz * (double)entity.width / 4.0D;
				entityFX = newEffectFromID(typeId, world, x, y, z, dx, dy + 0.2D, dz);
				
				if (entityFX != null)
				{
					mc.effectRenderer.addEffect((EntityFX)entityFX);
				}
			}
		}
		
	}
	
	private EntityFX newEffectFromID(int typeId, World world, double x, double y, double z, double vx, double vy, double vz)
	{
		EntityFX entityFX = null;
		
		if (typeId == 0)
		{
			entityFX = new EntityCritFX(world, x, y, z, vx, vy, vz);
			((EntityFX)entityFX).setRBGColorF(
				((EntityFX)entityFX).getRedColorF()   * 0.3F,
				((EntityFX)entityFX).getBlueColorF()  * 0.8F,
				((EntityFX)entityFX).getGreenColorF() * 0.3F
				);
			return entityFX;
		}
		else if (typeId == 1)
		{
			entityFX = new EntityCritFX(world, x, y, z, vx, vy, vz);
			((EntityFX)entityFX).setRBGColorF(
				((EntityFX)entityFX).getRedColorF()   * 0.8F,
				((EntityFX)entityFX).getBlueColorF()  * 0.3F,
				((EntityFX)entityFX).getGreenColorF() * 0.3F
				);
			return entityFX;
		}
		else if (typeId == 2)
		{
			entityFX = new EntityCritFX(world, x, y, z, vx, vy, vz);
			((EntityFX)entityFX).setRBGColorF(
				((EntityFX)entityFX).getRedColorF()   * 0.3F,
				((EntityFX)entityFX).getBlueColorF()  * 0.3F,
				((EntityFX)entityFX).getGreenColorF() * 0.8F
				);
			return entityFX;
		}
		else
		{
			return null;
		}
	}
}