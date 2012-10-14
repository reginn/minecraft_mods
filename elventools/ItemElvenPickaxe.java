package rgn.mods.elventools;

import java.util.Random;

import net.minecraft.src.*;

public class ItemElvenPickaxe extends ItemTool
{
	private static Block[] blocksEffectiveAgainst = 
		new Block[] 
		{
			Block.cobblestone, Block.stoneDoubleSlab, Block.stoneSingleSlab, Block.stone, Block.sandStone, 
			Block.cobblestoneMossy, Block.oreIron, Block.blockSteel, Block.oreCoal, Block.blockGold, 
			Block.oreGold, Block.oreDiamond, Block.blockDiamond, Block.ice, Block.netherrack,
			Block.oreLapis, Block.blockLapis, Block.oreRedstone, Block.oreRedstoneGlowing, 
			Block.rail, Block.railDetector, Block.railPowered
		};
	
	private Random random = new Random();
	
	public ItemElvenPickaxe(int itemId, EnumToolMaterial material)
	{
		super(itemId, 2, material, blocksEffectiveAgainst);
	}
	
	@Override
	public String getTextureFile()
	{
		return "/rgn/sprites/elventools/items.png";
	}
	
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
	
	public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block)
	{
		return par2Block != null && (par2Block.blockMaterial == Material.iron || par2Block.blockMaterial == Material.rock) 
			? this.efficiencyOnProperMaterial : super.getStrVsBlock(par1ItemStack, par2Block);
	}
	
	@Override
	public void onCreated(ItemStack itemstack, World world, EntityPlayer entityplayer)
	{
		super.onCreated(itemstack, world, entityplayer);
		if (itemstack.itemID == ElvenTools.itemElvenPickaxeMithril.shiftedIndex)
		{
			itemstack.addEnchantment(Enchantment.efficiency, 2);
		}
	}
	
	@Override
	public void onUpdate(ItemStack itemstack, World world, Entity entity, int par4, boolean par5)
	{
		super.onUpdate(itemstack, world, entity, par4, par5);
		
		if (itemstack.isItemEnchanted())
		{
			return ;
		}
		
		if (itemstack.itemID == ElvenTools.itemElvenPickaxeMithril.shiftedIndex)
		{
			itemstack.addEnchantment(Enchantment.efficiency, 2);
		}
	}
}
