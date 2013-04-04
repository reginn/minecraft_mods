package rgn.mods.rum;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

import rgn.mods.rum.block.RumBlock;
import rgn.mods.rum.config.Config;
import rgn.mods.rum.core.CommonProxy;
import rgn.mods.rum.core.LocalizationRegistry;
import rgn.mods.rum.generate.ForgeChestHooks;
import rgn.mods.rum.generate.RumWorldGeneretor;
import rgn.mods.rum.item.RumItem;

@Mod
(
	modid   = "Rum",
	name    = "Reginn's Unclassified Mod",
	version = "0.0.1"
)
@NetworkMod
(
	clientSideRequired = true,
	serverSideRequired = false
)
public class RumCore
{
	@Mod.Instance("Rum")
	public static RumCore instance;

	@SidedProxy(clientSide = "rgn.mods.rum.client.ClientProxy", serverSide = "rgn.mods.rum.core.CommonProxy")
	public static CommonProxy proxy;

	public Config config;

	@Mod.PreInit
	public void preInit(FMLPreInitializationEvent event)
	{
		config = new Config();
		config.buildConfiguration(event.getSuggestedConfigurationFile());
	}

	@Mod.Init
	public void init(FMLInitializationEvent event)
	{
		RumBlock.configure(config);
		RumItem.configure(config);

		proxy.registerRenderer();
		NetworkRegistry.instance().registerGuiHandler(this, proxy);
		(new LocalizationRegistry()).addLocalization();
		(new ForgeChestHooks()).addLoot();
		GameRegistry.registerWorldGenerator(new RumWorldGeneretor());
	}
}
