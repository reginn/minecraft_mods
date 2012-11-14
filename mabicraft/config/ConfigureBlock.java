package rgn.mods.mabicraft.config;

import net.minecraft.src.*;

import cpw.mods.fml.common.registry.GameRegistry;

import rgn.mods.mabicraft.common.*;

public class ConfigureBlock
{
	public static void init()
	{
		MabiCraftBlock.blockBonfire   = (new BlockBonfire(Config.blockIdBonfire,      31)).setBlockName("bonfire");
		MabiCraftBlock.blockEnchanter = (new BlockEnchanter(Config.blockIdEnchanter, 166)).setBlockName("enchanter");
		
		GameRegistry.registerBlock(MabiCraftBlock.blockBonfire);
		GameRegistry.registerBlock(MabiCraftBlock.blockEnchanter);
	}
}