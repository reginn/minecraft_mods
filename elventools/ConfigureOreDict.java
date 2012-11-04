package rgn.mods.elventools;

import net.minecraft.src.*;

import net.minecraftforge.oredict.OreDictionary;

public class ConfigureOreDict
{
	public static void init()
	{
		OreDictionary.registerOre("woodLogEbony",     ElvenBlock.blockEbonyLog);
		OreDictionary.registerOre("plankEbony",       ElvenBlock.blockEbonyWood);
		OreDictionary.registerOre("woodSaplingEbony", ElvenBlock.blockEbonySapling);
		OreDictionary.registerOre("woodLeavesEbony",  ElvenBlock.blockEbonyLeaves);
		OreDictionary.registerOre("stickEbony",       ElvenItem.itemEbonyStick);
	}
}