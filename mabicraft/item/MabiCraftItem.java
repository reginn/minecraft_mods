package rgn.mods.mabicraft.item;

import com.google.common.collect.Lists;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

import net.minecraftforge.oredict.OreDictionary;

import rgn.mods.mabicraft.config.Config;
import rgn.mods.mabicraft.item.cooking.CookingFoodDictionary;
import rgn.mods.mabicraft.item.cooking.FoodProperty;

public class MabiCraftItem
{
	public static Item itemEnchantScroll;

	public static Item itemBaseHerb;
	public static Item itemManaHerb;
	public static Item itemBloodyHerb;
	public static Item itemSunlightHerb;
	public static Item itemPoisonHerb;
	public static Item itemIvoryHerb;

	public static Item itemMagicPowder;
	public static Item itemBlessedPotion;
	public static Item itemBonfireKit;

	public static Item itemEvilScroll;

	public static Item itemCookingFood;
	public static Item itemCookingPot;

	public static Item itemMabiFishRod;

	public static void configure(Config config)
	{
		itemBaseHerb     = (new ItemHerb(config.itemIdBaseHerb     - 256, EnumHerbType.BASE    )).setIconCoord(0, 0).setItemName("baseherb").setCreativeTab(Config.tabMabiCraft);
		itemManaHerb     = (new ItemHerb(config.itemIdManaHerb     - 256, EnumHerbType.MANA    )).setIconCoord(1, 0).setItemName("manaherb").setCreativeTab(Config.tabMabiCraft);
		itemBloodyHerb   = (new ItemHerb(config.itemIdBloodyHerb   - 256, EnumHerbType.BLOODY  )).setIconCoord(2, 0).setItemName("bloodyherb").setCreativeTab(Config.tabMabiCraft);
		itemSunlightHerb = (new ItemHerb(config.itemIdSunlightHerb - 256, EnumHerbType.SUNLIGHT)).setIconCoord(3, 0).setItemName("sunlightherb").setCreativeTab(Config.tabMabiCraft);
		itemPoisonHerb   = (new ItemHerb(config.itemIdPoisonHerb   - 256, EnumHerbType.POISON  )).setIconCoord(4, 0).setItemName("poisonherb").setCreativeTab(Config.tabMabiCraft);
		itemIvoryHerb    = (new ItemHerb(config.itemIdIvoryHerb    - 256, EnumHerbType.IVORY   )).setIconCoord(5, 0).setItemName("iboryherb").setCreativeTab(Config.tabMabiCraft);

		itemBlessedPotion = (new ItemMabiCraftParts(config.itemIdBlessedPotion - 256)).setIconCoord(1, 1).setItemName("blessdpotion").setCreativeTab(Config.tabMabiCraft);
		itemMagicPowder   = (new ItemMabiCraftParts(config.itemIdMagicPowder   - 256)).setIconCoord(0, 1).setItemName("magicpowder").setCreativeTab(Config.tabMabiCraft);

		itemEnchantScroll = (new ItemEnchantScroll(config.itemIdEnchantScroll - 256)).setIconCoord(0, 3).setItemName("enchantscroll");
		itemBonfireKit    = (new ItemBonfireKit(config.itemIdBonfireKit - 256)).setIconCoord(0, 4).setItemName("bonfirekit").setCreativeTab(Config.tabMabiCraft);
		itemEvilScroll    = (new ItemEvilScroll(config.itemIdEvilScroll - 256)).setIconCoord(0, 2).setItemName("evilscroll").setCreativeTab(Config.tabMabiCraft);

		itemCookingFood   = (new ItemCookingFood(config.itemIdCookingFood - 256)).setIconCoord(0, 6).setItemName("cookingfood").setCreativeTab(Config.tabMabiCraft);
		itemCookingPot    = (new ItemMabiCraftParts(config.itemIdCookingPot - 256)).setIconCoord(1, 4).setItemName("cookingpot").setCreativeTab(Config.tabMabiCraft);

		itemMabiFishRod   = (new ItemMabiFishingRod(config.itemIdMabiFishRod - 256)).setIconCoord(5, 4).setItemName("mabifishrod").setCreativeTab(Config.tabMabiCraft);

		OreDictionary.registerOre("milk",  new ItemStack(Item.bucketMilk));
		OreDictionary.registerOre("cocoa", new ItemStack(Item.dyePowder, 1, 3));
		OreDictionary.registerOre("sugar", new ItemStack(Item.sugar));
		OreDictionary.registerOre("apple", new ItemStack(Item.appleRed));
		OreDictionary.registerOre("ice",   new ItemStack(Block.ice));

		CookingFoodDictionary.registerCookingFood(
			"foodChocolateMilk",
			new ItemStack(MabiCraftItem.itemCookingFood, 1, 0),
			new FoodProperty(1, 0.05F)
		);
		CookingFoodDictionary.registerCookingFood(
			"foodStrawberryMilk",
			new ItemStack(MabiCraftItem.itemCookingFood, 1, 1),
			new FoodProperty(1, 0.05F)
		);
		CookingFoodDictionary.registerCookingFood(
			"foodOrangeJuice",
			new ItemStack(MabiCraftItem.itemCookingFood, 1, 2),
			new FoodProperty(1, 0.05F),
			Lists.newArrayList(new PotionEffect(Potion.jump.id, 30, 0))
		);
		CookingFoodDictionary.registerCookingFood(
			"foodAppleJuice",
			new ItemStack(MabiCraftItem.itemCookingFood, 1, 3),
			new FoodProperty(1, 0.05F),
			Lists.newArrayList(new PotionEffect(Potion.digSpeed.id, 30, 0))
		);
		CookingFoodDictionary.registerCookingFood(
			"foodLemonJuice",
			new ItemStack(MabiCraftItem.itemCookingFood, 1, 4),
			new FoodProperty(1, 0.05F),
			Lists.newArrayList(new PotionEffect(Potion.moveSpeed.id, 30, 0))
		);
		CookingFoodDictionary.registerCookingFood(
			"foodCappuccino",
			new ItemStack(MabiCraftItem.itemCookingFood, 1, 5),
			new FoodProperty(1, 0.05F)
		);
		CookingFoodDictionary.registerCookingFood(
			"foodCafeMocha",
			new ItemStack(MabiCraftItem.itemCookingFood, 1, 6),
			new FoodProperty(1, 0.05F)
		);
		CookingFoodDictionary.registerCookingFood(
			"foodMacchiato",
			new ItemStack(MabiCraftItem.itemCookingFood, 1, 7),
			new FoodProperty(1, 0.05F)
		);
		CookingFoodDictionary.registerCookingFood(
			"foodIceCafeMocha",
			new ItemStack(MabiCraftItem.itemCookingFood, 1, 8),
			new FoodProperty(1, 0.05F)
		);
		CookingFoodDictionary.registerCookingFood(
			"foodIceMacchiato",
			new ItemStack(MabiCraftItem.itemCookingFood, 1, 9),
			new FoodProperty(1, 0.05F)
		);
		CookingFoodDictionary.registerCookingFood(
			"foodBnR",
			new ItemStack(MabiCraftItem.itemCookingFood, 1, 10),
			new FoodProperty(1, 0.05F)
		);
		CookingFoodDictionary.registerCookingFood(
			"foodKissOnTheLips",
			new ItemStack(MabiCraftItem.itemCookingFood, 1, 11),
			new FoodProperty(1, 0.05F)
		);
		CookingFoodDictionary.registerCookingFood(
			"foodScrewDriver",
			new ItemStack(MabiCraftItem.itemCookingFood, 1, 12),
			new FoodProperty(1, 0.05F)
		);
		CookingFoodDictionary.registerCookingFood(
			"foodBrifneRocks",
			new ItemStack(MabiCraftItem.itemCookingFood, 1, 13),
			new FoodProperty(1, 0.05F),
			Lists.newArrayList(
				new PotionEffect(Potion.heal.id, 10, 2),
				new PotionEffect(Potion.hunger.id, 60, 1)
			)
		);
		CookingFoodDictionary.registerCookingFood(
			"foodLeighenSling",
			new ItemStack(MabiCraftItem.itemCookingFood, 1, 14),
			new FoodProperty(1, 0.05F)
		);
		CookingFoodDictionary.registerCookingFood(
			"foodRedSunrise",
			new ItemStack(MabiCraftItem.itemCookingFood, 1, 15),
			new FoodProperty(1, 0.05F)
		);
		CookingFoodDictionary.registerCookingFood(
			"foodValesFire",
			new ItemStack(MabiCraftItem.itemCookingFood, 1, 16),
			new FoodProperty(1, 0.05F),
			Lists.newArrayList(
				new PotionEffect(Potion.damageBoost.id, 60, 1),
				new PotionEffect(Potion.hunger.id, 60, 1)
			)
		);

		//---------------- material
		CookingFoodDictionary.registerCookingFood(
			"fishBrifneCarp",
			new ItemStack(MabiCraftItem.itemCookingFood, 1, 128),
			new FoodProperty(2, 1.0F)
		);

		CookingFoodDictionary.registerCookingFood(
			"fishTaitinnCarp",
			new ItemStack(MabiCraftItem.itemCookingFood, 1, 129),
			new FoodProperty(2, 1.0F)
		);

		CookingFoodDictionary.registerCookingFood(
			"fishReinbowTrout",
			new ItemStack(MabiCraftItem.itemCookingFood, 1, 130),
			new FoodProperty(2, 1.0F)
		);

		CookingFoodDictionary.registerCookingFood(
			"fishGoldenScaleFish",
			new ItemStack(MabiCraftItem.itemCookingFood, 1, 131),
			new FoodProperty(2, 1.0F)
		);

		CookingFoodDictionary.registerCookingFood(
			"fishStripedMarlin",
			new ItemStack(MabiCraftItem.itemCookingFood, 1, 132),
			new FoodProperty(2, 1.0F)
		);

		CookingFoodDictionary.registerCookingFood(
			"fishSilkStripedMarlin",
			new ItemStack(MabiCraftItem.itemCookingFood, 1, 133),
			new FoodProperty(2, 1.0F)
		);

		CookingFoodDictionary.registerCookingFood(
			"fishOceanSunfish",
			new ItemStack(MabiCraftItem.itemCookingFood, 1, 134),
			new FoodProperty(2, 1.0F)
		);

		CookingFoodDictionary.registerCookingFood(
			"fishFlyingFish",
			new ItemStack(MabiCraftItem.itemCookingFood, 1, 135),
			new FoodProperty(2, 1.0F)
		);

		CookingFoodDictionary.registerCookingFood(
			"fishLamprey",
			new ItemStack(MabiCraftItem.itemCookingFood, 1, 136),
			new FoodProperty(2, 1.0F)
		);

		CookingFoodDictionary.registerCookingFood(
			"fishSweetFish",
			new ItemStack(MabiCraftItem.itemCookingFood, 1, 137),
			new FoodProperty(2, 1.0F)
		);

		CookingFoodDictionary.registerCookingFood(
			"fishCatfish",
			new ItemStack(MabiCraftItem.itemCookingFood, 1, 138),
			new FoodProperty(2, 1.0F)
		);

		CookingFoodDictionary.registerCookingFood(
			"plantStrawberry",
			new ItemStack(MabiCraftItem.itemCookingFood, 1, 144),
			new FoodProperty(1, 0.05F)
		);

		CookingFoodDictionary.registerCookingFood(
			"plantNuts",
			new ItemStack(MabiCraftItem.itemCookingFood, 1, 145),
			new FoodProperty(1, 0.05F)
		);
		CookingFoodDictionary.registerCookingFood(
			"plantLemon",
			new ItemStack(MabiCraftItem.itemCookingFood, 1, 146),
			new FoodProperty(1, 0.05F)
		);
		CookingFoodDictionary.registerCookingFood(
			"plantOrange",
			new ItemStack(MabiCraftItem.itemCookingFood, 1, 147),
			new FoodProperty(1, 0.05F)
		);
		CookingFoodDictionary.registerCookingFood(
			"liqueurBrifneWhisky",
			new ItemStack(MabiCraftItem.itemCookingFood, 1, 148),
			new FoodProperty(1, 0.05F)
		);
		CookingFoodDictionary.registerCookingFood(
			"liqueurValesWhisky",
			new ItemStack(MabiCraftItem.itemCookingFood, 1, 149),
			new FoodProperty(1, 0.05F)
		);
	}
}