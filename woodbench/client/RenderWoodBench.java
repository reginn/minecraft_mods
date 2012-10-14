package rgn.mods.woodbench.client;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import java.util.*;

import net.minecraft.src.*;
import net.minecraft.client.Minecraft;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

import rgn.mods.woodbench.BlockWoodBench;

@SideOnly(Side.CLIENT)
public class RenderWoodBench extends RenderBlocks
{
	public RenderWoodBench(IBlockAccess par1IBlockAccess)
	{
		super(par1IBlockAccess);
	}

	public boolean renderBlockByRenderTypeCustom(BlockWoodBench par1Block, int par2, int par3, int par4)
	{
		par1Block.setBlockBoundsBasedOnState(blockAccess, par2, par3, par4);
		return renderObliqueGBlock(par1Block, par2, par3, par4);
	}

	public void renderBlockAsItemCustom(Block renderBlock)
	{
		Tessellator tessellator = Tessellator.instance;

		renderBlock.setBlockBoundsForItemRender();
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);

		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, -1F, 0.0F);
		renderBottomFace(renderBlock, 0.0D, 0.0D, 0.0D, renderBlock.getBlockTextureFromSide(0));
		tessellator.draw();

		tessellator.startDrawingQuads();
		tessellator.setNormal(-1F, 0.0F, 0.0F);
		renderObliqueNSFace(renderBlock, 0.0D, 0.0D, 0.0D, renderBlock.getBlockTextureFromSide(0), false);
		tessellator.draw();

		tessellator.startDrawingQuads();
		tessellator.setNormal(1.0F, 0.0F, 0.0F);
		renderObliqueNSFace(renderBlock, 0.0D, 0.0D, 0.0D, renderBlock.getBlockTextureFromSide(0), true);
		tessellator.draw();

		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 1.0F, 0.0F);
		renderObliqueGTopFace(renderBlock, 0.0D, 0.0D, 0.0D, renderBlock.getBlockTextureFromSide(0), 2);
		tessellator.draw();

		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 1.0F, 0.0F);
		renderObliqueGTopFace(renderBlock, 0.0D, 0.0D, 0.0D, renderBlock.getBlockTextureFromSide(0), 3);
		tessellator.draw();

		GL11.glTranslatef(0.5F, 0.5F, 0.5F);

	}

	public boolean renderObliqueGBlock(Block par1Block, int par2, int par3, int par4)
	{
		int i = par1Block.colorMultiplier(blockAccess, par2, par3, par4);
		float f = (float)(i >> 16 & 0xff) / 255F;
		float f1 = (float)(i >> 8 & 0xff) / 255F;
		float f2 = (float)(i & 0xff) / 255F;

		if (EntityRenderer.anaglyphEnable)
		{
			float f3 = (f * 30F + f1 * 59F + f2 * 11F) / 100F;
			float f4 = (f * 30F + f1 * 70F) / 100F;
			float f5 = (f * 30F + f2 * 70F) / 100F;
			f = f3;
			f1 = f4;
			f2 = f5;
		}
		return renderObliqueGBlockWithColorMultiplier(par1Block, par2, par3, par4, f, f1, f2);
	}
	
	public boolean renderObliqueGBlockWithColorMultiplier(Block par1Block, int par2, int par3, int par4, float par5, float par6, float par7)
	{
		enableAO = false;
		Tessellator tessellator = Tessellator.instance;
		boolean flag = false;
		float f = 0.5F;
		float f1 = 1.0F;
		float f2 = 0.8F;
		float f3 = 0.6F;
		float f4 = f1 * par5;
		float f5 = f1 * par6;
		float f6 = f1 * par7;
		float f7 = f;
		float f8 = f2;
		float f9 = f3;
		float f10 = f;
		float f11 = f2;
		float f12 = f3;
		float f13 = f;
		float f14 = f2;
		float f15 = f3;

		if (par1Block != Block.grass)
		{
			f7 = f * par5;
			f8 = f2 * par5;
			f9 = f3 * par5;
			f10 = f * par6;
			f11 = f2 * par6;
			f12 = f3 * par6;
			f13 = f * par7;
			f14 = f2 * par7;
			f15 = f3 * par7;
		}

		int i = par1Block.getMixedBrightnessForBlock(blockAccess, par2, par3, par4);
		par1Block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
		int meta = blockAccess.getBlockMetadata(par2, par3, par4);
		int side = meta & 1;

		if(par1Block.shouldSideBeRendered(blockAccess, par2, par3 - 1, par4, 0)){
			tessellator.setBrightness(par1Block.minY <= 0.0D ? par1Block.getMixedBrightnessForBlock(blockAccess, par2, par3 - 1, par4) : i);
			tessellator.setColorOpaque_F(f7, f10, f13);
			renderBottomFace(par1Block, par2, par3, par4, par1Block.getBlockTexture(blockAccess, par2, par3, par4, 0));
		}

		if(side == 0){
			tessellator.setBrightness(i);
			tessellator.setColorOpaque_F(f4, f5, f6);
			renderObliqueGTopFace(par1Block, par2, par3, par4, par1Block.getBlockTexture(blockAccess, par2, par3, par4, 0), 0);

			tessellator.setBrightness(i);
			tessellator.setColorOpaque_F(f4, f5, f6);
			renderObliqueGTopFace(par1Block, par2, par3, par4, par1Block.getBlockTexture(blockAccess, par2, par3, par4, 0), 1);

			tessellator.setBrightness(par1Block.minZ <= 0.0D ? par1Block.getMixedBrightnessForBlock(blockAccess, par2, par3, par4 - 1) : i);
			tessellator.setColorOpaque_F(f8, f11, f14);
			renderObliqueEWFace(par1Block, par2, par3, par4, par1Block.getBlockTexture(blockAccess, par2, par3, par4, 0), false);

			tessellator.setBrightness(par1Block.maxZ >= 1.0D ? par1Block.getMixedBrightnessForBlock(blockAccess, par2, par3, par4 + 1) : i);
			tessellator.setColorOpaque_F(f8, f11, f14);
			renderObliqueEWFace(par1Block, par2, par3, par4, par1Block.getBlockTexture(blockAccess, par2, par3, par4, 0), true);
		}else{
			tessellator.setBrightness(i);
			tessellator.setColorOpaque_F(f4, f5, f6);
			renderObliqueGTopFace(par1Block, par2, par3, par4, par1Block.getBlockTexture(blockAccess, par2, par3, par4, 0), 2);

			tessellator.setBrightness(i);
			tessellator.setColorOpaque_F(f4, f5, f6);
			renderObliqueGTopFace(par1Block, par2, par3, par4, par1Block.getBlockTexture(blockAccess, par2, par3, par4, 0), 3);

			tessellator.setBrightness(par1Block.minX <= 0.0D ? par1Block.getMixedBrightnessForBlock(blockAccess, par2 - 1, par3, par4) : i);
			tessellator.setColorOpaque_F(f9, f12, f15);
			renderObliqueNSFace(par1Block, par2, par3, par4, par1Block.getBlockTexture(blockAccess, par2, par3, par4, 0), false);

			tessellator.setBrightness(par1Block.maxX >= 1.0D ? par1Block.getMixedBrightnessForBlock(blockAccess, par2 + 1, par3, par4) : i);
			tessellator.setColorOpaque_F(f9, f12, f15);
			renderObliqueNSFace(par1Block, par2, par3, par4, par1Block.getBlockTexture(blockAccess, par2, par3, par4, 0), true);
		}
		return true;
	}

	public void renderObliqueGTopFace(Block par1Block, double par2, double par4, double par6, int par8, int side)
	{
		Tessellator tessellator = Tessellator.instance;

		if (overrideBlockTexture >= 0)
		{
			par8 = overrideBlockTexture;
		}

		int i = (par8 & 0xf) << 4;
		int j = par8 & 0xf0;
		double d = ((double)i + par1Block.minZ * 16D) / 256D;
		double d1 = (((double)i + par1Block.maxZ * 16D) - 0.01D) / 256D;
		double d2 = ((double)(j + 16) - par1Block.maxY * 16D) / 256D;
		double d3 = ((double)(j + 16) - par1Block.minY * 16D - 0.01D) / 256D;

		if (par1Block.minX < 0.0D || par1Block.maxX > 1.0D)
		{
			d = ((float)i + 0.0F);
			d1 = ((float)i + 15.99F);
		}

		if (par1Block.minZ < 0.0D || par1Block.maxZ > 1.0D)
		{
			d2 = ((float)j + 0.0F);
			d3 = ((float)j + 15.99F);
		}

		double d4 = d1;
		double d5 = d;
		double d6 = d2;
		double d7 = d3;

		if (side == 0)
		{
			d = ((double)i + par1Block.minZ * 16D) / 256D;
			d2 = ((double)(j + 16) - par1Block.maxX * 16D) / 256D;
			d1 = ((double)i + par1Block.maxZ * 16D) / 256D;
			d3 = ((double)(j + 16) - par1Block.minX * 16D) / 256D;
			d6 = d2;
			d7 = d3;
			d4 = d;
			d5 = d1;
			d2 = d3;
			d3 = d6;
		}
		else if (side == 1)
		{
			d = ((double)(i + 16) - par1Block.maxZ * 16D) / 256D;
			d2 = ((double)j + par1Block.minX * 16D) / 256D;
			d1 = ((double)(i + 16) - par1Block.minZ * 16D) / 256D;
			d3 = ((double)j + par1Block.maxX * 16D) / 256D;
			d4 = d1;
			d5 = d;
			d = d1;
			d1 = d5;
			d6 = d3;
			d7 = d2;
		}
		else if (side == 2)
		{
			d = ((double)(i + 16) - par1Block.minX * 16D) / 256D;
			d1 = ((double)(i + 16) - par1Block.maxX * 16D - 0.01D) / 256D;
			d2 = ((double)(j + 16) - par1Block.minZ * 16D) / 256D;
			d3 = ((double)(j + 16) - par1Block.maxZ * 16D - 0.01D) / 256D;
			d4 = d1;
			d5 = d;
			d6 = d2;
			d7 = d3;
		}

		double d8 = par2 + par1Block.minX;
		double d9 = par2 + par1Block.maxX;
		double d10 = par4 + par1Block.minY;
		double d11 = par6 + par1Block.minZ;
		double d12 = par6 + par1Block.maxZ;

		double addside = (par1Block.maxY - par1Block.minY);
		double side1 = addside;
		double side2 = addside;
		double side3 = addside;
		double side4 = addside;

		if(side == 0){
			side1 = 0;
			side2 = 0;
			d8 += (par1Block.maxX - par1Block.minX)/2F;
		}else if(side == 1){
			side3 = 0;
			side4 = 0;
			d9 -= (par1Block.maxX - par1Block.minX)/2F;
		}else if(side == 2){
			side1 = 0;
			side4 = 0;
			d11 += (par1Block.maxZ - par1Block.minZ)/2F;
		}else if(side == 3){
			side2 = 0;
			side3 = 0;
			d12 -= (par1Block.maxZ - par1Block.minZ)/2F;
		}

		if (enableAO)
		{
			tessellator.setColorOpaque_F(colorRedTopLeft, colorGreenTopLeft, colorBlueTopLeft);
			tessellator.setBrightness(brightnessTopLeft);
			tessellator.addVertexWithUV(d9, d10+side1, d12, d1, d3);
			tessellator.setColorOpaque_F(colorRedBottomLeft, colorGreenBottomLeft, colorBlueBottomLeft);
			tessellator.setBrightness(brightnessBottomLeft);
			tessellator.addVertexWithUV(d9, d10+side2, d11, d4, d6);
			tessellator.setColorOpaque_F(colorRedBottomRight, colorGreenBottomRight, colorBlueBottomRight);
			tessellator.setBrightness(brightnessBottomRight);
			tessellator.addVertexWithUV(d8, d10+side3, d11, d, d2);
			tessellator.setColorOpaque_F(colorRedTopRight, colorGreenTopRight, colorBlueTopRight);
			tessellator.setBrightness(brightnessTopRight);
			tessellator.addVertexWithUV(d8, d10+side4, d12, d5, d7);
		}
		else
		{
			tessellator.addVertexWithUV(d9, d10+side1, d12, d1, d3);
			tessellator.addVertexWithUV(d9, d10+side2, d11, d4, d6);
			tessellator.addVertexWithUV(d8, d10+side3, d11, d, d2);
			tessellator.addVertexWithUV(d8, d10+side4, d12, d5, d7);
		}
	}

	public void renderObliqueEWFace(Block par1Block, double par2, double par4, double par6, int par8, boolean side)
	{
		Tessellator tessellator = Tessellator.instance;

		if (overrideBlockTexture >= 0)
		{
			par8 = overrideBlockTexture;
		}

		int i = (par8 & 0xf) << 4;
		int j = par8 & 0xf0;
		double d = ((double)i + par1Block.minX * 16D) / 256D;
		double d1 = (((double)i + par1Block.maxX * 16D) - 0.01D) / 256D;
		double d2 = ((double)(j + 16) - par1Block.maxY * 16D) / 256D;
		double d3 = ((double)(j + 16) - par1Block.minY * 16D - 0.01D) / 256D;
		double x0 = ((double)i + (par1Block.maxX - par1Block.minX) * 8D - 0.01D) / 256D;

		if (par1Block.minX < 0.0D || par1Block.maxX > 1.0D)
		{
			d = ((float)i + 0.0F);
			d1 = ((float)i + 15.99F);
		}

		if (par1Block.minZ < 0.0D || par1Block.maxZ > 1.0D)
		{
			d2 = ((float)j + 0.0F);
			d3 = ((float)j + 15.99F);
		}

		double d4 = d1;
		double d5 = d;
		double d6 = d2;
		double d7 = d3;
		double minX = par2 + par1Block.minX;
		double maxX = par2 + par1Block.maxX;
		double minY = par4 + par1Block.minY;
		double maxY = par4 + par1Block.maxY;
		double minZ = par6 + par1Block.minZ;
		double maxZ = par6 + par1Block.maxZ;

		double posX = minX+((maxX-minX)/2F);
		double posY = minY + (par1Block.maxY - par1Block.minY);

		if(side){
			tessellator.addVertexWithUV(minX, minY, maxZ, d, d3);
			tessellator.addVertexWithUV(posX, posY, maxZ, x0, d2);
			tessellator.addVertexWithUV(maxX, minY, maxZ, d1, d3);
			tessellator.addVertexWithUV(minX, minY, maxZ, d, d3);
		}else{
			tessellator.addVertexWithUV(minX, minY, minZ, d, d3);
			tessellator.addVertexWithUV(posX, posY, minZ, x0, d2);
			tessellator.addVertexWithUV(maxX, minY, minZ, d1, d3);
			tessellator.addVertexWithUV(minX, minY, minZ, d, d3);
		}
	}

	public void renderObliqueNSFace(Block par1Block, double par2, double par4, double par6, int par8, boolean side)
	{
		Tessellator tessellator = Tessellator.instance;

		if (overrideBlockTexture >= 0)
		{
			par8 = overrideBlockTexture;
		}

		int i = (par8 & 0xf) << 4;
		int j = par8 & 0xf0;
		double d = ((double)i + par1Block.minZ * 16D) / 256D;
		double d1 = (((double)i + par1Block.maxZ * 16D) - 0.01D) / 256D;
		double d2 = ((double)(j + 16) - par1Block.maxY * 16D) / 256D;
		double d3 = ((double)(j + 16) - par1Block.minY * 16D - 0.01D) / 256D;
		double x0 = ((double)i + (par1Block.maxZ - par1Block.minZ) * 8D - 0.01D) / 256D;

		if (par1Block.minX < 0.0D || par1Block.maxX > 1.0D)
		{
			d = ((float)i + 0.0F);
			d1 = ((float)i + 15.99F);
		}

		if (par1Block.minZ < 0.0D || par1Block.maxZ > 1.0D)
		{
			d2 = ((float)j + 0.0F);
			d3 = ((float)j + 15.99F);
		}

		double d4 = d1;
		double d5 = d;
		double d6 = d2;
		double d7 = d3;
		double minX = par2 + par1Block.minX;
		double maxX = par2 + par1Block.maxX;
		double minY = par4 + par1Block.minY;
		double maxY = par4 + par1Block.maxY;
		double minZ = par6 + par1Block.minZ;
		double maxZ = par6 + par1Block.maxZ;

		double posZ = minZ+((maxZ-minZ)/2F);
		double posY = minY + (par1Block.maxY - par1Block.minY);

		if(side){
			tessellator.addVertexWithUV(maxX, minY, minZ, d, d3);
			tessellator.addVertexWithUV(maxX, posY, posZ, x0, d2);
			tessellator.addVertexWithUV(maxX, minY, maxZ, d1, d3);
			tessellator.addVertexWithUV(maxX, minY, minZ, d, d3);
		}else{
			tessellator.addVertexWithUV(minX, minY, minZ, d, d3);
			tessellator.addVertexWithUV(minX, posY, posZ, x0, d2);
			tessellator.addVertexWithUV(minX, minY, maxZ, d1, d3);
			tessellator.addVertexWithUV(minX, minY, minZ, d, d3);
		}
	}
}
