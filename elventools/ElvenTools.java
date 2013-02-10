package rgn.mods.elventools;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;

import net.minecraftforge.common.MinecraftForge;

import rgn.mods.elventools.config.Config;
import rgn.mods.elventools.config.ConfigureBlock;
import rgn.mods.elventools.config.ConfigureEntity;
import rgn.mods.elventools.config.ConfigureItem;
import rgn.mods.elventools.config.ConfigureOreDict;
import rgn.mods.elventools.core.CommonProxy;
import rgn.mods.elventools.core.FuelHandler;
import rgn.mods.elventools.core.LocalizationRegistry;
import rgn.mods.elventools.core.RecipeRegistry;
import rgn.mods.elventools.generate.ElvenWorldGenerator;
import rgn.mods.elventools.event.ForgeEventRegistry;
import rgn.mods.elventools.network.PacketHandler;

@Mod
(
	modid   = "ElvenTools",
	name    = "ElvenTools",
	version = "1.1.1dev"
)
@NetworkMod
(
	clientSideRequired = true,
	serverSideRequired = false,
	channels = {"ElvenTools", "bind"},
	packetHandler = PacketHandler.class
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
		
		(new ForgeEventRegistry()).registerEvent();

		proxy.registerTextures();
		proxy.registerRenderers();

		(new RecipeRegistry()).addRecipe();
		(new LocalizationRegistry()).addLocalization();

	}


}
