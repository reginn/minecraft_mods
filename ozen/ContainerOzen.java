package rgn.mods.ozen;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerOzen extends Container
{
	private TileEntityOzen entity;

	public ContainerOzen(IInventory iinventory, TileEntityOzen tileEntityOzen)
	{
		entity = tileEntityOzen;
		for (int i = 0; i < 2; i++)
		{
			for (int l = 0; l < 3; l++)
			{
				addSlotToContainer(new Slot(tileEntityOzen, l + i * 3, 62 + l * 18, 25 + i * 18));
			}
		}

		for (int j = 0; j < 3; j++)
		{
			for (int i1 = 0; i1 < 9; i1++)
			{
				addSlotToContainer(new Slot(iinventory, i1 + j * 9 + 9, 8 + i1 * 18, 84 + j * 18));
			}
		}

		for (int k = 0; k < 9; k++)
		{
			addSlotToContainer(new Slot(iinventory, k, 8 + k * 18, 142));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer)
	{
		return entity.isUseableByPlayer(entityplayer);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int slotIndex)
	{
		ItemStack itemstack = null;
		Slot slot = (Slot)inventorySlots.get(slotIndex);
		if (slot != null && slot.getHasStack())
		{
			ItemStack temp = slot.getStack();
			itemstack = temp.copy();
			if (slotIndex < 6)
			{
				if (!mergeItemStack(temp, 6, inventorySlots.size(), true))
				{
					return null;
				}
			}
			else if (!mergeItemStack(temp, 0, 6, false))
			{
				return null;
			}
			if (temp.stackSize == 0)
			{
				slot.putStack(null);
			}
			else
			{
				slot.onSlotChanged();
			}
		}

		return itemstack;
	}
}
