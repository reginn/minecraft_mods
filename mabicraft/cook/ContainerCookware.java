package rgn.mods.mabicraft.cook;

import java.util.Iterator;
import java.util.List;

import java.util.Random;

import java.lang.Math;

import java.io.DataOutputStream;
import java.io.IOException;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.collect.Lists;

import cpw.mods.fml.common.network.PacketDispatcher;

import net.minecraft.src.*;
import net.minecraftforge.common.*;

import rgn.mods.mabicraft.config.*;
import rgn.mods.mabicraft.core.*;

public class ContainerCookware extends Container
{
	public IInventory inventoryInput;
	public IInventory inventoryResult;
	private World world;
	private int xCoord;
	private int yCoord;
	private int zCoord;
	private int buttonId;
	private int[] ticks;
	private int metadata;
	private EntityPlayer player;
	private IInventory playerInventory;
	
	public ContainerCookware(EntityPlayer player, World world, int x, int y, int z)
	{
		this.world  = world;
		this.xCoord = x;
		this.yCoord = y;
		this.zCoord = z;
		
		this.buttonId = -1;
		this.ticks    = new int[]{0, 0, 0};
		this.metadata = world.getBlockMetadata(x, y, z) & 0x03;
		
		this.player = player;
		this.playerInventory = player.inventory;
		
		inventoryInput = new InventoryBasic("cookware", 3);
		inventoryResult = new InventoryBasic("result", 1);
		
		addSlotToContainer(new Slot(inventoryInput, 0, 18     , 18));
		addSlotToContainer(new Slot(inventoryInput, 1, 18 + 36, 18));
		addSlotToContainer(new Slot(inventoryInput, 2, 18 + 72, 18));
		
		addSlotToContainer(new SlotResult(inventoryResult, 0, 134, 22));
		
		for (int rows = 0; rows < 3; rows++)
		{
			for (int slotIndex = 0; slotIndex < 9; slotIndex++)
			{
				addSlotToContainer(new Slot(playerInventory, slotIndex + rows * 9 + 9, 8 + slotIndex * 18, 84 + rows * 18));
			}
		}
		
		for (int slotIndex = 0; slotIndex < 9; slotIndex++)
		{
			addSlotToContainer(new Slot(playerInventory, slotIndex, 8 + slotIndex * 18, 142));
		}
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer entityPlayer)
	{
		return this.world.getBlockId(this.xCoord, this.yCoord, this.zCoord) != MabiCraftBlock.blockCookware.blockID ? false 
			: entityPlayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
	}
	
	@Override
	public void onCraftGuiClosed(EntityPlayer entityPlayer)
	{
		super.onCraftGuiClosed(entityPlayer);
		
		if (!this.world.isRemote)
		{
			List<ItemStack> dropItemList = Lists.newArrayList();
			
			for (int i = 0; i < this.inventoryInput.getSizeInventory(); ++i)
			{
				dropItemList.add(this.inventoryInput.getStackInSlotOnClosing(i));
			}
			
			dropItemList.add(this.inventoryResult.getStackInSlotOnClosing(0));
			
			for (ItemStack dropItem : dropItemList)
			{
				if (dropItem != null)
				{
					entityPlayer.dropPlayerItem(dropItem);
				}
			}
		}
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int slotIndex)
	{
		ItemStack itemstack = null;
		Slot slot = (Slot)this.inventorySlots.get(slotIndex);
		
		if (slot != null && slot.getHasStack())
		{
			ItemStack temp = slot.getStack();
			itemstack = temp.copy();
			
			if (slotIndex == 3)
			{
				if (!this.mergeItemStack(temp, 4, 39, true))
				{
					return null;
				}
				
				slot.onSlotChange(temp, itemstack);
			}
			else if (slotIndex < 3)
			{
				if (slotIndex >= 4 && slotIndex < 31)
				{
					if (!this.mergeItemStack(temp, 31, 39, false))
					{
						return null;
					}
				}
				else if (slotIndex >= 31 && slotIndex < 39 && !this.mergeItemStack(temp, 4, 30, false))
				{
					return null;
				}
			}
			else if (!this.mergeItemStack(temp, 4, 39, false))
			{
				return null;
			}
			
			if (temp.stackSize == 0)
			{
				slot.putStack((ItemStack)null);
			}
			else
			{
				slot.onSlotChanged();
			}
			
			if (temp.stackSize == itemstack.stackSize)
			{
				return null;
			}
			
			slot.onPickupFromSlot(par1EntityPlayer, temp);
		}
		
		return itemstack;
	}
	
	@Override
	public void updateCraftingResults()
	{
		super.updateCraftingResults();
		
		Iterator var1 = this.crafters.iterator();
		
		while (var1.hasNext())
		{
			ICrafting var2 = (ICrafting)var1.next();
			for (int i = 0; i < this.inventoryInput.getSizeInventory(); ++i)
			{
				var2.sendSlotContents(this, i, this.getSlot(i).getStack());
			}
			var2.sendSlotContents(this, 3, this.getSlot(3).getStack());
		}
	}
	
	@Override
	public void onCraftMatrixChanged(IInventory par1IInventory)
	{
		if (!this.world.isRemote)
		{
			List<Ingredient> input = Lists.newArrayList();
			
			for (int idx = 0; idx < inventoryInput.getSizeInventory(); ++idx)
			{
				if (this.getSlot(idx).getStack() != null)
				{
					input.add(new Ingredient(this.getSlot(idx).getStack(), this.ticks[idx]));
					// System.out.printf("%d\n", this.ticks[idx]);
				}
			}
			
			if (this.buttonId == 3)
			{
				inventoryResult.setInventorySlotContents(0, CookingManager.instance().findMatchingRecipe(this.inventoryInput, input, this.metadata, player));
				this.buttonId = -1;
				for (int tick : this.ticks)
				{
					tick = 0;
				}
			}
		}
	}
	
	public void onClicked(int _buttonId, int[] _ticks)
	{
		this.buttonId = _buttonId;
		
		this.ticks = (int[])_ticks.clone();
		
		PacketDispatcher.sendPacketToServer(PacketHandler.getPacket(this));
	}
	
	// custom packet
	public void readPacketData(ByteArrayDataInput data)
	{
		try
		{
			this.buttonId = (int)data.readByte();
			for (int i = 0; i < 3; ++i)
			{
				this.ticks[i] = (int)data.readByte();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void writePacketData(DataOutputStream dos)
	{
		try
		{
			dos.writeByte((byte)this.buttonId);
			for (int i = 0; i < 3; ++i)
			{
				dos.writeByte((byte)this.ticks[i]);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
}