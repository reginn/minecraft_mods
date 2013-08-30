package rgn.mods.elventools.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ContainerSeedBag extends Container
{
	public  InventorySeedBag inventorySeedBag;
	private IInventory inventoryPlayer;
	private World      world;

	public ContainerSeedBag(World world, EntityPlayer player, ItemStack itemStack)
	{
		this.inventorySeedBag = new InventorySeedBag(itemStack);
		this.inventoryPlayer  = player.inventory;
		this.world            = world;

		for (int col = 0; col < 3; ++col)
		{
			for (int row = 0; row < 3; ++row)
			{
				this.addSlotToContainer(new SlotSeed(this.inventorySeedBag, row + col * 3, 62 + row * 18, 17 + col * 18));
			}
		}

		for (int col = 0; col < 3; ++col)
		{
			for (int row = 0; row < 9; ++row)
			{
				this.addSlotToContainer(new Slot(this.inventoryPlayer, row + col * 9 + 9, 8 + row * 18, 84 + col * 18));
			}
		}

		for (int row = 0; row < 9; ++row)
		{
			this.addSlotToContainer(new Slot(this.inventoryPlayer, row, 8 + row * 18, 142));
		}

	}

	public void onGuiClosed()
	{
		this.inventorySeedBag.closeChest();
	}

	@Override
	public void onContainerClosed(EntityPlayer par1EntityPlayer)
	{
		super.onContainerClosed(par1EntityPlayer);
		this.inventorySeedBag.closeChest();
	}

	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		return this.inventorySeedBag.isUseableByPlayer(player);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int i)
	{
		ItemStack itemstack = null;
		Slot slot = (Slot)this.inventorySlots.get(i);
		SlotSeed slotSeed = (SlotSeed)this.inventorySlots.get(0);
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
			else if (!slotSeed.isItemValid(temp))
			{
				return null;
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