package rgn.mods.dwarventools;

import net.minecraft.src.*;

public class ContainerInfernalFurnace extends Container
{
	private TileEntityInfernalFurnace furnace;
	private int lastCookTime = 0;
	private int lastBurnTime = 0;
	private int lastItemBurnTime = 0;
	
	public ContainerInfernalFurnace(InventoryPlayer par1InventoryPlayer, TileEntityInfernalFurnace par2TileEntityInfernalFurnace)
	{
		this.furnace = par2TileEntityInfernalFurnace;
		this.addSlotToContainer(new Slot(par2TileEntityInfernalFurnace, 0, 56, 17));
		this.addSlotToContainer(new Slot(par2TileEntityInfernalFurnace, 1, 56, 53));
		this.addSlotToContainer(new SlotInfernalFurnace(par1InventoryPlayer.player, par2TileEntityInfernalFurnace, 2, 116, 35));
		int var3;
		
		for (var3 = 0; var3 < 3; ++var3)
		{
			for (int var4 = 0; var4 < 9; ++var4)
			{
				this.addSlotToContainer(new Slot(par1InventoryPlayer, var4 + var3 * 9 + 9, 8 + var4 * 18, 84 + var3 * 18));
			}
		}
		
		for (var3 = 0; var3 < 9; ++var3)
		{
			this.addSlotToContainer(new Slot(par1InventoryPlayer, var3, 8 + var3 * 18, 142));
		}
	}
	
	public void updateCraftingResults()
	{
		super.updateCraftingResults();
		
		for (int var1 = 0; var1 < this.crafters.size(); ++var1)
		{
			ICrafting player = (ICrafting)this.crafters.get(var1);
			
			if (this.lastCookTime != this.furnace.furnaceCookTime)
			{
				player.updateCraftingInventoryInfo(this, 0, this.furnace.furnaceCookTime);
			}
			
			if (this.lastBurnTime != this.furnace.furnaceBurnTime)
			{
				player.updateCraftingInventoryInfo(this, 1, this.furnace.furnaceBurnTime);
			}
			
			if (this.lastItemBurnTime != this.furnace.currentItemBurnTime)
			{
				player.updateCraftingInventoryInfo(this, 2, this.furnace.currentItemBurnTime);
			}
		}
		
		this.lastCookTime = this.furnace.furnaceCookTime;
		this.lastBurnTime = this.furnace.furnaceBurnTime;
		this.lastItemBurnTime = this.furnace.currentItemBurnTime;
	}
	
	public void updateProgressBar(int par1, int par2)
	{
		if (par1 == 0)
		{
			this.furnace.furnaceCookTime = par2;
		}
		
		if (par1 == 1)
		{
			this.furnace.furnaceBurnTime = par2;
		}
		
		if (par1 == 2)
		{
			this.furnace.currentItemBurnTime = par2;
		}
	}
	
	public boolean canInteractWith(EntityPlayer par1EntityPlayer)
	{
		return this.furnace.isUseableByPlayer(par1EntityPlayer);
	}
	
	// public ItemStack transferStackInSlot(int par1)

	/**
	 * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
	 */
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par1)
	{
		ItemStack var2 = null;
		Slot var3 = (Slot)this.inventorySlots.get(par1);
		
		if (var3 != null && var3.getHasStack())
		{
			ItemStack var4 = var3.getStack();
			var2 = var4.copy();
		
			if (par1 == 2)
			{
				if (!this.mergeItemStack(var4, 3, 39, true))
				{
					return null;
				}
			
			var3.onSlotChange(var4, var2);
			}
			else if (par1 != 1 && par1 != 0)
			{
				if (FurnaceRecipes.smelting().getSmeltingResult(var4) != null)
				{
					if (!this.mergeItemStack(var4, 0, 1, false))
					{
						return null;
					}
				}
				else if (TileEntityInfernalFurnace.isItemFuel(var4))
				{
					if (!this.mergeItemStack(var4, 1, 2, false))
					{
						return null;
					}
				}
				else if (par1 >= 3 && par1 < 30)
				{
					if (!this.mergeItemStack(var4, 30, 39, false))
					{
						return null;
					}
				}
				else if (par1 >= 30 && par1 < 39 && !this.mergeItemStack(var4, 3, 30, false))
				{
					return null;
				}
			}
			else if (!this.mergeItemStack(var4, 3, 39, false))
			{
				return null;
			}
			
			if (var4.stackSize == 0)
			{
				var3.putStack((ItemStack)null);
			}
			else
			{
				var3.onSlotChanged();
			}
			
			if (var4.stackSize == var2.stackSize)
			{
				return null;
			}
			
			// var3.onPickupFromSlot(var4);
			var3.onPickupFromSlot(par1EntityPlayer, var4);
		}
		
		return var2;
	}
}
