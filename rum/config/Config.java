package rgn.mods.rum.config;

import java.io.File;
import java.util.logging.Level;

import net.minecraft.creativetab.CreativeTabs;

import net.minecraftforge.common.Configuration;

import cpw.mods.fml.common.FMLLog;

import rgn.mods.rum.creativetab.CreativeTabRum;

public class Config
{
	public int blockIdLockedChest;

	public int itemIdKey;

	public static CreativeTabs tabRum = new CreativeTabRum();

	public void buildConfiguration(File cfgFile)
	{
		Configuration cfg = new Configuration(cfgFile);
		try
		{
			cfg.load();
			this.blockIdLockedChest = cfg.getBlock("LockedChest", 3010).getInt();

			this.itemIdKey = cfg.getItem("Key", 24000).getInt();

		}
		catch (Exception e)
		{
			FMLLog.log(Level.SEVERE, e, "Exception Occured in Rum.Configuration");
		}
		finally
		{
			cfg.save();
		}
	}
}
