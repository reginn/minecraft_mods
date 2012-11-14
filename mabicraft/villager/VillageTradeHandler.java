package rgn.mods.mabicraft.villager;

import java.util.Random;

import java.util.Map;
import java.util.HashMap;

import net.minecraft.src.*;

import cpw.mods.fml.common.registry.VillagerRegistry;

import rgn.mods.mabicraft.common.*;
import rgn.mods.mabicraft.config.*;
import rgn.mods.mabicraft.registry.*;

public class VillageTradeHandler
{
	public class FarmerTradeHandler implements VillagerRegistry.IVillageTradeHandler
	{
		public void manipulateTradesForVillager(EntityVillager villager, MerchantRecipeList recipeList, Random random)
		{
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(Item.emerald,       1), new ItemStack(MabiCraftItem.itemManaHerb, 2)));
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(Item.wheat,        32), new ItemStack(MabiCraftItem.itemManaHerb, 4)));
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(Item.seeds,        64), new ItemStack(MabiCraftItem.itemManaHerb, 4)));
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(Block.plantRed,    16), new ItemStack(MabiCraftItem.itemManaHerb, 4)));
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(Block.plantYellow, 16), new ItemStack(MabiCraftItem.itemManaHerb, 4)));
		}
	}
	
	public class PriestTradeHandler implements VillagerRegistry.IVillageTradeHandler
	{
		public void manipulateTradesForVillager(EntityVillager villager, MerchantRecipeList recipeList, Random random)
		{
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(Item.emerald,   1), new ItemStack(MabiCraftItem.itemBlessedPotion, 2)));
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(Item.appleRed, 10), new ItemStack(MabiCraftItem.itemBlessedPotion, 4)));
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(Item.egg,      10), new ItemStack(MabiCraftItem.itemBlessedPotion, 4)));
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(Item.wheat,    32), new ItemStack(MabiCraftItem.itemBlessedPotion, 4)));
		}
	}
	
	public class LibrarianTradeHandler implements VillagerRegistry.IVillageTradeHandler
	{
		public void manipulateTradesForVillager(EntityVillager villager, MerchantRecipeList recipeList, Random random)
		{
			
			for (int i = 0; i < EvilScrollRegistry.instance().getClassListSize(); ++i)
			{
				recipeList.add(
					new MerchantRecipe(
						new ItemStack(MabiCraftItem.itemEvilScroll, 10, EvilScrollRegistry.instance().getMetadataFromClass(EvilScrollRegistry.instance().getEntityClass(i))), 
						new ItemStack(Item.emerald, EvilScrollRegistry.instance().getNumberFromClass(EvilScrollRegistry.instance().getEntityClass(i)))
				));
			
			}
		}
	}
	
}