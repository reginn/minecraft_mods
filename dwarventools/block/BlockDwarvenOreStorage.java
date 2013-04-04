package rgn.mods.dwarventools.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockDwarvenOreStorage extends Block
{
	@SideOnly(Side.CLIENT)
	private Icon redstoneIcon;

	@SideOnly(Side.CLIENT)
	private Icon mithrilIcon;

	@SideOnly(Side.CLIENT)
	private Icon ebonyIcon;

	public BlockDwarvenOreStorage(int blockId)
	{
		super(blockId, Material.iron);
		this.setHardness(2.0F);
		this.setResistance(10.0F);
		this.setStepSound(soundMetalFootstep);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.redstoneIcon = par1IconRegister.registerIcon("rgn/dwarventools:blockRedstone");
		this.mithrilIcon  = par1IconRegister.registerIcon("rgn/dwarventools:blockMithril");
		this.ebonyIcon    = par1IconRegister.registerIcon("rgn/dwarventools:blockEbony");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List list)
	{
		if (blockID == DwarvenBlock.blockDwarvenOreStorage.blockID)
		{
			for (int i = 0; i < 3; i++)
			{
				list.add(new ItemStack(blockID, 1, i));
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getBlockTextureFromSideAndMetadata(int side, int meta)
	{
		return meta == 0 ? this.redstoneIcon : meta == 1 ? this.mithrilIcon : this.ebonyIcon;
	}


	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);

		if (meta == 0)
		{
			return 15;
		}
		return 0;
	}

	@Override
	public int damageDropped(int i)
	{
		return i;
	}
}
