package rgn.mods.mabicraft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class InventoryQuestBoard implements IInventory
{
	ItemStack[] contents;
	ContainerQuestBoard eventHandler;

	public InventoryQuestBoard(ContainerQuestBoard container)
	{
		this.contents = new ItemStack[1];
		this.eventHandler = container;
	}

	@Override
	public int getSizeInventory()
	{
		return 1;
	}

	@Override
	public ItemStack getStackInSlot(int slotIndex)
	{
		return slotIndex >= this.getSizeInventory() ? null : this.contents[slotIndex];
	}

	@Override
	public ItemStack decrStackSize(int par1, int par2)
	{
		if (this.contents[par1] != null)
		{
			ItemStack var3;

			if (this.contents[par1].stackSize <= par2)
			{
				var3 = this.contents[par1];
				this.contents[par1] = null;
				this.eventHandler.onCraftMatrixChanged(this);
				return var3;
				}
			else
			{
				var3 = this.contents[par1].splitStack(par2);

				if (this.contents[par1].stackSize == 0)
				{
					this.contents[par1] = null;
				}

				this.eventHandler.onCraftMatrixChanged(this);
				return var3;
			}
		}
		else
		{
			return null;
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slotIndex)
	{
		if (this.contents[slotIndex] != null)
		{
			ItemStack is = this.contents[slotIndex];
			this.contents[slotIndex] = null;
			return is;
		}
		else
		{
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
	{
		this.contents[par1] = par2ItemStack;
		this.eventHandler.onCraftMatrixChanged(this);
	}

	@Override
	public String getInvName()
	{
		return "QuestBoard";
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	@Override
	public void onInventoryChanged()
	{
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player)
	{
		return true;
	}

	@Override
	public void openChest()
	{
	}

	@Override
	public void closeChest()
	{
	}

	@Override

	/**
	 * If this returns false, the inventory name will be used as an unlocalized name, and translated into the player's
	 * language. Otherwise it will be used directly.
	 */
	public boolean isInvNameLocalized()
	{
		return false;
	}

	@Override

	/**
	 * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot.
	 */
	public boolean isStackValidForSlot(int i, ItemStack itemstack)
	{
		return false;
	}

}
