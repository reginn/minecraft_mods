package rgn.mods.mabicraft.inventory;

import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.google.common.io.ByteArrayDataInput;

import net.minecraft.entity.item.EntityXPOrb;
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

public class ContainerBonfire extends Container
{
	public InventoryEnchanting inventoryInput         = new InventoryEnchanting(this, "bonfire", 3);
	public InventoryEnchanting inventoryEnchantScroll = new InventoryEnchanting(this, "es", 1);
	public InventoryEnchanting inventoryMagicPowder   = new InventoryEnchanting(this, "mp", 1);
	private World world;
	private int xCoord;
	private int yCoord;
	private int zCoord;
	private byte buttonId = -1;
	private int probability;
	private EntityPlayer player;
	private IInventory playerInventory;

	private Random random = new Random();

	public ContainerBonfire(EntityPlayer player, World world, int x, int y, int z)
	{
		this.world  = world;
		this.xCoord = x;
		this.yCoord = y;
		this.zCoord = z;

		this.player = player;
		this.playerInventory = player.inventory;

		addSlotToContainer(new SlotEnchantedItem(inventoryInput, 0, 26, 17));
		addSlotToContainer(new SlotRestricted(inventoryInput, 1, 26, 17 + 18, MabiCraftItem.itemManaHerb.itemID));
		addSlotToContainer(new SlotRestricted(inventoryInput, 2, 26, 17 + 36, MabiCraftItem.itemBlessedPotion.itemID));

		addSlotToContainer(new SlotResult(inventoryEnchantScroll, 0, 134, 17));
		addSlotToContainer(new SlotResult(inventoryMagicPowder, 0, 134, 17 + 36));

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
		return this.world.getBlockId(this.xCoord, this.yCoord, this.zCoord) != MabiCraftBlock.blockBonfire.blockID ? false
			: entityPlayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
	}

	@Override
	public void onCraftGuiClosed(EntityPlayer entityPlayer)
	{
		super.onCraftGuiClosed(entityPlayer);

		if (!this.world.isRemote)
		{
			List<ItemStack> dropItemList = new ArrayList<ItemStack>();

			for (int i = 0; i < this.inventoryInput.getSizeInventory(); ++i)
			{
				dropItemList.add(this.inventoryInput.getStackInSlotOnClosing(i));
			}

			dropItemList.add(this.inventoryEnchantScroll.getStackInSlotOnClosing(0));
			dropItemList.add(this.inventoryMagicPowder.getStackInSlotOnClosing(0));

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

			if (slotIndex == 3 || slotIndex == 4)
			{
				if (!this.mergeItemStack(temp, 5, 39, true))
				{
					return null;
				}

				slot.onSlotChange(temp, itemstack);
			}
			else if (slotIndex != 0 && slotIndex != 1 && slotIndex != 2)
			{
				if (temp.isItemEnchanted())
				{
					if (!this.mergeItemStack(temp, 0, 1, false))
					{
						return null;
					}
				}
				else if (temp.itemID == MabiCraftItem.itemManaHerb.itemID)
				{
					if (!this.mergeItemStack(temp, 1, 2, false))
					{
						return null;
					}
				}
				else if (temp.itemID == MabiCraftItem.itemBlessedPotion.itemID)
				{
					if (!this.mergeItemStack(temp, 2, 3, false))
					{
						return null;
					}
				}
				else if (slotIndex >= 5 && slotIndex < 32)
				{
					if (!this.mergeItemStack(temp, 32, 41, false))
					{
						return null;
					}
				}
				else if (slotIndex >= 32 && slotIndex < 41 && !this.mergeItemStack(temp, 5, 32, false))
				{
					return null;
				}
			}
			else if (!this.mergeItemStack(temp, 5, 41, false))
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


			for (int i = 0; i < this.inventorySlots.getSizeInventory(); ++i)
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
			if (this.inventoryEnchantScroll.getStackInSlot(0) == null)
			{
				this.inventoryEnchantScroll.setInventorySlotContents(0, EnchantmentManager.instance().tryExtraction(this.player, this.inventoryInput));
				if (this.inventoryEnchantScroll.getStackInSlot(0) != null)
				{
					this.inventoryMagicPowder.setInventorySlotContents(0, EnchantmentManager.instance().getMagicPowder(this.inventoryMagicPowder));
				}
			}
			this.detectAndSendChanges();
			this.buttonId = -1;
			return ;
		}

		if (buttonId == 1)
		{
			// this.inventoryBonfire.setInventorySlotContents(0, null)
			this.toolBurning();
			this.detectAndSendChanges();
			this.buttonId = -1;
			return ;
		}
	}

	public int getProbability()
	{
		this.probability = EnchantmentManager.instance().calcProbability(inventoryInput);
		return this.probability;
	}

	public void toolBurning()
	{
		ItemStack tool = this.inventoryInput.getStackInSlot(0);

		if (tool != null)
		{
			if (!world.isRemote)
			{
				int exp;
				int enchantmentWeight = 1;
				int enchantability;
				if (tool.isItemEnchanted())
				{
					enchantmentWeight = EnchantmentManager.instance().getSumEnchantmentWeight(tool);
				}
				enchantability = tool.getItem().getItemEnchantability();
				exp = enchantmentWeight * enchantability;

				while (exp > 0)
				{
					int splitExp = EntityXPOrb.getXPSplit(exp);
					exp -= splitExp;
					world.spawnEntityInWorld(new EntityXPOrb(world, this.xCoord, this.yCoord, this.zCoord, splitExp));
				}
				this.inventoryInput.setInventorySlotContents(0, null);
				//this.detectAndSendChanges();
				player.addChatMessage("Buring finished");
			}
		}
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