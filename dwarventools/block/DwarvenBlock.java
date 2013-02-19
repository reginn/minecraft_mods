package rgn.mods.dwarventools.block;

import net.minecraft.block.Block;

import net.minecraftforge.common.MinecraftForge;

import cpw.mods.fml.common.registry.GameRegistry;

import rgn.mods.dwarventools.config.Config;
import rgn.mods.dwarventools.item.ItemDwarvenOre;
import rgn.mods.dwarventools.item.ItemDwarvenOreStorage;
import rgn.mods.dwarventools.tileentity.TileEntityInfernalFurnace;

public class DwarvenBlock
{
	public static Block blockDwarvenOre;
	public static Block blockDwarvenOreStorage;
	public static Block blockInfernalFurnace;

	public static void configure(Config config)
	{
		blockInfernalFurnace   = (new BlockInfernalFurnace(config.blockIdInfernalFurnace, 34)).setBlockName("infernalfurnace").setCreativeTab(Config.tabDwarvenTools);
		blockDwarvenOre        = (new BlockDwarvenOre(config.blockIdBlockDwarvenOre, 1)).setBlockName("blockDwarvenOre").setCreativeTab(Config.tabDwarvenTools);
		blockDwarvenOreStorage = (new BlockDwarvenOreStorage(config.blockIdBlockDwarvenOreStorage, 16)).setBlockName("blockDwarvenOreStorage").setCreativeTab(Config.tabDwarvenTools);

		GameRegistry.registerBlock(blockDwarvenOre, ItemDwarvenOre.class, "DwarvenOre");
		GameRegistry.registerBlock(blockDwarvenOreStorage, ItemDwarvenOreStorage.class, "DwarvenOreStorage");

		GameRegistry.registerBlock(blockInfernalFurnace, "InfernalFurnace");

		MinecraftForge.setBlockHarvestLevel(blockDwarvenOre, 0,   "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(blockDwarvenOre, 1,   "pickaxe", 3);
		MinecraftForge.setBlockHarvestLevel(blockDwarvenOreStorage, 0, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(blockDwarvenOreStorage, 1, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(blockDwarvenOreStorage, 2, "pickaxe", 3);

		MinecraftForge.setBlockHarvestLevel(blockInfernalFurnace, "pickaxe", 1);

		GameRegistry.registerTileEntity(TileEntityInfernalFurnace.class, "InfernalFurnace");
	}
}