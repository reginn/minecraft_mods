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
		if (itemstack.getItemDamage() == 0)
		{
			return "Mithril Ore";
		}
		else
		{
			return "Ebony Ore";
		}
	}
}
