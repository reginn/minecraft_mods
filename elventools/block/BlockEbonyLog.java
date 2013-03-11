package rgn.mods.elventools.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockEbonyLog extends Block
{
	@SideOnly(Side.CLIENT)
	protected Icon topIcon;
	@SideOnly(Side.CLIENT)
	protected Icon sideIcon;

	public BlockEbonyLog(int blockId)
	{
		super(blockId, Material.wood);
		this.setHardness(3.0F);
		this.setStepSound(soundWoodFootstep);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void func_94332_a(IconRegister par1IconRegister)
	{
		this.field_94336_cN = par1IconRegister.func_94245_a("rgn/elventools:ebonyWood_Side");
		this.topIcon  = par1IconRegister.func_94245_a("rgn/elventools:ebonyWood_top");
		this.sideIcon = par1IconRegister.func_94245_a("rgn/elventools:ebonyWood_side");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getBlockTextureFromSideAndMetadata(int side, int metadata)
	{
		if (side == 1 || side == 0)
		{
			return this.topIcon;
		}
		else
		{
			return this.sideIcon;
		}
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, int par5, int par6)
	{
		int sustainableRange = 4;
		int unsustainableRange = sustainableRange + 1;

		if (world.checkChunksExist( x - unsustainableRange, y - unsustainableRange, z - unsustainableRange,
									x + unsustainableRange, y + unsustainableRange, z + unsustainableRange))
		{
			for (int i = -sustainableRange; i <= sustainableRange; ++i)
			{
				for (int j = -sustainableRange; j <= sustainableRange; ++j)
				{
					for (int k = -sustainableRange; k <= sustainableRange; ++k)
					{
						int ebonyLeavesBlockId = world.getBlockId(x + i, y + j, z + k);

						if (Block.blocksList[ebonyLeavesBlockId] != null)
						{
							Block.blocksList[ebonyLeavesBlockId].beginLeavesDecay(world, x + i, y + j, z + k);
						}
					}
				}
			}
		}
	}

	@Override
	public boolean canSustainLeaves(World world, int x, int y, int z)
	{
		return true;
	}

	@Override
	public boolean isWood(World world, int x, int y, int z)
	{
		return true;
	}

}