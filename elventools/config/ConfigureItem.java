package rgn.mods.elventools.config;

import net.minecraftforge.common.MinecraftForge;

import rgn.mods.elventools.item.ElvenItem;
import rgn.mods.elventools.item.ItemEbonyBoat;
import rgn.mods.elventools.item.ItemElvenAxe;
import rgn.mods.elventools.item.ItemElvenLumberAxe;
import rgn.mods.elventools.item.ItemElvenParts;
import rgn.mods.elventools.item.ItemElvenPickaxe;
import rgn.mods.elventools.item.ItemElvenShovel;
import rgn.mods.elventools.item.ItemElvenSickle;
import rgn.mods.elventools.item.ItemElvenSword;
import rgn.mods.elventools.item.ItemElvenBow;
import rgn.mods.elventools.item.ItemElvishBow;
import rgn.mods.elventools.item.ItemShadowBow;
import rgn.mods.elventools.item.ItemSteelBow;
import rgn.mods.elventools.item.ItemRopeEstablisher;
import rgn.mods.elventools.item.EnumElvenBowType;

public class ConfigureItem
{
	public static void init()
	{
		ElvenItem.itemLeatherLongbow   = (new ItemElvenBow(Config.itemIdLeatherLongbow   - 256, EnumElvenBowType.LEATHER  )).setItemName("itemLeatherLongbow");
		ElvenItem.itemCompositeLongbow = (new ItemElvenBow(Config.itemIdCompositeLongbow - 256, EnumElvenBowType.COMPOSITE)).setItemName("itemCompositeLongbow");
		ElvenItem.itemEnhancedLongbow  = (new ItemElvenBow(Config.itemIdEnhancedLongbow  - 256, EnumElvenBowType.ENHANCED )).setItemName("itemEnhancedLongbow");
		ElvenItem.itemBoneCompositeBow = (new ItemElvenBow(Config.itemIdBoneCompositeBow - 256, EnumElvenBowType.BONE     )).disableCallEvent().setItemName("itemBoneCompositeBow");
		ElvenItem.itemShadowBow        = (new ItemShadowBow(Config.itemIdShadowBow       - 256, EnumElvenBowType.SHADOW   )).disableCallEvent().setItemName("itemShadowBow");
		ElvenItem.itemEndBow           = (new ItemElvenBow(Config.itemIdEndBow           - 256, EnumElvenBowType.END      )).disableCallEvent().setItemName("itemEndBow");
		ElvenItem.itemFeatherBow       = (new ItemElvenBow(Config.itemIdFeatherBow       - 256, EnumElvenBowType.FEATHER  )).disableCallEvent().setChargeSpeedRatio(2.0F).setItemName("itemFeatherBow");
		ElvenItem.itemSteelBow         = (new ItemSteelBow(Config.itemIdSteelBow         - 256, EnumElvenBowType.STEEL    )).disableCallEvent().setChargeSpeedRatio(0.5F).setItemName("itemSteelBow");
		ElvenItem.itemElvenBow         = (new ItemElvishBow(Config.itemIdElvenBow        - 256, EnumElvenBowType.ELVEN    )).disableCallEvent().setChargeSpeedRatio(2.0F).setItemName("itemElvenBow");
		
		ElvenItem.itemEbonyStick = (new ItemElvenParts(Config.itemIdEbonyStick - 256)).setIconCoord(1, 4).setItemName("itemEbonyStick");

		ElvenItem.itemEbonyBoat = (new ItemEbonyBoat(Config.itemIdEbonyBoat - 256)).setIconCoord(0, 4).setItemName("itemEbonyBoat");

		ElvenItem.itemTorchArrow = (new ItemElvenParts(Config.itemIdTorchArrow  - 256)).setIconCoord(0, 5).setItemName("itemTorchArrow");
		ElvenItem.itemRopeArrow  = (new ItemElvenParts(Config.itemIdRopeArrow   - 256)).setIconCoord(1, 5).setItemName("itemRopeArrow");

		ElvenItem.itemRopeEstablisher = (new ItemRopeEstablisher(Config.itemIdRopeEstablisher - 256)).setIconCoord(2, 5).setItemName("itemRopeEstablisher");

		ElvenItem.itemElvenShovelMithril  = (new ItemElvenShovel(Config.itemIdElvenShovelMithril   - 256, ConfigureEnum.enumToolMaterialMithril)).setIconCoord(0, 3).setItemName("itemElvenShovelMithril");
		ElvenItem.itemElvenPickaxeMithril = (new ItemElvenPickaxe(Config.itemIdElvenPickaxeMithril - 256, ConfigureEnum.enumToolMaterialMithril)).setIconCoord(1, 3).setItemName("itemElvenPickaxeMithril");
		ElvenItem.itemElvenAxeMithril     = (new ItemElvenAxe(Config.itemIdElvenAxeMithril         - 256, ConfigureEnum.enumToolMaterialMithril)).setIconCoord(2, 3).setItemName("itemElvenAxeMithril");
		ElvenItem.itemElvenSwordMithril   = (new ItemElvenSword(Config.itemIdElvenSwordMithril     - 256, ConfigureEnum.enumToolMaterialMithril)).setIconCoord(3, 3).setItemName("itemElvenSwordMithril");

		ElvenItem.itemElvenSickle    = (new ItemElvenSickle(Config.itemIdElvenSickle - 256,       ConfigureEnum.enumToolMaterialMithril)).setIconCoord(4, 3).setItemName("itemElvenSickle");
		ElvenItem.itemElvenLumberAxe = (new ItemElvenLumberAxe(Config.itemIdElvenLumberAxe - 256, ConfigureEnum.enumToolMaterialMithril)).setIconCoord(2, 3).setItemName("itemElvenLumberAxe");

		MinecraftForge.setToolClass(ElvenItem.itemElvenShovelMithril, "shovel", 0);
		MinecraftForge.setToolClass(ElvenItem.itemElvenPickaxeMithril, "pickaxe", 2);
		MinecraftForge.setToolClass(ElvenItem.itemElvenAxeMithril, "axe", 0);

		MinecraftForge.setToolClass(ElvenItem.itemElvenLumberAxe,  "axe", 0);
	}
}