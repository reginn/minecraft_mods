package rgn.mods.elventools.block;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import net.minecraftforge.common.MinecraftForge;

import cpw.mods.fml.common.registry.GameRegistry;

import rgn.mods.elventools.config.Config;

public class ElvenBlock
{
	public static Block blockEbonyLog;
	public static Block blockEbonyLeaves;
	public static Block blockEbonySapling;
	public static Block blockEbonyWood;

	public static Block blockRope;
	public static Block blockRopeEstablisher;

	public static void configure(Config config)
	{
		blockEbonyLog     = (new BlockEbonyLog(config.blockIdEbonyLog        )).setUnlocalizedName("blockEbonyLog").setCreativeTab(Config.tabElvenTools);
		blockEbonyLeaves  = (new BlockEbonyLeaves(config.blockIdEbonyLeaves  )).setUnlocalizedName("blockEbonyLeaves").setCreativeTab(Config.tabElvenTools);
		blockEbonySapling = (new BlockEbonySapling(config.blockIdEbonySapling)).setUnlocalizedName("blockEbonySapling").setCreativeTab(Config.tabElvenTools);
		blockEbonyWood    = (new BlockEbonyWood(config.blockIdEbonyWood      )).setUnlocalizedName("blockEbonyWood").setCreativeTab(Config.tabElvenTools);

		blockRope            = (new BlockRope(config.blockIdRope)).setUnlocalizedName("blockRope.nouses");
		blockRopeEstablisher = (new BlockRopeEstablisher(config.blockIdRopeEstablisher)).setUnlocalizedName("blockRopeEstablisher");

		MinecraftForge.setBlockHarvestLevel(blockEbonyLog,    "axe", 0);
		MinecraftForge.setBlockHarvestLevel(blockEbonyWood,   "axe", 0);
		MinecraftForge.setBlockHarvestLevel(blockEbonyLeaves, "axe", 0);

		GameRegistry.registerBlock(blockEbonyLog,        "EbonyLog");
		GameRegistry.registerBlock(blockEbonyLeaves,     "EbonyLeaves");
		GameRegistry.registerBlock(blockEbonySapling,    "EbonySapling");
		GameRegistry.registerBlock(blockEbonyWood,       "EbonyWood");
		GameRegistry.registerBlock(blockRope,            "Rope");
		GameRegistry.registerBlock(blockRopeEstablisher, "RopeEstablisher");

		GameRegistry.addSmelting(blockEbonyLog.blockID, new ItemStack(Item.coal, 2, 1), 1.0F);
	}
}