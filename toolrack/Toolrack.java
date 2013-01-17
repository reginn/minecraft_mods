package rgn.mods.toolrack;

import java.util.logging.Level;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import rgn.util.TranslationRegistry;
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
	modid   = "Toolrack",
	name    = "Toolrack",
	version = "1.0.0"
)
@NetworkMod
(
	clientSideRequired = true,
	serverSideRequired = false,
	channels = { "Toolrack" },
	packetHandler = PacketHandler.class
)
public class Toolrack
{
	@SidedProxy(clientSide = "rgn.mods.toolrack.client.ClientProxy", serverSide = "rgn.mods.toolrack.CommonProxy")
	public static CommonProxy proxy;

	@Mod.Instance("Toolrack")
	public static Toolrack instance;

	public static Block blockToolrack;
	public static final int GUI_ID_TOOLRACK = 1;
	public static int RENDER_TYPE_TOOLRACK;
	private int blockIdToolrack;

	@Mod.PreInit
	public void preInit(FMLPreInitializationEvent event)
	{
		Configuration cfg = new Configuration(event.getSuggestedConfigurationFile());
		try
		{
			cfg.load();
			blockIdToolrack = cfg.getBlock("Toolrack", 1750).getInt();
		}
		catch (Exception e)
		{
			FMLLog.log(Level.SEVERE, e, "Toolrack has a problem loading it's configuration");
		}
		finally
		{
			cfg.save();
		}
	}

	@Mod.Init
	public void load(FMLInitializationEvent event)
	{
		RENDER_TYPE_TOOLRACK = proxy.getUniqueRenderType();
		blockToolrack = (new BlockToolrack(blockIdToolrack)).setBlockName("blockToolrack");
		GameRegistry.registerBlock(blockToolrack, ItemToolrack.class, "Toolrack");

		GameRegistry.registerTileEntity(TileEntityToolrack.class, "Toolrack");

		proxy.registerRenderer();
		proxy.registerTileEntitySpecialRenderer();

		for (int i = 0; i < BlockToolrack.types.length; ++i)
		{
			TranslationRegistry.addLocalization(new ItemStack(blockToolrack, 1, i), "Toolrack", "ツールラック");
		}
		
		(new RecipeRegistry()).addRecipe();
			
	}


}
