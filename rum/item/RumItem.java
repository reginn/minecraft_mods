package rgn.mods.rum.item;

import net.minecraft.item.Item;

import rgn.mods.rum.config.Config;

public class RumItem
{
	public static Item itemKey;

	public static void configure(Config config)
	{
		itemKey = (new ItemKey(config.itemIdKey)).setUnlocalizedName("itemKey").setCreativeTab(Config.tabRum);
	}
}
