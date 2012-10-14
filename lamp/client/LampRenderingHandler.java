package rgn.mods.lamp.client;

import org.lwjgl.opengl.GL11;

import net.minecraft.src.*;
import cpw.mods.fml.client.registry.*;

import rgn.mods.lamp.*;

public class LampRenderingHandler implements ISimpleBlockRenderingHandler
{
	private final int glassTextureIndex    = 49;
	private final int frameTextureIndex    = 139;
	private final int torchTextureIndex    = 80;
	private final int glowTextureIndex     = 22;
	private final int goldTextureIndex     = 23;
	private final int diamondTextureIndex  = 24;
	
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
	{
		if (modelID == Lamp.lampRenderType)
		{
			if (metadata == 0)
			{
				// torch
				renderInvCuboid(renderer, block,  7.0F/16.0F,  1.0F/16.0F,  7.0F/16.0F,  9.0F/16.0F,  9.0F/16.0F,  9.0F/16.0F,  torchTextureIndex);
				// glass
				renderInvCuboid(renderer, block,  5.0F/16.0F,  3.0F/16.0F,  5.0F/16.0F, 11.0F/16.0F, 12.0F/16.0F, 11.0F/16.0F,  glassTextureIndex);
				//bottom
				renderInvCuboid(renderer, block,  4.0F/16.0F,        0.0F,  4.0F/16.0F, 12.0F/16.0F,  3.0F/16.0F, 12.0F/16.0F,  frameTextureIndex);
				//column
				renderInvCuboid(renderer, block,  4.0F/16.0F,  1.0F/16.0F,  4.0F/16.0F,  5.0F/16.0F, 12.0F/16.0F,  5.0F/16.0F,  frameTextureIndex);
				renderInvCuboid(renderer, block,  4.0F/16.0F,  1.0F/16.0F, 11.0F/16.0F,  5.0F/16.0F, 12.0F/16.0F, 12.0F/16.0F,  frameTextureIndex);
				renderInvCuboid(renderer, block, 11.0F/16.0F,  1.0F/16.0F,  4.0F/16.0F, 12.0F/16.0F, 12.0F/16.0F,  5.0F/16.0F,  frameTextureIndex);
				renderInvCuboid(renderer, block, 11.0F/16.0F,  1.0F/16.0F, 11.0F/16.0F, 12.0F/16.0F, 12.0F/16.0F, 12.0F/16.0F,  frameTextureIndex);
				// top
				renderInvCuboid(renderer, block,  4.0F/16.0F, 12.0F/16.0F,  4.0F/16.0F, 12.0F/16.0F, 13.0F/16.0F, 12.0F/16.0F,  frameTextureIndex);
				renderInvCuboid(renderer, block,  6.0F/16.0F, 13.0F/16.0F,  6.0F/16.0F, 10.0F/16.0F, 14.0F/16.0F, 10.0F/16.0F,  frameTextureIndex);
				renderInvCuboid(renderer, block,  7.0F/16.0F, 14.0F/16.0F,  7.0F/16.0F,  9.0F/16.0F, 16.0F/16.0F,  9.0F/16.0F,  frameTextureIndex);
			}
			else if (metadata == 1 || metadata == 2 || metadata == 3)
			{
				// cover
				int tex = metadata == 1 ? glowTextureIndex : metadata == 2 ? goldTextureIndex : diamondTextureIndex;
				renderInvCuboid(renderer, block,  5.0F/16.0F,  3.0F/16.0F,  5.0F/16.0F, 11.0F/16.0F, 12.0F/16.0F, 11.0F/16.0F,  tex);
				//bottom
				renderInvCuboid(renderer, block,  4.0F/16.0F,        0.0F,  4.0F/16.0F, 12.0F/16.0F,  3.0F/16.0F, 12.0F/16.0F,  frameTextureIndex);
				//column
				renderInvCuboid(renderer, block,  4.0F/16.0F,  1.0F/16.0F,  4.0F/16.0F,  5.0F/16.0F, 12.0F/16.0F,  5.0F/16.0F,  frameTextureIndex);
				renderInvCuboid(renderer, block,  4.0F/16.0F,  1.0F/16.0F, 11.0F/16.0F,  5.0F/16.0F, 12.0F/16.0F, 12.0F/16.0F,  frameTextureIndex);
				renderInvCuboid(renderer, block, 11.0F/16.0F,  1.0F/16.0F,  4.0F/16.0F, 12.0F/16.0F, 12.0F/16.0F,  5.0F/16.0F,  frameTextureIndex);
				renderInvCuboid(renderer, block, 11.0F/16.0F,  1.0F/16.0F, 11.0F/16.0F, 12.0F/16.0F, 12.0F/16.0F, 12.0F/16.0F,  frameTextureIndex);
				// top
				renderInvCuboid(renderer, block,  4.0F/16.0F, 12.0F/16.0F,  4.0F/16.0F, 12.0F/16.0F, 13.0F/16.0F, 12.0F/16.0F,  frameTextureIndex);
				renderInvCuboid(renderer, block,  6.0F/16.0F, 13.0F/16.0F,  6.0F/16.0F, 10.0F/16.0F, 14.0F/16.0F, 10.0F/16.0F,  frameTextureIndex);
				renderInvCuboid(renderer, block,  7.0F/16.0F, 14.0F/16.0F,  7.0F/16.0F,  9.0F/16.0F, 16.0F/16.0F,  9.0F/16.0F,  frameTextureIndex);
			}
		}
	}
	
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelID, RenderBlocks renderer)
	{
		if (modelID == Lamp.lampRenderType)
		{
			int meta = world.getBlockMetadata(x, y, z);
			if (meta == 0)
			{
				// torch
				renderer.overrideBlockTexture = torchTextureIndex;
				block.setBlockBounds(7.2F/16.0F, 1.0F/16.0F, 7.2F/16.0F, 8.8F/16.0F, 9.0F/16.0F, 8.8F/16.0F);
				renderer.renderStandardBlock(block, x, y, z);
				
				// glass
				renderer.overrideBlockTexture = glassTextureIndex;
				block.setBlockBounds(5.0F/16.0F, 3.0F/16.0F, 5.0F/16.0F, 11.0F/16.0F, 12.0F/16.0F, 11.0F/16.0F);
				renderer.renderStandardBlock(block, x, y, z);
				
				// bottom
				renderer.overrideBlockTexture = frameTextureIndex;
				block.setBlockBounds(4.0F/16.0F, 0.0F, 4.0F/16.0F, 12.0F/16.0F, 3.0F/16.0F, 12.0F/16.0F);
				renderer.renderStandardBlock(block, x, y, z);
				
				// coulumn
				block.setBlockBounds(4.0F/16.0F, 1.0F/16.0F, 4.0F/16.0F, 5.0F/16.0F, 12.0F/16.0F, 5.0F/16.0F);
				renderer.renderStandardBlock(block, x, y, z);
				block.setBlockBounds(4.0F/16.0F, 1.0F/16.0F, 11.0F/16.0F, 5.0F/16.0F, 12.0F/16.0F, 12.0F/16.0F);
				renderer.renderStandardBlock(block, x, y, z);
				block.setBlockBounds(11.0F/16.0F, 1.0F/16.0F, 4.0F/16.0F, 12.0F/16.0F, 12.0F/16.0F, 5.0F/16.0F);
				renderer.renderStandardBlock(block, x, y, z);
				block.setBlockBounds(11.0F/16.0F, 1.0F/16.0F, 11.0F/16.0F, 12.0F/16.0F, 12.0F/16.0F, 12.0F/16.0F);
				renderer.renderStandardBlock(block, x, y, z);
				
				// top
				block.setBlockBounds(4.0F/16.0F, 12.0F/16.0F, 4.0F/16.0F, 12.0F/16.0F, 13.0F/16.0F, 12.0F/16.0F);
				renderer.renderStandardBlock(block, x, y, z);
				
				block.setBlockBounds(6.0F/16.0F, 13.0F/16.0F, 6.0F/16.0F, 10.0F/16.0F, 14.0F/16.0F, 10.0F/16.0F);
				renderer.renderStandardBlock(block, x, y, z);
				
				block.setBlockBounds(7.0F/16.0F, 14.0F/16.0F, 7.0F/16.0F, 9.0F/16.0F, 16.0F/16.0F, 9.0F/16.0F);
				renderer.renderStandardBlock(block, x, y, z);
					
				renderer.overrideBlockTexture = -1;
				block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
				
				return true;
			}
			else if (meta == 1 || meta == 2 || meta == 3)
			{
				// cover
				renderer.overrideBlockTexture = meta == 1 ? glowTextureIndex : meta == 2 ? goldTextureIndex : diamondTextureIndex;
				block.setBlockBounds(5.0F/16.0F, 1.0F/16.0F, 5.0F/16.0F, 11.0F/16.0F, 12.0F/16.0F, 11.0F/16.0F);
				renderer.renderStandardBlock(block, x, y, z);
				
				// bottom
				renderer.overrideBlockTexture = frameTextureIndex;
				block.setBlockBounds( 4.0F/16.0F, 0.0F,  4.0F/16.0F,  5.0F/16.0F, 1.0F/16.0F, 12.0F/16.0F);
				renderer.renderStandardBlock(block, x, y, z);
				block.setBlockBounds( 4.0F/16.0F, 0.0F,  4.0F/16.0F, 12.0F/16.0F, 1.0F/16.0F,  5.0F/16.0F);
				renderer.renderStandardBlock(block, x, y, z);
				block.setBlockBounds(11.0F/16.0F, 0.0F,  4.0F/16.0F, 12.0F/16.0F, 1.0F/16.0F, 12.0F/16.0F);
				renderer.renderStandardBlock(block, x, y, z);
				block.setBlockBounds( 4.0F/16.0F, 0.0F, 11.0F/16.0F, 12.0F/16.0F, 1.0F/16.0F, 12.0F/16.0F);
				renderer.renderStandardBlock(block, x, y, z);
				
				// coulumn
				block.setBlockBounds(4.0F/16.0F, 1.0F/16.0F, 4.0F/16.0F, 5.0F/16.0F, 12.0F/16.0F, 5.0F/16.0F);
				renderer.renderStandardBlock(block, x, y, z);
				block.setBlockBounds(4.0F/16.0F, 1.0F/16.0F, 11.0F/16.0F, 5.0F/16.0F, 12.0F/16.0F, 12.0F/16.0F);
				renderer.renderStandardBlock(block, x, y, z);
				block.setBlockBounds(11.0F/16.0F, 1.0F/16.0F, 4.0F/16.0F, 12.0F/16.0F, 12.0F/16.0F, 5.0F/16.0F);
				renderer.renderStandardBlock(block, x, y, z);
				block.setBlockBounds(11.0F/16.0F, 1.0F/16.0F, 11.0F/16.0F, 12.0F/16.0F, 12.0F/16.0F, 12.0F/16.0F);
				renderer.renderStandardBlock(block, x, y, z);
				
				// top
				block.setBlockBounds(4.0F/16.0F, 12.0F/16.0F, 4.0F/16.0F, 12.0F/16.0F, 13.0F/16.0F, 12.0F/16.0F);
				renderer.renderStandardBlock(block, x, y, z);
				
				block.setBlockBounds(6.0F/16.0F, 13.0F/16.0F, 6.0F/16.0F, 10.0F/16.0F, 14.0F/16.0F, 10.0F/16.0F);
				renderer.renderStandardBlock(block, x, y, z);
				
				block.setBlockBounds(7.0F/16.0F, 14.0F/16.0F, 7.0F/16.0F, 9.0F/16.0F, 16.0F/16.0F, 9.0F/16.0F);
				renderer.renderStandardBlock(block, x, y, z);
					
				renderer.overrideBlockTexture = -1;
				block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
				
				return true;
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
		return Lamp.lampRenderType;
	}
	
	private void renderInvCuboid(RenderBlocks renderer, Block block, float minX, float minY, float minZ, float maxX, float maxY, float maxZ, int textureIndex)
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

}