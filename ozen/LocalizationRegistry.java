package rgn.mods.ozen;

import net.minecraft.item.ItemStack;
import rgn.util.TranslationRegistry;

public class LocalizationRegistry
{
	public void addLocalization()
	{
		TranslationRegistry.addLocalization(new ItemStack(Ozen.blockOzen, 1,  0), "Oak Oshiki",    "オークの折敷");
		TranslationRegistry.addLocalization(new ItemStack(Ozen.blockOzen, 1,  1), "Spruce Oshiki", "松の折敷");
		TranslationRegistry.addLocalization(new ItemStack(Ozen.blockOzen, 1,  2), "Birch Oshiki",  "白樺の折敷");
		TranslationRegistry.addLocalization(new ItemStack(Ozen.blockOzen, 1,  3), "Black Oshiki",  "黒い折敷");
		TranslationRegistry.addLocalization(new ItemStack(Ozen.blockOzen, 1,  4), "Red Oshiki",    "赤い折敷");
		TranslationRegistry.addLocalization(new ItemStack(Ozen.blockOzen, 1,  5), "White Oshiki",  "白色の折敷");
		TranslationRegistry.addLocalization(new ItemStack(Ozen.blockOzen, 1,  6), "Gold Oshiki",   "金色の折敷");

		TranslationRegistry.addLocalization(new ItemStack(Ozen.blockOzen, 1,  8), "Oak Ozen",    "オークのお膳");
		TranslationRegistry.addLocalization(new ItemStack(Ozen.blockOzen, 1, 10), "Spruce Ozen", "松のお膳");
		TranslationRegistry.addLocalization(new ItemStack(Ozen.blockOzen, 1,  9), "Birch Ozen",  "白樺のお膳");
		TranslationRegistry.addLocalization(new ItemStack(Ozen.blockOzen, 1, 11), "Black Ozen",  "黒いお膳");
		TranslationRegistry.addLocalization(new ItemStack(Ozen.blockOzen, 1, 12), "Red Ozen",    "赤いお膳");
		TranslationRegistry.addLocalization(new ItemStack(Ozen.blockOzen, 1, 13), "White Ozen",  "白色のお膳");
		TranslationRegistry.addLocalization(new ItemStack(Ozen.blockOzen, 1, 14), "Gold Ozen",   "金色のお膳");
	}
}