package rgn.mods.dwarventools;

import net.minecraft.src.*;

public class ItemDwarvenParts extends Item
{
	public ItemDwarvenParts(int itemId)
	{
		super(itemId);
		this.setCreativeTab(Config.tabDwarvenTools);
	}
	
	@Override
	public String getTextureFile()
	{
		return "/rgn/sprites/dwarventools/items.png";
	}
}
