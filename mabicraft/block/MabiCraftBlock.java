package rgn.mods.mabicraft.block;

import net.minecraft.block.Block;

import cpw.mods.fml.common.registry.GameRegistry;

import rgn.mods.mabicraft.config.Config;

public class MabiCraftBlock
{
	public static Block blockBonfire;
	public static Block blockEnchanter;
	public static Block blockCookware;

	public static void configure(Config config)
	{
		blockBonfire   = (new BlockBonfire(config.blockIdBonfire,      31)).setBlockName("bonfire");
		blockEnchanter = (new BlockEnchanter(config.blockIdEnchanter, 166)).setBlockName("enchanter").setCreativeTab(Config.tabMabiCraft);
		blockCookware  = (new BlockCookware(config.blockIdCookware, 0)).setBlockName("cookware").setCreativeTab(Config.tabMabiCraft);

		GameRegistry.registerBlock(blockBonfire,   "Bonfire");
		GameRegistry.registerBlock(blockEnchanter, "Enchanter");
		GameRegistry.registerBlock(blockCookware,  "Cookware");
	}
}