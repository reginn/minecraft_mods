package rgn.mods.mabicraft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotQuestBoard extends Slot
{
	private EntityPlayer player;
	private IInventory inventory;
	private int amountCrafted;

	public SlotQuestBoard(EntityPlayer player, IInventory inventory, IInventory result, int slotNum, int x, int y)
	{
		super(result, slotNum, x, y);
		this.player = player;
		this.inventory = inventory;
	}

	@Override
	public boolean isItemValid(ItemStack par1ItemStack)
	{
		return false;
	}

	public void onPickupFromSlot(EntityPlayer entityPlayer, ItemStack itemStack)
	{
		ItemStack is = this.inventory.getStackInSlot(0);

		if (is != null)
		{
			this.inventory.decrStackSize(0, 10);
		}
	}
}
