package rgn.mods.mabicraft;

import java.util.logging.Level;

import net.minecraft.src.*;
import net.minecraftforge.common.*;

import cpw.mods.fml.common.*;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.network.*;
import cpw.mods.fml.common.registry.*;

import cpw.mods.fml.common.Mod;

import rgn.util.TranslationRegistry;

@Mod(modid = "MabiCraft", name = "MabiCraft", version = "1.1.7")
@NetworkMod(channels = { "Bonfire", "Enchanter" }, clientSideRequired = true, serverSideRequired = false, packetHandler = PacketHandler.class)
public class MabiCraft
{
	@Mod.Instance("MabiCraft")
	public static MabiCraft instance;
	
	@SidedProxy(clientSide = "rgn.mods.mabicraft.client.ClientProxy", serverSide = "rgn.mods.mabicraft.CommonProxy")
	public static CommonProxy proxy;
	
	public static Block blockBonfire;
	public static Block blockEnchanter;
	
	public static Item itemEnchantScroll;
	
	public static Item itemBaseHerb;
	public static Item itemManaHerb;
	public static Item itemBloodyHerb;
	public static Item itemSunlightHerb;
	public static Item itemPoisonHerb;
	public static Item itemIvoryHerb;
	
	public static Item itemMagicPowder;
	public static Item itemBlessedPotion;
	public static Item itemBonfireKit;
	
	public static Item itemEvilScroll;
	
	private int blockIdBonfire;
	private int blockIdEnchanter;
	
	private int itemIdEnchantScroll;
	
	private int itemIdBaseHerb;
	private int itemIdManaHerb;
	private int itemIdBloodyHerb;
	private int itemIdSunlightHerb;
	private int itemIdPoisonHerb;
	private int itemIdIvoryHerb;
	
	private int itemIdMagicPowder;
	private int itemIdBlessedPotion;
	private int itemIdBonfireKit;
	
	private int itemIdEvilScroll;
	
	public static int guiIdBonfire = 0;
	public static int guiIdEnchanter = 1;
	
	//public static int entityIdHerbPig;
	// future entities
	/*
	public static int entityIdGhost;
	public static int entityIdFlyingSword;
	public static int entityIdBlackBearRat;
	public static int entityIdMetalSkelton;
	public static int entityIdRedSkelton;
	public static int entityIdLaghodessa;
	*/
	
	public static int bonfireRenderType;
	public static int enchanterRenderType;
	
	@Mod.PreInit
	public void preInit(FMLPreInitializationEvent event)
	{
		Configuration cfg = new Configuration(event.getSuggestedConfigurationFile());
		try
		{
			cfg.load();
			
			//entityIdHerbPig = cfg.getOrCreateIntProperty("entity.id.HerbPig", Configuration.CATEGORY_GENERAL, 240).getInt();
			
			blockIdBonfire   = cfg.getOrCreateIntProperty("Bonfire", Configuration.CATEGORY_BLOCK, 1900).getInt();
			blockIdEnchanter = cfg.getOrCreateIntProperty("Enchanter", Configuration.CATEGORY_BLOCK, 1901).getInt();
			
			itemIdEnchantScroll = cfg.getOrCreateIntProperty("EnchantScroll", Configuration.CATEGORY_ITEM, 13007).getInt();
			
			itemIdBaseHerb     = cfg.getOrCreateIntProperty("BaseHerb",     Configuration.CATEGORY_ITEM, 13000).getInt();
			itemIdManaHerb     = cfg.getOrCreateIntProperty("ManaHerb",     Configuration.CATEGORY_ITEM, 13001).getInt();
			itemIdBloodyHerb   = cfg.getOrCreateIntProperty("BloodyHerb",   Configuration.CATEGORY_ITEM, 13002).getInt();
			itemIdSunlightHerb = cfg.getOrCreateIntProperty("SunlightHerb", Configuration.CATEGORY_ITEM, 13003).getInt();
			itemIdPoisonHerb   = cfg.getOrCreateIntProperty("PoisonHerb",   Configuration.CATEGORY_ITEM, 13004).getInt();
			itemIdIvoryHerb    = cfg.getOrCreateIntProperty("IvoryHerb",    Configuration.CATEGORY_ITEM, 13005).getInt();
			
			itemIdMagicPowder   = cfg.getOrCreateIntProperty("MagicPowder",   Configuration.CATEGORY_ITEM, 13006).getInt();
			itemIdBlessedPotion = cfg.getOrCreateIntProperty("BlessedPotion", Configuration.CATEGORY_ITEM, 13008).getInt();
			itemIdBonfireKit    = cfg.getOrCreateIntProperty("BonfireKit", Configuration.CATEGORY_ITEM, 13009).getInt();
			itemIdEvilScroll    = cfg.getOrCreateIntProperty("EvilScroll", Configuration.CATEGORY_ITEM, 13010).getInt();
			
		}
		catch (Exception e)
		{
			FMLLog.log(Level.SEVERE, e, "MabiCraft has a problem loading it's configuration");
		}
		finally
		{
			cfg.save();
		}
	}
	
	@Mod.Init
	public void load(FMLInitializationEvent event)
	{
		bonfireRenderType   = proxy.getNewRenderType();
		enchanterRenderType = proxy.getNewRenderType();
		
		itemBaseHerb     = (new ItemHerb(itemIdBaseHerb     - 256, EnumHerbType.BASE    )).setIconCoord(0, 0).setItemName("baseherb");
		itemManaHerb     = (new ItemHerb(itemIdManaHerb     - 256, EnumHerbType.MANA    )).setIconCoord(1, 0).setItemName("manaherb");
		itemBloodyHerb   = (new ItemHerb(itemIdBloodyHerb   - 256, EnumHerbType.BLOODY  )).setIconCoord(2, 0).setItemName("bloodyherb");
		itemSunlightHerb = (new ItemHerb(itemIdSunlightHerb - 256, EnumHerbType.SUNLIGHT)).setIconCoord(3, 0).setItemName("sunlightherb");
		itemPoisonHerb   = (new ItemHerb(itemIdPoisonHerb   - 256, EnumHerbType.POISON  )).setIconCoord(4, 0).setItemName("poisonherb");
		itemIvoryHerb    = (new ItemHerb(itemIdIvoryHerb    - 256, EnumHerbType.IVORY   )).setIconCoord(5, 0).setItemName("iboryherb");
		
		itemBlessedPotion = (new ItemMabiCraftParts(itemIdBlessedPotion - 256)).setIconCoord(1, 1).setItemName("blessdpotion");
		itemMagicPowder   = (new ItemMabiCraftParts(itemIdMagicPowder  - 256)).setIconCoord(0, 1).setItemName("magicpowder");
		
		itemEnchantScroll = (new ItemEnchantScroll(itemIdEnchantScroll - 256)).setIconCoord(0, 3).setItemName("enchantscroll");
		itemBonfireKit    = (new ItemBonfireKit(itemIdBonfireKit - 256)).setIconCoord(0, 4).setItemName("bonfirekit");
		itemEvilScroll    = (new ItemEvilScroll(itemIdEvilScroll - 256)).setIconCoord(0, 2).setItemName("evilscroll");
		
		blockBonfire   = (new BlockBonfire(blockIdBonfire, 31)).setBlockName("bonfire");
		blockEnchanter = (new BlockEnchanter(blockIdEnchanter, 166)).setBlockName("enchanter");
		GameRegistry.registerBlock(blockBonfire);
		GameRegistry.registerBlock(blockEnchanter);
		
		NetworkRegistry.instance().registerGuiHandler(this, proxy);
		
		VillagerRegistry.instance().registerVillageTradeHandler(0, new MabiCraftVillageTradeHandler().new FarmerTradeHandler());
		VillagerRegistry.instance().registerVillageTradeHandler(1, new MabiCraftVillageTradeHandler().new LibrarianTradeHandler());
		VillagerRegistry.instance().registerVillageTradeHandler(2, new MabiCraftVillageTradeHandler().new PriestTradeHandler());
		
		MinecraftForge.EVENT_BUS.register(new EntityLivingEventHooks());
		
		proxy.registerTextures();
		proxy.registerRenderers();
		this.addLocalization();
		
		GameRegistry.addRecipe(
			new ItemStack(itemBonfireKit, 1),
				new Object[]
				{
					" F ", "LLL",
					Character.valueOf('F'), Item.flintAndSteel,
					Character.valueOf('L'), Block.wood
				});
		
		GameRegistry.addRecipe(
			new ItemStack(blockEnchanter, 1),
				new Object[]
				{
					"DRD", "BBB", "WWW",
					Character.valueOf('D'), Item.diamond,
					Character.valueOf('R'), new ItemStack(Block.cloth, 1, 14),
					Character.valueOf('B'), Item.book,
					Character.valueOf('W'), Block.planks
				});
	}
	
	private void addLocalization()
	{
		TranslationRegistry.addLocalization(itemBaseHerb,     "Base Herb",     "ベースハーブ");
		TranslationRegistry.addLocalization(itemManaHerb,     "Mana Herb",     "マナハーブ");
		TranslationRegistry.addLocalization(itemBloodyHerb,   "Bloody Herb",   "ブラッディハーブ");
		TranslationRegistry.addLocalization(itemSunlightHerb, "Sunlight Herb", "サンライトハーブ");
		TranslationRegistry.addLocalization(itemPoisonHerb,   "Poison Herb",   "ポイズンハーブ");
		TranslationRegistry.addLocalization(itemIvoryHerb,    "Ivory Herb",    "アイボリーハーブ");	
		
		TranslationRegistry.addLocalization(blockBonfire,   "Bonfire", "たき火");
		TranslationRegistry.addLocalization(blockEnchanter, "Enchanter", "付呪台");
		
		TranslationRegistry.addLocalization(itemBlessedPotion, "Blessed Potion", "祝福ポーション");
		TranslationRegistry.addLocalization(itemEnchantScroll, "Enchant Scroll", "エンチャントスクロール");
		TranslationRegistry.addLocalization(itemMagicPowder,   "Magic Powder",   "魔法の粉");
		
		TranslationRegistry.addLocalization(itemBonfireKit, "Bonfire Kit", "たき火キット");
		TranslationRegistry.addLocalization(itemEvilScroll, "EvilScroll", "魔符");
		
	}
}