package rgn.mods.mabicraft;

import net.minecraft.src.*;

import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDropsEvent;

import rgn.mods.mabicraft.config.MabiCraftItem;
import rgn.mods.mabicraft.registry.EvilScrollRegistry;

public class ForgeEventHooks
{
	@ForgeSubscribe
	public void addDropItem(LivingDropsEvent event)
	{
		EntityLiving target = event.entityLiving;
		World         world = target.worldObj;
		int    lootingLevel = event.lootingLevel;
		
		/*
		 public final DamageSource source;
		 public final ArrayList<EntityItem> drops;
		 public final int lootingLevel;
		 public final boolean recentlyHit;
		 public final int specialDropValue;
		*/

		for (int i = 0; i < EvilScrollRegistry.instance().getClassListSize(); ++i)
		{
			if (target.getClass() == EvilScrollRegistry.instance().getEntityClass(i) 
				&& (event.source.getDamageType().equals("player") || event.source.getDamageType().equals("arrow")))
			{
				if (event.specialDropValue > 5 &&
					event.specialDropValue < EvilScrollRegistry.instance().getRarityFromClass(target.getClass()) * (lootingLevel + 1))
				{
					event.drops.add(
						new EntityItem(
							world, target.posX, target.posY, target.posZ, 
							new ItemStack(MabiCraftItem.itemEvilScroll, 1, EvilScrollRegistry.instance().getMetadataFromClass(target.getClass()))
						));
				}
			}
		}
		
	}
	
	
}