package rgn.mods.dwarventools.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import rgn.mods.dwarventools.config.Config;
import rgn.mods.dwarventools.core.DwarvenBlock;

public class BlockDwarvenOreStorage extends Block
{
	public BlockDwarvenOreStorage(int blockId, int terrainId)
	{
		super(blockId, terrainId, Material.iron);
		this.setHardness(2.0F);
		this.setResistance(10.0F);
		this.setStepSound(soundMetalFootstep);
		this.setCreativeTab(Config.tabDwarvenTools);
	}

	@Override
	public String getTextureFile()
	{
		return "/rgn/sprites/dwarventools/blocks.png";
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
	public int getBlockTextureFromSideAndMetadata(int i, int j)
	{
		return this.blockIndexInTexture + j;
	}

	@Override
	public int damageDropped(int i)
	{
		return i;
	}
}
