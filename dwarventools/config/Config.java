package rgn.mods.dwarventools.config;

import java.io.File;
import java.util.logging.Level;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.Configuration;
import rgn.mods.dwarventools.creativetab.CreativeTabDwarvenTools;
import cpw.mods.fml.common.FMLLog;

public class Config
{
	public static int blockIdBlockDwarvenOre;
	public static int blockIdBlockDwarvenOreStorage;
	public static int blockIdInfernalFurnace;

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
	
	public static int enchantmentIdExecutioner;
	public static int enchantmentIdCriticalStrike;
	public static int enchantmentIdVitalize;

	public static final CreativeTabs tabDwarvenTools = new CreativeTabDwarvenTools("dwarventools");

	public static void buildConfiguration(File cfgFile)
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