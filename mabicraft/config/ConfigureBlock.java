package rgn.mods.mabicraft.config;

import net.minecraft.src.*;

import cpw.mods.fml.common.registry.GameRegistry;

import rgn.mods.mabicraft.core.MabiCraftBlock;

import rgn.mods.mabicraft.enchant.BlockBonfire;
import rgn.mods.mabicraft.enchant.BlockEnchanter;

import rgn.mods.mabicraft.cook.BlockCookware;
import rgn.mods.mabicraft.cook.ItemCookware;

public class ConfigureBlock
{
	public static void init()
	{
		MabiCraftBlock.blockBonfire   = (new BlockBonfire(Config.blockIdBonfire,      31)).setBlockName("bonfire");
		MabiCraftBlock.blockEnchanter = (new BlockEnchanter(Config.blockIdEnchanter, 166)).setBlockName("enchanter");
		MabiCraftBlock.blockCookware  = (new BlockCookware(Config.blockIdCookware, 0)).setBlockName("cookware");
		
		GameRegistry.registerBlock(MabiCraftBlock.blockBonfire);
		GameRegistry.registerBlock(MabiCraftBlock.blockEnchanter);
		GameRegistry.registerBlock(MabiCraftBlock.blockCookware, ItemCookware.class);
	}
}