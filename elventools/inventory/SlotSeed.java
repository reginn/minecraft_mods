package rgn.mods.elventools.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import net.minecraftforge.common.IPlantable;

public class SlotSeed extends Slot
{
	public SlotSeed(IInventory iinventory, int par3, int par4, int par5)
	{
		super(iinventory, par3, par4, par5);
	}

	@Override
	public boolean isItemValid(ItemStack itemStack)
	{
		return itemStack.getItem() instanceof IPlantable && this.isOnlySeed(itemStack);
	}
	
	public boolean isOnlySeed(ItemStack seed)
	{
		for (int i = 0; i < this.inventory.getSizeInventory(); ++i)
		{
			ItemStack is = this.inventory.getStackInSlot(i);
			if (is == null)
			{
				continue ;
			}
			return is.itemID == seed.itemID;
		}
		
		return true;
	}
}