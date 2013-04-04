package rgn.mods.elventools.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockEbonyLadder extends Block
{
	protected BlockEbonyLadder(int blockId)
	{
		super(blockId, Material.circuits);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.blockIcon = par1IconRegister.registerIcon("rgn/elventools:EbonyLadder");
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		this.setBlockBoundsBasedOnState(world, x, y, z);
		return super.getCollisionBoundingBoxFromPool(world, x, y, z);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z)
	{
		this.setBlockBoundsBasedOnState(world, x, y, z);
		return super.getSelectedBoundingBoxFromPool(world, x, y, z);
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
	{
		this.updateLadderBounds(world.getBlockMetadata(x, y, z));
	}

	private void updateLadderBounds(int metadata)
	{
		float f = 0.125F;

		if (metadata == 2)
		{
			this.setBlockBounds(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
		}

		if (metadata == 3)
		{
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
		}

		if (metadata == 4)
		{
			this.setBlockBounds(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		}

		if (metadata == 5)
		{
			this.setBlockBounds(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
		}
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
		return ElvenBlock.EBONY_LADDER_RENDER_TYPE;
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving entityLiving, ItemStack itemStack)
	{
		int playerDir = MathHelper.floor_double((double)((entityLiving.rotationYaw * 4F) / 360F) + 0.5D) & 0x03;
		byte[] facing = new byte[]{2, 5, 3, 4};
		world.setBlockMetadataWithNotify(x, y, z, facing[playerDir], 2);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
	{
		ItemStack currentItem = player.getCurrentEquippedItem();
		int meta = world.getBlockMetadata(x, y, z);

		if (currentItem.itemID == ElvenBlock.blockEbonyLadder.blockID)
		{
			if (world.isAirBlock(x, y + 1, z))
			{
				if(world.setBlock(x, y + 1, z, ElvenBlock.blockEbonyLadder.blockID, meta, 2))
				{
					--currentItem.stackSize;
				}
			}
			return true;
		}

		return false;
	}

	@Override
	public int quantityDropped(Random par1Random)
	{
		return 1;
	}

	@Override
	public boolean isLadder(World world, int x, int y, int z)
	{
		return true;
	}
}
