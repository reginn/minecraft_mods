package rgn.mods.mabicraft.item.cooking;

import java.util.List;

import net.minecraft.item.ItemStack;

public class CookingRecipe
{
	private ItemStack output;
	private List<Ingredient> recipe;

	public CookingRecipe(ItemStack _output, List<Ingredient> _recipe)
	{
		this.recipe = _recipe;
		this.output = _output;
	}

	public boolean isMatch(List<Ingredient> input)
	{
		if (input.size() != this.recipe.size())
		{
			return false;
		}
		int numberOfIngredient = input.size();

		boolean[] isIngredientMatch = new boolean[numberOfIngredient];

		for (boolean tmp : isIngredientMatch)
		{
			tmp = false;
		}

		for (int idx = 0; idx < numberOfIngredient; ++idx)
		{
			for (Ingredient target : this.recipe)
			{
				if (!isIngredientEqual(target, input.get(idx)))
				{
					continue;
				}
				isIngredientMatch[idx] = true;
			}
		}

		boolean isSumOfIngredientMatch = true;

		for (boolean tmp : isIngredientMatch)
		{
			isSumOfIngredientMatch &= tmp;
		}

		return isSumOfIngredientMatch;
	}

	public ItemStack getOutput()
	{
		return this.output.copy();
	}

	private boolean isItemStackEqual(ItemStack target, ItemStack input)
	{
		if (input == null && target != null || input != null && target == null)
        {
            return false;
        }
        return (target.itemID == input.itemID && (target.getItemDamage() == input.getItemDamage()));
	}


	// input items size() always 1
	private boolean isIngredientEqual(Ingredient target, Ingredient input)
	{
		if (input == null && target != null || input != null && target == null)
		{
			return false;
		}

		boolean isItemMatch = false;

		ItemStack inputItem = input.getItems().get(0);
		for (ItemStack targetItem : target.getItems())
		{
			isItemMatch |= isItemStackEqual(targetItem, inputItem);
		}

		boolean isRatioMatch;
		isRatioMatch = isMatchRationInPlay(target.getRatio(), input.getRatio());

		return isItemMatch & isRatioMatch;
	}

	private boolean isMatchRationInPlay(int targetRatio, int inputRatio)
	{
		int play = this.getPlay(targetRatio);

		return targetRatio - play <= inputRatio && targetRatio + play >= inputRatio;
	}

	private int getPlay(int median)
	{
		int m = median > 50 ? 100 - median : median;

		int[] p = new int[]{3, 3, 5, 5, 7, 7};

		return p[(m / 10)];
	}
}