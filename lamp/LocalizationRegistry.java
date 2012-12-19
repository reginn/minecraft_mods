package rgn.mods.lamp;

import net.minecraft.item.ItemStack;
import rgn.util.TranslationRegistry;

public class LocalizationRegistry
{
	public void addLocalization()
	{
		TranslationRegistry.addLocalization(new ItemStack(Lamp.blockLamp, 1, 0), "Lantern",         "ランタン");
		TranslationRegistry.addLocalization(new ItemStack(Lamp.blockLamp, 1, 1), "Glow Lantern",    "グロウランタン");
		TranslationRegistry.addLocalization(new ItemStack(Lamp.blockLamp, 1, 2), "Gold Lantern",    "ゴールドランタン");
		TranslationRegistry.addLocalization(new ItemStack(Lamp.blockLamp, 1, 3), "Diamond Lantern", "ダイアランタン");
	}
}