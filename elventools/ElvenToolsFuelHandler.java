package rgn.mods.elventools;

import cpw.mods.fml.common.*;
import net.minecraft.src.*;

public class ElvenToolsFuelHandler implements IFuelHandler
{
	public int getBurnTime(ItemStack fuel)
	{
		return  
			fuel.itemID == ElvenTools.blockEbonyLog.blockID ? 1000 : 
			fuel.itemID == ElvenTools.blockEbonyWood.blockID ? 800 : 
			fuel.itemID == ElvenTools.blockEbonySapling.blockID ? 400 : 
			fuel.itemID == ElvenTools.itemEbonyStick.shiftedIndex ? 400 : 0;
	}
}