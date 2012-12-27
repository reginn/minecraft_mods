package rgn.mods.dwarventools.item;

import net.minecraft.item.Item;
import rgn.mods.dwarventools.config.Config;

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
