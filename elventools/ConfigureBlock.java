package rgn.mods.elventools;

import net.minecraft.src.*;

import net.minecraftforge.common.MinecraftForge;

import cpw.mods.fml.common.registry.GameRegistry;

public class ConfigureBlock
{
	public static void init()
	{
		ElvenBlock.blockEbonyLog     = (new BlockEbonyLog(Config.blockIdEbonyLog,         0)).setBlockName("blockEbonyLog");
		ElvenBlock.blockEbonyLeaves  = (new BlockEbonyLeaves(Config.blockIdEbonyLeaves,   3)).setBlockName("blockEbonyLeaves");
		ElvenBlock.blockEbonySapling = (new BlockEbonySapling(Config.blockIdEbonySapling, 4)).setBlockName("blockEbonySapling");
		ElvenBlock.blockEbonyWood    = (new BlockEbonyWood(Config.blockIdEbonyWood,       2)).setBlockName("blockEbonyWood");
		
		ElvenBlock.blockRope            = (new BlockRope(Config.blockIdRope, 16)).setBlockName("blockRope.nouses");
		ElvenBlock.blockRopeEstablisher = (new BlockRopeEstablisher(Config.blockIdRopeEstablisher, 16)).setBlockName("blockRopeEstablisher");
		
		MinecraftForge.setBlockHarvestLevel(ElvenBlock.blockEbonyLog,    "axe", 0);
		MinecraftForge.setBlockHarvestLevel(ElvenBlock.blockEbonyWood,   "axe", 0);
		MinecraftForge.setBlockHarvestLevel(ElvenBlock.blockEbonyLeaves, "axe", 0);
		
		GameRegistry.registerBlock(ElvenBlock.blockEbonyLog);
		GameRegistry.registerBlock(ElvenBlock.blockEbonyLeaves);
		GameRegistry.registerBlock(ElvenBlock.blockEbonySapling);
		GameRegistry.registerBlock(ElvenBlock.blockEbonyWood);
		GameRegistry.registerBlock(ElvenBlock.blockRope);
		GameRegistry.registerBlock(ElvenBlock.blockRopeEstablisher);
		
		GameRegistry.addSmelting(ElvenBlock.blockEbonyLog.blockID, new ItemStack(Item.coal, 2, 1), 1.0F);
	}
}