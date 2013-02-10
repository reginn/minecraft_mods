package rgn.mods.dwarventools.config;

import rgn.mods.dwarventools.enchantment.DwarvenEnchantment;
import rgn.mods.dwarventools.enchantment.EnchantmentVitalize;
import rgn.mods.dwarventools.enchantment.EnchantmentExecutioner;
import rgn.mods.dwarventools.enchantment.EnchantmentCriticalStrike;

public class ConfigureEnchantment
{
	public static void init()
	{
		DwarvenEnchantment.enchantmentVitalize       = (new EnchantmentVitalize(Config.enchantmentIdVitalize));
		DwarvenEnchantment.enchantmentExecutioner    = (new EnchantmentExecutioner(Config.enchantmentIdExecutioner));
		DwarvenEnchantment.enchantmentCriticalStrike = (new EnchantmentCriticalStrike(Config.enchantmentIdCriticalStrike));
	}
}