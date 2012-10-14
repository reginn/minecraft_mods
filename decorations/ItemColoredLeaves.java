package rgn.mods.decorations;

import net.minecraft.src.*;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

import rgn.mods.decorations.Decorations;
import rgn.mods.decorations.BlockColoredLeaves;

public class ItemColoredLeaves extends ItemBlock
{
	public ItemColoredLeaves(int itemId)
	{
		super(itemId);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}
	
	public int getMetadata(int par1)
	{
		return par1 | 4;
	}
	
	@SideOnly(Side.CLIENT)
	public int getIconFromDamage(int par1)
	{
		return Decorations.blockColoredLeaves.getBlockTextureFromSideAndMetadata(0, par1);
	}
	
	@SideOnly(Side.CLIENT)
	public int getColorFromDamage(int damage, int par2)
	{
		int type = damage;
		if (damage < 0 || damage > ((BlockColoredLeaves)Decorations.blockColoredLeaves).colors.length)
		{
			type = 0;
		}
		return ((BlockColoredLeaves)Decorations.blockColoredLeaves).colors[type];
	}
	
	public String getItemNameIS(ItemStack par1ItemStack)
	{
		int var2 = par1ItemStack.getItemDamage();
		
		if (var2 < 0 || var2 >= ((BlockColoredLeaves)Decorations.blockColoredLeaves).types.length)
		{
			var2 = 0;
		}
		
		return super.getItemName() + "." + ((BlockColoredLeaves)Decorations.blockColoredLeaves).types[var2];
	}
}
