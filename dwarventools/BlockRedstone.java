package rgn.mods.dwarventools;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.src.*;

public class BlockRedstone extends Block
{
	public BlockRedstone(int blockId, int terrainId)
	{
		super(blockId, terrainId, Material.rock);
		this.setHardness(2.0F);
		this.setResistance(10.0F);
		this.setLightValue(1.0F);
		this.setStepSound(soundStoneFootstep);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public String getTextureFile()
	{
		return "/rgn/sprites/dwarventools/blocks.png";
	}
}
