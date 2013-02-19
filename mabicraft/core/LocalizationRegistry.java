package rgn.mods.mabicraft.core;

import net.minecraft.item.ItemStack;

import rgn.mods.mabicraft.block.MabiCraftBlock;
import rgn.mods.mabicraft.item.MabiCraftItem;

import rgn.util.TranslationRegistry;

public final class LocalizationRegistry
{
	public void addLocalization()
	{
		TranslationRegistry.addLocalization(MabiCraftBlock.blockBonfire,   "Bonfire",   "たき火");
		TranslationRegistry.addLocalization(MabiCraftBlock.blockEnchanter, "Enchanter", "付呪台");
		TranslationRegistry.addLocalization(MabiCraftBlock.blockCookware, "Cooking Table", "調理用テーブル");

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
		TranslationRegistry.addLocalization(MabiCraftItem.itemCookingPot, "Cooking Pot", "万能鍋");

		TranslationRegistry.addLocalization(MabiCraftItem.itemMabiFishRod, "Fishing Rod (MabiCraft)", "釣り竿 (MabiCraft)");

		TranslationRegistry.addLocalization(new ItemStack(MabiCraftItem.itemCookingFood, 1,  0), "Chocolate Milk",  "チョコレートミルク");
		TranslationRegistry.addLocalization(new ItemStack(MabiCraftItem.itemCookingFood, 1,  1), "Strawberry Milk", "イチゴ牛乳");
		TranslationRegistry.addLocalization(new ItemStack(MabiCraftItem.itemCookingFood, 1,  2), "Orange Juice",    "オレンジジュース");
		TranslationRegistry.addLocalization(new ItemStack(MabiCraftItem.itemCookingFood, 1,  3), "Apple Juice",     "アップルジュース");
		TranslationRegistry.addLocalization(new ItemStack(MabiCraftItem.itemCookingFood, 1,  4), "Lemon Juice",     "レモンジュース");
		TranslationRegistry.addLocalization(new ItemStack(MabiCraftItem.itemCookingFood, 1,  5), "Cappuccino",      "カプチーノ");
		TranslationRegistry.addLocalization(new ItemStack(MabiCraftItem.itemCookingFood, 1,  6), "CafeMocha",       "カフェモカ");
		TranslationRegistry.addLocalization(new ItemStack(MabiCraftItem.itemCookingFood, 1,  7), "Macchiato",       "マキアート");

		TranslationRegistry.addLocalization(new ItemStack(MabiCraftItem.itemCookingFood, 1,  8), "Ice CafeMocha",    "アイスカフェモカ");
		TranslationRegistry.addLocalization(new ItemStack(MabiCraftItem.itemCookingFood, 1,  9), "Ice Macchiato",    "アイスマキアート");
		TranslationRegistry.addLocalization(new ItemStack(MabiCraftItem.itemCookingFood, 1, 10), "BnR",              "BnR");
		TranslationRegistry.addLocalization(new ItemStack(MabiCraftItem.itemCookingFood, 1, 11), "Kiss on the Lips", "キスオンザリップ");
		TranslationRegistry.addLocalization(new ItemStack(MabiCraftItem.itemCookingFood, 1, 12), "ScrewDriver",      "スクリュードライバー");
		TranslationRegistry.addLocalization(new ItemStack(MabiCraftItem.itemCookingFood, 1, 13), "Brifne Rocks",     "ブリフネロックス");
		TranslationRegistry.addLocalization(new ItemStack(MabiCraftItem.itemCookingFood, 1, 14), "Leighean Sling",   "ラインスリング");
		TranslationRegistry.addLocalization(new ItemStack(MabiCraftItem.itemCookingFood, 1, 15), "Red Sunrise",      "レッドサンライズ");
		TranslationRegistry.addLocalization(new ItemStack(MabiCraftItem.itemCookingFood, 1, 16), "Vales Fire",       "バレスファイア");

		TranslationRegistry.addLocalization(new ItemStack(MabiCraftItem.itemCookingFood, 1, 128), "Brifne Carp",         "ブリフネ鯉");
		TranslationRegistry.addLocalization(new ItemStack(MabiCraftItem.itemCookingFood, 1, 129), "Taitinn Carp",        "銀鮒");
		TranslationRegistry.addLocalization(new ItemStack(MabiCraftItem.itemCookingFood, 1, 130), "Rainbow Trout",       "虹色のマス");
		TranslationRegistry.addLocalization(new ItemStack(MabiCraftItem.itemCookingFood, 1, 131), "Golden Scale Fish",   "金輪魚");
		TranslationRegistry.addLocalization(new ItemStack(MabiCraftItem.itemCookingFood, 1, 132), "Striped Marlin",      "マカジキ");
		TranslationRegistry.addLocalization(new ItemStack(MabiCraftItem.itemCookingFood, 1, 133), "Silk Striped Marlin", "カジキマグロ");
		TranslationRegistry.addLocalization(new ItemStack(MabiCraftItem.itemCookingFood, 1, 134), "Ocean Sunfish",       "マンボウ");
		TranslationRegistry.addLocalization(new ItemStack(MabiCraftItem.itemCookingFood, 1, 135), "Flying Fish",         "トビウオ");
		TranslationRegistry.addLocalization(new ItemStack(MabiCraftItem.itemCookingFood, 1, 136), "Lamprey",             "カワヤツメ");
		TranslationRegistry.addLocalization(new ItemStack(MabiCraftItem.itemCookingFood, 1, 137), "Sweet Fish",          "アユ");
		TranslationRegistry.addLocalization(new ItemStack(MabiCraftItem.itemCookingFood, 1, 138), "Catfish",             "ナマズ");

		TranslationRegistry.addLocalization(new ItemStack(MabiCraftItem.itemCookingFood, 1, 144), "Strawverry",    "イチゴ");
		TranslationRegistry.addLocalization(new ItemStack(MabiCraftItem.itemCookingFood, 1, 145), "Nuts",          "木の実");
		TranslationRegistry.addLocalization(new ItemStack(MabiCraftItem.itemCookingFood, 1, 146), "Lemon",         "レモン");
		TranslationRegistry.addLocalization(new ItemStack(MabiCraftItem.itemCookingFood, 1, 147), "Orange",        "オレンジ");
		TranslationRegistry.addLocalization(new ItemStack(MabiCraftItem.itemCookingFood, 1, 148), "Brifne Whisky", "ブリフネウィスキー");
		TranslationRegistry.addLocalization(new ItemStack(MabiCraftItem.itemCookingFood, 1, 149), "Vales Whisky",  "バレスウィスキー");

	}
}