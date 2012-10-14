package rgn.mods.elventools;

import java.util.logging.Level;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

import cpw.mods.fml.common.*;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.network.*;
import cpw.mods.fml.common.registry.*;

import cpw.mods.fml.common.Mod;

import net.minecraft.src.*;

import net.minecraftforge.common.*;
import net.minecraftforge.oredict.*;

import rgn.util.TranslationRegistry;

@Mod(modid = "ElvenTools", name = "ElvenTools", version = "2.2.6")
@NetworkMod(channels = { "ElvenTools" }, clientSideRequired = true, serverSideRequired = false, packetHandler = PacketHandler.class)
public class ElvenTools
{
	@SidedProxy(clientSide = "rgn.mods.elventools.client.ClientProxy", serverSide = "rgn.mods.elventools.CommonProxy")
	public static CommonProxy proxy;
	
	@Mod.Instance("ElvenTools")
	public static ElvenTools instance;
	
	//---------------------------------- blocks
	public static Block blockEbonyLog;
	public static Block blockEbonyLeaves;
	public static Block blockEbonySapling;
	public static Block blockEbonyWood;
	
	public static Block blockRope;
	public static Block blockRopeEstablisher;

	//---------------------------------- items
	public static Item itemLeatherLongbow;
	public static Item itemCompositeLongbow;
	public static Item itemEnhancedLongbow;
	
	public static Item itemEbonyStick;
	
	public static Item itemEbonyBoat;
	
	public static Item itemTorchArrow;
	public static Item itemRopeArrow;
	
	public static Item itemRopeEstablisher;
	
	public static Item itemElvenShovelMithril;
	public static Item itemElvenPickaxeMithril;
	public static Item itemElvenAxeMithril;
	public static Item itemElvenSwordMithril;
	
	public static Item itemElvenShovelSilver;
	public static Item itemElvenPickaxeSilver;
	public static Item itemElvenAxeSilver;
	public static Item itemElvenSwordSilver;
	
	public static Item itemElvenSickle;
	public static Item itemElvenLumberAxe;
	
	//---------------------------------- add enum
	public static EnumToolMaterial enumToolMaterialMithril;
	public static EnumToolMaterial enumToolMaterialSilver;
	
	//---------------------------------- block ids
	private int blockIdEbonyLog;
	private int blockIdEbonyLeaves;
	private int blockIdEbonySapling;
	private int blockIdEbonyWood;
	
	private int blockIdRope;
	private int blockIdRopeEstablisher;
	
	//---------------------------------- item ids
	private int itemIdLeatherLongbow;
	private int itemIdCompositeLongbow;
	private int itemIdEnhancedLongbow;
	
	private int itemIdEbonyStick;
	
	private int itemIdEbonyBoat;
	
	private int itemIdTorchArrow;
	private int itemIdRopeArrow;
	
	private int itemIdRopeEstablisher;
	
	private int itemIdElvenShovelMithril;
	private int itemIdElvenPickaxeMithril;
	private int itemIdElvenAxeMithril;
	private int itemIdElvenSwordMithril;
	
	private int itemIdElvenShovelSilver;
	private int itemIdElvenPickaxeSilver;
	private int itemIdElvenAxeSilver;
	private int itemIdElvenSwordSilver;
	
	private int itemIdElvenSickle;
	private int itemIdElvenLumberAxe;
	
	//---------------------------------- other ids
	private int entityIdEbonyBoat;
	private int entityIdTorchArrow;
	private int entityIdRopeArrow;
	
	
	@Mod.PreInit
	public void preInit(FMLPreInitializationEvent event)
	{
		Configuration cfg = new Configuration(event.getSuggestedConfigurationFile());
		try
		{
			cfg.load();
			
			blockIdEbonyLog      = cfg.getOrCreateIntProperty("EbonyLog",     Configuration.CATEGORY_BLOCK, 1650).getInt();
			blockIdEbonyLeaves   = cfg.getOrCreateIntProperty("EbonyLeaves",  Configuration.CATEGORY_BLOCK, 1651).getInt();
			blockIdEbonySapling  = cfg.getOrCreateIntProperty("EbonySapling", Configuration.CATEGORY_BLOCK, 1652).getInt();
			blockIdEbonyWood     = cfg.getOrCreateIntProperty("EbonyWood",    Configuration.CATEGORY_BLOCK, 1653).getInt();
			
			blockIdRope            = cfg.getOrCreateIntProperty("Rope",            Configuration.CATEGORY_BLOCK, 1654).getInt();
			blockIdRopeEstablisher = cfg.getOrCreateIntProperty("RopeEstablisher", Configuration.CATEGORY_BLOCK, 1655).getInt();

			itemIdLeatherLongbow   = cfg.getOrCreateIntProperty("LetherLongbow",    Configuration.CATEGORY_ITEM, 21001).getInt();
			itemIdCompositeLongbow = cfg.getOrCreateIntProperty("CompositeLongbow", Configuration.CATEGORY_ITEM, 21002).getInt();
			itemIdEnhancedLongbow  = cfg.getOrCreateIntProperty("EnhancedLongbow",  Configuration.CATEGORY_ITEM, 21003).getInt();
			
			itemIdEbonyStick = cfg.getOrCreateIntProperty("EbonyStick", Configuration.CATEGORY_ITEM, 23004).getInt();
			
			itemIdEbonyBoat = cfg.getOrCreateIntProperty("EbonyBoat", Configuration.CATEGORY_ITEM, 23005).getInt();
			
			itemIdTorchArrow = cfg.getOrCreateIntProperty("TorchArrow", Configuration.CATEGORY_ITEM, 23006).getInt();
			itemIdRopeArrow  = cfg.getOrCreateIntProperty("RopeArrow",  Configuration.CATEGORY_ITEM, 23007).getInt();
			
			itemIdRopeEstablisher = cfg.getOrCreateIntProperty("RopeEstablisher", Configuration.CATEGORY_ITEM, 23008).getInt();
			
			itemIdElvenShovelMithril  = cfg.getOrCreateIntProperty("MithrilShovel",  Configuration.CATEGORY_ITEM, 23009).getInt();
			itemIdElvenPickaxeMithril = cfg.getOrCreateIntProperty("MithrilPickaxe", Configuration.CATEGORY_ITEM, 23010).getInt();
			itemIdElvenAxeMithril     = cfg.getOrCreateIntProperty("MithrilAxe",     Configuration.CATEGORY_ITEM, 23011).getInt();
			itemIdElvenSwordMithril   = cfg.getOrCreateIntProperty("MithrilSrowd",   Configuration.CATEGORY_ITEM, 23012).getInt();
			
			itemIdElvenShovelSilver  = cfg.getOrCreateIntProperty("SilverShovel",  Configuration.CATEGORY_ITEM, 23013).getInt();
			itemIdElvenPickaxeSilver = cfg.getOrCreateIntProperty("SilverPickaxe", Configuration.CATEGORY_ITEM, 23014).getInt();
			itemIdElvenAxeSilver     = cfg.getOrCreateIntProperty("SilverAxe",     Configuration.CATEGORY_ITEM, 23015).getInt();
			itemIdElvenSwordSilver   = cfg.getOrCreateIntProperty("SilverSrowd",   Configuration.CATEGORY_ITEM, 23016).getInt();
			
			itemIdElvenSickle    = cfg.getOrCreateIntProperty("Sickle",    Configuration.CATEGORY_ITEM, 23017).getInt();
			itemIdElvenLumberAxe = cfg.getOrCreateIntProperty("LumberAxe", Configuration.CATEGORY_ITEM, 23018).getInt();
			
			entityIdEbonyBoat  = cfg.getOrCreateIntProperty("entity.id.EbonyBoat",  Configuration.CATEGORY_GENERAL, 225).getInt();
			entityIdTorchArrow = cfg.getOrCreateIntProperty("entity.id.TorchArrow", Configuration.CATEGORY_GENERAL, 226).getInt();
			entityIdRopeArrow  = cfg.getOrCreateIntProperty("entity.id.RopeArrow",  Configuration.CATEGORY_GENERAL, 227).getInt();
			
		}
		catch (Exception e)
		{
			FMLLog.log(Level.SEVERE, e, "ElvenTools has a problem loading it's configuration");
		}
		finally
		{
			cfg.save();
		}
	}
	
	
	@Mod.Init
	public void init(FMLInitializationEvent event)
	{
		enumToolMaterialMithril = EnumHelper.addToolMaterial("MITHRIL", 2, 1024, 6.0F, 3,  0);
		enumToolMaterialSilver  = EnumHelper.addToolMaterial("SILVER",  2, 1024, 6.0F, 3, 20);
		
		blockEbonyLog     = (new BlockEbonyLog(blockIdEbonyLog, 0)).setBlockName("blockEbonyLog");
		blockEbonyLeaves  = (new BlockEbonyLeaves(blockIdEbonyLeaves, 3)).setBlockName("blockEbonyLeaves");
		blockEbonySapling = (new BlockEbonySapling(blockIdEbonySapling, 4)).setBlockName("blockEbonySapling");
		blockEbonyWood    = (new BlockEbonyWood(blockIdEbonyWood, 2)).setBlockName("blockEbonyWood");
		
		blockRope            = (new BlockRope(blockIdRope, 16)).setBlockName("blockRope.nouses");
		blockRopeEstablisher = (new BlockRopeEstablisher(blockIdRopeEstablisher, 16)).setBlockName("blockRopeEstablisher");
		
		MinecraftForge.setBlockHarvestLevel(blockEbonyLog, "axe", 0);
		MinecraftForge.setBlockHarvestLevel(blockEbonyWood, "axe", 0);
		MinecraftForge.setBlockHarvestLevel(blockEbonyLeaves, "axe", 0);
		
		itemLeatherLongbow   = (new ItemEnhancedBows(itemIdLeatherLongbow   - 256,  0,  512, 1.0F, 15)).setItemName("itemLeatherLongbow");
		itemCompositeLongbow = (new ItemEnhancedBows(itemIdCompositeLongbow - 256, 16,  768, 1.5F, 10)).setItemName("itemCompositeLongbow");
		itemEnhancedLongbow  = (new ItemEnhancedBows(itemIdEnhancedLongbow  - 256, 32, 4096, 2.0F,  1)).setItemName("itemEnhancedLongbow");
		
		itemEbonyStick = (new ItemElvenParts(itemIdEbonyStick - 256)).setIconCoord(1, 4).setItemName("itemEbonyStick");
		
		itemEbonyBoat = (new ItemEbonyBoat(itemIdEbonyBoat - 256)).setIconCoord(0, 4).setItemName("itemEbonyBoat");
		
		itemTorchArrow = (new ItemElvenParts(itemIdTorchArrow - 256)).setIconCoord(0, 5).setItemName("itemTorchArrow").setTabToDisplayOn(CreativeTabs.tabCombat);
		itemRopeArrow  = (new ItemElvenParts(itemIdRopeArrow  - 256)).setIconCoord(1, 5).setItemName("itemRopeArrow").setTabToDisplayOn(CreativeTabs.tabCombat);
		
		itemRopeEstablisher = (new ItemRopeEstablisher(itemIdRopeEstablisher - 256)).setIconCoord(2, 5).setItemName("itemRopeEstablisher");
		
		itemElvenShovelMithril  = (new ItemElvenShovel(itemIdElvenShovelMithril   - 256, enumToolMaterialMithril)).setIconCoord(0, 3).setItemName("itemElvenShovelMithril");
		itemElvenPickaxeMithril = (new ItemElvenPickaxe(itemIdElvenPickaxeMithril - 256, enumToolMaterialMithril)).setIconCoord(1, 3).setItemName("itemElvenPickaxeMithril");
		itemElvenAxeMithril     = (new ItemElvenAxe(itemIdElvenAxeMithril         - 256, enumToolMaterialMithril)).setIconCoord(2, 3).setItemName("itemElvenAxeMithril");
		itemElvenSwordMithril   = (new ItemElvenSword(itemIdElvenSwordMithril     - 256, enumToolMaterialMithril)).setIconCoord(3, 3).setItemName("itemElvenSwordMithril");
		
		itemElvenShovelSilver   = (new ItemElvenShovel(itemIdElvenShovelSilver   - 256, enumToolMaterialSilver)).setIconCoord(0, 3).setItemName("itemElvenShovelSilver");
		itemElvenPickaxeSilver  = (new ItemElvenPickaxe(itemIdElvenPickaxeSilver - 256, enumToolMaterialSilver)).setIconCoord(1, 3).setItemName("itemElvenPickaxeSilver");
		itemElvenAxeSilver      = (new ItemElvenAxe(itemIdElvenAxeSilver         - 256, enumToolMaterialSilver)).setIconCoord(2, 3).setItemName("itemElvenAxeSilver");
		itemElvenSwordSilver    = (new ItemElvenSword(itemIdElvenSwordSilver     - 256, enumToolMaterialSilver)).setIconCoord(3, 3).setItemName("itemElvenSwordSilver");
		
		itemElvenSickle = (new ItemElvenSickle(itemIdElvenSickle - 256, enumToolMaterialMithril)).setIconCoord(4, 3).setItemName("itemElvenSickle");
		itemElvenLumberAxe = (new ItemElvenLumberAxe(itemIdElvenLumberAxe - 256, enumToolMaterialMithril)).setIconCoord(2, 3).setItemName("itemElvenLumberAxe");
		
		EntityRegistry.registerGlobalEntityID(EntityEbonyBoat.class,  "EbonyBoat",  entityIdEbonyBoat);
		EntityRegistry.registerGlobalEntityID(EntityTorchArrow.class, "TorchArrow", entityIdTorchArrow);
		EntityRegistry.registerGlobalEntityID(EntityRopeArrow.class,  "RopeArrow",  entityIdRopeArrow);
		
		EntityRegistry.registerModEntity(EntityEbonyBoat.class,  "EbonyBoat",  0, this, 250, 5, true);
		EntityRegistry.registerModEntity(EntityTorchArrow.class, "TorchArrow", 1, this, 250, 5, true);
		EntityRegistry.registerModEntity(EntityRopeArrow.class,  "RopeArrow",  2, this, 250, 5, true);
		
		MinecraftForge.setToolClass(itemElvenShovelMithril, "shovel", 0);
		MinecraftForge.setToolClass(itemElvenShovelSilver,  "shovel", 0);
		
		MinecraftForge.setToolClass(itemElvenPickaxeMithril, "pickaxe", 2);
		MinecraftForge.setToolClass(itemElvenPickaxeSilver,  "pickaxe", 2);
		
		MinecraftForge.setToolClass(itemElvenAxeMithril, "axe", 0);
		MinecraftForge.setToolClass(itemElvenAxeSilver,  "axe", 0);
		MinecraftForge.setToolClass(itemElvenLumberAxe,  "axe", 0);
		
		GameRegistry.registerBlock(blockEbonyLog);
		GameRegistry.registerBlock(blockEbonyLeaves);
		GameRegistry.registerBlock(blockEbonySapling);
		GameRegistry.registerBlock(blockEbonyWood);
		GameRegistry.registerBlock(blockRope);
		GameRegistry.registerBlock(blockRopeEstablisher);
				
		OreDictionary.registerOre("woodLogEbony",     blockEbonyLog);
		OreDictionary.registerOre("plankEbony",       blockEbonyWood);
		OreDictionary.registerOre("stickEbony",       itemEbonyStick);
		OreDictionary.registerOre("woodSaplingEbony", blockEbonySapling);
		OreDictionary.registerOre("woodLeavesEbony",  blockEbonyLeaves);
		
		GameRegistry.registerFuelHandler(new ElvenToolsFuelHandler());
		GameRegistry.registerWorldGenerator(new ElvenWorldGenerator());
		MinecraftForge.EVENT_BUS.register(new ElvenEventHooks());
		
		proxy.registerTextures();
		proxy.registerRenderers();
		
		this.addSmelting();
		this.addRecipeNewBows();
		this.addRecipeEbony();
		this.addRecipeMithrilAndSilver();
		this.addRecipeArrow();
		
		this.addLocalization();
	}
	
	private void addLocalization()
	{
		TranslationRegistry.addLocalization(itemLeatherLongbow,   "Leather Longbow",   "レザーロングボウ");
		TranslationRegistry.addLocalization(itemCompositeLongbow, "Composite Longbow", "複合ロングボウ");
		TranslationRegistry.addLocalization(itemEnhancedLongbow,  "Enhanced Longbow",  "エンハンスドロングボウ");
		
		TranslationRegistry.addLocalization(blockEbonyLog,     "Ebony Log",           "黒檀の原木");
		TranslationRegistry.addLocalization(blockEbonyLeaves,  "Ebony Leaves",        "黒檀の葉");
		TranslationRegistry.addLocalization(blockEbonySapling, "Ebony Sapling",       "黒檀の苗木");
		TranslationRegistry.addLocalization(blockEbonyWood,    "Ebony Wooden Planks", "黒檀の木材");
		
		TranslationRegistry.addLocalization(blockRope,            "RopeBlock",              "ロープブロック");
		TranslationRegistry.addLocalization(blockRopeEstablisher, "Rope Establisher Block", "ロープ設置ブロック");
		
		TranslationRegistry.addLocalization(itemEbonyStick, "Ebony Stick", "黒檀の棒");
		TranslationRegistry.addLocalization(itemEbonyBoat,  "Ebony Boat",  "黒檀のボート");
		
		TranslationRegistry.addLocalization(itemTorchArrow,      "Torch Arrow",      "灯火の矢");
		TranslationRegistry.addLocalization(itemRopeArrow,       "Rope Arrow",       "ロープの矢");
		TranslationRegistry.addLocalization(itemRopeEstablisher, "Rope Establisher", "ロープ");
		
		TranslationRegistry.addLocalization(itemElvenShovelMithril,  "Elven Mithril Shovel",  "エルフのミスリルショベル");
		TranslationRegistry.addLocalization(itemElvenPickaxeMithril, "Elven Mithril Pickaxe", "エルフのミスリルつるはし");
		TranslationRegistry.addLocalization(itemElvenAxeMithril,     "Elven Mithril Axe",     "エルフのミスリル斧");
		TranslationRegistry.addLocalization(itemElvenSwordMithril,   "Elven Mithril Sword",   "エルフのミスリル直剣");
		
		TranslationRegistry.addLocalization(itemElvenShovelSilver,  "Silver Shovel",  "銀のショベル");
		TranslationRegistry.addLocalization(itemElvenPickaxeSilver, "Silver Pickaxe", "銀のつるはし");
		TranslationRegistry.addLocalization(itemElvenAxeSilver,     "Silver Axe",     "銀の斧");
		TranslationRegistry.addLocalization(itemElvenSwordSilver,   "Silver Sword",   "銀の剣");
		
		TranslationRegistry.addLocalization(itemElvenSickle,    "Elven Sickle",     "エルフの草刈り鎌");
		TranslationRegistry.addLocalization(itemElvenLumberAxe, "Elven Lumber Axe", "エルフの伐採斧");
		
	}
	
	private void addRecipeNewBows()
	{
		GameRegistry.addRecipe(
			new ShapedOreRecipe(
				new ItemStack(itemLeatherLongbow, 1),
					new Object[]
					{
						"SEL", "S E", "SEL",
						Character.valueOf('S'), Item.silk,
						Character.valueOf('L'), Item.leather,
						Character.valueOf('E'), "stickEbony"
					}));
		
		GameRegistry.addRecipe(
			new ItemStack(itemCompositeLongbow, 1),
				new Object[]
				{
					"IRR", "RBI", "RI ",
					Character.valueOf('I'), Item.ingotIron,
					Character.valueOf('B'), itemLeatherLongbow,
					Character.valueOf('R'), new ItemStack(Item.dyePowder, 1, 1)
				});
			
		GameRegistry.addRecipe(
			new ItemStack(itemEnhancedLongbow, 1), 
				new Object[]
				{
					"GOO", "DBG", "DG ",
					Character.valueOf('D'), Item.diamond,
					Character.valueOf('B'), itemCompositeLongbow,
					Character.valueOf('G'), Item.ingotGold,
					Character.valueOf('O'), Block.obsidian
				});
	}
	
	private void addRecipeEbony()
	{
		GameRegistry.addRecipe(
			new ShapelessOreRecipe(
				new ItemStack(blockEbonyWood, 4),
					new Object[]
					{
						"woodLogEbony"
					}));
		
		GameRegistry.addRecipe(
			new ShapedOreRecipe(
				new ItemStack(itemEbonyStick, 4),
					new Object[]
					{
						"E", "E",
						Character.valueOf('E'), "plankEbony"
					}));
		
		GameRegistry.addRecipe(
			new ShapedOreRecipe(
				new ItemStack(itemEbonyBoat, 1),
					new Object[]
					{
						"E E", "EEE",
						Character.valueOf('E'), "plankEbony"
					}));
		
		GameRegistry.addRecipe(
			new ShapedOreRecipe(
				new ItemStack(Block.torchWood, 4),
					new Object[]
					{
						"C", "E",
						Character.valueOf('C'), new ItemStack(Item.coal, 1, 0),
						Character.valueOf('E'), "stickEbony"
					}));
		
		GameRegistry.addRecipe(
			new ShapedOreRecipe(
				new ItemStack(Block.torchWood, 4),
					new Object[]
					{
						"C", "E",
						Character.valueOf('C'), new ItemStack(Item.coal, 1, 1),
						Character.valueOf('E'), "stickEbony"
					}));
		
		GameRegistry.addRecipe(
			new ShapedOreRecipe(
				new ItemStack(itemElvenSickle, 1),
					new Object[]
					{
						"MM ", " E ", " E ",
						Character.valueOf('M'), "ingotMithril",
						Character.valueOf('E'), "stickEbony"
					}));
		
		GameRegistry.addRecipe(
			new ShapedOreRecipe(
				new ItemStack(itemElvenLumberAxe, 1),
					new Object[]
					{
						"MM ", "ME ", "LEL",
						Character.valueOf('M'), "ingotMithril",
						Character.valueOf('E'), "stickEbony",
						Character.valueOf('L'), Item.leather
					}));
	}
	
	private void addRecipeMithrilAndSilver()
	{
		final Item[] shovelList  = new Item[] {itemElvenShovelMithril,  itemElvenShovelSilver};
		final Item[] pickaxeList = new Item[] {itemElvenPickaxeMithril, itemElvenPickaxeSilver};
		final Item[] axeList     = new Item[] {itemElvenAxeMithril,     itemElvenAxeSilver};
		final Item[] swordList   = new Item[] {itemElvenSwordMithril,   itemElvenSwordSilver};
		
		final String[] shovelRecipe  = new String[] {  "M",   "S",   "S"};
		final String[] pickaxeRecipe = new String[] {"MMM", " S ", " S "};
		final String[] axeRecipe     = new String[] { "MM",  "MS",  " S"};
		final String[] swordRecipe   = new String[] {  "M",   "M",   "S"};
		
		final String[] materialList = new String[] {"ingotMithril", "ingotSilver"};
		
		List<Item[]> itemList = new ArrayList<Item[]>();
		itemList.add(shovelList);
		itemList.add(pickaxeList);
		itemList.add(axeList);
		itemList.add(swordList);
		
		List<String[]> recipeList = new ArrayList<String[]>();
		recipeList.add(shovelRecipe);
		recipeList.add(pickaxeRecipe);
		recipeList.add(axeRecipe);
		recipeList.add(swordRecipe);
		
		for (int i = 0; i < itemList.size(); i++)
		{
			for (int j = 0; j < materialList.length; j++ )
			{
				GameRegistry.addRecipe(
					new ShapedOreRecipe(
						new ItemStack((itemList.get(i))[j], 1),
							new Object[]
							{
								(recipeList.get(i))[0], (recipeList.get(i))[1], (recipeList.get(i))[2],
								Character.valueOf('M'), materialList[j],
								Character.valueOf('S'), "stickEbony"
							}));
			}
		}
		
	}
	
	private void addRecipeArrow()
	{
		GameRegistry.addRecipe(
			new ItemStack(itemRopeEstablisher, 1),
				new Object[]
				{
					"SS", "SS", "SS",
					Character.valueOf('S'), Item.silk
				});
		
		GameRegistry.addRecipe(
			new ItemStack(itemTorchArrow, 1),
				new Object[]
				{
					" T", "A ",
					Character.valueOf('T'), Block.torchWood,
					Character.valueOf('A'), Item.arrow
				});
		
		GameRegistry.addRecipe(
			new ItemStack(itemRopeArrow, 1),
				new Object[]
				{
					" R", "A ",
					Character.valueOf('R'), itemRopeEstablisher,
					Character.valueOf('A'), Item.arrow
				});
	}
	
	private void addSmelting()
	{
		GameRegistry.addSmelting(blockEbonyLog.blockID, new ItemStack(Item.coal, 2, 1), 1.0F);
	}
	
}
 