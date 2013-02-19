package rgn.mods.elventools.config;

import net.minecraftforge.oredict.OreDictionary;

import rgn.mods.elventools.block.ElvenBlock;
import rgn.mods.elventools.item.ElvenItem;

public class OreDictRegistry
{
	public void register()
	{
		OreDictionary.registerOre("woodLogEbony",     ElvenBlock.blockEbonyLog);
		OreDictionary.registerOre("plankEbony",       ElvenBlock.blockEbonyWood);
		OreDictionary.registerOre("woodSaplingEbony", ElvenBlock.blockEbonySapling);
		OreDictionary.registerOre("woodLeavesEbony",  ElvenBlock.blockEbonyLeaves);
		OreDictionary.registerOre("stickEbony",       ElvenItem.itemEbonyStick);
	}
}