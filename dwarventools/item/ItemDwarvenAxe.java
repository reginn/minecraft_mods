package rgn.mods.dwarventools.item;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;

public class ItemDwarvenAxe extends ItemDwarvenTool
{
	public static final Block[] blocksEffectiveAgainst =
		new Block[]
		{
			Block.planks, Block.bookShelf, Block.wood, Block.chest, Block.stoneDoubleSlab,
			Block.stoneSingleSlab, Block.pumpkin, Block.pumpkinLantern
		};

	public ItemDwarvenAxe(int itemId, EnumToolMaterial material)
	{
		super(itemId, 2, material, blocksEffectiveAgainst);
	}

	public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block)
	{
		return par2Block != null && par2Block.blockMaterial == Material.wood ? this.efficiencyOnProperMaterial : super.getStrVsBlock(par1ItemStack, par2Block);
	}

}
