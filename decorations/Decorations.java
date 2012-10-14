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

import rgn.util.TranslationRegistry;

@Mod(modid = "Decorations", name = "Decorations", version = "1.0.0")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class Decorations
{
	@SidedProxy(clientSide = "rgn.mods.decorations.client.ClientProxy", serverSide = "rgn.mods.decorations.CommonProxy")
	public static CommonProxy proxy;
	
	public static Block blockGlowFlower;
	public static Block blockBetterSnow;
	
	public static Block blockColoredLeaves;
	public static Block blockColoredSapling;
	
	public static Block blockTriIngot;
	
	private int blockIdGlowFlower;
	private int blockIdBetterSnow;
	
	private int blockIdColoredLeaves;
	private int blockIdColoredSapling;
	
	private int blockIdTriIngot;
	
	@Mod.PreInit
	public void preInit(FMLPreInitializationEvent event)
	{
		Configuration cfg = new Configuration(event.getSuggestedConfigurationFile());
		try
		{
			cfg.load();
			blockIdGlowFlower   = cfg.getOrCreateIntProperty("GlowFlower", Configuration.CATEGORY_BLOCK, 1700).getInt();
			blockIdBetterSnow   = cfg.getOrCreateIntProperty("BetterSnow", Configuration.CATEGORY_BLOCK, 1701).getInt();
			
			blockIdColoredLeaves  = cfg.getOrCreateIntProperty("ColoredLeaves",  Configuration.CATEGORY_BLOCK, 1702).getInt();
			blockIdColoredSapling = cfg.getOrCreateIntProperty("ColoredSapling", Configuration.CATEGORY_BLOCK, 1703).getInt();
			
			blockIdTriIngot = cfg.getOrCreateIntProperty("TriIngot", Configuration.CATEGORY_BLOCK, 1704).getInt();
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
		//triIngotRenderType = proxy.getUniqueRenderType();
		
		blockGlowFlower     = (new BlockGlowFlower(blockIdGlowFlower, 39)).setBlockName("blockglowflower");
		blockBetterSnow     = (new BlockBetterSnow(blockIdBetterSnow, 66)).setBlockName("blockbettersnow");
		blockColoredLeaves  = (new BlockColoredLeaves(blockIdColoredLeaves, 0)).setBlockName("blockcoloredleaves");
		blockColoredSapling = (new BlockColoredSapling(blockIdColoredSapling, 15)).setBlockName("blockcoloredsapling");
		
		//blockTriIngot = (new BlockTriIngot(blockIdTriIngot, 22)).setBlockName("blocktriingot");
		
		GameRegistry.registerBlock(blockGlowFlower,     ItemGlowFlower.class);
		GameRegistry.registerBlock(blockBetterSnow,     ItemBetterSnow.class);
		GameRegistry.registerBlock(blockColoredLeaves,  ItemColoredLeaves.class);
		GameRegistry.registerBlock(blockColoredSapling, ItemColoredSapling.class);
		
		MinecraftForge.setBlockHarvestLevel(blockBetterSnow, "shovel", 0);
		
		MinecraftForge.EVENT_BUS.register(new ForgeEventHooks());
		
		proxy.registerTextures();
		proxy.registerRenderers();
		
		this.addGlowFlowerRecipe();
		this.addBetterSnowRecipe();
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
		
		TranslationRegistry.addLocalization(new ItemStack(blockColoredLeaves, 1, 0), "Cherry Blossom",       "桜");
		TranslationRegistry.addLocalization(new ItemStack(blockColoredLeaves, 1, 1), "White Cherry Blossom", "白桜");
		TranslationRegistry.addLocalization(new ItemStack(blockColoredLeaves, 1, 2), "Maple Leaves",         "紅葉の葉");
		TranslationRegistry.addLocalization(new ItemStack(blockColoredLeaves, 1, 3), "Yellow Leaves",        "銀杏の葉");
		
	}
}