package rgn.mods.rum.generate;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;

import net.minecraftforge.common.ChestGenHooks;

public class LockedChestInfo
{
	public static final String LOCKED_CHEST = "lockedChest";

	public static ChestGenHooks lockedChestContents;

	static
	{
		lockedChestContents = ChestGenHooks.getInfo(LOCKED_CHEST);

		lockedChestContents.setMax(6);
		lockedChestContents.setMin(6);

		lockedChestContents.addItem(new WeightedRandomChestContent(new ItemStack(Item.record11),      1, 1, 5));
		lockedChestContents.addItem(new WeightedRandomChestContent(new ItemStack(Item.record13),      1, 1, 5));
		lockedChestContents.addItem(new WeightedRandomChestContent(new ItemStack(Item.recordBlocks),  1, 1, 5));
		lockedChestContents.addItem(new WeightedRandomChestContent(new ItemStack(Item.recordCat),     1, 1, 5));
		lockedChestContents.addItem(new WeightedRandomChestContent(new ItemStack(Item.recordChirp),   1, 1, 5));
		lockedChestContents.addItem(new WeightedRandomChestContent(new ItemStack(Item.recordFar),     1, 1, 5));
		lockedChestContents.addItem(new WeightedRandomChestContent(new ItemStack(Item.recordMall),    1, 1, 5));
		lockedChestContents.addItem(new WeightedRandomChestContent(new ItemStack(Item.recordMellohi), 1, 1, 5));
		lockedChestContents.addItem(new WeightedRandomChestContent(new ItemStack(Item.recordStal),    1, 1, 5));
		lockedChestContents.addItem(new WeightedRandomChestContent(new ItemStack(Item.recordStrad),   1, 1, 5));
		lockedChestContents.addItem(new WeightedRandomChestContent(new ItemStack(Item.recordWard),    1, 1, 5));
		lockedChestContents.addItem(new WeightedRandomChestContent(new ItemStack(Item.recordWait),    1, 1, 5));

		lockedChestContents.addItem(new WeightedRandomChestContent(new ItemStack(Item.diamond),        1, 4, 5));
		lockedChestContents.addItem(new WeightedRandomChestContent(new ItemStack(Item.emerald),        1, 4, 5));
		lockedChestContents.addItem(new WeightedRandomChestContent(new ItemStack(Item.ingotIron),      1, 4, 5));
		lockedChestContents.addItem(new WeightedRandomChestContent(new ItemStack(Item.ingotGold),      1, 4, 5));
		lockedChestContents.addItem(new WeightedRandomChestContent(new ItemStack(Item.field_94583_ca), 1, 4, 5));
		lockedChestContents.addItem(new WeightedRandomChestContent(new ItemStack(Item.lightStoneDust), 1, 4, 5));

		lockedChestContents.addItem(new WeightedRandomChestContent(new ItemStack(Item.skull, 1, 0), 1, 1, 5));
		lockedChestContents.addItem(new WeightedRandomChestContent(new ItemStack(Item.skull, 1, 1), 1, 1, 5));
		lockedChestContents.addItem(new WeightedRandomChestContent(new ItemStack(Item.skull, 1, 2), 1, 1, 5));
		lockedChestContents.addItem(new WeightedRandomChestContent(new ItemStack(Item.skull, 1, 3), 1, 1, 5));
		lockedChestContents.addItem(new WeightedRandomChestContent(new ItemStack(Item.skull, 1, 4), 1, 1, 5));

		lockedChestContents.addItem(new WeightedRandomChestContent(new ItemStack(Block.blockDiamond),      1, 4, 5));
		lockedChestContents.addItem(new WeightedRandomChestContent(new ItemStack(Block.blockEmerald),      1, 4, 5));
		lockedChestContents.addItem(new WeightedRandomChestContent(new ItemStack(Block.blockSteel),        1, 4, 5));
		lockedChestContents.addItem(new WeightedRandomChestContent(new ItemStack(Block.blockGold),         1, 4, 5));
		lockedChestContents.addItem(new WeightedRandomChestContent(new ItemStack(Block.blockNetherQuartz), 1, 4, 5));
		lockedChestContents.addItem(new WeightedRandomChestContent(new ItemStack(Block.glowStone),         1, 4, 5));

		lockedChestContents.addItem(new WeightedRandomChestContent(new ItemStack(Item.enchantedBook, 1, 0), 1, 1, 5));
		lockedChestContents.addItem(new WeightedRandomChestContent(new ItemStack(Item.expBottle),           1, 4, 5));

		lockedChestContents.addItem(new WeightedRandomChestContent(new ItemStack(Item.netherStar), 1, 1, 1));
	}
}
