package rgn.mods.elventools;

import net.minecraft.src.*;

public class ItemElvenParts extends Item
{
	public ItemElvenParts(int itemId)
	{
		super(itemId);
		this.setCreativeTab(Config.tabElvenTools);
	}
	
	@Override
	public String getTextureFile()
	{
		return "/rgn/sprites/elventools/items.png";
	}
}