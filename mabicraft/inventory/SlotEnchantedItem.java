package rgn.mods.mabicraft.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotEnchantedItem extends Slot
{
	public SlotEnchantedItem(IInventory iinventory, int par3, int par4, int par5)
	{
		super(iinventory, par3, par4, par5);
	}

	@Override
	public boolean isItemValid(ItemStack itemstack)
	{
		return itemstack.hasTagCompound() && itemstack.getTagCompound().hasKey("ench");
	}
}
