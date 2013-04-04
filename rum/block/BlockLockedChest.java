package rgn.mods.rum.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import rgn.mods.rum.RumCore;
import rgn.mods.rum.item.ItemKey;
import rgn.mods.rum.tileentity.TileEntityLockedChest;

public class BlockLockedChest extends BlockContainer
{
	public BlockLockedChest(int blockId)
	{
		super(blockId, Material.rock);
		this.setLightValue(1.0F);
		this.setHardness(10.0F);
		this.setResistance(2000.0F);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.blockIcon = Block.chest.getBlockTextureFromSide(0);
	}

	@Override
	public TileEntity createNewTileEntity(World world)
	{
		return new TileEntityLockedChest();
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public int getRenderType()
	{
		return RumBlock.LOCKED_CHEST_RENDER_TYPE;
	}


	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving entityliving, ItemStack itemstack)
	{
		int playerFacing = MathHelper.floor_double((double)((entityliving.rotationYaw * 4F) / 360F) + 0.5D) & 3;

		byte facing = 0;
		if (playerFacing == 0)
		{
			facing = 2;
		}
		if (playerFacing == 1)
		{
			facing = 5;
		}
		if (playerFacing == 2)
		{
			facing = 3;
		}
		if (playerFacing == 3)
		{
			facing = 4;
		}

		world.setBlockMetadataWithNotify(x, y, z, facing, 2);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int par6, float par7, float par8, float par9)
	{
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		if (tileEntity != null && tileEntity instanceof TileEntityLockedChest && !world.isRemote)
		{
			TileEntityLockedChest tileEntityLockedChest = (TileEntityLockedChest)tileEntity;

			if (!tileEntityLockedChest.isLocked())
			{
				entityPlayer.openGui(RumCore.instance, 0, world, x, y, z);
			}
			else
			{
				this.unlockingChest(world, entityPlayer, tileEntityLockedChest);
			}
		}
		return true;
	}

	private boolean isKey(ItemStack currentItemStack)
	{
		return currentItemStack != null && currentItemStack.getItem() instanceof ItemKey;
	}

	private void unlockingChest(World world, EntityPlayer entityPlayer, TileEntityLockedChest tileEntityLockedChest)
	{
		ItemStack key = entityPlayer.getCurrentEquippedItem();
		if (this.isKey(key))
		{
			this.decreaseKeyStackSize(key);
			tileEntityLockedChest.setUnlocked();
			//PacketDispatcher.sendPacketToAllPlayers(FMLPacketHandler.getLockedChestPacket(tileEntityLockedChest));
		}
		else
		{
			entityPlayer.addChatMessage("Locked...");
		}
	}

	private void decreaseKeyStackSize(ItemStack key)
	{
		if (key.getItemDamage() == 0)
		{
			--key.stackSize;
		}
	}

	@Override
	public int quantityDropped(Random par1Random)
	{
		return 0;
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, int par5, int par6)
	{
		/*
		TileEntityLockedChest tileEntity = (TileEntityLockedChest)world.getBlockTileEntity(x, y, z);
		if (tileEntity != null)
		{
			this.dropItem(tileEntity, world, tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord);
		}
		super.breakBlock(world, x, y, z, par5, par6);
		*/
	}

	private void dropItem(IInventory iinventory, World world, int xCoord, int yCoord, int zCoord)
	{
		for (int i = 0; i < iinventory.getSizeInventory(); i++)
		{
			ItemStack itemstack = iinventory.getStackInSlot(i);
			if (itemstack == null)
			{
				continue ;
			}

			float xf = world.rand.nextFloat() * 0.8F + 0.1F;
			float yf = world.rand.nextFloat() * 0.8F + 0.1F;
			float zf = world.rand.nextFloat() * 0.8F + 0.1F;

			while (itemstack.stackSize > 0)
			{
				int dropnum = world.rand.nextInt(21) + 10;
				if (dropnum > itemstack.stackSize)
				{
					dropnum = itemstack.stackSize;
				}
				itemstack.stackSize -= dropnum;

				EntityItem entityitem = new EntityItem(world, (float)xCoord + xf, (float)yCoord + yf, (float) zCoord + zf,
													   new ItemStack(itemstack.itemID, dropnum, itemstack.getItemDamage()));
				float bias = 0.05F;
				entityitem.motionX = (float)world.rand.nextGaussian() * bias;
				entityitem.motionY = (float)world.rand.nextGaussian() * bias + 0.2F;
				entityitem.motionZ = (float)world.rand.nextGaussian() * bias;

				if (itemstack.hasTagCompound())
				{
					entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
				}
				world.spawnEntityInWorld(entityitem);
			}
		}
	}
}
