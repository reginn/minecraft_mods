package rgn.mods.dwarventools;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.src.*;

public class BlockDwarvenOreStorage extends Block
{
	public BlockDwarvenOreStorage(int blockId, int terrainId)
	{
		super(blockId, terrainId, Material.iron);
		this.setHardness(2.0F);
		this.setResistance(10.0F);
		this.setStepSound(soundMetalFootstep);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public String getTextureFile()
	{
		return "/rgn/sprites/dwarventools/blocks.png";
	}
	
}
