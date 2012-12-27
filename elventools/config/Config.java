package rgn.mods.elventools.config;

import java.io.File;
import java.util.logging.Level;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.Configuration;
import rgn.mods.elventools.creativetab.CreativeTabElvenTools;
import cpw.mods.fml.common.FMLLog;

public class Config
{
	public static int blockIdEbonyLog;
	public static int blockIdEbonyLeaves;
	public static int blockIdEbonySapling;
	public static int blockIdEbonyWood;

	public static int blockIdRope;
	public static int blockIdRopeEstablisher;


	public static int itemIdLeatherLongbow;
	public static int itemIdCompositeLongbow;
	public static int itemIdEnhancedLongbow;

	public static int itemIdEbonyStick;

	public static int itemIdEbonyBoat;

	public static int itemIdTorchArrow;
	public static int itemIdRopeArrow;

	public static int itemIdRopeEstablisher;

	public static int itemIdElvenShovelMithril;
	public static int itemIdElvenPickaxeMithril;
	public static int itemIdElvenAxeMithril;
	public static int itemIdElvenSwordMithril;

	public static int itemIdElvenShovelSilver;
	public static int itemIdElvenPickaxeSilver;
	public static int itemIdElvenAxeSilver;
	public static int itemIdElvenSwordSilver;

	public static int itemIdElvenSickle;
	public static int itemIdElvenLumberAxe;

	public static final CreativeTabs tabElvenTools = new CreativeTabElvenTools("elventools");

	public static void buildConfiguration(File cfgFile)
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

			itemIdLeatherLongbow   = cfg.getItem("LetherLongbow",    21001).getInt();
			itemIdCompositeLongbow = cfg.getItem("CompositeLongbow", 21002).getInt();
			itemIdEnhancedLongbow  = cfg.getItem("EnhancedLongbow",  21003).getInt();

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