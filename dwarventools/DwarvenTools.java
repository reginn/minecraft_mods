package rgn.mods.dwarventools;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

import net.minecraftforge.common.MinecraftForge;

import rgn.mods.dwarventools.config.Config;
import rgn.mods.dwarventools.config.ConfigureBlock;
import rgn.mods.dwarventools.config.ConfigureItem;
import rgn.mods.dwarventools.config.ConfigureOreDict;
import rgn.mods.dwarventools.config.ConfigureEnchantment;
import rgn.mods.dwarventools.event.ForgeEventRegistry;
import rgn.mods.dwarventools.core.CommonProxy;
import rgn.mods.dwarventools.core.LocalizationRegistry;
import rgn.mods.dwarventools.core.RecipeRegistry;
import rgn.mods.dwarventools.generate.DwarvenWorldGenerator;
import rgn.mods.dwarventools.generate.ForgeChestHooks;
import rgn.mods.dwarventools.network.PacketHandler;

@Mod
(
	modid   = "DwarvenTools",
	name    = "DwarvenTools",
	version = "1.1.1dev"
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
		ConfigureEnchantment.init();
		
		(new ForgeEventRegistry()).registerEvent();
		
		proxy.registerTextures();
		NetworkRegistry.instance().registerGuiHandler(instance, proxy);
		
		GameRegistry.registerWorldGenerator(new DwarvenWorldGenerator());

		(new ForgeChestHooks()).addLoot();
		(new LocalizationRegistry()).addLocalization();
		(new RecipeRegistry()).addRecipe();
	}

}
