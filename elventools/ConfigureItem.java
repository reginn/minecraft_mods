package rgn.mods.elventools;

import net.minecraft.src.*;

import net.minecraftforge.common.MinecraftForge;

public class ConfigureItem
{
	public static void init()
	{
		ElvenItem.itemLeatherLongbow   = (new ItemEnhancedBow(Config.itemIdLeatherLongbow  ,  0,  512, 1.0F, 15)).setItemName("itemLeatherLongbow");
		ElvenItem.itemCompositeLongbow = (new ItemEnhancedBow(Config.itemIdCompositeLongbow, 16,  768, 1.5F, 10)).setItemName("itemCompositeLongbow");
		ElvenItem.itemEnhancedLongbow  = (new ItemEnhancedBow(Config.itemIdEnhancedLongbow , 32, 4096, 2.0F,  1)).setItemName("itemEnhancedLongbow");
		
		ElvenItem.itemEbonyStick = (new ItemElvenParts(Config.itemIdEbonyStick)).setIconCoord(1, 4).setItemName("itemEbonyStick");
		
		ElvenItem.itemEbonyBoat = (new ItemEbonyBoat(Config.itemIdEbonyBoat)).setIconCoord(0, 4).setItemName("itemEbonyBoat");
		
		ElvenItem.itemTorchArrow = (new ItemElvenParts(Config.itemIdTorchArrow )).setIconCoord(0, 5).setItemName("itemTorchArrow").setCreativeTab(CreativeTabs.tabCombat);
		ElvenItem.itemRopeArrow  = (new ItemElvenParts(Config.itemIdRopeArrow  )).setIconCoord(1, 5).setItemName("itemRopeArrow").setCreativeTab(CreativeTabs.tabCombat);
		
		ElvenItem.itemRopeEstablisher = (new ItemRopeEstablisher(Config.itemIdRopeEstablisher)).setIconCoord(2, 5).setItemName("itemRopeEstablisher");
		
		ElvenItem.itemElvenShovelMithril  = (new ItemElvenShovel(Config.itemIdElvenShovelMithril  , ConfigureEnum.enumToolMaterialMithril)).setIconCoord(0, 3).setItemName("itemElvenShovelMithril");
		ElvenItem.itemElvenPickaxeMithril = (new ItemElvenPickaxe(Config.itemIdElvenPickaxeMithril, ConfigureEnum.enumToolMaterialMithril)).setIconCoord(1, 3).setItemName("itemElvenPickaxeMithril");
		ElvenItem.itemElvenAxeMithril     = (new ItemElvenAxe(Config.itemIdElvenAxeMithril        , ConfigureEnum.enumToolMaterialMithril)).setIconCoord(2, 3).setItemName("itemElvenAxeMithril");
		ElvenItem.itemElvenSwordMithril   = (new ItemElvenSword(Config.itemIdElvenSwordMithril    , ConfigureEnum.enumToolMaterialMithril)).setIconCoord(3, 3).setItemName("itemElvenSwordMithril");
		
		ElvenItem.itemElvenSickle    = (new ItemElvenSickle(Config.itemIdElvenSickle,       ConfigureEnum.enumToolMaterialMithril)).setIconCoord(4, 3).setItemName("itemElvenSickle");
		ElvenItem.itemElvenLumberAxe = (new ItemElvenLumberAxe(Config.itemIdElvenLumberAxe, ConfigureEnum.enumToolMaterialMithril)).setIconCoord(2, 3).setItemName("itemElvenLumberAxe");
		
		MinecraftForge.setToolClass(ElvenItem.itemElvenShovelMithril, "shovel", 0);
		MinecraftForge.setToolClass(ElvenItem.itemElvenPickaxeMithril, "pickaxe", 2);		
		MinecraftForge.setToolClass(ElvenItem.itemElvenAxeMithril, "axe", 0);

		MinecraftForge.setToolClass(ElvenItem.itemElvenLumberAxe,  "axe", 0);		
	}
}