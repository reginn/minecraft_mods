package rgn.mods.rum.core;

import net.minecraft.item.ItemStack;

import rgn.mods.rum.block.RumBlock;
import rgn.mods.rum.item.RumItem;

import rgn.util.TranslationRegistry;

public class LocalizationRegistry
{
	public void addLocalization()
	{
		TranslationRegistry.addLocalization("itemGroup.rum", "Reginn's Unclassified Mod", "Reginn's Unclassified Mod");
		TranslationRegistry.addLocalization(RumBlock.blockLockedChest, "LockedChest", "宝箱");
		TranslationRegistry.addLocalization(new ItemStack(RumItem.itemKey, 1, 0), "Old Key",  "古い鍵");
		TranslationRegistry.addLocalization(new ItemStack(RumItem.itemKey, 1, 1), "Skeleton Key", "不壊の鍵");
	}
}
