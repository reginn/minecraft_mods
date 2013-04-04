package rgn.mods.toolrack;

import java.util.logging.Level;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

import net.minecraftforge.common.Configuration;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;

import rgn.util.TranslationRegistry;

@Mod
(
	modid   = "Toolrack",
	name    = "Toolrack",
	version = "2.0.0"
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

	public static final CreativeTabs tabToolrack = new CreativeTabToolrack("Toolrack");

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
		blockToolrack = (new BlockToolrack(blockIdToolrack)).setUnlocalizedName("blockToolrack").setCreativeTab(tabToolrack);
		GameRegistry.registerBlock(blockToolrack, ItemToolrack.class, "Toolrack");

		GameRegistry.registerTileEntity(TileEntityToolrack.class, "Toolrack");

		proxy.registerRenderer();
		proxy.registerTileEntitySpecialRenderer();

		TranslationRegistry.addLocalization(new ItemStack(blockToolrack, 1, 0), "Ork Toolrack",    "オークのツールラック");
		TranslationRegistry.addLocalization(new ItemStack(blockToolrack, 1, 1), "Spruce Toolrack", "松のツールラック");
		TranslationRegistry.addLocalization(new ItemStack(blockToolrack, 1, 2), "Birch Toolrack",  "白樺のツールラック");
		TranslationRegistry.addLocalization(new ItemStack(blockToolrack, 1, 3), "Jungle Toolrack", "ジャングル木のツールラック");
		TranslationRegistry.addLocalization(new ItemStack(blockToolrack, 1, 4), "Black Toolrack",  "黒ツールラック");
		TranslationRegistry.addLocalization(new ItemStack(blockToolrack, 1, 5), "Red Toolrack",    "赤ツールラック");
		TranslationRegistry.addLocalization(new ItemStack(blockToolrack, 1, 6), "White Toolrack",  "白ツールラック");

		(new RecipeRegistry()).addRecipe();

	}


}
