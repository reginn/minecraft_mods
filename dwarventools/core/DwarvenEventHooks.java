package rgn.mods.dwarventools.core;

import java.util.Map;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
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
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;

public class DwarvenEventHooks
{
	private final Enchantment critical    = DwarvenEnchantment.enchantmentCriticalStrike;
	private final Enchantment executioner = DwarvenEnchantment.enchantmentExecutioner;
	private final Enchantment vitalize    = DwarvenEnchantment.enchantmentVitalize;

	@ForgeSubscribe
	public void onSwordDestroyed(PlayerDestroyItemEvent event)
	{
		EntityPlayer player = event.entityPlayer;
		ItemStack itemstack = event.original;
		World         world = player.worldObj;

		if (event.isCancelable())
		{
			event.setCanceled(true);
		}

		ItemStack dropItem = null;
		if (itemstack.itemID == DwarvenItem.itemDwarvenSwordMithril.itemID)
		{
			dropItem = new ItemStack(DwarvenItem.itemDwarvenBrokenSwordMithril, 1);
		}
		else if(itemstack.itemID == DwarvenItem.itemDwarvenSwordEbony.itemID)
		{
			dropItem = new ItemStack(DwarvenItem.itemDwarvenBrokenSwordEbony, 1);
		}

		if (dropItem != null)
		{
			if (!world.isRemote)
			{
				world.spawnEntityInWorld(new EntityItem(world, player.posX, player.posY, player.posZ, dropItem));
			}
		}
	}

	@ForgeSubscribe
	public void doCriticalStrike(AttackEntityEvent event)
	{
		EntityPlayer player = event.entityPlayer;
		Entity target       = event.target;
		ItemStack equippedItem = player.getCurrentEquippedItem();

		if (equippedItem != null && this.isItemUniqueEnchanted(equippedItem, this.critical))
		{
			int[] prob = new int[]{30, 20, 10};
			float[] damageRatio = new float[]{1.5F, 2.5F, 3.5F};

			if (target instanceof EntityLiving)
			{
				EntityLiving entityLiving = (EntityLiving)target;

				int enchLv = this.getUniqueEnchantmentLv(equippedItem, this.critical);
				float baseDamage = equippedItem.getItem().getDamageVsEntity(target);

				enchLv = enchLv > this.critical.getMaxLevel() ? this.critical.getMaxLevel() : enchLv;

				if (target.worldObj.rand.nextInt(100) <= prob[enchLv - 1])
				{
					for (int i = 0; i < 16; ++i)
					{
						double dx = (double)(target.worldObj.rand.nextFloat() * 2.0F - 1.0F);
						double dy = (double)(target.worldObj.rand.nextFloat() * 2.0F - 1.0F);
						double dz = (double)(target.worldObj.rand.nextFloat() * 2.0F - 1.0F);

						if (dx * dx + dy * dy + dz * dz <= 1.0D)
						{
							double x = target.posX + dx * (double)target.width / 4.0D;
							double y = target.boundingBox.minY + (double)(target.height / 2.0F) + dy * (double)target.height / 4.0D;
							double z = target.posZ + dz * (double)target.width / 4.0D;
							player.worldObj.spawnParticle("crit", x, y, z, dx, dy + 0.2D, dz);
						}
					}

					player.addChatMessage("Critical Strike!");
					target.attackEntityFrom(DamageSource.causePlayerDamage(player), (int)(baseDamage * damageRatio[enchLv - 1]));
				}
			}
		}
	}

	@ForgeSubscribe
	public void doExecution(LivingDropsEvent event)
	{
		Entity target        = event.entity;
		DamageSource source  = event.source;
		int lootingLevel     = event.lootingLevel;
		int specialDropValue = event.specialDropValue;

		int mobType = this.getMobType(target);

		if (mobType < 0)
		{
			return ;
		}

		if (source.getEntity() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)source.getEntity();
			ItemStack equippedItem = player.getCurrentEquippedItem();

			if (equippedItem != null && this.isItemUniqueEnchanted(equippedItem, this.executioner))
			{
				int enchLv = this.getUniqueEnchantmentLv(equippedItem, this.executioner);
				enchLv = enchLv > this.executioner.getMaxLevel() ? this.executioner.getMaxLevel() : enchLv;

				if (specialDropValue < enchLv * 20 * (lootingLevel + 1))
				{
					event.drops.add(
						new EntityItem(
							target.worldObj, target.posX, target.posY, target.posZ,
							new ItemStack(Item.skull, 1, mobType)
						));
					player.addChatMessage("Executed!");
				}
			}
		}
	}

	@ForgeSubscribe
	public void doVitalize(LivingHurtEvent event)
	{
		Entity entity       = event.entity;
		DamageSource source = event.source;
		int ammount         = event.ammount;

		if (entity instanceof EntityPlayer && source.getEntity() != null)
		{
			EntityPlayer player = (EntityPlayer)entity;
			ItemStack torso = player.getCurrentArmor(2);

			if (torso != null && this.isItemUniqueEnchanted(torso, this.vitalize))
			{
				int enchLv = this.getUniqueEnchantmentLv(torso, this.vitalize);
				enchLv = enchLv > this.vitalize.getMaxLevel() ? this.vitalize.getMaxLevel() : enchLv;

				if (player.getHealth() <= player.getMaxHealth() / 2)
				{
					if (player.worldObj.rand.nextInt(100) < 35 - (enchLv * 5))
					{
						for (int i = 0; i < 16; ++i)
						{
							double dx = (double)(player.worldObj.rand.nextFloat() * 2.0F - 1.0F);
							double dy = (double)(player.worldObj.rand.nextFloat() * 2.0F - 1.0F);
							double dz = (double)(player.worldObj.rand.nextFloat() * 2.0F - 1.0F);

							if (dx * dx + dy * dy + dz * dz <= 1.0D)
							{
								double x = player.posX + dx * (double)player.width / 4.0D;
								double y = player.boundingBox.minY + (double)(player.height / 2.0F) + dy * (double)player.height / 4.0D;
								double z = player.posZ + dz * (double)player.width / 4.0D;
								player.worldObj.spawnParticle("crit", x, y, z, dx, dy + 0.2D, dz);
							}
						}

						player.addChatMessage("Vitalize!");
						player.heal(ammount * (enchLv + 1));
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

	private boolean isItemUniqueEnchanted(ItemStack itemstack, Enchantment uniqueEnchantment)
	{
		return EnchantmentHelper.getEnchantments(itemstack).keySet().contains(uniqueEnchantment.effectId);
	}

	private int getUniqueEnchantmentLv(ItemStack itemstack, Enchantment uniqueEnchantment)
	{
		Map<Integer, Integer> enchantments = EnchantmentHelper.getEnchantments(itemstack);
		return enchantments.get(uniqueEnchantment.effectId).intValue();
	}

}
