package rgn.mods.dwarventools;

import net.minecraft.src.*;

public class ItemDwarvenOre extends ItemBlock
{
	private int blockID;
	
	public ItemDwarvenOre(int itemId)
	{
		super(itemId);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
		this.maxStackSize = 64;
	}
	
	public int getMetadata(int par1)
	{
		return par1;
	}
	
	public String getItemNameIS(ItemStack itemstack)
	{
		String str;
		if (itemstack.getItemDamage() == 0)
		{
			str = "oreMithril";
		}
		else
		{
			str = "oreEbony";
		}
		return (new StringBuilder()).append(getItemName()).append(".").append(str).toString();
	}
}
