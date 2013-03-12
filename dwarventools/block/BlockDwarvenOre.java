package rgn.mods.dwarventools.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockDwarvenOre extends Block
{
	@SideOnly(Side.CLIENT)
	private Icon mithrilIcon;

	@SideOnly(Side.CLIENT)
	private Icon ebonyIcon;

	public BlockDwarvenOre(int blockId)
	{
		super(blockId, Material.rock);
		this.setHardness(2.0F);
		this.setResistance(10.0F);
		this.setStepSound(soundStoneFootstep);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void func_94332_a(IconRegister par1IconRegister)
	{
		this.mithrilIcon = par1IconRegister.func_94245_a("rgn/dwarventools:mithrilOre");
		this.ebonyIcon   = par1IconRegister.func_94245_a("rgn/dwarventools:ebonyOre");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getBlockTextureFromSideAndMetadata(int side, int meta)
	{
		return meta == 0 ? this.mithrilIcon : this.ebonyIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List list)
	{
		for (int i = 0; i < 2; i++)
		{
			list.add(new ItemStack(blockID, 1, i));
		}
	}

	@Override
	public int damageDropped(int i)
	{
		return i;
	}

}
