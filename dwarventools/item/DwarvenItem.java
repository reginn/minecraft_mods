package rgn.mods.dwarventools.item;

import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;

import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.MinecraftForge;

import rgn.mods.dwarventools.DwarvenTools;
import rgn.mods.dwarventools.config.Config;

public class DwarvenItem
{
	public static Item itemIronBar;
	public static Item itemMithrilIngot;
	public static Item itemEbonyIngot;

	public static Item itemDwarvenShovelObsidian;
	public static Item itemDwarvenPickaxeObsidian;
	public static Item itemDwarvenAxeObsidian;
	public static Item itemDwarvenHoeObsidian;
	public static Item itemDwarvenSwordObsidian;

	public static Item itemDwarvenShovelLapislazuli;
	public static Item itemDwarvenPickaxeLapislazuli;
	public static Item itemDwarvenAxeLapislazuli;
	public static Item itemDwarvenHoeLapislazuli;
	public static Item itemDwarvenSwordLapislazuli;

	public static Item itemDwarvenShovelRedstone;
	public static Item itemDwarvenPickaxeRedstone;
	public static Item itemDwarvenAxeRedstone;
	public static Item itemDwarvenHoeRedstone;
	public static Item itemDwarvenSwordRedstone;

	public static Item itemDwarvenShovelMithril;
	public static Item itemDwarvenPickaxeMithril;
	public static Item itemDwarvenBattleaxeMithril;
	public static Item itemDwarvenBrokenSwordMithril;
	public static Item itemDwarvenSwordMithril;

	public static Item itemDwarvenShovelEbony;
	public static Item itemDwarvenPickaxeEbony;
	public static Item itemDwarvenBattleaxeEbony;
	public static Item itemDwarvenBrokenSwordEbony;
	public static Item itemDwarvenSwordEbony;

	public static Item itemDwarvenHelmetMithril;
	public static Item itemDwarvenPlateMithril;
	public static Item itemDwarvenLegsMithril;
	public static Item itemDwarvenBootMithril;

	public static Item itemDwarvenHelmetEbony;
	public static Item itemDwarvenPlateEbony;
	public static Item itemDwarvenLegsEbony;
	public static Item itemDwarvenBootEbony;

	public static EnumToolMaterial enumToolMaterialObsidian    = EnumHelper.addToolMaterial("OBSIDIAN",    1, 4096,  4.0F, 1, 0);
	public static EnumToolMaterial enumToolMaterialLapislazuli = EnumHelper.addToolMaterial("LAPISLAZULI", 2,  512,  6.0F, 2, 0);
	public static EnumToolMaterial enumToolMaterialRedstone    = EnumHelper.addToolMaterial("REDSTONE",    3, 1024,  8.0F, 3, 0);
	public static EnumToolMaterial enumToolMaterialMithril     = EnumHelper.addToolMaterial("MITHRIL",     2, 1024,  6.0F, 3, 0);
	public static EnumToolMaterial enumToolMaterialEbony       = EnumHelper.addToolMaterial("EBONY",       3, 2048,  8.0F, 3, 0);

	public static EnumArmorMaterial enumArmorMaterialMithril = EnumHelper.addArmorMaterial("MITHRIL", 15, new int[] {2, 6, 5, 2}, 0);
	public static EnumArmorMaterial enumArmorMaterialEbony   = EnumHelper.addArmorMaterial("EBONY",   33, new int[] {3, 8, 6, 3}, 0);

	public static void configure(Config config)
	{
		itemIronBar      = (new ItemDwarvenParts(config.itemIdIronBar      - 256)).setIconCoord( 5, 0).setItemName("itemIronBar").setCreativeTab(Config.tabDwarvenTools);
		itemMithrilIngot = (new ItemDwarvenParts(config.itemIdMithrilIngot - 256)).setIconCoord( 4, 5).setItemName("itemMithrilIngot").setCreativeTab(Config.tabDwarvenTools);
		itemEbonyIngot   = (new ItemDwarvenParts(config.itemIdEbonyIngot   - 256)).setIconCoord( 4, 6).setItemName("itemEbonyIngot").setCreativeTab(Config.tabDwarvenTools);

		itemDwarvenShovelObsidian  = (new ItemDwarvenShovel(config.itemIdDwarvenShovelObsidian   - 256, enumToolMaterialObsidian)).setIconCoord( 0, 0).setItemName("itemShovelObsidian").setCreativeTab(Config.tabDwarvenTools);
		itemDwarvenPickaxeObsidian = (new ItemDwarvenPickaxe(config.itemIdDwarvenPickaxeObsidian - 256, enumToolMaterialObsidian)).setIconCoord( 1, 0).setItemName("itemPickaxeObsidian").setCreativeTab(Config.tabDwarvenTools);
		itemDwarvenAxeObsidian     = (new ItemDwarvenAxe(config.itemIdDwarvenAxeObsidian         - 256, enumToolMaterialObsidian)).setIconCoord( 2, 0).setItemName("itemAxeObsidian").setCreativeTab(Config.tabDwarvenTools);
		itemDwarvenHoeObsidian     = (new ItemDwarvenHoe(config.itemIdDwarvenHoeObsidian         - 256, enumToolMaterialObsidian)).setIconCoord( 3, 0).setItemName("itemHoeObsidian").setCreativeTab(Config.tabDwarvenTools);
		itemDwarvenSwordObsidian   = (new ItemDwarvenSword(config.itemIdDwarvenSwordObsidian     - 256, enumToolMaterialObsidian)).setIconCoord( 4, 0).setItemName("itemSwordObsidian").setCreativeTab(Config.tabDwarvenTools);

		itemDwarvenShovelLapislazuli  = (new ItemDwarvenShovel(config.itemIdDwarvenShovelLapislazuli   - 256, enumToolMaterialLapislazuli)).setIconCoord( 0, 1).setItemName("itemShovelLapis").setCreativeTab(Config.tabDwarvenTools);
		itemDwarvenPickaxeLapislazuli = (new ItemDwarvenPickaxe(config.itemIdDwarvenPickaxeLapislazuli - 256, enumToolMaterialLapislazuli)).setIconCoord( 1, 1).setItemName("itemPickaxeLapis").setCreativeTab(Config.tabDwarvenTools);
		itemDwarvenAxeLapislazuli     = (new ItemDwarvenAxe(config.itemIdDwarvenAxeLapislazuli         - 256, enumToolMaterialLapislazuli)).setIconCoord( 2, 1).setItemName("itemAxeLapis").setCreativeTab(Config.tabDwarvenTools);
		itemDwarvenHoeLapislazuli     = (new ItemDwarvenHoe(config.itemIdDwarvenHoeLapislazuli         - 256, enumToolMaterialLapislazuli)).setIconCoord( 3, 1).setItemName("itemHoeLapis").setCreativeTab(Config.tabDwarvenTools);
		itemDwarvenSwordLapislazuli   = (new ItemDwarvenSword(config.itemIdDwarvenSwordLapislazuli     - 256, enumToolMaterialLapislazuli)).setIconCoord( 4, 1).setItemName("itemSwordLapis").setCreativeTab(Config.tabDwarvenTools);

		itemDwarvenShovelRedstone  = (new ItemDwarvenShovel(config.itemIdDwarvenShovelRedstone   - 256, enumToolMaterialRedstone)).setIconCoord( 0, 2).setItemName("itemShovelRedstone").setCreativeTab(Config.tabDwarvenTools);
		itemDwarvenPickaxeRedstone = (new ItemDwarvenPickaxe(config.itemIdDwarvenPickaxeRedstone - 256, enumToolMaterialRedstone)).setIconCoord( 1, 2).setItemName("itemPickaxeRedstone").setCreativeTab(Config.tabDwarvenTools);
		itemDwarvenAxeRedstone     = (new ItemDwarvenAxe(config.itemIdDwarvenAxeRedstone         - 256, enumToolMaterialRedstone)).setIconCoord( 2, 2).setItemName("itemAxeRedstone").setCreativeTab(Config.tabDwarvenTools);
		itemDwarvenHoeRedstone     = (new ItemDwarvenHoe(config.itemIdDwarvenHoeRedstone         - 256, enumToolMaterialRedstone)).setIconCoord( 3, 2).setItemName("itemHoeRedstone").setCreativeTab(Config.tabDwarvenTools);
		itemDwarvenSwordRedstone   = (new ItemDwarvenSword(config.itemIdDwarvenSwordRedstone     - 256, enumToolMaterialRedstone)).setIconCoord( 4, 2).setItemName("itemSwordRedstone").setCreativeTab(Config.tabDwarvenTools);

		itemDwarvenBrokenSwordMithril = (new ItemDwarvenParts(config.itemIdDwarvenBrokenSwordMithril - 256)).setIconCoord( 3, 3).setItemName("itemBrokenSwordMithril").setCreativeTab(Config.tabDwarvenTools);
		itemDwarvenBrokenSwordEbony   = (new ItemDwarvenParts(config.itemIdDwarvenBrokenSwordEbony - 256)).setIconCoord( 3, 4).setItemName("itemBrokenSwordEbony").setCreativeTab(Config.tabDwarvenTools);

		itemDwarvenShovelMithril      = (new ItemDwarvenShovel(config.itemIdDwarvenShovelMithril       - 256, enumToolMaterialMithril)).setIconCoord( 0, 3).setItemName("itemShovelMithril").setCreativeTab(Config.tabDwarvenTools);
		itemDwarvenPickaxeMithril     = (new ItemDwarvenPickaxe(config.itemIdDwarvenPickaxeMithril     - 256, enumToolMaterialMithril)).setIconCoord( 1, 3).setItemName("itemPickaxeMithril").setCreativeTab(Config.tabDwarvenTools);
		itemDwarvenBattleaxeMithril   = (new ItemDwarvenBattleaxe(config.itemIdDwarvenBattleaxeMithril - 256, enumToolMaterialMithril)).setIconCoord( 2, 3).setItemName("itemBattleaxeMithril").setCreativeTab(Config.tabDwarvenTools);
		itemDwarvenSwordMithril       = (new ItemDwarvenSword(config.itemIdDwarvenSwordMithril         - 256, enumToolMaterialMithril)).setIconCoord( 4, 3).setItemName("itemSwordMithril").setCreativeTab(Config.tabDwarvenTools);

		itemDwarvenShovelEbony      = (new ItemDwarvenShovel(config.itemIdDwarvenShovelEbony       - 256, enumToolMaterialEbony)).setIconCoord( 0, 4).setItemName("itemShovelEbony").setCreativeTab(Config.tabDwarvenTools);
		itemDwarvenPickaxeEbony     = (new ItemDwarvenPickaxe(config.itemIdDwarvenPickaxeEbony     - 256, enumToolMaterialEbony)).setIconCoord( 1, 4).setItemName("itemPickaxeEbony").setCreativeTab(Config.tabDwarvenTools);
		itemDwarvenBattleaxeEbony   = (new ItemDwarvenBattleaxe(config.itemIdDwarvenBattleaxeEbony - 256, enumToolMaterialEbony)).setIconCoord( 2, 4).setItemName("itemBattleaxeEbony").setCreativeTab(Config.tabDwarvenTools);
		itemDwarvenSwordEbony       = (new ItemDwarvenSword(config.itemIdDwarvenSwordEbony         - 256, enumToolMaterialEbony)).setIconCoord( 4, 4).setItemName("itemSwordEbony").setCreativeTab(Config.tabDwarvenTools);

		itemDwarvenHelmetMithril = (new ItemDwarvenArmor(config.itemIdDwarvenHelmetMithril - 256, enumArmorMaterialMithril, DwarvenTools.proxy.addArmor("MithrilArmor"), 0)).setIconCoord( 0, 5).setItemName("itemHelmentMithril").setCreativeTab(Config.tabDwarvenTools);
		itemDwarvenPlateMithril  = (new ItemDwarvenArmor(config.itemIdDwarvenPlateMithril  - 256, enumArmorMaterialMithril, DwarvenTools.proxy.addArmor("MithrilArmor"), 1)).setIconCoord( 1, 5).setItemName("itemPlateMithril").setCreativeTab(Config.tabDwarvenTools);
		itemDwarvenLegsMithril   = (new ItemDwarvenArmor(config.itemIdDwarvenLegsMithril   - 256, enumArmorMaterialMithril, DwarvenTools.proxy.addArmor("MithrilArmor"), 2)).setIconCoord( 2, 5).setItemName("itemLegsMithril").setCreativeTab(Config.tabDwarvenTools);
		itemDwarvenBootMithril   = (new ItemDwarvenArmor(config.itemIdDwarvenBootMithril   - 256, enumArmorMaterialMithril, DwarvenTools.proxy.addArmor("MithrilArmor"), 3)).setIconCoord( 3, 5).setItemName("itemBootMithril").setCreativeTab(Config.tabDwarvenTools);

		itemDwarvenHelmetEbony = (new ItemDwarvenArmor(config.itemIdDwarvenHelmetEbony - 256, enumArmorMaterialEbony, DwarvenTools.proxy.addArmor("EbonyArmor"), 0)).setIconCoord( 0, 6).setItemName("itemHelmentEbony").setCreativeTab(Config.tabDwarvenTools);
		itemDwarvenPlateEbony  = (new ItemDwarvenArmor(config.itemIdDwarvenPlateEbony  - 256, enumArmorMaterialEbony, DwarvenTools.proxy.addArmor("EbonyArmor"), 1)).setIconCoord( 1, 6).setItemName("itemPlateEbony").setCreativeTab(Config.tabDwarvenTools);
		itemDwarvenLegsEbony   = (new ItemDwarvenArmor(config.itemIdDwarvenLegsEbony   - 256, enumArmorMaterialEbony, DwarvenTools.proxy.addArmor("EbonyArmor"), 2)).setIconCoord( 2, 6).setItemName("itemLegsEbony").setCreativeTab(Config.tabDwarvenTools);
		itemDwarvenBootEbony   = (new ItemDwarvenArmor(config.itemIdDwarvenBootEbony   - 256, enumArmorMaterialEbony, DwarvenTools.proxy.addArmor("EbonyArmor"), 3)).setIconCoord( 3, 6).setItemName("itemBootEbony").setCreativeTab(Config.tabDwarvenTools);

		MinecraftForge.setToolClass(itemDwarvenShovelObsidian,    "shovel", 1);
		MinecraftForge.setToolClass(itemDwarvenShovelLapislazuli, "shovel", 2);
		MinecraftForge.setToolClass(itemDwarvenShovelRedstone,    "shovel", 3);
		MinecraftForge.setToolClass(itemDwarvenShovelMithril,     "shovel", 2);
		MinecraftForge.setToolClass(itemDwarvenShovelEbony,       "shovel", 3);

		MinecraftForge.setToolClass(itemDwarvenPickaxeObsidian,    "pickaxe", 1);
		MinecraftForge.setToolClass(itemDwarvenPickaxeLapislazuli, "pickaxe", 2);
		MinecraftForge.setToolClass(itemDwarvenPickaxeRedstone,    "pickaxe", 3);
		MinecraftForge.setToolClass(itemDwarvenPickaxeMithril,     "pickaxe", 2);
		MinecraftForge.setToolClass(itemDwarvenPickaxeEbony,       "pickaxe", 3);

		MinecraftForge.setToolClass(itemDwarvenAxeObsidian,    "axe", 1);
		MinecraftForge.setToolClass(itemDwarvenAxeLapislazuli, "axe", 2);
		MinecraftForge.setToolClass(itemDwarvenAxeRedstone,    "axe", 3);
	}
}