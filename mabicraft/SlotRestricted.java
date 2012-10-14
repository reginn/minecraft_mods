package rgn.mods.mabicraft;

import net.minecraft.src.*;

public class SlotRestricted extends Slot
{
	private int validItemId;
	public SlotRestricted(IInventory iinventory, int par3, int par4, int par5, int restrictedItemID)
	{
		super(iinventory, par3, par4, par5);
		this.validItemId = restrictedItemID;
	}
	
	@Override
	public boolean isItemValid(ItemStack itemstack)
	{
		return itemstack.itemID == this.validItemId;
	}
	
}
