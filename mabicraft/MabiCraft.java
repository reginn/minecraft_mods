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
import rgn.mods.mabicraft.core.*;
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
	channels = { "Bonfire", "Enchanter", "Cookware" },
	packetHandler = PacketHandler.class
)
public class MabiCraft
{
	@SidedProxy
	(
		clientSide = "rgn.mods.mabicraft.core.client.ClientProxy",
		serverSide = "rgn.mods.mabicraft.core.CommonProxy"
	)
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
		
		MinecraftForge.EVENT_BUS.register(new ForgeEventHooks());
		
		proxy.registerTextures();
		proxy.registerRenderers();
		
		(new VillageTradeHandler()).addTradeHandler();
		(new LocalizationRegistry()).addLocalization();
		(new RecipeRegistry()).addRecipe();
	}
	
}