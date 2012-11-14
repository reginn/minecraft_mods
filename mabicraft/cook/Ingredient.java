package rgn.mods.mabicraft.cook;

public class Ingredient
{
	private String name;
	private int ratio;
	
	public Ingredient(String _name, int _ratio)
	{
		this.name  = _name;
		this.ratio = _ratio;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public int getRatio()
	{
		return this.ratio;
	}
}
	