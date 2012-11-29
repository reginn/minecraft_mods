package rgn.mods.mabicraft.registry;

import net.minecraft.src.*;
import net.minecraftforge.oredict.OreDictionary;

import rgn.util.TranslationRegistry;

import rgn.mods.mabicraft.config.*;
import rgn.mods.mabicraft.core.*;

public final class LocalizationRegistry
{
	public void addLocalization()
	{
		TranslationRegistry.addLocalization(MabiCraftBlock.blockBonfire,   "Bonfire",   "たき火");
		TranslationRegistry.addLocalization(MabiCraftBlock.blockEnchanter, "Enchanter", "付呪台");

		TranslationRegistry.addLocalization(new ItemStack(MabiCraftBlock.blockCookware, 1, 0), "Cooking Table", "調理用テーブル");
		TranslationRegistry.addLocalization(new ItemStack(MabiCraftBlock.blockCookware, 1, 1), "Cooking Oven",  "調理用かまど");
		TranslationRegistry.addLocalization(new ItemStack(MabiCraftBlock.blockCookware, 1, 2), "Cooking Pot",   "万能鍋");
		
		
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
		
		
		TranslationRegistry.addLocalization(new ItemStack(MabiCraftItem.itemCookingFood, 1,  0), "Chocolate Milk", "チョコレートミルク");
		TranslationRegistry.addLocalization(new ItemStack(MabiCraftItem.itemCookingFood, 1,  1), "Strawberry Milk", "イチゴ牛乳");
		TranslationRegistry.addLocalization(new ItemStack(MabiCraftItem.itemCookingFood, 1,  2), "Orange Juice", "オレンジジュース");
		TranslationRegistry.addLocalization(new ItemStack(MabiCraftItem.itemCookingFood, 1,  3), "Apple Juice", "アップルジュース");
		TranslationRegistry.addLocalization(new ItemStack(MabiCraftItem.itemCookingFood, 1,  4), "Lemon Juice", "レモンジュース");
		TranslationRegistry.addLocalization(new ItemStack(MabiCraftItem.itemCookingFood, 1,  5), "Cappuccino", "カプチーノ");
		TranslationRegistry.addLocalization(new ItemStack(MabiCraftItem.itemCookingFood, 1,  6), "CafeMocha", "カフェモカ");
		TranslationRegistry.addLocalization(new ItemStack(MabiCraftItem.itemCookingFood, 1,  7), "Macchiato", "マキアート");
		
		TranslationRegistry.addLocalization(new ItemStack(MabiCraftItem.itemCookingFood, 1,  8), "Ice CafeMocha", "アイスカフェモカ");
		TranslationRegistry.addLocalization(new ItemStack(MabiCraftItem.itemCookingFood, 1,  9), "Ice Macchiato", "アイスマキアート");
		TranslationRegistry.addLocalization(new ItemStack(MabiCraftItem.itemCookingFood, 1, 10), "BnR", "BnR");
		TranslationRegistry.addLocalization(new ItemStack(MabiCraftItem.itemCookingFood, 1, 11), "Kiss on the Lips", "キスオンザリップ");
		TranslationRegistry.addLocalization(new ItemStack(MabiCraftItem.itemCookingFood, 1, 12), "ScrewDriver", "スクリュードライバー");
		TranslationRegistry.addLocalization(new ItemStack(MabiCraftItem.itemCookingFood, 1, 13), "Brifne Rocks", "ブリフネロックス");
		TranslationRegistry.addLocalization(new ItemStack(MabiCraftItem.itemCookingFood, 1, 14), "Leighean Sling", "ラインスリング");
		TranslationRegistry.addLocalization(new ItemStack(MabiCraftItem.itemCookingFood, 1, 15), "Red Sunrise", "レッドサンライズ");
		TranslationRegistry.addLocalization(new ItemStack(MabiCraftItem.itemCookingFood, 1, 16), "Vales Fire", "バレスファイア");

		TranslationRegistry.addLocalization(new ItemStack(MabiCraftItem.itemCookingFood, 1, 144), "Strawverry", "イチゴ");
		TranslationRegistry.addLocalization(new ItemStack(MabiCraftItem.itemCookingFood, 1, 145), "Nuts", "木の実");
		TranslationRegistry.addLocalization(new ItemStack(MabiCraftItem.itemCookingFood, 1, 146), "Lemon", "レモン");
		TranslationRegistry.addLocalization(new ItemStack(MabiCraftItem.itemCookingFood, 1, 147), "Orange", "オレンジ");
		TranslationRegistry.addLocalization(new ItemStack(MabiCraftItem.itemCookingFood, 1, 148), "Brifne Whisky", "ブリフネウィスキー");
		TranslationRegistry.addLocalization(new ItemStack(MabiCraftItem.itemCookingFood, 1, 149), "Vales Whisky", "バレスウィスキー");

	}
}