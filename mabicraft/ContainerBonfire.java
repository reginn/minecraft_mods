package rgn.mods.mabicraft;

import java.io.*;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

import java.util.Random;

import java.lang.Math;

import java.io.DataOutputStream;
import java.io.IOException;

import com.google.common.io.ByteArrayDataInput;

import cpw.mods.fml.common.network.PacketDispatcher;

import net.minecraft.src.*;
import net.minecraftforge.common.*;

public class ContainerBonfire extends Container
{
	public IInventory inventoryBonfire = new InventoryBonfire();
	private World world;
	private int xCoord;
	private int yCoord;
	private int zCoord;
	private byte buttonId = -1;
	private byte probability;
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
		
		addSlotToContainer(new           Slot(inventoryBonfire, 0, 26, 17));
		addSlotToContainer(new SlotRestricted(inventoryBonfire, 1, 26, 17 + 18, MabiCraft.itemManaHerb.shiftedIndex));
		addSlotToContainer(new SlotRestricted(inventoryBonfire, 2, 26, 17 + 36, MabiCraft.itemBlessedPotion.shiftedIndex));
		
		addSlotToContainer(new SlotResult(inventoryBonfire, 3, 134, 17));
		addSlotToContainer(new SlotResult(inventoryBonfire, 4, 134, 17 + 36));
		
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
		return this.world.getBlockId(this.xCoord, this.yCoord, this.zCoord) != MabiCraft.blockBonfire.blockID ? false 
			: entityPlayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
	}
	
	@Override
	public void onCraftGuiClosed(EntityPlayer entityPlayer)
	{
		super.onCraftGuiClosed(entityPlayer);
		
		if (!this.world.isRemote)
		{
			List<ItemStack> dropItemList = new ArrayList<ItemStack>();
			
			for (int i = 0; i < this.inventoryBonfire.getSizeInventory(); ++i)
			{
				dropItemList.add(this.inventoryBonfire.getStackInSlotOnClosing(i));
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
	public ItemStack transferStackInSlot(int slotIndex)
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
				else if (temp.itemID == MabiCraft.itemManaHerb.shiftedIndex)
				{
					if (!this.mergeItemStack(temp, 1, 2, false))
					{
						return null;
					}
				}
				else if (temp.itemID == MabiCraft.itemBlessedPotion.shiftedIndex)
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
			
			slot.onPickupFromSlot(temp);
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
			for (int i = 0; i < this.inventoryBonfire.getSizeInventory(); ++i)
			{
				var2.updateCraftingInventorySlot(this, i, this.getSlot(i).getStack());
			}
		}
	}
	
	@Override
	public void onCraftMatrixChanged(IInventory par1IInventory)
	{
		if (buttonId == 0)
		{
			this.enchantExtraction();
			this.buttonId = -1;
			return ;
		}
		
		if (buttonId == 1)
		{
			this.toolBurning();
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
	
	private void enchantExtraction()
	{
		Slot enchantedToolSlot = this.getSlot(0);
		Slot manaHerbSlot      = this.getSlot(1);
		Slot blessedPotionSlot = this.getSlot(2);
		
		if (enchantedToolSlot != null && manaHerbSlot != null && blessedPotionSlot != null
		 && enchantedToolSlot.getHasStack() && manaHerbSlot.getHasStack() && blessedPotionSlot.getHasStack())
		{
			ItemStack enchantedTool = enchantedToolSlot.getStack();
			ItemStack manaHerb      = manaHerbSlot.getStack();
			ItemStack blessedPotion = blessedPotionSlot.getStack();
			
			if (enchantedTool.isItemEnchanted() && enchantedTool.getItem().getItemEnchantability() > 0)
			{
				if (!world.isRemote)
				{
					if (random.nextInt(100) > this.calcProbability())
					{
						player.addChatMessage("Extraction failed");
						this.toolBurning();
						return ;
					}
					
					int color = random.nextInt(32767);
					ItemStack enchantScroll = new ItemStack(MabiCraft.itemEnchantScroll, 1, (int)color);
					
					NBTTagList enchantList = enchantedTool.getEnchantmentTagList();
					
					byte[] ids  = new byte[enchantList.tagCount()];
					byte[] lvls = new byte[enchantList.tagCount()];
					
					for (int i = 0; i < enchantList.tagCount(); i++)
					{
						NBTTagCompound nbtTagCompound = (NBTTagCompound)enchantList.tagAt(i);
						this.setEnchantment(enchantScroll, nbtTagCompound.getShort("id"), nbtTagCompound.getShort("lvl"));
					}
					
					this.putStackInSlot(0, null);
					
					if (--manaHerb.stackSize == 0)
					{
						this.putStackInSlot(1, null);
					}
					else
					{
						this.putStackInSlot(1, manaHerb);
					}
					
					if (--blessedPotion.stackSize == 0)
					{
						this.putStackInSlot(2, null);
					}
					else
					{
						this.putStackInSlot(2, blessedPotion);
					}
					
					this.putStackInSlot(3, enchantScroll);
					
					if (this.getSlot(4) == null)
					{
						this.putStackInSlot(4, new ItemStack(MabiCraft.itemMagicPowder));
					}
					else
					{
						this.mergeItemStack(new ItemStack(MabiCraft.itemMagicPowder), 4, 5, false);
					}
					
					this.updateCraftingResults();
					player.addChatMessage("Extraction succeed");
				}
			}
		}
		
	}
	
	private void setEnchantment(ItemStack enchantScroll, short id, short lvl)
	{
		if (enchantScroll.stackTagCompound == null)
		{
			enchantScroll.setTagCompound(new NBTTagCompound());
		}
		
		if (!enchantScroll.stackTagCompound.hasKey("ench"))
		{
			enchantScroll.stackTagCompound.setTag("ench", new NBTTagList("ench"));
		}
		
		NBTTagList nbtTagList = (NBTTagList)enchantScroll.stackTagCompound.getTag("ench");
		NBTTagCompound nbtTagCompound = new NBTTagCompound();
		nbtTagCompound.setShort("id", id);
		nbtTagCompound.setShort("lvl", lvl);
		nbtTagList.appendTag(nbtTagCompound);
	}
	
	private void toolBurning()
	{
		Slot slot = this.getSlot(0);
		
		if (slot != null && slot.getHasStack())
		{
			if (!world.isRemote)
			{
				ItemStack enchantedTool = slot.getStack();
				
				int exp;
				if (enchantedTool.isItemEnchanted())
				{
					int enchantmentWeight = this.getSumEnchantmentWeight(enchantedTool);
					int enchantability = enchantedTool.getItem().getItemEnchantability();
					exp = (int)((double)(enchantmentWeight * enchantability) * Math.exp((double)this.getUsesRatio(enchantedTool) - 1.0D));
					
					int spawnExp = exp;
					
					while (spawnExp > 0)
					{
						int splitExp = EntityXPOrb.getXPSplit(spawnExp);
						spawnExp -= splitExp;
						world.spawnEntityInWorld(new EntityXPOrb(world, this.xCoord, this.yCoord, this.zCoord, splitExp));
					}
					slot.putStack(null);
					this.updateCraftingResults();
					player.addChatMessage("Buring finished");
				}
			}
		}
	}
	
	
	private byte calcProbability()
	{
		byte result;
		
		Slot slot = this.getSlot(0);
		
		if (slot != null && slot.getHasStack())
		{
			ItemStack enchantedTool = slot.getStack();
			if (!enchantedTool.isItemEnchanted())
			{
				return 0;
			}
			
			int baseProbability = 100 - this.getSumEnchantmentWeight(enchantedTool);
			int enchantability = enchantedTool.getItem().getItemEnchantability() / 2;
			
			int prob = (int)((double)(baseProbability + enchantability) * Math.exp((double)this.getUsesRatio(enchantedTool) - 1.0D));
			
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
	
	private int getSumEnchantmentWeight(ItemStack enchantedTool)
	{
		NBTTagList enchantList = enchantedTool.getEnchantmentTagList();
		
		int id, lvl;
		int weight = 0;
		for (int i = 0; i < enchantList.tagCount(); ++i)
		{
			NBTTagCompound nbtTagCompound = (NBTTagCompound)enchantList.tagAt(i);
			id  = nbtTagCompound.getShort("id");
			lvl = nbtTagCompound.getShort("lvl");
			weight += EnchantmentMap.getEnchantmentRarity(id, lvl);
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