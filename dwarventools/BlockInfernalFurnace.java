package rgn.mods.dwarventools;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.src.*;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

public class BlockInfernalFurnace extends BlockContainer
{
	private Random furnaceRand = new Random();
	private boolean isActive;
	private static boolean keepFurnaceInventory = false;
	
	public BlockInfernalFurnace(int blockId, int terrainId)
	{
		super(blockId, Material.rock);
		this.isActive            = false;
		this.blockIndexInTexture = terrainId;
		this.setHardness(3.5F);
		this.setStepSound(soundStoneFootstep);
		this.setRequiresSelfNotify();
		this.setCreativeTab(CreativeTabs.tabDeco);
	}
	
	@Override
	public String getTextureFile()
	{
		return "/rgn/sprites/dwarventools/blocks.png";
	}
	
	@Override
	public TileEntity createNewTileEntity(World world)
	{
		return new TileEntityInfernalFurnace();
	}
	
	@Override
	public void onBlockAdded(World world, int x, int y, int z)
	{
		super.onBlockAdded(world, x, y, z);
		this.setDefaultDirection(world, x, y, z);
	}
	
	private void setDefaultDirection(World world, int x, int y, int z)
	{
		if (!world.isRemote)
		{
			int northBlockId = world.getBlockId(x,     y, z - 1);
			int southBlockId = world.getBlockId(x,     y, z + 1);
			int  westBlockId = world.getBlockId(x - 1, y, z    );
			int  eastBlockId = world.getBlockId(x + 1, y, z    );
			
			byte dir = 3;
			
			if (Block.opaqueCubeLookup[northBlockId] && !Block.opaqueCubeLookup[southBlockId])
			{
				dir = 3;
			}
			
			if (Block.opaqueCubeLookup[southBlockId] && !Block.opaqueCubeLookup[northBlockId])
			{
				dir = 2;
			}
			
			if (Block.opaqueCubeLookup[westBlockId] && !Block.opaqueCubeLookup[eastBlockId])
			{
				dir = 5;
			}
			
			if (Block.opaqueCubeLookup[eastBlockId] && !Block.opaqueCubeLookup[westBlockId])
			{
				dir = 4;
			}
			
			world.setBlockMetadataWithNotify(x, y, z, dir);
		}
	}
	
	@Override
	public int getBlockTexture(IBlockAccess world, int x, int y, int z, int side)
	{
		if (side == 1)
		{
			return this.blockIndexInTexture - 2;
		}
		else if (side == 0)
		{
			return this.blockIndexInTexture - 2;
		}
		else
		{
			int metadata = world.getBlockMetadata(x, y, z);
			TileEntity te = world.getBlockTileEntity(x, y, z);
			TileEntityInfernalFurnace teif = (TileEntityInfernalFurnace)te;
			return side != metadata ? this.blockIndexInTexture : (teif.isBurning() ? this.blockIndexInTexture + 1 : this.blockIndexInTexture - 1);
		}
	}
	
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random random)
	{
		TileEntity te = world.getBlockTileEntity(x, y, z);
		TileEntityInfernalFurnace teif = (TileEntityInfernalFurnace)te;
		if (teif.isBurning())
		{
			int var6 = world.getBlockMetadata(x, y, z);
			float var7 = (float)x + 0.5F;
			float var8 = (float)y + 0.0F + random.nextFloat() * 6.0F / 16.0F;
			float var9 = (float)z + 0.5F;
			float var10 = 0.52F;
			float var11 = random.nextFloat() * 0.6F - 0.3F;
			
			if (var6 == 4)
			{
				world.spawnParticle("smoke", (double)(var7 - var10), (double)var8, (double)(var9 + var11), 0.0D, 0.0D, 0.0D);
				world.spawnParticle("flame", (double)(var7 - var10), (double)var8, (double)(var9 + var11), 0.0D, 0.0D, 0.0D);
			}
			else if (var6 == 5)
			{
				world.spawnParticle("smoke", (double)(var7 + var10), (double)var8, (double)(var9 + var11), 0.0D, 0.0D, 0.0D);
				world.spawnParticle("flame", (double)(var7 + var10), (double)var8, (double)(var9 + var11), 0.0D, 0.0D, 0.0D);
			}
			else if (var6 == 2)
			{
				world.spawnParticle("smoke", (double)(var7 + var11), (double)var8, (double)(var9 - var10), 0.0D, 0.0D, 0.0D);
				world.spawnParticle("flame", (double)(var7 + var11), (double)var8, (double)(var9 - var10), 0.0D, 0.0D, 0.0D);
			}
			else if (var6 == 3)
			{
				world.spawnParticle("smoke", (double)(var7 + var11), (double)var8, (double)(var9 + var10), 0.0D, 0.0D, 0.0D);
				world.spawnParticle("flame", (double)(var7 + var11), (double)var8, (double)(var9 + var10), 0.0D, 0.0D, 0.0D);
			}
		}
	}
	
	@Override
	public int getBlockTextureFromSide(int par1)
	{
		return par1 == 1 ? this.blockIndexInTexture - 2 : (par1 == 0 ? this.blockIndexInTexture - 2 : (par1 == 3 ? this.blockIndexInTexture - 1 : this.blockIndexInTexture));
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int par6, float par7, float par8, float par9)
	{
		if (world.isRemote)
		{
			return true;
		}
		else
		{
			TileEntityInfernalFurnace tileentity = (TileEntityInfernalFurnace)world.getBlockTileEntity(x, y, z);
			
			if (tileentity != null)
			{
				entityPlayer.openGui(DwarvenTools.instance, DwarvenTools.guiIdInfernalFurnace, world, x, y, z);
			}
			
			return true;
		}
	}
		
	@Override
	public void onBlockEventReceived(World world, int x, int y, int z, int eventId, int eventParam)
	{
		super.onBlockEventReceived(world, x, y, z, eventId, eventParam);
		world.markBlockNeedsUpdate(x, y, z);
	}
	
	public static void updateFurnaceBlockState(boolean par0, World world, int x, int y, int z)
	{
		int metadata          = world.getBlockMetadata(x, y, z);
		TileEntity tileentity = world.getBlockTileEntity(x, y, z);
		keepFurnaceInventory  = true;
		keepFurnaceInventory  = false;
		world.notifyBlockChange(x, y, z, DwarvenTools.blockInfernalFurnace.blockID);
		
		if (tileentity != null)
		{
			tileentity.validate();
			world.setBlockTileEntity(x, y, z, tileentity);
		}
	}
	

	
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving entityliving)
	{
		int playerdir = MathHelper.floor_double((double)(entityliving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		
		if (playerdir == 0)
		{
			world.setBlockMetadataWithNotify(x, y, z, 2);
		}
		
		if (playerdir == 1)
		{
			world.setBlockMetadataWithNotify(x, y, z, 5);
		}
		
		if (playerdir == 2)
		{
			world.setBlockMetadataWithNotify(x, y, z, 3);
		}
		
		if (playerdir == 3)
		{
			world.setBlockMetadataWithNotify(x, y, z, 4);
		}
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, int par5, int par6)
	{
		TileEntityInfernalFurnace tileEntity = (TileEntityInfernalFurnace)world.getBlockTileEntity(x, y, z);
		if (tileEntity != null)
		{
			this.dropItem(tileEntity, world, tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord);
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
			
			float xf = furnaceRand.nextFloat() * 0.8F + 0.1F;
			float yf = furnaceRand.nextFloat() * 0.8F + 0.1F;
			float zf = furnaceRand.nextFloat() * 0.8F + 0.1F;
			
			while (itemstack.stackSize > 0)
			{
				int dropnum = furnaceRand.nextInt(21) + 10;
				if (dropnum > itemstack.stackSize)
				{
					dropnum = itemstack.stackSize;
				}
				itemstack.stackSize -= dropnum;
				
				EntityItem entityitem = new EntityItem(world, (float)xCoord + xf, (float)yCoord + yf, (float) zCoord + zf, 
													   new ItemStack(itemstack.itemID, dropnum, itemstack.getItemDamage()));
				float bias = 0.05F;
				entityitem.motionX = (float)furnaceRand.nextGaussian() * bias;
				entityitem.motionY = (float)furnaceRand.nextGaussian() * bias + 0.2F;
				entityitem.motionZ = (float)furnaceRand.nextGaussian() * bias;
				
				if (itemstack.hasTagCompound())
				{
					entityitem.item.setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
				}
				world.spawnEntityInWorld(entityitem);
			}
		}
	}
	
}
