package rgn.mods.mabicraft.client.renderer;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import rgn.mods.mabicraft.config.Config;

@SideOnly(Side.CLIENT)
public class CookwareRenderingHandler implements ISimpleBlockRenderingHandler
{

	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
	{
		int type = metadata & 0x03;
		int dir  = metadata >>> 2;

		RenderHelper.disableStandardItemLighting();

		if (type == 0)
		{
			this.renderCookingTableInInv(block, metadata, modelID, renderer);
		}
		else if (type == 1)
		{
			this.renderCookingTableInInv(block, metadata, modelID, renderer);
		}
		else if (type == 2)
		{

		}

		RenderHelper.enableStandardItemLighting();
	}

	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelID, RenderBlocks renderer)
	{
		if (modelID == this.getRenderId())
		{
			int type = world.getBlockMetadata(x, y, z) & 0x03;
			int dir  = world.getBlockMetadata(x, y, z) >>> 2;

			if (type == 0)
			{
				if (dir % 2 == 0)
				{
					this.renderCookingTableX(world, x, y, z, block, renderer);
				}
				else
				{
					this.renderCookingTableZ(world, x, y, z, block, renderer);
				}
			}
			else if (type == 1)
			{
				this.renderCookingOven(world, x, y, z, block, renderer);
			}
			else if (type == 2)
			{
				//renderCookingPot
			}
		}
		return true;
	}

	public boolean shouldRender3DInInventory()
	{
		return true;
	}

	public int getRenderId()
	{
		return Config.RENDER_TYPE_COOKWARE;
	}

	private void renderInvCuboid(Tessellator tessellator, RenderBlocks renderer, Block block, float minX, float minY, float minZ, float maxX, float maxY, float maxZ, int textureIndex)
	{
		// Tessellator tessellator = Tessellator.instance;
		block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		renderer.setRenderBoundsFromBlock(block);
		// GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		block.setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
		renderer.setRenderBoundsFromBlock(block);
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
		// GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		renderer.setRenderBoundsFromBlock(block);
	}

	private void renderCookingTableX(IBlockAccess world, int x, int y, int z, Block block, RenderBlocks renderer)
	{
		renderBlockAtAngle
			(
				world, block, (double)x + 2.0D/16.0D, (double)y, (double)z + 4.0D/16.0D,
				22, 0.08D, 0.8D, 0.0D, this.degreeToRadian(45), 0.0D
			);

		renderBlockAtAngle
			(
				world, block, (double)x + 14.0D/16.0D, (double)y, (double)z + 4.0D/16.0D,
				22, 0.08D, 0.8D, 0.0D, this.degreeToRadian(-45), 0.0D
			);

		renderBlockAtAngle
			(
				world, block, (double)x + 2.0D/16.0D, (double)y, (double)z + 12.0D/16.0D,
				22, 0.08D, 0.8D, 0.0D, this.degreeToRadian(45), 0.0D
			);

		renderBlockAtAngle
			(
				world, block, (double)x + 14.0D/16.0D, (double)y, (double)z + 12.0D/16.0D,
				22, 0.08D, 0.8D, 0.0D, this.degreeToRadian(-45), 0.0D
			);

		renderer.overrideBlockTexture = 4;
		block.setBlockBounds(0.0F/16.0F, 12.0F/16.0F, 2.0F/16.0F, 16.0F/16.0F, 14.0F/16.0F, 14.0F/16.0F);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlock(block, x, y, z);

		renderer.overrideBlockTexture = -1;
		block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		renderer.setRenderBoundsFromBlock(block);

	}

	private void renderCookingTableZ(IBlockAccess world, int x, int y, int z, Block block, RenderBlocks renderer)
	{
		renderBlockAtAngle
			(
				world, block, (double)x + 4.0D/16.0D, (double)y, (double)z + 2.0D/16.0D,
				22, 0.08D, 0.8D, 0.0D, 0.0D, this.degreeToRadian(45)
			);

		renderBlockAtAngle
			(
				world, block, (double)x + 4.0D/16.0D, (double)y, (double)z + 14.0D/16.0D,
				22, 0.08D, 0.8D, 0.0D, 0.0D, this.degreeToRadian(-45)
			);

		renderBlockAtAngle
			(
				world, block, (double)x + 12.0D/16.0D, (double)y, (double)z + 2.0D/16.0D,
				22, 0.08D, 0.8D, 0.0D, 0.0D, this.degreeToRadian(45)
			);

		renderBlockAtAngle
			(
				world, block, (double)x + 12.0D/16.0D, (double)y, (double)z + 14.0D/16.0D,
				22, 0.08D, 0.8D, 0.0D, 0.0D, this.degreeToRadian(-45)
			);

		renderer.overrideBlockTexture = 4;
		block.setBlockBounds(2.0F/16.0F, 12.0F/16.0F, 0.0F/16.0F, 14.0F/16.0F, 14.0F/16.0F, 16.0F/16.0F);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlock(block, x, y, z);

		renderer.overrideBlockTexture = -1;
		block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		renderer.setRenderBoundsFromBlock(block);
	}

	private void renderBlockAtAngle
		(
			IBlockAccess world, Block block, double x, double y, double z,
			int textureIndex, double thickness, double top, double bottom, double xAngle, double zAngle
		)
    {
		Tessellator tessellator = Tessellator.instance;
		tessellator.setBrightness(block.getMixedBrightnessForBlock(world, (int)x, (int)y, (int)z));
		tessellator.setColorOpaque_F(1.0F, 1.0F, 1.0F);

		this.doRenderBlockAtAngle(tessellator, block, x, y, z, textureIndex, thickness, top, bottom, xAngle, zAngle);
    }

	private void doRenderBlockAtAngle
		(
			Tessellator tessellator, Block block, double x, double y, double z,
			int textureIndex, double thickness, double top, double bottom, double xAngle, double zAngle
		)
	{
		int posU = (textureIndex & 15) << 4;
		int posV =  textureIndex & 240;
		float minU =  (float)posU           / 256.0F;
		float maxU = ((float)posU + 15.99F) / 256.0F;
		float minV =  (float)posV           / 256.0F;
		float maxV = ((float)posV + 15.99F) / 256.0F;
		double var20 = (double)minU + 0.02734375D;
		double var22 = (double)minV + 0.0234375D;
		double var24 = (double)minU + 0.03515625D;
		double var26 = (double)minV + 0.03125D;
		double var28 = (double)minU + 0.02734375D;
		double var30 = (double)minV + 0.05078125D;
		double var32 = (double)minU + 0.03515625D;
		double var34 = (double)minV + 0.05859375D;

		double minX = x - thickness / 2.0D;
		double maxX = x + thickness / 2.0D;
		double minZ = z - thickness / 2.0D;
		double maxZ = z + thickness / 2.0D;
		double bias = thickness / 2.0D;

		tessellator.addVertexWithUV(x + xAngle * (1.0D - top) - bias, y + top, z + zAngle * (1.0D - top) - bias, var20, var22);
		tessellator.addVertexWithUV(x + xAngle * (1.0D - top) - bias, y + top, z + zAngle * (1.0D - top) + bias, var20, var26);
		tessellator.addVertexWithUV(x + xAngle * (1.0D - top) + bias, y + top, z + zAngle * (1.0D - top) + bias, var24, var26);
		tessellator.addVertexWithUV(x + xAngle * (1.0D - top) + bias, y + top, z + zAngle * (1.0D - top) - bias, var24, var22);


		tessellator.addVertexWithUV(x + bias + xAngle, y, z - bias + zAngle, var32, var30);
		tessellator.addVertexWithUV(x + bias + xAngle, y, z + bias + zAngle, var32, var34);
		tessellator.addVertexWithUV(x - bias + xAngle, y, z + bias + zAngle, var28, var34);
		tessellator.addVertexWithUV(x - bias + xAngle, y, z - bias + zAngle, var28, var30);


		tessellator.addVertexWithUV(x - bias,          y + top,    minZ,          (double)minU, (double)minV);
		tessellator.addVertexWithUV(x - bias + xAngle, y + bottom, minZ + zAngle, (double)minU, (double)maxV);
		tessellator.addVertexWithUV(x - bias + xAngle, y + bottom, maxZ + zAngle, (double)maxU, (double)maxV);
		tessellator.addVertexWithUV(x - bias,          y + top,    maxZ,          (double)maxU, (double)minV);

		tessellator.addVertexWithUV(x + bias,          y + top,    maxZ,          (double)minU, (double)minV);
		tessellator.addVertexWithUV(x + bias + xAngle, y + bottom, maxZ + zAngle, (double)minU, (double)maxV);
		tessellator.addVertexWithUV(x + bias + xAngle, y + bottom, minZ + zAngle, (double)maxU, (double)maxV);
		tessellator.addVertexWithUV(x + bias,          y + top,    minZ,          (double)maxU, (double)minV);

		tessellator.addVertexWithUV(x + bias,          y + top,    minZ,          (double)minU, (double)minV);
		tessellator.addVertexWithUV(x + bias + xAngle, y + bottom, minZ + zAngle, (double)minU, (double)maxV);
		tessellator.addVertexWithUV(x + bias + xAngle, y + bottom, maxZ + zAngle, (double)maxU, (double)maxV);
		tessellator.addVertexWithUV(x + bias,          y + top,    maxZ,          (double)maxU, (double)minV);

		tessellator.addVertexWithUV(x - bias,          y + top,    maxZ,          (double)minU, (double)minV);
		tessellator.addVertexWithUV(x - bias + xAngle, y + bottom, maxZ + zAngle, (double)minU, (double)maxV);
		tessellator.addVertexWithUV(x - bias + xAngle, y + bottom, minZ + zAngle, (double)maxU, (double)maxV);
		tessellator.addVertexWithUV(x - bias,          y + top,    minZ,          (double)maxU, (double)minV);

		//------------
		tessellator.addVertexWithUV(minX,          y + top,    z + bias,          (double)minU, (double)minV);
		tessellator.addVertexWithUV(minX + xAngle, y + bottom, z + bias + zAngle, (double)minU, (double)maxV);
		tessellator.addVertexWithUV(maxX + xAngle, y + bottom, z + bias + zAngle, (double)maxU, (double)maxV);
		tessellator.addVertexWithUV(maxX,          y + top,    z + bias,          (double)maxU, (double)minV);

		tessellator.addVertexWithUV(maxX,          y + top,    z - bias,          (double)minU, (double)minV);
		tessellator.addVertexWithUV(maxX + xAngle, y + bottom, z - bias + zAngle, (double)minU, (double)maxV);
		tessellator.addVertexWithUV(minX + xAngle, y + bottom, z - bias + zAngle, (double)maxU, (double)maxV);
		tessellator.addVertexWithUV(minX,          y + top,    z - bias,          (double)maxU, (double)minV);

		tessellator.addVertexWithUV(minX,          y + top,    z - bias,          (double)minU, (double)minV);
		tessellator.addVertexWithUV(minX + xAngle, y + bottom, z - bias + zAngle, (double)minU, (double)maxV);
		tessellator.addVertexWithUV(maxX + xAngle, y + bottom, z - bias + zAngle, (double)maxU, (double)maxV);
		tessellator.addVertexWithUV(maxX,          y + top,    z - bias,          (double)maxU, (double)minV);

		tessellator.addVertexWithUV(maxX,          y + top,    z + bias,          (double)minU, (double)minV);
		tessellator.addVertexWithUV(maxX + xAngle, y + bottom, z + bias + zAngle, (double)minU, (double)maxV);
		tessellator.addVertexWithUV(minX + xAngle, y + bottom, z + bias + zAngle, (double)maxU, (double)maxV);
		tessellator.addVertexWithUV(minX,          y + top,    z + bias,          (double)maxU, (double)minV);
	}


	private void renderCookingTableInInv(Block block, int metadata, int modelID, RenderBlocks renderer)
	{
		Tessellator tessellator = Tessellator.instance;
		tessellator.setBrightness(0);
		tessellator.setColorOpaque_F(1.0F, 1.0F, 1.0F);
		block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		tessellator.startDrawingQuads();

		this.doRenderBlockAtAngle
			(
				tessellator, block, 2.0D/16.0D, 0.0D, 4.0D/16.0D,
				22, 0.08D, 0.8D, 0.0D, this.degreeToRadian(45), 0.0D
			);

		this.doRenderBlockAtAngle
			(
				tessellator, block, 14.0D/16.0D, 0.0D, 4.0D/16.0D,
				22, 0.08D, 0.8D, 0.0D, this.degreeToRadian(-45), 0.0D
			);

		this.doRenderBlockAtAngle
			(
				tessellator, block, 2.0D/16.0D, 0.0D, 12.0D/16.0D,
				22, 0.08D, 0.8D, 0.0D, this.degreeToRadian(45), 0.0D
			);

		this.doRenderBlockAtAngle
			(
				tessellator, block, 14.0D/16.0D, 0.0D, 12.0D/16.0D,
				22, 0.08D, 0.8D, 0.0D, this.degreeToRadian(-45), 0.0D
			);

		tessellator.draw();
		this.renderInvCuboid(tessellator, renderer, block, 0.0F/16.0F, 12.0F/16.0F, 2.0F/16.0F, 16.0F/16.0F, 14.0F/16.0F, 14.0F/16.0F, 4);
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}

	private double degreeToRadian(double degree)
	{
		return degree * Math.PI / 180.0D;
	}

	private void renderCookingOven(IBlockAccess world, int x, int y, int z, Block block, RenderBlocks renderer)
	{
		Tessellator tessellator = Tessellator.instance;
		int fireTextureIndex = Block.fire.getBlockTextureFromSide(0);

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
		tessellator.addVertexWithUV(var26, (double)((float)y + 0.6F), (double)((float)z + 0.8F), maxU, minV);
		tessellator.addVertexWithUV(var18, (double)((float)y + 0.1F), (double)((float)z + 0.8F), maxU, maxV);
		tessellator.addVertexWithUV(var18, (double)((float)y + 0.1F), (double)((float)z + 0.2F), minU, maxV);
		tessellator.addVertexWithUV(var26, (double)((float)y + 0.6F), (double)((float)z + 0.2F), minU, minV);

		tessellator.addVertexWithUV(var28, (double)((float)y + 0.6F), (double)((float)z + 0.2F), maxU, minV);
		tessellator.addVertexWithUV(var20, (double)((float)y + 0.1F), (double)((float)z + 0.2F), maxU, maxV);
		tessellator.addVertexWithUV(var20, (double)((float)y + 0.1F), (double)((float)z + 0.8F), minU, maxV);
		tessellator.addVertexWithUV(var28, (double)((float)y + 0.6F), (double)((float)z + 0.8F), minU, minV);


		minU = (double)( (float)textureU           / 256.0F);
		maxU = (double)(((float)textureU + 15.99F) / 256.0F);
		minV = (double)( (float)(textureV + 16)    / 256.0F);
		maxV = (double)(((float)textureV + 15.99F + 16.0F) / 256.0F);
		tessellator.addVertexWithUV((double)(x + 0.8F), (double)((float)y + 0.6F), var32, maxU, minV);
		tessellator.addVertexWithUV((double)(x + 0.8F), (double)((float)y + 0.1F), var24, maxU, maxV);
		tessellator.addVertexWithUV((double)(x + 0.2F), (double)((float)y + 0.1F), var24, minU, maxV);
		tessellator.addVertexWithUV((double)(x + 0.2F), (double)((float)y + 0.6F), var32, minU, minV);

		tessellator.addVertexWithUV((double)(x + 0.2F), (double)((float)y + 0.6F), var30, maxU, minV);
		tessellator.addVertexWithUV((double)(x + 0.2F), (double)((float)y + 0.1F), var22, maxU, maxV);
		tessellator.addVertexWithUV((double)(x + 0.8F), (double)((float)y + 0.1F), var22, minU, maxV);
		tessellator.addVertexWithUV((double)(x + 0.8F), (double)((float)y + 0.6F), var30, minU, minV);

		var18 = (double)x + 0.5D - 0.5D + 0.2D;
		var20 = (double)x + 0.5D + 0.5D - 0.2D;
		var22 = (double)z + 0.5D - 0.5D + 0.2D;
		var24 = (double)z + 0.5D + 0.5D - 0.2D;
		var26 = (double)x + 0.5D - 0.4D + 0.2D;
		var28 = (double)x + 0.5D + 0.4D - 0.2D;
		var30 = (double)z + 0.5D - 0.4D + 0.2D;
		var32 = (double)z + 0.5D + 0.4D - 0.2D;
		tessellator.addVertexWithUV(var26, (double)((float)y + 0.6F), (double)((float)z + 0.2F), minU, minV);
		tessellator.addVertexWithUV(var18, (double)((float)y + 0.1F), (double)((float)z + 0.2F), minU, maxV);
		tessellator.addVertexWithUV(var18, (double)((float)y + 0.1F), (double)((float)z + 0.8F), maxU, maxV);
		tessellator.addVertexWithUV(var26, (double)((float)y + 0.6F), (double)((float)z + 0.8F), maxU, minV);

		tessellator.addVertexWithUV(var28, (double)((float)y + 0.6F), (double)((float)z + 0.8F), minU, minV);
		tessellator.addVertexWithUV(var20, (double)((float)y + 0.1F), (double)((float)z + 0.8F), minU, maxV);
		tessellator.addVertexWithUV(var20, (double)((float)y + 0.1F), (double)((float)z + 0.2F), maxU, maxV);
		tessellator.addVertexWithUV(var28, (double)((float)y + 0.6F), (double)((float)z + 0.2F), maxU, minV);

		minU = (double)( (float)textureU           / 256.0F);
		maxU = (double)(((float)textureU + 15.99F) / 256.0F);
		minV = (double)( (float)textureV           / 256.0F);
		maxV = (double)(((float)textureV + 15.99F) / 256.0F);
		tessellator.addVertexWithUV((double)((float)x + 0.2F), (double)((float)y + 0.6F), var32, minU, minV);
		tessellator.addVertexWithUV((double)((float)x + 0.2F), (double)((float)y + 0.1F), var24, minU, maxV);
		tessellator.addVertexWithUV((double)((float)x + 0.8F), (double)((float)y + 0.1F), var24, maxU, maxV);
		tessellator.addVertexWithUV((double)((float)x + 0.8F), (double)((float)y + 0.6F), var32, maxU, minV);

		tessellator.addVertexWithUV((double)((float)x + 0.8F), (double)((float)y + 0.6F), var30, minU, minV);
		tessellator.addVertexWithUV((double)((float)x + 0.8F), (double)((float)y + 0.1F), var22, minU, maxV);
		tessellator.addVertexWithUV((double)((float)x + 0.2F), (double)((float)y + 0.1F), var22, maxU, maxV);
		tessellator.addVertexWithUV((double)((float)x + 0.2F), (double)((float)y + 0.6F), var30, maxU, minV);

		renderer.overrideBlockTexture = Block.cobblestone.getBlockTextureFromSide(0);
		block.setBlockBounds(0.0F/16.0F, 0.0F/16.0F, 0.0F/16.0F, 16.0F/16.0F, 2.0F/16.0F, 16.0F/16.0F);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlock(block, x, y, z);

		block.setBlockBounds(0.0F/16.0F, 2.0F/16.0F, 0.0F/16.0F, 2.0F/16.0F, 8.0F/16.0F, 16.0F/16.0F);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlock(block, x, y, z);

		block.setBlockBounds(14.0F/16.0F, 2.0F/16.0F, 0.0F/16.0F, 16.0F/16.0F, 8.0F/16.0F, 16.0F/16.0F);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlock(block, x, y, z);

		block.setBlockBounds(0.0F/16.0F, 2.0F/16.0F, 0.0F/16.0F, 16.0F/16.0F, 8.0F/16.0F, 2.0F/16.0F);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlock(block, x, y, z);

		block.setBlockBounds(0.0F/16.0F, 2.0F/16.0F, 14.0F/16.0F, 2.0F/16.0F, 8.0F/16.0F, 16.0F/16.0F);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlock(block, x, y, z);

		renderer.overrideBlockTexture = -1;
	}
}