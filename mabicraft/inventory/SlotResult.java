package rgn.mods.mabicraft.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

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
