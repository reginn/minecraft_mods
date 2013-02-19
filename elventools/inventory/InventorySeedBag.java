package rgn.mods.elventools.inventory;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import rgn.mods.elventools.network.PacketHandler;

public class InventorySeedBag implements IInventory
{
	private ItemStack seedBag;
	private ItemStack[] items;

	public InventorySeedBag(ItemStack itemStack)
	{
		this.seedBag = itemStack;
		this.readFromNBT(this.seedBag.getTagCompound());
	}

	public void readFromNBT(NBTTagCompound nbtTagCompound)
	{
		this.items = new ItemStack[this.getSizeInventory()];
		
		if (nbtTagCompound != null)
		{
			NBTTagList nbtTagList = nbtTagCompound.getTagList("Items");
			for (int i = 0; i < nbtTagList.tagCount(); ++i)
			{
				NBTTagCompound slotNbtTagCompound = (NBTTagCompound)nbtTagList.tagAt(i);
				int j = slotNbtTagCompound.getByte("Slot") & 0xff;
				if (j >= 0 && j < this.getSizeInventory())
				{
					this.items[j] = ItemStack.loadItemStackFromNBT(slotNbtTagCompound);
				}
			}
		}
	}

	public void writeToNBT(NBTTagCompound nbtTagCompound)
	{
		NBTTagList nbtTagList = new NBTTagList();
		for (int i = 0; i < this.getSizeInventory(); ++i)
		{
			if (this.items[i] != null)
			{
				NBTTagCompound slotNbtTagCompound = new NBTTagCompound();
				slotNbtTagCompound.setByte("Slot", (byte)i);
				this.items[i].writeToNBT(slotNbtTagCompound);
				nbtTagList.appendTag(slotNbtTagCompound);
			}
		}
		
		nbtTagCompound.setTag("Items", nbtTagList);
	}
	
	public void save()
	{
		NBTTagCompound nbtTagCompound = this.seedBag.getTagCompound();
		if (nbtTagCompound == null)
		{
			nbtTagCompound = new NBTTagCompound();
		}
		this.writeToNBT(nbtTagCompound);
		this.seedBag.setTagCompound(nbtTagCompound);
		if (FMLCommonHandler.instance().getSide() == Side.CLIENT)
		{
			PacketDispatcher.sendPacketToServer(PacketHandler.getPacketSeedBagItems(this));
		}
	}
	
	// implements IInventory
	@Override
	public boolean isUseableByPlayer(EntityPlayer player)
	{
		ItemStack equippedItem = player.getCurrentEquippedItem();
		if (((ContainerSeedBag)player.openContainer).inventorySeedBag != this)
		{
			return false;
		}
		return true;
	}

	@Override
	public void onInventoryChanged()
	{
		this.save();
	}

	@Override
	public void openChest()
	{
	}

	@Override
	public void closeChest()
	{
		this.save();
	}

	@Override
	public int getSizeInventory()
	{
		return 9;
	}

	@Override
	public ItemStack getStackInSlot(int index)
	{
		return this.items[index];
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
				this.onInventoryChanged();
				return itemstack;
			}
			ItemStack itemstack1 = items[i].splitStack(j);
			if (items[i].stackSize == 0)
			{
				items[i] = null;
			}
			this.onInventoryChanged();
			return itemstack1;
		}
		else
		{
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack)
	{
		this.items[i] = itemstack;
		if (itemstack != null && itemstack.stackSize > getInventoryStackLimit())
		{
			itemstack.stackSize = getInventoryStackLimit();
		}
		this.onInventoryChanged();
	}
	
	@Override
	public ItemStack getStackInSlotOnClosing(int i)
	{
		if (this.items[i] != null)
        {
            ItemStack is = this.items[i];
            this.items[i] = null;
            return is;
        }
        else
        {
            return null;
        }
	}
	
	public ItemStack getSeedBag()
	{
		return this.seedBag;
	}
	
	@Override
	public String getInvName()
	{
		return "SeedBag";
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}
}