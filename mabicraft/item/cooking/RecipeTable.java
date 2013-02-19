package rgn.mods.mabicraft.item.cooking;

import com.google.common.collect.Lists;

public class RecipeTable
{
	public void addRecipes(CookingManager manager)
	{
		manager.registerCookingOnUseTable
		(
			"foodChocolateMilk",
			Lists.newArrayList(new Ingredient("milk", 41), new Ingredient("cocoa", 42), new Ingredient("sugar", 17))
		);

		manager.registerCookingOnUseTable
		(
			"foodStrawberryMilk",
			Lists.newArrayList(new Ingredient("milk", 65), new Ingredient("plantStrawberry", 30), new Ingredient("sugar", 5))
		);

		manager.registerCookingOnUseTable
		(
			"foodOrangeJuice",
			Lists.newArrayList(new Ingredient("plantOrange", 73), new Ingredient("plantNuts", 19), new Ingredient("sugar", 8))
		);

		manager.registerCookingOnUseTable
		(
			"foodAppleJuice",
			Lists.newArrayList(new Ingredient("apple", 73), new Ingredient("plantNuts", 20), new Ingredient("sugar", 7))
		);

		manager.registerCookingOnUseTable
		(
			"foodLemonJuice",
			Lists.newArrayList(new Ingredient("plantLemon", 60), new Ingredient("plantNuts", 30), new Ingredient("sugar", 10))
		);

		manager.registerCookingOnUseTable
		(
			"foodKissOnTheLips",
			Lists.newArrayList(new Ingredient("liqueurBrifneWhisky", 55), new Ingredient("foodLemonJuice", 35), new Ingredient("sugar", 10))
		);

		manager.registerCookingOnUseTable
		(
			"foodScrewDriver",
			Lists.newArrayList(new Ingredient("foodOrangeJuice", 76), new Ingredient("liqueurBrifneWhisky", 19), new Ingredient("plantLemon", 5))
		);

		manager.registerCookingOnUseTable
		(
			"foodRedSunrise",
			Lists.newArrayList(new Ingredient("foodOrangeJuice", 60), new Ingredient("liqueurBrifneWhisky", 25), new Ingredient("sugar", 15))
		);

		manager.registerCookingOnUseTable
		(
			"foodBrifneRocks",
			Lists.newArrayList(new Ingredient("ice", 56), new Ingredient("liqueurBrifneWhisky", 39), new Ingredient("plantLemon", 5))
		);

		manager.registerCookingOnUseTable
		(
			"foodValesFire",
			Lists.newArrayList(new Ingredient("liqueurValesWhisky", 75), new Ingredient("ice", 25))
		);
	}
}