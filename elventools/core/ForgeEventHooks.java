package rgn.mods.elventools.core;

import java.util.Random;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.Event;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;
import net.minecraftforge.event.entity.player.BonemealEvent;
import rgn.mods.elventools.block.BlockEbonySapling;
import rgn.mods.elventools.entity.EntityArrowBase;
import rgn.mods.elventools.entity.EntityRopeArrow;
import rgn.mods.elventools.entity.EntityTorchArrow;
import rgn.mods.elventools.item.ItemEnhancedBow;

public class ForgeEventHooks
{
	private Random random = new Random();

	@ForgeSubscribe
	public void onArrowNock(ArrowNockEvent event)
	{
		EntityPlayer player = event.entityPlayer;
		ItemStack itemstack = event.result;

		if (player.capabilities.isCreativeMode
		 || player.inventory.hasItem(ElvenItem.itemTorchArrow.shiftedIndex)
		 || player.inventory.hasItem(ElvenItem.itemRopeArrow.shiftedIndex))
		{
			player.setItemInUse(itemstack, Item.bow.getMaxItemUseDuration(itemstack));
			event.setCanceled(true);
		}

	}

	@ForgeSubscribe
	public void onArrowLoose(ArrowLooseEvent event)
	{
		EntityPlayer player = event.entityPlayer;
		ItemStack itemstack = event.bow;
		int heldTime        = event.charge;
		World world         = player.worldObj;

		boolean isInfinity = player.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, itemstack) > 0;
		float damageRatio = itemstack.getItem() instanceof ItemEnhancedBow ? ((ItemEnhancedBow)itemstack.getItem()).getDamageRatio() : 1.0F;

		float baseDamage = (float)heldTime / 20.0F;
		baseDamage = (baseDamage * baseDamage + baseDamage * 2.0F) / 3.0F;

		if ((double)baseDamage < 0.1D)
		{
			return;
		}

		if (baseDamage > 1.0F)
		{
			baseDamage = 1.0F;
		}

		EntityArrowBase entityArrow = null;

		if (player.inventory.hasItem(ElvenItem.itemTorchArrow.shiftedIndex))
		{
			entityArrow = new EntityTorchArrow(world, player, baseDamage * 2.0F * damageRatio);
		}
		else if (player.inventory.hasItem(ElvenItem.itemRopeArrow.shiftedIndex))
		{
			entityArrow = new EntityRopeArrow(world, player, baseDamage * 2.0F * damageRatio);
		}
		else
		{
			return ;
		}

		if (isInfinity
		 || player.inventory.hasItem(ElvenItem.itemTorchArrow.shiftedIndex)
		 || player.inventory.hasItem(ElvenItem.itemRopeArrow.shiftedIndex))
		{

			if (baseDamage == 1.0F)
			{
				entityArrow.setIsCritical(true);
			}

			int powerLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, itemstack);

			if (powerLevel > 0)
			{
				entityArrow.setDamage(entityArrow.getDamage() + (double)powerLevel * 0.5D + 0.5D);
			}

			int punchLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, itemstack);

			if (punchLevel > 0)
			{
				entityArrow.setKnockbackStrength(punchLevel);
			}

			if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, itemstack) > 0)
			{
				entityArrow.setFire(100);
			}

			itemstack.damageItem(1, player);
			world.playSoundAtEntity(player, "random.bow", 1.0F, 1.0F / (random.nextFloat() * 0.4F + 1.2F) + baseDamage * 0.5F);

			if (isInfinity)
			{
				entityArrow.canBePickedUp = 2;
			}
			else
			{
				if (entityArrow instanceof EntityTorchArrow)
				{
					player.inventory.consumeInventoryItem(ElvenItem.itemTorchArrow.shiftedIndex);
				}
				else if(entityArrow instanceof EntityRopeArrow)
				{
					player.inventory.consumeInventoryItem(ElvenItem.itemRopeArrow.shiftedIndex);
				}
				else
				{
					return ;
				}
			}

			if (!world.isRemote)
			{
				if (entityArrow instanceof EntityTorchArrow)
				{
					world.spawnEntityInWorld(entityArrow);
				}
				else if(entityArrow instanceof EntityRopeArrow)
				{
					world.spawnEntityInWorld(entityArrow);
				}
				else
				{
					return ;
				}
			}
			event.setCanceled(true);
		}
	}

	@ForgeSubscribe
	public void onBonemealUsed(BonemealEvent event)
	{
		EntityPlayer player = event.entityPlayer;
		World         world = event.world;
		int         blockID = event.ID;
		int               x = event.X;
		int               y = event.Y;
		int               z = event.Z;


		if (blockID == ElvenBlock.blockEbonySapling.blockID)
		{
			if (!world.isRemote)
			{
				((BlockEbonySapling)ElvenBlock.blockEbonySapling).growTree(world, x, y, z, world.rand);
				event.setResult(Event.Result.ALLOW);
			}
		}
		event.setResult(Event.Result.DENY);
	}
}
