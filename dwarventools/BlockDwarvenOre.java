package rgn.mods.dwarventools;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import net.minecraft.src.*;

public class BlockDwarvenOre extends Block
{
	public BlockDwarvenOre(int blockId, int terrainId)
	{
		super(blockId, terrainId, Material.rock);
		this.setHardness(2.0F);
		this.setResistance(10.0F);
		this.setStepSound(soundStoneFootstep);
		this.setCreativeTab(Config.tabDwarvenTools);
	}
	
	@Override
	public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List list)
	{
		for (int i = 0; i < 2; i++)
		{
			list.add(new ItemStack(blockID, 1, i));
		}
	}
	
	@Override
	public String getTextureFile()
	{
		return "/rgn/sprites/dwarventools/blocks.png";
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
