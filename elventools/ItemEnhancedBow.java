package rgn.mods.elventools;

import net.minecraft.src.*;

import net.minecraftforge.common.MinecraftForge;

import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;

public class ItemEnhancedBow extends ItemBow
{
	private int[] animation = new int[3];
	private float ratio;
	private int   enchantability;
	
	public ItemEnhancedBow(int itemId, int spriteId, int maxDamage, float ratio, int enchantability)
	{
		super(itemId);
		this.maxStackSize   = 1;
		this.ratio          = ratio;
		this.enchantability = enchantability;
		this.iconIndex      = spriteId;
		
		this.setMaxDamage(maxDamage);
		this.setFull3D();
		
		for(int i = 0; i < 3; i++)
		{
			animation[i] = this.iconIndex + i + 1;
		}
	}
	
	@Override
	public String getTextureFile()
	{
		return "/rgn/sprites/elventools/items.png";
	}
	
	@Override
	public void onPlayerStoppedUsing(ItemStack itemstack, World world, EntityPlayer player, int par4)
	{
		int var6 = this.getMaxItemUseDuration(itemstack) - par4;
		
		ArrowLooseEvent event = new ArrowLooseEvent(player, itemstack, var6);
		MinecraftForge.EVENT_BUS.post(event);
		if (event.isCanceled())
		{
			return;
		}
		var6 = event.charge;
		
		boolean var5 = player.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, itemstack) > 0;
		
		if (var5 || player.inventory.hasItem(Item.arrow.shiftedIndex))
		{
			float var7 = (float)var6 / 20.0F;
			var7 = (var7 * var7 + var7 * 2.0F) / 3.0F;
			
			if ((double)var7 < 0.1D)
			{
				return;
			}
			
			if (var7 > 1.0F)
			{
				var7 = 1.0F;
			}
			
			EntityArrow var8 = new EntityArrow(world, player, var7 * 2.0F * this.ratio);
			
			if (var7 == 1.0F)
			{
				var8.setIsCritical(true);
			}
			
			int var9 = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, itemstack);
			
			if (var9 > 0)
			{
				var8.setDamage(var8.getDamage() + (double)var9 * 0.5D + 0.5D);
			}
			
			int var10 = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, itemstack);
			
			if (var10 > 0)
			{
				var8.setKnockbackStrength(var10);
			}
			
			if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, itemstack) > 0)
			{
				var8.setFire(100);
			}
			
			itemstack.damageItem(1, player);
			world.playSoundAtEntity(player, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + var7 * 0.5F);
			
			if (var5)
			{
				var8.canBePickedUp = 2;
			}
			else
			{
				player.inventory.consumeInventoryItem(Item.arrow.shiftedIndex);
			}
			
			if (!world.isRemote)
			{
				world.spawnEntityInWorld(var8);
			}
		}
	}
	
	@Override
	public int getIconIndex(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining)
	{
		if (usingItem != null && usingItem.getItem().shiftedIndex == this.shiftedIndex)
		{
			int k = usingItem.getMaxItemUseDuration() - useRemaining;
			if (k >= 18) return animation[2];
			if (k >  13) return animation[1];
			if (k >   0) return animation[0];
		}
		
		return getIconIndex(stack);
	}
	
	@Override
	public int getItemEnchantability()
	{
		return this.enchantability;
	}
	
	public float getDamageRatio()
	{
		return ratio;
	}

}