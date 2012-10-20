package rgn.mods.ozen;

import net.minecraft.src.*;

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
	public void handlePacketData(int[] intData)
	{
		if (intData != null)
		{
			int pos = 0;
			if (intData.length < this.items.length * 3)
			{
				return;
			}
			for (int i = 0; i < this.items.length; i++)
			{
				if (intData[pos + 2] != 0)
				{
					ItemStack is = new ItemStack(intData[pos], intData[pos + 2], intData[pos + 1]);
					this.items[i] = is;
				}
				else
				{
					this.items[i] = null;
				}
				pos += 3;
			}
		}
	}
	
	public int[] buildIntDataList()
	{
		int[] sortList = new int[this.items.length * 3];
		int pos = 0;
		for (ItemStack is : this.items)
		{
			if (is != null)
			{
				sortList[pos++] = is.itemID;
				sortList[pos++] = is.getItemDamage();
				sortList[pos++] = is.stackSize;
			}
			else
			{
				sortList[pos++] = 0;
				sortList[pos++] = 0;
				sortList[pos++] = 0;
			}
		}
		return sortList;
	}
	
	@Override
	public Packet getAuxillaryInfoPacket()
	{
		return PacketHandler.getPacket(this);
	}
}