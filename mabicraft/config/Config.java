package rgn.mods.mabicraft.config;

import java.io.File;
import java.util.logging.Level;

import net.minecraft.creativetab.CreativeTabs;

import net.minecraftforge.common.Configuration;

import cpw.mods.fml.common.FMLLog;

import rgn.mods.mabicraft.MabiCraft;
import rgn.mods.mabicraft.creativetab.CreativeTabMabiCraft;

public class Config
{
	public int blockIdBonfire;
	public int blockIdEnchanter;
	public int blockIdCookware;

	public int itemIdEnchantScroll;

	public int itemIdBaseHerb;
	public int itemIdManaHerb;
	public int itemIdBloodyHerb;
	public int itemIdSunlightHerb;
	public int itemIdPoisonHerb;
	public int itemIdIvoryHerb;

	public int itemIdMagicPowder;
	public int itemIdBlessedPotion;
	public int itemIdBonfireKit;

	public int itemIdEvilScroll;

	public int itemIdCookingFood;
	public int itemIdCookingPot;

	public int itemIdMabiFishRod;
	public int itemIdQuestBoard;

	public int startVillagerID;

	public static final int RENDER_TYPE_BONFIRE = MabiCraft.proxy.getUniqueRenderType();
	public static final int RENDER_TYPE_COOKWARE = MabiCraft.proxy.getUniqueRenderType();

	public static CreativeTabs tabMabiCraft = new CreativeTabMabiCraft("mabicraft");

	public void buildConfiguration(File cfgFile)
	{
		Configuration cfg = new Configuration(cfgFile);
		try
		{
			cfg.load();

			this.blockIdBonfire    = cfg.getBlock("Bonfire",   1900).getInt();
			this.blockIdEnchanter  = cfg.getBlock("Enchanter", 1901).getInt();
			this.blockIdCookware   = cfg.getBlock("Cookware",  1902).getInt();

			this.itemIdEnchantScroll = cfg.getItem("EnchantScroll", 13007).getInt();

			this.itemIdBaseHerb     = cfg.getItem("BaseHerb",     13000).getInt();
			this.itemIdManaHerb     = cfg.getItem("ManaHerb",     13001).getInt();
			this.itemIdBloodyHerb   = cfg.getItem("BloodyHerb",   13002).getInt();
			this.itemIdSunlightHerb = cfg.getItem("SunlightHerb", 13003).getInt();
			this.itemIdPoisonHerb   = cfg.getItem("PoisonHerb",   13004).getInt();
			this.itemIdIvoryHerb    = cfg.getItem("IvoryHerb",    13005).getInt();

			this.itemIdMagicPowder   = cfg.getItem("MagicPowder",   13006).getInt();
			this.itemIdBlessedPotion = cfg.getItem("BlessedPotion", 13008).getInt();
			this.itemIdBonfireKit    = cfg.getItem("BonfireKit",    13009).getInt();
			this.itemIdEvilScroll    = cfg.getItem("EvilScroll",    13010).getInt();

			this.itemIdCookingFood   = cfg.getItem("CookingFood",   13011).getInt();
			this.itemIdCookingPot    = cfg.getItem("CookingPot",    13012).getInt();
			this.itemIdMabiFishRod   = cfg.getItem("MabiFishRod",   13013).getInt();

			this.startVillagerID = cfg.get(Configuration.CATEGORY_GENERAL, "StartVillagerID", 100).getInt();
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