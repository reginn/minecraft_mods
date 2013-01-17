package rgn.mods.elventools.core;

import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.IFuelHandler;

public class FuelHandler implements IFuelHandler
{
	@Override
	public int getBurnTime(ItemStack fuel)
	{
		return
			fuel.itemID == ElvenBlock.blockEbonyLog.blockID      ? 1000 :
			fuel.itemID == ElvenBlock.blockEbonyWood.blockID     ? 800 :
			fuel.itemID == ElvenBlock.blockEbonySapling.blockID  ? 400 :
			fuel.itemID == ElvenItem.itemEbonyStick.itemID ? 400 : 0;
	}
}