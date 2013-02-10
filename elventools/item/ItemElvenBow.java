package rgn.mods.elventools.item;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowNockEvent;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;

import rgn.mods.elventools.config.Config;

public class ItemElvenBow extends Item
{
	protected int[] animation = new int[3];
	protected float baseDamage;
	protected float velocityRatio;
	protected int   enchantability;
	protected boolean isCallEvent = true;
	protected float chargeSpeedRatio = 1.0F;
	
	protected EnumElvenBowType bowType;
	
	public ItemElvenBow(int itemId, EnumElvenBowType type)
	{
		super(itemId);
		this.maxStackSize   = 1;
		this.iconIndex      = type.getSpriteId();
		this.baseDamage     = type.getBaseDamage();
		this.velocityRatio  = type.getVelocityRatio();
		this.enchantability = type.getEnchantability();

		this.setMaxDamage(type.getDurability());
		this.setFull3D();
		this.setCreativeTab(Config.tabElvenTools);

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
	public int getMaxItemUseDuration(ItemStack bow)
	{
		return (int)((float)72000 / this.getChargeSpeedRatio());
	}
	
	public ItemStack onFoodEaten(ItemStack itemstack, World world, EntityPlayer player)
	{
		return itemstack;
	}

	public EnumAction getItemUseAction(ItemStack itemstack)
	{
		return EnumAction.bow;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack bow, World world, EntityPlayer player)
	{
		if (this.isCallEvent)
		{
			ArrowNockEvent event = new ArrowNockEvent(player, bow);
			MinecraftForge.EVENT_BUS.post(event);
			if (event.isCanceled())
			{
				return event.result;
			}
		}

		if (this.isCreativeMode(player) || player.inventory.hasItem(Item.arrow.itemID))
		{
			player.setItemInUse(bow, this.getMaxItemUseDuration(bow));
		}

		return bow;
	}
	
	@Override
	public void onPlayerStoppedUsing(ItemStack itemstack, World world, EntityPlayer player, int par4)
	{
		int var6 = this.getMaxItemUseDuration(itemstack) - par4;
		var6 *= this.getChargeSpeedRatio();

		if (this.isCallEvent)
		{
			ArrowLooseEvent event = new ArrowLooseEvent(player, itemstack, var6);
			MinecraftForge.EVENT_BUS.post(event);
			if (event.isCanceled())
			{
				return;
			}
			var6 = event.charge;
		}
		
		if (this.isCanShot(player, itemstack))
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

			EntityArrow entityArrow = new EntityArrow(world, player, var7 * 2.0F * this.velocityRatio);
			
			entityArrow.setDamage(this.baseDamage);

			if (var7 == 1.0F)
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
			world.playSoundAtEntity(player, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + var7 * 0.5F);

			if (this.isCreativeMode(player) || this.isInfinity(itemstack))
			{
				entityArrow.canBePickedUp = 2;
			}
			else
			{
				player.inventory.consumeInventoryItem(Item.arrow.itemID);
			}

			if (!world.isRemote)
			{
				world.spawnEntityInWorld(entityArrow);
			}
		}
	}
	
	@Override
	public int getIconIndex(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining)
	{
		if (usingItem != null && usingItem.getItem().itemID == this.itemID)
		{
			int k = usingItem.getMaxItemUseDuration() - useRemaining;
			if (k >= this.getChargeValue(18)) return animation[2];
			if (k >  this.getChargeValue(13)) return animation[1];
			if (k >  this.getChargeValue(0)) return animation[0];
		}

		return getIconIndex(stack);
	}

	@Override
	public int getItemEnchantability()
	{
		return this.enchantability;
	}
	
	public int getChargeValue(int base)
	{
		return (int)((float)base/this.getChargeSpeedRatio());
	}
	
	public float getChargeSpeedRatio()
	{
		return this.chargeSpeedRatio;
	}
	
	public float getVelocityRatio()
	{
		return this.velocityRatio;
	}
	
	public float getBaseDamage()
	{
		return this.baseDamage;
	}
	
	public boolean isCanShot(EntityPlayer player, ItemStack itemstack)
	{
		return this.isCreativeMode(player)
			|| this.isInfinity(itemstack)
			|| player.inventory.hasItem(Item.arrow.itemID);
	}
	
	public boolean isCreativeMode(EntityPlayer player)
	{
		return player.capabilities.isCreativeMode;
	}
	
	public boolean isInfinity(ItemStack itemstack)
	{
		return EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, itemstack) > 0;
	}
	
	public ItemElvenBow disableCallEvent()
	{
		this.isCallEvent = false;
		return this;
	}
	
	public ItemElvenBow setChargeSpeedRatio(float chargeSpeedRatio)
	{
		this.chargeSpeedRatio = chargeSpeedRatio;
		return this;
	}
}