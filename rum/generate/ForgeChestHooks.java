package rgn.mods.rum.generate;

import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;

import net.minecraftforge.common.ChestGenHooks;

import rgn.mods.rum.item.RumItem;

public class ForgeChestHooks
{
	public void addLoot()
	{
		ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST,          new WeightedRandomChestContent(new ItemStack(RumItem.itemKey, 1, 0), 1, 1, 50));
		ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH,   new WeightedRandomChestContent(new ItemStack(RumItem.itemKey, 1, 0), 1, 1, 50));
		ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST,        new WeightedRandomChestContent(new ItemStack(RumItem.itemKey, 1, 0), 1, 1, 15));
		ChestGenHooks.addItem(ChestGenHooks.MINESHAFT_CORRIDOR,   new WeightedRandomChestContent(new ItemStack(RumItem.itemKey, 1, 0), 1, 1, 15));
		ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_CORRIDOR,  new WeightedRandomChestContent(new ItemStack(RumItem.itemKey, 1, 0), 1, 1, 15));
		ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_CROSSING,  new WeightedRandomChestContent(new ItemStack(RumItem.itemKey, 1, 0), 1, 1, 15));
		ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_LIBRARY,   new WeightedRandomChestContent(new ItemStack(RumItem.itemKey, 1, 0), 1, 1, 10));
		ChestGenHooks.addItem(ChestGenHooks.PYRAMID_DESERT_CHEST, new WeightedRandomChestContent(new ItemStack(RumItem.itemKey, 1, 0), 1, 1, 10));
		ChestGenHooks.addItem(ChestGenHooks.PYRAMID_JUNGLE_CHEST, new WeightedRandomChestContent(new ItemStack(RumItem.itemKey, 1, 0), 1, 1, 10));
	}
}
