package rgn.mods.mabicraft;

import net.minecraft.src.*;

import net.minecraftforge.event.ForgeSubscribe;

import net.minecraftforge.event.entity.living.LivingDropsEvent;

public class EntityLivingEventHooks
{
	@ForgeSubscribe
	public void addDropItem(LivingDropsEvent event)
	{
		EntityLiving target = event.entityLiving;
		World         world = target.worldObj;
		
		/*
		 public final DamageSource source;
		 public final ArrayList<EntityItem> drops;
		 public final int lootingLevel;
		 public final boolean recentlyHit;
		 public final int specialDropValue;
		*/
		
		Class<?>[] entityClassList = 
			{
				EntityZombie.class, EntitySkeleton.class, EntityCreeper.class, EntitySpider.class, 
				EntitySlime.class, EntityEnderman.class, EntitySilverfish.class, EntityCaveSpider.class, 
				EntityPigZombie.class, EntityGhast.class, EntityMagmaCube.class, EntityBlaze.class
			};
		
		int[] rarity = 
			{
				10, 10, 10, 10,
				 7,  8,  8,  8,
				 6,  6,  6,  6
			};
		
		for (int i = 0; i < entityClassList.length; ++i)
		{
			if (target.getClass() == entityClassList[i])
			{
				if (event.source.getDamageType().equals("player"))
				{
					if (event.specialDropValue > 5 && event.specialDropValue < rarity[i])
					{
						event.drops.add(new EntityItem(world, target.posX, target.posY, target.posZ, new ItemStack(MabiCraft.itemEvilScroll, 1, EntityList.getEntityID(target))));
					}
				}
			}
		}
		
	}
	
	
}