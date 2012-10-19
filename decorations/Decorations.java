package rgn.mods.decorations;

import java.util.logging.Level;

import net.minecraft.src.*;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.network.NetworkMod;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.network.NetworkRegistry;

import rgn.util.TranslationRegistry;

@Mod(modid = "Decorations", name = "Decorations", version = "1.0.0")
@NetworkMod(
	clientSideRequired = true,
	serverSideRequired = false,
	channels = { "ozen" },
	packetHandler = PacketHandler.class
	)
public class Decorations
{
	@SidedProxy(clientSide = "rgn.mods.decorations.client.ClientProxy", serverSide = "rgn.mods.decorations.CommonProxy")
	public static CommonProxy proxy;
	
	@Mod.Instance("Decorations")
	public static Decorations instance;
	
	public static Block blockGlowFlower;
	public static Block blockBetterSnow;
	
	public static Block blockOzen;
	
	private int blockIdGlowFlower;
	private int blockIdBetterSnow;
	
	private int blockIdOzen;
	
	public static int ozenRenderID;
	public static int guiIdOzen = 1;
	
	@Mod.PreInit
	public void preInit(FMLPreInitializationEvent event)
	{
		Configuration cfg = new Configuration(event.getSuggestedConfigurationFile());
		try
		{
			cfg.load();
			blockIdGlowFlower   = cfg.getOrCreateIntProperty("GlowFlower", Configuration.CATEGORY_BLOCK, 1700).getInt();
			blockIdBetterSnow   = cfg.getOrCreateIntProperty("BetterSnow", Configuration.CATEGORY_BLOCK, 1701).getInt();
			
			blockIdOzen = cfg.getOrCreateIntProperty("Ozen", Configuration.CATEGORY_BLOCK, 1702).getInt();
		
		}
		catch (Exception e)
		{
			FMLLog.log(Level.SEVERE, e, "Decorations has a problem loading it's configuration");
		}
		finally
		{
			cfg.save();
		}
	}
	
	@Mod.Init
	public void init(FMLInitializationEvent event)
	{
		this.ozenRenderID = proxy.getUniqueRenderID();
		
		blockGlowFlower     = (new BlockGlowFlower(blockIdGlowFlower, 39)).setBlockName("blockglowflower");
		blockBetterSnow     = (new BlockBetterSnow(blockIdBetterSnow, 66)).setBlockName("blockbettersnow");
		
		blockOzen = (new BlockOzen(blockIdOzen)).setBlockName("blockozen");
		
		GameRegistry.registerBlock(blockGlowFlower,     ItemGlowFlower.class);
		GameRegistry.registerBlock(blockBetterSnow,     ItemBetterSnow.class);
		
		GameRegistry.registerBlock(blockOzen, ItemOzen.class);
		GameRegistry.registerTileEntity(TileEntityOzen.class, "ozen");
		
		MinecraftForge.setBlockHarvestLevel(blockBetterSnow, "shovel", 0);
				
		proxy.registerTextures();
		proxy.registerRenderers();
		
		NetworkRegistry.instance().registerGuiHandler(instance, proxy);
		
		this.addGlowFlowerRecipe();
		this.addBetterSnowRecipe();
		this.addOzenRecipe();
		this.addLocalizations();
	}
	
	private void addGlowFlowerRecipe()
	{
		ItemStack[] materials = 
			{
				new ItemStack(Block.plantRed),        new ItemStack(Block.plantYellow), 
				new ItemStack(Block.mushroomRed),     new ItemStack(Block.mushroomBrown),
				new ItemStack(Block.tallGrass, 1, 1), new ItemStack(Block.tallGrass, 1, 2),
				new ItemStack(Block.deadBush)
			};
		
		for (int i = 0; i < materials.length; ++i)
		{
			GameRegistry.addShapelessRecipe(
				new ItemStack(blockGlowFlower, 1, i),
					new Object[]
					{
						Item.lightStoneDust, materials[i]
					});
		}
	}
	
	private void addBetterSnowRecipe()
	{
		GameRegistry.addRecipe(
			new ItemStack(blockBetterSnow, 1, 0),
				new Object[]
				{
					"XX",
					Character.valueOf('X'), Item.snowball
				});
		
		GameRegistry.addShapelessRecipe(
			new ItemStack(blockBetterSnow, 1, 1),
				new Object[]
				{
					new ItemStack(blockBetterSnow, 1, 0), Item.lightStoneDust
				});
	}
	
	private void addOzenRecipe()
	{
		for (int i = 0; i < 3; ++i)
		{
			GameRegistry.addRecipe(
				new ItemStack(blockOzen, 1, i),
					new Object[]
					{
						" S ", "SWS", " S ",
						Character.valueOf('W'), new ItemStack(Block.woodSingleSlab, 1, i),
						Character.valueOf('S'), Item.stick
					});
		}
		
		ItemStack[] material = new ItemStack[]
			{
				new ItemStack(Item.dyePowder, 1, 0), new ItemStack(Item.dyePowder, 1, 1), new ItemStack(Item.ingotIron), new ItemStack(Item.ingotGold)
			};
		
		for (int i = 0; i < material.length; ++i)
		{
			for (int j = 0; j < 3; ++j)
			{
				GameRegistry.addShapelessRecipe(
					new ItemStack(blockOzen, 1, 3 + i),
						new Object[]
						{
							new ItemStack(blockOzen, 1, j), material[i]
						});
			}
		}
		
		for (int i = 0; i < 7; ++i)
		{
			GameRegistry.addShapelessRecipe(
				new ItemStack(blockOzen, 1, i),
					new Object[]
					{
						new ItemStack(blockOzen, 1, 8 + i),
					});
			
			GameRegistry.addShapelessRecipe(
				new ItemStack(blockOzen, 1, 8 + i),
					new Object[]
					{
						new ItemStack(blockOzen, 1, i),
					});
			
		}
	}
	
	private void addLocalizations()
	{
		TranslationRegistry.addLocalization(new ItemStack(blockGlowFlower, 1, 0), "Glow Rose",           "光るバラ");
		TranslationRegistry.addLocalization(new ItemStack(blockGlowFlower, 1, 1), "Glow Flower",         "光る花");
		TranslationRegistry.addLocalization(new ItemStack(blockGlowFlower, 1, 2), "Glow Red Mushroom",   "光る赤キノコ");
		TranslationRegistry.addLocalization(new ItemStack(blockGlowFlower, 1, 3), "Glow Brown Mushroom", "光る茶キノコ");
		TranslationRegistry.addLocalization(new ItemStack(blockGlowFlower, 1, 4), "Glow Tall Grass",     "光る草");
		TranslationRegistry.addLocalization(new ItemStack(blockGlowFlower, 1, 5), "Glow Fern",           "光るシダ");
		TranslationRegistry.addLocalization(new ItemStack(blockGlowFlower, 1, 6), "Glow Dead Bush",      "光る枯れ木");
		
		TranslationRegistry.addLocalization(new ItemStack(blockBetterSnow, 1, 0), "No Melt Snow", "溶けない雪");
		TranslationRegistry.addLocalization(new ItemStack(blockBetterSnow, 1, 1), "Glow Snow",    "光る雪");
		
		TranslationRegistry.addLocalization(new ItemStack(blockOzen, 1,  0), "Oak Oshiki",    "オーク材の折敷");
		TranslationRegistry.addLocalization(new ItemStack(blockOzen, 1,  1), "Birch Oshiki",  "白樺材の折敷");
		TranslationRegistry.addLocalization(new ItemStack(blockOzen, 1,  2), "Spruce Oshiki", "松材の折敷");
		TranslationRegistry.addLocalization(new ItemStack(blockOzen, 1,  3), "Black Oshiki",  "黒い折敷");
		TranslationRegistry.addLocalization(new ItemStack(blockOzen, 1,  4), "Red Oshiki",    "赤い折敷");
		TranslationRegistry.addLocalization(new ItemStack(blockOzen, 1,  5), "White Oshiki",  "白色の折敷");
		TranslationRegistry.addLocalization(new ItemStack(blockOzen, 1,  6), "Gold Oshiki",   "金色の折敷");
		
		TranslationRegistry.addLocalization(new ItemStack(blockOzen, 1,  8), "Oak Ozen",    "オーク材のお膳");
		TranslationRegistry.addLocalization(new ItemStack(blockOzen, 1,  9), "Birch Ozen",  "白樺材のお膳");
		TranslationRegistry.addLocalization(new ItemStack(blockOzen, 1, 10), "Spruce Ozen", "松材のお膳");
		TranslationRegistry.addLocalization(new ItemStack(blockOzen, 1, 11), "Black Ozen",  "黒いお膳");
		TranslationRegistry.addLocalization(new ItemStack(blockOzen, 1, 12), "Red Ozen",    "赤いお膳");
		TranslationRegistry.addLocalization(new ItemStack(blockOzen, 1, 13), "White Ozen",  "白色のお膳");
		TranslationRegistry.addLocalization(new ItemStack(blockOzen, 1, 14), "Gold Ozen",   "金色のお膳");
	}
	
}