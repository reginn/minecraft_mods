package rgn.mods.mabicraft.config;

import rgn.mods.mabicraft.cook.BlockCookware;
import rgn.mods.mabicraft.cook.ItemCookware;
import rgn.mods.mabicraft.core.MabiCraftBlock;
import rgn.mods.mabicraft.enchant.BlockBonfire;
import rgn.mods.mabicraft.enchant.BlockEnchanter;
import cpw.mods.fml.common.registry.GameRegistry;

public class ConfigureBlock
{
	public static void init()
	{
		MabiCraftBlock.blockBonfire   = (new BlockBonfire(Config.blockIdBonfire,      31)).setBlockName("bonfire");
		MabiCraftBlock.blockEnchanter = (new BlockEnchanter(Config.blockIdEnchanter, 166)).setBlockName("enchanter");
		MabiCraftBlock.blockCookware  = (new BlockCookware(Config.blockIdCookware, 0)).setBlockName("cookware");

		GameRegistry.registerBlock(MabiCraftBlock.blockBonfire, "Bonfire");
		GameRegistry.registerBlock(MabiCraftBlock.blockEnchanter, "Enchanter");
		GameRegistry.registerBlock(MabiCraftBlock.blockCookware, ItemCookware.class, "Cookware");
	}
}