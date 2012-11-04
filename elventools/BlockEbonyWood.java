package rgn.mods.elventools;

import java.util.ArrayList;

import net.minecraft.src.*;

public class BlockEbonyWood extends Block
{
	public BlockEbonyWood(int blockId, int terrainId)
	{
		super(blockId, terrainId, Material.wood);
		this.setHardness(3.0F);
		this.setResistance(5.0F);
		this.setStepSound(soundWoodFootstep);
		this.setRequiresSelfNotify();
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public String getTextureFile()
	{
		return "/rgn/sprites/elventools/blocks.png";
	}

}