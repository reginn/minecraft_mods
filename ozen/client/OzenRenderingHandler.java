package rgn.mods.ozen.client;

import org.lwjgl.opengl.GL11;

import net.minecraft.src.*;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

import rgn.mods.ozen.Ozen;

@SideOnly(Side.CLIENT)
public class OzenRenderingHandler implements ISimpleBlockRenderingHandler
{
	private int[] textureIndex = new int[]
		{
			4, 198, 214, 22, 22, 22, 23
		};
		
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
	{
		if (modelID == this.getRenderId())
		{
			int modelType = metadata >>> 3;
			int texture   = metadata & 7;
			
			if (modelType == 0)
			{
				renderInvCuboid(renderer, block,   2.0F/16.0F,  0.0F/16.0F,  2.0F/16.0F, 14.0F/16.0F, 1.0F/16.0F, 14.0F/16.0F,  this.textureIndex[texture]);
				renderInvCuboid(renderer, block,   1.0F/16.0F,  0.0F/16.0F,  1.0F/16.0F,  2.0F/16.0F, 2.0F/16.0F, 15.0F/16.0F,  this.textureIndex[texture]);
				renderInvCuboid(renderer, block,   1.0F/16.0F,  0.0F/16.0F,  1.0F/16.0F, 15.0F/16.0F, 2.0F/16.0F,  2.0F/16.0F,  this.textureIndex[texture]);
				renderInvCuboid(renderer, block,  14.0F/16.0F,  0.0F/16.0F,  1.0F/16.0F, 15.0F/16.0F, 2.0F/16.0F, 15.0F/16.0F,  this.textureIndex[texture]);
				renderInvCuboid(renderer, block,   1.0F/16.0F,  0.0F/16.0F, 14.0F/16.0F, 15.0F/16.0F, 2.0F/16.0F, 15.0F/16.0F,  this.textureIndex[texture]);
			}
			else if(modelType == 1)
			{
				renderInvCuboid(renderer, block,   2.0F/16.0F,  4.0F/16.0F,  2.0F/16.0F, 14.0F/16.0F,  5.0F/16.0F, 14.0F/16.0F,  this.textureIndex[texture]);
				renderInvCuboid(renderer, block,   1.0F/16.0F,  4.0F/16.0F,  1.0F/16.0F,  2.0F/16.0F,  6.0F/16.0F, 15.0F/16.0F,  this.textureIndex[texture]);
				renderInvCuboid(renderer, block,   1.0F/16.0F,  4.0F/16.0F,  1.0F/16.0F, 15.0F/16.0F,  6.0F/16.0F,  2.0F/16.0F,  this.textureIndex[texture]);
				renderInvCuboid(renderer, block,  14.0F/16.0F,  4.0F/16.0F,  1.0F/16.0F, 15.0F/16.0F,  6.0F/16.0F, 15.0F/16.0F,  this.textureIndex[texture]);
				renderInvCuboid(renderer, block,   1.0F/16.0F,  4.0F/16.0F, 14.0F/16.0F, 15.0F/16.0F,  6.0F/16.0F, 15.0F/16.0F,  this.textureIndex[texture]);
				
				renderInvCuboid(renderer, block,   2.0F/16.0F,  0.0F/16.0F,  2.0F/16.0F,  5.0F/16.0F,  4.0F/16.0F,  5.0F/16.0F,  this.textureIndex[texture]);
				renderInvCuboid(renderer, block,   2.0F/16.0F,  0.0F/16.0F, 11.0F/16.0F,  5.0F/16.0F,  4.0F/16.0F, 14.0F/16.0F,  this.textureIndex[texture]);
				renderInvCuboid(renderer, block,  11.0F/16.0F,  0.0F/16.0F,  2.0F/16.0F, 14.0F/16.0F,  4.0F/16.0F,  5.0F/16.0F,  this.textureIndex[texture]);
				renderInvCuboid(renderer, block,  11.0F/16.0F,  0.0F/16.0F, 11.0F/16.0F, 14.0F/16.0F,  4.0F/16.0F, 14.0F/16.0F,  this.textureIndex[texture]);
			}
		}
	}
	
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
	{
		if (modelId == this.getRenderId())
		{
			int modelType = world.getBlockMetadata(x, y, z) >>> 3;
			int texture   = world.getBlockMetadata(x, y, z) & 7;
			
			if (modelType == 0)
			{
				renderer.overrideBlockTexture = this.textureIndex[texture];
				int rgb = block.colorMultiplier(world, x, y, z);
				float r = (float)(rgb >> 16 & 255) / 255.0F;
				float g = (float)(rgb >>  8 & 255) / 255.0F;
				float b = (float)(rgb       & 255) / 255.0F;
				
				block.setBlockBounds( 2.0F/16.0F, 0.0F/16.0F, 2.0F/16.0F, 14.0F/16.0F, 1.0F/16.0F, 14.0F/16.0F);
				renderer.func_83018_a(block);
				renderer.renderStandardBlockWithColorMultiplier(block, x, y, z, r, g, b);
				
				block.setBlockBounds( 1.0F/16.0F, 0.0F/16.0F, 1.0F/16.0F, 2.0F/16.0F, 2.0F/16.0F, 15.0F/16.0F);
				renderer.func_83018_a(block);
				renderer.renderStandardBlockWithColorMultiplier(block, x, y, z, r, g, b);
				
				block.setBlockBounds( 1.0F/16.0F, 0.0F/16.0F, 1.0F/16.0F, 15.0F/16.0F, 2.0F/16.0F, 2.0F/16.0F);
				renderer.func_83018_a(block);
				renderer.renderStandardBlockWithColorMultiplier(block, x, y, z, r, g, b);
				
				block.setBlockBounds(14.0F/16.0F, 0.0F/16.0F, 1.0F/16.0F, 15.0F/16.0F, 2.0F/16.0F, 15.0F/16.0F);
				renderer.func_83018_a(block);
				renderer.renderStandardBlockWithColorMultiplier(block, x, y, z, r, g, b);
				
				block.setBlockBounds( 1.0F/16.0F, 0.0F/16.0F, 14.0F/16.0F, 15.0F/16.0F, 2.0F/16.0F, 15.0F/16.0F);
				renderer.func_83018_a(block);
				renderer.renderStandardBlockWithColorMultiplier(block, x, y, z, r, g, b);
				
				renderer.overrideBlockTexture = -1;
				block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
				renderer.func_83018_a(block);
				
				return false;
			}
			else if (modelType == 1)
			{
				renderer.overrideBlockTexture = this.textureIndex[texture];
				int rgb = block.colorMultiplier(world, x, y, z);
				float r = (float)(rgb >> 16 & 255) / 255.0F;
				float g = (float)(rgb >>  8 & 255) / 255.0F;
				float b = (float)(rgb       & 255) / 255.0F;
				
				block.setBlockBounds( 2.0F/16.0F,  4.0F/16.0F, 2.0F/16.0F, 14.0F/16.0F, 5.0F/16.0F, 14.0F/16.0F);
				renderer.func_83018_a(block);
				renderer.renderStandardBlockWithColorMultiplier(block, x, y, z, r, g, b);
				
				block.setBlockBounds( 1.0F/16.0F,  4.0F/16.0F, 1.0F/16.0F, 2.0F/16.0F, 6.0F/16.0F, 15.0F/16.0F);
				renderer.func_83018_a(block);
				renderer.renderStandardBlockWithColorMultiplier(block, x, y, z, r, g, b);
				
				block.setBlockBounds( 1.0F/16.0F,  4.0F/16.0F, 1.0F/16.0F, 15.0F/16.0F, 6.0F/16.0F, 2.0F/16.0F);
				renderer.func_83018_a(block);
				renderer.renderStandardBlockWithColorMultiplier(block, x, y, z, r, g, b);
				
				block.setBlockBounds(14.0F/16.0F,  4.0F/16.0F, 1.0F/16.0F, 15.0F/16.0F, 6.0F/16.0F, 15.0F/16.0F);
				renderer.func_83018_a(block);
				renderer.renderStandardBlockWithColorMultiplier(block, x, y, z, r, g, b);
				
				block.setBlockBounds( 1.0F/16.0F,  4.0F/16.0F, 14.0F/16.0F, 15.0F/16.0F, 6.0F/16.0F, 15.0F/16.0F);
				renderer.func_83018_a(block);
				renderer.renderStandardBlockWithColorMultiplier(block, x, y, z, r, g, b);
				
				block.setBlockBounds( 2.0F/16.0F, 0.0F/16.0F, 2.0F/16.0F, 5.0F/16.0F, 4.0F/16.0F, 5.0F/16.0F);
				renderer.func_83018_a(block);
				renderer.renderStandardBlockWithColorMultiplier(block, x, y, z, r, g, b);
				
				block.setBlockBounds( 2.0F/16.0F, 0.0F/16.0F, 11.0F/16.0F, 5.0F/16.0F, 4.0F/16.0F, 14.0F/16.0F);
				renderer.func_83018_a(block);
				renderer.renderStandardBlockWithColorMultiplier(block, x, y, z, r, g, b);
				
				block.setBlockBounds( 11.0F/16.0F, 0.0F/16.0F, 2.0F/16.0F, 14.0F/16.0F, 4.0F/16.0F, 5.0F/16.0F);
				renderer.func_83018_a(block);
				renderer.renderStandardBlockWithColorMultiplier(block, x, y, z, r, g, b);
				
				block.setBlockBounds( 11.0F/16.0F, 0.0F/16.0F, 11.0F/16.0F, 14.0F/16.0F, 4.0F/16.0F, 14.0F/16.0F);
				renderer.func_83018_a(block);
				renderer.renderStandardBlockWithColorMultiplier(block, x, y, z, r, g, b);
				
				renderer.overrideBlockTexture = -1;
				block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
				renderer.func_83018_a(block);
				
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
	
	private void renderInvCuboid(RenderBlocks renderer, Block block, float minX, float minY, float minZ, float maxX, float maxY, float maxZ, int textureIndex)
	{
		Tessellator tessellator = Tessellator.instance;
		block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		renderer.func_83018_a(block);
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		block.setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
		renderer.func_83018_a(block);
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
		renderer.func_83018_a(block);
	}
}