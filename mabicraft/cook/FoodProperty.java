package rgn.mods.mabicraft.cook;

public class FoodProperty
{
	private final int   healAmount;
	private final float saturationModifier;

	public FoodProperty(int _healAmount, float _saturationModifier)
	{
		this.healAmount = _healAmount;
		this.saturationModifier = _saturationModifier;
	}

	public int getHealAmount()
	{
		return this.healAmount;
	}

	public float getSaturationModifier()
	{
		return this.saturationModifier;
	}
}