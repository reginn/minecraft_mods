package rgn.mods.elventools.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockEbonyWood extends Block
{
	public BlockEbonyWood(int blockId)
	{
		super(blockId, Material.wood);
		this.setHardness(3.0F);
		this.setResistance(5.0F);
		this.setStepSound(soundWoodFootstep);
	}

}