package rgn.mods.mabicraft.registry;

import net.minecraft.src.*;

import rgn.util.TranslationRegistry;

import rgn.mods.mabicraft.config.*;

public final class LocalizationRegistry
{
	public void addLocalization()
	{
		TranslationRegistry.addLocalization(MabiCraftBlock.blockBonfire,   "Bonfire",   "たき火");
		TranslationRegistry.addLocalization(MabiCraftBlock.blockEnchanter, "Enchanter", "付呪台");
		
		TranslationRegistry.addLocalization(MabiCraftItem.itemBaseHerb,     "Base Herb",     "ベースハーブ");
		TranslationRegistry.addLocalization(MabiCraftItem.itemManaHerb,     "Mana Herb",     "マナハーブ");
		TranslationRegistry.addLocalization(MabiCraftItem.itemBloodyHerb,   "Bloody Herb",   "ブラッディハーブ");
		TranslationRegistry.addLocalization(MabiCraftItem.itemSunlightHerb, "Sunlight Herb", "サンライトハーブ");
		TranslationRegistry.addLocalization(MabiCraftItem.itemPoisonHerb,   "Poison Herb",   "ポイズンハーブ");
		TranslationRegistry.addLocalization(MabiCraftItem.itemIvoryHerb,    "Ivory Herb",    "アイボリーハーブ");	

		TranslationRegistry.addLocalization(MabiCraftItem.itemBlessedPotion, "Blessed Potion", "祝福ポーション");
		TranslationRegistry.addLocalization(MabiCraftItem.itemEnchantScroll, "Enchant Scroll", "エンチャントスクロール");
		TranslationRegistry.addLocalization(MabiCraftItem.itemMagicPowder,   "Magic Powder",   "魔法の粉");

		TranslationRegistry.addLocalization(MabiCraftItem.itemBonfireKit, "Bonfire Kit", "たき火キット");
		TranslationRegistry.addLocalization(MabiCraftItem.itemEvilScroll, "EvilScroll",  "魔符");
	}
}