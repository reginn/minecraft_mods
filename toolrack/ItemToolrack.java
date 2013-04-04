package rgn.mods.toolrack;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemToolrack extends ItemBlock
{
	public ItemToolrack(int itemId)
	{
		super(itemId);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack)
	{
		int type = itemstack.getItemDamage();
		if (type < 0 || type >= BlockToolrack.types.length)
		{
			type = 0;
		}
		return (new StringBuilder()).append(this.getUnlocalizedName()).append(".").append(BlockToolrack.types[type]).toString();
	}

	@Override
	public int getMetadata(int par1)
	{
		return par1;
	}
}