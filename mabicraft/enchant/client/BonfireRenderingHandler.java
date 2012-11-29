package rgn.mods.mabicraft.enchant.client;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

import net.minecraft.src.*;
import net.minecraftforge.client.*;

import rgn.mods.mabicraft.*;
import rgn.mods.mabicraft.config.Config;

@SideOnly(Side.CLIENT)
public class BonfireRenderingHandler implements ISimpleBlockRenderingHandler
{
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
	{
		renderInvCuboid(renderer, block, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F, block.blockIndexInTexture);
	}
	
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelID, RenderBlocks renderer)
	{
		Tessellator tessellator = Tessellator.instance;
		int fireTextureIndex = block.getBlockTextureFromSide(0);
		
		if (renderer.overrideBlockTexture >= 0)
		{
			fireTextureIndex = renderer.overrideBlockTexture;
		}
		
		tessellator.setColorOpaque_F(1.0F, 1.0F, 1.0F);
		tessellator.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z));
		int textureU = (fireTextureIndex & 15) << 4;
		int textureV =  fireTextureIndex & 240;
		double minU = (double)( (float)textureU           / 256.0F);
		double maxU = (double)(((float)textureU + 15.99F) / 256.0F);
		double minV = (double)( (float)textureV           / 256.0F);
		double maxV = (double)(((float)textureV + 15.99F) / 256.0F);
		
		double var18;
		double var20;
		double var22;
		double var24;
		double var26;
		double var28;
		double var30;
		double var32;
		
		// fire
		var18 = (double)x + 0.5D + 0.2D;
		var20 = (double)x + 0.5D - 0.2D;
		var22 = (double)z + 0.5D + 0.2D;
		var24 = (double)z + 0.5D - 0.2D;
		var26 = (double)x + 0.5D - 0.3D;
		var28 = (double)x + 0.5D + 0.3D;
		var30 = (double)z + 0.5D - 0.3D;
		var32 = (double)z + 0.5D + 0.3D;
		tessellator.addVertexWithUV(var26, (double)((float)y + 1.2F), (double)((float)z + 0.8F), maxU, minV);
		tessellator.addVertexWithUV(var18, (double)((float)y + 0.1F), (double)((float)z + 0.8F), maxU, maxV);
		tessellator.addVertexWithUV(var18, (double)((float)y + 0.1F), (double)((float)z + 0.2F), minU, maxV);
		tessellator.addVertexWithUV(var26, (double)((float)y + 1.2F), (double)((float)z + 0.2F), minU, minV);
		
		tessellator.addVertexWithUV(var28, (double)((float)y + 1.2F), (double)((float)z + 0.2F), maxU, minV);
		tessellator.addVertexWithUV(var20, (double)((float)y + 0.1F), (double)((float)z + 0.2F), maxU, maxV);
		tessellator.addVertexWithUV(var20, (double)((float)y + 0.1F), (double)((float)z + 0.8F), minU, maxV);
		tessellator.addVertexWithUV(var28, (double)((float)y + 1.2F), (double)((float)z + 0.8F), minU, minV);
		
		
		minU = (double)( (float)textureU           / 256.0F);
		maxU = (double)(((float)textureU + 15.99F) / 256.0F);
		minV = (double)( (float)(textureV + 16)    / 256.0F);
		maxV = (double)(((float)textureV + 15.99F + 16.0F) / 256.0F);
		tessellator.addVertexWithUV((double)(x + 0.8F), (double)((float)y + 1.2F), var32, maxU, minV);
		tessellator.addVertexWithUV((double)(x + 0.8F), (double)((float)y + 0.1F), var24, maxU, maxV);
		tessellator.addVertexWithUV((double)(x + 0.2F), (double)((float)y + 0.1F), var24, minU,  maxV);
		tessellator.addVertexWithUV((double)(x + 0.2F), (double)((float)y + 1.2F), var32, minU,  minV);
		
		tessellator.addVertexWithUV((double)(x + 0.2F), (double)((float)y + 1.2F), var30, maxU, minV);
		tessellator.addVertexWithUV((double)(x + 0.2F), (double)((float)y + 0.1F), var22, maxU, maxV);
		tessellator.addVertexWithUV((double)(x + 0.8F), (double)((float)y + 0.1F), var22, minU,  maxV);
		tessellator.addVertexWithUV((double)(x + 0.8F), (double)((float)y + 1.2F), var30, minU,  minV);
		
		var18 = (double)x + 0.5D - 0.5D + 0.2D;
		var20 = (double)x + 0.5D + 0.5D - 0.2D;
		var22 = (double)z + 0.5D - 0.5D + 0.2D;
		var24 = (double)z + 0.5D + 0.5D - 0.2D;
		var26 = (double)x + 0.5D - 0.4D + 0.2D;
		var28 = (double)x + 0.5D + 0.4D - 0.2D;
		var30 = (double)z + 0.5D - 0.4D + 0.2D;
		var32 = (double)z + 0.5D + 0.4D - 0.2D;
		tessellator.addVertexWithUV(var26, (double)((float)y + 1.2F), (double)((float)z + 0.2F), minU,  minV);
		tessellator.addVertexWithUV(var18, (double)((float)y + 0.1F), (double)((float)z + 0.2F), minU,  maxV);
		tessellator.addVertexWithUV(var18, (double)((float)y + 0.1F), (double)((float)z + 0.8F), maxU, maxV);
		tessellator.addVertexWithUV(var26, (double)((float)y + 1.2F), (double)((float)z + 0.8F), maxU, minV);
		
		tessellator.addVertexWithUV(var28, (double)((float)y + 1.2F), (double)((float)z + 0.8F), minU,  minV);
		tessellator.addVertexWithUV(var20, (double)((float)y + 0.1F), (double)((float)z + 0.8F), minU,  maxV);
		tessellator.addVertexWithUV(var20, (double)((float)y + 0.1F), (double)((float)z + 0.2F), maxU, maxV);
		tessellator.addVertexWithUV(var28, (double)((float)y + 1.2F), (double)((float)z + 0.2F), maxU, minV);
		
		minU = (double)( (float)textureU           / 256.0F);
		maxU = (double)(((float)textureU + 15.99F) / 256.0F);
		minV = (double)( (float)textureV           / 256.0F);
		maxV = (double)(((float)textureV + 15.99F) / 256.0F);
		tessellator.addVertexWithUV((double)((float)x + 0.2F), (double)((float)y + 1.2F), var32, minU,  minV);
		tessellator.addVertexWithUV((double)((float)x + 0.2F), (double)((float)y + 0.1F), var24, minU,  maxV);
		tessellator.addVertexWithUV((double)((float)x + 0.8F), (double)((float)y + 0.1F), var24, maxU, maxV);
		tessellator.addVertexWithUV((double)((float)x + 0.8F), (double)((float)y + 1.2F), var32, maxU, minV);
		
		tessellator.addVertexWithUV((double)((float)x + 0.8F), (double)((float)y + 1.2F), var30, minU,  minV);
		tessellator.addVertexWithUV((double)((float)x + 0.8F), (double)((float)y + 0.1F), var22, minU,  maxV);
		tessellator.addVertexWithUV((double)((float)x + 0.2F), (double)((float)y + 0.1F), var22, maxU, maxV);
		tessellator.addVertexWithUV((double)((float)x + 0.2F), (double)((float)y + 1.2F), var30, maxU, minV);
		
		
		renderer.overrideBlockTexture = 20;
		block.setBlockBounds(13.0F/16.0F, 0.0F/16.0F, 0.0F, 14.0F/16.0F, 1.0F/16.0F, 16.0F/16.0F);
		renderer.func_83018_a(block);
		renderer.renderStandardBlock(block, x, y, z);
		
		block.setBlockBounds(13.0F/16.0F, 3.0F/16.0F, 0.0F, 14.0F/16.0F, 4.0F/16.0F, 16.0F/16.0F);
		renderer.func_83018_a(block);
		renderer.renderStandardBlock(block, x, y, z);
		
		block.setBlockBounds(13.0F/16.0F, 6.0F/16.0F, 0.0F, 14.0F/16.0F, 7.0F/16.0F, 16.0F/16.0F);
		renderer.func_83018_a(block);
		renderer.renderStandardBlock(block, x, y, z);
		
		block.setBlockBounds(1.0F/16.0F, 0.0F/16.0F, 0.0F, 2.0F/16.0F, 1.0F/16.0F, 16.0F/16.0F);
		renderer.func_83018_a(block);
		renderer.renderStandardBlock(block, x, y, z);
		
		block.setBlockBounds(1.0F/16.0F, 3.0F/16.0F, 0.0F, 2.0F/16.0F, 4.0F/16.0F, 16.0F/16.0F);
		renderer.func_83018_a(block);
		renderer.renderStandardBlock(block, x, y, z);
		
		block.setBlockBounds(1.0F/16.0F, 6.0F/16.0F, 0.0F, 2.0F/16.0F, 7.0F/16.0F, 16.0F/16.0F);
		renderer.func_83018_a(block);
		renderer.renderStandardBlock(block, x, y, z);
		
		block.setBlockBounds( 0.0F/16.0F, 1.0F/16.0F, 13.0F/16.0F, 16.0F/16.0F, 2.0F/16.0F, 14.0F/16.0F);
		renderer.func_83018_a(block);
		renderer.renderStandardBlock(block, x, y, z);
		
		block.setBlockBounds( 0.0F/16.0F, 4.0F/16.0F, 13.0F/16.0F, 16.0F/16.0F, 5.0F/16.0F, 14.0F/16.0F);
		renderer.func_83018_a(block);
		renderer.renderStandardBlock(block, x, y, z);
		
		block.setBlockBounds( 0.0F/16.0F, 7.0F/16.0F, 13.0F/16.0F, 16.0F/16.0F, 8.0F/16.0F, 14.0F/16.0F);
		renderer.func_83018_a(block);
		renderer.renderStandardBlock(block, x, y, z);
		
		block.setBlockBounds( 0.0F/16.0F, 1.0F/16.0F, 1.0F/16.0F, 16.0F/16.0F, 2.0F/16.0F, 2.0F/16.0F);
		renderer.func_83018_a(block);
		renderer.renderStandardBlock(block, x, y, z);
		
		block.setBlockBounds( 0.0F/16.0F, 4.0F/16.0F, 1.0F/16.0F, 16.0F/16.0F, 5.0F/16.0F, 2.0F/16.0F);
		renderer.func_83018_a(block);
		renderer.renderStandardBlock(block, x, y, z);

		block.setBlockBounds( 0.0F/16.0F, 7.0F/16.0F, 1.0F/16.0F, 16.0F/16.0F, 8.0F/16.0F, 2.0F/16.0F);
		renderer.func_83018_a(block);
		renderer.renderStandardBlock(block, x, y, z);
		
		renderer.overrideBlockTexture = -1;
		block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		renderer.func_83018_a(block);
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