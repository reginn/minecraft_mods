package rgn.mods.mabicraft.inventory;

import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.google.common.io.ByteArrayDataInput;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import cpw.mods.fml.common.network.PacketDispatcher;

import rgn.mods.mabicraft.block.MabiCraftBlock;
import rgn.mods.mabicraft.item.MabiCraftItem;
import rgn.mods.mabicraft.item.enchanting.EnchantmentManager;
import rgn.mods.mabicraft.network.PacketHandler;

public class ContainerEnchanter extends Container
{
	public InventoryEnchanting inventoryEnchanter = new InventoryEnchanting(this, "enchanter", 4);
	private World world;
	private int xCoord;
	private int yCoord;
	private int zCoord;
	private int probability;
	private byte buttonId = -1;
	private EntityPlayer player;
	private IInventory playerInventory;

	private Random random = new Random();

	public ContainerEnchanter(EntityPlayer player, World world, int x, int y, int z)
	{
		this.world  = world;
		this.player = player;
		this.xCoord = x;
		this.yCoord = y;
		this.zCoord = z;
		this.playerInventory = player.inventory;

		addSlotToContainer(new            Slot(inventoryEnchanter, 0,  26, 12));
		addSlotToContainer(new  SlotRestricted(inventoryEnchanter, 1,  26, 12 + 18, MabiCraftItem.itemEnchantScroll.itemID));
		addSlotToContainer(new  SlotRestricted(inventoryEnchanter, 2,  26, 12 + 36, MabiCraftItem.itemMagicPowder.itemID));
		addSlotToContainer(new      SlotResult(inventoryEnchanter, 3, 134, 12 + 18));

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
		return this.world.getBlockId(this.xCoord, this.yCoord, this.zCoord) != MabiCraftBlock.blockEnchanter.blockID ? false
			: entityPlayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
	}

	@Override
	public void onCraftGuiClosed(EntityPlayer entityPlayer)
	{
		super.onCraftGuiClosed(entityPlayer);

		if (!this.world.isRemote)
		{
			List<ItemStack> dropItemList = new ArrayList<ItemStack>();

			for (int i = 0; i < this.inventoryEnchanter.getSizeInventory(); ++i)
			{
				dropItemList.add(this.inventoryEnchanter.getStackInSlotOnClosing(i));
			}

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
				if (!this.mergeItemStack(temp, 3, 39, true))
				{
					return null;
				}

				slot.onSlotChange(temp, itemstack);
			}
			else if (slotIndex != 0 && slotIndex != 1 && slotIndex != 2)
			{
				if (temp.getItem().getItemEnchantability() > 0)
				{
					if (!this.mergeItemStack(temp, 0, 1, false))
					{
						return null;
					}
				}
				else if (temp.itemID == MabiCraftItem.itemEnchantScroll.itemID)
				{
					if (!this.mergeItemStack(temp, 1, 2, false))
					{
						return null;
					}
				}
				else if (temp.itemID == MabiCraftItem.itemMagicPowder.itemID)
				{
					if (!this.mergeItemStack(temp, 2, 3, false))
					{
						return null;
					}
				}
				else if (slotIndex >= 4 && slotIndex < 31)
				{
					if (!this.mergeItemStack(temp, 31, 40, false))
					{
						return null;
					}
				}
				else if (slotIndex >= 31 && slotIndex < 40 && !this.mergeItemStack(temp, 4, 31, false))
				{
					return null;
				}
			}
			else if (!this.mergeItemStack(temp, 4, 40, false))
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

	/*
	@Override
	public void detectAndSendChanges()
	{
		super.detectAndSendChanges();

		Iterator var1 = this.crafters.iterator();

		while (var1.hasNext())
		{
			ICrafting var2 = (ICrafting)var1.next();
			for (int i = 0; i < this.inventoryEnchanter.getSizeInventory(); ++i)
			{
				var2.sendSlotContents(this, i, this.getSlot(i).getStack());
			}
		}
	}
	*/

	@Override
	public void onCraftMatrixChanged(IInventory par1IInventory)
	{
		if (buttonId == 0)
		{
			if (this.inventoryEnchanter.getStackInSlot(0) != null &&
				this.inventoryEnchanter.getStackInSlot(1) != null &&
				this.inventoryEnchanter.getStackInSlot(3) == null)
			{
				if (EnchantmentManager.instance().canEnchantItem(this.inventoryEnchanter) &&
					EnchantmentManager.instance().canApplyTogether(this.inventoryEnchanter))
				{
					this.inventoryEnchanter.setInventorySlotContents(3, EnchantmentManager.instance().tryEnchanting(this.player, this.inventoryEnchanter));
				}
				else
				{
					this.player.addChatMessage("Can't Enchant, or can't apply...");
				}
			}
			this.detectAndSendChanges();
			this.buttonId = -1;
			return ;
		}
	}

	public int getProbability()
	{
		this.probability = EnchantmentManager.instance().calcProbability(this.inventoryEnchanter);
		return this.probability;
	}

	public void onButtonPushed(int buttonId)
	{
		this.buttonId = (byte)buttonId;
		PacketDispatcher.sendPacketToServer(PacketHandler.getPacket(this));
	}

	// custom packet
	public void readPacketData(ByteArrayDataInput data)
	{
		try
		{
			this.buttonId    = data.readByte();
			this.probability = (int)data.readByte();
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
			dos.writeByte(this.buttonId);
			dos.writeByte((byte)this.probability);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}