package rgn.mods.ozen;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.packet.Packet;
import net.minecraft.tileentity.TileEntity;

public class TileEntityOzen extends TileEntity implements IInventory
{
	private ItemStack items[];
	private byte facing;

	public TileEntityOzen()
	{
		items = new ItemStack[6];
	}

	public byte getFacing()
	{
		return facing;
	}

	public void setFacing(byte _facing)
	{
		this.facing = _facing;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbttagcompound)
	{
		super.readFromNBT(nbttagcompound);
		NBTTagList nbttaglist = nbttagcompound.getTagList("Items");
		items = new ItemStack[getSizeInventory()];
		for (int i = 0; i < nbttaglist.tagCount(); i++)
		{
			NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.tagAt(i);
			int j = nbttagcompound1.getByte("Slot") & 0xff;
			if (j >= 0 && j < items.length)
			{
				items[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}
		facing = nbttagcompound.getByte("facing");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbttagcompound)
	{
		super.writeToNBT(nbttagcompound);
		NBTTagList nbttaglist = new NBTTagList();
		for (byte byte0 = 0; byte0 < items.length; byte0++)
		{
			if (items[byte0] != null)
			{
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", byte0);
				items[byte0].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}

		nbttagcompound.setTag("Items", nbttaglist);
		nbttagcompound.setByte("facing", facing);
	}

	@Override
	public int getSizeInventory()
	{
		return items.length;
	}

	@Override
	public ItemStack getStackInSlot(int i)
	{
		return items[i];
	}

	@Override
	public ItemStack decrStackSize(int i, int j)
	{
		if (items[i] != null)
		{
			if (items[i].stackSize <= j)
			{
				ItemStack itemstack = items[i];
				items[i] = null;
				onInventoryChanged();
				return itemstack;
			}

			ItemStack itemstack1 = items[i].splitStack(j);

			if (items[i].stackSize == 0)
			{
				items[i] = null;
			}

			onInventoryChanged();

			return itemstack1;
		}
		else
		{
			return null;
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i)
	{
		return items[i];
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack)
	{
		items[i] = itemstack;
		if (itemstack != null && itemstack.stackSize > getInventoryStackLimit())
		{
			itemstack.stackSize = getInventoryStackLimit();
		}
		onInventoryChanged();
	}

	@Override
	public String getInvName()
	{
		return "Ozen";
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	@Override
	public void onInventoryChanged()
	{
		super.onInventoryChanged();
		if (this.worldObj != null)
		{
			this.worldObj.addBlockEvent(xCoord, yCoord, zCoord, Ozen.blockOzen.blockID, 0, 0);
		}
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer)
	{
		if (worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) != this)
		{
			return false;
		}
		return entityplayer.getDistanceSq((double)xCoord + 0.5D, (double)yCoord + 0.5D, (double)zCoord + 0.5D) <= 64D;
	}

	@Override
	public void openChest()
	{
	}

	@Override
	public void closeChest()
	{
	}

	// custom packet
	public void setItems(ItemStack[] _items)
	{
		if (_items != null)
		{
			for (int i = 0; i < this.getSizeInventory(); ++i)
			{
				if (_items[i] != null)
				{
					this.items[i] = _items[i].copy();
				}
				else
				{
					this.items[i] = null;
				}
			}
		}
	}

	public ItemStack[] getItems()
	{
		return this.items;
	}

	@Override
	public Packet getDescriptionPacket()
	{
		return PacketHandler.getPacket(this);
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