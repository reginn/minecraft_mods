package rgn.mods.mabicraft.inventory;

import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryBasic;

public class InventoryEnchanting extends InventoryBasic
{
	private Container eventHandler;

	public InventoryEnchanting(Container container, String name, int slotNum)
	{
		super(name, false, slotNum);
		this.eventHandler = container;
	}

	public Container getEventHandler()
	{
		return this.eventHandler;
	}
}