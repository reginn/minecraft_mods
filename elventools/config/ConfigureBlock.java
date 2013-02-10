package rgn.mods.elventools.config;

import cpw.mods.fml.common.registry.GameRegistry;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import net.minecraftforge.common.MinecraftForge;

import rgn.mods.elventools.block.BlockEbonyLeaves;
import rgn.mods.elventools.block.BlockEbonyLog;
import rgn.mods.elventools.block.BlockEbonySapling;
import rgn.mods.elventools.block.BlockEbonyWood;
import rgn.mods.elventools.block.BlockRope;
import rgn.mods.elventools.block.BlockRopeEstablisher;
import rgn.mods.elventools.block.ElvenBlock;

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

		GameRegistry.registerBlock(ElvenBlock.blockEbonyLog,        "EbonyLog");
		GameRegistry.registerBlock(ElvenBlock.blockEbonyLeaves,     "EbonyLeaves");
		GameRegistry.registerBlock(ElvenBlock.blockEbonySapling,    "EbonySapling");
		GameRegistry.registerBlock(ElvenBlock.blockEbonyWood,       "EbonyWood");
		GameRegistry.registerBlock(ElvenBlock.blockRope,            "Rope");
		GameRegistry.registerBlock(ElvenBlock.blockRopeEstablisher, "RopeEstablisher");

		GameRegistry.addSmelting(ElvenBlock.blockEbonyLog.blockID, new ItemStack(Item.coal, 2, 1), 1.0F);
	}
}