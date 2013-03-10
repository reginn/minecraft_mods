package rgn.mods.lamp;

import java.util.logging.Level;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;

import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod
(
	modid   = "Lamp",
	name    = "Lamp",
	version = "4.0.0pre"
)
@NetworkMod
(
	clientSideRequired = true,
	serverSideRequired = false
)
public class Lamp
{
	@SidedProxy(clientSide = "rgn.mods.lamp.client.ClientProxy", serverSide = "rgn.mods.lamp.CommonProxy")
	public static CommonProxy proxy;

	public static Block blockLamp;
	public static Block blockLight;

	public static int lampRenderType;

	private int blockIdLamp;
	private int blockIdLight;
	public static int[] gen = new int[]{16, 4, 1};
	public static int number;

	public static final CreativeTabs tabLamp = new CreativeTabLamp("lamp");

	@Mod.PreInit
	public void preInit(FMLPreInitializationEvent event)
	{
		Configuration cfg = new Configuration(event.getSuggestedConfigurationFile());
		try
		{
			cfg.load();
			blockIdLamp  = cfg.getBlock("Lamp", 1613).getInt();
			blockIdLight = cfg.getBlock("Light", 1614).getInt();
			Property prop = cfg.get(Configuration.CATEGORY_GENERAL, "number.of.lamp", 1);
			prop.comment = "crafting number of lamp, 0 is easy: 16 lamps, 1 is normal: 4 lamps, 2 is hardcore: 1 lamp";
			number = prop.getInt();
		}
		catch (Exception e)
		{
			FMLLog.log(Level.SEVERE, e, "Exception occured! in Lamp Configuration.");
		}
		finally
		{
			cfg.save();
		}
	}

	@Mod.Init
	public void init(FMLInitializationEvent event)
	{
		lampRenderType = proxy.getUniqueRenderType();

		proxy.registerRenderers();

		blockLamp  = (new BlockLamp(blockIdLamp)).setUnlocalizedName("blockLamp");
		blockLight = (new BlockLight(blockIdLight)).setUnlocalizedName("blockLight");

		GameRegistry.registerBlock(blockLamp, ItemLamp.class, "blockLamp");
		GameRegistry.registerBlock(blockLight, "blockLight");

		(new RecipeRegistry()).addRecipe();
		(new LocalizationRegistry()).addLocalization();
	}

}
