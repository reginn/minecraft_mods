package rgn.mods.elventools.config;

import net.minecraftforge.common.MinecraftForge;
import rgn.mods.elventools.core.ElvenItem;
import rgn.mods.elventools.item.ItemEbonyBoat;
import rgn.mods.elventools.item.ItemElvenAxe;
import rgn.mods.elventools.item.ItemElvenLumberAxe;
import rgn.mods.elventools.item.ItemElvenParts;
import rgn.mods.elventools.item.ItemElvenPickaxe;
import rgn.mods.elventools.item.ItemElvenShovel;
import rgn.mods.elventools.item.ItemElvenSickle;
import rgn.mods.elventools.item.ItemElvenSword;
import rgn.mods.elventools.item.ItemEnhancedBow;
import rgn.mods.elventools.item.ItemRopeEstablisher;

public class ConfigureItem
{
	public static void init()
	{
		ElvenItem.itemLeatherLongbow   = (new ItemEnhancedBow(Config.itemIdLeatherLongbow   - 256,  0,  512, 1.0F, 15)).setItemName("itemLeatherLongbow");
		ElvenItem.itemCompositeLongbow = (new ItemEnhancedBow(Config.itemIdCompositeLongbow - 256, 16,  768, 1.5F, 10)).setItemName("itemCompositeLongbow");
		ElvenItem.itemEnhancedLongbow  = (new ItemEnhancedBow(Config.itemIdEnhancedLongbow  - 256, 32, 4096, 2.0F,  1)).setItemName("itemEnhancedLongbow");

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