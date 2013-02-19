package rgn.mods.elventools.item;

import net.minecraft.item.Item;

public class ItemElvenParts extends Item
{
	public ItemElvenParts(int itemId)
	{
		super(itemId);
	}

	@Override
	public String getTextureFile()
	{
		return "/rgn/sprites/elventools/items.png";
	}
}