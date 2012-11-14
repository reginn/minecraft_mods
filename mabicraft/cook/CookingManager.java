package rgn.mods.mabicraft.cook;

import java.util.List;

import com.google.common.collect.Lists;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.ArrayListMultimap;

import net.minecraft.src.*;
import net.minecraftforge.oredict.OreDictionary;


public final class CookingManager
{	
	private static final CookingManager INSTANCE = new CookingManager();
	
	private ListMultimap<String, Ingredient> foodNameToIngredientList = ArrayListMultimap.create();
	
	public static CookingManager instance()
	{
		return INSTANCE;
	}
	
	public static void registerCooking(String foodName, List<Ingredient> ingredients)
	{
		instance().doRegisterCooking(foodName, ingredients);
	}
	
	private void doRegisterCooking(String foodName, List<Ingredient> ingredients)
	{
		for (Ingredient ingredient : ingredients)
		{
			foodNameToIngredientList.put(foodName, ingredient);
		}
	}
	
	static
	{
		registerCooking("Strawberry Milk", 
			Lists.newArrayList(new Ingredient("milk", 65), new Ingredient("plantStrawberry", 30), new Ingredient("sugar", 5)));
	}
	
	public static ItemStack findMatchRecipeAndRatio(IInventory inventory, List ingredients)
	{
		if (ingredients.size() == 2)
		{
			return instance().doFindMatchRecipeAndRatio2(inventory, ingredients);
		}
		else
		{
			return instance().doFindMatchRecipeAndRatio3(inventory, ingredients);
		}
	}
	
	private ItemStack doFindMatchRecipeAndRatio2(IInventory inventory, List ingredients)
	{
		return null;
	}
	
	private ItemStack doFindMatchRecipeAndRatio3(IInventory inventory, List ingredients)
	{
		return null;
	}
}