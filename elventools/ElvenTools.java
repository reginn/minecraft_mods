package rgn.mods.elventools;

import net.minecraftforge.common.MinecraftForge;
import rgn.mods.elventools.config.Config;
import rgn.mods.elventools.config.ConfigureBlock;
import rgn.mods.elventools.config.ConfigureEntity;
import rgn.mods.elventools.config.ConfigureItem;
import rgn.mods.elventools.config.ConfigureOreDict;
import rgn.mods.elventools.core.CommonProxy;
import rgn.mods.elventools.core.ForgeEventHooks;
import rgn.mods.elventools.core.FuelHandler;
import rgn.mods.elventools.core.LocalizationRegistry;
import rgn.mods.elventools.core.RecipeRegistry;
import rgn.mods.elventools.generate.ElvenWorldGenerator;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;


@Mod
(
	modid   = "ElvenTools",
	name    = "ElvenTools",
	version = "3.2.3"
)
@NetworkMod
(
	clientSideRequired = true,
	serverSideRequired = false
)
public class ElvenTools
{
	@SidedProxy(clientSide = "rgn.mods.elventools.client.ClientProxy", serverSide = "rgn.mods.elventools.core.CommonProxy")
	public static CommonProxy proxy;

	@Mod.Instance("ElvenTools")
	public static ElvenTools instance;


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
		ConfigureEntity.init();
		ConfigureOreDict.init();

		GameRegistry.registerFuelHandler(new FuelHandler());
		GameRegistry.registerWorldGenerator(new ElvenWorldGenerator());
		MinecraftForge.EVENT_BUS.register(new ForgeEventHooks());

		proxy.registerTextures();
		proxy.registerRenderers();

		(new RecipeRegistry()).addRecipe();
		(new LocalizationRegistry()).addLocalization();

	}


}
