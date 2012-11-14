package rgn.mods.elventools;

import net.minecraft.src.*;

import rgn.util.TranslationRegistry;

public class LocalizationRegistry
{
	public void addLocalization()
	{
		TranslationRegistry.addLocalization(ElvenItem.itemLeatherLongbow,   "Leather Longbow",   "レザーロングボウ");
		TranslationRegistry.addLocalization(ElvenItem.itemCompositeLongbow, "Composite Longbow", "複合ロングボウ");
		TranslationRegistry.addLocalization(ElvenItem.itemEnhancedLongbow,  "Enhanced Longbow",  "エンハンスドロングボウ");
		
		TranslationRegistry.addLocalization(ElvenBlock.blockEbonyLog,     "Ebony Log",           "黒檀の原木");
		TranslationRegistry.addLocalization(ElvenBlock.blockEbonyLeaves,  "Ebony Leaves",        "黒檀の葉");
		TranslationRegistry.addLocalization(ElvenBlock.blockEbonySapling, "Ebony Sapling",       "黒檀の苗木");
		TranslationRegistry.addLocalization(ElvenBlock.blockEbonyWood,    "Ebony Wooden Planks", "黒檀の木材");
		
		TranslationRegistry.addLocalization(ElvenBlock.blockRope,            "RopeBlock",              "ロープブロック");
		TranslationRegistry.addLocalization(ElvenBlock.blockRopeEstablisher, "Rope Establisher Block", "ロープ設置ブロック");
		
		TranslationRegistry.addLocalization(ElvenItem.itemEbonyStick, "Ebony Stick", "黒檀の棒");
		TranslationRegistry.addLocalization(ElvenItem.itemEbonyBoat,  "Ebony Boat",  "黒檀のボート");
		
		TranslationRegistry.addLocalization(ElvenItem.itemTorchArrow,      "Torch Arrow",      "灯火の矢");
		TranslationRegistry.addLocalization(ElvenItem.itemRopeArrow,       "Rope Arrow",       "ロープの矢");
		TranslationRegistry.addLocalization(ElvenItem.itemRopeEstablisher, "Rope Establisher", "ロープ");
		
		TranslationRegistry.addLocalization(ElvenItem.itemElvenShovelMithril,  "Elven Mithril Shovel",  "エルフのミスリルショベル");
		TranslationRegistry.addLocalization(ElvenItem.itemElvenPickaxeMithril, "Elven Mithril Pickaxe", "エルフのミスリルつるはし");
		TranslationRegistry.addLocalization(ElvenItem.itemElvenAxeMithril,     "Elven Mithril Axe",     "エルフのミスリル斧");
		TranslationRegistry.addLocalization(ElvenItem.itemElvenSwordMithril,   "Elven Mithril Sword",   "エルフのミスリル直剣");
		
		TranslationRegistry.addLocalization(ElvenItem.itemElvenSickle,    "Elven Sickle",     "エルフの草刈り鎌");
		TranslationRegistry.addLocalization(ElvenItem.itemElvenLumberAxe, "Elven Lumber Axe", "エルフの伐採斧");
	}
}