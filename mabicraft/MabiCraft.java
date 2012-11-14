package rgn.mods.mabicraft;

import net.minecraft.src.*;

import net.minecraftforge.common.MinecraftForge;

import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.network.NetworkMod;

import rgn.util.TranslationRegistry;

import rgn.mods.mabicraft.config.*;
import rgn.mods.mabicraft.common.*;
import rgn.mods.mabicraft.enchant.*;
import rgn.mods.mabicraft.villager.*;
import rgn.mods.mabicraft.registry.*;

@Mod
(
	modid   = "MabiCraft",
	name    = "MabiCraft",
	version = "2.0.0"
)
@NetworkMod
(
	clientSideRequired = true,
	serverSideRequired = false,
	channels = { "Bonfire", "Enchanter" },
	packetHandler = PacketHandler.class
)
public class MabiCraft
{
	@SidedProxy(clientSide = "rgn.mods.mabicraft.client.ClientProxy", serverSide = "rgn.mods.mabicraft.CommonProxy")
	public static CommonProxy proxy;

	@Mod.Instance("MabiCraft")
	public static MabiCraft instance;
	
	@Mod.PreInit
	public void preInit(FMLPreInitializationEvent event)
	{
		Config.buildConfiguration(event.getSuggestedConfigurationFile());
	}
	
	@Mod.Init
	public void load(FMLInitializationEvent event)
	{
		ConfigureBlock.init();
		ConfigureItem.init();
		
		NetworkRegistry.instance().registerGuiHandler(this, proxy);
		
		VillagerRegistry.instance().registerVillageTradeHandler(0, new VillageTradeHandler().new FarmerTradeHandler());
		VillagerRegistry.instance().registerVillageTradeHandler(1, new VillageTradeHandler().new LibrarianTradeHandler());
		VillagerRegistry.instance().registerVillageTradeHandler(2, new VillageTradeHandler().new PriestTradeHandler());
		
		MinecraftForge.EVENT_BUS.register(new ForgeEventHooks());
		
		proxy.registerTextures();
		proxy.registerRenderers();
		
		(new LocalizationRegistry()).addLocalization();
		(new RecipeRegistry()).addRecipe();
	}
	
}