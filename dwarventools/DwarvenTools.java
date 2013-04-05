package rgn.mods.dwarventools;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

import rgn.mods.dwarventools.block.DwarvenBlock;
import rgn.mods.dwarventools.config.Config;
import rgn.mods.dwarventools.config.OreDictRegistry;
import rgn.mods.dwarventools.core.CommonProxy;
import rgn.mods.dwarventools.core.LocalizationRegistry;
import rgn.mods.dwarventools.core.RecipeRegistry;
import rgn.mods.dwarventools.enchantment.DwarvenEnchantment;
import rgn.mods.dwarventools.event.ForgeEventRegistry;
import rgn.mods.dwarventools.generate.DwarvenWorldGenerator;
import rgn.mods.dwarventools.generate.ForgeChestHooks;
import rgn.mods.dwarventools.item.DwarvenItem;
import rgn.mods.dwarventools.network.PacketHandler;

@Mod
(
	modid   = "DwarvenTools",
	name    = "DwarvenTools",
	version = "4.1.1"
)
@NetworkMod
(
	clientSideRequired = true,
	serverSideRequired = false,
	channels = {"DwarvenTools"},
	packetHandler = PacketHandler.class
)
public class DwarvenTools
{
	@SidedProxy(clientSide = "rgn.mods.dwarventools.client.ClientProxy", serverSide = "rgn.mods.dwarventools.core.CommonProxy")
	public static CommonProxy proxy;

	@Mod.Instance("DwarvenTools")
	public static DwarvenTools instance;

	Config config = new Config();

	@Mod.PreInit
	public void preInit(FMLPreInitializationEvent event)
	{
		config.buildConfiguration(event.getSuggestedConfigurationFile());
	}

	@Mod.Init
	public void init(FMLInitializationEvent event)
	{
		DwarvenBlock.configure(config);
		DwarvenItem.configure(config);
		DwarvenEnchantment.configure(config);

		(new ForgeEventRegistry()).registerEvent();

		proxy.registerTextures();
		NetworkRegistry.instance().registerGuiHandler(instance, proxy);

		GameRegistry.registerWorldGenerator(new DwarvenWorldGenerator());

		(new OreDictRegistry()).register();
		(new ForgeChestHooks()).addLoot();
		(new LocalizationRegistry()).addLocalization();
		(new RecipeRegistry()).addRecipe();
	}

}
