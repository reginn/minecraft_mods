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
		itemIronBar      = (new ItemDwarvenParts(config.itemIdIronBar      - 256)).setUnlocalizedName("ironBar").setCreativeTab(Config.tabDwarvenTools);
		itemMithrilIngot = (new ItemDwarvenParts(config.itemIdMithrilIngot - 256)).setUnlocalizedName("mithrilIngot").setCreativeTab(Config.tabDwarvenTools);
		itemEbonyIngot   = (new ItemDwarvenParts(config.itemIdEbonyIngot   - 256)).setUnlocalizedName("ebonyIngot").setCreativeTab(Config.tabDwarvenTools);

		itemDwarvenShovelObsidian  = (new ItemDwarvenShovel(config.itemIdDwarvenShovelObsidian   - 256, enumToolMaterialObsidian)).setUnlocalizedName("obsidianShovel").setCreativeTab(Config.tabDwarvenTools);
		itemDwarvenPickaxeObsidian = (new ItemDwarvenPickaxe(config.itemIdDwarvenPickaxeObsidian - 256, enumToolMaterialObsidian)).setUnlocalizedName("obsidianPickaxe").setCreativeTab(Config.tabDwarvenTools);
		itemDwarvenAxeObsidian     = (new ItemDwarvenAxe(config.itemIdDwarvenAxeObsidian         - 256, enumToolMaterialObsidian)).setUnlocalizedName("obsidianAxe").setCreativeTab(Config.tabDwarvenTools);
		itemDwarvenHoeObsidian     = (new ItemDwarvenHoe(config.itemIdDwarvenHoeObsidian         - 256, enumToolMaterialObsidian)).setUnlocalizedName("obsidianHoe").setCreativeTab(Config.tabDwarvenTools);
		itemDwarvenSwordObsidian   = (new ItemDwarvenSword(config.itemIdDwarvenSwordObsidian     - 256, enumToolMaterialObsidian)).setUnlocalizedName("obsidianSword").setCreativeTab(Config.tabDwarvenTools);

		itemDwarvenShovelLapislazuli  = (new ItemDwarvenShovel(config.itemIdDwarvenShovelLapislazuli   - 256, enumToolMaterialLapislazuli)).setUnlocalizedName("lapisShovel").setCreativeTab(Config.tabDwarvenTools);
		itemDwarvenPickaxeLapislazuli = (new ItemDwarvenPickaxe(config.itemIdDwarvenPickaxeLapislazuli - 256, enumToolMaterialLapislazuli)).setUnlocalizedName("lapisPickaxe").setCreativeTab(Config.tabDwarvenTools);
		itemDwarvenAxeLapislazuli     = (new ItemDwarvenAxe(config.itemIdDwarvenAxeLapislazuli         - 256, enumToolMaterialLapislazuli)).setUnlocalizedName("lapisAxe").setCreativeTab(Config.tabDwarvenTools);
		itemDwarvenHoeLapislazuli     = (new ItemDwarvenHoe(config.itemIdDwarvenHoeLapislazuli         - 256, enumToolMaterialLapislazuli)).setUnlocalizedName("lapisHoe").setCreativeTab(Config.tabDwarvenTools);
		itemDwarvenSwordLapislazuli   = (new ItemDwarvenSword(config.itemIdDwarvenSwordLapislazuli     - 256, enumToolMaterialLapislazuli)).setUnlocalizedName("lapisSword").setCreativeTab(Config.tabDwarvenTools);

		itemDwarvenShovelRedstone  = (new ItemDwarvenShovel(config.itemIdDwarvenShovelRedstone   - 256, enumToolMaterialRedstone)).setUnlocalizedName("redShovel").setCreativeTab(Config.tabDwarvenTools);
		itemDwarvenPickaxeRedstone = (new ItemDwarvenPickaxe(config.itemIdDwarvenPickaxeRedstone - 256, enumToolMaterialRedstone)).setUnlocalizedName("redPickaxe").setCreativeTab(Config.tabDwarvenTools);
		itemDwarvenAxeRedstone     = (new ItemDwarvenAxe(config.itemIdDwarvenAxeRedstone         - 256, enumToolMaterialRedstone)).setUnlocalizedName("redAxe").setCreativeTab(Config.tabDwarvenTools);
		itemDwarvenHoeRedstone     = (new ItemDwarvenHoe(config.itemIdDwarvenHoeRedstone         - 256, enumToolMaterialRedstone)).setUnlocalizedName("redHoe").setCreativeTab(Config.tabDwarvenTools);
		itemDwarvenSwordRedstone   = (new ItemDwarvenSword(config.itemIdDwarvenSwordRedstone     - 256, enumToolMaterialRedstone)).setUnlocalizedName("redSword").setCreativeTab(Config.tabDwarvenTools);

		itemDwarvenBrokenSwordMithril = (new ItemDwarvenParts(config.itemIdDwarvenBrokenSwordMithril - 256)).setUnlocalizedName("mithrilBrokenSword").setCreativeTab(Config.tabDwarvenTools);
		itemDwarvenBrokenSwordEbony   = (new ItemDwarvenParts(config.itemIdDwarvenBrokenSwordEbony - 256)).setUnlocalizedName("ebonyBrokenSword").setCreativeTab(Config.tabDwarvenTools);

		itemDwarvenShovelMithril      = (new ItemDwarvenShovel(config.itemIdDwarvenShovelMithril       - 256, enumToolMaterialMithril)).setUnlocalizedName("mithrilShovel").setCreativeTab(Config.tabDwarvenTools);
		itemDwarvenPickaxeMithril     = (new ItemDwarvenPickaxe(config.itemIdDwarvenPickaxeMithril     - 256, enumToolMaterialMithril)).setUnlocalizedName("mithrilPickaxe").setCreativeTab(Config.tabDwarvenTools);
		itemDwarvenBattleaxeMithril   = (new ItemDwarvenBattleaxe(config.itemIdDwarvenBattleaxeMithril - 256, enumToolMaterialMithril)).setUnlocalizedName("mithrilBattleaxe").setCreativeTab(Config.tabDwarvenTools);
		itemDwarvenSwordMithril       = (new ItemDwarvenSword(config.itemIdDwarvenSwordMithril         - 256, enumToolMaterialMithril)).setUnlocalizedName("mithrilSword").setCreativeTab(Config.tabDwarvenTools);

		itemDwarvenShovelEbony      = (new ItemDwarvenShovel(config.itemIdDwarvenShovelEbony       - 256, enumToolMaterialEbony)).setUnlocalizedName("ebonyShovel").setCreativeTab(Config.tabDwarvenTools);
		itemDwarvenPickaxeEbony     = (new ItemDwarvenPickaxe(config.itemIdDwarvenPickaxeEbony     - 256, enumToolMaterialEbony)).setUnlocalizedName("ebonyPickaxe").setCreativeTab(Config.tabDwarvenTools);
		itemDwarvenBattleaxeEbony   = (new ItemDwarvenBattleaxe(config.itemIdDwarvenBattleaxeEbony - 256, enumToolMaterialEbony)).setUnlocalizedName("ebonyBattleaxe").setCreativeTab(Config.tabDwarvenTools);
		itemDwarvenSwordEbony       = (new ItemDwarvenSword(config.itemIdDwarvenSwordEbony         - 256, enumToolMaterialEbony)).setUnlocalizedName("ebonySword").setCreativeTab(Config.tabDwarvenTools);

		itemDwarvenHelmetMithril = (new ItemDwarvenArmor(config.itemIdDwarvenHelmetMithril - 256, enumArmorMaterialMithril, DwarvenTools.proxy.addArmor("MithrilArmor"), 0)).setUnlocalizedName("mithrilHelmet").setCreativeTab(Config.tabDwarvenTools);
		itemDwarvenPlateMithril  = (new ItemDwarvenArmor(config.itemIdDwarvenPlateMithril  - 256, enumArmorMaterialMithril, DwarvenTools.proxy.addArmor("MithrilArmor"), 1)).setUnlocalizedName("mithrilPlate").setCreativeTab(Config.tabDwarvenTools);
		itemDwarvenLegsMithril   = (new ItemDwarvenArmor(config.itemIdDwarvenLegsMithril   - 256, enumArmorMaterialMithril, DwarvenTools.proxy.addArmor("MithrilArmor"), 2)).setUnlocalizedName("mithrilLegs").setCreativeTab(Config.tabDwarvenTools);
		itemDwarvenBootMithril   = (new ItemDwarvenArmor(config.itemIdDwarvenBootMithril   - 256, enumArmorMaterialMithril, DwarvenTools.proxy.addArmor("MithrilArmor"), 3)).setUnlocalizedName("mithrilBoot").setCreativeTab(Config.tabDwarvenTools);

		itemDwarvenHelmetEbony = (new ItemDwarvenArmor(config.itemIdDwarvenHelmetEbony - 256, enumArmorMaterialEbony, DwarvenTools.proxy.addArmor("EbonyArmor"), 0)).setUnlocalizedName("ebonyHelmet").setCreativeTab(Config.tabDwarvenTools);
		itemDwarvenPlateEbony  = (new ItemDwarvenArmor(config.itemIdDwarvenPlateEbony  - 256, enumArmorMaterialEbony, DwarvenTools.proxy.addArmor("EbonyArmor"), 1)).setUnlocalizedName("ebonyPlate").setCreativeTab(Config.tabDwarvenTools);
		itemDwarvenLegsEbony   = (new ItemDwarvenArmor(config.itemIdDwarvenLegsEbony   - 256, enumArmorMaterialEbony, DwarvenTools.proxy.addArmor("EbonyArmor"), 2)).setUnlocalizedName("ebonyLegs").setCreativeTab(Config.tabDwarvenTools);
		itemDwarvenBootEbony   = (new ItemDwarvenArmor(config.itemIdDwarvenBootEbony   - 256, enumArmorMaterialEbony, DwarvenTools.proxy.addArmor("EbonyArmor"), 3)).setUnlocalizedName("ebonyBoot").setCreativeTab(Config.tabDwarvenTools);

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