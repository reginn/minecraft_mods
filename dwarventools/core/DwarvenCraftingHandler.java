package rgn.mods.dwarventools.core;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

import cpw.mods.fml.common.ICraftingHandler;

import rgn.mods.dwarventools.enchantment.DwarvenEnchantment;
import rgn.mods.dwarventools.item.DwarvenItem;
import rgn.mods.dwarventools.item.ItemDwarvenArmor;
import rgn.mods.dwarventools.item.ItemDwarvenSword;

public class DwarvenCraftingHandler implements ICraftingHandler
{
	@Override
	public void onCrafting(EntityPlayer player, ItemStack item, IInventory craftMatrix)
	{
		if (item.getItem() instanceof ItemDwarvenSword)
		{
			this.onSwordCreated(item);
		}
		if (item.getItem() instanceof ItemDwarvenArmor)
		{
			this.onArmorCreated(item);
		}
	}

	@Override
	public void onSmelting(EntityPlayer player, ItemStack item)
	{
		// noop
	}

	private void onSwordCreated(ItemStack item)
	{
		if (this.isSwordMaterialMatch(item, DwarvenItem.enumToolMaterialMithril))
		{
			item.addEnchantment(DwarvenEnchantment.enchantmentCriticalStrike, 1);
			item.addEnchantment(Enchantment.smite,   3);
			item.addEnchantment(Enchantment.looting, 1);
		}
		if (this.isSwordMaterialMatch(item, DwarvenItem.enumToolMaterialEbony))
		{
			item.addEnchantment(DwarvenEnchantment.enchantmentLifeSteal, 1);
			item.addEnchantment(Enchantment.sharpness, 3);
			item.addEnchantment(Enchantment.looting,   2);
		}
	}

	private boolean isSwordMaterialMatch(ItemStack item, EnumToolMaterial material)
	{
		if (item.getItem() instanceof ItemDwarvenSword)
		{
			return ((ItemDwarvenSword)item.getItem()).getToolMaterial().equals(material);
		}
		return false;
	}

	private boolean isArmorMaterialMatch(ItemStack item, EnumArmorMaterial material)
	{
		if (item.getItem() instanceof ItemDwarvenArmor)
		{
			return ((ItemDwarvenArmor)item.getItem()).getArmorMaterial().equals(material);
		}
		return false;
	}

	private void onArmorCreated(ItemStack item)
	{
		if (this.isArmorMaterialMatch(item, DwarvenItem.enumArmorMaterialMithril))
		{
			switch (((ItemArmor)item.getItem()).armorType)
			{
				case 0 :
					item.addEnchantment(Enchantment.respiration,  1);
					item.addEnchantment(Enchantment.aquaAffinity, 1);
					break;

				case 1 :
					item.addEnchantment(DwarvenEnchantment.enchantmentVitalize, 1);
					break;

				case 2 :
					item.addEnchantment(Enchantment.protection,           1);
					item.addEnchantment(Enchantment.fireProtection,       1);
					item.addEnchantment(Enchantment.blastProtection,      1);
					item.addEnchantment(Enchantment.projectileProtection, 1);
					break;

				case 3 :
					item.addEnchantment(Enchantment.featherFalling, 1);
					break;

				default :
					break;
			}
		}

		if (this.isArmorMaterialMatch(item, DwarvenItem.enumArmorMaterialEbony))
		{
			switch (((ItemArmor)item.getItem()).armorType)
			{
				case 0 :
					item.addEnchantment(Enchantment.respiration,  3);
					item.addEnchantment(Enchantment.aquaAffinity, 1);
					break;

				case 1 :
					item.addEnchantment(DwarvenEnchantment.enchantmentVitalize, 2);
					break;

				case 2 :
					item.addEnchantment(Enchantment.protection,           3);
					item.addEnchantment(Enchantment.fireProtection,       3);
					item.addEnchantment(Enchantment.blastProtection,      3);
					item.addEnchantment(Enchantment.projectileProtection, 3);
					break;

				case 3 :
					item.addEnchantment(Enchantment.featherFalling, 3);
					break;

				default :
					break;
			}
		}
	}
}
