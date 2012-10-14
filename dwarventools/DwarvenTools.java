package rgn.mods.dwarventools;

import java.util.logging.Level;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

import net.minecraft.src.*;

import net.minecraftforge.common.*;
import net.minecraftforge.oredict.*;

import cpw.mods.fml.common.*;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.network.*;
import cpw.mods.fml.common.registry.*;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;

import rgn.util.TranslationRegistry;

@Mod(modid = "DwarvenTools", name = "DwarvenTools", version = "2.2.6")
@NetworkMod(channels = { "DwarvenTools" }, clientSideRequired = true, serverSideRequired = false, packetHandler = PacketHandler.class)
public class DwarvenTools
{
	@Instance("DwarvenTools")
	public static DwarvenTools instance;
	
	@SidedProxy(clientSide = "rgn.mods.dwarventools.client.ClientProxy", serverSide = "rgn.mods.dwarventools.CommonProxy")
	public static CommonProxy proxy;
	
	//------------------------------------------------------ blocks
	public static Block blockRedstone;
	public static Block blockMithrilOre;
	public static Block blockEbonyOre;
	public static Block blockMithril;
	public static Block blockEbony;
	public static Block blockDwarvenStorage;
	
	public static Block blockInfernalFurnace;
	
	public static Block blockDwarvenOre;
	
	public static int blockIdBlockRedstone;
	public static int blockIdBlockMithrilOre;
	public static int blockIdBlockEbonyOre;
	public static int blockIdBlockMithril;
	public static int blockIdBlockEbony;
	
	public static int blockIdInfernalFurnace;
	
	//------------------------------------------------------ items
	public static Item itemIronBar;
	public static Item itemMithrilIngot;
	public static Item itemEbonyIngot;
	
	public static Item itemDwarvenShovelObsidian;
	public static Item itemDwarvenPickaxeObsidian;
	public static Item itemDwarvenAxeObsidian;
	public static Item itemDwarvenHoeObsidian;
	public static Item itemDwarvenSwordObsidian;
	
	public static Item itemDwarvenShovelLapislazuli;
	public static Item itemDwarvenPickaxeLapislazuli;
	public static Item itemDwarvenAxeLapislazuli;
	public static Item itemDwarvenHoeLapislazuli;
	public static Item itemDwarvenSwordLapislazuli;
	
	public static Item itemDwarvenShovelRedstone;
	public static Item itemDwarvenPickaxeRedstone;
	public static Item itemDwarvenAxeRedstone;
	public static Item itemDwarvenHoeRedstone;
	public static Item itemDwarvenSwordRedstone;
	
	public static Item itemDwarvenShovelMithril;
	public static Item itemDwarvenPickaxeMithril;
	public static Item itemDwarvenBattleaxeMithril;
	public static Item itemDwarvenBrokenSwordMithril;
	public static Item itemDwarvenSwordMithril;
	
	public static Item itemDwarvenShovelEbony;
	public static Item itemDwarvenPickaxeEbony;
	public static Item itemDwarvenBattleaxeEbony;
	public static Item itemDwarvenBrokenSwordEbony;
	public static Item itemDwarvenSwordEbony;
	
	public static Item itemDwarvenHelmetMithril;
	public static Item itemDwarvenPlateMithril;
	public static Item itemDwarvenLegsMithril;
	public static Item itemDwarvenBootMithril;
	
	public static Item itemDwarvenHelmetEbony;
	public static Item itemDwarvenPlateEbony;
	public static Item itemDwarvenLegsEbony;
	public static Item itemDwarvenBootEbony;
	
	//------------------------------------------------------ item ids
	public static int itemIdDwarvenShovelObsidian;
	public static int itemIdDwarvenPickaxeObsidian;
	public static int itemIdDwarvenAxeObsidian;
	public static int itemIdDwarvenHoeObsidian;
	public static int itemIdDwarvenSwordObsidian;
	
	public static int itemIdDwarvenShovelLapislazuli;
	public static int itemIdDwarvenPickaxeLapislazuli;
	public static int itemIdDwarvenAxeLapislazuli;
	public static int itemIdDwarvenHoeLapislazuli;
	public static int itemIdDwarvenSwordLapislazuli;
	
	public static int itemIdDwarvenShovelRedstone;
	public static int itemIdDwarvenPickaxeRedstone;
	public static int itemIdDwarvenAxeRedstone;
	public static int itemIdDwarvenHoeRedstone;
	public static int itemIdDwarvenSwordRedstone;
		
	public static int itemIdDwarvenShovelMithril;
	public static int itemIdDwarvenPickaxeMithril;
	public static int itemIdDwarvenBattleaxeMithril;
	public static int itemIdDwarvenBrokenSwordMithril;
	public static int itemIdDwarvenSwordMithril;
	                        
	public static int itemIdDwarvenShovelEbony;
	public static int itemIdDwarvenPickaxeEbony;
	public static int itemIdDwarvenBattleaxeEbony;
	public static int itemIdDwarvenBrokenSwordEbony;
	public static int itemIdDwarvenSwordEbony;
	
	public static int itemIdIronBar;
	public static int itemIdMithrilIngot;
	public static int itemIdEbonyIngot;
	
	public static int itemIdDwarvenHelmetMithril;
	public static int itemIdDwarvenPlateMithril;
	public static int itemIdDwarvenBootMithril;
	public static int itemIdDwarvenLegsMithril;
	
	public static int itemIdDwarvenHelmetEbony;
	public static int itemIdDwarvenPlateEbony;
	public static int itemIdDwarvenBootEbony;
	public static int itemIdDwarvenLegsEbony;	
	
	//------------------------------------------------------ enum
	public static EnumToolMaterial enumToolMaterialObsidian;
	public static EnumToolMaterial enumToolMaterialLapislazuli;
	public static EnumToolMaterial enumToolMaterialRedstone;
	public static EnumToolMaterial enumToolMaterialMithril;
	public static EnumToolMaterial enumToolMaterialEbony;
	
	public static EnumArmorMaterial enumArmorMaterialMithril;
	public static EnumArmorMaterial enumArmorMaterialEbony;
	
	//------------------------------------------------------ gui ids
	public static int guiIdInfernalFurnace = 0;
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event)
	{
		Configuration cfg = new Configuration(event.getSuggestedConfigurationFile());
		try
		{
			cfg.load();
			//guiIdInfernalFurnace = cfg.getOrCreateIntProperty("gui.id.InfernalFurnace", Configuration.CATEGORY_GENERAL, 113).getInt();
			
			blockIdBlockRedstone   = cfg.getOrCreateIntProperty("RedstoneBlock",   Configuration.CATEGORY_BLOCK, 1500).getInt();
			blockIdBlockMithrilOre = cfg.getOrCreateIntProperty("MithrilOre",      Configuration.CATEGORY_BLOCK, 1501).getInt();
			blockIdBlockEbonyOre   = cfg.getOrCreateIntProperty("EbonyOre",        Configuration.CATEGORY_BLOCK, 1502).getInt();
			blockIdBlockMithril    = cfg.getOrCreateIntProperty("MithrilBlock",    Configuration.CATEGORY_BLOCK, 1503).getInt();
			blockIdBlockEbony      = cfg.getOrCreateIntProperty("EbonyBlock",      Configuration.CATEGORY_BLOCK, 1504).getInt();
			blockIdInfernalFurnace = cfg.getOrCreateIntProperty("InfernalFurnace", Configuration.CATEGORY_BLOCK, 1505).getInt();
			
			itemIdIronBar      = cfg.getOrCreateIntProperty("IronBar",      Configuration.CATEGORY_ITEM, 10500).getInt();
			itemIdMithrilIngot = cfg.getOrCreateIntProperty("MithrilIngot", Configuration.CATEGORY_ITEM, 10501).getInt();
			itemIdEbonyIngot   = cfg.getOrCreateIntProperty("EbonyIngot",   Configuration.CATEGORY_ITEM, 10502).getInt();
			
			itemIdDwarvenShovelObsidian  = cfg.getOrCreateIntProperty("DwarvenObsidianShovel",  Configuration.CATEGORY_ITEM, 11000).getInt();
			itemIdDwarvenPickaxeObsidian = cfg.getOrCreateIntProperty("DwarvenObsidianPickaxe", Configuration.CATEGORY_ITEM, 11001).getInt();
			itemIdDwarvenAxeObsidian     = cfg.getOrCreateIntProperty("DwarvenObsidianAxe",     Configuration.CATEGORY_ITEM, 11002).getInt();
			itemIdDwarvenHoeObsidian     = cfg.getOrCreateIntProperty("DwarvenObsidianHoe",     Configuration.CATEGORY_ITEM, 11003).getInt();
			itemIdDwarvenSwordObsidian   = cfg.getOrCreateIntProperty("DwarvenObsidianSword",   Configuration.CATEGORY_ITEM, 11004).getInt();
			
			itemIdDwarvenShovelLapislazuli  = cfg.getOrCreateIntProperty("DwarvenLapislazuliShovel",  Configuration.CATEGORY_ITEM, 11005).getInt();
			itemIdDwarvenPickaxeLapislazuli = cfg.getOrCreateIntProperty("DwarvenLapislazuliPickaxe", Configuration.CATEGORY_ITEM, 11006).getInt();
			itemIdDwarvenAxeLapislazuli     = cfg.getOrCreateIntProperty("DwarvenLapislazuliAxe",     Configuration.CATEGORY_ITEM, 11007).getInt();
			itemIdDwarvenHoeLapislazuli     = cfg.getOrCreateIntProperty("DwarvenLapislazuliHoe",     Configuration.CATEGORY_ITEM, 11008).getInt();
			itemIdDwarvenSwordLapislazuli   = cfg.getOrCreateIntProperty("DwarvenLapislazuliSword",   Configuration.CATEGORY_ITEM, 11009).getInt();
			
			itemIdDwarvenShovelRedstone  = cfg.getOrCreateIntProperty("DwarvenRedstoneShovel",  Configuration.CATEGORY_ITEM, 11010).getInt();
			itemIdDwarvenPickaxeRedstone = cfg.getOrCreateIntProperty("DwarvenRedstonePickaxe", Configuration.CATEGORY_ITEM, 11011).getInt();
			itemIdDwarvenAxeRedstone     = cfg.getOrCreateIntProperty("DwarvenRedstoneAxe",     Configuration.CATEGORY_ITEM, 11012).getInt();
			itemIdDwarvenHoeRedstone     = cfg.getOrCreateIntProperty("DwarvenRedstoneHoe",     Configuration.CATEGORY_ITEM, 11013).getInt();
			itemIdDwarvenSwordRedstone   = cfg.getOrCreateIntProperty("DwarvenRedstoneSword",   Configuration.CATEGORY_ITEM, 11014).getInt();
			
			itemIdDwarvenShovelMithril      = cfg.getOrCreateIntProperty("DwarvenMithrilShovel",      Configuration.CATEGORY_ITEM, 11015).getInt();
			itemIdDwarvenPickaxeMithril     = cfg.getOrCreateIntProperty("DwarvenMithrilPickaxe",     Configuration.CATEGORY_ITEM, 11016).getInt();
			itemIdDwarvenBattleaxeMithril   = cfg.getOrCreateIntProperty("DwarvenMithrilBattleaxe",   Configuration.CATEGORY_ITEM, 11017).getInt();
			itemIdDwarvenBrokenSwordMithril = cfg.getOrCreateIntProperty("DwarvenMithrilBrokenSword", Configuration.CATEGORY_ITEM, 11018).getInt();
			itemIdDwarvenSwordMithril       = cfg.getOrCreateIntProperty("DwarvenMithrilSword",       Configuration.CATEGORY_ITEM, 11019).getInt();
			      
			itemIdDwarvenShovelEbony      = cfg.getOrCreateIntProperty("DwarvenEbonyShovel",      Configuration.CATEGORY_ITEM, 11020).getInt();
			itemIdDwarvenPickaxeEbony     = cfg.getOrCreateIntProperty("DwarvenEbonyPickaxe",     Configuration.CATEGORY_ITEM, 11021).getInt();
			itemIdDwarvenBattleaxeEbony   = cfg.getOrCreateIntProperty("DwarvenEbonyBattleaxe",   Configuration.CATEGORY_ITEM, 11022).getInt();
			itemIdDwarvenBrokenSwordEbony = cfg.getOrCreateIntProperty("DwarvenEbonyBrokenSword", Configuration.CATEGORY_ITEM, 11023).getInt();
			itemIdDwarvenSwordEbony       = cfg.getOrCreateIntProperty("DwarvenEbonySword",       Configuration.CATEGORY_ITEM, 11024).getInt();
			
			itemIdDwarvenHelmetMithril = cfg.getOrCreateIntProperty("DwarvenMithrilHelmet", Configuration.CATEGORY_ITEM, 11025).getInt();
			itemIdDwarvenPlateMithril  = cfg.getOrCreateIntProperty("DwarvenMithrilPlate",  Configuration.CATEGORY_ITEM, 11026).getInt();
			itemIdDwarvenBootMithril   = cfg.getOrCreateIntProperty("DwarvenMithrilBoot",   Configuration.CATEGORY_ITEM, 11027).getInt();
			itemIdDwarvenLegsMithril   = cfg.getOrCreateIntProperty("DwarvenMithrilLegs",   Configuration.CATEGORY_ITEM, 11028).getInt();
			
			itemIdDwarvenHelmetEbony = cfg.getOrCreateIntProperty("DwarvenEbonyHelmet", Configuration.CATEGORY_ITEM, 11029).getInt();
			itemIdDwarvenPlateEbony  = cfg.getOrCreateIntProperty("DwarvenEbonyPlate",  Configuration.CATEGORY_ITEM, 11030).getInt();
			itemIdDwarvenBootEbony   = cfg.getOrCreateIntProperty("DwarvenEbonyBoot",   Configuration.CATEGORY_ITEM, 11031).getInt();
			itemIdDwarvenLegsEbony   = cfg.getOrCreateIntProperty("DwarvenEbonyLegs",   Configuration.CATEGORY_ITEM, 11032).getInt();
		}
		catch (Exception e)
		{
			FMLLog.log(Level.SEVERE, e, "DwarvenTools has a problem loading it's configuration");
		}
		finally
		{
			cfg.save();
		}
	}
	
	@Init
	public void init(FMLInitializationEvent event)
	{
		
		itemIronBar      = (new ItemDwarvenParts(itemIdIronBar      - 256)).setIconCoord( 5, 0).setItemName("itemIronBar");
		itemMithrilIngot = (new ItemDwarvenParts(itemIdMithrilIngot - 256)).setIconCoord( 4, 5).setItemName("itemMithrilIngot");
		itemEbonyIngot   = (new ItemDwarvenParts(itemIdEbonyIngot   - 256)).setIconCoord( 4, 6).setItemName("itemEbonyIngot");
		
		blockMithril    = (new BlockDwarvenOreStorage(blockIdBlockMithril, 2)).setBlockName("blockMithril");
		blockEbony      = (new BlockDwarvenOreStorage(blockIdBlockEbony,   3)).setBlockName("blockEbony");
		blockRedstone   = (new BlockRedstone(blockIdBlockRedstone,         4)).setBlockName("blockRedstone");
		
		blockInfernalFurnace = (new BlockInfernalFurnace(blockIdInfernalFurnace, 18)).setBlockName("infernalfurnace").setLightValue(12.0F/16.0F);
		
		blockDwarvenOre = (new BlockDwarvenOre(blockIdBlockMithrilOre, 0)).setBlockName("blockDwarvenOre");
		
		GameRegistry.registerBlock(blockDwarvenOre, ItemDwarvenOre.class);
		GameRegistry.registerBlock(blockRedstone);
		GameRegistry.registerBlock(blockMithril);
		GameRegistry.registerBlock(blockEbony);
		GameRegistry.registerBlock(blockInfernalFurnace);
		
		OreDictionary.registerOre("oreMithril", new ItemStack(blockDwarvenOre, 1, 0));
		OreDictionary.registerOre("oreEbony",   new ItemStack(blockDwarvenOre, 1, 1));
		OreDictionary.registerOre("ingotMithril", itemMithrilIngot);
		OreDictionary.registerOre("ingotEbony",   itemEbonyIngot);
		
		FurnaceRecipes.smelting().addSmelting(blockDwarvenOre.blockID, 0, new ItemStack(itemMithrilIngot));
		FurnaceRecipes.smelting().addSmelting(blockDwarvenOre.blockID, 1, new ItemStack(itemEbonyIngot));
		
		enumToolMaterialObsidian    = EnumHelper.addToolMaterial("OBSIDIAN",    1, 4096,  4.0F, 1, 0);
		enumToolMaterialLapislazuli = EnumHelper.addToolMaterial("LAPISLAZULI", 2,  512,  6.0F, 2, 0);
		enumToolMaterialRedstone    = EnumHelper.addToolMaterial("REDSTONE",    3, 1024,  8.0F, 3, 0);
		enumToolMaterialMithril     = EnumHelper.addToolMaterial("MITHRIL",     2, 1024,  6.0F, 3, 0);
		enumToolMaterialEbony       = EnumHelper.addToolMaterial("EBONY",       3, 2048,  8.0F, 3, 0);
		
		enumArmorMaterialMithril = EnumHelper.addArmorMaterial("MITHRIL", 15, new int[] {2, 6, 5, 2}, 0);
		enumArmorMaterialEbony   = EnumHelper.addArmorMaterial("EBONY",   33, new int[] {3, 8, 6, 3}, 0);
		
		itemDwarvenShovelObsidian  = (new ItemDwarvenShovel(itemIdDwarvenShovelObsidian   - 256, enumToolMaterialObsidian)).setIconCoord( 0, 0).setItemName("itemShovelObsidian");
		itemDwarvenPickaxeObsidian = (new ItemDwarvenPickaxe(itemIdDwarvenPickaxeObsidian - 256, enumToolMaterialObsidian)).setIconCoord( 1, 0).setItemName("itemPickaxeObsidian");
		itemDwarvenAxeObsidian     = (new ItemDwarvenAxe(itemIdDwarvenAxeObsidian         - 256, enumToolMaterialObsidian)).setIconCoord( 2, 0).setItemName("itemAxeObsidian");
		itemDwarvenHoeObsidian     = (new ItemDwarvenHoe(itemIdDwarvenHoeObsidian         - 256, enumToolMaterialObsidian)).setIconCoord( 3, 0).setItemName("itemHoeObsidian");
		itemDwarvenSwordObsidian   = (new ItemDwarvenSword(itemIdDwarvenSwordObsidian     - 256, enumToolMaterialObsidian)).setIconCoord( 4, 0).setItemName("itemSwordObsidian");
		
		itemDwarvenShovelLapislazuli  = (new ItemDwarvenShovel(itemIdDwarvenShovelLapislazuli   - 256, enumToolMaterialLapislazuli)).setIconCoord( 0, 1).setItemName("itemShovelLapis");
		itemDwarvenPickaxeLapislazuli = (new ItemDwarvenPickaxe(itemIdDwarvenPickaxeLapislazuli - 256, enumToolMaterialLapislazuli)).setIconCoord( 1, 1).setItemName("itemPickaxeLapis");
		itemDwarvenAxeLapislazuli     = (new ItemDwarvenAxe(itemIdDwarvenAxeLapislazuli         - 256, enumToolMaterialLapislazuli)).setIconCoord( 2, 1).setItemName("itemAxeLapis");
		itemDwarvenHoeLapislazuli     = (new ItemDwarvenHoe(itemIdDwarvenHoeLapislazuli         - 256, enumToolMaterialLapislazuli)).setIconCoord( 3, 1).setItemName("itemHoeLapis");
		itemDwarvenSwordLapislazuli   = (new ItemDwarvenSword(itemIdDwarvenSwordLapislazuli     - 256, enumToolMaterialLapislazuli)).setIconCoord( 4, 1).setItemName("itemSwordLapis");
		
		itemDwarvenShovelRedstone  = (new ItemDwarvenShovel(itemIdDwarvenShovelRedstone   - 256, enumToolMaterialRedstone)).setIconCoord( 0, 2).setItemName("itemShovelRedstone");
		itemDwarvenPickaxeRedstone = (new ItemDwarvenPickaxe(itemIdDwarvenPickaxeRedstone - 256, enumToolMaterialRedstone)).setIconCoord( 1, 2).setItemName("itemPickaxeRedstone");
		itemDwarvenAxeRedstone     = (new ItemDwarvenAxe(itemIdDwarvenAxeRedstone         - 256, enumToolMaterialRedstone)).setIconCoord( 2, 2).setItemName("itemAxeRedstone");
		itemDwarvenHoeRedstone     = (new ItemDwarvenHoe(itemIdDwarvenHoeRedstone         - 256, enumToolMaterialRedstone)).setIconCoord( 3, 2).setItemName("itemHoeRedstone");
		itemDwarvenSwordRedstone   = (new ItemDwarvenSword(itemIdDwarvenSwordRedstone     - 256, enumToolMaterialRedstone)).setIconCoord( 4, 2).setItemName("itemSwordRedstone");
		
		itemDwarvenBrokenSwordMithril = (new ItemDwarvenParts(itemIdDwarvenBrokenSwordMithril - 256)).setIconCoord( 3, 3).setItemName("itemBrokenSwordMithril");
		itemDwarvenBrokenSwordEbony   = (new ItemDwarvenParts(itemIdDwarvenBrokenSwordEbony -   256)).setIconCoord( 3, 4).setItemName("itemBrokenSwordEbony");
		
		itemDwarvenShovelMithril      = (new ItemDwarvenShovel(itemIdDwarvenShovelMithril       - 256, enumToolMaterialMithril)).setIconCoord( 0, 3).setItemName("itemShovelMithril");
		itemDwarvenPickaxeMithril     = (new ItemDwarvenPickaxe(itemIdDwarvenPickaxeMithril     - 256, enumToolMaterialMithril)).setIconCoord( 1, 3).setItemName("itemPickaxeMithril");
		itemDwarvenBattleaxeMithril   = (new ItemDwarvenBattleaxe(itemIdDwarvenBattleaxeMithril - 256, enumToolMaterialMithril)).setIconCoord( 2, 3).setItemName("itemBattleaxeMithril");
		itemDwarvenSwordMithril       = (new ItemDwarvenSword(itemIdDwarvenSwordMithril         - 256, enumToolMaterialMithril)).setIconCoord( 4, 3).setItemName("itemSwordMithril");
		
		itemDwarvenShovelEbony      = (new ItemDwarvenShovel(itemIdDwarvenShovelEbony       - 256, enumToolMaterialEbony)).setIconCoord( 0, 4).setItemName("itemShovelEbony");
		itemDwarvenPickaxeEbony     = (new ItemDwarvenPickaxe(itemIdDwarvenPickaxeEbony     - 256, enumToolMaterialEbony)).setIconCoord( 1, 4).setItemName("itemPickaxeEbony");
		itemDwarvenBattleaxeEbony   = (new ItemDwarvenBattleaxe(itemIdDwarvenBattleaxeEbony - 256, enumToolMaterialEbony)).setIconCoord( 2, 4).setItemName("itemBattleaxeEbony");
		itemDwarvenSwordEbony       = (new ItemDwarvenSword(itemIdDwarvenSwordEbony         - 256, enumToolMaterialEbony)).setIconCoord( 4, 4).setItemName("itemSwordEbony");
		
		
		itemDwarvenHelmetMithril = (new ItemDwarvenArmor(itemIdDwarvenHelmetMithril - 256, enumArmorMaterialMithril, proxy.addArmor("MithrilArmor"), 0)).setIconCoord( 0, 5).setItemName("itemHelmentMithril");
		itemDwarvenPlateMithril  = (new ItemDwarvenArmor(itemIdDwarvenPlateMithril  - 256, enumArmorMaterialMithril, proxy.addArmor("MithrilArmor"), 1)).setIconCoord( 1, 5).setItemName("itemPlateMithril");
		itemDwarvenLegsMithril   = (new ItemDwarvenArmor(itemIdDwarvenLegsMithril   - 256, enumArmorMaterialMithril, proxy.addArmor("MithrilArmor"), 2)).setIconCoord( 2, 5).setItemName("itemLegsMithril");
		itemDwarvenBootMithril   = (new ItemDwarvenArmor(itemIdDwarvenBootMithril   - 256, enumArmorMaterialMithril, proxy.addArmor("MithrilArmor"), 3)).setIconCoord( 3, 5).setItemName("itemBootMithril");
		
		itemDwarvenHelmetEbony = (new ItemDwarvenArmor(itemIdDwarvenHelmetEbony - 256, enumArmorMaterialEbony, proxy.addArmor("EbonyArmor"), 0)).setIconCoord( 0, 6).setItemName("itemHelmentEbony");
		itemDwarvenPlateEbony  = (new ItemDwarvenArmor(itemIdDwarvenPlateEbony  - 256, enumArmorMaterialEbony, proxy.addArmor("EbonyArmor"), 1)).setIconCoord( 1, 6).setItemName("itemPlateEbony");
		itemDwarvenLegsEbony   = (new ItemDwarvenArmor(itemIdDwarvenLegsEbony   - 256, enumArmorMaterialEbony, proxy.addArmor("EbonyArmor"), 2)).setIconCoord( 2, 6).setItemName("itemLegsEbony");
		itemDwarvenBootEbony   = (new ItemDwarvenArmor(itemIdDwarvenBootEbony   - 256, enumArmorMaterialEbony, proxy.addArmor("EbonyArmor"), 3)).setIconCoord( 3, 6).setItemName("itemBootEbony");
		
		MinecraftForge.setToolClass(itemDwarvenShovelObsidian,    "shovel", 1);
		MinecraftForge.setToolClass(itemDwarvenShovelLapislazuli, "shovel", 2);
		MinecraftForge.setToolClass(itemDwarvenShovelRedstone,    "shovel", 3);
		MinecraftForge.setToolClass(itemDwarvenShovelMithril,     "shovel", 2);
		MinecraftForge.setToolClass(itemDwarvenShovelEbony,       "shovel", 3);
		
		MinecraftForge.setToolClass(itemDwarvenPickaxeObsidian,    "pickaxe", 1);
		MinecraftForge.setToolClass(itemDwarvenPickaxeLapislazuli, "pickaxe", 2);
		MinecraftForge.setToolClass(itemDwarvenPickaxeRedstone,    "pickaxe", 3);
		MinecraftForge.setToolClass(itemDwarvenPickaxeMithril,     "pickaxe", 2);
		MinecraftForge.setToolClass(itemDwarvenPickaxeEbony,       "pickaxe", 3);
		
		MinecraftForge.setToolClass(itemDwarvenAxeObsidian,    "axe", 1);
		MinecraftForge.setToolClass(itemDwarvenAxeLapislazuli, "axe", 2);
		MinecraftForge.setToolClass(itemDwarvenAxeRedstone,    "axe", 3);
		
		MinecraftForge.setBlockHarvestLevel(blockDwarvenOre, 0,   "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(blockDwarvenOre, 1,   "pickaxe", 3);
		MinecraftForge.setBlockHarvestLevel(blockMithril,         "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(blockEbony,           "pickaxe", 3);
		MinecraftForge.setBlockHarvestLevel(blockRedstone,        "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(blockInfernalFurnace, "pickaxe", 1);
		
		NetworkRegistry.instance().registerGuiHandler(instance, proxy);
		GameRegistry.registerTileEntity(TileEntityInfernalFurnace.class, "InfernalFurnace");
		GameRegistry.registerWorldGenerator(new DwarvenWorldGenerator());
		MinecraftForge.EVENT_BUS.register(new DwarvenEventHooks());
		
		proxy.registerTextures();
		
		DungeonHooks.setDungeonLootTries(16);
		
		DungeonHooks.addDungeonLoot(new ItemStack(itemMithrilIngot),              20, 1, 3);
		DungeonHooks.addDungeonLoot(new ItemStack(itemEbonyIngot),                10, 1, 2);
		DungeonHooks.addDungeonLoot(new ItemStack(itemDwarvenBrokenSwordMithril),  5);
		DungeonHooks.addDungeonLoot(new ItemStack(itemDwarvenBrokenSwordEbony),    4);
		DungeonHooks.addDungeonLoot(new ItemStack(itemDwarvenHelmetMithril),       3);
		DungeonHooks.addDungeonLoot(new ItemStack(itemDwarvenPlateMithril),        3);
		DungeonHooks.addDungeonLoot(new ItemStack(itemDwarvenBootMithril),         3);
		DungeonHooks.addDungeonLoot(new ItemStack(itemDwarvenLegsMithril),         3);
		DungeonHooks.addDungeonLoot(new ItemStack(itemDwarvenHelmetEbony),         2);
		DungeonHooks.addDungeonLoot(new ItemStack(itemDwarvenPlateEbony),          2);
		DungeonHooks.addDungeonLoot(new ItemStack(itemDwarvenBootEbony),           2);
		DungeonHooks.addDungeonLoot(new ItemStack(itemDwarvenLegsEbony)  ,         2);
		
		DungeonHooks.addDungeonLoot(new ItemStack(Item.expBottle),     50, 1, 5);
		DungeonHooks.addDungeonLoot(new ItemStack(Item.recordBlocks),  5);
		DungeonHooks.addDungeonLoot(new ItemStack(Item.recordChirp),   5);
		DungeonHooks.addDungeonLoot(new ItemStack(Item.recordFar),     5);
		DungeonHooks.addDungeonLoot(new ItemStack(Item.recordMall),    5);
		DungeonHooks.addDungeonLoot(new ItemStack(Item.recordMellohi), 5);
		DungeonHooks.addDungeonLoot(new ItemStack(Item.recordStal),    5);
		DungeonHooks.addDungeonLoot(new ItemStack(Item.recordStrad),   5);
		DungeonHooks.addDungeonLoot(new ItemStack(Item.recordWard),    5);
		DungeonHooks.addDungeonLoot(new ItemStack(Item.record11),      5);
		
		DungeonHooks.addDungeonLoot(new ItemStack(Item.lightStoneDust),   50, 4, 32);
		DungeonHooks.addDungeonLoot(new ItemStack(Item.netherStalkSeeds), 30, 1,  4);
		DungeonHooks.addDungeonLoot(new ItemStack(Item.emerald),          10, 1,  4);
		DungeonHooks.addDungeonLoot(new ItemStack(Item.diamond),          10, 1,  2);
		
		this.addRecipeNewMaterialTools();
		this.addRecipeUsedOreDictForNewTools();
		this.addRecipeUsedOreDict();
		this.addRecipeOthers();
		this.addLocalization();
	}
	
	private void addLocalization()
	{
		
		TranslationRegistry.addLocalization(blockRedstone, "Block of Redstone", "レッドストーンブロック");
		TranslationRegistry.addLocalization(blockMithril,  "Block of Mithril",  "ミスリルブロック");
		TranslationRegistry.addLocalization(blockEbony,    "Block of Ebony",    "エボニーブロック");
		
		TranslationRegistry.addLocalization(blockInfernalFurnace, "Infernal Furnace", "地獄かまど");
		
		TranslationRegistry.addLocalization(new ItemStack(blockDwarvenOre, 1, 0),  "Mithril Ore",   "ミスリル鉱石");
		TranslationRegistry.addLocalization(new ItemStack(blockDwarvenOre, 1, 1),  "Ebony Ore",     "エボニー鉱石");
		
		TranslationRegistry.addLocalization(itemMithrilIngot, "Mithril Ingot", "ミスリルインゴット");
		TranslationRegistry.addLocalization(itemEbonyIngot,   "Ebony Ingot",   "エボニーインゴット");
		TranslationRegistry.addLocalization(itemIronBar,      "Iron Bar",      "鉄の棒");
		
		TranslationRegistry.addLocalization(itemDwarvenBrokenSwordMithril, "Broken Mithril Sword", "折れたミスリル直剣");
		TranslationRegistry.addLocalization(itemDwarvenBrokenSwordEbony,   "Broken Ebony Sword", "折れたエボニー直剣");
		
		TranslationRegistry.addLocalization(itemDwarvenShovelMithril,    "Dwarven Mithril Shovel",    "ドワーフのミスリルショベル");
		TranslationRegistry.addLocalization(itemDwarvenPickaxeMithril,   "Dwarven Mithril Pickaxe",   "ドワーフのミスリルつるはし");
		TranslationRegistry.addLocalization(itemDwarvenBattleaxeMithril, "Dwarven Mithril Battleaxe", "ドワーフのミスリルバトルアックス");
		TranslationRegistry.addLocalization(itemDwarvenSwordMithril,     "Dwarven Mithril Sword",     "ドワーフのミスリルソード");
		
		TranslationRegistry.addLocalization(itemDwarvenShovelEbony,    "Dwarven Ebony Shovel",    "ドワーフのエボニーショベル");
		TranslationRegistry.addLocalization(itemDwarvenPickaxeEbony,   "Dwarven Ebony Pickaxe",   "ドワーフのエボニーつるはし");
		TranslationRegistry.addLocalization(itemDwarvenBattleaxeEbony, "Dwarven Ebony Battleaxe", "ドワーフのエボニーバトルアックス");
		TranslationRegistry.addLocalization(itemDwarvenSwordEbony,     "Dwarven Ebony Sword",     "ドワーフのエボニーソード");
		
		TranslationRegistry.addLocalization(itemDwarvenHelmetMithril, "Mithril Helmet",  "ミスリルヘルメット");
		TranslationRegistry.addLocalization(itemDwarvenPlateMithril,  "Mithril Plate",   "ミスリルチェストプレート");
		TranslationRegistry.addLocalization(itemDwarvenLegsMithril,   "Mithril Leggins", "ミスリルレギンス");
		TranslationRegistry.addLocalization(itemDwarvenBootMithril,   "Mithril Boots",   "ミスリルブーツ");
		
		TranslationRegistry.addLocalization(itemDwarvenHelmetEbony, "Ebony Helmet",  "エボニーヘルメット");
		TranslationRegistry.addLocalization(itemDwarvenPlateEbony,  "Ebony Plate",   "エボニーチェストプレート");
		TranslationRegistry.addLocalization(itemDwarvenLegsEbony,   "Ebony Leggins", "エボニーレギンス");
		TranslationRegistry.addLocalization(itemDwarvenBootEbony,   "Ebony Boots",   "エボニーブーツ");
		
		TranslationRegistry.addLocalization(itemDwarvenShovelObsidian,  "Obsidian Shovel",  "黒曜石のショベル");
		TranslationRegistry.addLocalization(itemDwarvenPickaxeObsidian, "Obsidian Pickaxe", "黒曜石のつるはし");
		TranslationRegistry.addLocalization(itemDwarvenAxeObsidian,     "Obsidian Axe",     "黒曜石の斧");
		TranslationRegistry.addLocalization(itemDwarvenHoeObsidian,     "Obsidian Hoe",     "黒曜石のクワ");
		TranslationRegistry.addLocalization(itemDwarvenSwordObsidian,   "Obsidian Sword",   "黒曜石の剣");
		
		TranslationRegistry.addLocalization(itemDwarvenShovelLapislazuli,  "Lapislazuli Shovel",  "ラピスラズリのショベル");
		TranslationRegistry.addLocalization(itemDwarvenPickaxeLapislazuli, "Lapislazuli Pickaxe", "ラピスラズリのつるはし");
		TranslationRegistry.addLocalization(itemDwarvenAxeLapislazuli,     "Lapislazuli Axe",     "ラピスラズリの斧");
		TranslationRegistry.addLocalization(itemDwarvenHoeLapislazuli,     "Lapislazuli Hoe",     "ラピスラズリのクワ");
		TranslationRegistry.addLocalization(itemDwarvenSwordLapislazuli,   "Lapislazuli Sword",   "ラピスラズリの剣");
		
		TranslationRegistry.addLocalization(itemDwarvenShovelRedstone,  "Redstone Shovel",  "レッドストーンのショベル");
		TranslationRegistry.addLocalization(itemDwarvenPickaxeRedstone, "Redstone Pickaxe", "レッドストーンのつるはし");
		TranslationRegistry.addLocalization(itemDwarvenAxeRedstone,     "Redstone Axe",     "レッドストーンの斧");
		TranslationRegistry.addLocalization(itemDwarvenHoeRedstone,     "Redstone Hoe",     "レッドストーンのクワ");
		TranslationRegistry.addLocalization(itemDwarvenSwordRedstone,   "Redstone Sword",   "レッドストーンの剣");
		
		
	}
	
	private void addRecipeUsedOreDict()
	{
		Item[]   unstorageItemList = new Item[] {itemMithrilIngot, itemEbonyIngot};
		String[] unstorageNameList = new String[] {"ingotMithril", "ingotEbony"};
		Block[]        storageList = new Block[] {blockMithril, blockEbony};
		
		
		for (int i = 0; i < storageList.length; i++)
		{
			
			GameRegistry.addShapelessRecipe(
				new ItemStack(unstorageItemList[i], 9),
					new Object[]
					{
						storageList[i]
					 });
			
			GameRegistry.addRecipe(
				new ShapedOreRecipe(new ItemStack(storageList[i], 1),
					new Object[]
						{
							"XXX", "XXX", "XXX",
							Character.valueOf('X'), unstorageNameList[i]
						}));
			
		}
		
		GameRegistry.addRecipe(
			new ItemStack(itemIronBar, 4),
				new Object[]
				{
					"I","B",
					Character.valueOf('I'), Item.ingotIron,
					Character.valueOf('B'), Item.blazeRod
				});
	}
	
	private void addRecipeOthers()
	{
		GameRegistry.addRecipe(
			new ItemStack(Block.netherBrick, 1),
				new Object[]
				{
					"NN","NN",
					Character.valueOf('N'), Block.netherrack
				});
		
		GameRegistry.addRecipe(
			new ItemStack(blockInfernalFurnace, 1),
				new Object[]
				{
					"NNN", "N N", "NNN",
					Character.valueOf('N'), Block.netherBrick
				});
		
		GameRegistry.addRecipe(
			new ItemStack(blockRedstone, 1),
				new Object[]
				{
					"XXX", "XXX", "XXX",
					Character.valueOf('X'), Item.redstone
				});
		
		GameRegistry.addShapelessRecipe(
			new ItemStack(Item.redstone, 9),
				 new Object[]
				 {
						blockRedstone
				 });
	}
	
	private void addRecipeNewMaterialTools()
	{
		final Item[] shovelList  = new Item[] {this.itemDwarvenShovelObsidian,  this.itemDwarvenShovelLapislazuli,  this.itemDwarvenShovelRedstone};
		final Item[] pickaxeList = new Item[] {this.itemDwarvenPickaxeObsidian, this.itemDwarvenPickaxeLapislazuli, this.itemDwarvenPickaxeRedstone};
		final Item[] axeList     = new Item[] {this.itemDwarvenAxeObsidian,     this.itemDwarvenAxeLapislazuli,     this.itemDwarvenAxeRedstone};
		final Item[] hoeList     = new Item[] {this.itemDwarvenHoeObsidian,     this.itemDwarvenHoeLapislazuli,     this.itemDwarvenHoeRedstone};
		final Item[] swordList   = new Item[] {this.itemDwarvenSwordObsidian,   this.itemDwarvenSwordLapislazuli,   this.itemDwarvenSwordRedstone};
		
		final String[] shovelRecipe  = new String[] {  "M",   "S",   "S"};
		final String[] pickaxeRecipe = new String[] {"MMM", " S ", " S "};
		final String[] axeRecipe     = new String[] { "MM",  "MS",  " S"};
		final String[] hoeRecipe     = new String[] { "MM",  " S",  " S"};
		final String[] swordRecipe   = new String[] {  "M",   "M",   "S"};
		
		Block[] materialList = new Block[] {Block.obsidian, Block.blockLapis, blockRedstone};
		
		List itemList = new ArrayList();
		itemList.add(shovelList);
		itemList.add(pickaxeList);
		itemList.add(axeList);
		itemList.add(hoeList);
		itemList.add(swordList);
		
		List recipeList = new ArrayList();
		recipeList.add(shovelRecipe);
		recipeList.add(pickaxeRecipe);
		recipeList.add(axeRecipe);
		recipeList.add(hoeRecipe);
		recipeList.add(swordRecipe);
		
		for (int i = 0; i < itemList.size(); i++)
		{
			for (int j = 0; j < materialList.length; j++ )
			{
				GameRegistry.addRecipe(
					new ItemStack(((Item[])itemList.get(i))[j], 1),
						new Object[]
						{
							((String[])recipeList.get(i))[0], ((String[])recipeList.get(i))[1], ((String[])recipeList.get(i))[2],
							Character.valueOf('M'), materialList[j],
							Character.valueOf('S'), Item.stick
						});
			}
		}
	}
	
	private void addRecipeUsedOreDictForNewTools()
	{
		final Item[] shovelList    = new Item[] {this.itemDwarvenShovelMithril,    this.itemDwarvenShovelEbony};
		final Item[] pickaxeList   = new Item[] {this.itemDwarvenPickaxeMithril,   this.itemDwarvenPickaxeEbony};
		final Item[] battleaxeList = new Item[] {this.itemDwarvenBattleaxeMithril, this.itemDwarvenBattleaxeEbony};
		
		final Item[] swordList     = new Item[] {this.itemDwarvenSwordMithril,     this.itemDwarvenSwordEbony};
		
		final Item[] helmetList    = new Item[] {this.itemDwarvenHelmetMithril,    this.itemDwarvenHelmetEbony};
		final Item[] plateList     = new Item[] {this.itemDwarvenPlateMithril,     this.itemDwarvenPlateEbony};
		final Item[] legsList      = new Item[] {this.itemDwarvenLegsMithril,      this.itemDwarvenLegsEbony};
		final Item[] bootList      = new Item[] {this.itemDwarvenBootMithril,      this.itemDwarvenBootEbony};
		
		final String[] shovelRecipe    = new String[] {  "M",   "S",   "S"};
		final String[] pickaxeRecipe   = new String[] {"MMM", " S ", " S "};
		final String[] battleaxeRecipe = new String[] {"MMM", "MSM", " S "};
	
		final String[] helmetRecipe = new String[] {"MMM", "M M"};
		final String[] plateRecipe  = new String[] {"M M", "MMM", "MMM"};
		final String[] legsRecipe   = new String[] {"MMM", "M M", "M M"};
		final String[] bootRecipe   = new String[] {"M M", "M M"};
		
		List itemList = new ArrayList();
		itemList.add(shovelList);
		itemList.add(pickaxeList);
		itemList.add(battleaxeList);
			
		List recipeList = new ArrayList();
		recipeList.add(shovelRecipe);
		recipeList.add(pickaxeRecipe);
		recipeList.add(battleaxeRecipe);
		
		//Item[] materialList = {itemMithrilIngot, itemEbonyIngot};
		String[] materialList = {"ingotMithril", "ingotEbony"};
		
		for (int i = 0; i < itemList.size(); i++)
		{
			for (int j = 0; j < materialList.length; j++ )
			{
				GameRegistry.addRecipe(
					new ShapedOreRecipe(
						new ItemStack(((Item[])itemList.get(i))[j], 1),
							new Object[]
							{
								((String[])recipeList.get(i))[0], ((String[])recipeList.get(i))[1], ((String[])recipeList.get(i))[2],
								Character.valueOf('M'), materialList[j],
								Character.valueOf('S'), itemIronBar
							}));
			}
		}
		
		Item[] brokenSword = new Item[] {itemDwarvenBrokenSwordMithril, itemDwarvenBrokenSwordEbony};
		
		for (int i = 0; i < materialList.length; i++)
		{
			GameRegistry.addRecipe(
				new ShapedOreRecipe(
					new ItemStack(swordList[i], 1),
						new Object[]
						{
							"M", "M", "B",
							Character.valueOf('M'), materialList[i],
							Character.valueOf('B'), brokenSword[i]
						}));
		}
		
		List helmetBootList = new ArrayList();
		helmetBootList.add(helmetList);
		helmetBootList.add(bootList);
		
		List plateLegsList = new ArrayList();
		plateLegsList.add(plateList);
		plateLegsList.add(legsList);
		
		List helmetBootRecipeList = new ArrayList();
		helmetBootRecipeList.add(helmetRecipe);
		helmetBootRecipeList.add(bootRecipe);
		
		List plateLegsRecipeList = new ArrayList();
		plateLegsRecipeList.add(plateRecipe);
		plateLegsRecipeList.add(legsRecipe);
		
		for (int i = 0; i < 2; i++)
		{
			for (int j = 0; j < materialList.length; j++)
			{
				GameRegistry.addRecipe(
					new ShapedOreRecipe(
						new ItemStack(((Item[])helmetBootList.get(i))[j], 1),
							new Object[]
							{
								((String[])helmetBootRecipeList.get(i))[0], ((String[])helmetBootRecipeList.get(i))[1],
								Character.valueOf('M'), materialList[j],
							}));
				
				GameRegistry.addRecipe(
					new ShapedOreRecipe(
						new ItemStack(((Item[])plateLegsList.get(i))[j], 1),
							new Object[]
							{
								((String[])plateLegsRecipeList.get(i))[0], ((String[])plateLegsRecipeList.get(i))[1], ((String[])plateLegsRecipeList.get(i))[2],
								Character.valueOf('M'), materialList[j],
							}));
			}
		}
		
	}
	
}
