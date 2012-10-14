package rgn.mods.decorations;

import net.minecraft.src.*;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

public class ItemGlowFlower extends ItemBlock
{
	private String[] itemNames =
		{
			"glowrose", "glowflower", "glowredmushroom", "glowbrownmushroom", "glowtallgrass", "glowfern", "glowdeadbush"
		};
	
	public ItemGlowFlower(int itemId)
	{
		super(itemId);
		setMaxDamage(0);
		setHasSubtypes(true);
	}
	
	@Override
	public String getItemNameIS(ItemStack itemstack)
	{
		int damage = itemstack.getItemDamage();
		if (damage >= 0 && damage < itemNames.length)
		{
			return (new StringBuilder()).append(getItemName()).append(".").append(itemNames[damage]).toString();
		}
		return (new StringBuilder()).append(getItemName()).append(".").append(itemNames[0]).toString();
	}
	
	@SideOnly(Side.CLIENT)
	public int getColorFromDamage(int par1, int par2)
	{
		return Decorations.blockGlowFlower.getRenderColor(par1);
	}
	
	@Override
	public int getMetadata(int par1)
	{
		return par1;
	}
	
	@Override
	public int getIconFromDamage(int par1)
	{
		return Decorations.blockGlowFlower.getBlockTextureFromSideAndMetadata(0, par1);
	}
}