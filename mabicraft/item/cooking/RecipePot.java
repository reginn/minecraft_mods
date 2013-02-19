package rgn.mods.mabicraft.item.cooking;

import com.google.common.collect.Lists;

public class RecipePot
{
	public void addRecipes(CookingManager manager)
	{
		manager.registerCookingOnUsePot
		(
			"foodChocolateMilk",
			Lists.newArrayList(new Ingredient("milk", 41), new Ingredient("cocoa", 42), new Ingredient("sugar", 17))
		);
	}
}