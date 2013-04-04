package rgn.mods.elventools.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockEbonyWood extends Block
{
	public BlockEbonyWood(int blockId)
	{
		super(blockId, Material.wood);
		this.setHardness(3.0F);
		this.setResistance(5.0F);
		this.setStepSound(soundWoodFootstep);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.blockIcon = par1IconRegister.registerIcon("rgn/elventools:ebonyPlank");
	}

}