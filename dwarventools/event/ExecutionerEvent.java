package rgn.mods.dwarventools.event;

import cpw.mods.fml.common.network.PacketDispatcher;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDropsEvent;

import rgn.mods.dwarventools.enchantment.DwarvenEnchantment;
import rgn.mods.dwarventools.enchantment.UniqueEnchantmentHelper;
import rgn.mods.dwarventools.network.PacketHandler;

public class ExecutionerEvent implements IForgeEvent
{
	private final Enchantment executioner = DwarvenEnchantment.enchantmentExecutioner;
	
	@ForgeSubscribe
	public void doExecution(LivingDropsEvent event)
	{
		Entity        target = event.entity;
		DamageSource  source = event.source;
		int     lootingLevel = event.lootingLevel;
		int specialDropValue = event.specialDropValue;
		World          world = target.worldObj;

		int mobType = this.getMobType(target);
		
		if (mobType < 0)
		{
			return ;
		}

		if (source.getEntity() instanceof EntityPlayer)
		{
			EntityPlayer    player = (EntityPlayer)source.getEntity();
			ItemStack equippedItem = player.getCurrentEquippedItem();

			if (equippedItem != null && UniqueEnchantmentHelper.isItemUniqueEnchanted(equippedItem, this.executioner))
			{
				int enchLv = UniqueEnchantmentHelper.getUniqueEnchantmentLv(equippedItem, this.executioner);
				enchLv = enchLv > this.executioner.getMaxLevel() ? this.executioner.getMaxLevel() : enchLv;

				if (specialDropValue < enchLv * 20 * (lootingLevel + 1))
				{
					if (!world.isRemote)
					{
						event.drops.add(
							new EntityItem(
								world, target.posX, target.posY, target.posZ,
								new ItemStack(Item.skull, 1, mobType)
							));
						PacketDispatcher.sendPacketToAllPlayers(PacketHandler.getPacketCustomAnimation(target, 1));
						player.addChatMessage("Executed!");
					}
				}
			}
		}
	}
	
	private int getMobType(Entity entity)
	{
		if (entity instanceof EntitySkeleton)
		{
			return ((EntitySkeleton)entity).getSkeletonType() == 1 ? 1 : 0;
		}

		return entity instanceof EntityZombie ? 2 : entity instanceof EntityPlayer ? 3 : entity instanceof EntityCreeper ? 4 : -1;
	}
}