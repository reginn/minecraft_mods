package rgn.mods.dwarventools.enchantment;

import java.util.Map;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;

public class UniqueEnchantmentHelper
{
	public static boolean isItemUniqueEnchanted(ItemStack itemstack, Enchantment uniqueEnchantment)
	{
		return EnchantmentHelper.getEnchantments(itemstack).keySet().contains(uniqueEnchantment.effectId);
	}

	public static int getUniqueEnchantmentLv(ItemStack itemstack, Enchantment uniqueEnchantment)
	{
		Map<Integer, Integer> enchantments = EnchantmentHelper.getEnchantments(itemstack);
		return enchantments.get(uniqueEnchantment.effectId).intValue();
	}
}