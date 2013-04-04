package rgn.mods.rum.client.renderer;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.ChestItemRenderHelper;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;

import rgn.mods.rum.block.BlockLockedChest;
import rgn.mods.rum.block.RumBlock;
import rgn.mods.rum.tileentity.TileEntityLockedChest;

public class LockedChestRenderHelper extends ChestItemRenderHelper
{
	private TileEntityLockedChest tileEntity;

	public LockedChestRenderHelper()
	{
		tileEntity = (TileEntityLockedChest)RumBlock.blockLockedChest.createTileEntity(null, 0);
	}

	@Override
	public void renderChest(Block par1Block, int par2, float par3)
	{
		if (par1Block instanceof BlockLockedChest)
		{
			TileEntityRenderer.instance.renderTileEntityAt(this.tileEntity, 0.0D, 0.0D, 0.0D, 0.0F);
		}
		else
		{
			super.renderChest(par1Block, par2, par3);
		}
	}
}
