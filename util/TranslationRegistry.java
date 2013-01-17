package rgn.util;

import java.io.UnsupportedEncodingException;

import cpw.mods.fml.common.registry.LanguageRegistry;

public class TranslationRegistry
{
	public static void addLocalization(String key, String usname, String jpname)
	{
		try
		{
			byte[] var1 = jpname.getBytes("UTF-8");
			byte[] var2 = usname.getBytes("UTF-8");
			String utfJpName = new String(var1, "UTF-8");
			String utfUsName = new String(var2, "UTF-8");
			LanguageRegistry.instance().addStringLocalization(key, "ja_JP", utfJpName);
			LanguageRegistry.instance().addStringLocalization(key, "en_US", utfUsName);
		}
		catch (UnsupportedEncodingException e)
		{  
			e.printStackTrace();
		}
	}
	
	public static void addLocalization(Object object, String usname, String jpname)
	{
		try
		{
			byte[] var1 = jpname.getBytes("UTF-8");
			byte[] var2 = usname.getBytes("UTF-8");
			String utfJpName = new String(var1, "UTF-8");
			String utfUsName = new String(var2, "UTF-8");
			LanguageRegistry.instance().addNameForObject(object, "ja_JP", utfJpName);
			LanguageRegistry.instance().addNameForObject(object, "en_US", utfUsName);
		}
		catch (UnsupportedEncodingException e)
		{  
			e.printStackTrace();
		}
	}
}