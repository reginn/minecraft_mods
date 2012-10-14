package rgn.mods.lamp;

import java.util.logging.Level;

import net.minecraft.src.*;
import net.minecraftforge.common.*;

import cpw.mods.fml.common.*;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.network.*;
import cpw.mods.fml.common.registry.*;

import cpw.mods.fml.common.Mod;

import rgn.util.TranslationRegistry;

@Mod(modid = "Lamp", name = "Lamp", version = "2.3.1")
@NetworkMod(channels = { "lamp" }, clientSideRequired = true, serverSideRequired = false, packetHandler = PacketHandler.class)
public class Lamp
{
	@SidedProxy(clientSide = "rgn.mods.lamp.client.ClientProxy", serverSide = "rgn.mods.lamp.CommonProxy")
	public static CommonProxy proxy;
	
	@Mod.Instance("Lamp")
	public static Lamp instance;
	
	public static Block blockLamp;
	public static Block blockLight;
	
	public static int   lampRenderType;
	
	private int blockIdLamp;
	private int blockIdLight;
	
	@Mod.PreInit
	public void preInit(FMLPreInitializationEvent event)
	{
		Configuration cfg = new Configuration(event.getSuggestedConfigurationFile());
		try
		{
			cfg.load();
			blockIdLamp  = cfg.getOrCreateIntProperty("Lamp",  Configuration.CATEGORY_BLOCK, 1613).getInt();
			blockIdLight = cfg.getOrCreateIntProperty("Light", Configuration.CATEGORY_BLOCK, 1614).getInt();
		}
		catch (Exception e)
		{
			FMLLog.log(Level.SEVERE, e, "Lamp has a problem loading it's configuration");
		}
		finally
		{
			cfg.save();
		}
	}
	
	@Mod.Init
	public void Init(FMLInitializationEvent event)
	{
		this.lampRenderType = proxy.getNewRenderType();
		
		blockLamp  = (new BlockLamp(blockIdLamp,  139)).setBlockName("blocklamp");
		blockLight = (new BlockLight(blockIdLight, 0)).setBlockName("dontuse");
		
		GameRegistry.registerBlock(blockLamp, ItemLamp.class);
		GameRegistry.registerBlock(blockLight);
		
		proxy.registerTextures();
		proxy.registerRenderers();
		
		this.addLanternRecipe();
		this.addLocalizations();
	}
	
	private void addLocalizations()
	{
		TranslationRegistry.addLocalization(new ItemStack(blockLamp, 1, 0), "Lantern",         "ランタン");
		TranslationRegistry.addLocalization(new ItemStack(blockLamp, 1, 1), "Glow Lantern",    "グロウランタン");
		TranslationRegistry.addLocalization(new ItemStack(blockLamp, 1, 2), "Gold Lantern",    "ゴールドランタン");
		TranslationRegistry.addLocalization(new ItemStack(blockLamp, 1, 3), "Diamond Lantern", "ダイアランタン");
	}
	
	private void addLanternRecipe()
	{
		GameRegistry.addRecipe(
			new ItemStack(blockLamp, 16, 0),
				new Object[]
				{
					"ITI", "TCT", "ITI",
					Character.valueOf('I'), Item.ingotIron,
					Character.valueOf('T'), Block.thinGlass,
					Character.valueOf('C'), new ItemStack(Item.coal, 1, 0)
				});
				
		GameRegistry.addRecipe(
			new ItemStack(blockLamp, 16, 1),
				new Object[]
				{
					"ITI", "TLT", "ITI",
					Character.valueOf('I'), Item.ingotIron,
					Character.valueOf('T'), Block.thinGlass,
					Character.valueOf('L'), Block.glowStone
				});
							
		GameRegistry.addRecipe(
			new ItemStack(blockLamp, 16, 2),
				new Object[]
				{
					"IGI", "GLG", "IGI",
					Character.valueOf('I'), Item.ingotIron,
					Character.valueOf('G'), Item.ingotGold,
					Character.valueOf('L'), Block.glowStone
				});
							
		GameRegistry.addRecipe(
			new ItemStack(blockLamp, 16, 3),
				new Object[]
				{
					"IDI", "DLD", "IDI",
					Character.valueOf('I'), Item.ingotIron,
					Character.valueOf('L'), Block.glowStone,
					Character.valueOf('D'), Item.diamond
				});
	}
	
	

}
