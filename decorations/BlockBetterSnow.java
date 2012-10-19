package rgn.mods.decorations;

import java.util.List;

import net.minecraft.src.*;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

public class BlockBetterSnow extends Block
{
	public BlockBetterSnow(int blockId, int terrainId)
	{
		super(blockId, terrainId, Material.snow);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
		this.setCreativeTab(CreativeTabs.tabDeco);
	}
	
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
	{
		for (int i = 0; i < 2; ++i)
		{
			par3List.add(new ItemStack(par1, 1, i));
		}
	}
	
	@Override
	protected int damageDropped(int i)
	{
		return i;
	}
	
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	public boolean renderAsNormalBlock()
	{
		return false;
	}
	
	public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
	{
		int var5 = par1World.getBlockId(par2, par3 - 1, par4);
		Block block = Block.blocksList[var5];
		return block != null && (block.isLeaves(par1World, par2, par3 - 1, par4) || Block.blocksList[var5].isOpaqueCube()) ? par1World.getBlockMaterial(par2, par3 - 1, par4).blocksMovement() : false;
	}
	
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
	{
		this.canSnowStay(par1World, par2, par3, par4);
	}
	
	private boolean canSnowStay(World par1World, int par2, int par3, int par4)
	{
		if (!this.canPlaceBlockAt(par1World, par2, par3, par4))
		{
			par1World.setBlockWithNotify(par2, par3, par4, 0);
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public int getLightValue(IBlockAccess world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);
		
		if (meta == 0)
		{
			return 0;
		}
		else
		{
			return 10;
		}
	}
}