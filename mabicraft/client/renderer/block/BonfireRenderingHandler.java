package rgn.mods.mabicraft.client.renderer.block;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import rgn.mods.mabicraft.config.Config;

@SideOnly(Side.CLIENT)
public class BonfireRenderingHandler implements ISimpleBlockRenderingHandler
{
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
	{
		renderInvCuboid(renderer, block, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F, block.getBlockTextureFromSide(0));
	}

	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelID, RenderBlocks renderer)
	{
		this.renderBlockFire(world, x, y, z, block, modelID, renderer);

		renderer.setOverrideBlockTexture(Block.wood.getBlockTextureFromSide(2));
		block.setBlockBounds(13.0F/16.0F, 0.0F/16.0F, 0.0F, 14.0F/16.0F, 1.0F/16.0F, 16.0F/16.0F);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlock(block, x, y, z);

		block.setBlockBounds(13.0F/16.0F, 3.0F/16.0F, 0.0F, 14.0F/16.0F, 4.0F/16.0F, 16.0F/16.0F);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlock(block, x, y, z);

		block.setBlockBounds(13.0F/16.0F, 6.0F/16.0F, 0.0F, 14.0F/16.0F, 7.0F/16.0F, 16.0F/16.0F);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlock(block, x, y, z);

		block.setBlockBounds(1.0F/16.0F, 0.0F/16.0F, 0.0F, 2.0F/16.0F, 1.0F/16.0F, 16.0F/16.0F);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlock(block, x, y, z);

		block.setBlockBounds(1.0F/16.0F, 3.0F/16.0F, 0.0F, 2.0F/16.0F, 4.0F/16.0F, 16.0F/16.0F);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlock(block, x, y, z);

		block.setBlockBounds(1.0F/16.0F, 6.0F/16.0F, 0.0F, 2.0F/16.0F, 7.0F/16.0F, 16.0F/16.0F);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlock(block, x, y, z);

		block.setBlockBounds( 0.0F/16.0F, 1.0F/16.0F, 13.0F/16.0F, 16.0F/16.0F, 2.0F/16.0F, 14.0F/16.0F);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlock(block, x, y, z);

		block.setBlockBounds( 0.0F/16.0F, 4.0F/16.0F, 13.0F/16.0F, 16.0F/16.0F, 5.0F/16.0F, 14.0F/16.0F);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlock(block, x, y, z);

		block.setBlockBounds( 0.0F/16.0F, 7.0F/16.0F, 13.0F/16.0F, 16.0F/16.0F, 8.0F/16.0F, 14.0F/16.0F);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlock(block, x, y, z);

		block.setBlockBounds( 0.0F/16.0F, 1.0F/16.0F, 1.0F/16.0F, 16.0F/16.0F, 2.0F/16.0F, 2.0F/16.0F);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlock(block, x, y, z);

		block.setBlockBounds( 0.0F/16.0F, 4.0F/16.0F, 1.0F/16.0F, 16.0F/16.0F, 5.0F/16.0F, 2.0F/16.0F);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlock(block, x, y, z);

		block.setBlockBounds( 0.0F/16.0F, 7.0F/16.0F, 1.0F/16.0F, 16.0F/16.0F, 8.0F/16.0F, 2.0F/16.0F);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlock(block, x, y, z);

		renderer.clearOverrideBlockTexture();
		block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		renderer.setRenderBoundsFromBlock(block);
		return true;
	}

	public boolean shouldRender3DInInventory()
	{
		return false;
	}

	public int getRenderId()
	{
		return Config.RENDER_TYPE_BONFIRE;
	}

	private void renderInvCuboid(RenderBlocks renderer, Block block, float minX, float minY, float minZ, float maxX, float maxY, float maxZ, Icon textureIndex)
	{
		Tessellator tessellator = Tessellator.instance;
		block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		block.setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, -1F, 0.0F);
		renderer.renderBottomFace(block, 0.0D, 0.0D, 0.0D, textureIndex);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 1.0F, 0.0F);
		renderer.renderTopFace(block, 0.0D, 0.0D, 0.0D, textureIndex);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, -1F);
		renderer.renderEastFace(block, 0.0D, 0.0D, 0.0D, textureIndex);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, 1.0F);
		renderer.renderWestFace(block, 0.0D, 0.0D, 0.0D, textureIndex);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(-1F, 0.0F, 0.0F);
		renderer.renderNorthFace(block, 0.0D, 0.0D, 0.0D, textureIndex);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(1.0F, 0.0F, 0.0F);
		renderer.renderSouthFace(block, 0.0D, 0.0D, 0.0D, textureIndex);
		tessellator.draw();
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}

	private void renderBlockFire(IBlockAccess world, int x, int y, int z, Block block, int modelID, RenderBlocks renderer)
	{
		Tessellator tessellator = Tessellator.instance;

		Icon icon  = Block.fire.func_94438_c(0);
		Icon icon1 = Block.fire.func_94438_c(1);
		Icon icon2 = icon;

		if (renderer.hasOverrideBlockTexture())
		{
			icon2 = renderer.overrideBlockTexture;
		}

		tessellator.setColorOpaque_F(1.0F, 1.0F, 1.0F);
		tessellator.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z));

		double d0 = (double)icon2.getMinU();
		double d1 = (double)icon2.getMinV();
		double d2 = (double)icon2.getMaxU();
		double d3 = (double)icon2.getMaxV();

		tessellator.addVertexWithUV((double)((float)x + 0.2F), (double)((float)y + 1.2F), (double)((float)z + 0.8F), d2, d1);
		tessellator.addVertexWithUV((double)((float)x + 0.7F), (double)((float)y + 0.1F), (double)((float)z + 0.8F), d2, d3);
		tessellator.addVertexWithUV((double)((float)x + 0.7F), (double)((float)y + 0.1F), (double)((float)z + 0.2F), d0, d3);
		tessellator.addVertexWithUV((double)((float)x + 0.2F), (double)((float)y + 1.2F), (double)((float)z + 0.2F), d0, d1);

		tessellator.addVertexWithUV((double)((float)x + 0.8F), (double)((float)y + 1.2F), (double)((float)z + 0.2F), d2, d1);
		tessellator.addVertexWithUV((double)((float)x + 0.3F), (double)((float)y + 0.1F), (double)((float)z + 0.2F), d2, d3);
		tessellator.addVertexWithUV((double)((float)x + 0.3F), (double)((float)y + 0.1F), (double)((float)z + 0.8F), d0, d3);
		tessellator.addVertexWithUV((double)((float)x + 0.8F), (double)((float)y + 1.2F), (double)((float)z + 0.8F), d0, d1);

		d0 = (double)icon1.getMinU();
		d1 = (double)icon1.getMinV();
		d2 = (double)icon1.getMaxU();
		d3 = (double)icon1.getMaxV();


		tessellator.addVertexWithUV((double)(x + 0.8F), (double)((float)y + 1.2F), (double)((float)z + 0.8F), d2, d1);
		tessellator.addVertexWithUV((double)(x + 0.8F), (double)((float)y + 0.1F), (double)((float)z + 0.3F), d2, d3);
		tessellator.addVertexWithUV((double)(x + 0.2F), (double)((float)y + 0.1F), (double)((float)z + 0.3F), d0, d3);
		tessellator.addVertexWithUV((double)(x + 0.2F), (double)((float)y + 1.2F), (double)((float)z + 0.8F), d0, d1);

		tessellator.addVertexWithUV((double)(x + 0.2F), (double)((float)y + 1.2F), (double)((float)z + 0.2F), d2, d1);
		tessellator.addVertexWithUV((double)(x + 0.2F), (double)((float)y + 0.1F), (double)((float)z + 0.7F), d2, d3);
		tessellator.addVertexWithUV((double)(x + 0.8F), (double)((float)y + 0.1F), (double)((float)z + 0.7F), d0, d3);
		tessellator.addVertexWithUV((double)(x + 0.8F), (double)((float)y + 1.2F), (double)((float)z + 0.2F), d0, d1);

		tessellator.addVertexWithUV((double)((float)x + 0.3F), (double)((float)y + 1.2F), (double)((float)z + 0.2F), d0, d1);
		tessellator.addVertexWithUV((double)((float)x + 0.2F), (double)((float)y + 0.1F), (double)((float)z + 0.2F), d0, d3);
		tessellator.addVertexWithUV((double)((float)x + 0.2F), (double)((float)y + 0.1F), (double)((float)z + 0.8F), d2, d3);
		tessellator.addVertexWithUV((double)((float)x + 0.3F), (double)((float)y + 1.2F), (double)((float)z + 0.8F), d2, d1);

		tessellator.addVertexWithUV((double)((float)x + 0.7F), (double)((float)y + 1.2F), (double)((float)z + 0.8F), d0, d1);
		tessellator.addVertexWithUV((double)((float)x + 0.8F), (double)((float)y + 0.1F), (double)((float)z + 0.8F), d0, d3);
		tessellator.addVertexWithUV((double)((float)x + 0.8F), (double)((float)y + 0.1F), (double)((float)z + 0.2F), d2, d3);
		tessellator.addVertexWithUV((double)((float)x + 0.7F), (double)((float)y + 1.2F), (double)((float)z + 0.2F), d2, d1);

		d0 = (double)icon.getMinU();
		d1 = (double)icon.getMinV();
		d2 = (double)icon.getMaxU();
		d3 = (double)icon.getMaxV();

		tessellator.addVertexWithUV((double)((float)x + 0.2F), (double)((float)y + 1.2F), (double)((float)z + 0.7F), d0, d1);
		tessellator.addVertexWithUV((double)((float)x + 0.2F), (double)((float)y + 0.1F), (double)((float)z + 0.8F), d0, d3);
		tessellator.addVertexWithUV((double)((float)x + 0.8F), (double)((float)y + 0.1F), (double)((float)z + 0.8F), d2, d3);
		tessellator.addVertexWithUV((double)((float)x + 0.8F), (double)((float)y + 1.2F), (double)((float)z + 0.7F), d2, d1);

		tessellator.addVertexWithUV((double)((float)x + 0.8F), (double)((float)y + 1.2F), (double)((float)z + 0.3F), d0, d1);
		tessellator.addVertexWithUV((double)((float)x + 0.8F), (double)((float)y + 0.1F), (double)((float)z + 0.2F), d0, d3);
		tessellator.addVertexWithUV((double)((float)x + 0.2F), (double)((float)y + 0.1F), (double)((float)z + 0.2F), d2, d3);
		tessellator.addVertexWithUV((double)((float)x + 0.2F), (double)((float)y + 1.2F), (double)((float)z + 0.3F), d2, d1);
	}
}