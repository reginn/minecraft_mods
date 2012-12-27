package rgn.mods.dwarventools.generate;

import net.minecraft.item.Item;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.DungeonHooks;
import rgn.mods.dwarventools.core.DwarvenItem;

public class ForgeChestHooks
{
	private final static String STRONGHOLD_CORRIDOR = ChestGenHooks.STRONGHOLD_CORRIDOR;
	private final static String STRONGHOLD_CROSSING = ChestGenHooks.STRONGHOLD_CROSSING;
	private final static String STRONGHOLD_LIBRARY  = ChestGenHooks.STRONGHOLD_LIBRARY;

	private final static String MINESHAFT_CORRIDOR = ChestGenHooks.MINESHAFT_CORRIDOR;

	public void addLoot()
	{
		this.addDungeonLoot();
		this.addMineshaftLoot();
		this.addStrongholdLoot();
	}

	public void addStrongholdLoot()
	{
		ChestGenHooks.addItem(STRONGHOLD_CORRIDOR, new WeightedRandomChestContent(new ItemStack(DwarvenItem.itemDwarvenBrokenSwordMithril), 1, 1, 5));
		ChestGenHooks.addItem(STRONGHOLD_CORRIDOR, new WeightedRandomChestContent(new ItemStack(DwarvenItem.itemDwarvenBrokenSwordEbony),   1, 1, 5));

		ChestGenHooks.addItem(STRONGHOLD_CROSSING, new WeightedRandomChestContent(new ItemStack(DwarvenItem.itemDwarvenPlateMithril), 1, 1, 5));
		ChestGenHooks.addItem(STRONGHOLD_CROSSING, new WeightedRandomChestContent(new ItemStack(DwarvenItem.itemDwarvenPlateMithril), 1, 1, 5));

		ChestGenHooks.addItem(STRONGHOLD_CROSSING, new WeightedRandomChestContent(new ItemStack(DwarvenItem.itemDwarvenLegsMithril), 1, 1, 5));
		ChestGenHooks.addItem(STRONGHOLD_CROSSING, new WeightedRandomChestContent(new ItemStack(DwarvenItem.itemDwarvenLegsMithril), 1, 1, 5));

	}

	public void addMineshaftLoot()
	{
		ChestGenHooks.addItem(MINESHAFT_CORRIDOR, new WeightedRandomChestContent(new ItemStack(DwarvenItem.itemMithrilIngot), 1, 4, 20));
		ChestGenHooks.addItem(MINESHAFT_CORRIDOR, new WeightedRandomChestContent(new ItemStack(DwarvenItem.itemEbonyIngot),   1, 2, 10));

		ChestGenHooks.addItem(MINESHAFT_CORRIDOR, new WeightedRandomChestContent(new ItemStack(DwarvenItem.itemDwarvenHelmetMithril), 1, 1, 5));
		ChestGenHooks.addItem(MINESHAFT_CORRIDOR, new WeightedRandomChestContent(new ItemStack(DwarvenItem.itemDwarvenHelmetEbony),   1, 1, 5));

		ChestGenHooks.addItem(MINESHAFT_CORRIDOR, new WeightedRandomChestContent(new ItemStack(DwarvenItem.itemDwarvenPickaxeMithril), 1, 1, 5));
		ChestGenHooks.addItem(MINESHAFT_CORRIDOR, new WeightedRandomChestContent(new ItemStack(DwarvenItem.itemDwarvenPickaxeEbony), 1, 1, 5));
	}

	public void addDungeonLoot()
	{
		if (DungeonHooks.getDungeonLootTries() < 12)
		{
			DungeonHooks.setDungeonLootTries(12);
		}

		DungeonHooks.addDungeonLoot(new ItemStack(Item.expBottle),     50, 1, 5);
		DungeonHooks.addDungeonLoot(new ItemStack(Item.recordBlocks),  3);
		DungeonHooks.addDungeonLoot(new ItemStack(Item.recordChirp),   3);
		DungeonHooks.addDungeonLoot(new ItemStack(Item.recordFar),     3);
		DungeonHooks.addDungeonLoot(new ItemStack(Item.recordMall),    3);
		DungeonHooks.addDungeonLoot(new ItemStack(Item.recordMellohi), 3);
		DungeonHooks.addDungeonLoot(new ItemStack(Item.recordStal),    3);
		DungeonHooks.addDungeonLoot(new ItemStack(Item.recordStrad),   3);
		DungeonHooks.addDungeonLoot(new ItemStack(Item.recordWard),    3);
		DungeonHooks.addDungeonLoot(new ItemStack(Item.record11),      3);

		DungeonHooks.addDungeonLoot(new ItemStack(Item.lightStoneDust),   30, 4, 32);
		DungeonHooks.addDungeonLoot(new ItemStack(Item.netherStalkSeeds), 20, 1,  4);
		DungeonHooks.addDungeonLoot(new ItemStack(Item.emerald),           5, 1,  4);
		DungeonHooks.addDungeonLoot(new ItemStack(Item.diamond),           5, 1,  2);
	}
}