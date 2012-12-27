package rgn.mods.dwarventools.config;

import net.minecraftforge.common.MinecraftForge;
import rgn.mods.dwarventools.DwarvenTools;
import rgn.mods.dwarventools.core.DwarvenItem;
import rgn.mods.dwarventools.item.ItemDwarvenArmor;
import rgn.mods.dwarventools.item.ItemDwarvenAxe;
import rgn.mods.dwarventools.item.ItemDwarvenBattleaxe;
import rgn.mods.dwarventools.item.ItemDwarvenHoe;
import rgn.mods.dwarventools.item.ItemDwarvenParts;
import rgn.mods.dwarventools.item.ItemDwarvenPickaxe;
import rgn.mods.dwarventools.item.ItemDwarvenShovel;
import rgn.mods.dwarventools.item.ItemDwarvenSword;

public class ConfigureItem
{
	public static void init()
	{
		DwarvenItem.itemIronBar      = (new ItemDwarvenParts(Config.itemIdIronBar      - 256)).setIconCoord( 5, 0).setItemName("itemIronBar");
		DwarvenItem.itemMithrilIngot = (new ItemDwarvenParts(Config.itemIdMithrilIngot - 256)).setIconCoord( 4, 5).setItemName("itemMithrilIngot");
		DwarvenItem.itemEbonyIngot   = (new ItemDwarvenParts(Config.itemIdEbonyIngot   - 256)).setIconCoord( 4, 6).setItemName("itemEbonyIngot");

		DwarvenItem.itemDwarvenShovelObsidian  = (new ItemDwarvenShovel(Config.itemIdDwarvenShovelObsidian   - 256, ConfigureEnum.enumToolMaterialObsidian)).setIconCoord( 0, 0).setItemName("itemShovelObsidian");
		DwarvenItem.itemDwarvenPickaxeObsidian = (new ItemDwarvenPickaxe(Config.itemIdDwarvenPickaxeObsidian - 256, ConfigureEnum.enumToolMaterialObsidian)).setIconCoord( 1, 0).setItemName("itemPickaxeObsidian");
		DwarvenItem.itemDwarvenAxeObsidian     = (new ItemDwarvenAxe(Config.itemIdDwarvenAxeObsidian         - 256, ConfigureEnum.enumToolMaterialObsidian)).setIconCoord( 2, 0).setItemName("itemAxeObsidian");
		DwarvenItem.itemDwarvenHoeObsidian     = (new ItemDwarvenHoe(Config.itemIdDwarvenHoeObsidian         - 256, ConfigureEnum.enumToolMaterialObsidian)).setIconCoord( 3, 0).setItemName("itemHoeObsidian");
		DwarvenItem.itemDwarvenSwordObsidian   = (new ItemDwarvenSword(Config.itemIdDwarvenSwordObsidian     - 256, ConfigureEnum.enumToolMaterialObsidian)).setIconCoord( 4, 0).setItemName("itemSwordObsidian");

		DwarvenItem.itemDwarvenShovelLapislazuli  = (new ItemDwarvenShovel(Config.itemIdDwarvenShovelLapislazuli   - 256, ConfigureEnum.enumToolMaterialLapislazuli)).setIconCoord( 0, 1).setItemName("itemShovelLapis");
		DwarvenItem.itemDwarvenPickaxeLapislazuli = (new ItemDwarvenPickaxe(Config.itemIdDwarvenPickaxeLapislazuli - 256, ConfigureEnum.enumToolMaterialLapislazuli)).setIconCoord( 1, 1).setItemName("itemPickaxeLapis");
		DwarvenItem.itemDwarvenAxeLapislazuli     = (new ItemDwarvenAxe(Config.itemIdDwarvenAxeLapislazuli         - 256, ConfigureEnum.enumToolMaterialLapislazuli)).setIconCoord( 2, 1).setItemName("itemAxeLapis");
		DwarvenItem.itemDwarvenHoeLapislazuli     = (new ItemDwarvenHoe(Config.itemIdDwarvenHoeLapislazuli         - 256, ConfigureEnum.enumToolMaterialLapislazuli)).setIconCoord( 3, 1).setItemName("itemHoeLapis");
		DwarvenItem.itemDwarvenSwordLapislazuli   = (new ItemDwarvenSword(Config.itemIdDwarvenSwordLapislazuli     - 256, ConfigureEnum.enumToolMaterialLapislazuli)).setIconCoord( 4, 1).setItemName("itemSwordLapis");

		DwarvenItem.itemDwarvenShovelRedstone  = (new ItemDwarvenShovel(Config.itemIdDwarvenShovelRedstone   - 256, ConfigureEnum.enumToolMaterialRedstone)).setIconCoord( 0, 2).setItemName("itemShovelRedstone");
		DwarvenItem.itemDwarvenPickaxeRedstone = (new ItemDwarvenPickaxe(Config.itemIdDwarvenPickaxeRedstone - 256, ConfigureEnum.enumToolMaterialRedstone)).setIconCoord( 1, 2).setItemName("itemPickaxeRedstone");
		DwarvenItem.itemDwarvenAxeRedstone     = (new ItemDwarvenAxe(Config.itemIdDwarvenAxeRedstone         - 256, ConfigureEnum.enumToolMaterialRedstone)).setIconCoord( 2, 2).setItemName("itemAxeRedstone");
		DwarvenItem.itemDwarvenHoeRedstone     = (new ItemDwarvenHoe(Config.itemIdDwarvenHoeRedstone         - 256, ConfigureEnum.enumToolMaterialRedstone)).setIconCoord( 3, 2).setItemName("itemHoeRedstone");
		DwarvenItem.itemDwarvenSwordRedstone   = (new ItemDwarvenSword(Config.itemIdDwarvenSwordRedstone     - 256, ConfigureEnum.enumToolMaterialRedstone)).setIconCoord( 4, 2).setItemName("itemSwordRedstone");

		DwarvenItem.itemDwarvenBrokenSwordMithril = (new ItemDwarvenParts(Config.itemIdDwarvenBrokenSwordMithril - 256)).setIconCoord( 3, 3).setItemName("itemBrokenSwordMithril");
		DwarvenItem.itemDwarvenBrokenSwordEbony   = (new ItemDwarvenParts(Config.itemIdDwarvenBrokenSwordEbony - 256)).setIconCoord( 3, 4).setItemName("itemBrokenSwordEbony");

		DwarvenItem.itemDwarvenShovelMithril      = (new ItemDwarvenShovel(Config.itemIdDwarvenShovelMithril       - 256, ConfigureEnum.enumToolMaterialMithril)).setIconCoord( 0, 3).setItemName("itemShovelMithril");
		DwarvenItem.itemDwarvenPickaxeMithril     = (new ItemDwarvenPickaxe(Config.itemIdDwarvenPickaxeMithril     - 256, ConfigureEnum.enumToolMaterialMithril)).setIconCoord( 1, 3).setItemName("itemPickaxeMithril");
		DwarvenItem.itemDwarvenBattleaxeMithril   = (new ItemDwarvenBattleaxe(Config.itemIdDwarvenBattleaxeMithril - 256, ConfigureEnum.enumToolMaterialMithril)).setIconCoord( 2, 3).setItemName("itemBattleaxeMithril");
		DwarvenItem.itemDwarvenSwordMithril       = (new ItemDwarvenSword(Config.itemIdDwarvenSwordMithril         - 256, ConfigureEnum.enumToolMaterialMithril)).setIconCoord( 4, 3).setItemName("itemSwordMithril");

		DwarvenItem.itemDwarvenShovelEbony      = (new ItemDwarvenShovel(Config.itemIdDwarvenShovelEbony       - 256, ConfigureEnum.enumToolMaterialEbony)).setIconCoord( 0, 4).setItemName("itemShovelEbony");
		DwarvenItem.itemDwarvenPickaxeEbony     = (new ItemDwarvenPickaxe(Config.itemIdDwarvenPickaxeEbony     - 256, ConfigureEnum.enumToolMaterialEbony)).setIconCoord( 1, 4).setItemName("itemPickaxeEbony");
		DwarvenItem.itemDwarvenBattleaxeEbony   = (new ItemDwarvenBattleaxe(Config.itemIdDwarvenBattleaxeEbony - 256, ConfigureEnum.enumToolMaterialEbony)).setIconCoord( 2, 4).setItemName("itemBattleaxeEbony");
		DwarvenItem.itemDwarvenSwordEbony       = (new ItemDwarvenSword(Config.itemIdDwarvenSwordEbony         - 256, ConfigureEnum.enumToolMaterialEbony)).setIconCoord( 4, 4).setItemName("itemSwordEbony");

		DwarvenItem.itemDwarvenHelmetMithril = (new ItemDwarvenArmor(Config.itemIdDwarvenHelmetMithril - 256, ConfigureEnum.enumArmorMaterialMithril, DwarvenTools.proxy.addArmor("MithrilArmor"), 0)).setIconCoord( 0, 5).setItemName("itemHelmentMithril");
		DwarvenItem.itemDwarvenPlateMithril  = (new ItemDwarvenArmor(Config.itemIdDwarvenPlateMithril  - 256, ConfigureEnum.enumArmorMaterialMithril, DwarvenTools.proxy.addArmor("MithrilArmor"), 1)).setIconCoord( 1, 5).setItemName("itemPlateMithril");
		DwarvenItem.itemDwarvenLegsMithril   = (new ItemDwarvenArmor(Config.itemIdDwarvenLegsMithril   - 256, ConfigureEnum.enumArmorMaterialMithril, DwarvenTools.proxy.addArmor("MithrilArmor"), 2)).setIconCoord( 2, 5).setItemName("itemLegsMithril");
		DwarvenItem.itemDwarvenBootMithril   = (new ItemDwarvenArmor(Config.itemIdDwarvenBootMithril   - 256, ConfigureEnum.enumArmorMaterialMithril, DwarvenTools.proxy.addArmor("MithrilArmor"), 3)).setIconCoord( 3, 5).setItemName("itemBootMithril");

		DwarvenItem.itemDwarvenHelmetEbony = (new ItemDwarvenArmor(Config.itemIdDwarvenHelmetEbony - 256, ConfigureEnum.enumArmorMaterialEbony, DwarvenTools.proxy.addArmor("EbonyArmor"), 0)).setIconCoord( 0, 6).setItemName("itemHelmentEbony");
		DwarvenItem.itemDwarvenPlateEbony  = (new ItemDwarvenArmor(Config.itemIdDwarvenPlateEbony  - 256, ConfigureEnum.enumArmorMaterialEbony, DwarvenTools.proxy.addArmor("EbonyArmor"), 1)).setIconCoord( 1, 6).setItemName("itemPlateEbony");
		DwarvenItem.itemDwarvenLegsEbony   = (new ItemDwarvenArmor(Config.itemIdDwarvenLegsEbony   - 256, ConfigureEnum.enumArmorMaterialEbony, DwarvenTools.proxy.addArmor("EbonyArmor"), 2)).setIconCoord( 2, 6).setItemName("itemLegsEbony");
		DwarvenItem.itemDwarvenBootEbony   = (new ItemDwarvenArmor(Config.itemIdDwarvenBootEbony   - 256, ConfigureEnum.enumArmorMaterialEbony, DwarvenTools.proxy.addArmor("EbonyArmor"), 3)).setIconCoord( 3, 6).setItemName("itemBootEbony");

		MinecraftForge.setToolClass(DwarvenItem.itemDwarvenShovelObsidian,    "shovel", 1);
		MinecraftForge.setToolClass(DwarvenItem.itemDwarvenShovelLapislazuli, "shovel", 2);
		MinecraftForge.setToolClass(DwarvenItem.itemDwarvenShovelRedstone,    "shovel", 3);
		MinecraftForge.setToolClass(DwarvenItem.itemDwarvenShovelMithril,     "shovel", 2);
		MinecraftForge.setToolClass(DwarvenItem.itemDwarvenShovelEbony,       "shovel", 3);

		MinecraftForge.setToolClass(DwarvenItem.itemDwarvenPickaxeObsidian,    "pickaxe", 1);
		MinecraftForge.setToolClass(DwarvenItem.itemDwarvenPickaxeLapislazuli, "pickaxe", 2);
		MinecraftForge.setToolClass(DwarvenItem.itemDwarvenPickaxeRedstone,    "pickaxe", 3);
		MinecraftForge.setToolClass(DwarvenItem.itemDwarvenPickaxeMithril,     "pickaxe", 2);
		MinecraftForge.setToolClass(DwarvenItem.itemDwarvenPickaxeEbony,       "pickaxe", 3);

		MinecraftForge.setToolClass(DwarvenItem.itemDwarvenAxeObsidian,    "axe", 1);
		MinecraftForge.setToolClass(DwarvenItem.itemDwarvenAxeLapislazuli, "axe", 2);
		MinecraftForge.setToolClass(DwarvenItem.itemDwarvenAxeRedstone,    "axe", 3);
	}
}