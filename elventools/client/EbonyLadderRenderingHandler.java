package rgn.mods.elventools.client;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;

import net.minecraftforge.common.ForgeDirection;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import rgn.mods.elventools.block.ElvenBlock;

@SideOnly(Side.CLIENT)
public class EbonyLadderRenderingHandler implements ISimpleBlockRenderingHandler
{

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
	{

	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
	{
		if (modelId == this.getRenderId())
		{
			int meta = world.getBlockMetadata(x, y, z);
			return this.renderLadder(world, x, y, z, block, meta, renderer);
		}
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory()
	{
		return false;
	}

	@Override
	public int getRenderId()
	{
		return ElvenBlock.EBONY_LADDER_RENDER_TYPE;
	}

	private boolean renderLadder(IBlockAccess world, int x, int y, int z, Block block, int meta, RenderBlocks renderer)
	{
		ForgeDirection dir = ForgeDirection.getOrientation(meta);

		switch (dir)
		{
			case EAST  : this.renderEast(x, y, z, block, renderer);  return true;
			case WEST  : this.renderWest(x, y, z, block, renderer);  return true;
			case SOUTH : this.renderSouth(x, y, z, block, renderer); return true;
			case NORTH : this.renderNorth(x, y, z, block, renderer); return true;
			default : return false;
		}
	}

	private void renderEast(int x, int y, int z, Block block, RenderBlocks renderer)
	{
		renderer.setOverrideBlockTexture(ElvenBlock.blockEbonyLog.getBlockTextureFromSide(2));
		block.setBlockBounds(1.0F/16.0F, 1.0F/16.0F, 0.0F/16.0F, 2.0F/16.0F, 3.0F/16.0F, 16.0F/16.0F);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlock(block, x, y, z);

		block.setBlockBounds(1.0F/16.0F, 5.0F/16.0F, 0.0F/16.0F, 2.0F/16.0F, 7.0F/16.0F, 16.0F/16.0F);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlock(block, x, y, z);

		block.setBlockBounds(1.0F/16.0F, 9.0F/16.0F, 0.0F/16.0F, 2.0F/16.0F, 11.0F/16.0F, 16.0F/16.0F);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlock(block, x, y, z);

		block.setBlockBounds(1.0F/16.0F, 13.0F/16.0F, 0.0F/16.0F, 2.0F/16.0F, 15.0F/16.0F, 16.0F/16.0F);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlock(block, x, y, z);

		block.setBlockBounds(0.0F/16.0F, 0.0F/16.0F, 1.0F/16.0F, 1.0F/16.0F, 16.0F/16.0F, 3.0F/16.0F);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlock(block, x, y, z);

		block.setBlockBounds(0.0F/16.0F, 0.0F/16.0F, 13.0F/16.0F, 1.0F/16.0F, 16.0F/16.0F, 15.0F/16.0F);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlock(block, x, y, z);

		renderer.clearOverrideBlockTexture();
		block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}

	private void renderWest(int x, int y, int z, Block block, RenderBlocks renderer)
	{
		renderer.setOverrideBlockTexture(ElvenBlock.blockEbonyLog.getBlockTextureFromSide(2));
		block.setBlockBounds(14.0F/16.0F, 1.0F/16.0F, 0.0F/16.0F, 15.0F/16.0F, 3.0F/16.0F, 16.0F/16.0F);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlock(block, x, y, z);

		block.setBlockBounds(14.0F/16.0F, 5.0F/16.0F, 0.0F/16.0F, 15.0F/16.0F, 7.0F/16.0F, 16.0F/16.0F);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlock(block, x, y, z);

		block.setBlockBounds(14.0F/16.0F, 9.0F/16.0F, 0.0F/16.0F, 15.0F/16.0F, 11.0F/16.0F, 16.0F/16.0F);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlock(block, x, y, z);

		block.setBlockBounds(14.0F/16.0F, 13.0F/16.0F, 0.0F/16.0F, 15.0F/16.0F, 15.0F/16.0F, 16.0F/16.0F);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlock(block, x, y, z);

		block.setBlockBounds(15.0F/16.0F, 0.0F/16.0F, 1.0F/16.0F, 16.0F/16.0F, 16.0F/16.0F, 3.0F/16.0F);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlock(block, x, y, z);

		block.setBlockBounds(15.0F/16.0F, 0.0F/16.0F, 13.0F/16.0F, 16.0F/16.0F, 16.0F/16.0F, 15.0F/16.0F);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlock(block, x, y, z);

		renderer.clearOverrideBlockTexture();
		block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}

	private void renderSouth(int x, int y, int z, Block block, RenderBlocks renderer)
	{
		renderer.setOverrideBlockTexture(ElvenBlock.blockEbonyLog.getBlockTextureFromSide(2));
		block.setBlockBounds(0.0F/16.0F, 1.0F/16.0F, 1.0F/16.0F, 16.0F/16.0F, 3.0F/16.0F, 2.0F/16.0F);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlock(block, x, y, z);

		block.setBlockBounds(0.0F/16.0F, 5.0F/16.0F, 1.0F/16.0F, 16.0F/16.0F, 7.0F/16.0F, 2.0F/16.0F);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlock(block, x, y, z);

		block.setBlockBounds(0.0F/16.0F, 9.0F/16.0F, 1.0F/16.0F, 16.0F/16.0F, 11.0F/16.0F, 2.0F/16.0F);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlock(block, x, y, z);

		block.setBlockBounds(0.0F/16.0F, 13.0F/16.0F, 1.0F/16.0F, 16.0F/16.0F, 15.0F/16.0F, 2.0F/16.0F);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlock(block, x, y, z);

		block.setBlockBounds(1.0F/16.0F, 0.0F/16.0F, 0.0F/16.0F, 3.0F/16.0F, 16.0F/16.0F, 1.0F/16.0F);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlock(block, x, y, z);

		block.setBlockBounds(13.0F/16.0F, 0.0F/16.0F, 0.0F/16.0F, 15.0F/16.0F, 16.0F/16.0F, 1.0F/16.0F);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlock(block, x, y, z);

		renderer.clearOverrideBlockTexture();
		block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}

	private void renderNorth(int x, int y, int z, Block block, RenderBlocks renderer)
	{
		renderer.setOverrideBlockTexture(ElvenBlock.blockEbonyLog.getBlockTextureFromSide(2));
		block.setBlockBounds(0.0F/16.0F, 1.0F/16.0F, 14.0F/16.0F, 16.0F/16.0F, 3.0F/16.0F, 15.0F/16.0F);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlock(block, x, y, z);

		block.setBlockBounds(0.0F/16.0F, 5.0F/16.0F, 14.0F/16.0F, 16.0F/16.0F, 7.0F/16.0F, 15.0F/16.0F);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlock(block, x, y, z);

		block.setBlockBounds(0.0F/16.0F, 9.0F/16.0F, 14.0F/16.0F, 16.0F/16.0F, 11.0F/16.0F, 15.0F/16.0F);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlock(block, x, y, z);

		block.setBlockBounds(0.0F/16.0F, 13.0F/16.0F, 14.0F/16.0F, 16.0F/16.0F, 15.0F/16.0F, 15.0F/16.0F);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlock(block, x, y, z);

		block.setBlockBounds(1.0F/16.0F, 0.0F/16.0F, 15.0F/16.0F, 3.0F/16.0F, 16.0F/16.0F, 16.0F/16.0F);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlock(block, x, y, z);

		block.setBlockBounds(13.0F/16.0F, 0.0F/16.0F, 15.0F/16.0F, 15.0F/16.0F, 16.0F/16.0F, 16.0F/16.0F);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlock(block, x, y, z);

		renderer.clearOverrideBlockTexture();
		block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}
}
