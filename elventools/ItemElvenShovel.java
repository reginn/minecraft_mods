package rgn.mods.elventools;

import java.util.Random;

import net.minecraft.src.*;

public class ItemElvenShovel extends ItemTool
{
	private static Block[] blocksEffectiveAgainst = 
		new Block[]
			{
				Block.grass, Block.dirt, Block.sand, Block.gravel, Block.snow, 
				Block.blockSnow, Block.blockClay, Block.tilledField, Block.slowSand, Block.mycelium
			};
	
	private Random random = new Random();
	
	public ItemElvenShovel(int itemId, EnumToolMaterial material)
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
		return par1Block == Block.snow ? true : par1Block == Block.blockSnow;
	}
	
	@Override
	public void onCreated(ItemStack itemstack, World world, EntityPlayer entityplayer)
	{
		super.onCreated(itemstack, world, entityplayer);
		if (itemstack.itemID == ElvenTools.itemElvenShovelMithril.shiftedIndex)
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
		
		if (itemstack.itemID == ElvenTools.itemElvenShovelMithril.shiftedIndex)
		{
			itemstack.addEnchantment(Enchantment.efficiency, 2);
		}
	}
}
