package rgn.mods.mabicraft.enchant;

import java.util.Map;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Maps;

import net.minecraft.src.*;

public class EnchantmentRegistry
{
	protected class EnchantmentRegistration
	{
		private String name;
		private int correlation;
		private int maxLevel;
		private int baseRarity;
		
		public EnchantmentRegistration(String _name, int _correlation, int _maxLevel)
		{
			this.name        = _name;
			this.correlation = _correlation;
			this.maxLevel    = _maxLevel;
			this.baseRarity  = (int)(10.0F * (1.0F / (float)_correlation));
		}
		
		public String getName()
		{
			return this.name;
		}
		
		public int getCorrection()
		{
			return this.correlation;
		}
		
		public int getMaxLevel()
		{
			return this.maxLevel;
		}
		
		public int getRarity()
		{
			return this.baseRarity;
		}
	}
	
	private Map<Integer, EnchantmentRegistration> IDToRegistrationMapping = Maps.newHashMap();
	private BiMap<Integer, String> IDToNameBiMapping = HashBiMap.create();
	
	private static EnchantmentRegistry INSTANCE = new EnchantmentRegistry();
	
	public static EnchantmentRegistry instance()
	{
		return INSTANCE;
	}
	
	public static void registerEnchantment(int ID, String name, int correlation, int maxLevel)
	{
		instance().doRegisterEnchantment(ID, name, correlation, maxLevel);
	}
	
	private void doRegisterEnchantment(int ID, String name, int correlation, int maxLevel)
	{
		this.IDToRegistrationMapping.put(Integer.valueOf(ID), new EnchantmentRegistration(name, correlation, maxLevel));
		this.IDToNameBiMapping.put(Integer.valueOf(ID), name);
	}
	
	public int getCorrection(int enchantmentId)
	{
		return IDToRegistrationMapping.get(enchantmentId).getCorrection();
	}
	
	public int getMaxLevel(int enchantmentId)
	{
		return IDToRegistrationMapping.get(enchantmentId).getMaxLevel();
	}
	
	public int getBaseRarity(int enchantmentId)
	{
		return IDToRegistrationMapping.get(enchantmentId).getRarity();
	}
	
	public int getLevelingRarity(int enchantmentId, int lvl)
	{
		if (lvl > getMaxLevel(enchantmentId))
		{
			lvl = getMaxLevel(enchantmentId);
		}

		return (int)(10.0F * MathHelper.sqrt_float((float)lvl / (float)getMaxLevel(enchantmentId)));
	}
	
	public int getEnchantmentRarity(int enchantmentId, int lvl)
	{
		return this.getBaseRarity(enchantmentId) + this.getLevelingRarity(enchantmentId, lvl);
	}
	
	public int getEnchantmentCorrection(int enchantmentId)
	{
		return this.getCorrection(enchantmentId) + 1;
	}
	
	public boolean isFit(int enchantmentId, ItemStack itemstack)
	{
		return ((enchantmentId >=  0 && enchantmentId <=  6) && itemstack.getItem() instanceof ItemArmor)
			|| ((enchantmentId >= 16 && enchantmentId <= 21) && itemstack.getItem() instanceof ItemSword)
			|| ((enchantmentId >= 32 && enchantmentId <= 35) && itemstack.getItem() instanceof ItemPickaxe)
			|| ((enchantmentId >= 32 && enchantmentId <= 35) && itemstack.getItem() instanceof ItemAxe)
			|| ((enchantmentId >= 32 && enchantmentId <= 35) && itemstack.getItem() instanceof ItemSpade)
			|| ((enchantmentId >= 48 && enchantmentId <= 51) && itemstack.getItem() instanceof ItemBow);
	}
	
	public int getEnchantmentIdFromName(String name)
	{
		return IDToNameBiMapping.inverse().get(name).intValue();
	}
	
	public String getEnchantmentNameFromID(int ID)
	{
		return IDToNameBiMapping.get(Integer.valueOf(ID));
	}
	
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
}