package rgn.mods.elventools;

import java.util.Random;

import net.minecraft.src.*;

public class ItemElvenAxe extends ItemTool
{
	private static Block[] blocksEffectiveAgainst = 
		new Block[]
		{
			Block.planks, Block.bookShelf, Block.wood, Block.chest, Block.woodDoubleSlab, 
			Block.woodSingleSlab, Block.pumpkin, Block.pumpkinLantern
		};
	
	private Random random = new Random();
	
	public ItemElvenAxe(int itemId, EnumToolMaterial material)
	{
		super(itemId, 2, material, blocksEffectiveAgainst);
	}
	
	@Override
	public String getTextureFile()
	{
		return "/rgn/sprites/elventools/items.png";
	}
	
	public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block)
	{
		return par2Block != null && par2Block.blockMaterial == Material.wood ? this.efficiencyOnProperMaterial : super.getStrVsBlock(par1ItemStack, par2Block);
	}
	
	@Override
	public void onCreated(ItemStack itemstack, World world, EntityPlayer entityplayer)
	{
		super.onCreated(itemstack, world, entityplayer);
		if (itemstack.itemID == ElvenTools.itemElvenAxeMithril.shiftedIndex)
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
		
		if (itemstack.itemID == ElvenTools.itemElvenAxeMithril.shiftedIndex)
		{
			itemstack.addEnchantment(Enchantment.efficiency, 2);
		}
	}
}
