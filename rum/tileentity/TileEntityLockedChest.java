package rgn.mods.rum.tileentity;

import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.packet.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

import rgn.mods.rum.block.RumBlock;
import rgn.mods.rum.inventory.ContainerLockedChest;
import rgn.mods.rum.network.FMLPacketHandler;

public class TileEntityLockedChest extends TileEntity implements IInventory
{
	private ItemStack[] contents;
	private int numUsingPlayers;
	private int ticksSinceSync;
	public float prevLidAngle;
	public float lidAngle;
	private boolean inventoryTouched;
	private boolean isLocked;
	private int emptyTicks;

	public TileEntityLockedChest()
	{
		this.contents = new ItemStack[9];
		this.isLocked = true;
	}

	public void setLocked()
	{
		this.isLocked = true;
	}

	public void setUnlocked()
	{
		this.isLocked = false;
		this.worldObj.playSoundEffect((double)xCoord, (double)yCoord + 0.5D, (double)zCoord,
				"random.door_close", 0.5F, this.worldObj.rand.nextFloat() * 0.1F + 0.9F);
	}

	public boolean isLocked()
	{
		return this.isLocked;
	}

	@Override
	public void updateEntity()
	{
		super.updateEntity();
		// Re-synchronize clients with the server state
		if (worldObj != null && !this.worldObj.isRemote && this.numUsingPlayers != 0 && (this.ticksSinceSync + this.xCoord + this.yCoord + this.zCoord) % 200 == 0)
		{
			this.numUsingPlayers = 0;
			float var1 = 5.0F;
			List var2 = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getAABBPool().getAABB((double)((float)this.xCoord - var1), (double)((float)this.yCoord - var1), (double)((float)this.zCoord - var1), (double)((float)(this.xCoord + 1) + var1), (double)((float)(this.yCoord + 1) + var1), (double)((float)(this.zCoord + 1) + var1)));
			Iterator var3 = var2.iterator();

			while (var3.hasNext())
			{
				EntityPlayer var4 = (EntityPlayer)var3.next();

				if (var4.openContainer instanceof ContainerLockedChest)
				{
					++this.numUsingPlayers;
				}
			}
		}

		if (worldObj != null && !worldObj.isRemote && ticksSinceSync < 0)
		{
			worldObj.addBlockEvent(xCoord, yCoord, zCoord, RumBlock.blockLockedChest.blockID, 1, numUsingPlayers);
		}
		if (!worldObj.isRemote && inventoryTouched)
		{
			inventoryTouched = false;
		}

		this.ticksSinceSync++;
		prevLidAngle = lidAngle;
		float f = 0.1F;
		if (numUsingPlayers > 0 && lidAngle == 0.0F)
		{
			double d = (double) xCoord + 0.5D;
			double d1 = (double) zCoord + 0.5D;
			worldObj.playSoundEffect(d, (double) yCoord + 0.5D, d1, "random.chestopen", 0.5F, worldObj.rand.nextFloat() * 0.1F + 0.9F);
		}
		if (numUsingPlayers == 0 && lidAngle > 0.0F || numUsingPlayers > 0 && lidAngle < 1.0F)
		{
			float f1 = lidAngle;
			if (numUsingPlayers > 0)
			{
				lidAngle += f;
			}
			else
			{
				lidAngle -= f;
			}
			if (lidAngle > 1.0F)
			{
				lidAngle = 1.0F;
			}
			float f2 = 0.5F;
			if (lidAngle < f2 && f1 >= f2)
			{
				double d2 = (double) xCoord + 0.5D;
				double d3 = (double) zCoord + 0.5D;
				worldObj.playSoundEffect(d2, (double) yCoord + 0.5D, d3, "random.chestclosed", 0.5F, worldObj.rand.nextFloat() * 0.1F + 0.9F);
			}
			if (lidAngle < 0.0F)
			{
				lidAngle = 0.0F;
			}
		}

		if (numUsingPlayers == 0 && isContentsEmpty() && !this.isLocked)
		{
			emptyTicks++;
		}

		if (emptyTicks == 100)
		{
			worldObj.setBlockToAir(xCoord, yCoord, zCoord);
		}
	}

	private boolean isContentsEmpty()
	{
		boolean[] isEmpty = new boolean[9];
		for (int i = 0; i < this.getSizeInventory(); ++i)
		{
			isEmpty[i] = this.contents[i] == null;
		}

		boolean ret = true;
		for (boolean b : isEmpty)
		{
			ret &= b;
		}

		return ret;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbttagcompound)
	{
		super.readFromNBT(nbttagcompound);
		NBTTagList nbttaglist = nbttagcompound.getTagList("Items");
		contents = new ItemStack[getSizeInventory()];
		for (int i = 0; i < nbttaglist.tagCount(); i++)
		{
			NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.tagAt(i);
			int j = nbttagcompound1.getByte("Slot") & 0xff;
			if (j >= 0 && j < contents.length)
			{
				contents[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}
		this.isLocked = nbttagcompound.getBoolean("isLocked");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbttagcompound)
	{
		super.writeToNBT(nbttagcompound);
		NBTTagList nbttaglist = new NBTTagList();
		for (byte byte0 = 0; byte0 < contents.length; byte0++)
		{
			if (contents[byte0] != null)
			{
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", byte0);
				contents[byte0].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}

		nbttagcompound.setTag("Items", nbttaglist);
		nbttagcompound.setBoolean("isLocked", this.isLocked);
	}

	@Override
	public int getSizeInventory()
	{
		return 9;
	}

	@Override
	public ItemStack getStackInSlot(int i)
	{
		return this.contents[i];
	}

	@Override
	public ItemStack decrStackSize(int i, int j)
	{
		if (this.contents[i] != null)
		{
			if (this.contents[i].stackSize <= j)
			{
				ItemStack itemstack = this.contents[i];
				this.contents[i] = null;
				onInventoryChanged();
				return itemstack;
			}

			ItemStack itemstack1 = this.contents[i].splitStack(j);

			if (this.contents[i].stackSize == 0)
			{
				this.contents[i] = null;
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
	public ItemStack getStackInSlotOnClosing(int i)
	{
		return this.contents[i];
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack)
	{
		this.contents[i] = itemstack;
		if (itemstack != null && itemstack.stackSize > getInventoryStackLimit())
		{
			itemstack.stackSize = getInventoryStackLimit();
		}
		this.onInventoryChanged();
	}

	@Override
	public String getInvName()
	{
		return "lockedchest";
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
	public int getInventoryStackLimit()
	{
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer)
	{
		if (this.worldObj != null && this.worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) != this)
		{
			return false;
		}
		return entityplayer.getDistanceSq((double)xCoord + 0.5D, (double)yCoord + 0.5D, (double)zCoord + 0.5D) <= 64D;
	}

	@Override
	public boolean receiveClientEvent(int i, int j)
	{
		if (i == 1)
		{
			numUsingPlayers = j;
		}
		return true;
	}

	@Override
	public void openChest()
	{
		if (worldObj == null)
		{
			return;
		}
		this.numUsingPlayers++;
		this.worldObj.addBlockEvent(xCoord, yCoord, zCoord, RumBlock.blockLockedChest.blockID, 1, numUsingPlayers);
	}

	@Override
	public void closeChest()
	{
		if (worldObj == null)
		{
			return;
		}
		this.numUsingPlayers--;
		this.worldObj.addBlockEvent(xCoord, yCoord, zCoord, RumBlock.blockLockedChest.blockID, 1, numUsingPlayers);
	}

	@Override
	public boolean isStackValidForSlot(int i, ItemStack itemstack)
	{
		return false;
	}

	@Override
	public Packet getDescriptionPacket()
	{
		return FMLPacketHandler.getLockedChestPacket(this);
	}
}
