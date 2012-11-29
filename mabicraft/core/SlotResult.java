package rgn.mods.mabicraft.core;

import net.minecraft.src.*;

public class SlotResult extends Slot
{
	public SlotResult(IInventory iinventory, int par3, int par4, int par5)
	{
		super(iinventory, par3, par4, par5);
	}
	
	@Override
	public boolean isItemValid(ItemStack itemstack)
	{
		return false;
	}
	
}
