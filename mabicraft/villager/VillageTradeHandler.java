package rgn.mods.mabicraft.villager;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

import net.minecraft.src.*;

import cpw.mods.fml.common.registry.VillagerRegistry;

import rgn.mods.mabicraft.core.*;
import rgn.mods.mabicraft.config.*;
import rgn.mods.mabicraft.registry.*;

public class VillageTradeHandler
{
	public class EvilScrollTradeHandler implements VillagerRegistry.IVillageTradeHandler
	{
		public void manipulateTradesForVillager(EntityVillager villager, MerchantRecipeList recipeList, Random random)
		{
			
			for (int i = 0; i < EvilScrollRegistry.instance().getClassListSize(); ++i)
			{
				recipeList.add
				(
					new MerchantRecipe
					(
						new ItemStack(MabiCraftItem.itemEvilScroll, 10, EvilScrollRegistry.instance().getMetadataFromClass(EvilScrollRegistry.instance().getEntityClass(i))), 
						new ItemStack(Item.emerald, EvilScrollRegistry.instance().getNumberFromClass(EvilScrollRegistry.instance().getEntityClass(i)))
					)
				);
			
			}
		}
	}
	
	public class BressedPotionTradeHandler implements VillagerRegistry.IVillageTradeHandler
	{
		public void manipulateTradesForVillager(EntityVillager villager, MerchantRecipeList recipeList, Random random)
		{
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(Item.emerald,   1), new ItemStack(MabiCraftItem.itemBlessedPotion, 2)));
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(Item.appleRed, 10), new ItemStack(MabiCraftItem.itemBlessedPotion, 4)));
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(Item.egg,      10), new ItemStack(MabiCraftItem.itemBlessedPotion, 4)));
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(Item.wheat,    32), new ItemStack(MabiCraftItem.itemBlessedPotion, 4)));
		}
	}
	
	public class HealerTradeHandler implements VillagerRegistry.IVillageTradeHandler
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
	
	public class CookingFoodTradeHandler implements VillagerRegistry.IVillageTradeHandler
	{
		public void manipulateTradesForVillager(EntityVillager villager, MerchantRecipeList recipeList, Random random)
		{
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(MabiCraftItem.itemCookingFood, 4, 144)));
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(MabiCraftItem.itemCookingFood, 4, 145)));
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(MabiCraftItem.itemCookingFood, 4, 146)));
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(MabiCraftItem.itemCookingFood, 4, 147)));
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(MabiCraftItem.itemCookingFood, 1, 148)));
			recipeList.addToListWithCheck(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(MabiCraftItem.itemCookingFood, 1, 149)));
		}
	}
	
	
	public void addTradeHandler()
	{
		int villagerID = Config.startVillagerID;
		
		List<String> textures = Lists.newArrayList
			(
				"/rgn/sprites/mabicraft/mob/villager/brotherhood.png",
				"/rgn/sprites/mabicraft/mob/villager/greatvillager.png",
				"/rgn/sprites/mabicraft/mob/villager/healer.png",
				"/rgn/sprites/mabicraft/mob/villager/redvillager.png"
			);
		
		for (String texture : textures)
		{
			VillagerRegistry.instance().registerVillagerType(villagerID++, texture);
		}
		
		List<VillagerRegistry.IVillageTradeHandler> tradeHandlers = Lists.newArrayList
			(
				new EvilScrollTradeHandler(),
				new BressedPotionTradeHandler(),
				new HealerTradeHandler(), 
				new CookingFoodTradeHandler()
			);
		
		villagerID = Config.startVillagerID;
		for (VillagerRegistry.IVillageTradeHandler tradeHandler : tradeHandlers)
		{
			VillagerRegistry.instance().registerVillageTradeHandler(villagerID++, tradeHandler);
		}
	}
}