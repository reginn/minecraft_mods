package rgn.mods.dwarventools.item;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemDwarvenOreStorage extends ItemBlock
{
	public ItemDwarvenOreStorage(int itemId)
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
		String str[] = new String[]
			{
				"blockRedstone", "blockMithril", "blockEbony"
			};

		int type = itemstack.getItemDamage();

		if (type < 0 || type >= str.length)
		{
			type = 0;
		}

		return (new StringBuilder()).append(getItemName()).append(".").append(str[type]).toString();
	}
}
