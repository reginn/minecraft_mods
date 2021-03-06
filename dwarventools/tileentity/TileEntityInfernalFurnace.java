package rgn.mods.dwarventools.tileentity;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import rgn.mods.dwarventools.block.BlockInfernalFurnace;

public class TileEntityInfernalFurnace extends TileEntity implements ISidedInventory
{
	private static final int[] slots_top = new int[] {0};
	private static final int[] slots_bottom = new int[] {2, 1};
	private static final int[] slots_sides = new int[] {1};

	private ItemStack[] furnaceItemStacks = new ItemStack[3];

	public int furnaceBurnTime     = 0;
	public int currentItemBurnTime = 0;
	public int furnaceCookTime     = 0;

	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.readFromNBT(par1NBTTagCompound);
		NBTTagList itemsTagList = par1NBTTagCompound.getTagList("Items");

		this.furnaceItemStacks = new ItemStack[this.getSizeInventory()];

		for (int i = 0; i < itemsTagList.tagCount(); ++i)
		{
			NBTTagCompound itemTagCompound = (NBTTagCompound)itemsTagList.tagAt(i);
			byte slotIndex = itemTagCompound.getByte("Slot");

			if (slotIndex >= 0 && slotIndex < this.furnaceItemStacks.length)
			{
				this.furnaceItemStacks[slotIndex] = ItemStack.loadItemStackFromNBT(itemTagCompound);
			}
		}

		this.furnaceBurnTime = par1NBTTagCompound.getShort("BurnTime");
		this.furnaceCookTime = par1NBTTagCompound.getShort("CookTime");
		this.currentItemBurnTime = getItemBurnTime(this.furnaceItemStacks[1]);
	}

	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.writeToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setShort("BurnTime", (short)this.furnaceBurnTime);
		par1NBTTagCompound.setShort("CookTime", (short)this.furnaceCookTime);
		NBTTagList itemsTagList = new NBTTagList();

		for (int slotIndex = 0; slotIndex < this.furnaceItemStacks.length; ++slotIndex)
		{
			if (this.furnaceItemStacks[slotIndex] != null)
			{
				NBTTagCompound itemTagCompound = new NBTTagCompound();
				itemTagCompound.setByte("Slot", (byte)slotIndex);
				this.furnaceItemStacks[slotIndex].writeToNBT(itemTagCompound);
				itemsTagList.appendTag(itemTagCompound);
			}
		}
		par1NBTTagCompound.setTag("Items", itemsTagList);
	}

	// implements IInventory
	@Override
	public int getSizeInventory()
	{
		return this.furnaceItemStacks.length;
	}

	@Override
	public ItemStack getStackInSlot(int slotIndex)
	{
		return this.furnaceItemStacks[slotIndex];
	}

	@Override
	public ItemStack decrStackSize(int slotIndex, int stackSize)
	{
		if (this.furnaceItemStacks[slotIndex] != null)
		{
			ItemStack itemstack;

			if (this.furnaceItemStacks[slotIndex].stackSize <= stackSize)
			{
				itemstack = this.furnaceItemStacks[slotIndex];
				this.furnaceItemStacks[slotIndex] = null;
				return itemstack;
			}
			else
			{
				itemstack = this.furnaceItemStacks[slotIndex].splitStack(stackSize);

				if (this.furnaceItemStacks[slotIndex].stackSize == 0)
				{
					this.furnaceItemStacks[slotIndex] = null;
				}

				return itemstack;
			}
		}
		else
		{
			return null;
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slotIndex)
	{
		if (this.furnaceItemStacks[slotIndex] != null)
		{
			ItemStack itemstack = this.furnaceItemStacks[slotIndex];
			this.furnaceItemStacks[slotIndex] = null;
			return itemstack;
		}
		else
		{
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int slotIndex, ItemStack itemstack)
	{
		this.furnaceItemStacks[slotIndex] = itemstack;

		if (itemstack != null && itemstack.stackSize > this.getInventoryStackLimit())
		{
			itemstack.stackSize = this.getInventoryStackLimit();
		}
	}

	@Override
	public String getInvName()
	{
		return "container.furnace";
	}

	@Override
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
	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
	{
		return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ?
			false : par1EntityPlayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
	}

	@Override
	public void openChest()
	{
	}

	@Override
	public void closeChest()
	{
	}

	@Override
	public boolean isItemValidForSlot(int par1, ItemStack par2ItemStack)
	{
		return par1 == 2 ? false : (par1 == 1 ? isItemFuel(par2ItemStack) : true);
	}

	@SideOnly(Side.CLIENT)
	public int getCookProgressScaled(int scale)
	{
		return this.furnaceCookTime * scale / 100;
	}

	@SideOnly(Side.CLIENT)
	public int getBurnTimeRemainingScaled(int scale)
	{
		if (this.currentItemBurnTime == 0)
		{
			this.currentItemBurnTime = 100;
		}
		return this.furnaceBurnTime * scale / this.currentItemBurnTime;
	}

	public boolean isBurning()
	{
		return this.furnaceBurnTime > 0;
	}

	@Override
	public void updateEntity()
	{
		boolean isBurning = this.furnaceBurnTime > 0;
		boolean var2 = false;

		if (this.furnaceBurnTime > 0)
		{
			--this.furnaceBurnTime;
		}

		if (!this.worldObj.isRemote)
		{
			if (this.furnaceBurnTime == 0 && this.canSmelt())
			{
				this.currentItemBurnTime = this.furnaceBurnTime = getItemBurnTime(this.furnaceItemStacks[1]);
				if (this.furnaceBurnTime > 0)
				{
					var2 = true;
					if (this.furnaceItemStacks[1] != null)
					{
						--this.furnaceItemStacks[1].stackSize;
						if (this.furnaceItemStacks[1].stackSize == 0)
						{
							this.furnaceItemStacks[1] = null;
						}
					}
				}
			}

			if (this.isBurning() && this.canSmelt())
			{
				++this.furnaceCookTime;
				if (this.furnaceCookTime == 100)
				{
					this.furnaceCookTime = 0;
					this.smeltItem();
					var2 = true;
				}
			}
			else
			{
				this.furnaceCookTime = 0;
			}

			if (isBurning != this.furnaceBurnTime > 0)
			{
				var2 = true;
				BlockInfernalFurnace.updateFurnaceBlockState(this.furnaceBurnTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
			}
		}

		if (var2)
		{
			this.onInventoryChanged();
		}
	}

	private boolean canSmelt()
	{
		if (this.furnaceItemStacks[0] == null)
		{
			return false;
		}
		else
		{
			ItemStack var1 = FurnaceRecipes.smelting().getSmeltingResult(this.furnaceItemStacks[0]);
			if (var1 == null)
			{
				return false;
			}
			if (this.furnaceItemStacks[2] == null)
			{
				return true;
			}
			if (!this.furnaceItemStacks[2].isItemEqual(var1))
			{
				return false;
			}
			int result = furnaceItemStacks[2].stackSize + var1.stackSize;
			return (result <= getInventoryStackLimit() && result <= var1.getMaxStackSize());
		}
	}

	public void smeltItem()
	{
		if (this.canSmelt())
		{
			ItemStack var1 = FurnaceRecipes.smelting().getSmeltingResult(this.furnaceItemStacks[0]);

			if (this.furnaceItemStacks[2] == null)
			{
				this.furnaceItemStacks[2] = var1.copy();
			}
			else if (this.furnaceItemStacks[2].isItemEqual(var1))
			{
				this.furnaceItemStacks[2].stackSize += var1.stackSize;
			}

			--this.furnaceItemStacks[0].stackSize;

			if (this.furnaceItemStacks[0].stackSize <= 0)
			{
				this.furnaceItemStacks[0] = null;
			}
		}
	}

	public static int getItemBurnTime(ItemStack par0ItemStack)
	{
		if (par0ItemStack == null)
		{
			return 0;
		}
		else
		{
			int var1 = par0ItemStack.getItem().itemID;
			Item item = par0ItemStack.getItem();

			if (par0ItemStack.getItem() instanceof ItemBlock && Block.blocksList[var1] != null)
			{
				Block var3 = Block.blocksList[var1];

				if (var3 == Block.woodSingleSlab)
				{
					return 150;
				}

				if (var3.blockMaterial == Material.wood)
				{
					return 300;
				}
			}

			if (item instanceof ItemTool  && ((ItemTool) item).getToolMaterialName().equals("WOOD")) return 200;
			if (item instanceof ItemSword && ((ItemSword)item).getToolMaterialName().equals("WOOD")) return 200;
			if (item instanceof ItemHoe   && ((ItemHoe)  item).getMaterialName().equals("WOOD")) return 200;
			if (var1 == Item.stick.itemID) return 100;
			if (var1 == Item.coal.itemID) return 1600;
			if (var1 == Item.bucketLava.itemID) return 20000;
			if (var1 == Block.sapling.blockID) return 100;
			if (var1 == Item.blazeRod.itemID) return 2400;
			return GameRegistry.getFuelValue(par0ItemStack);
		}
	}

	public static boolean isItemFuel(ItemStack par0ItemStack)
	{
		return getItemBurnTime(par0ItemStack) > 0;
	}

	//-- implements ISidedInventory
	@Override
	public int[] getAccessibleSlotsFromSide(int par1)
	{
		return par1 == 0 ? slots_bottom : (par1 == 1 ? slots_top : slots_sides);
	}

	@Override
	public boolean canInsertItem(int par1, ItemStack par2ItemStack, int par3)
	{
		return this.isItemValidForSlot(par1, par2ItemStack);
	}

	@Override
	public boolean canExtractItem(int par1, ItemStack par2ItemStack, int par3)
	{
		return par3 != 0 || par1 != 1 || par2ItemStack.itemID == Item.bucketEmpty.itemID;
	}
	//----------
}
