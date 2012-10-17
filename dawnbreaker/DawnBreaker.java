package rgn.mods.dawnbreaker;

import java.util.logging.Level;

import net.minecraft.src.*;

import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.Property;

import cpw.mods.fml.common.FMLLog;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.network.NetworkMod;

import cpw.mods.fml.common.SidedProxy;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLInitializationEvent;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

import rgn.util.TranslationRegistry;

@Mod(modid = "DawnBreaker", name = "Dawn Breaker", version = "1.0.1")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class DawnBreaker
{
	@SidedProxy(clientSide = "rgn.mods.dawnbreaker.client.ClientProxy", serverSide = "rgn.mods.dawnbreaker.CommonProxy")
	public static CommonProxy proxy;
	
	public static Item itemDawnBreaker;
	private int itemIdDawnBreaker;
	
	public static int explodePower;
	
	@Mod.PreInit
	public void preInit(FMLPreInitializationEvent event)
	{
		Configuration cfg = new Configuration(event.getSuggestedConfigurationFile());
		try
		{
			cfg.load();
			itemIdDawnBreaker = cfg.getOrCreateIntProperty("DawnBreaker", Configuration.CATEGORY_ITEM, 28000).getInt();
			Property exploadPowerProperty = cfg.getOrCreateIntProperty("ExplodePower", Configuration.CATEGORY_GENERAL, 1);
			exploadPowerProperty.comment = "Explosion Power (0 = very weak explosion, 1 = ghast's fireball, 2 = crepper's explosion, 3 = powered creeper's explosion";
			explodePower = exploadPowerProperty.getInt();
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