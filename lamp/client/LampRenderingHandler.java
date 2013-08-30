package rgn.mods.lamp.client;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import rgn.mods.lamp.Lamp;

@SideOnly(Side.CLIENT)
public class LampRenderingHandler implements ISimpleBlockRenderingHandler
{
	private Icon frameIcon;
	private Icon torchIcon;
	private Icon glassIcon;
	private Icon steelIcon;
	private Icon goldIcon;
	private Icon diamondIcon;

	private void refreshIcon()
	{
		this.frameIcon   = Block.cauldron.func_94375_b("inner");
		this.torchIcon   = Block.torchWood.getBlockTextureFromSide(0);
		this.glassIcon   = Block.glass.getBlockTextureFromSide(0);
		this.steelIcon   = Block.blockIron.getBlockTextureFromSide(0);
		this.goldIcon    = Block.blockGold.getBlockTextureFromSide(0);
		this.diamondIcon = Block.blockDiamond.getBlockTextureFromSide(0);
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
	{
		this.refreshIcon();
		if (modelID == this.getRenderId())
		{
			if (metadata == 0)
			{
				// torch
				renderInvCuboid(renderer, block,  7.0F/16.0F,  1.0F/16.0F,  7.0F/16.0F,  9.0F/16.0F,  9.0F/16.0F,  9.0F/16.0F,  this.torchIcon);
				// glass
				renderInvCuboid(renderer, block,  5.0F/16.0F,  3.0F/16.0F,  5.0F/16.0F, 11.0F/16.0F, 12.0F/16.0F, 11.0F/16.0F,  this.glassIcon);
				//bottom
				renderInvCuboid(renderer, block,  4.0F/16.0F,        0.0F,  4.0F/16.0F, 12.0F/16.0F,  3.0F/16.0F, 12.0F/16.0F,  this.frameIcon);
				//column
				renderInvCuboid(renderer, block,  4.0F/16.0F,  1.0F/16.0F,  4.0F/16.0F,  5.0F/16.0F, 12.0F/16.0F,  5.0F/16.0F,  this.frameIcon);
				renderInvCuboid(renderer, block,  4.0F/16.0F,  1.0F/16.0F, 11.0F/16.0F,  5.0F/16.0F, 12.0F/16.0F, 12.0F/16.0F,  this.frameIcon);
				renderInvCuboid(renderer, block, 11.0F/16.0F,  1.0F/16.0F,  4.0F/16.0F, 12.0F/16.0F, 12.0F/16.0F,  5.0F/16.0F,  this.frameIcon);
				renderInvCuboid(renderer, block, 11.0F/16.0F,  1.0F/16.0F, 11.0F/16.0F, 12.0F/16.0F, 12.0F/16.0F, 12.0F/16.0F,  this.frameIcon);
				// top
				renderInvCuboid(renderer, block,  4.0F/16.0F, 12.0F/16.0F,  4.0F/16.0F, 12.0F/16.0F, 13.0F/16.0F, 12.0F/16.0F,  this.frameIcon);
				renderInvCuboid(renderer, block,  6.0F/16.0F, 13.0F/16.0F,  6.0F/16.0F, 10.0F/16.0F, 14.0F/16.0F, 10.0F/16.0F,  this.frameIcon);
				renderInvCuboid(renderer, block,  7.0F/16.0F, 14.0F/16.0F,  7.0F/16.0F,  9.0F/16.0F, 16.0F/16.0F,  9.0F/16.0F,  this.frameIcon);
			}
			else if (metadata == 1 || metadata == 2 || metadata == 3)
			{
				// cover
				Icon icon = metadata == 1 ? this.steelIcon : metadata == 2 ? this.goldIcon : this.diamondIcon;
				renderInvCuboid(renderer, block,  5.0F/16.0F,  3.0F/16.0F,  5.0F/16.0F, 11.0F/16.0F, 12.0F/16.0F, 11.0F/16.0F,  icon);
				//bottom
				renderInvCuboid(renderer, block,  4.0F/16.0F,        0.0F,  4.0F/16.0F, 12.0F/16.0F,  3.0F/16.0F, 12.0F/16.0F,  this.frameIcon);
				//column
				renderInvCuboid(renderer, block,  4.0F/16.0F,  1.0F/16.0F,  4.0F/16.0F,  5.0F/16.0F, 12.0F/16.0F,  5.0F/16.0F,  this.frameIcon);
				renderInvCuboid(renderer, block,  4.0F/16.0F,  1.0F/16.0F, 11.0F/16.0F,  5.0F/16.0F, 12.0F/16.0F, 12.0F/16.0F,  this.frameIcon);
				renderInvCuboid(renderer, block, 11.0F/16.0F,  1.0F/16.0F,  4.0F/16.0F, 12.0F/16.0F, 12.0F/16.0F,  5.0F/16.0F,  this.frameIcon);
				renderInvCuboid(renderer, block, 11.0F/16.0F,  1.0F/16.0F, 11.0F/16.0F, 12.0F/16.0F, 12.0F/16.0F, 12.0F/16.0F,  this.frameIcon);
				// top
				renderInvCuboid(renderer, block,  4.0F/16.0F, 12.0F/16.0F,  4.0F/16.0F, 12.0F/16.0F, 13.0F/16.0F, 12.0F/16.0F,  this.frameIcon);
				renderInvCuboid(renderer, block,  6.0F/16.0F, 13.0F/16.0F,  6.0F/16.0F, 10.0F/16.0F, 14.0F/16.0F, 10.0F/16.0F,  this.frameIcon);
				renderInvCuboid(renderer, block,  7.0F/16.0F, 14.0F/16.0F,  7.0F/16.0F,  9.0F/16.0F, 16.0F/16.0F,  9.0F/16.0F,  this.frameIcon);
			}
		}
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelID, RenderBlocks renderer)
	{
		this.refreshIcon();
		if (modelID == this.getRenderId())
		{
			int meta = world.getBlockMetadata(x, y, z);
			if (meta == 0)
			{
				// torch
				renderer.setOverrideBlockTexture(this.torchIcon);
				renderer.renderBlockTorch(block, x, y, z);

				// glass
				renderer.setOverrideBlockTexture(this.glassIcon);
				block.setBlockBounds(5.0F/16.0F, 3.0F/16.0F, 5.0F/16.0F, 11.0F/16.0F, 12.0F/16.0F, 11.0F/16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);

				// bottom
				renderer.setOverrideBlockTexture(this.frameIcon);
				block.setBlockBounds(4.0F/16.0F, -0.1F/16.0F, 4.0F/16.0F, 12.0F/16.0F, 3.0F/16.0F, 12.0F/16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);

				// column
				block.setBlockBounds(4.0F/16.0F, 1.0F/16.0F, 4.0F/16.0F, 5.0F/16.0F, 12.0F/16.0F, 5.0F/16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);

				block.setBlockBounds(4.0F/16.0F, 1.0F/16.0F, 11.0F/16.0F, 5.0F/16.0F, 12.0F/16.0F, 12.0F/16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);

				block.setBlockBounds(11.0F/16.0F, 1.0F/16.0F, 4.0F/16.0F, 12.0F/16.0F, 12.0F/16.0F, 5.0F/16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);

				block.setBlockBounds(11.0F/16.0F, 1.0F/16.0F, 11.0F/16.0F, 12.0F/16.0F, 12.0F/16.0F, 12.0F/16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);

				// top
				block.setBlockBounds(4.0F/16.0F, 12.0F/16.0F, 4.0F/16.0F, 12.0F/16.0F, 13.0F/16.0F, 12.0F/16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);

				block.setBlockBounds(6.0F/16.0F, 13.0F/16.0F, 6.0F/16.0F, 10.0F/16.0F, 14.0F/16.0F, 10.0F/16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);

				block.setBlockBounds(7.0F/16.0F, 14.0F/16.0F, 7.0F/16.0F, 9.0F/16.0F, 16.0F/16.0F, 9.0F/16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);

				renderer.clearOverrideBlockTexture();
				block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
				renderer.setRenderBoundsFromBlock(block);

				return false;
			}
			else if (meta == 1 || meta == 2 || meta == 3)
			{
				// cover
				renderer.setOverrideBlockTexture(meta == 1 ? this.steelIcon : meta == 2 ? this.goldIcon : this.diamondIcon);
				block.setBlockBounds(5.0F/16.0F, 1.0F/16.0F, 5.0F/16.0F, 11.0F/16.0F, 12.0F/16.0F, 11.0F/16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);

				// bottom
				renderer.overrideBlockTexture = this.frameIcon;
				block.setBlockBounds( 4.0F/16.0F, 0.0F,  4.0F/16.0F,  5.0F/16.0F, 1.0F/16.0F, 12.0F/16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);

				block.setBlockBounds( 4.0F/16.0F, 0.0F,  4.0F/16.0F, 12.0F/16.0F, 1.0F/16.0F,  5.0F/16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);

				block.setBlockBounds(11.0F/16.0F, 0.0F,  4.0F/16.0F, 12.0F/16.0F, 1.0F/16.0F, 12.0F/16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);

				block.setBlockBounds( 4.0F/16.0F, 0.0F, 11.0F/16.0F, 12.0F/16.0F, 1.0F/16.0F, 12.0F/16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);

				// column
				block.setBlockBounds(4.0F/16.0F, 1.0F/16.0F, 4.0F/16.0F, 5.0F/16.0F, 12.0F/16.0F, 5.0F/16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);

				block.setBlockBounds(4.0F/16.0F, 1.0F/16.0F, 11.0F/16.0F, 5.0F/16.0F, 12.0F/16.0F, 12.0F/16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);

				block.setBlockBounds(11.0F/16.0F, 1.0F/16.0F, 4.0F/16.0F, 12.0F/16.0F, 12.0F/16.0F, 5.0F/16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);

				block.setBlockBounds(11.0F/16.0F, 1.0F/16.0F, 11.0F/16.0F, 12.0F/16.0F, 12.0F/16.0F, 12.0F/16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);

				// top
				block.setBlockBounds(4.0F/16.0F, 12.0F/16.0F, 4.0F/16.0F, 12.0F/16.0F, 13.0F/16.0F, 12.0F/16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);

				block.setBlockBounds(6.0F/16.0F, 13.0F/16.0F, 6.0F/16.0F, 10.0F/16.0F, 14.0F/16.0F, 10.0F/16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);

				block.setBlockBounds(7.0F/16.0F, 14.0F/16.0F, 7.0F/16.0F, 9.0F/16.0F, 16.0F/16.0F, 9.0F/16.0F);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);

				renderer.clearOverrideBlockTexture();
				block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
				renderer.setRenderBoundsFromBlock(block);

				return true;
			}
		}
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
		return Lamp.lampRenderType;
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