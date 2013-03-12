package rgn.mods.dwarventools.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;

public class EnchantmentLifeSteal extends Enchantment
{
	public EnchantmentLifeSteal(int effectId)
	{
		super(effectId, 3, EnumEnchantmentType.weapon);
		this.setName("lifeSteal");
	}

	@Override
	public int getMinEnchantability(int par1)
	{
		return 10 + 20 * (par1 - 1);
	}

	@Override
	public int getMaxEnchantability(int par1)
	{
		return this.getMinEnchantability(par1) + 50;
	}

	@Override
	public int getMaxLevel()
	{
		return 3;
	}

	@Override
	public boolean canApplyTogether(Enchantment par1Enchantment)
	{
		return !(par1Enchantment instanceof EnchantmentLifeSteal);
	}
}
