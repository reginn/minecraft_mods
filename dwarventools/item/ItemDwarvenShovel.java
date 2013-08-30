package rgn.mods.dwarventools.item;

import net.minecraft.block.Block;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import net.minecraftforge.common.ForgeHooks;

public class ItemDwarvenShovel extends ItemDwarvenTool
{
	public static final Block[] blocksEffectiveAgainst =
		new Block[]
			{
				Block.grass, Block.dirt, Block.sand, Block.gravel, Block.snow,
				Block.blockSnow, Block.blockClay, Block.tilledField, Block.slowSand, Block.mycelium
			};

	public ItemDwarvenShovel(int itemId, EnumToolMaterial material)
	{
		super(itemId, 2, material, blocksEffectiveAgainst);
	}

	public boolean canHarvestBlock(Block par1Block)
	{
		return ForgeHooks.isToolEffective(new ItemStack(Item.shovelIron), par1Block, 0);
	}

}
