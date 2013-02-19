package rgn.mods.mabicraft;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;

import rgn.mods.mabicraft.block.MabiCraftBlock;
import rgn.mods.mabicraft.config.Config;
import rgn.mods.mabicraft.core.CommonProxy;
import rgn.mods.mabicraft.core.LocalizationRegistry;
import rgn.mods.mabicraft.core.RecipeRegistry;
import rgn.mods.mabicraft.entity.MabiCraftEntity;
import rgn.mods.mabicraft.event.ForgeEventRegistry;
import rgn.mods.mabicraft.item.MabiCraftItem;
import rgn.mods.mabicraft.network.PacketHandler;
import rgn.mods.mabicraft.registry.VillageTradeRegistry;

@Mod
(
	modid   = "MabiCraft",
	name    = "MabiCraft",
	version = "3.0.0dev"
)
@NetworkMod
(
	clientSideRequired = true,
	serverSideRequired = false,
	channels = { "Bonfire", "Enchanter", "Cookware", "Cookpot", "FishingRod" },
	packetHandler = PacketHandler.class
)
public class MabiCraft
{
	@SidedProxy
	(
		clientSide = "rgn.mods.mabicraft.client.ClientProxy",
		serverSide = "rgn.mods.mabicraft.core.CommonProxy"
	)
	public static CommonProxy proxy;

	@Mod.Instance("MabiCraft")
	public static MabiCraft instance;

	public Config config;

	@Mod.PreInit
	public void preInit(FMLPreInitializationEvent event)
	{
		this.config = new Config();
		this.config.buildConfiguration(event.getSuggestedConfigurationFile());
	}

	@Mod.Init
	public void load(FMLInitializationEvent event)
	{
		MabiCraftBlock.configure(this.config);
		MabiCraftItem.configure(this.config);
		MabiCraftEntity.configure();

		NetworkRegistry.instance().registerGuiHandler(this, proxy);

		proxy.registerTextures();
		proxy.registerRenderers();

		(new ForgeEventRegistry()).registerAll();
		(new VillageTradeRegistry()).addTradeHandler(this.config);
		(new LocalizationRegistry()).addLocalization();
		(new RecipeRegistry()).addRecipe();
	}

}