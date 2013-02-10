package rgn.mods.elventools.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ItemShadowBow extends ItemElvenBow
{
	public ItemShadowBow(int itemId, EnumElvenBowType type)
	{
		super(itemId, type);
	}
	
	@Override
	public boolean isCanShot(EntityPlayer player, ItemStack itemstack)
	{
		return true;
	}
	
	@Override
	public boolean isCreativeMode(EntityPlayer player)
	{
		return true;
	}
	
	@Override
	public boolean isInfinity(ItemStack itemstack)
	{
		return true;
	}
}