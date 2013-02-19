package rgn.mods.mabicraft.item.cooking;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public final class CookingManager
{
	private static final CookingManager INSTANCE = new CookingManager();

	public static final String CATEGORY_COOKING_TABLE = "table";
	public static final String CATEGORY_COOKING_POT   = "pot";

	private List<CookingRecipe> useTableRecipe = Lists.newArrayList();
	private List<CookingRecipe> usePotRecipe = Lists.newArrayList();

	public static CookingManager instance()
	{
		return INSTANCE;
	}

	private CookingManager()
	{
		(new RecipeTable()).addRecipes(this);
		(new RecipePot()).addRecipes(this);
	}

	public void registerCookingOnUseTable(String foodName, List<Ingredient> ingredients)
	{
		doRegisterCooking(CATEGORY_COOKING_TABLE, foodName, ingredients);
	}

	public void registerCookingOnUsePot(String foodName, List<Ingredient> ingredients)
	{
		doRegisterCooking(CATEGORY_COOKING_POT, foodName, ingredients);
	}

	private void doRegisterCooking(String category, String foodName, List<Ingredient> ingredients)
	{
		ItemStack result = CookingFoodDictionary.getCookingFood(foodName);

		if (category.equals(CATEGORY_COOKING_TABLE))
		{
			useTableRecipe.add(new CookingRecipe(result, ingredients));
		}
		else if (category.equals(CATEGORY_COOKING_POT))
		{
			usePotRecipe.add(new CookingRecipe(result, ingredients));
		}
	}

	// usually interface
	public ItemStack findMatchingRecipe(IInventory inventoryInput, List<Ingredient> input, int type, EntityPlayer player)
	{
		List<CookingRecipe> recipes = type == 0 ? useTableRecipe : usePotRecipe;

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