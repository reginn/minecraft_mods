package rgn.mods.toolrack;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import net.minecraftforge.common.ForgeDirection;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cpw.mods.fml.common.network.PacketDispatcher;

public class BlockToolrack extends BlockContainer
{
	public final static String types[] =
		{
			"oak", "spruce", "birch", "jungle", "black", "red", "white"
		};

	public final int[] textureIndex = new int[]
		{
			4, 198, 214, 199, 22, 22, 22
		};

	public BlockToolrack(int blockId)
	{
		super(blockId, 4, Material.wood);
		this.blockHardness = 0.3F;
		this.setCreativeTab(CreativeTabs.tabDecorations);
	}

	@Override
	public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List list)
	{
		for (int i = 0; i < types.length; i++)
		{
			list.add(new ItemStack(blockID, 1, i));
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getBlockTexture(IBlockAccess world, int x, int y, int z, int side)
	{
		return this.getBlockTextureFromSideAndMetadata(side, world.getBlockMetadata(x, y, z));
	}

	@Override
	public int getBlockTextureFromSideAndMetadata(int side, int meta)
	{
		this.blockIndexInTexture = this.textureIndex[meta];
		return this.blockIndexInTexture;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderColor(int metadata)
	{
		int texture = metadata;

		if (texture == 4)
		{
			return 0x222222;
		}
		else if (texture == 5)
		{
			return 0xEF3333;
		}
		else
		{
			return 0xFFFFFF;
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess world, int x, int y, int z)
	{
		int metadata = world.getBlockMetadata(x, y, z);

		int texture = metadata;

		if (texture == 4)
		{
			return 0x222222;
		}
		else if (texture == 5)
		{
			return 0xEF3333;
		}
		else
		{
			return 0xFFFFFF;
		}
	}
	
	@Override
	public int getRenderType()
	{
		return Toolrack.RENDER_TYPE_TOOLRACK;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		return null;
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
	public int damageDropped(int damage)
	{
		return damage;
	}
	
	@Override
	public void onBlockEventReceived(World world, int x, int y, int z, int eventId, int eventParam)
	{
		super.onBlockEventReceived(world, x, y, z, eventId, eventParam);
		world.markBlockForUpdate(x, y, z);
		TileEntityToolrack tileEntityToolrack = (TileEntityToolrack)world.getBlockTileEntity(x, y, z);
		
		if (tileEntityToolrack != null && !world.isRemote)
		{
			PacketDispatcher.sendPacketToAllPlayers(tileEntityToolrack.getDescriptionPacket());
		}
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z)
	{
		super.onBlockAdded(world, x, y, z);
		world.markBlockForUpdate(x, y, z);
	}

	@Override
	public TileEntity createNewTileEntity(World world)
	{
		return new TileEntityToolrack();
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving player)
	{
		int playerDir = MathHelper.floor_double((double)((player.rotationYaw * 4F) / 360F) + 0.5D) & 0x03;
		byte[] facing = new byte[] {2, 5, 3, 4};

		TileEntity tileentity = world.getBlockTileEntity(x, y, z);
		if (tileentity != null && tileentity instanceof TileEntityToolrack)
		{
			((TileEntityToolrack)tileentity).setFacing((facing[playerDir]));
			world.markBlockForUpdate(x, y, z);
		}
	}

	@Override
    public MovingObjectPosition collisionRayTrace(World world, int x, int y, int z, Vec3 par5Vec3, Vec3 par6Vec3)
    {
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);

		if (tileEntity != null && tileEntity instanceof TileEntityToolrack)
		{
			ForgeDirection dir = ForgeDirection.getOrientation(((TileEntityToolrack)tileEntity).getFacing());

			if (dir == ForgeDirection.EAST)  { this.setBlockBounds(       0.0F, 0.0F,        0.0F, 2.0F/16.0F, 1.0F,       1.0F); }
			if (dir == ForgeDirection.WEST)  { this.setBlockBounds(14.0F/16.0F, 0.0F,        0.0F,       1.0F, 1.0F,       1.0F); }
			if (dir == ForgeDirection.SOUTH) { this.setBlockBounds(       0.0F, 0.0F,        0.0F,       1.0F, 1.0F, 2.0F/16.0F); }
			if (dir == ForgeDirection.NORTH) { this.setBlockBounds(       0.0F, 0.0F, 14.0F/16.0F,       1.0F, 1.0F,       1.0F); }
		}

		return super.collisionRayTrace(world, x, y, z, par5Vec3, par6Vec3);
    }

	@Override
	public boolean canPlaceBlockOnSide(World world, int x, int y, int z, int side)
	{
		ForgeDirection dir = ForgeDirection.getOrientation(side);
		return (dir == ForgeDirection.NORTH && world.isBlockSolidOnSide(x    , y, z + 1, ForgeDirection.NORTH))
			|| (dir == ForgeDirection.SOUTH && world.isBlockSolidOnSide(x    , y, z - 1, ForgeDirection.SOUTH))
			|| (dir == ForgeDirection.WEST  && world.isBlockSolidOnSide(x + 1, y, z    , ForgeDirection.WEST ))
			|| (dir == ForgeDirection.EAST  && world.isBlockSolidOnSide(x - 1, y, z    , ForgeDirection.EAST ));
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z)
    {
		return world.isBlockSolidOnSide(x - 1, y, z    , ForgeDirection.EAST,  true)
			|| world.isBlockSolidOnSide(x + 1, y, z    , ForgeDirection.WEST,  true)
			|| world.isBlockSolidOnSide(x    , y, z - 1, ForgeDirection.SOUTH, true)
			|| world.isBlockSolidOnSide(x    , y, z + 1, ForgeDirection.NORTH, true);
    }
	
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int neighborBlockId)
	{
		TileEntityToolrack tileEntityToolrack = (TileEntityToolrack)world.getBlockTileEntity(x, y, z);
		ForgeDirection dir = ForgeDirection.getOrientation(tileEntityToolrack.getFacing());
		
		if (!this.canPlaceBlockAt(world, x, y, z))
		{
			boolean isCantStay = false;
			if (!world.isBlockSolidOnSide(x - 1, y, z, ForgeDirection.EAST, true) && dir == ForgeDirection.EAST)   { isCantStay = true; }
			if (!world.isBlockSolidOnSide(x + 1, y, z, ForgeDirection.WEST, true) && dir == ForgeDirection.WEST)   { isCantStay = true; }
			if (!world.isBlockSolidOnSide(x, y, z - 1, ForgeDirection.SOUTH, true) && dir == ForgeDirection.SOUTH) { isCantStay = true; }
			if (!world.isBlockSolidOnSide(x, y, z + 1, ForgeDirection.NORTH, true) && dir == ForgeDirection.NORTH) { isCantStay = true; }

			if (isCantStay)
			{
				this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
				this.dropItem(tileEntityToolrack, world, x, y, z);
				world.setBlockWithNotify(x, y, z, 0);
			}
		}
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int par6, float par7, float par8, float par9)
	{
		TileEntityToolrack tileEntityToolrack = (TileEntityToolrack)world.getBlockTileEntity(x, y, z);
		ItemStack currentItem = entityPlayer.getCurrentEquippedItem();

		if (tileEntityToolrack != null && !world.isRemote)
		{
			if (tileEntityToolrack.getStackInSlot(0) == null && currentItem != null && this.isItem(currentItem))
			{
				tileEntityToolrack.setInventorySlotContents(0, currentItem.copy());
				entityPlayer.inventory.setInventorySlotContents(entityPlayer.inventory.currentItem, null);
			}
			else if (tileEntityToolrack.getStackInSlot(0) != null)
			{
				ItemStack inventoryItem = tileEntityToolrack.getStackInSlot(0);
				if (entityPlayer.getCurrentEquippedItem() == null)
				{
					entityPlayer.inventory.setInventorySlotContents(entityPlayer.inventory.currentItem, inventoryItem.copy());
					tileEntityToolrack.setInventorySlotContents(0, null);
				}
			}
		}
		world.markBlockForUpdate(x, y, z);
		return true;
	}
	
	private boolean isItem(ItemStack currentItem)
	{
		return currentItem.itemID > 255
			&& Item.itemsList[currentItem.itemID] != null
			&& (Item.class).isAssignableFrom(currentItem.getItem().getClass())
			&& !this.isBlock(currentItem);
	}
	
	private boolean isBlock(ItemStack currentItem)
	{
		return currentItem.itemID < Block.blocksList.length
			&& Block.blocksList[currentItem.itemID] != null
			&& Block.blocksList[currentItem.itemID].blockMaterial != Material.air;
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, int par5, int par6)
	{
		TileEntityToolrack tileEntityToolrack = (TileEntityToolrack)world.getBlockTileEntity(x, y, z);
		if (tileEntityToolrack != null)
		{
			this.dropItem
			(
				tileEntityToolrack,
				world,
				tileEntityToolrack.xCoord,
				tileEntityToolrack.yCoord,
				tileEntityToolrack.zCoord
			);
		}
		super.breakBlock(world, x, y, z, par5, par6);
	}

	private void dropItem(IInventory iinventory, World world, int xCoord, int yCoord, int zCoord)
	{
		ItemStack itemstack = iinventory.getStackInSlot(0);
		if (itemstack == null)
		{
			return ;
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

			EntityItem entityitem =
				new EntityItem
				(
					world, (float)xCoord + xf, (float)yCoord + yf, (float) zCoord + zf,
					new ItemStack(itemstack.itemID, dropnum, itemstack.getItemDamage())
				);
			float bias = 0.05F;
			entityitem.motionX = (float)world.rand.nextGaussian() * bias;
			entityitem.motionY = (float)world.rand.nextGaussian() * bias + 0.2F;
			entityitem.motionZ = (float)world.rand.nextGaussian() * bias;

			if (itemstack.hasTagCompound())
			{
				entityitem.func_92014_d().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
			}
			world.spawnEntityInWorld(entityitem);
		}
	}
}
