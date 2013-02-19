package rgn.mods.dwarventools.enchantment;

import net.minecraft.enchantment.Enchantment;

import rgn.mods.dwarventools.config.Config;

public class DwarvenEnchantment
{
	public static Enchantment enchantmentVitalize;
	public static Enchantment enchantmentExecutioner;
	public static Enchantment enchantmentCriticalStrike;

	public static void configure(Config config)
	{
		enchantmentVitalize       = (new EnchantmentVitalize(config.enchantmentIdVitalize));
		enchantmentExecutioner    = (new EnchantmentExecutioner(config.enchantmentIdExecutioner));
		enchantmentCriticalStrike = (new EnchantmentCriticalStrike(config.enchantmentIdCriticalStrike));

	}
}