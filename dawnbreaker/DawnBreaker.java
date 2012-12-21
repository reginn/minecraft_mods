package rgn.mods.dawnbreaker;

import java.util.logging.Level;

import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.Property;
import rgn.util.TranslationRegistry;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod
(
	modid   = "DawnBreaker",
	name    = "Dawn Breaker",
	version = "2.1.0"
)
@NetworkMod
(
	clientSideRequired = true,
	serverSideRequired = false
)
public class DawnBreaker
{
	@SidedProxy(clientSide = "rgn.mods.dawnbreaker.client.ClientProxy", serverSide = "rgn.mods.dawnbreaker.CommonProxy")
	public static CommonProxy proxy;

	public static Item itemDawnBreaker;
	private int itemIdDawnBreaker;

	public static int explodePower;
	public static boolean isBlockDestroy;

	@Mod.PreInit
	public void preInit(FMLPreInitializationEvent event)
	{
		Configuration cfg = new Configuration(event.getSuggestedConfigurationFile());
		try
		{
			cfg.load();
			itemIdDawnBreaker = cfg.getItem("DawnBreaker", 28000).getInt();
			Property exploadPowerProperty = cfg.get(Configuration.CATEGORY_GENERAL, "ExplodePower", 1);
			exploadPowerProperty.comment = "Explosion Power (0 = very weak explosion, 1 = ghast's fireball, 2 = crepper's explosion, 3 = powered creeper's explosion";
			explodePower = exploadPowerProperty.getInt();

			Property isBlockDestroyProperty = cfg.get(Configuration.CATEGORY_GENERAL, "isBlockDestroy", false);
			isBlockDestroyProperty.comment = "Explosion can destroy block? true : can destroy, false : can't";
			isBlockDestroy = isBlockDestroyProperty.getBoolean(false);
		}
		catch (Exception e)
		{
			FMLLog.log(Level.SEVERE, e, "Exception occured! in DawnBreaker.configuration");
		}
		finally
		{
			cfg.save();
		}
	}

	@Mod.Init
	public void init(FMLInitializationEvent event)
	{
		itemDawnBreaker = (new ItemDawnBreaker(itemIdDawnBreaker - 256, EnumToolMaterial.IRON)).setIconCoord(0, 0).setItemName("dawnbreaker");

		TranslationRegistry.addLocalization(itemDawnBreaker, "DawnBreaker", "ドーンブレイカー");

		MinecraftForge.EVENT_BUS.register(new ForgeEventHooks());

		GameRegistry.addRecipe(
			new ItemStack(itemDawnBreaker, 1),
				new Object[]
				{
					"G", "G", "B",
					Character.valueOf('G'), Item.ingotGold,
					Character.valueOf('B'), Item.blazeRod,
				}
				);
	}
}