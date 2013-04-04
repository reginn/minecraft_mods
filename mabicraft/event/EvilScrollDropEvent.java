package rgn.mods.mabicraft.event;

import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDropsEvent;

import rgn.mods.mabicraft.item.MabiCraftItem;
import rgn.mods.mabicraft.registry.EvilScrollRegistry;

public class EvilScrollDropEvent implements IForgeEvent
{
	@ForgeSubscribe
	public void addDropItemFromVanillaMob(LivingDropsEvent event)
	{
		EntityLiving  target = event.entityLiving;
		World          world = target.worldObj;
		int     lootingLevel = event.lootingLevel;
		int specialDropValue = event.specialDropValue;

		/*
		 public final DamageSource source;
		 public final ArrayList<EntityItem> drops;
		 public final int lootingLevel;
		 public final boolean recentlyHit;
		 public final int specialDropValue;
		*/

		for (Integer metadata : EvilScrollRegistry.instance().getAllMetadata())
		{
			int entityTypeID = EntityList.getEntityID(target);
			if (entityTypeID == metadata.intValue() &&
				(event.source.getDamageType().equals("player") || event.source.getDamageType().equals("arrow")))
			{
				if (specialDropValue > 5 &&
					specialDropValue < EvilScrollRegistry.instance().getDropRateFromMetadata(entityTypeID) * (lootingLevel + 1))
					{
						event.drops.add(
							new EntityItem(
								world, target.posX, target.posY, target.posZ,
								new ItemStack(MabiCraftItem.itemEvilScroll, 1, metadata.intValue())
							));
					}
			}
		}
	}

}