package rgn.mods.mabicraft.config;

import net.minecraft.src.*;

import rgn.mods.mabicraft.common.*;

public class ConfigureItem
{
	public static void init()
	{
		MabiCraftItem.itemBaseHerb     = (new ItemHerb(Config.itemIdBaseHerb    ,EnumHerbType.BASE    )).setIconCoord(0, 0).setItemName("baseherb");
		MabiCraftItem.itemManaHerb     = (new ItemHerb(Config.itemIdManaHerb    ,EnumHerbType.MANA    )).setIconCoord(1, 0).setItemName("manaherb");
		MabiCraftItem.itemBloodyHerb   = (new ItemHerb(Config.itemIdBloodyHerb  ,EnumHerbType.BLOODY  )).setIconCoord(2, 0).setItemName("bloodyherb");
		MabiCraftItem.itemSunlightHerb = (new ItemHerb(Config.itemIdSunlightHerb,EnumHerbType.SUNLIGHT)).setIconCoord(3, 0).setItemName("sunlightherb");
		MabiCraftItem.itemPoisonHerb   = (new ItemHerb(Config.itemIdPoisonHerb  ,EnumHerbType.POISON  )).setIconCoord(4, 0).setItemName("poisonherb");
		MabiCraftItem.itemIvoryHerb    = (new ItemHerb(Config.itemIdIvoryHerb   ,EnumHerbType.IVORY   )).setIconCoord(5, 0).setItemName("iboryherb");
		
		MabiCraftItem.itemBlessedPotion = (new ItemMabiCraftParts(Config.itemIdBlessedPotion)).setIconCoord(1, 1).setItemName("blessdpotion");
		MabiCraftItem.itemMagicPowder   = (new ItemMabiCraftParts(Config.itemIdMagicPowder  )).setIconCoord(0, 1).setItemName("magicpowder");
		
		MabiCraftItem.itemEnchantScroll = (new ItemEnchantScroll(Config.itemIdEnchantScroll)).setIconCoord(0, 3).setItemName("enchantscroll");
		MabiCraftItem.itemBonfireKit    = (new ItemBonfireKit(Config.itemIdBonfireKit)).setIconCoord(0, 4).setItemName("bonfirekit");
		MabiCraftItem.itemEvilScroll    = (new ItemEvilScroll(Config.itemIdEvilScroll)).setIconCoord(0, 2).setItemName("evilscroll");
	}

}