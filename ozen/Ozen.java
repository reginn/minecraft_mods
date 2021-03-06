package rgn.mods.ozen;

import java.util.logging.Level;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;

import net.minecraftforge.common.Configuration;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod
(
	modid   = "Ozen",
	name    = "Ozen",
	version = "4.0.0"
)
@NetworkMod
(
	clientSideRequired = true,
	serverSideRequired = false,
	channels = { "ozen" },
	packetHandler = PacketHandler.class
)
public class Ozen
{
	@SidedProxy(clientSide = "rgn.mods.ozen.client.ClientProxy", serverSide = "rgn.mods.ozen.CommonProxy")
	public static CommonProxy proxy;

	@Mod.Instance("Ozen")
	public static Ozen instance;

	public static Block blockOzen;

	private int blockIdOzen;

	public static int ozenRenderID;
	public static int guiIdOzen = 1;

	public static final CreativeTabs tabOzen = new CreativeTabOzen("Ozen");

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		Configuration cfg = new Configuration(event.getSuggestedConfigurationFile());
		try
		{
			cfg.load();
			blockIdOzen = cfg.getBlock("Ozen", 1700).getInt();
		}
		catch (Exception e)
		{
			FMLLog.log(Level.SEVERE, e, "Ozen's configuraion has Probrems!");
		}
		finally
		{
			cfg.save();
		}
		this.ozenRenderID = proxy.getUniqueRenderID();

		blockOzen = (new BlockOzen(blockIdOzen)).setUnlocalizedName("blockozen");

		GameRegistry.registerBlock(blockOzen, ItemOzen.class, "Ozen");
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{

		GameRegistry.registerTileEntity(TileEntityOzen.class, "Ozen");

		proxy.registerRenderers();

		NetworkRegistry.instance().registerGuiHandler(instance, proxy);

		(new RecipeRegistry()).addRecipe();
		(new LocalizationRegistry()).addLocalization();
	}

}