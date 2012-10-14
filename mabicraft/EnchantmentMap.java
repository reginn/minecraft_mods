package rgn.mods.mabicraft;

import java.lang.Math;

import java.util.Map;
import java.util.HashMap;

import net.minecraft.src.*;

public class EnchantmentMap
{
	private static Map<Integer, Integer> enchantmentCorrectionTable = new HashMap<Integer, Integer>();
	private static Map<Integer, Integer> enchantmentLevelTable      = new HashMap<Integer, Integer>();
	private static Map<String,  Integer> enchantmentNameTable       = new HashMap<String,  Integer>();
	
	public static int getCorrection(int enchantmentId)
	{
		if (!enchantmentCorrectionTable.isEmpty() && enchantmentCorrectionTable.containsKey(new Integer(enchantmentId)))
		{
			return enchantmentCorrectionTable.get(new Integer(enchantmentId)).intValue();
		}
		else
		{
			return 0;
		}
	}
	
	public static int getMaxLevel(int enchantmentId)
	{
		if (!enchantmentLevelTable.isEmpty() && enchantmentLevelTable.containsKey(new Integer(enchantmentId)))
		{
			return enchantmentLevelTable.get(new Integer(enchantmentId)).intValue();
		}
		else
		{
			return 0;
		}
	}
	
	public static int getEnchantmentRarity(int enchantmentId, int lvl)
	{
		if (lvl > getMaxLevel(enchantmentId))
		{
			lvl = getMaxLevel(enchantmentId);
		}
		return (int)(10.0F * (1.0F / (float)getEnchantmentCorrection(enchantmentId))) + (int)(10.0F * MathHelper.sqrt_float((float)lvl / (float)getMaxLevel(enchantmentId)));
	}
	
	public static int getEnchantmentCorrection(int enchantmentId)
	{
		return getCorrection(enchantmentId) + 1;
	}
	
	public static boolean isFit(int enchantmentId, ItemStack itemstack)
	{
		return ((enchantmentId >=  0 && enchantmentId <=  6) && itemstack.getItem() instanceof ItemArmor)
			|| ((enchantmentId >= 16 && enchantmentId <= 21) && itemstack.getItem() instanceof ItemSword)
			|| ((enchantmentId >= 32 && enchantmentId <= 35) && itemstack.getItem() instanceof ItemPickaxe)
			|| ((enchantmentId >= 32 && enchantmentId <= 35) && itemstack.getItem() instanceof ItemAxe)
			|| ((enchantmentId >= 32 && enchantmentId <= 35) && itemstack.getItem() instanceof ItemSpade)
			|| ((enchantmentId >= 48 && enchantmentId <= 51) && itemstack.getItem() instanceof ItemBow);
	}
	
	public static int getEnchantmentIdFromName(String name)
	{
		if (!enchantmentNameTable.isEmpty() && enchantmentNameTable.containsKey(name))
		{
			return enchantmentNameTable.get(name).intValue();
		}
		else
		{
			return -1;
		}
	}
	
	static
	{
		enchantmentCorrectionTable.put(new Integer( 0), new Integer(10)); // protection
		enchantmentCorrectionTable.put(new Integer( 1), new Integer( 5)); // fire protection
		enchantmentCorrectionTable.put(new Integer( 2), new Integer( 5)); // feather falling
		enchantmentCorrectionTable.put(new Integer( 3), new Integer( 2)); // blast protection
		enchantmentCorrectionTable.put(new Integer( 4), new Integer( 5)); // projectile protection
		enchantmentCorrectionTable.put(new Integer( 5), new Integer( 2)); // respiration
		enchantmentCorrectionTable.put(new Integer( 6), new Integer( 2)); // aqua affinity
		
		enchantmentCorrectionTable.put(new Integer(16), new Integer(10)); // sharpness
		enchantmentCorrectionTable.put(new Integer(17), new Integer( 5)); // smite
		enchantmentCorrectionTable.put(new Integer(18), new Integer( 5)); // bane of arthropods
		enchantmentCorrectionTable.put(new Integer(19), new Integer( 5)); // knockback
		enchantmentCorrectionTable.put(new Integer(20), new Integer( 2)); // fire aspect
		enchantmentCorrectionTable.put(new Integer(21), new Integer( 2)); // looting
		
		enchantmentCorrectionTable.put(new Integer(32), new Integer(10)); // efficienty
		enchantmentCorrectionTable.put(new Integer(33), new Integer( 1)); // silk touch
		enchantmentCorrectionTable.put(new Integer(34), new Integer( 5)); // unbreaking
		enchantmentCorrectionTable.put(new Integer(35), new Integer( 2)); // fortune
		
		enchantmentCorrectionTable.put(new Integer(48), new Integer(10)); // power
		enchantmentCorrectionTable.put(new Integer(49), new Integer( 2)); // punch
		enchantmentCorrectionTable.put(new Integer(50), new Integer( 2)); // flame
		enchantmentCorrectionTable.put(new Integer(51), new Integer( 1)); // infinity
		
		enchantmentLevelTable.put(new Integer( 0), new Integer(4)); // protection
		enchantmentLevelTable.put(new Integer( 1), new Integer(4)); // fire protection
		enchantmentLevelTable.put(new Integer( 2), new Integer(4)); // feather falling
		enchantmentLevelTable.put(new Integer( 3), new Integer(4)); // blast protection
		enchantmentLevelTable.put(new Integer( 4), new Integer(4)); // projectile protection
		enchantmentLevelTable.put(new Integer( 5), new Integer(3)); // respiration
		enchantmentLevelTable.put(new Integer( 6), new Integer(1)); // aqua affinity
		
		enchantmentLevelTable.put(new Integer(16), new Integer(5)); // sharpness
		enchantmentLevelTable.put(new Integer(17), new Integer(5)); // smite
		enchantmentLevelTable.put(new Integer(18), new Integer(5)); // bane of arthropods
		enchantmentLevelTable.put(new Integer(19), new Integer(2)); // knockback
		enchantmentLevelTable.put(new Integer(20), new Integer(2)); // fire aspect
		enchantmentLevelTable.put(new Integer(21), new Integer(3)); // looting
		
		enchantmentLevelTable.put(new Integer(32), new Integer(5)); // efficienty
		enchantmentLevelTable.put(new Integer(33), new Integer(3)); // silk touch
		enchantmentLevelTable.put(new Integer(34), new Integer(3)); // unbreaking
		enchantmentLevelTable.put(new Integer(35), new Integer(1)); // fortune
		
		enchantmentLevelTable.put(new Integer(48), new Integer(5)); // power
		enchantmentLevelTable.put(new Integer(49), new Integer(2)); // punch
		enchantmentLevelTable.put(new Integer(50), new Integer(1)); // flame
		enchantmentLevelTable.put(new Integer(51), new Integer(1)); // infinity
		
		enchantmentNameTable.put("protection",            new Integer( 0)); // protection
		enchantmentNameTable.put("fire protection",       new Integer( 1)); // fire protection
		enchantmentNameTable.put("feather falling",       new Integer( 2)); // feather falling
		enchantmentNameTable.put("blast protection",      new Integer( 3)); // blast protection
		enchantmentNameTable.put("projectile protection", new Integer( 4)); // projectile protection
		enchantmentNameTable.put("respiration",           new Integer( 5)); // respiration
		enchantmentNameTable.put("aqua affinity",         new Integer( 6)); // aqua affinity
		
		enchantmentNameTable.put("sharpness",          new Integer(16)); // sharpness
		enchantmentNameTable.put("smite",              new Integer(17)); // smite
		enchantmentNameTable.put("bane of arthropods", new Integer(18)); // bane of arthropods
		enchantmentNameTable.put("knockback",          new Integer(19)); // knockback
		enchantmentNameTable.put("fire aspect",        new Integer(20)); // fire aspect
		enchantmentNameTable.put("looting",            new Integer(21)); // looting
		
		enchantmentNameTable.put("efficiency", new Integer(32)); // efficiency
		enchantmentNameTable.put("silk touch", new Integer(33)); // silk touch
		enchantmentNameTable.put("unbreaking", new Integer(34)); // unbreaking
		enchantmentNameTable.put("fortune",    new Integer(35)); // fortune
		
		enchantmentNameTable.put("power",    new Integer(48)); // power
		enchantmentNameTable.put("punch",    new Integer(49)); // punch
		enchantmentNameTable.put("flame",    new Integer(50)); // flame
		enchantmentNameTable.put("infinity", new Integer(51)); // infinity
	}
}