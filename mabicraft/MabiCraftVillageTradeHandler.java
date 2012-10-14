package rgn.mods.mabicraft;

import java.io.*;
import java.util.Random;

import java.util.Map;
import java.util.HashMap;
import java.lang.reflect.Field;

import net.minecraft.src.*;

import cpw.mods.fml.common.registry.VillagerRegistry;

public class MabiCraftVillageTradeHandler
{
	public class FarmerTradeHandler implements VillagerRegistry.IVillageTradeHandler
	{
		public void manipulateTradesForVillager(EntityVillager villager, MerchantRecipeList recipeList, Random random)
		{
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(Item.emerald,       1), new ItemStack(MabiCraft.itemManaHerb, 2)));
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(Item.wheat,        32), new ItemStack(MabiCraft.itemManaHerb, 4)));
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(Item.seeds,        64), new ItemStack(MabiCraft.itemManaHerb, 4)));
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(Block.plantRed,    16), new ItemStack(MabiCraft.itemManaHerb, 4)));
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(Block.plantYellow, 16), new ItemStack(MabiCraft.itemManaHerb, 4)));
		}
	}
	
	public class PriestTradeHandler implements VillagerRegistry.IVillageTradeHandler
	{
		public void manipulateTradesForVillager(EntityVillager villager, MerchantRecipeList recipeList, Random random)
		{
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(Item.emerald,   1), new ItemStack(MabiCraft.itemBlessedPotion, 2)));
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(Item.appleRed, 10), new ItemStack(MabiCraft.itemBlessedPotion, 4)));
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(Item.egg,      10), new ItemStack(MabiCraft.itemBlessedPotion, 4)));
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(Item.wheat,    32), new ItemStack(MabiCraft.itemBlessedPotion, 4)));
		}
	}
	
	public class LibrarianTradeHandler implements VillagerRegistry.IVillageTradeHandler
	{
		public void manipulateTradesForVillager(EntityVillager villager, MerchantRecipeList recipeList, Random random)
		{
						
			Class<?>[] entityClassList = 
				{
					EntityZombie.class, EntitySkeleton.class, EntityCreeper.class, EntitySpider.class, 
					EntitySlime.class, EntityEnderman.class, EntitySilverfish.class, EntityCaveSpider.class, 
					EntityPigZombie.class, EntityGhast.class, EntityMagmaCube.class, EntityBlaze.class
				};
			
			int[] nums = 
				{
					4, 4, 4, 4,
					2, 5, 5, 4,
					3, 8, 5, 3
				};
			
			Class<EntityList> entityList = EntityList.class;
			
			try
			{
				Field fieldClassToIDMapping = entityList.getDeclaredField("classToIDMapping");
				fieldClassToIDMapping.setAccessible(true);
				
				try
				{
					Map classToIDMapping = (HashMap)fieldClassToIDMapping.get(null);
					
					for (int i = 0; i < entityClassList.length; ++i)
					{
						recipeList.add(
						new MerchantRecipe(
							new ItemStack(MabiCraft.itemEvilScroll, 10, ((Integer)classToIDMapping.get(entityClassList[i])).intValue()), 
							new ItemStack(Item.emerald, nums[i])
						));
					}
				}
				catch(IllegalAccessException e)
				{
					e.printStackTrace();
				}
			}
			catch (NoSuchFieldException e)
			{
				e.printStackTrace();
			}
		}
	}
	
}