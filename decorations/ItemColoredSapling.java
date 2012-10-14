package rgn.mods.decorations;

import net.minecraft.src.*;

public class ItemColoredSapling extends ItemBlock
{
	private String[] types =
		{
			"cherry", "whitecherry", "maple", "yellow"
		};
	
	public ItemColoredSapling(int itemId)
	{
		super(itemId);
		setMaxDamage(0);
		setHasSubtypes(true);
	}
	
	@Override
	public String getItemNameIS(ItemStack itemstack)
	{
		int damage = itemstack.getItemDamage();
		int type = damage;
		if (damage >= 0 && damage < types.length)
		{
			type = 0;
		}
		return (new StringBuilder()).append(getItemName()).append(".").append(types[type]).toString();
	}
	
	@Override
	public int getMetadata(int par1)
	{
		return par1;
	}
}