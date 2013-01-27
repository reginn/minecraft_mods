package rgn.mods.dwarventools.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;

public class EnchantmentExecutioner extends Enchantment
{
	public EnchantmentExecutioner(int effectId)
	{
		super(effectId, 2, EnumEnchantmentType.weapon);
		this.setName("executioner");
	}
	
	@Override
	public int getMinEnchantability(int par1)
	{
		return 15 + 10 * (par1 - 1);
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
		return !(par1Enchantment instanceof EnchantmentExecutioner);
	}
}