package rgn.mods.mabicraft.enchant;

import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import rgn.mods.mabicraft.core.MabiCraftBlock;
import rgn.mods.mabicraft.core.MabiCraftItem;
import rgn.mods.mabicraft.core.PacketHandler;
import rgn.mods.mabicraft.core.SlotRestricted;
import rgn.mods.mabicraft.core.SlotResult;

import com.google.common.io.ByteArrayDataInput;

import cpw.mods.fml.common.network.PacketDispatcher;

public class ContainerEnchanter extends Container
{
	public IInventory inventoryEnchanter = new InventoryEnchanter();
	private World world;
	private int xCoord;
	private int yCoord;
	private int zCoord;
	private byte buttonId = -1;
	private byte probability;
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
	// public ItemStack transferStackInSlot(int slotIndex)

	/**
	 * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
	 */
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

			// slot.onPickupFromSlot(temp);
			slot.onPickupFromSlot(par1EntityPlayer, temp);
		}

		return itemstack;
	}

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

	@Override
	public void onCraftMatrixChanged(IInventory par1IInventory)
	{
		if (buttonId == 0)
		{
			this.enchant();
			this.buttonId = -1;
			return ;
		}
	}

	public int getProbability()
	{
		this.probability = this.calcProbability();
		return this.probability;
	}

	public void onButtonPushed(int buttonId)
	{
		this.buttonId = (byte)buttonId;
		PacketDispatcher.sendPacketToServer(PacketHandler.getPacket(this));
	}

	private void enchant()
	{
		Slot objectiveToolSlot = this.getSlot(0);
		Slot enchantScrollSlot   = this.getSlot(1);
		Slot magicPowderSlot     = this.getSlot(2);

		if (objectiveToolSlot != null && objectiveToolSlot.getHasStack()
		 && enchantScrollSlot   != null && enchantScrollSlot.getHasStack())
		{
			ItemStack objectiveTool = objectiveToolSlot.getStack();
			ItemStack enchantScroll   = enchantScrollSlot.getStack();
			ItemStack resultTool      = objectiveTool.copy();
			ItemStack magicPowder = null;

			if (magicPowderSlot != null && magicPowderSlot.getHasStack())
			{
				magicPowder = magicPowderSlot.getStack();
			}

			//if (!objectiveTool.isItemEnchanted() && objectiveTool.getItem().getItemEnchantability() > 0 && this.isFitEnchantment(enchantScroll, objectiveTool))
			if (objectiveTool.getItem().getItemEnchantability() > 0 && this.isFitEnchantment(enchantScroll, objectiveTool))
			{
				if (!world.isRemote)
				{
					if (random.nextInt(100) > this.calcProbability())
					{
						this.putStackInSlot(1, null);
						if (magicPowder != null)
						{
							if (--magicPowder.stackSize == 0)
							{
								this.putStackInSlot(2, null);
							}
							else
							{
								this.putStackInSlot(2, magicPowder);
							}
						}
						this.player.addChatMessage("Enchantment failed");
						return ;
					}

					NBTTagList enchantList = enchantScroll.getEnchantmentTagList();

					for (int i = 0; i < enchantList.tagCount(); i++)
					{
						NBTTagCompound nbtTagCompound = (NBTTagCompound)enchantList.tagAt(i);

						if (objectiveTool.isItemEnchanted())
						{
							this.mergeEnchantment(resultTool, nbtTagCompound.getShort("id"), nbtTagCompound.getShort("lvl"));
						}
						else
						{
							this.setEnchantment(resultTool, nbtTagCompound.getShort("id"), nbtTagCompound.getShort("lvl"));
						}
					}

					this.putStackInSlot(0, null);
					this.putStackInSlot(1, null);

					if (magicPowder != null)
					{
						if (--magicPowder.stackSize == 0)
						{
							this.putStackInSlot(2, null);
						}
						else
						{
							this.putStackInSlot(2, magicPowder);
						}
					}

					this.putStackInSlot(3, resultTool);
					this.player.addChatMessage("Enchantment succeed");
				}
			}
			else
			{
				this.player.addChatMessage("Unfit enchantment");
			}
		}

	}

	private void mergeEnchantment(ItemStack objective, short id, short lvl)
	{
		NBTTagList nbtTagList = (NBTTagList)objective.stackTagCompound.getTag("ench");

		Set<Short> enchantmentIdSet = new HashSet<Short>();

		for (int i = 0; i < nbtTagList.tagCount(); ++i)
		{
			NBTTagCompound nbtTagCompound = (NBTTagCompound)nbtTagList.tagAt(i);
			enchantmentIdSet.add(new Short(nbtTagCompound.getShort("id")));
		}

		if (enchantmentIdSet.contains(new Short(id)))
		{
			for (int i = 0; i < nbtTagList.tagCount(); ++i)
			{
				NBTTagCompound nbtTagCompound = (NBTTagCompound)nbtTagList.tagAt(i);

				if (nbtTagCompound.getShort("id") == id && nbtTagCompound.getShort("lvl") < lvl)
				{
					nbtTagCompound.setShort("lvl", lvl);
				}
			}
		}
		else
		{
			this.setEnchantment(objective, id, lvl);
		}

	}

	private void setEnchantment(ItemStack objective, short id, short lvl)
	{
		if (objective.stackTagCompound == null)
		{
			objective.setTagCompound(new NBTTagCompound());
		}

		if (!objective.stackTagCompound.hasKey("ench"))
		{
			objective.stackTagCompound.setTag("ench", new NBTTagList("ench"));
		}

		NBTTagList nbtTagList = (NBTTagList)objective.stackTagCompound.getTag("ench");
		NBTTagCompound nbtTagCompound = new NBTTagCompound();

		nbtTagCompound.setShort("id", id);
		nbtTagCompound.setShort("lvl", lvl);
		nbtTagList.appendTag(nbtTagCompound);
	}

	private boolean isFitEnchantment(ItemStack enchantScroll, ItemStack objectiveTool)
	{
		NBTTagList enchantList = enchantScroll.getEnchantmentTagList();

		boolean isFit       = true;
		boolean isFortune   = false;
		boolean isSilkTouch = false;
		boolean isExclusive = false;

		for (int i = 0; i < enchantList.tagCount(); i++)
		{
			NBTTagCompound nbtTagCompound = (NBTTagCompound)enchantList.tagAt(i);
			isFit = isFit && EnchantmentRegistry.instance().isFit((int)nbtTagCompound.getShort("id"), objectiveTool);
		}

		if (objectiveTool.isItemEnchanted())
		{
			isExclusive = (this.hasFortune(enchantScroll) & this.hasSilkTouch(objectiveTool)) ^ (this.hasFortune(objectiveTool) & this.hasSilkTouch(enchantScroll));
		}

		isFit = isFit && !isExclusive;

		return isFit;
	}

	private boolean hasFortune(ItemStack objective)
	{
		NBTTagList enchantmentList = objective.getEnchantmentTagList();
		boolean hasFortune = false;

		if (enchantmentList != null)
		{
			for (int i = 0; i < enchantmentList.tagCount(); ++i)
			{
				NBTTagCompound nbtTagCompound = (NBTTagCompound)enchantmentList.tagAt(i);
				hasFortune = EnchantmentRegistry.instance().getEnchantmentIdFromName("fortune") == (int)nbtTagCompound.getShort("id") ? true : false;
			}
		}

		return hasFortune;
	}

	private boolean hasSilkTouch(ItemStack objective)
	{
		NBTTagList enchantmentList = objective.getEnchantmentTagList();
		boolean hasSilkTouch = false;

		if (enchantmentList != null)
		{
			for (int i = 0; i < enchantmentList.tagCount(); ++i)
			{
				NBTTagCompound nbtTagCompound = (NBTTagCompound)enchantmentList.tagAt(i);
				hasSilkTouch = EnchantmentRegistry.instance().getEnchantmentIdFromName("silk touch") == (int)nbtTagCompound.getShort("id") ? true : false;
			}
		}

		return hasSilkTouch;
	}



	private byte calcProbability()
	{
		byte result;

		Slot objectiveToolSlot = this.getSlot(0);
		Slot enchantScrollSlot = this.getSlot(1);
		Slot magicPowderSlot   = this.getSlot(2);

		if (objectiveToolSlot != null && objectiveToolSlot.getHasStack()
		 && enchantScrollSlot != null && enchantScrollSlot.getHasStack())
		{
			ItemStack objectiveTool = objectiveToolSlot.getStack();
			ItemStack enchantScroll = enchantScrollSlot.getStack();

			/*
			if (objectiveTool.isItemEnchanted())
			{
				return 0;
			}
			*/
			int boost = 0;
			if (magicPowderSlot != null && magicPowderSlot.getHasStack())
			{
				boost = 10;
			}

			int baseProbability = 100 - this.getSumEnchantmentWeight(enchantScroll);
			int enchantability = objectiveTool.getItem().getItemEnchantability() / 5;

			int prob = (int)((double)(baseProbability + enchantability) * Math.exp((double)this.getUsesRatio(objectiveTool) - 1.0D)) + boost;

			if (prob >= 90)
			{
				prob = 90;
			}
			else if (prob < 10)
			{
				prob = 10;
			}

			return (byte)prob;
		}
		else
		{
			return 0;
		}
	}

	private int getSumEnchantmentWeight(ItemStack itemstack)
	{
		NBTTagList enchantList = itemstack.getEnchantmentTagList();

		int id, lvl;
		int weight = 0;
		for (int i = 0; i < enchantList.tagCount(); ++i)
		{
			NBTTagCompound nbtTagCompound = (NBTTagCompound)enchantList.tagAt(i);
			id  = nbtTagCompound.getShort("id");
			lvl = nbtTagCompound.getShort("lvl");
			weight += EnchantmentRegistry.instance().getEnchantmentRarity(id, lvl);
		}

		return weight;
	}

	private float getUsesRatio(ItemStack itemstack)
	{
		int damage = itemstack.getItem().getMaxDamage() - itemstack.getItemDamage();
		return (float)damage / (float)itemstack.getItem().getMaxDamage();
	}

	// custom packet
	public void readPacketData(ByteArrayDataInput data)
	{
		try
		{
			this.buttonId    = data.readByte();
			this.probability = data.readByte();
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
			dos.writeByte(this.probability);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}