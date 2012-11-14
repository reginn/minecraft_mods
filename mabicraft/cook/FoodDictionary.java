package rgn.mods.mabicraft.cook;

import java.util.Map;

import com.google.common.collect.Maps;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import net.minecraft.src.*;
import net.minecraftforge.oredict.OreDictionary;

public final class FoodDictionary
{
	private BiMap<String, ItemStack> foodNameToItemStackBiMapping = HashBiMap.create();
	
	private static final FoodDictionary INSTANCE = new FoodDictionary();
	
	public static FoodDictionary instance()
	{
		return INSTANCE;
	}
	
	public static void registerFoodDictionary(String name, ItemStack itemstack)
	{
		instance().doRegisterFoodDictionary(name, itemstack);
	}

	private void doRegisterFoodDictionary(String name, ItemStack itemstack)
	{
		OreDictionary.registerOre(name, itemstack);
		foodNameToItemStackBiMapping.put(name, itemstack);
	}
	
	// interface
	public static ItemStack getItemStackFromFoodName(String foodName)
	{
		return instance().foodNameToItemStackBiMapping.get(foodName);
	}
	
	public String getFoodNameFromItemStack(ItemStack foodIS)
	{
		return instance().foodNameToItemStackBiMapping.inverse().get(foodIS);
	}
	
}