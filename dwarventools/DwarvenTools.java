package rgn.mods.dwarventools;

import net.minecraftforge.common.MinecraftForge;
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
	version = "3.0.2"
)
@NetworkMod
(
	clientSideRequired = true,
	serverSideRequired = false,
	channels = { "DwarvenTools" },
	packetHandler = PacketHandler.class
)
public class DwarvenTools
{
	@SidedProxy(clientSide = "rgn.mods.dwarventools.client.ClientProxy", serverSide = "rgn.mods.dwarventools.CommonProxy")
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
