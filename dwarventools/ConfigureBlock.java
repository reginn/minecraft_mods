package rgn.mods.dwarventools;

import java.io.*;
import net.minecraft.src.*;

import net.minecraftforge.common.MinecraftForge;

import cpw.mods.fml.common.registry.GameRegistry;

public class ConfigureBlock
{
	public static void init()
	{		
		DwarvenBlock.blockMithril    = (new BlockDwarvenOreStorage(Config.blockIdBlockMithril, 17)).setBlockName("oldMithrilBlock");
		DwarvenBlock.blockEbony      = (new BlockDwarvenOreStorage(Config.blockIdBlockEbony,   18)).setBlockName("oldEbonyBlock");
		// DwarvenBlock.blockRedstone   = (new BlockRedstone(Config.blockIdBlockRedstone,         4)).setBlockName("blockRedstone");
		
		DwarvenBlock.blockInfernalFurnace = (new BlockInfernalFurnace(Config.blockIdInfernalFurnace, 34)).setBlockName("infernalfurnace");
		
		DwarvenBlock.blockDwarvenOre        = (new BlockDwarvenOre(Config.blockIdBlockMithrilOre, 1)).setBlockName("blockDwarvenOre");
		DwarvenBlock.blockDwarvenOreStorage = (new BlockDwarvenOreStorage(Config.blockIdBlockRedstone, 16)).setBlockName("blockDwarvenOreStorage");
		
		GameRegistry.registerBlock(DwarvenBlock.blockDwarvenOre, ItemDwarvenOre.class);
		GameRegistry.registerBlock(DwarvenBlock.blockDwarvenOreStorage, ItemDwarvenOreStorage.class);

		GameRegistry.registerBlock(DwarvenBlock.blockMithril);
		GameRegistry.registerBlock(DwarvenBlock.blockEbony);
		GameRegistry.registerBlock(DwarvenBlock.blockInfernalFurnace);
		
		MinecraftForge.setBlockHarvestLevel(DwarvenBlock.blockDwarvenOre, 0,   "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(DwarvenBlock.blockDwarvenOre, 1,   "pickaxe", 3);
		MinecraftForge.setBlockHarvestLevel(DwarvenBlock.blockDwarvenOreStorage, 0, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(DwarvenBlock.blockDwarvenOreStorage, 1, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(DwarvenBlock.blockDwarvenOreStorage, 2, "pickaxe", 3);
		
		MinecraftForge.setBlockHarvestLevel(DwarvenBlock.blockMithril,         "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(DwarvenBlock.blockEbony,           "pickaxe", 3);
		
		MinecraftForge.setBlockHarvestLevel(DwarvenBlock.blockInfernalFurnace, "pickaxe", 1);
		
		GameRegistry.registerTileEntity(TileEntityInfernalFurnace.class, "InfernalFurnace");
	}
}