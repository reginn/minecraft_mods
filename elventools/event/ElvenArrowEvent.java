package rgn.mods.elventools.event;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;

import rgn.mods.elventools.entity.EntityElvenArrow;
import rgn.mods.elventools.entity.EntityRopeArrow;
import rgn.mods.elventools.entity.EntityTorchArrow;
import rgn.mods.elventools.item.ElvenItem;
import rgn.mods.elventools.item.ItemElvenBow;

public class ElvenArrowEvent implements IForgeEvent
{
	List<Integer> elvenArrowList = Lists.newArrayList(ElvenItem.itemTorchArrow.itemID, ElvenItem.itemRopeArrow.itemID);
	
	@ForgeSubscribe
	public void onArrowNock(ArrowNockEvent event)
	{
		EntityPlayer player = event.entityPlayer;
		ItemStack       bow = event.result;

		if (((ItemElvenBow)bow.getItem()).isCreativeMode(player) || this.hasElvenArrow(player))
		{
			player.setItemInUse(bow, bow.getItem().getMaxItemUseDuration(bow));
			event.setCanceled(true);
		}

	}
	
	public boolean hasElvenArrow(EntityPlayer player)
	{
		boolean hasArrow = false;
		
		for (int itemId : this.elvenArrowList)
		{
			hasArrow |= player.inventory.hasItem(itemId);
		}
		return hasArrow;
	}
	
	@ForgeSubscribe
	public void onArrowLoose(ArrowLooseEvent event)
	{
		EntityPlayer player = event.entityPlayer;
		ItemStack       bow = event.bow;
		int heldTime        = event.charge;
		World world         = player.worldObj;

		boolean isInfinity = this.isInfinity(player, bow);
		float velocityRatio = bow.getItem() instanceof ItemElvenBow ? ((ItemElvenBow)bow.getItem()).getVelocityRatio() : 1.0F;
		
		heldTime *= bow.getItem() instanceof ItemElvenBow ? ((ItemElvenBow)bow.getItem()).getChargeSpeedRatio() : 1;
		
		float baseVelocity = (float)heldTime / 20.0F;
		baseVelocity = (baseVelocity * baseVelocity + baseVelocity * 2.0F) / 3.0F;

		if ((double)baseVelocity < 0.1D)
		{
			return;
		}

		if (baseVelocity > 1.0F)
		{
			baseVelocity = 1.0F;
		}

		EntityElvenArrow entityArrow = this.newEntityElvenArrow(world, player, baseVelocity * 2.0F * velocityRatio);

		if (entityArrow != null && (isInfinity || this.hasElvenArrow(player)))
		{
			if (bow.getItem() instanceof ItemElvenBow)
			{
				entityArrow.setDamage(((ItemElvenBow)(bow.getItem())).getBaseDamage());
			}
			
			if (baseVelocity == 1.0F)
			{
				entityArrow.setIsCritical(true);
			}

			int powerLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, bow);

			if (powerLevel > 0)
			{
				entityArrow.setDamage(entityArrow.getDamage() + (double)powerLevel * 0.5D + 0.5D);
			}

			int punchLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, bow);

			if (punchLevel > 0)
			{
				entityArrow.setKnockbackStrength(punchLevel);
			}

			if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, bow) > 0)
			{
				entityArrow.setFire(100);
			}

			bow.damageItem(1, player);
			world.playSoundAtEntity(player, "random.bow", 1.0F, 1.0F / (world.rand.nextFloat() * 0.4F + 1.2F) + baseVelocity * 0.5F);

			if (isInfinity)
			{
				entityArrow.canBePickedUp = 2;
			}
			else
			{
				player.inventory.consumeInventoryItem(this.getConsumeItemID(entityArrow));
			}

			if (!world.isRemote)
			{
				world.spawnEntityInWorld(entityArrow);
			}
			event.setCanceled(true);
		}
	}

	public boolean isInfinity(EntityPlayer player, ItemStack bow)
	{
		if (bow.getItem() instanceof ItemElvenBow)
		{
			return ((ItemElvenBow)bow.getItem()).isCreativeMode(player) || ((ItemElvenBow)bow.getItem()).isInfinity(bow);
		}
		return player.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, bow) > 0;
	}
	
	public EntityElvenArrow newEntityElvenArrow(World world, EntityPlayer player, float velocity)
	{
		if (player.inventory.hasItem(ElvenItem.itemTorchArrow.itemID))
		{
			return new EntityTorchArrow(world, player, velocity);
		}
		if (player.inventory.hasItem(ElvenItem.itemRopeArrow.itemID))
		{
			return new EntityRopeArrow(world, player, velocity);
		}
		return null;
	}
	
	public int getConsumeItemID(EntityElvenArrow entityArrow)
	{
		if (entityArrow instanceof EntityTorchArrow)
		{
			return ElvenItem.itemTorchArrow.itemID;
		}
		if (entityArrow instanceof EntityRopeArrow)
		{
			return ElvenItem.itemRopeArrow.itemID;
		}
		return Item.arrow.itemID;
	}
}