package rgn.mods.mabicraft.inventory;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import rgn.mods.mabicraft.item.MabiCraftItem;
import rgn.mods.mabicraft.item.quest.QuestBoardManager;

public class ContainerQuestBoard extends Container
{
	public IInventory inventory;
	public IInventory inventoryResult;

	private int x;
	private int y;
	private int z;
	private EntityPlayer player;
	private World world;

	public ContainerQuestBoard(EntityPlayer player, World world, int x, int y, int z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.player = player;
		this.world = world;

		this.inventory = new InventoryQuestBoard(this);
		this.inventoryResult = new InventoryCraftResult();

		this.addSlotToContainer(new SlotRestricted(this.inventory, 0, 80, 30, MabiCraftItem.itemEvilScroll.itemID));
		this.addSlotToContainer(new SlotQuestBoard(player, this.inventory, this.inventoryResult, 1, 134, 30));

		for (int col = 0; col < 3; ++col)
		{
			for (int row = 0; row < 9; ++row)
			{
				this.addSlotToContainer(new Slot(player.inventory, row + col * 9 + 9, 8 + row * 18, 84 + col * 18));
			}
		}

		for (int row = 0; row < 9; ++row)
		{
			this.addSlotToContainer(new Slot(player.inventory, row, 8 + row * 18, 142));
		}

		this.onCraftMatrixChanged(this.inventory);
	}

	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		return this.world.getBlockId(this.x, this.y, this.z) != Block.signWall.blockID ?
				false : player.getDistanceSq((double)this.x + 0.5D, (double)this.y + 0.5D, (double)this.z + 0.5D) <= 64.0D;
	}

	@Override
	public void onCraftGuiClosed(EntityPlayer player)
	{
		super.onCraftGuiClosed(player);

		if (!this.world.isRemote)
		{
			ItemStack is = this.inventory.getStackInSlotOnClosing(0);

			if (is != null)
			{
				player.dropPlayerItem(is);
			}
		}
	}

	public void onCraftMatrixChanged(IInventory inventory)
	{
		this.inventoryResult.setInventorySlotContents(0, QuestBoardManager.instance().findQuest(this.inventory, this.world));
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex)
	{
		ItemStack is = null;
		Slot slot = (Slot)this.inventorySlots.get(slotIndex);

		if (slot != null && slot.getHasStack())
		{
			ItemStack isTemp = slot.getStack();
			is = isTemp.copy();


			if (slotIndex == 1)
			{
				if (!this.mergeItemStack(isTemp, 2, 38, true))
				{
					return null;
				}

				slot.onSlotChange(isTemp, is);
			}
			else if (slotIndex != 0)
			{
				if (isTemp.itemID == MabiCraftItem.itemEvilScroll.itemID)
				{
					if (!this.mergeItemStack(isTemp, 0, 1, false))
					{
						return null;
					}
				}
			}
			else if (slotIndex >= 2 && slotIndex < 29)
			{
				if (!this.mergeItemStack(isTemp, 29, 38, false))
				{
					return null;
				}
			}
			else if (slotIndex >= 29 && slotIndex < 38)
			{
				if (!this.mergeItemStack(isTemp, 2, 29, false))
				{
					return null;
				}
			}
			else if (!this.mergeItemStack(isTemp, 2, 38, false))
			{
				return null;
			}

			if (isTemp.stackSize == 0)
			{
				slot.putStack((ItemStack)null);
			}
			else
			{
				slot.onSlotChanged();
			}

			if (isTemp.stackSize == is.stackSize)
			{
				return null;
			}
			slot.onPickupFromSlot(player, isTemp);
		}
		return is;
	}
}
