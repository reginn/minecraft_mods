package rgn.mods.mabicraft.item.enchanting;

import java.util.Map;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Maps;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;

public class EnchantmentDictionary
{
	private class EnchantmentRegistration
	{
		private String name;
		private int weight;
		private int maxLevel;
		private int rarity;

		public EnchantmentRegistration(String _name, int _weight, int _maxLevel)
		{
			this.name     = _name;
			this.weight   = _weight;
			this.maxLevel = _maxLevel;
			this.rarity   = (int)(10.0F * (1.0F / (float)_weight));
		}

		public String getName()
		{
			return this.name;
		}

		public int getWeight()
		{
			return this.weight;
		}

		public int getMaxLevel()
		{
			return this.maxLevel;
		}

		public int getRarity()
		{
			return this.rarity;
		}
	}

	private Map<Integer, EnchantmentRegistration> IDToRegistrationMapping = Maps.newHashMap();
	private BiMap<Integer, String>                      IDToNameBiMapping = HashBiMap.create();

	private static EnchantmentDictionary INSTANCE = new EnchantmentDictionary();
	
	public static EnchantmentDictionary instance()
	{
		return INSTANCE;
	}

	public static void registerEnchantment(int ID, String name, int wegiht, int maxLevel)
	{
		instance().doRegisterEnchantment(ID, name, wegiht, maxLevel);
	}

	public static void registerEnchantmentFromEnchantmentList()
	{
		for (Enchantment ench : Enchantment.enchantmentsList)
		{
			if (ench != null)
			{
				instance().doRegisterEnchantment(
					ench.effectId,
					ench.getName(),
					ench.getWeight(),
					ench.getMaxLevel()
				);
			}
		}
	}

	private void doRegisterEnchantment(int ID, String name, int wegiht, int maxLevel)
	{
		this.IDToRegistrationMapping.put(Integer.valueOf(ID), new EnchantmentRegistration(name, wegiht, maxLevel));
		this.IDToNameBiMapping.put(Integer.valueOf(ID), name);
	}

	public int getWeight(int enchantmentId)
	{
		return this.IDToRegistrationMapping.get(enchantmentId).getWeight();
	}

	public int getMaxLevel(int enchantmentId)
	{
		return this.IDToRegistrationMapping.get(enchantmentId).getMaxLevel();
	}

	public int getRarity(int enchantmentId)
	{
		return this.IDToRegistrationMapping.get(enchantmentId).getRarity();
	}

	public int getLevelingRarity(int enchantmentId, int lvl)
	{
		if (lvl > getMaxLevel(enchantmentId))
		{
			lvl = getMaxLevel(enchantmentId);
		}

		return (int)(10.0F * MathHelper.sqrt_float((float)lvl / (float)this.getMaxLevel(enchantmentId)));
	}

	public int getEnchantmentRarity(int enchantmentId, int lvl)
	{
		return this.getRarity(enchantmentId) + this.getLevelingRarity(enchantmentId, lvl);
	}

	public int getEnchantmentCorrection(int enchantmentId)
	{
		return this.getWeight(enchantmentId) + 1;
	}

	public boolean isFit(int enchantmentId, ItemStack itemstack)
	{
		return Enchantment.enchantmentsList[enchantmentId] != null
			&& Enchantment.enchantmentsList[enchantmentId].canApplyAtEnchantingTable(itemstack);
	}

	public int getEnchantmentIdFromName(String name)
	{
		return this.IDToNameBiMapping.inverse().get(name).intValue();
	}

	public String getEnchantmentNameFromID(int ID)
	{
		return this.IDToNameBiMapping.get(Integer.valueOf(ID));
	}

	/*
	static
	{
		registerEnchantment(0, "protection",            10, 4);
		registerEnchantment(1, "fire protection",        5, 4);
		registerEnchantment(2, "feather falling",        5, 4);
		registerEnchantment(3, "blast protection",       2, 4);
		registerEnchantment(4, "projectile protection",  5, 4);
		registerEnchantment(5, "respiration",            2, 3);
		registerEnchantment(6, "aqua affinity",          2, 1);

		registerEnchantment(16, "sharpness",          10, 5);
		registerEnchantment(17, "smite",               5, 5);
		registerEnchantment(18, "bane of arthropods",  5, 5);
		registerEnchantment(19, "knockback",           5, 2);
		registerEnchantment(20, "fire aspect",         2, 2);
		registerEnchantment(21, "looting",             2, 3);

		registerEnchantment(32, "efficiency", 10, 5);
		registerEnchantment(33, "silk touch",  1, 1);
		registerEnchantment(34, "unbreaking",  5, 3);
		registerEnchantment(35, "fortune",     2, 3);

		registerEnchantment(48, "power",    10, 5);
		registerEnchantment(49, "punch",     2, 2);
		registerEnchantment(50, "flame",     2, 1);
		registerEnchantment(51, "infinity",  1, 1);
	}
	*/
}