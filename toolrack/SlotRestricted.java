package rgn.mods.toolrack;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;

public class SlotRestricted extends Slot
{
	public SlotRestricted(IInventory iinventory, int par3, int par4, int par5)
	{
		super(iinventory, par3, par4, par5);
	}

	@Override
	public boolean isItemValid(ItemStack itemstack)
	{
		return itemstack.getItem() instanceof Item
			&& (itemstack.getItem().isFull3D()
			|| itemstack.getItem() instanceof ItemBow);
	}

}
