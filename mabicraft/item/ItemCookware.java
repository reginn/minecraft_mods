package rgn.mods.mabicraft.item;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemCookware extends ItemBlock
{
	private String[] type = new String[]
		{
			"table", "stove", "pot"
		};

	public ItemCookware(int itemId)
	{
		super(itemId);
		setMaxDamage(0);
		setHasSubtypes(true);
	}

	@Override
	public String getItemNameIS(ItemStack itemstack)
	{
		int metadata = itemstack.getItemDamage();

		if (metadata < 0 || metadata >= type.length)
		{
			metadata = 0;
		}

		return (new StringBuilder()).append(getItemName()).append(".").append(type[metadata]).toString();
	}

	@Override
	public int getMetadata(int par1)
	{
		return par1;
	}
}