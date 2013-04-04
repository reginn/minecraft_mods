package rgn.mods.mabicraft.item.enchanting;

import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import rgn.mods.mabicraft.inventory.ContainerBonfire;
import rgn.mods.mabicraft.inventory.InventoryEnchanting;
import rgn.mods.mabicraft.item.ItemEnchantScroll;
import rgn.mods.mabicraft.item.MabiCraftItem;

public class EnchantmentManager
{
	Random random = new Random();

	private static EnchantmentManager INSTANCE = new EnchantmentManager();

	private EnchantmentManager()
	{
		EnchantmentDictionary.instance().registerEnchantmentFromEnchantmentList();
	}

	public static EnchantmentManager instance()
	{
		return INSTANCE;
	}

	//-------------- for bonfire interface
	public ItemStack tryExtraction(EntityPlayer player, InventoryEnchanting inventory)
	{
		ItemStack enchantedItem = inventory.getStackInSlot(0);
		ItemStack manaHerb      = inventory.getStackInSlot(1);
		ItemStack blessedPotion = inventory.getStackInSlot(2);

		if (enchantedItem != null && manaHerb != null && blessedPotion != null)
		{
			if (enchantedItem.isItemEnchanted() && enchantedItem.getItem().getItemEnchantability() > 0)
			{
				if (!player.worldObj.isRemote)
				{
					if (random.nextInt(100) > this.calcProbability(inventory))
					{
						player.addChatMessage("Extraction failed");
						((ContainerBonfire)inventory.getEventHandler()).toolBurning();
						return null;
					}

					ItemStack enchantScroll = this.getEnchantScrollFromEnchantedItem(enchantedItem);
					((ItemEnchantScroll)enchantScroll.getItem()).setColorInfoToNBT(enchantScroll);

					inventory.setInventorySlotContents(0, null);
					inventory.setInventorySlotContents(1, --manaHerb.stackSize == 0 ? null : manaHerb);
					inventory.setInventorySlotContents(2, --blessedPotion.stackSize == 0 ? null : blessedPotion);

					player.addChatMessage("Extraction succeed");

					return enchantScroll;
				}
			}
		}
		return null;
	}

	public ItemStack getMagicPowder(InventoryEnchanting inventory)
	{
		ItemStack magicPowder = inventory.getStackInSlot(0);

		if (magicPowder == null)
		{
			return new ItemStack(MabiCraftItem.itemMagicPowder, 1);
		}
		else
		{
			++magicPowder.stackSize;
			return magicPowder;
		}
	}

	protected ItemStack getEnchantScrollFromEnchantedItem(ItemStack enchantedItem)
	{
		Map enchantments = EnchantmentHelper.getEnchantments(enchantedItem);
		ItemStack scroll = new ItemStack(MabiCraftItem.itemEnchantScroll, 1);

		this.setEnchantmentsToScroll(scroll, enchantments);

		return scroll;
	}

	protected void setEnchantmentsToScroll(ItemStack itemStack, Map<Integer, Integer> enchantments)
	{
		this.writeEnchantmentsToNBT(itemStack, enchantments, "scroll");
	}

	//-------------- for enchanter interface
	public ItemStack tryEnchanting(EntityPlayer player, InventoryEnchanting inputInventory)
	{
		ItemStack objective     = inputInventory.getStackInSlot(0);
		ItemStack enchantScroll = inputInventory.getStackInSlot(1);
		ItemStack magicPowder   = inputInventory.getStackInSlot(2);

		int boost = 0;
		int prob  = 0;

		if (objective != null && enchantScroll != null)
		{
			if (magicPowder != null)
			{
				boost = 10;
				inputInventory.setInventorySlotContents(2, --magicPowder.stackSize == 0 ? null : magicPowder);
			}

			prob = this.calcProbability(inputInventory) + boost;
			prob = prob > 90 ? 90 : (prob < 10 ? 10 : prob);

			if (random.nextInt(100) > prob)
			{
				int toolDamage   = objective.getMaxDamage() / 10;
				int scrollDamage = enchantScroll.getMaxDamage() / 10;

				objective.setItemDamage(objective.getItemDamage() + toolDamage);
				enchantScroll.setItemDamage(enchantScroll.getItemDamage() + scrollDamage);

				inputInventory.setInventorySlotContents(0, objective.getItemDamage() >= objective.getMaxDamage() ? null : objective);
				inputInventory.setInventorySlotContents(1, enchantScroll.getItemDamage() >= enchantScroll.getMaxDamage() ? null : enchantScroll);

				player.addChatMessage("Enchantment failed...");
				return null;
			}

			this.setEnchantmentsToItemStack(objective, this.getEnchantmentsFromScroll(enchantScroll));
			inputInventory.setInventorySlotContents(0, null);
			inputInventory.setInventorySlotContents(1, null);
			player.addChatMessage("Enchantment succeed");
			return objective;
		}

		player.addChatMessage("nop");
		return null;
	}

	public Map<Integer, Integer> getEnchantmentsFromScroll(ItemStack scroll)
	{
		Map<Integer, Integer> enchantments = Maps.newHashMap();
		NBTTagList nbtTagList = scroll.getTagCompound().getTagList("scroll");

		for (int i = 0; i < nbtTagList.tagCount(); ++i)
		{
			short id  = ((NBTTagCompound)nbtTagList.tagAt(i)).getShort("id");
			short lvl = ((NBTTagCompound)nbtTagList.tagAt(i)).getShort("lvl");
			enchantments.put((int)id, (int)lvl);
		}

		return enchantments;
	}

	public void setEnchantmentsToItemStack(ItemStack tool, Map<Integer, Integer> enchantments)
	{
		if (tool.isItemEnchanted())
		{
			this.mergeEnchantments(tool, enchantments);
		}
		else
		{
			this.setNewEnchantments(tool, enchantments);
		}

	}

	protected void mergeEnchantments(ItemStack tool, Map<Integer, Integer> enchantments)
	{
		Map<Integer, Integer> oldEnchantments = EnchantmentHelper.getEnchantments(tool);
		Map<Integer, Integer> mergeEnchantments = Maps.newLinkedHashMap(oldEnchantments);

		Set<Integer> mergeEnchantmentIdSet = Sets.newHashSet(oldEnchantments.keySet());
		mergeEnchantmentIdSet.addAll(enchantments.keySet());

		for (Integer id : mergeEnchantmentIdSet)
		{
			if (!mergeEnchantments.containsKey(id))
			{
				mergeEnchantments.put(id, enchantments.get(id));
			}
			else if (mergeEnchantments.containsKey(id) && enchantments.containsKey(id))
			{
				Integer lvl = mergeEnchantments.get(id).compareTo(enchantments.get(id)) > 0 ?
							  mergeEnchantments.get(id) : enchantments.get(id);
				mergeEnchantments.put(id, lvl);
			}
		}

		tool.setTagCompound(null);
		this.writeEnchantmentsToNBT(tool, mergeEnchantments, "ench");
	}

	protected void setNewEnchantments(ItemStack tool, Map<Integer, Integer> enchantments)
	{
		this.writeEnchantmentsToNBT(tool, enchantments, "ench");
	}

	protected void writeEnchantmentsToNBT(ItemStack itemStack, Map<Integer, Integer> enchantments, String tag)
	{
		NBTTagList nbtTagList = new NBTTagList();

		for (Map.Entry<Integer, Integer> entry : enchantments.entrySet())
		{
			NBTTagCompound nbtTagCompound = new NBTTagCompound();
			nbtTagCompound.setShort("id", (short)entry.getKey().intValue());
			nbtTagCompound.setShort("lvl", (short)entry.getValue().intValue());
			nbtTagList.appendTag(nbtTagCompound);
		}

		itemStack.setTagInfo(tag, nbtTagList);
	}

	public boolean canEnchantItem(InventoryEnchanting inventory)
	{
		ItemStack objective = inventory.getStackInSlot(0);
		ItemStack    scroll = inventory.getStackInSlot(1);

		Map<Integer, Integer> enchantmens = this.getEnchantmentsFromScroll(scroll);

		boolean canEnchant = true;
		for (Map.Entry<Integer, Integer> entry : enchantmens.entrySet())
		{
			Enchantment ench = Enchantment.enchantmentsList[entry.getKey().intValue()];
			if (ench != null)
			{
				canEnchant &= ench.func_92089_a(objective);
			}
		}
		return canEnchant;
	}

	public boolean canApplyTogether(InventoryEnchanting inventory)
	{
		ItemStack objective = inventory.getStackInSlot(0);
		ItemStack    scroll = inventory.getStackInSlot(1);

		if (!objective.isItemEnchanted())
		{
			return true;
		}

		Map<Integer, Integer> toolEnchantments   = EnchantmentHelper.getEnchantments(objective);
		Map<Integer, Integer> scrollEnchantments = this.getEnchantmentsFromScroll(scroll);


		return !((toolEnchantments.containsKey(Enchantment.fortune.effectId) & scrollEnchantments.containsKey(Enchantment.silkTouch.effectId))
			^ (toolEnchantments.containsKey(Enchantment.silkTouch.effectId) & scrollEnchantments.containsKey(Enchantment.fortune.effectId)));
	}

	//-------- common
	public int calcProbability(IInventory inventory)
	{
		int slotNum = inventory.getInvName().equals("bonfire") ? 0 : 1;
		ItemStack enchantedItem = inventory.getStackInSlot(slotNum);

		if (enchantedItem != null)
		{
			int baseProbability = 100 - this.getSumEnchantmentWeight(enchantedItem);
			int enchantability = enchantedItem.getItem().getItemEnchantability() / 2;
			int prob = baseProbability + enchantability;
			return prob >= 90 ? 90 : (prob < 10 ? 10 : prob);
		}
		return 0;
	}

	public int getSumEnchantmentWeight(ItemStack enchantedItem)
	{
		if (enchantedItem == null || !enchantedItem.hasTagCompound())
		{
			return 0;
		}

		Map<Integer, Integer> enchantments
			= enchantedItem.getTagCompound().hasKey("scroll") ?
				this.getEnchantmentsFromScroll(enchantedItem) :
				EnchantmentHelper.getEnchantments(enchantedItem);

		int weight = 0;

		for (Map.Entry<Integer, Integer> entry : enchantments.entrySet())
		{
			weight += EnchantmentDictionary.instance().getEnchantmentRarity(
					entry.getKey().intValue(),
					entry.getValue().intValue()
					);
		}

		return weight;
	}
}