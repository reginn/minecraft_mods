package rgn.mods.elventools.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemElvenBow extends Item
{
	@SideOnly(Side.CLIENT)
	protected Icon[] animation;

	protected float   baseDamage;
	protected float   velocityRatio;
	protected int     enchantability;
	protected boolean isCallEvent = true;
	protected float   chargeSpeedRatio = 1.0F;
	protected String  information;
	protected boolean rarity;

	protected EnumElvenBowType bowType;

	public ItemElvenBow(int itemId, EnumElvenBowType type)
	{
		super(itemId);
		this.maxStackSize   = 1;
		this.baseDamage     = type.getBaseDamage();
		this.velocityRatio  = type.getVelocityRatio();
		this.enchantability = type.getEnchantability();
		this.bowType        = type;

		this.setMaxDamage(type.getDurability());
		this.setFull3D();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateIcons(IconRegister par1IconRegister)
	{
		this.bowType.createIcon(par1IconRegister);
		this.animation = new Icon[3];
		this.iconIndex = this.bowType.getBaseIcon();
		this.animation = this.bowType.getAnimation();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining)
	{
		if (usingItem != null && usingItem.getItem().itemID == this.itemID)
		{
			int k = usingItem.getMaxItemUseDuration() - useRemaining;
			if (k >= this.getChargeValue(18)) return this.animation[2];
			if (k >  this.getChargeValue(13)) return this.animation[1];
			if (k >  this.getChargeValue(0))  return this.animation[0];
		}

		return super.getIcon(stack, renderPass, player, usingItem, useRemaining);
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
	public int getItemEnchantability()
	{
		return this.enchantability;
	}

	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean par4)
	{
		if (this.information != null)
		{
			list.add(this.information);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack itemtack)
	{
		return this.rarity ? EnumRarity.rare : EnumRarity.common;
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

	public ItemElvenBow setInfo(String info)
	{
		this.information = info;
		return this;
	}

	public ItemElvenBow setRare()
	{
		this.rarity = true;
		return this;
	}
}