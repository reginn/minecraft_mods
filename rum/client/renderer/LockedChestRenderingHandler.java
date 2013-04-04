package rgn.mods.rum.client.renderer;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

import rgn.mods.rum.block.BlockLockedChest;
import rgn.mods.rum.block.RumBlock;
import rgn.mods.rum.tileentity.TileEntityLockedChest;

public class LockedChestRenderingHandler implements ISimpleBlockRenderingHandler
{
	private static LockedChestRenderingHandler INSTANCE = new LockedChestRenderingHandler();
	private static TileEntityLockedChest tileEntity = (TileEntityLockedChest)RumBlock.blockLockedChest.createTileEntity(null, 0);

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
	{
		if (block instanceof BlockLockedChest)
		{
			TileEntityLockedChestRenderer.instance().renderTileEntityInInventory(tileEntity, -0.5D, -0.5D, -0.5D, 0.0F);
		}
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
	{
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory()
	{
		return true;
	}

	@Override
	public int getRenderId()
	{
		return RumBlock.LOCKED_CHEST_RENDER_TYPE;
	}

	public static LockedChestRenderingHandler instance()
	{
		return INSTANCE;
	}
}
