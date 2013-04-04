package rgn.mods.mabicraft.item.cooking;

import java.util.List;
import java.util.Map;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;

import net.minecraftforge.oredict.OreDictionary;

public class CookingFoodDictionary
{
	private static BiMap<String, ItemStack> dict = HashBiMap.create();

	private static List<String>    names = Lists.newArrayList();
	private static List<ItemStack> items = Lists.newArrayList();

	private static BiMap<Integer, String>                nameMapping = HashBiMap.create();
	private static Map<Integer, FoodProperty>         foodProperties = Maps.newHashMap();
	private static ListMultimap<Integer, PotionEffect> potionEffects = ArrayListMultimap.create();

	public static void registerCookingFood(String name, ItemStack itemstack, FoodProperty foodProperty)
	{
		dict.put(name, itemstack);
		OreDictionary.registerOre(name, itemstack);
		names.add(name);
		items.add(itemstack);
		foodProperties.put(itemstack.getItemDamage(), foodProperty);
		nameMapping.put(Integer.valueOf(itemstack.getItemDamage()), name);
	}

	public static void registerCookingFood(String name, ItemStack itemstack, FoodProperty foodProperty, List<PotionEffect> potionEffectList)
	{
		registerCookingFood(name, itemstack, foodProperty);
		int damage = itemstack.getItemDamage();

		for (PotionEffect pe : potionEffectList)
		{
			pe.combine(new PotionEffect(pe.getPotionID(), pe.getDuration() * 20, pe.getAmplifier()));
			potionEffects.put(damage, pe);
		}
	}

	public static ItemStack getCookingFood(String name)
	{
		if (dict.containsKey(name))
		{
			return dict.get(name);
		}
		return null;
	}

	public static String getName(ItemStack itemstack)
	{
		if (dict.containsValue(itemstack))
		{
			return dict.inverse().get(itemstack);
		}
		return null;
	}

	public static String getName(int damage)
	{
		if (nameMapping.containsKey(Integer.valueOf(damage)))
		{
			return nameMapping.get(Integer.valueOf(damage));
		}
		return null;
	}

	public static int getDamage(String name)
	{
		if (nameMapping.inverse().containsKey(name))
		{
			return nameMapping.inverse().get(name).intValue();
		}
		return -1;
	}

	public static List<String> getNames()
	{
		return names;
	}

	public static FoodProperty getFoodPropertyFromDamage(int damage)
	{
		if (foodProperties.containsKey(damage))
		{
			return foodProperties.get(damage);
		}
		return null;
	}

	public static List<PotionEffect> getPotionEffectsFromDamage(int damage)
	{
		if (potionEffects.containsKey(damage))
		{
			return potionEffects.get(damage);
		}
		return null;
	}

}