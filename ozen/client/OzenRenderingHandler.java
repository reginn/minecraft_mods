package rgn.mods.ozen.client;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import rgn.mods.ozen.Ozen;

@SideOnly(Side.CLIENT)
public class OzenRenderingHandler implements ISimpleBlockRenderingHandler
{
	private Icon[] icon = new Icon[]
		{
			Block.planks.getIcon(0, 0),
			Block.planks.getIcon(0, 1),
			Block.planks.getIcon(0, 2),
			Block.blockIron.getBlockTextureFromSide(0),
			Block.blockIron.getBlockTextureFromSide(0),
			Block.blockIron.getBlockTextureFromSide(0),
			Block.blockGold.getBlockTextureFromSide(0)
		};

	private void refreshIcon()
	{
		this.icon[0] = Block.planks.getIcon(0, 0);
		this.icon[1] = Block.planks.getIcon(0, 1);
		this.icon[2] = Block.planks.getIcon(0, 2);
		this.icon[3] = Block.blockIron.getBlockTextureFromSide(0);
		this.icon[4] = Block.blockIron.getBlockTextureFromSide(0);
		this.icon[5] = Block.blockIron.getBlockTextureFromSide(0);
		this.icon[6] = Block.blockGold.getBlockTextureFromSide(0);
	}

	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
	{
		this.refreshIcon();
		if (modelID == this.getRenderId())
		{
			int modelType = metadata >>> 3;
			int texture   = metadata & 7;

			if (modelType == 0)
			{
				renderInvCuboid(renderer, block,   2.0F/16.0F,  0.0F/16.0F,  2.0F/16.0F, 14.0F/16.0F, 1.0F/16.0F, 14.0F/16.0F,  this.icon[texture]);
				renderInvCuboid(renderer, block,   1.0F/16.0F,  0.0F/16.0F,  1.0F/16.0F,  2.0F/16.0F, 2.0F/16.0F, 15.0F/16.0F,  this.icon[texture]);
				renderInvCuboid(renderer, block,   1.0F/16.0F,  0.0F/16.0F,  1.0F/16.0F, 15.0F/16.0F, 2.0F/16.0F,  2.0F/16.0F,  this.icon[texture]);
				renderInvCuboid(renderer, block,  14.0F/16.0F,  0.0F/16.0F,  1.0F/16.0F, 15.0F/16.0F, 2.0F/16.0F, 15.0F/16.0F,  this.icon[texture]);
				renderInvCuboid(renderer, block,   1.0F/16.0F,  0.0F/16.0F, 14.0F/16.0F, 15.0F/16.0F, 2.0F/16.0F, 15.0F/16.0F,  this.icon[texture]);
			}
			else if(modelType == 1)
			{
				renderInvCuboid(renderer, block,   2.0F/16.0F,  4.0F/16.0F,  2.0F/16.0F, 14.0F/16.0F,  5.0F/16.0F, 14.0F/16.0F,  this.icon[texture]);
				renderInvCuboid(renderer, block,   1.0F/16.0F,  4.0F/16.0F,  1.0F/16.0F,  2.0F/16.0F,  6.0F/16.0F, 15.0F/16.0F,  this.icon[texture]);
				renderInvCuboid(renderer, block,   1.0F/16.0F,  4.0F/16.0F,  1.0F/16.0F, 15.0F/16.0F,  6.0F/16.0F,  2.0F/16.0F,  this.icon[texture]);
				renderInvCuboid(renderer, block,  14.0F/16.0F,  4.0F/16.0F,  1.0F/16.0F, 15.0F/16.0F,  6.0F/16.0F, 15.0F/16.0F,  this.icon[texture]);
				renderInvCuboid(renderer, block,   1.0F/16.0F,  4.0F/16.0F, 14.0F/16.0F, 15.0F/16.0F,  6.0F/16.0F, 15.0F/16.0F,  this.icon[texture]);

				renderInvCuboid(renderer, block,   2.0F/16.0F,  0.0F/16.0F,  2.0F/16.0F,  5.0F/16.0F,  4.0F/16.0F,  5.0F/16.0F,  this.icon[texture]);
				renderInvCuboid(renderer, block,   2.0F/16.0F,  0.0F/16.0F, 11.0F/16.0F,  5.0F/16.0F,  4.0F/16.0F, 14.0F/16.0F,  this.icon[texture]);
				renderInvCuboid(renderer, block,  11.0F/16.0F,  0.0F/16.0F,  2.0F/16.0F, 14.0F/16.0F,  4.0F/16.0F,  5.0F/16.0F,  this.icon[texture]);
				renderInvCuboid(renderer, block,  11.0F/16.0F,  0.0F/16.0F, 11.0F/16.0F, 14.0F/16.0F,  4.0F/16.0F, 14.0F/16.0F,  this.icon[texture]);
			}
		}
	}

	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
	{
		this.refreshIcon();
		if (modelId == this.getRenderId())
		{
			int modelType = world.getBlockMetadata(x, y, z) >>> 3;
			int texture   = world.getBlockMetadata(x, y, z) & 7;

			if (modelType == 0)
			{
				renderer.setOverrideBlockTexture(icon[texture]);
				int rgb = block.colorMultiplier(world, x, y, z);
				float r = (float)(rgb >> 16 & 255) / 255.0F;
				float g = (float)(rgb >>  8 & 255) / 255.0F;
				float b = (float)(rgb       & 255) / 255.0F;

				block.setBlockBounds( 2.0F/16.0F, 0.0F/16.0F, 2.0F/16.0F, 14.0F/16.0F, 1.0F/16.0F, 14.0F/16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlockWithColorMultiplier(block, x, y, z, r, g, b);

				block.setBlockBounds( 1.0F/16.0F, 0.0F/16.0F, 1.0F/16.0F, 2.0F/16.0F, 2.0F/16.0F, 15.0F/16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlockWithColorMultiplier(block, x, y, z, r, g, b);

				block.setBlockBounds( 1.0F/16.0F, 0.0F/16.0F, 1.0F/16.0F, 15.0F/16.0F, 2.0F/16.0F, 2.0F/16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlockWithColorMultiplier(block, x, y, z, r, g, b);

				block.setBlockBounds(14.0F/16.0F, 0.0F/16.0F, 1.0F/16.0F, 15.0F/16.0F, 2.0F/16.0F, 15.0F/16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlockWithColorMultiplier(block, x, y, z, r, g, b);

				block.setBlockBounds( 1.0F/16.0F, 0.0F/16.0F, 14.0F/16.0F, 15.0F/16.0F, 2.0F/16.0F, 15.0F/16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlockWithColorMultiplier(block, x, y, z, r, g, b);

				renderer.clearOverrideBlockTexture();
				block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
				renderer.setRenderBoundsFromBlock(block);

				return false;
			}
			else if (modelType == 1)
			{
				renderer.overrideBlockTexture = this.icon[texture];
				int rgb = block.colorMultiplier(world, x, y, z);
				float r = (float)(rgb >> 16 & 255) / 255.0F;
				float g = (float)(rgb >>  8 & 255) / 255.0F;
				float b = (float)(rgb       & 255) / 255.0F;

				block.setBlockBounds( 2.0F/16.0F,  4.0F/16.0F, 2.0F/16.0F, 14.0F/16.0F, 5.0F/16.0F, 14.0F/16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlockWithColorMultiplier(block, x, y, z, r, g, b);

				block.setBlockBounds( 1.0F/16.0F,  4.0F/16.0F, 1.0F/16.0F, 2.0F/16.0F, 6.0F/16.0F, 15.0F/16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlockWithColorMultiplier(block, x, y, z, r, g, b);

				block.setBlockBounds( 1.0F/16.0F,  4.0F/16.0F, 1.0F/16.0F, 15.0F/16.0F, 6.0F/16.0F, 2.0F/16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlockWithColorMultiplier(block, x, y, z, r, g, b);

				block.setBlockBounds(14.0F/16.0F,  4.0F/16.0F, 1.0F/16.0F, 15.0F/16.0F, 6.0F/16.0F, 15.0F/16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlockWithColorMultiplier(block, x, y, z, r, g, b);

				block.setBlockBounds( 1.0F/16.0F,  4.0F/16.0F, 14.0F/16.0F, 15.0F/16.0F, 6.0F/16.0F, 15.0F/16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlockWithColorMultiplier(block, x, y, z, r, g, b);

				block.setBlockBounds( 2.0F/16.0F, 0.0F/16.0F, 2.0F/16.0F, 5.0F/16.0F, 4.0F/16.0F, 5.0F/16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlockWithColorMultiplier(block, x, y, z, r, g, b);

				block.setBlockBounds( 2.0F/16.0F, 0.0F/16.0F, 11.0F/16.0F, 5.0F/16.0F, 4.0F/16.0F, 14.0F/16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlockWithColorMultiplier(block, x, y, z, r, g, b);

				block.setBlockBounds( 11.0F/16.0F, 0.0F/16.0F, 2.0F/16.0F, 14.0F/16.0F, 4.0F/16.0F, 5.0F/16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlockWithColorMultiplier(block, x, y, z, r, g, b);

				block.setBlockBounds( 11.0F/16.0F, 0.0F/16.0F, 11.0F/16.0F, 14.0F/16.0F, 4.0F/16.0F, 14.0F/16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlockWithColorMultiplier(block, x, y, z, r, g, b);

				renderer.clearOverrideBlockTexture();
				block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
				renderer.setRenderBoundsFromBlock(block);

				return false;
			}
		}
		return false;
	}

	public boolean shouldRender3DInInventory()
	{
		return true;
	}

	public int getRenderId()
	{
		return Ozen.ozenRenderID;
	}

	private void renderInvCuboid(RenderBlocks renderer, Block block, float minX, float minY, float minZ, float maxX, float maxY, float maxZ, Icon icon)
	{
		Tessellator tessellator = Tessellator.instance;
		block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		renderer.setRenderBoundsFromBlock(block);
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		block.setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
		renderer.setRenderBoundsFromBlock(block);
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, -1F, 0.0F);
		renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, icon);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 1.0F, 0.0F);
		renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, icon);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, -1F);
		renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, icon);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, 1.0F);
		renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, icon);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(-1F, 0.0F, 0.0F);
		renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, icon);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(1.0F, 0.0F, 0.0F);
		renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, icon);
		tessellator.draw();
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		renderer.setRenderBoundsFromBlock(block);
	}
}