package rgn.mods.rum.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import rgn.mods.rum.tileentity.TileEntityLockedChest;

public class ContainerLockedChest extends Container
{
	private TileEntityLockedChest tileEntity;

	public ContainerLockedChest(IInventory playerInventory, TileEntityLockedChest tileEntityLockedChest)
	{
		this.tileEntity = tileEntityLockedChest;
		this.tileEntity.openChest();
		for (int i = 0; i < 3; i++)
		{
			for (int l = 0; l < 3; l++)
			{
				addSlotToContainer(new Slot(tileEntityLockedChest, l + i * 3, 62 + l * 18, 17 + i * 18));
			}
		}

		for (int j = 0; j < 3; j++)
		{
			for (int i1 = 0; i1 < 9; i1++)
			{
				addSlotToContainer(new Slot(playerInventory, i1 + j * 9 + 9, 8 + i1 * 18, 84 + j * 18));
			}
		}

		for (int k = 0; k < 9; k++)
		{
			addSlotToContainer(new Slot(playerInventory, k, 8 + k * 18, 142));
		}
	}

	@Override
	public void onCraftGuiClosed(EntityPlayer par1EntityPlayer)
	{
		super.onCraftGuiClosed(par1EntityPlayer);
		this.tileEntity.closeChest();
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer)
	{
		return this.tileEntity.isUseableByPlayer(entityplayer);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int i)
	{
		ItemStack itemstack = null;
		Slot slot = (Slot)inventorySlots.get(i);
		if (slot != null && slot.getHasStack())
		{
			ItemStack temp = slot.getStack();
			itemstack = temp.copy();
			if (i < 9)
			{
				if (!mergeItemStack(temp, 9, inventorySlots.size(), true))
				{
					return null;
				}
			}
			else if (!mergeItemStack(temp, 0, 9, false))
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
