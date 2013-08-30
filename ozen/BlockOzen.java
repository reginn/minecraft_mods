package rgn.mods.ozen;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockOzen extends BlockContainer
{
	public final String[] names = new String[]
		{
			"wood", "spruce", "birch", "black", "red", "iron", "gold"
		};

	public final String[] types = new String[]
		{
			"oshiki", "ozen",
		};

	@SideOnly(Side.CLIENT)
	private Icon[] icons;

	public BlockOzen(int blockId)
	{
		super(blockId, Material.wood);
		this.blockHardness = 0.3F;
		this.setCreativeTab(Ozen.tabOzen);
		this.icons = new Icon[7];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.icons[0] = Block.planks.getIcon(0, 0);
		this.icons[1] = Block.planks.getIcon(0, 1);
		this.icons[2] = Block.planks.getIcon(0, 2);
		this.icons[3] = Block.blockIron.getBlockTextureFromSide(0);
		this.icons[4] = Block.blockIron.getBlockTextureFromSide(0);
		this.icons[5] = Block.blockIron.getBlockTextureFromSide(0);
		this.icons[6] = Block.blockGold.getBlockTextureFromSide(0);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List list)
	{
		for (int i = 0; i < 7; i++)
		{
			list.add(new ItemStack(blockID, 1, i));
		}

		for (int i = 0; i < 7; i++)
		{
			list.add(new ItemStack(blockID, 1, 8 + i));
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getBlockTexture(IBlockAccess world, int x, int y, int z, int side)
	{
		return this.getIcon(side, world.getBlockMetadata(x, y, z));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta)
	{
		this.blockIcon = this.icons[meta & 7];
		return this.blockIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderColor(int metadata)
	{
		int texture = metadata & 7;

		if (texture == 3)
		{
			return 0x222222;
		}
		else if (texture == 4)
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

		int texture = metadata & 7;

		if (texture == 3)
		{
			return 0x222222;
		}
		else if (texture == 4)
		{
			return 0xEF3333;
		}
		else
		{
			return 0xFFFFFF;
		}
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		return null;
	}

	@Override
	public int getRenderType()
	{
		return Ozen.ozenRenderID;
	}

	@Override
	public TileEntity createNewTileEntity(World var1)
	{
		return new TileEntityOzen();
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
	public int getMobilityFlag()
	{
		return 2;
	}

	@Override
	public int damageDropped(int damage)
	{
		return damage;
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack itemstack)
	{
		int playerdir = MathHelper.floor_double((double)((player.rotationYaw * 4F) / 360F) + 0.5D) & 0x03;
		byte facing = 0;
		if (playerdir == 0)
		{
			facing = 2;
		}
		if (playerdir == 1)
		{
			facing = 5;
		}
		if (playerdir == 2)
		{
			facing = 3;
		}
		if (playerdir == 3)
		{
			facing = 4;
		}

		TileEntity tileentity = world.getBlockTileEntity(x, y, z);
		if (tileentity != null && tileentity instanceof TileEntityOzen)
		{
			((TileEntityOzen)tileentity).setFacing(facing);
			world.markBlockForUpdate(x, y, z);
		}
	}

	@Override
	public boolean onBlockEventReceived(World world, int x, int y, int z, int eventId, int eventParam)
	{
		super.onBlockEventReceived(world, x, y, z, eventId, eventParam);
		world.markBlockForUpdate(x, y, z);
		return true;
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z)
	{
		super.onBlockAdded(world, x, y, z);
		world.markBlockForUpdate(x, y, z);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int par6, float par7, float par8, float par9)
	{
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		if (tileEntity == null || !(tileEntity instanceof TileEntityOzen))
		{
			return true;
		}

		if (world.isRemote)
		{
			return true;
		}

		entityPlayer.openGui(Ozen.instance, Ozen.guiIdOzen, world, x, y, z);

		return true;
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, int par5, int par6)
	{
		TileEntityOzen tileEntityOzen = (TileEntityOzen)world.getBlockTileEntity(x, y, z);
		if (tileEntityOzen != null)
		{
			this.dropItem(tileEntityOzen, world, tileEntityOzen.xCoord, tileEntityOzen.yCoord, tileEntityOzen.zCoord);
		}
		super.breakBlock(world, x, y, z, par5, par6);
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
