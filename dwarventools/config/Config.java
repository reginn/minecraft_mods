package rgn.mods.dwarventools.config;

import java.io.File;
import java.util.logging.Level;

import net.minecraft.creativetab.CreativeTabs;

import net.minecraftforge.common.Configuration;

import cpw.mods.fml.common.FMLLog;

import rgn.mods.dwarventools.creativetab.CreativeTabDwarvenTools;

public class Config
{
	public int blockIdBlockDwarvenOre;
	public int blockIdBlockDwarvenOreStorage;
	public int blockIdInfernalFurnace;

	public int blockIdQuestBoard;

	public int itemIdDwarvenShovelObsidian;
	public int itemIdDwarvenPickaxeObsidian;
	public int itemIdDwarvenAxeObsidian;
	public int itemIdDwarvenHoeObsidian;
	public int itemIdDwarvenSwordObsidian;

	public int itemIdDwarvenShovelLapislazuli;
	public int itemIdDwarvenPickaxeLapislazuli;
	public int itemIdDwarvenAxeLapislazuli;
	public int itemIdDwarvenHoeLapislazuli;
	public int itemIdDwarvenSwordLapislazuli;

	public int itemIdDwarvenShovelRedstone;
	public int itemIdDwarvenPickaxeRedstone;
	public int itemIdDwarvenAxeRedstone;
	public int itemIdDwarvenHoeRedstone;
	public int itemIdDwarvenSwordRedstone;

	public int itemIdDwarvenShovelMithril;
	public int itemIdDwarvenPickaxeMithril;
	public int itemIdDwarvenBattleaxeMithril;
	public int itemIdDwarvenBrokenSwordMithril;
	public int itemIdDwarvenSwordMithril;

	public int itemIdDwarvenShovelEbony;
	public int itemIdDwarvenPickaxeEbony;
	public int itemIdDwarvenBattleaxeEbony;
	public int itemIdDwarvenBrokenSwordEbony;
	public int itemIdDwarvenSwordEbony;

	public int itemIdIronBar;
	public int itemIdMithrilIngot;
	public int itemIdEbonyIngot;

	public int itemIdDwarvenHelmetMithril;
	public int itemIdDwarvenPlateMithril;
	public int itemIdDwarvenBootMithril;
	public int itemIdDwarvenLegsMithril;

	public int itemIdDwarvenHelmetEbony;
	public int itemIdDwarvenPlateEbony;
	public int itemIdDwarvenBootEbony;
	public int itemIdDwarvenLegsEbony;

	public int enchantmentIdExecutioner;
	public int enchantmentIdCriticalStrike;
	public int enchantmentIdVitalize;

	public static int guiIdInfernalFurnace = 0;
	public static final CreativeTabs tabDwarvenTools = new CreativeTabDwarvenTools("dwarventools");

	public void buildConfiguration(File cfgFile)
	{
		Configuration cfg = new Configuration(cfgFile);
		try
		{
			cfg.load();

			blockIdBlockDwarvenOreStorage = cfg.getBlock("StrageBlock",        1500).getInt();
			blockIdBlockDwarvenOre        = cfg.getBlock("Ore",                1501).getInt();
			blockIdInfernalFurnace        = cfg.getBlock("InfernalFurnace",    1505).getInt();

			itemIdIronBar      = cfg.getItem("IronBar",      10500).getInt();
			itemIdMithrilIngot = cfg.getItem("MithrilIngot", 10501).getInt();
			itemIdEbonyIngot   = cfg.getItem("EbonyIngot",   10502).getInt();

			itemIdDwarvenShovelObsidian  = cfg.getItem("DwarvenObsidianShovel",  11000).getInt();
			itemIdDwarvenPickaxeObsidian = cfg.getItem("DwarvenObsidianPickaxe", 11001).getInt();
			itemIdDwarvenAxeObsidian     = cfg.getItem("DwarvenObsidianAxe",     11002).getInt();
			itemIdDwarvenHoeObsidian     = cfg.getItem("DwarvenObsidianHoe",     11003).getInt();
			itemIdDwarvenSwordObsidian   = cfg.getItem("DwarvenObsidianSword",   11004).getInt();

			itemIdDwarvenShovelLapislazuli  = cfg.getItem("DwarvenLapislazuliShovel",  11005).getInt();
			itemIdDwarvenPickaxeLapislazuli = cfg.getItem("DwarvenLapislazuliPickaxe", 11006).getInt();
			itemIdDwarvenAxeLapislazuli     = cfg.getItem("DwarvenLapislazuliAxe",     11007).getInt();
			itemIdDwarvenHoeLapislazuli     = cfg.getItem("DwarvenLapislazuliHoe",     11008).getInt();
			itemIdDwarvenSwordLapislazuli   = cfg.getItem("DwarvenLapislazuliSword",   11009).getInt();

			itemIdDwarvenShovelRedstone  = cfg.getItem("DwarvenRedstoneShovel",  11010).getInt();
			itemIdDwarvenPickaxeRedstone = cfg.getItem("DwarvenRedstonePickaxe", 11011).getInt();
			itemIdDwarvenAxeRedstone     = cfg.getItem("DwarvenRedstoneAxe",     11012).getInt();
			itemIdDwarvenHoeRedstone     = cfg.getItem("DwarvenRedstoneHoe",     11013).getInt();
			itemIdDwarvenSwordRedstone   = cfg.getItem("DwarvenRedstoneSword",   11014).getInt();

			itemIdDwarvenShovelMithril      = cfg.getItem("DwarvenMithrilShovel",      11015).getInt();
			itemIdDwarvenPickaxeMithril     = cfg.getItem("DwarvenMithrilPickaxe",     11016).getInt();
			itemIdDwarvenBattleaxeMithril   = cfg.getItem("DwarvenMithrilBattleaxe",   11017).getInt();
			itemIdDwarvenBrokenSwordMithril = cfg.getItem("DwarvenMithrilBrokenSword", 11018).getInt();
			itemIdDwarvenSwordMithril       = cfg.getItem("DwarvenMithrilSword",       11019).getInt();

			itemIdDwarvenShovelEbony      = cfg.getItem("DwarvenEbonyShovel",      11020).getInt();
			itemIdDwarvenPickaxeEbony     = cfg.getItem("DwarvenEbonyPickaxe",     11021).getInt();
			itemIdDwarvenBattleaxeEbony   = cfg.getItem("DwarvenEbonyBattleaxe",   11022).getInt();
			itemIdDwarvenBrokenSwordEbony = cfg.getItem("DwarvenEbonyBrokenSword", 11023).getInt();
			itemIdDwarvenSwordEbony       = cfg.getItem("DwarvenEbonySword",       11024).getInt();

			itemIdDwarvenHelmetMithril = cfg.getItem("DwarvenMithrilHelmet", 11025).getInt();
			itemIdDwarvenPlateMithril  = cfg.getItem("DwarvenMithrilPlate",  11026).getInt();
			itemIdDwarvenBootMithril   = cfg.getItem("DwarvenMithrilBoot",   11027).getInt();
			itemIdDwarvenLegsMithril   = cfg.getItem("DwarvenMithrilLegs",   11028).getInt();

			itemIdDwarvenHelmetEbony = cfg.getItem("DwarvenEbonyHelmet", 11029).getInt();
			itemIdDwarvenPlateEbony  = cfg.getItem("DwarvenEbonyPlate",  11030).getInt();
			itemIdDwarvenBootEbony   = cfg.getItem("DwarvenEbonyBoot",   11031).getInt();
			itemIdDwarvenLegsEbony   = cfg.getItem("DwarvenEbonyLegs",   11032).getInt();

			enchantmentIdVitalize       = cfg.get("EnchantmentVitalize",       Configuration.CATEGORY_GENERAL, 10).getInt();
			enchantmentIdExecutioner    = cfg.get("EnchantmentExecutionerID",  Configuration.CATEGORY_GENERAL, 26).getInt();
			enchantmentIdCriticalStrike = cfg.get("EnchantmentCriticalStrike", Configuration.CATEGORY_GENERAL, 27).getInt();
		}
		catch (Exception e)
		{
			FMLLog.log(Level.SEVERE, e, "Exception occured! in DwarvenTools's configuration");
		}
		finally
		{
			cfg.save();
		}
	}
}