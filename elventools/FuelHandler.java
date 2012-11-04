package rgn.mods.elventools;

import net.minecraft.src.*;

import cpw.mods.fml.common.IFuelHandler;

public class FuelHandler implements IFuelHandler
{
	public int getBurnTime(ItemStack fuel)
	{
		return  
			fuel.itemID == ElvenBlock.blockEbonyLog.blockID      ? 1000 : 
			fuel.itemID == ElvenBlock.blockEbonyWood.blockID     ? 800 : 
			fuel.itemID == ElvenBlock.blockEbonySapling.blockID  ? 400 : 
			fuel.itemID == ElvenItem.itemEbonyStick.shiftedIndex ? 400 : 0;
	}
}