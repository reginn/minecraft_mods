package rgn.mods.elventools;

import java.io.*;
import java.util.Random;

import net.minecraft.src.*;
import net.minecraftforge.common.*;

import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;
import net.minecraftforge.event.entity.player.BonemealEvent;

public class ElvenEventHooks
{
	private Random random = new Random();
	
	@ForgeSubscribe
	public void onArrowNock(ArrowNockEvent event)
	{
		EntityPlayer player = event.entityPlayer;
		ItemStack itemstack = event.result;
		
		if (player.capabilities.isCreativeMode
		 || player.inventory.hasItem(ElvenTools.itemTorchArrow.shiftedIndex)
		 || player.inventory.hasItem(ElvenTools.itemRopeArrow.shiftedIndex))
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
		World world         = player.worldObj;//ElvenTools.proxy.getServerWorld();
		
		boolean isInfinity = player.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, itemstack) > 0;
		float damageRatio = itemstack.getItem() instanceof ItemEnhancedBows ? ((ItemEnhancedBows)itemstack.getItem()).getDamageRatio() : 1.0F;
		
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
		
		//if (!world.isRemote)
		//{
			if (player.inventory.hasItem(ElvenTools.itemTorchArrow.shiftedIndex))
			{
				//entityArrow = new EntityTorchArrow(serverWorld, player, baseDamage * 2.0F * damageRatio);
				entityArrow = new EntityTorchArrow(world, player, baseDamage * 2.0F * damageRatio);
			}
			else if (player.inventory.hasItem(ElvenTools.itemRopeArrow.shiftedIndex))
			{
				//entityArrow = new EntityRopeArrow(serverWorld, player, baseDamage * 2.0F * damageRatio);
				entityArrow = new EntityRopeArrow(world, player, baseDamage * 2.0F * damageRatio);
			}
			else
			{
				return ;
			}
		//}
		
		if (isInfinity
		 || player.inventory.hasItem(ElvenTools.itemTorchArrow.shiftedIndex)
		 || player.inventory.hasItem(ElvenTools.itemRopeArrow.shiftedIndex))
		{
			
			if (baseDamage == 1.0F)
			{
				entityArrow.func_70243_d(true);
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
					player.inventory.consumeInventoryItem(ElvenTools.itemTorchArrow.shiftedIndex);
				}
				else if(entityArrow instanceof EntityRopeArrow)
				{
					player.inventory.consumeInventoryItem(ElvenTools.itemRopeArrow.shiftedIndex);
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
		
		
		if (blockID == ElvenTools.blockEbonySapling.blockID)
		{
			if (!world.isRemote)
			{
				((BlockEbonySapling)ElvenTools.blockEbonySapling).growTree(world, x, y, z, world.rand);
			}
			event.setHandeled();
		}
	}
}
