package rgn.mods.dwarventools.config;

import cpw.mods.fml.common.registry.GameRegistry;

import net.minecraftforge.common.MinecraftForge;

import rgn.mods.dwarventools.block.BlockDwarvenOre;
import rgn.mods.dwarventools.block.BlockDwarvenOreStorage;
import rgn.mods.dwarventools.block.BlockInfernalFurnace;
import rgn.mods.dwarventools.block.DwarvenBlock;
import rgn.mods.dwarventools.item.ItemDwarvenOre;
import rgn.mods.dwarventools.item.ItemDwarvenOreStorage;
import rgn.mods.dwarventools.tileentity.TileEntityInfernalFurnace;

public class ConfigureBlock
{
	public static void init()
	{
		DwarvenBlock.blockInfernalFurnace = (new BlockInfernalFurnace(Config.blockIdInfernalFurnace, 34)).setBlockName("infernalfurnace");

		DwarvenBlock.blockDwarvenOre        = (new BlockDwarvenOre(Config.blockIdBlockDwarvenOre, 1)).setBlockName("blockDwarvenOre");
		DwarvenBlock.blockDwarvenOreStorage = (new BlockDwarvenOreStorage(Config.blockIdBlockDwarvenOreStorage, 16)).setBlockName("blockDwarvenOreStorage");

		GameRegistry.registerBlock(DwarvenBlock.blockDwarvenOre, ItemDwarvenOre.class, "DwarvenOre");
		GameRegistry.registerBlock(DwarvenBlock.blockDwarvenOreStorage, ItemDwarvenOreStorage.class, "DwarvenOreStorage");

		GameRegistry.registerBlock(DwarvenBlock.blockInfernalFurnace, "InfernalFurnace");

		MinecraftForge.setBlockHarvestLevel(DwarvenBlock.blockDwarvenOre, 0,   "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(DwarvenBlock.blockDwarvenOre, 1,   "pickaxe", 3);
		MinecraftForge.setBlockHarvestLevel(DwarvenBlock.blockDwarvenOreStorage, 0, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(DwarvenBlock.blockDwarvenOreStorage, 1, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(DwarvenBlock.blockDwarvenOreStorage, 2, "pickaxe", 3);
		
		MinecraftForge.setBlockHarvestLevel(DwarvenBlock.blockInfernalFurnace, "pickaxe", 1);

		GameRegistry.registerTileEntity(TileEntityInfernalFurnace.class, "InfernalFurnace");
	}
}