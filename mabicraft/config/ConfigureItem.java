package rgn.mods.mabicraft.config;

import com.google.common.collect.Lists;

import net.minecraft.src.*;

import net.minecraftforge.oredict.OreDictionary;

import rgn.mods.mabicraft.core.EnumHerbType;
import rgn.mods.mabicraft.core.MabiCraftItem;
import rgn.mods.mabicraft.core.ItemEvilScroll;
import rgn.mods.mabicraft.core.ItemHerb;
import rgn.mods.mabicraft.core.ItemMabiCraftParts;

import rgn.mods.mabicraft.enchant.ItemBonfireKit;
import rgn.mods.mabicraft.enchant.ItemEnchantScroll;

import rgn.mods.mabicraft.cook.CookingFoodDictionary;
import rgn.mods.mabicraft.cook.ItemCookingFood;
import rgn.mods.mabicraft.cook.FoodProperty;

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
		
		MabiCraftItem.itemCookingFood   = (new ItemCookingFood(Config.itemIdCookingFood)).setIconCoord(0, 6).setItemName("cookingfood");
		
		OreDictionary.registerOre("milk",  new ItemStack(Item.bucketMilk));
		OreDictionary.registerOre("cocoa", new ItemStack(Item.dyePowder, 1, 3));
		OreDictionary.registerOre("sugar", new ItemStack(Item.sugar));
		OreDictionary.registerOre("apple", new ItemStack(Item.appleRed));
		OreDictionary.registerOre("ice",   new ItemStack(Block.ice));
		
		CookingFoodDictionary.registerCookingFood
			(
				"foodChocolateMilk",  new ItemStack(MabiCraftItem.itemCookingFood, 1, 0), new FoodProperty(1, 0.05F)
			);
		CookingFoodDictionary.registerCookingFood
			(
				"foodStrawberryMilk", new ItemStack(MabiCraftItem.itemCookingFood, 1, 1), new FoodProperty(1, 0.05F)
			);
		CookingFoodDictionary.registerCookingFood
			(
				"foodOrangeJuice", new ItemStack(MabiCraftItem.itemCookingFood, 1, 2), new FoodProperty(1, 0.05F),
				Lists.newArrayList(new PotionEffect(Potion.jump.id, 30, 0))
			);
		CookingFoodDictionary.registerCookingFood
			(
				"foodAppleJuice", new ItemStack(MabiCraftItem.itemCookingFood, 1, 3), new FoodProperty(1, 0.05F),
				Lists.newArrayList(new PotionEffect(Potion.digSpeed.id, 30, 0))
			);
		CookingFoodDictionary.registerCookingFood
			(
				"foodLemonJuice", new ItemStack(MabiCraftItem.itemCookingFood, 1, 4), new FoodProperty(1, 0.05F),
				Lists.newArrayList(new PotionEffect(Potion.moveSpeed.id, 30, 0))
			);
		CookingFoodDictionary.registerCookingFood
			(
				"foodCappuccino", new ItemStack(MabiCraftItem.itemCookingFood, 1, 5), new FoodProperty(1, 0.05F)
			);
		CookingFoodDictionary.registerCookingFood
			(
				"foodCafeMocha", new ItemStack(MabiCraftItem.itemCookingFood, 1, 6), new FoodProperty(1, 0.05F)
			);
		CookingFoodDictionary.registerCookingFood
			(
				"foodMacchiato", new ItemStack(MabiCraftItem.itemCookingFood, 1, 7), new FoodProperty(1, 0.05F)
			);
		CookingFoodDictionary.registerCookingFood
			(
				"foodIceCafeMocha", new ItemStack(MabiCraftItem.itemCookingFood, 1, 8), new FoodProperty(1, 0.05F)
			);
		CookingFoodDictionary.registerCookingFood
			(
				"foodIceMacchiato", new ItemStack(MabiCraftItem.itemCookingFood, 1, 9), new FoodProperty(1, 0.05F)
			);
		CookingFoodDictionary.registerCookingFood
			(
				"foodBnR", new ItemStack(MabiCraftItem.itemCookingFood, 1, 10), new FoodProperty(1, 0.05F)
			);
		CookingFoodDictionary.registerCookingFood
			(
				"foodKissOnTheLips", new ItemStack(MabiCraftItem.itemCookingFood, 1, 11), new FoodProperty(1, 0.05F)
			);
		CookingFoodDictionary.registerCookingFood
			(
				"foodScrewDriver", new ItemStack(MabiCraftItem.itemCookingFood, 1, 12), new FoodProperty(1, 0.05F)
			);
		CookingFoodDictionary.registerCookingFood
			(
				"foodBrifneRocks", new ItemStack(MabiCraftItem.itemCookingFood, 1, 13), new FoodProperty(1, 0.05F),
				Lists.newArrayList(new PotionEffect(Potion.heal.id, 10, 2), new PotionEffect(Potion.hunger.id, 60, 1))
			);
		CookingFoodDictionary.registerCookingFood
			(
				"foodLeighenSling", new ItemStack(MabiCraftItem.itemCookingFood, 1, 14), new FoodProperty(1, 0.05F)
			);
		CookingFoodDictionary.registerCookingFood
			(
				"foodRedSunrise", new ItemStack(MabiCraftItem.itemCookingFood, 1, 15), new FoodProperty(1, 0.05F)
			);
		CookingFoodDictionary.registerCookingFood
			(
				"foodValesFire", new ItemStack(MabiCraftItem.itemCookingFood, 1, 16), new FoodProperty(1, 0.05F),
				Lists.newArrayList(new PotionEffect(Potion.damageBoost.id, 60, 1), new PotionEffect(Potion.hunger.id, 60, 1))
			);
		
		//----------------
		CookingFoodDictionary.registerCookingFood
			(
				"plantStrawberry", new ItemStack(MabiCraftItem.itemCookingFood, 1, 144), new FoodProperty(1, 0.05F)
			);
		
		CookingFoodDictionary.registerCookingFood
			(
				"plantNuts", new ItemStack(MabiCraftItem.itemCookingFood, 1, 145), new FoodProperty(1, 0.05F)
			);
		CookingFoodDictionary.registerCookingFood
			(
				"plantLemon", new ItemStack(MabiCraftItem.itemCookingFood, 1, 146), new FoodProperty(1, 0.05F)
			);
		CookingFoodDictionary.registerCookingFood
			(
				"plantOrange", new ItemStack(MabiCraftItem.itemCookingFood, 1, 147), new FoodProperty(1, 0.05F)
			);
		CookingFoodDictionary.registerCookingFood
			(
				"liqueurBrifneWhisky", new ItemStack(MabiCraftItem.itemCookingFood, 1, 148), new FoodProperty(1, 0.05F)
			);
		CookingFoodDictionary.registerCookingFood
			(
				"liqueurValesWhisky", new ItemStack(MabiCraftItem.itemCookingFood, 1, 149), new FoodProperty(1, 0.05F)
			);
		
		
	}

}