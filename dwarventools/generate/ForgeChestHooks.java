package rgn.mods.dwarventools.generate;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;

import net.minecraftforge.common.ChestGenHooks;

import rgn.mods.dwarventools.item.DwarvenItem;

public class ForgeChestHooks
{
	private final String STRONGHOLD_CORRIDOR = ChestGenHooks.STRONGHOLD_CORRIDOR;
	private final String STRONGHOLD_CROSSING = ChestGenHooks.STRONGHOLD_CROSSING;
	private final String STRONGHOLD_LIBRARY  = ChestGenHooks.STRONGHOLD_LIBRARY;
	private final String MINESHAFT_CORRIDOR  = ChestGenHooks.MINESHAFT_CORRIDOR;
	private final String DUNGEON_CHEST       = ChestGenHooks.DUNGEON_CHEST;

	public void addLoot()
	{
		this.addDungeonLoot();
		this.addMineshaftLoot();
		this.addStrongholdLoot();
	}

	public void addStrongholdLoot()
	{
		if (ChestGenHooks.getInfo(STRONGHOLD_CORRIDOR).getMax() < 5)
		{
			ChestGenHooks.getInfo(STRONGHOLD_CORRIDOR).setMax(5);
			ChestGenHooks.getInfo(STRONGHOLD_CORRIDOR).setMin(5);
		}

		ChestGenHooks.addItem(STRONGHOLD_CORRIDOR, new WeightedRandomChestContent(new ItemStack(DwarvenItem.itemDwarvenBrokenSwordMithril), 1, 1, 5));
		ChestGenHooks.addItem(STRONGHOLD_CORRIDOR, new WeightedRandomChestContent(new ItemStack(DwarvenItem.itemDwarvenBrokenSwordEbony),   1, 1, 5));

		ChestGenHooks.addItem(STRONGHOLD_CROSSING, new WeightedRandomChestContent(new ItemStack(DwarvenItem.itemDwarvenPlateMithril), 1, 1, 5));
		ChestGenHooks.addItem(STRONGHOLD_CROSSING, new WeightedRandomChestContent(new ItemStack(DwarvenItem.itemDwarvenPlateMithril), 1, 1, 5));

		ChestGenHooks.addItem(STRONGHOLD_CROSSING, new WeightedRandomChestContent(new ItemStack(DwarvenItem.itemDwarvenLegsMithril), 1, 1, 5));
		ChestGenHooks.addItem(STRONGHOLD_CROSSING, new WeightedRandomChestContent(new ItemStack(DwarvenItem.itemDwarvenLegsMithril), 1, 1, 5));

		ChestGenHooks.addItem(STRONGHOLD_LIBRARY,  new WeightedRandomChestContent(new ItemStack(Item.enchantedBook, 1, 0), 1, 1, 100));

	}

	public void addMineshaftLoot()
	{
		if (ChestGenHooks.getInfo(MINESHAFT_CORRIDOR).getMax() < 5)
		{
			ChestGenHooks.getInfo(MINESHAFT_CORRIDOR).setMax(5);
			ChestGenHooks.getInfo(MINESHAFT_CORRIDOR).setMin(5);
		}

		ChestGenHooks.addItem(MINESHAFT_CORRIDOR, new WeightedRandomChestContent(new ItemStack(DwarvenItem.itemMithrilIngot), 1, 4, 20));
		ChestGenHooks.addItem(MINESHAFT_CORRIDOR, new WeightedRandomChestContent(new ItemStack(DwarvenItem.itemEbonyIngot),   1, 2, 10));

		ChestGenHooks.addItem(MINESHAFT_CORRIDOR, new WeightedRandomChestContent(new ItemStack(DwarvenItem.itemDwarvenHelmetMithril), 1, 1, 5));
		ChestGenHooks.addItem(MINESHAFT_CORRIDOR, new WeightedRandomChestContent(new ItemStack(DwarvenItem.itemDwarvenHelmetEbony),   1, 1, 5));

		ChestGenHooks.addItem(MINESHAFT_CORRIDOR, new WeightedRandomChestContent(new ItemStack(DwarvenItem.itemDwarvenPickaxeMithril), 1, 1, 5));
		ChestGenHooks.addItem(MINESHAFT_CORRIDOR, new WeightedRandomChestContent(new ItemStack(DwarvenItem.itemDwarvenPickaxeEbony), 1, 1, 5));

		ChestGenHooks.addItem(MINESHAFT_CORRIDOR, new WeightedRandomChestContent(new ItemStack(Item.enchantedBook, 1, 0), 1, 1, 100));
	}

	public void addDungeonLoot()
	{
		if (ChestGenHooks.getInfo(DUNGEON_CHEST).getMax() < 12)
		{
			ChestGenHooks.getInfo(DUNGEON_CHEST).setMax(12);
			ChestGenHooks.getInfo(DUNGEON_CHEST).setMin(12);
		}

		ChestGenHooks.addItem(DUNGEON_CHEST, new WeightedRandomChestContent(new ItemStack(Item.expBottle),     1, 5, 50));
		ChestGenHooks.addItem(DUNGEON_CHEST, new WeightedRandomChestContent(new ItemStack(Item.recordBlocks),  1, 1, 3));
		ChestGenHooks.addItem(DUNGEON_CHEST, new WeightedRandomChestContent(new ItemStack(Item.recordChirp),   1, 1, 3));
		ChestGenHooks.addItem(DUNGEON_CHEST, new WeightedRandomChestContent(new ItemStack(Item.recordFar),     1, 1, 3));
		ChestGenHooks.addItem(DUNGEON_CHEST, new WeightedRandomChestContent(new ItemStack(Item.recordMall),    1, 1, 3));
		ChestGenHooks.addItem(DUNGEON_CHEST, new WeightedRandomChestContent(new ItemStack(Item.recordMellohi), 1, 1, 3));
		ChestGenHooks.addItem(DUNGEON_CHEST, new WeightedRandomChestContent(new ItemStack(Item.recordStal),    1, 1, 3));
		ChestGenHooks.addItem(DUNGEON_CHEST, new WeightedRandomChestContent(new ItemStack(Item.recordStrad),   1, 1, 3));
		ChestGenHooks.addItem(DUNGEON_CHEST, new WeightedRandomChestContent(new ItemStack(Item.recordWard),    1, 1, 3));
		ChestGenHooks.addItem(DUNGEON_CHEST, new WeightedRandomChestContent(new ItemStack(Item.record11),      1, 1, 3));

		ChestGenHooks.addItem(DUNGEON_CHEST, new WeightedRandomChestContent(new ItemStack(Item.lightStoneDust),   4, 32, 30));
		ChestGenHooks.addItem(DUNGEON_CHEST, new WeightedRandomChestContent(new ItemStack(Item.netherStalkSeeds), 1,  4, 20));
		ChestGenHooks.addItem(DUNGEON_CHEST, new WeightedRandomChestContent(new ItemStack(Item.emerald),          1,  4,  5));
		ChestGenHooks.addItem(DUNGEON_CHEST, new WeightedRandomChestContent(new ItemStack(Item.diamond),          1,  2,  5));
	}
}