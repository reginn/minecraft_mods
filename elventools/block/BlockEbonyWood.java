package rgn.mods.elventools.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import rgn.mods.elventools.config.Config;

public class BlockEbonyWood extends Block
{
	public BlockEbonyWood(int blockId, int terrainId)
	{
		super(blockId, terrainId, Material.wood);
		this.setHardness(3.0F);
		this.setResistance(5.0F);
		this.setStepSound(soundWoodFootstep);
		this.setRequiresSelfNotify();
		this.setCreativeTab(Config.tabElvenTools);
	}

	@Override
	public String getTextureFile()
	{
		return "/rgn/sprites/elventools/blocks.png";
	}

}