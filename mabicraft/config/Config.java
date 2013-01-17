package rgn.mods.mabicraft.config;

import java.io.File;
import java.util.logging.Level;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.Configuration;
import rgn.mods.mabicraft.MabiCraft;
import rgn.mods.mabicraft.core.CreativeTabMabiCraft;
import cpw.mods.fml.common.FMLLog;

public class Config
{
	public static int blockIdBonfire;
	public static int blockIdEnchanter;

	public static int blockIdCookware;

	public static int itemIdEnchantScroll;

	public static int itemIdBaseHerb;
	public static int itemIdManaHerb;
	public static int itemIdBloodyHerb;
	public static int itemIdSunlightHerb;
	public static int itemIdPoisonHerb;
	public static int itemIdIvoryHerb;

	public static int itemIdMagicPowder;
	public static int itemIdBlessedPotion;
	public static int itemIdBonfireKit;

	public static int itemIdEvilScroll;

	public static int itemIdCookingFood;

	public static int startVillagerID;

	public static final int RENDER_TYPE_BONFIRE = MabiCraft.proxy.getUniqueRenderType();
	public static final int RENDER_TYPE_COOKWARE = MabiCraft.proxy.getUniqueRenderType();

	public static final CreativeTabs tabMabiCraft = new CreativeTabMabiCraft("mabicraft");

	public static void buildConfiguration(File cfgFile)
	{
		Configuration cfg = new Configuration(cfgFile);
		try
		{
			cfg.load();

			blockIdBonfire   = cfg.getBlock("Bonfire",   1900).getInt();
			blockIdEnchanter = cfg.getBlock("Enchanter", 1901).getInt();
			blockIdCookware  = cfg.getBlock("Cookware",  1902).getInt();

			itemIdEnchantScroll = cfg.getItem("EnchantScroll", 13007).getInt();

			itemIdBaseHerb     = cfg.getItem("BaseHerb",     13000).getInt();
			itemIdManaHerb     = cfg.getItem("ManaHerb",     13001).getInt();
			itemIdBloodyHerb   = cfg.getItem("BloodyHerb",   13002).getInt();
			itemIdSunlightHerb = cfg.getItem("SunlightHerb", 13003).getInt();
			itemIdPoisonHerb   = cfg.getItem("PoisonHerb",   13004).getInt();
			itemIdIvoryHerb    = cfg.getItem("IvoryHerb",    13005).getInt();

			itemIdMagicPowder   = cfg.getItem("MagicPowder",   13006).getInt();
			itemIdBlessedPotion = cfg.getItem("BlessedPotion", 13008).getInt();
			itemIdBonfireKit    = cfg.getItem("BonfireKit",    13009).getInt();
			itemIdEvilScroll    = cfg.getItem("EvilScroll",    13010).getInt();

			itemIdCookingFood   = cfg.getItem("CookingFood",   13011).getInt();

			startVillagerID = cfg.get(Configuration.CATEGORY_GENERAL, "StartVillagerID", 100).getInt();
		}
		catch (Exception e)
		{
			FMLLog.log(Level.SEVERE, e, "Exception Occured in MabiCraft.Configuration");
		}
		finally
		{
			cfg.save();
		}
	}
}