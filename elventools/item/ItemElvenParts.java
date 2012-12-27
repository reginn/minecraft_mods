package rgn.mods.elventools.item;

import net.minecraft.item.Item;
import rgn.mods.elventools.config.Config;

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