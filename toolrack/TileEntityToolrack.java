package rgn.mods.toolrack;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.packet.Packet;
import net.minecraft.tileentity.TileEntity;

public class TileEntityToolrack extends TileEntity implements IInventory
{
	private ItemStack items[];
	private byte      facing;
	private int       ticks;

	public TileEntityToolrack()
	{
		items = new ItemStack[1];
	}

	public byte getFacing()
	{
		return this.facing;
	}

	public void setFacing(byte facing)
	{
		this.facing = facing;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbttagcompound)
	{
		super.readFromNBT(nbttagcompound);
		NBTTagList nbttaglist = nbttagcompound.getTagList("Items");
		items = new ItemStack[this.getSizeInventory()];
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

	// implements IInventory
	@Override
	public int getSizeInventory()
	{
		return this.items.length;
	}
	@Override
	public ItemStack getStackInSlot(int var1)
	{
		return this.items[0];
	}

	@Override
	public ItemStack decrStackSize(int i, int j)
	{
		if (this.items[0] != null)
		{
			if (this.items[0].stackSize <= j)
			{
				ItemStack is = this.items[0];
				this.items[0] = null;
				this.onInventoryChanged();
				return is;
			}
			ItemStack is = items[0].splitStack(j);
			if (items[0].stackSize == 0)
			{
				items[0] = null;
			}
			this.onInventoryChanged();
			return is;
		}
		else
		{
			return null;
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i)
	{
		return this.items[0];
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack)
	{
		this.items[0] = itemstack;
		if (itemstack != null && itemstack.stackSize > getInventoryStackLimit())
		{
			itemstack.stackSize = this.getInventoryStackLimit();
		}
		this.onInventoryChanged();
	}

	@Override
	public String getInvName()
	{
		return "Toolrack";
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
			this.worldObj.addBlockEvent(xCoord, yCoord, zCoord, Toolrack.blockToolrack.blockID, 0, 0);
		}
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player)
	{
		if (worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) != this)
		{
			return false;
		}
		else
		{
			return player.getDistanceSq((double)xCoord + 0.5D, (double)yCoord + 0.5D, (double)zCoord + 0.5D) <= 64D;
		}
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
	public boolean isInvNameLocalized()
	{
		return false;
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack)
	{
		return false;
	}

}
