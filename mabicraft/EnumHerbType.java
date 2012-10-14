package rgn.mods.mabicraft;

import net.minecraft.src.*;

public enum EnumHerbType
{
	BASE("+4"),
	BLOODY(PotionHelper.speckledMelonEffect),
	SUNLIGHT("+4"),
	MANA("+4"),
	POISON(PotionHelper.spiderEyeEffect),
	IVORY("+4");
	
	private String potionEffect;
	
	private EnumHerbType(String _potionEffect)
	{
		this.potionEffect = _potionEffect;
	}
	
	public String getPotionEffect()
	{
		return this.potionEffect;
	}
}