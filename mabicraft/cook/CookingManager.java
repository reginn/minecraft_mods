package rgn.mods.mabicraft.cook;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

import com.google.common.collect.Lists;

public final class CookingManager
{
	private static final CookingManager INSTANCE = new CookingManager();

	public static final String CATEGORY_COOKING_TABLE = "table";
	public static final String CATEGORY_COOKING_OVEN  = "oven";
	public static final String CATEGORY_COOKING_POT   = "pot";

	private List<CookingRecipe> useTableRecipe = Lists.newArrayList();
	private List<CookingRecipe> useOvenRecipe = Lists.newArrayList();
	private List<CookingRecipe> usePotRecipe = Lists.newArrayList();

	public static CookingManager instance()
	{
		return INSTANCE;
	}

	static
	{
		registerCookingOnUseTable
		(
			"foodChocolateMilk",
			Lists.newArrayList(new Ingredient("milk", 41), new Ingredient("cocoa", 42), new Ingredient("sugar", 17))
		);

		registerCookingOnUseTable
		(
			"foodStrawberryMilk",
			Lists.newArrayList(new Ingredient("milk", 65), new Ingredient("plantStrawberry", 30), new Ingredient("sugar", 5))
		);

		registerCookingOnUseTable
		(
			"foodOrangeJuice",
			Lists.newArrayList(new Ingredient("plantOrange", 73), new Ingredient("plantNuts", 19), new Ingredient("sugar", 8))
		);

		registerCookingOnUseTable
		(
			"foodAppleJuice",
			Lists.newArrayList(new Ingredient("apple", 73), new Ingredient("plantNuts", 20), new Ingredient("sugar", 7))
		);

		registerCookingOnUseTable
		(
			"foodLemonJuice",
			Lists.newArrayList(new Ingredient("plantLemon", 60), new Ingredient("plantNuts", 30), new Ingredient("sugar", 10))
		);

		registerCookingOnUseTable
		(
			"foodKissOnTheLips",
			Lists.newArrayList(new Ingredient("liqueurBrifneWhisky", 55), new Ingredient("foodLemonJuice", 35), new Ingredient("sugar", 10))
		);

		registerCookingOnUseTable
		(
			"foodScrewDriver",
			Lists.newArrayList(new Ingredient("foodOrangeJuice", 76), new Ingredient("liqueurBrifneWhisky", 19), new Ingredient("plantLemon", 5))
		);

		registerCookingOnUseTable
		(
			"foodRedSunrise",
			Lists.newArrayList(new Ingredient("foodOrangeJuice", 60), new Ingredient("liqueurBrifneWhisky", 25), new Ingredient("sugar", 15))
		);

		registerCookingOnUseTable
		(
			"foodBrifneRocks",
			Lists.newArrayList(new Ingredient("ice", 56), new Ingredient("liqueurBrifneWhisky", 39), new Ingredient("plantLemon", 5))
		);

		registerCookingOnUseTable
		(
			"foodValesFire",
			Lists.newArrayList(new Ingredient("liqueurValesWhisky", 75), new Ingredient("ice", 25))
		);
	}

	public static void registerCookingOnUseTable(String foodName, List<Ingredient> ingredients)
	{
		instance().doRegisterCooking(CATEGORY_COOKING_TABLE, foodName, ingredients);
	}

	public static void registerCookingOnUseOven(String foodName, List<Ingredient> ingredients)
	{
		instance().doRegisterCooking(CATEGORY_COOKING_OVEN, foodName, ingredients);
	}

	public static void registerCookingOnUsepot(String foodName, List<Ingredient> ingredients)
	{
		instance().doRegisterCooking(CATEGORY_COOKING_POT, foodName, ingredients);
	}

	private void doRegisterCooking(String category, String foodName, List<Ingredient> ingredients)
	{
		ItemStack result = CookingFoodDictionary.getCookingFood(foodName);

		if (category.equals(CATEGORY_COOKING_TABLE))
		{
			useTableRecipe.add(new CookingRecipe(result, ingredients));
		}
		else if (category.equals(CATEGORY_COOKING_OVEN))
		{
			useOvenRecipe.add(new CookingRecipe(result, ingredients));
		}
		else if (category.equals(CATEGORY_COOKING_POT))
		{
			usePotRecipe.add(new CookingRecipe(result, ingredients));
		}
	}

	// usually interface
	public ItemStack findMatchingRecipe(IInventory inventoryInput, List<Ingredient> input, int type, EntityPlayer player)
	{
		List<CookingRecipe> recipes =
			(type == 0 ? useTableRecipe : (type == 1 ? useOvenRecipe : usePotRecipe));

		if (!player.worldObj.isRemote)
		{
			for (CookingRecipe recipe : recipes)
			{
				if (recipe.isMatch(input))
				{
					for (int idx = 0; idx < input.size(); ++idx)
					{
						if (inventoryInput.getStackInSlot(idx) != null)
						{
							int stackSize = --inventoryInput.getStackInSlot(idx).stackSize;
							inventoryInput.setInventorySlotContents(idx, stackSize == 0 ? null : inventoryInput.getStackInSlot(idx));
						}
					}
					player.addChatMessage("Cooking Succeed");
					return recipe.getOutput();
				}
			}
		}
		player.addChatMessage("no match...");
		return null;
	}



}