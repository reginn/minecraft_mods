package rgn.mods.elventools.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityEnchantmentTableParticleFX;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.EntityPortalFX;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import cpw.mods.fml.client.FMLClientHandler;

public class ElvenEffectRenderer
{
	public void spawnCustomParticle(World world, EntityPlayer player, Entity entity, int typeId)
	{
		if (typeId < 0 || typeId > 1 || world == null || entity == null)
		{
			return ;
		}

		Minecraft mc = FMLClientHandler.instance().getClient();
		EntityFX entityFX = null;

		for (int i = 0; i < 4; ++i)
		{
			double dx = (double)(world.rand.nextFloat() * 2.0F - 1.0F);
			double dy = (double)(world.rand.nextFloat() * 2.0F - 1.0F);
			double dz = (double)(world.rand.nextFloat() * 2.0F - 1.0F);

			if (dx * dx + dy * dy + dz * dz <= 1.0D)
			{
				double x = entity.posX + dx * (double)entity.width / 4.0D;
				double y = entity.boundingBox.minY + (double)(entity.height / 2.0F) + dy * (double)entity.height / 4.0D;
				double z = entity.posZ + dz * (double)entity.width / 4.0D;
				entityFX = newEffectFromID(typeId, world, x, y, z, dx, dy, dz);

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
			entityFX = new EntityPortalFX(world, x, y, z, vx, vy, vz);
			return entityFX;
		}
		else if (typeId == 1)
		{
			entityFX = new EntityEnchantmentTableParticleFX(world, x, y, z, vx, vy, vz);
			return entityFX;
		}
		else
		{
			return null;
		}
	}
}