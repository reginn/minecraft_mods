package rgn.mods.elventools;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

import rgn.mods.elventools.block.ElvenBlock;
import rgn.mods.elventools.config.Config;
import rgn.mods.elventools.config.OreDictRegistry;
import rgn.mods.elventools.core.CommonProxy;
import rgn.mods.elventools.core.FuelHandler;
import rgn.mods.elventools.core.LocalizationRegistry;
import rgn.mods.elventools.core.RecipeRegistry;
import rgn.mods.elventools.entity.ElvenEntity;
import rgn.mods.elventools.event.ForgeEventRegistry;
import rgn.mods.elventools.generate.ElvenWorldGenerator;
import rgn.mods.elventools.item.ElvenItem;
import rgn.mods.elventools.network.PacketHandler;

@Mod
(
	modid   = "ElvenTools",
	name    = "ElvenTools",
	version = "5.0.0"
)
@NetworkMod
(
	clientSideRequired = true,
	serverSideRequired = false,
	channels = {"ElvenPatricle", "BindInfo", "SeedBag"},
	packetHandler = PacketHandler.class
)
public class ElvenTools
{
	@SidedProxy(clientSide = "rgn.mods.elventools.client.ClientProxy", serverSide = "rgn.mods.elventools.core.CommonProxy")
	public static CommonProxy proxy;

	@Mod.Instance("ElvenTools")
	public static ElvenTools instance;

	Config config = new Config();

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		config.buildConfiguration(event.getSuggestedConfigurationFile());
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{
		ElvenBlock.configure(config);
		ElvenItem.configure(config);
		ElvenEntity.configure();

		GameRegistry.registerFuelHandler(new FuelHandler());
		GameRegistry.registerWorldGenerator(new ElvenWorldGenerator());

		NetworkRegistry.instance().registerGuiHandler(this, proxy);

		(new ForgeEventRegistry()).registerEvent();

		proxy.registerRenderers();

		(new OreDictRegistry()).register();
		(new RecipeRegistry()).addRecipe();
		(new LocalizationRegistry()).addLocalization();
	}


}
