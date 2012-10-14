package rgn.mods.decorations;

import net.minecraft.src.*;

public class ItemBetterSnow extends ItemBlock
{
	private int blockID;
	private String[] itemNames =
		{
			"bettersnow", "glowsnow"
		};
		
	public ItemBetterSnow(int itemId)
	{
		super(itemId);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
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
	
	@Override
	public int getMetadata(int par1)
	{
		return par1;
	}
}