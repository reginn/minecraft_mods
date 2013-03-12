package rgn.mods.dwarventools.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import rgn.mods.dwarventools.DwarvenTools;
import rgn.mods.dwarventools.config.Config;
import rgn.mods.dwarventools.tileentity.TileEntityInfernalFurnace;

public class BlockInfernalFurnace extends BlockContainer
{
	private Random furnaceRand = new Random();
	private boolean isActive;
	private static boolean keepFurnaceInventory = false;

	@SideOnly(Side.CLIENT)
	private Icon topIcon;

	@SideOnly(Side.CLIENT)
	private Icon sideIcon;

	@SideOnly(Side.CLIENT)
	private Icon frontIcon;

	@SideOnly(Side.CLIENT)
	private Icon idleIcon;

	public BlockInfernalFurnace(int blockId)
	{
		super(blockId, Material.rock);
		this.setHardness(3.5F);
		this.setStepSound(soundStoneFootstep);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void func_94332_a(IconRegister par1IconRegister)
	{
		this.field_94336_cN = null;
		this.topIcon   = par1IconRegister.func_94245_a("rgn/dwarventools:blockInfernalFurnace_top");
		this.sideIcon  = par1IconRegister.func_94245_a("rgn/dwarventools:blockInfernalFurnace_side");
		this.frontIcon = par1IconRegister.func_94245_a("rgn/dwarventools:blockInfernalFurnace_front");
		this.idleIcon  = par1IconRegister.func_94245_a("rgn/dwarventools:blockInfernalFurnace_burning");
	}

	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z)
	{
		int msb = world.getBlockMetadata(x, y, z) >>> 3;

		if (msb == 1)
		{
			return 15;
		}
		return 0;
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

			world.setBlockMetadataWithNotify(x, y, z, dir & 7, 2);
		}
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving entityliving, ItemStack itemstack)
	{
		int playerdir = MathHelper.floor_double((double)(entityliving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

		if (playerdir == 0)
		{
			world.setBlockMetadataWithNotify(x, y, z, 2, 2);
		}

		if (playerdir == 1)
		{
			world.setBlockMetadataWithNotify(x, y, z, 5, 2);
		}

		if (playerdir == 2)
		{
			world.setBlockMetadataWithNotify(x, y, z, 3, 2);
		}

		if (playerdir == 3)
		{
			world.setBlockMetadataWithNotify(x, y, z, 4, 2);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getBlockTexture(IBlockAccess world, int x, int y, int z, int side)
	{
		if (side == 0 || side == 1)
		{
			return this.topIcon;
		}
		else
		{
			int metadata = world.getBlockMetadata(x, y, z) & 7;
			int msb = world.getBlockMetadata(x, y, z) >>> 3;
			return side != metadata ? this.sideIcon : (msb == 1 ? this.idleIcon : this.frontIcon);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getBlockTextureFromSideAndMetadata(int side, int meta)
	{
		return side == 0 ? this.topIcon : (side == 1 ? this.topIcon : ( side == 3 ? this.frontIcon : this.sideIcon));
	}

	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random random)
	{
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		TileEntityInfernalFurnace tileEntityInfernalFurnace = (TileEntityInfernalFurnace)tileEntity;

		int metadata = world.getBlockMetadata(x, y, z);
		int msb = metadata >>> 3;
		int dir = metadata & 7;

		if (tileEntityInfernalFurnace.isBurning() || msb == 1)
		{
			float var7 = (float)x + 0.5F;
			float var8 = (float)y + 0.0F + random.nextFloat() * 6.0F / 16.0F;
			float var9 = (float)z + 0.5F;
			float var10 = 0.52F;
			float var11 = random.nextFloat() * 0.6F - 0.3F;

			if (dir == 4)
			{
				world.spawnParticle("smoke", (double)(var7 - var10), (double)var8, (double)(var9 + var11), 0.0D, 0.0D, 0.0D);
				world.spawnParticle("flame", (double)(var7 - var10), (double)var8, (double)(var9 + var11), 0.0D, 0.0D, 0.0D);
			}
			else if (dir == 5)
			{
				world.spawnParticle("smoke", (double)(var7 + var10), (double)var8, (double)(var9 + var11), 0.0D, 0.0D, 0.0D);
				world.spawnParticle("flame", (double)(var7 + var10), (double)var8, (double)(var9 + var11), 0.0D, 0.0D, 0.0D);
			}
			else if (dir == 2)
			{
				world.spawnParticle("smoke", (double)(var7 + var11), (double)var8, (double)(var9 - var10), 0.0D, 0.0D, 0.0D);
				world.spawnParticle("flame", (double)(var7 + var11), (double)var8, (double)(var9 - var10), 0.0D, 0.0D, 0.0D);
			}
			else if (dir == 3)
			{
				world.spawnParticle("smoke", (double)(var7 + var11), (double)var8, (double)(var9 + var10), 0.0D, 0.0D, 0.0D);
				world.spawnParticle("flame", (double)(var7 + var11), (double)var8, (double)(var9 + var10), 0.0D, 0.0D, 0.0D);
			}
		}
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
				entityPlayer.openGui(DwarvenTools.instance, Config.guiIdInfernalFurnace, world, x, y, z);
			}

			return true;
		}
	}

	public static void updateFurnaceBlockState(boolean isBurning, World world, int x, int y, int z)
	{
		int metadata = world.getBlockMetadata(x, y, z);

		if (isBurning)
		{
			world.setBlockMetadataWithNotify(x, y, z, metadata + 8, 2);
		}
		else
		{
			world.setBlockMetadataWithNotify(x, y, z, metadata & 7, 2);
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
					entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
				}
				world.spawnEntityInWorld(entityitem);
			}
		}
	}

	@Override
	public boolean func_96468_q_()
	{
		return true;
	}

	@Override
	public int func_94328_b_(World par1World, int par2, int par3, int par4, int par5)
	{
		return Container.func_94526_b((IInventory)par1World.getBlockTileEntity(par2, par3, par4));
	}

}
