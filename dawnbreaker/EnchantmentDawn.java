package rgn.mods.dawnbreaker;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.util.MathHelper;

public class EnchantmentDawn extends Enchantment
{
	public EnchantmentDawn(int effectId, int weight, EnumEnchantmentType enumEnchantmentType)
	{
		super(effectId, weight, enumEnchantmentType);
	}
	
	@Override
	public int getMinEnchantability(int par1)
	{
		return 50;
	}
	
	@Override
	public int getMaxEnchantability(int par1)
	{
		return this.getMinEnchantability(par1);
	}
	
	@Override
	public int getMaxLevel()
	{
		return 1;
	}
	
	@Override
	public int calcModifierLiving(int par1, EntityLiving par2EntityLiving)
	{
		return par2EntityLiving.getCreatureAttribute() == EnumCreatureAttribute.UNDEAD ? MathHelper.floor_float((float)par1 * 4.5F) : 1;
	}
	
	@Override
	public String getName()
	{
		return "enchantment.dawn";
	}
	
	@Override
	public boolean canApplyTogether(Enchantment par1Enchantment)
	{
		return !(par1Enchantment instanceof EnchantmentDawn);
	}
}