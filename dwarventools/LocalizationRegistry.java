package rgn.mods.dwarventools;

import net.minecraft.src.*;

import rgn.util.TranslationRegistry;

public class LocalizationRegistry
{
	public void addLocalization()
	{
		// TranslationRegistry.addLocalization(DwarvenBlock.blockRedstone, "Block of Redstone", "レッドストーンブロック");
		TranslationRegistry.addLocalization(DwarvenBlock.blockMithril,  "old Block of Mithril",  "旧ミスリルブロック");
		TranslationRegistry.addLocalization(DwarvenBlock.blockEbony,    "old Block of Ebony",    "旧エボニーブロック");
		
		TranslationRegistry.addLocalization(DwarvenBlock.blockInfernalFurnace, "Infernal Furnace", "地獄かまど");
		
		TranslationRegistry.addLocalization(new ItemStack(DwarvenBlock.blockDwarvenOreStorage, 1, 0), "Block of Redstone", "レッドストーンブロック");
		TranslationRegistry.addLocalization(new ItemStack(DwarvenBlock.blockDwarvenOreStorage, 1, 1), "Block of Mithril",  "ミスリルブロック");
		TranslationRegistry.addLocalization(new ItemStack(DwarvenBlock.blockDwarvenOreStorage, 1, 2), "Block of Ebony",    "エボニーブロック");
		
		
		TranslationRegistry.addLocalization(new ItemStack(DwarvenBlock.blockDwarvenOre, 1, 0),  "Mithril Ore",   "ミスリル鉱石");
		TranslationRegistry.addLocalization(new ItemStack(DwarvenBlock.blockDwarvenOre, 1, 1),  "Ebony Ore",     "エボニー鉱石");
		
		TranslationRegistry.addLocalization(DwarvenItem.itemMithrilIngot, "Mithril Ingot", "ミスリルインゴット");
		TranslationRegistry.addLocalization(DwarvenItem.itemEbonyIngot,   "Ebony Ingot",   "エボニーインゴット");
		TranslationRegistry.addLocalization(DwarvenItem.itemIronBar,      "Iron Bar",      "鉄の棒");
		
		TranslationRegistry.addLocalization(DwarvenItem.itemDwarvenBrokenSwordMithril, "Broken Mithril Sword", "折れたミスリル直剣");
		TranslationRegistry.addLocalization(DwarvenItem.itemDwarvenBrokenSwordEbony,   "Broken Ebony Sword", "折れたエボニー直剣");
		
		TranslationRegistry.addLocalization(DwarvenItem.itemDwarvenShovelMithril,    "Dwarven Mithril Shovel",    "ドワーフのミスリルショベル");
		TranslationRegistry.addLocalization(DwarvenItem.itemDwarvenPickaxeMithril,   "Dwarven Mithril Pickaxe",   "ドワーフのミスリルつるはし");
		TranslationRegistry.addLocalization(DwarvenItem.itemDwarvenBattleaxeMithril, "Dwarven Mithril Battleaxe", "ドワーフのミスリルバトルアックス");
		TranslationRegistry.addLocalization(DwarvenItem.itemDwarvenSwordMithril,     "Dwarven Mithril Sword",     "ドワーフのミスリルソード");
		
		TranslationRegistry.addLocalization(DwarvenItem.itemDwarvenShovelEbony,    "Dwarven Ebony Shovel",    "ドワーフのエボニーショベル");
		TranslationRegistry.addLocalization(DwarvenItem.itemDwarvenPickaxeEbony,   "Dwarven Ebony Pickaxe",   "ドワーフのエボニーつるはし");
		TranslationRegistry.addLocalization(DwarvenItem.itemDwarvenBattleaxeEbony, "Dwarven Ebony Battleaxe", "ドワーフのエボニーバトルアックス");
		TranslationRegistry.addLocalization(DwarvenItem.itemDwarvenSwordEbony,     "Dwarven Ebony Sword",     "ドワーフのエボニーソード");
		
		TranslationRegistry.addLocalization(DwarvenItem.itemDwarvenHelmetMithril, "Mithril Helmet",  "ミスリルヘルメット");
		TranslationRegistry.addLocalization(DwarvenItem.itemDwarvenPlateMithril,  "Mithril Plate",   "ミスリルチェストプレート");
		TranslationRegistry.addLocalization(DwarvenItem.itemDwarvenLegsMithril,   "Mithril Leggins", "ミスリルレギンス");
		TranslationRegistry.addLocalization(DwarvenItem.itemDwarvenBootMithril,   "Mithril Boots",   "ミスリルブーツ");
		
		TranslationRegistry.addLocalization(DwarvenItem.itemDwarvenHelmetEbony, "Ebony Helmet",  "エボニーヘルメット");
		TranslationRegistry.addLocalization(DwarvenItem.itemDwarvenPlateEbony,  "Ebony Plate",   "エボニーチェストプレート");
		TranslationRegistry.addLocalization(DwarvenItem.itemDwarvenLegsEbony,   "Ebony Leggins", "エボニーレギンス");
		TranslationRegistry.addLocalization(DwarvenItem.itemDwarvenBootEbony,   "Ebony Boots",   "エボニーブーツ");
		
		TranslationRegistry.addLocalization(DwarvenItem.itemDwarvenShovelObsidian,  "Obsidian Shovel",  "黒曜石のショベル");
		TranslationRegistry.addLocalization(DwarvenItem.itemDwarvenPickaxeObsidian, "Obsidian Pickaxe", "黒曜石のつるはし");
		TranslationRegistry.addLocalization(DwarvenItem.itemDwarvenAxeObsidian,     "Obsidian Axe",     "黒曜石の斧");
		TranslationRegistry.addLocalization(DwarvenItem.itemDwarvenHoeObsidian,     "Obsidian Hoe",     "黒曜石のクワ");
		TranslationRegistry.addLocalization(DwarvenItem.itemDwarvenSwordObsidian,   "Obsidian Sword",   "黒曜石の剣");
		
		TranslationRegistry.addLocalization(DwarvenItem.itemDwarvenShovelLapislazuli,  "Lapislazuli Shovel",  "ラピスラズリのショベル");
		TranslationRegistry.addLocalization(DwarvenItem.itemDwarvenPickaxeLapislazuli, "Lapislazuli Pickaxe", "ラピスラズリのつるはし");
		TranslationRegistry.addLocalization(DwarvenItem.itemDwarvenAxeLapislazuli,     "Lapislazuli Axe",     "ラピスラズリの斧");
		TranslationRegistry.addLocalization(DwarvenItem.itemDwarvenHoeLapislazuli,     "Lapislazuli Hoe",     "ラピスラズリのクワ");
		TranslationRegistry.addLocalization(DwarvenItem.itemDwarvenSwordLapislazuli,   "Lapislazuli Sword",   "ラピスラズリの剣");
		
		TranslationRegistry.addLocalization(DwarvenItem.itemDwarvenShovelRedstone,  "Redstone Shovel",  "レッドストーンのショベル");
		TranslationRegistry.addLocalization(DwarvenItem.itemDwarvenPickaxeRedstone, "Redstone Pickaxe", "レッドストーンのつるはし");
		TranslationRegistry.addLocalization(DwarvenItem.itemDwarvenAxeRedstone,     "Redstone Axe",     "レッドストーンの斧");
		TranslationRegistry.addLocalization(DwarvenItem.itemDwarvenHoeRedstone,     "Redstone Hoe",     "レッドストーンのクワ");
		TranslationRegistry.addLocalization(DwarvenItem.itemDwarvenSwordRedstone,   "Redstone Sword",   "レッドストーンの剣");
	}
}