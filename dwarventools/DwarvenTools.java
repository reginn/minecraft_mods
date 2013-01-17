package rgn.mods.dwarventools;

import net.minecraftforge.common.MinecraftForge;
import rgn.mods.dwarventools.config.Config;
import rgn.mods.dwarventools.config.ConfigureBlock;
import rgn.mods.dwarventools.config.ConfigureItem;
import rgn.mods.dwarventools.config.ConfigureOreDict;
import rgn.mods.dwarventools.core.CommonProxy;
import rgn.mods.dwarventools.core.DwarvenEventHooks;
import rgn.mods.dwarventools.core.LocalizationRegistry;
import rgn.mods.dwarventools.core.RecipeRegistry;
import rgn.mods.dwarventools.generate.DwarvenWorldGenerator;
import rgn.mods.dwarventools.generate.ForgeChestHooks;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod
(
	modid   = "DwarvenTools",
	name    = "DwarvenTools",
	version = "3.2.5"
)
@NetworkMod
(
	clientSideRequired = true,
	serverSideRequired = false
)
public class DwarvenTools
{
	@SidedProxy(clientSide = "rgn.mods.dwarventools.client.ClientProxy", serverSide = "rgn.mods.dwarventools.core.CommonProxy")
	public static CommonProxy proxy;

	@Mod.Instance("DwarvenTools")
	public static DwarvenTools instance;

	public static int guiIdInfernalFurnace = 0;

	@Mod.PreInit
	public void preInit(FMLPreInitializationEvent event)
	{
		Config.buildConfiguration(event.getSuggestedConfigurationFile());
	}

	@Mod.Init
	public void init(FMLInitializationEvent event)
	{
		ConfigureBlock.init();
		ConfigureItem.init();
		ConfigureOreDict.init();

		proxy.registerTextures();
		NetworkRegistry.instance().registerGuiHandler(instance, proxy);
		MinecraftForge.EVENT_BUS.register(new DwarvenEventHooks());
		GameRegistry.registerWorldGenerator(new DwarvenWorldGenerator());

		(new ForgeChestHooks()).addLoot();
		(new LocalizationRegistry()).addLocalization();
		(new RecipeRegistry()).addRecipe();
	}

}
