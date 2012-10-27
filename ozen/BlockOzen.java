package rgn.mods.ozen;

import java.util.List;

import net.minecraft.src.*;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

public class BlockOzen extends BlockContainer
{
	public final String[] names = new String[]
		{
			"oak", "spruce", "birch", "black", "red", "iron", "gold"
		};
	
	public final String[] types = new String[]
		{
			"oshiki", "ozen",
		};
	
	public final int[] textureIndex = new int[]
		{
			4, 198, 214, 22, 22, 22, 23
		};
	
	public BlockOzen(int blockId)
	{
		super(blockId, 4, Material.wood);
		this.blockHardness = 0.3F;
		this.setCreativeTab(CreativeTabs.tabDecorations);
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		return null;
	}
	
	@Override
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
	public int getBlockTexture(IBlockAccess world, int x, int y, int z, int side)
	{
		return this.getBlockTextureFromSideAndMetadata(side, world.getBlockMetadata(x, y, z));
	}
	
	@Override
	public int getBlockTextureFromSideAndMetadata(int side, int meta)
	{
		this.blockIndexInTexture = this.textureIndex[meta & 7];
		return this.blockIndexInTexture;
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
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving player)
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
			world.markBlockNeedsUpdate(x, y, z);
		}
	}
	
	@Override
	public void onBlockEventReceived(World world, int x, int y, int z, int eventId, int eventParam)
	{
		super.onBlockEventReceived(world, x, y, z, eventId, eventParam);
		world.markBlockNeedsUpdate(x, y, z);
	}
	
	@Override
	public void onBlockAdded(World world, int x, int y, int z)
	{
		super.onBlockAdded(world, x, y, z);
		world.markBlockNeedsUpdate(x, y, z);
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
					entityitem.item.setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
				}
				world.spawnEntityInWorld(entityitem);
			}
		}
	}
}
