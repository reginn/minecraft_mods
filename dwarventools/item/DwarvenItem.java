package rgn.mods.dwarventools.item;

import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemSword;

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
		itemIronBar      = (new Item(config.itemIdIronBar      - 256)).setUnlocalizedName("ironBar").setCreativeTab(Config.tabDwarvenTools).func_111206_d("rgn.dwarventools:ironBar");
		itemMithrilIngot = (new Item(config.itemIdMithrilIngot - 256)).setUnlocalizedName("mithrilIngot").setCreativeTab(Config.tabDwarvenTools).func_111206_d("rgn.dwarventools:mithrilIngot");
		itemEbonyIngot   = (new Item(config.itemIdEbonyIngot   - 256)).setUnlocalizedName("ebonyIngot").setCreativeTab(Config.tabDwarvenTools).func_111206_d("rgn.dwarventools:ebonyIngot");

		itemDwarvenShovelObsidian  = (new ItemSpade(config.itemIdDwarvenShovelObsidian    - 256, enumToolMaterialObsidian)).setUnlocalizedName("obsidianShovel").setCreativeTab(Config.tabDwarvenTools).func_111206_d("rgn.dwarventools:obsidianShovel");
		itemDwarvenPickaxeObsidian = (new ItemPickaxe(config.itemIdDwarvenPickaxeObsidian - 256, enumToolMaterialObsidian)).setUnlocalizedName("obsidianPickaxe").setCreativeTab(Config.tabDwarvenTools).func_111206_d("rgn.dwarventools:obsidianPickaxe");
		itemDwarvenAxeObsidian     = (new ItemAxe(config.itemIdDwarvenAxeObsidian         - 256, enumToolMaterialObsidian)).setUnlocalizedName("obsidianAxe").setCreativeTab(Config.tabDwarvenTools).func_111206_d("rgn.dwarventools:obsidianAxe");
		itemDwarvenHoeObsidian     = (new ItemHoe(config.itemIdDwarvenHoeObsidian         - 256, enumToolMaterialObsidian)).setUnlocalizedName("obsidianHoe").setCreativeTab(Config.tabDwarvenTools).func_111206_d("rgn.dwarventools:obsidianHoe");
		itemDwarvenSwordObsidian   = (new ItemSword(config.itemIdDwarvenSwordObsidian     - 256, enumToolMaterialObsidian)).setUnlocalizedName("obsidianSword").setCreativeTab(Config.tabDwarvenTools).func_111206_d("rgn.dwarventools:obsidianSword");

		itemDwarvenShovelLapislazuli  = (new ItemSpade(config.itemIdDwarvenShovelLapislazuli    - 256, enumToolMaterialLapislazuli)).setUnlocalizedName("lapisShovel").setCreativeTab(Config.tabDwarvenTools).func_111206_d("rgn.dwarventools:lapisShovel");
		itemDwarvenPickaxeLapislazuli = (new ItemPickaxe(config.itemIdDwarvenPickaxeLapislazuli - 256, enumToolMaterialLapislazuli)).setUnlocalizedName("lapisPickaxe").setCreativeTab(Config.tabDwarvenTools).func_111206_d("rgn.dwarventools:lapisPickaxe");
		itemDwarvenAxeLapislazuli     = (new ItemAxe(config.itemIdDwarvenAxeLapislazuli         - 256, enumToolMaterialLapislazuli)).setUnlocalizedName("lapisAxe").setCreativeTab(Config.tabDwarvenTools).func_111206_d("rgn.dwarventools:lapisAxe");
		itemDwarvenHoeLapislazuli     = (new ItemHoe(config.itemIdDwarvenHoeLapislazuli         - 256, enumToolMaterialLapislazuli)).setUnlocalizedName("lapisHoe").setCreativeTab(Config.tabDwarvenTools).func_111206_d("rgn.dwarventools:lapisHoe");
		itemDwarvenSwordLapislazuli   = (new ItemSword(config.itemIdDwarvenSwordLapislazuli     - 256, enumToolMaterialLapislazuli)).setUnlocalizedName("lapisSword").setCreativeTab(Config.tabDwarvenTools).func_111206_d("rgn.dwarventools:lapisSword");

		itemDwarvenShovelRedstone  = (new ItemSpade(config.itemIdDwarvenShovelRedstone    - 256, enumToolMaterialRedstone)).setUnlocalizedName("redShovel").setCreativeTab(Config.tabDwarvenTools).func_111206_d("rgn.dwarventools:redShovel");
		itemDwarvenPickaxeRedstone = (new ItemPickaxe(config.itemIdDwarvenPickaxeRedstone - 256, enumToolMaterialRedstone)).setUnlocalizedName("redPickaxe").setCreativeTab(Config.tabDwarvenTools).func_111206_d("rgn.dwarventools:redPickaxe");
		itemDwarvenAxeRedstone     = (new ItemAxe(config.itemIdDwarvenAxeRedstone         - 256, enumToolMaterialRedstone)).setUnlocalizedName("redAxe").setCreativeTab(Config.tabDwarvenTools).func_111206_d("rgn.dwarventools:redAxe");
		itemDwarvenHoeRedstone     = (new ItemHoe(config.itemIdDwarvenHoeRedstone         - 256, enumToolMaterialRedstone)).setUnlocalizedName("redHoe").setCreativeTab(Config.tabDwarvenTools).func_111206_d("rgn.dwarventools:redHoe");
		itemDwarvenSwordRedstone   = (new ItemSword(config.itemIdDwarvenSwordRedstone     - 256, enumToolMaterialRedstone)).setUnlocalizedName("redSword").setCreativeTab(Config.tabDwarvenTools).func_111206_d("rgn.dwarventools:redSword");

		itemDwarvenBrokenSwordMithril = (new Item(config.itemIdDwarvenBrokenSwordMithril - 256)).setUnlocalizedName("mithrilBrokenSword").setCreativeTab(Config.tabDwarvenTools).func_111206_d("rgn.dwarventools:mithrilBrokenSword");
		itemDwarvenBrokenSwordEbony   = (new Item(config.itemIdDwarvenBrokenSwordEbony   - 256)).setUnlocalizedName("ebonyBrokenSword").setCreativeTab(Config.tabDwarvenTools).func_111206_d("rgn.dwarventools:ebonyBrokenSword");

		itemDwarvenShovelMithril      = (new ItemDwarvenShovel(config.itemIdDwarvenShovelMithril       - 256, enumToolMaterialMithril)).setUnlocalizedName("mithrilShovel").setCreativeTab(Config.tabDwarvenTools).func_111206_d("rgn.dwarventools:mithrilShovel");
		itemDwarvenPickaxeMithril     = (new ItemDwarvenPickaxe(config.itemIdDwarvenPickaxeMithril     - 256, enumToolMaterialMithril)).setUnlocalizedName("mithrilPickaxe").setCreativeTab(Config.tabDwarvenTools).func_111206_d("rgn.dwarventools:mithrilPickaxe");
		itemDwarvenBattleaxeMithril   = (new ItemDwarvenBattleaxe(config.itemIdDwarvenBattleaxeMithril - 256, enumToolMaterialMithril)).setUnlocalizedName("mithrilBattleaxe").setCreativeTab(Config.tabDwarvenTools).func_111206_d("rgn.dwarventools:mithrilBattleaxe");
		itemDwarvenSwordMithril       = (new ItemDwarvenSword(config.itemIdDwarvenSwordMithril         - 256, enumToolMaterialMithril)).setUnlocalizedName("mithrilSword").setCreativeTab(Config.tabDwarvenTools).func_111206_d("rgn.dwarventools:mithrilSword");

		itemDwarvenShovelEbony      = (new ItemDwarvenShovel(config.itemIdDwarvenShovelEbony       - 256, enumToolMaterialEbony)).setUnlocalizedName("ebonyShovel").setCreativeTab(Config.tabDwarvenTools).func_111206_d("rgn.dwarventools:ebonyShovel");
		itemDwarvenPickaxeEbony     = (new ItemDwarvenPickaxe(config.itemIdDwarvenPickaxeEbony     - 256, enumToolMaterialEbony)).setUnlocalizedName("ebonyPickaxe").setCreativeTab(Config.tabDwarvenTools).func_111206_d("rgn.dwarventools:ebonyPickaxe");
		itemDwarvenBattleaxeEbony   = (new ItemDwarvenBattleaxe(config.itemIdDwarvenBattleaxeEbony - 256, enumToolMaterialEbony)).setUnlocalizedName("ebonyBattleaxe").setCreativeTab(Config.tabDwarvenTools).func_111206_d("rgn.dwarventools:ebonyBattleaxe");
		itemDwarvenSwordEbony       = (new ItemDwarvenSword(config.itemIdDwarvenSwordEbony         - 256, enumToolMaterialEbony)).setUnlocalizedName("ebonySword").setCreativeTab(Config.tabDwarvenTools).func_111206_d("rgn.dwarventools:ebonySword");

		itemDwarvenHelmetMithril = (new ItemDwarvenArmor(config.itemIdDwarvenHelmetMithril - 256, enumArmorMaterialMithril, DwarvenTools.proxy.addArmor("MithrilArmor"), 0)).setUnlocalizedName("mithrilHelmet").setCreativeTab(Config.tabDwarvenTools).func_111206_d("rgn.dwarventools:mithrilHelmet");
		itemDwarvenPlateMithril  = (new ItemDwarvenArmor(config.itemIdDwarvenPlateMithril  - 256, enumArmorMaterialMithril, DwarvenTools.proxy.addArmor("MithrilArmor"), 1)).setUnlocalizedName("mithrilPlate").setCreativeTab(Config.tabDwarvenTools).func_111206_d("rgn.dwarventools:mithrilPlate");
		itemDwarvenLegsMithril   = (new ItemDwarvenArmor(config.itemIdDwarvenLegsMithril   - 256, enumArmorMaterialMithril, DwarvenTools.proxy.addArmor("MithrilArmor"), 2)).setUnlocalizedName("mithrilLegs").setCreativeTab(Config.tabDwarvenTools).func_111206_d("rgn.dwarventools:mithrilLegs");
		itemDwarvenBootMithril   = (new ItemDwarvenArmor(config.itemIdDwarvenBootMithril   - 256, enumArmorMaterialMithril, DwarvenTools.proxy.addArmor("MithrilArmor"), 3)).setUnlocalizedName("mithrilBoot").setCreativeTab(Config.tabDwarvenTools).func_111206_d("rgn.dwarventools:mithrilBoot");

		itemDwarvenHelmetEbony = (new ItemDwarvenArmor(config.itemIdDwarvenHelmetEbony - 256, enumArmorMaterialEbony, DwarvenTools.proxy.addArmor("EbonyArmor"), 0)).setUnlocalizedName("ebonyHelmet").setCreativeTab(Config.tabDwarvenTools).func_111206_d("rgn.dwarventools:ebonyHelmet");
		itemDwarvenPlateEbony  = (new ItemDwarvenArmor(config.itemIdDwarvenPlateEbony  - 256, enumArmorMaterialEbony, DwarvenTools.proxy.addArmor("EbonyArmor"), 1)).setUnlocalizedName("ebonyPlate").setCreativeTab(Config.tabDwarvenTools).func_111206_d("rgn.dwarventools:ebonyPlate");
		itemDwarvenLegsEbony   = (new ItemDwarvenArmor(config.itemIdDwarvenLegsEbony   - 256, enumArmorMaterialEbony, DwarvenTools.proxy.addArmor("EbonyArmor"), 2)).setUnlocalizedName("ebonyLegs").setCreativeTab(Config.tabDwarvenTools).func_111206_d("rgn.dwarventools:ebonyLegs");
		itemDwarvenBootEbony   = (new ItemDwarvenArmor(config.itemIdDwarvenBootEbony   - 256, enumArmorMaterialEbony, DwarvenTools.proxy.addArmor("EbonyArmor"), 3)).setUnlocalizedName("ebonyBoot").setCreativeTab(Config.tabDwarvenTools).func_111206_d("rgn.dwarventools:ebonyBoot");

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