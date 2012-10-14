package rgn.mods.lamp;

public enum EnumLampType
{
	CLASSIC("Classic", "ITITxTITI");
	
	private String lampTypeName;
	private String recipe;
	
	private EnumLampType(String _lampTypeName, String _recipe)
	{
		this.lampTypeName = _lampTypeName;
		this.recipe = _recipe;
	}
	
	public String getLampType()
	{
		return this.lampTypeName;
	}
	
	public String getRecipe()
	{
		return this.recipe;
	}
	
}