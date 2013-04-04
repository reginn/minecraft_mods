package rgn.mods.elventools.config;

import java.io.File;
import java.util.logging.Level;

import net.minecraft.creativetab.CreativeTabs;

import net.minecraftforge.common.Configuration;

import cpw.mods.fml.common.FMLLog;

import rgn.mods.elventools.creativetab.CreativeTabElvenTools;

public class Config
{
	public int blockIdEbonyLog;
	public int blockIdEbonyLeaves;
	public int blockIdEbonySapling;
	public int blockIdEbonyWood;

	public int blockIdRope;
	public int blockIdRopeEstablisher;

	public int blockIdEbonyLadder;


	public int itemIdLeatherLongbow;
	public int itemIdCompositeLongbow;
	public int itemIdEnhancedLongbow;
	public int itemIdBoneCompositeBow;
	public int itemIdShadowBow;
	public int itemIdEndBow;
	public int itemIdFeatherBow;
	public int itemIdSteelBow;
	public int itemIdElvenBow;

	public int itemIdEbonyStick;

	public int itemIdEbonyBoat;

	public int itemIdTorchArrow;
	public int itemIdRopeArrow;

	public int itemIdRopeEstablisher;

	public int itemIdElvenShovelMithril;
	public int itemIdElvenPickaxeMithril;
	public int itemIdElvenAxeMithril;
	public int itemIdElvenSwordMithril;

	public int itemIdElvenSickle;
	public int itemIdElvenLumberAxe;

	public int itemIdElvenSeedBag;

	public static final int guiIdSeedBag = 0;

	public static final CreativeTabs tabElvenTools = new CreativeTabElvenTools("elventools");

	public void buildConfiguration(File cfgFile)
	{
		Configuration cfg = new Configuration(cfgFile);
		try
		{
			cfg.load();

			blockIdEbonyLog      = cfg.getBlock("EbonyLog",     1650).getInt();
			blockIdEbonyLeaves   = cfg.getBlock("EbonyLeaves",  1651).getInt();
			blockIdEbonySapling  = cfg.getBlock("EbonySapling", 1652).getInt();
			blockIdEbonyWood     = cfg.getBlock("EbonyWood",    1653).getInt();

			blockIdRope            = cfg.getBlock("Rope",            1654).getInt();
			blockIdRopeEstablisher = cfg.getBlock("RopeEstablisher", 1655).getInt();

			blockIdEbonyLadder = cfg.getBlock("EbonyLadder", 1656).getInt();

			itemIdLeatherLongbow   = cfg.getItem("LetherLongbow",    21001).getInt();
			itemIdCompositeLongbow = cfg.getItem("CompositeLongbow", 21002).getInt();
			itemIdEnhancedLongbow  = cfg.getItem("EnhancedLongbow",  21003).getInt();
			itemIdBoneCompositeBow = cfg.getItem("BoneCompositeBow", 21004).getInt();
			itemIdShadowBow        = cfg.getItem("ShadowBow",        21005).getInt();
			itemIdEndBow           = cfg.getItem("EndBow",           21006).getInt();
			itemIdFeatherBow       = cfg.getItem("FeatherBow",       21007).getInt();
			itemIdSteelBow         = cfg.getItem("SteelBow",         21008).getInt();
			itemIdElvenBow         = cfg.getItem("ElvenBow",         21009).getInt();

			itemIdEbonyStick = cfg.getItem("EbonyStick", 23004).getInt();

			itemIdEbonyBoat = cfg.getItem("EbonyBoat", 23005).getInt();

			itemIdTorchArrow = cfg.getItem("TorchArrow", 23006).getInt();
			itemIdRopeArrow  = cfg.getItem("RopeArrow",  23007).getInt();

			itemIdRopeEstablisher = cfg.getItem("RopeEstablisher", 23008).getInt();

			itemIdElvenShovelMithril  = cfg.getItem("MithrilShovel",  23009).getInt();
			itemIdElvenPickaxeMithril = cfg.getItem("MithrilPickaxe", 23010).getInt();
			itemIdElvenAxeMithril     = cfg.getItem("MithrilAxe",     23011).getInt();
			itemIdElvenSwordMithril   = cfg.getItem("MithrilSrowd",   23012).getInt();

			itemIdElvenSickle    = cfg.getItem("Sickle",    23017).getInt();
			itemIdElvenLumberAxe = cfg.getItem("LumberAxe", 23018).getInt();

			itemIdElvenSeedBag = cfg.getItem("Seedbag", 23019).getInt();
		}
		catch (Exception e)
		{
			FMLLog.log(Level.SEVERE, e, "Exception occured in ElvenTools.Config");
		}
		finally
		{
			cfg.save();
		}
	}
}