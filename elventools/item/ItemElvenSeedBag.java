package rgn.mods.elventools.item;

import java.util.List;
import java.util.Set;

import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import net.minecraftforge.common.ForgeDirection;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import rgn.mods.elventools.ElvenTools;
import rgn.mods.elventools.config.Config;
import rgn.mods.elventools.inventory.InventorySeedBag;

public class ItemElvenSeedBag extends Item
{
	private class Coord
	{
		int x;
		int y;
		int z;

		public Coord(int i, int j, int k)
		{
			x = i;
			y = j;
			z = k;
		}
	}

	public ItemElvenSeedBag(int itemId)
	{
		super(itemId);
		this.setMaxStackSize(1);
	}

	@Override
	public boolean onItemUse(ItemStack seedBag, EntityPlayer player, World world, int x, int y, int z, int meta, float par8, float par9, float par10)
    {
		if (player.isSneaking())
		{
			return false;
		}
		if (world.getBlockId(x, y, z) == Block.tilledField.blockID || world.getBlockId(x, y, z) == Block.slowSand.blockID)
		{
			if (seedBag.hasTagCompound())
			{
				IInventory inventorySeedBag = new InventorySeedBag(seedBag);
				for (int i = 0; i < inventorySeedBag.getSizeInventory(); ++i)
				{
					ItemStack seed = inventorySeedBag.getStackInSlot(i);
					if (seed != null)
					{
						this.tryPlaceSeedOnAroundBlock(seed, player, world, x, y, z, meta, par8, par9, par10);
						inventorySeedBag.setInventorySlotContents(i, seed.stackSize != 0 ? inventorySeedBag.decrStackSize(i, seed.stackSize) : null);
						inventorySeedBag.closeChest();
						return true;
					}
				}
			}
		}
		return false;
	}

	private void tryPlaceSeedOnAroundBlock(ItemStack seed, EntityPlayer player, World world, int x, int y, int z, int meta, float par7, float par8, float par9)
	{
		int playerDir = MathHelper.floor_double((double)((player.rotationYaw * 4F) / 360F) + 0.5D) & 0x03;
		int[] facing  = new int[] {2, 5, 3, 4};

		Set<Coord> targetsSet = this.getPositions(facing[playerDir], x, y, z);

		for (Coord target : targetsSet)
		{
			if (seed.stackSize > 0)
			{
				seed.tryPlaceItemIntoWorld(player, world, target.x, target.y, target.z, meta, par7, par8, par9);
			}
		}
	}

	private Set<Coord> getPositions(int facing, int x, int y, int z)
	{
		Set<Coord> targets = Sets.newLinkedHashSet();

		int[] upper = new int[]{-2, -1, 0,  1,  2};
		int[] lower = new int[]{ 2,  1, 0, -1, -2};

		int[] dx = upper;
		int[] dz = upper;

		ForgeDirection playerDir = ForgeDirection.getOrientation(facing);

		if (playerDir == ForgeDirection.EAST)  { dx = lower; dz = upper; }
		if (playerDir == ForgeDirection.WEST)  { dx = upper; dz = lower; }
		if (playerDir == ForgeDirection.SOUTH) { dx = upper; dz = upper; }
		if (playerDir == ForgeDirection.NORTH) { dx = lower; dz = lower; }

		for (int i : dx)
		{
			for (int j : dz)
			{
				int _x = x + i;
				int _z = z + j;
				if (playerDir == ForgeDirection.EAST || playerDir == ForgeDirection.WEST)
				{
					_x = x + j;
					_z = z + i;
				}
				targets.add((new Coord(_x, y, _z)));
			}
		}


		return targets;
	}

	private int getSeedStacks(ItemStack seedBag)
	{
		IInventory inventorySeedBag = new InventorySeedBag(seedBag);
		int stacks = 0;
		for (int i = 0; i < inventorySeedBag.getSizeInventory(); ++i)
		{
			ItemStack seed = inventorySeedBag.getStackInSlot(i);
			if (seed != null)
			{
				stacks += seed.stackSize;
			}
		}
		return stacks;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer)
	{
		if (entityPlayer.isSneaking())
		{
			entityPlayer.openGui(ElvenTools.instance, Config.guiIdSeedBag, world, 0, 0, 0);
		}
		return itemStack;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack seedBag, EntityPlayer player, List infomationList, boolean par4)
	{
		if (seedBag.hasTagCompound())
		{
			NBTTagCompound nbtTagCompound = new NBTTagCompound();
			ItemStack seed = null;
			nbtTagCompound = seedBag.getTagCompound();
			NBTTagList nbtTagList = nbtTagCompound.getTagList("Items");
			for (int i = 0; i < nbtTagList.tagCount(); ++i)
			{
				NBTTagCompound slotNbtTagCompound = (NBTTagCompound)nbtTagList.tagAt(i);
				int j = slotNbtTagCompound.getByte("Slot") & 0xff;

				seed = ItemStack.loadItemStackFromNBT(slotNbtTagCompound);
				if (seed != null)
				{
					break;
				}
			}

			infomationList.add("Type : " + (seed != null ? seed.getDisplayName() : "Empty"));
			infomationList.add("StackSize : " + (seed != null ? this.getSeedStacks(seedBag) : "0"));
		}
	}

	@Override
	public boolean getShareTag()
    {
        return true;
    }

}