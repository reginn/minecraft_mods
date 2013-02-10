package rgn.mods.dwarventools.item;

import java.util.Set;

import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;

public class ItemDwarvenPickaxe extends ItemDwarvenTool
{
	public static final Block[] blocksEffectiveAgainst =
		new Block[]
		{
			Block.cobblestone, Block.stoneDoubleSlab, Block.stoneSingleSlab, Block.stone, Block.sandStone,
			Block.cobblestoneMossy, Block.oreIron, Block.blockSteel, Block.oreCoal, Block.blockGold,
			Block.oreGold, Block.oreDiamond, Block.blockDiamond, Block.ice, Block.netherrack,
			Block.oreLapis, Block.blockLapis, Block.oreRedstone, Block.oreRedstoneGlowing,
			Block.rail, Block.railDetector, Block.railPowered, Block.silverfish
		};
	
	private Set<Block> blocksEffectiveAgainstSet = Sets.newHashSet(blocksEffectiveAgainst);
		
	public ItemDwarvenPickaxe(int itemId, EnumToolMaterial material)
	{
		super(itemId, 2, material, blocksEffectiveAgainst);
	}
	
	@Override
	protected boolean canEffectiveAgainst(Block block)
	{
		return this.canHarvestBlock(block) || this.blocksEffectiveAgainstSet.contains(block);
	}
	
	@Override
	public boolean canHarvestBlock(Block par1Block)
	{
		return par1Block == Block.obsidian ? this.toolMaterial.getHarvestLevel() == 3
			: (par1Block != Block.blockDiamond && par1Block != Block.oreDiamond ?
			  (par1Block != Block.blockGold    && par1Block != Block.oreGold ?
			  (par1Block != Block.blockSteel   && par1Block != Block.oreIron ?
			  (par1Block != Block.blockLapis   && par1Block != Block.oreLapis ?
			  (par1Block != Block.oreRedstone  && par1Block != Block.oreRedstoneGlowing ?
			  (par1Block.blockMaterial == Material.rock ? true : par1Block.blockMaterial == Material.iron)
			: this.toolMaterial.getHarvestLevel() >= 2)
			: this.toolMaterial.getHarvestLevel() >= 1)
			: this.toolMaterial.getHarvestLevel() >= 1)
			: this.toolMaterial.getHarvestLevel() >= 2)
			: this.toolMaterial.getHarvestLevel() >= 2);
	}

	@Override
	public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block)
	{
		return par2Block != null && (par2Block.blockMaterial == Material.iron || par2Block.blockMaterial == Material.rock)
			? this.efficiencyOnProperMaterial : super.getStrVsBlock(par1ItemStack, par2Block);
	}
}
