package rgn.mods.elventools.item;

import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;

import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.MinecraftForge;

import rgn.mods.elventools.config.Config;

public class ElvenItem
{
	public static Item itemLeatherLongbow;
	public static Item itemCompositeLongbow;
	public static Item itemEnhancedLongbow;
	public static Item itemBoneCompositeBow;
	public static Item itemShadowBow;
	public static Item itemEndBow;
	public static Item itemFeatherBow;
	public static Item itemSteelBow;
	public static Item itemElvenBow;

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

	public static Item itemElvenSeedBag;

	public static EnumToolMaterial enumToolMaterialMithril = EnumHelper.addToolMaterial("MITHRIL", 2, 1024, 6.0F, 3,  0);
	public static EnumToolMaterial enumToolMaterialSilver  = EnumHelper.addToolMaterial("SILVER",  2, 1024, 6.0F, 3, 20);

	public static void configure(Config config)
	{
		itemLeatherLongbow   = (new ItemElvenBow(config.itemIdLeatherLongbow   - 256, EnumElvenBowType.LEATHER  )).setUnlocalizedName("itemLeatherLongbow").setCreativeTab(Config.tabElvenTools);
		itemCompositeLongbow = (new ItemElvenBow(config.itemIdCompositeLongbow - 256, EnumElvenBowType.COMPOSITE)).setUnlocalizedName("itemCompositeLongbow").setCreativeTab(Config.tabElvenTools);
		itemEnhancedLongbow  = (new ItemElvenBow(config.itemIdEnhancedLongbow  - 256, EnumElvenBowType.ENHANCED )).setUnlocalizedName("itemEnhancedLongbow").setCreativeTab(Config.tabElvenTools);
		itemBoneCompositeBow = (new ItemElvenBow(config.itemIdBoneCompositeBow - 256, EnumElvenBowType.BONE     )).disableCallEvent().setUnlocalizedName("itemBoneCompositeBow").setCreativeTab(Config.tabElvenTools);
		itemShadowBow        = (new ItemShadowBow(config.itemIdShadowBow       - 256, EnumElvenBowType.SHADOW   )).disableCallEvent().setUnlocalizedName("itemShadowBow").setCreativeTab(Config.tabElvenTools);
		itemEndBow           = (new ItemElvenBow(config.itemIdEndBow           - 256, EnumElvenBowType.END      )).disableCallEvent().setRare().setInfo("Bind Mob").setUnlocalizedName("itemEndBow").setCreativeTab(Config.tabElvenTools);
		itemFeatherBow       = (new ItemElvenBow(config.itemIdFeatherBow       - 256, EnumElvenBowType.FEATHER  )).disableCallEvent().setRare().setInfo("Slow Falling").setUnlocalizedName("itemFeatherBow").setCreativeTab(Config.tabElvenTools);
		itemSteelBow         = (new ItemSteelBow(config.itemIdSteelBow         - 256, EnumElvenBowType.STEEL    )).setChargeSpeedRatio(0.5F).setUnlocalizedName("itemSteelBow").setCreativeTab(Config.tabElvenTools);
		itemElvenBow         = (new ItemElvishBow(config.itemIdElvenBow        - 256, EnumElvenBowType.ELVEN    )).setChargeSpeedRatio(2.0F).setUnlocalizedName("itemElvenBow").setCreativeTab(Config.tabElvenTools);

		itemEbonyStick = (new ItemElvenParts(config.itemIdEbonyStick - 256)).setUnlocalizedName("itemEbonyStick").setCreativeTab(Config.tabElvenTools);

		itemEbonyBoat = (new ItemEbonyBoat(config.itemIdEbonyBoat - 256)).setUnlocalizedName("itemEbonyBoat").setCreativeTab(Config.tabElvenTools);

		itemTorchArrow = (new ItemElvenParts(config.itemIdTorchArrow  - 256)).setUnlocalizedName("itemTorchArrow").setCreativeTab(Config.tabElvenTools);
		itemRopeArrow  = (new ItemElvenParts(config.itemIdRopeArrow   - 256)).setUnlocalizedName("itemRopeArrow").setCreativeTab(Config.tabElvenTools);

		itemRopeEstablisher = (new ItemRopeEstablisher(config.itemIdRopeEstablisher - 256)).setUnlocalizedName("itemRopeEstablisher").setCreativeTab(Config.tabElvenTools);

		itemElvenShovelMithril  = (new ItemElvenShovel(config.itemIdElvenShovelMithril   - 256, enumToolMaterialMithril)).setUnlocalizedName("itemElvenShovelMithril").setCreativeTab(Config.tabElvenTools);
		itemElvenPickaxeMithril = (new ItemElvenPickaxe(config.itemIdElvenPickaxeMithril - 256, enumToolMaterialMithril)).setUnlocalizedName("itemElvenPickaxeMithril").setCreativeTab(Config.tabElvenTools);
		itemElvenAxeMithril     = (new ItemElvenAxe(config.itemIdElvenAxeMithril         - 256, enumToolMaterialMithril)).setUnlocalizedName("itemElvenAxeMithril").setCreativeTab(Config.tabElvenTools);
		itemElvenSwordMithril   = (new ItemElvenSword(config.itemIdElvenSwordMithril     - 256, enumToolMaterialMithril)).setUnlocalizedName("itemElvenSwordMithril").setCreativeTab(Config.tabElvenTools);

		itemElvenSickle    = (new ItemElvenSickle(config.itemIdElvenSickle - 256,       enumToolMaterialMithril)).setUnlocalizedName("itemElvenSickle").setCreativeTab(Config.tabElvenTools);
		itemElvenLumberAxe = (new ItemElvenLumberAxe(config.itemIdElvenLumberAxe - 256, enumToolMaterialMithril)).setUnlocalizedName("itemElvenLumberAxe").setCreativeTab(Config.tabElvenTools);

		itemElvenSeedBag = (new ItemElvenSeedBag(config.itemIdElvenSeedBag - 256)).setUnlocalizedName("itemSeedBag").setCreativeTab(Config.tabElvenTools);

		MinecraftForge.setToolClass(itemElvenShovelMithril, "shovel", 0);
		MinecraftForge.setToolClass(itemElvenPickaxeMithril, "pickaxe", 2);
		MinecraftForge.setToolClass(itemElvenAxeMithril, "axe", 0);

		MinecraftForge.setToolClass(itemElvenLumberAxe,  "axe", 0);
	}
}