package rgn.mods.elventools.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import rgn.mods.elventools.item.ElvenItem;

public class BlockRopeEstablisher extends Block
{
	public BlockRopeEstablisher(int blockId)
	{
		super(blockId, Material.vine);
		this.setBlockBounds(0.4F, 0.0F, 0.4F, 0.6F, 1.0F, 0.6F);
	}

	public int getMobilityFlag()
	{
		return 2;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.blockIcon = par1IconRegister.registerIcon("rgn/elventools:rope");
	}

	@Override
	public boolean isLadder(World world, int x, int y, int z)
	{
		return true;
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
		return 1;
	}

	@Override
	public int idDropped(int par1, Random par2Random, int par3)
	{
		return ElvenItem.itemRopeEstablisher.itemID;
	}

	@Override
	public void dropBlockAsItemWithChance(World world, int x, int y, int z, int par5, float par6, int par7)
	{
		world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(ElvenItem.itemRopeEstablisher, 1)));
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z)
	{
		if (world.isAirBlock(x, y - 1, z) && y - 1 >= 0)
		{
			for (; world.isAirBlock(x, y - 1, z) && y - 1 >= 0; y--)
			{
				world.setBlock(x, y - 1, z, ElvenBlock.blockRope.blockID);
				System.out.printf("g()=>%f, k()=>%d, e()=>%f, h()=>%f, a()=>%d, f()=>%f, j()=%d, b()=>%d\n",
						this.blockIcon.getMinV(),
						this.blockIcon.getSheetHeight(),
						this.blockIcon.getMinU(),
						this.blockIcon.getMaxV(),
						this.blockIcon.getOriginX(),
						this.blockIcon.getMaxU(),
						this.blockIcon.getSheetWidth(),
						this.blockIcon.getOriginY());

				System.out.println(this.blockIcon.getClass().getName());
			}
		}
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int neighborBlockId)
	{
		if (world.isAirBlock(x, y + 1, z))
		{
			world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(ElvenItem.itemRopeEstablisher, 1)));
			world.setBlockToAir(x, y, z);
		}
		else if (world.isAirBlock(x, y - 1, z) && y - 1 >= 0)
		{
			for (; world.isAirBlock(x, y - 1, z) && y - 1 >= 0; y--)
			{
				world.setBlock(x, y - 1, z, ElvenBlock.blockRope.blockID);
			}
		}
	}


}