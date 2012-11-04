package rgn.mods.elventools;

import net.minecraft.src.*;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.network.NetworkMod;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

import cpw.mods.fml.common.registry.GameRegistry;

import net.minecraftforge.common.MinecraftForge;

import rgn.util.TranslationRegistry;

@Mod
(
	modid   = "ElvenTools", 
	name    = "ElvenTools", 
	version = "3.0.0"
)
@NetworkMod
( 
	clientSideRequired = true, 
	serverSideRequired = false, 
	channels           = { "ElvenTools" },
	packetHandler      = PacketHandler.class
)
public class ElvenTools
{
	@SidedProxy(clientSide = "rgn.mods.elventools.client.ClientProxy", serverSide = "rgn.mods.elventools.CommonProxy")
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
 