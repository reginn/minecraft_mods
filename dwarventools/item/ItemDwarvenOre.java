package rgn.mods.dwarventools.item;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

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

	@Override
	public String getUnlocalizedName(ItemStack itemstack)
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
		return (new StringBuilder()).append(this.getUnlocalizedName()).append(".").append(str).toString();
	}
}
