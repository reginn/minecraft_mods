package rgn.mods.toolrack.client;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;

import net.minecraftforge.common.ForgeDirection;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

import rgn.mods.toolrack.TileEntityToolrack;
import rgn.mods.toolrack.Toolrack;

public class ToolrackRenderingHandler implements ISimpleBlockRenderingHandler
{
	private Icon[] icons = new Icon[]
		{
			Block.planks.getIcon(0, 0),
			Block.planks.getIcon(0, 1),
			Block.planks.getIcon(0, 2),
			Block.planks.getIcon(0, 3),
			Block.blockIron.getBlockTextureFromSide(0),
			Block.blockIron.getBlockTextureFromSide(0),
			Block.blockIron.getBlockTextureFromSide(0)
		};

	private void refreshIcon()
	{
		icons[0] = Block.planks.getIcon(0, 0);
		icons[1] = Block.planks.getIcon(0, 1);
		icons[2] = Block.planks.getIcon(0, 2);
		icons[3] = Block.planks.getIcon(0, 3);
		icons[4] = Block.blockIron.getBlockTextureFromSide(0);
		icons[5] = Block.blockIron.getBlockTextureFromSide(0);
		icons[6] = Block.blockIron.getBlockTextureFromSide(0);
	}

	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
	{
		this.refreshIcon();
		if (modelID == this.getRenderId())
		{
			this.renderInvCuboid(renderer, block, 1.0F/16.0F, 4.0F/16.0F, 7.0F/16.0F, 15.0F/16.0F, 6.0F/16.0F, 8.0F/16.0F, icons[metadata]);
			this.renderInvCuboid(renderer, block, 1.0F/16.0F, 10.0F/16.0F, 7.0F/16.0F, 15.0F/16.0F, 12.0F/16.0F, 8.0F/16.0F, icons[metadata]);
		}
	}

	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelID, RenderBlocks renderer)
	{
		this.refreshIcon();
		if (modelID == this.getRenderId())
		{
			renderer.setOverrideBlockTexture(icons[world.getBlockMetadata(x, y, z) & 7]);

			TileEntityToolrack tileEntityToolrack = (TileEntityToolrack)world.getBlockTileEntity(x, y, z);
			byte facing = tileEntityToolrack.getFacing();

			ForgeDirection dir = ForgeDirection.getOrientation(facing);

			if (dir == ForgeDirection.EAST)
			{
				this.renderToolrackEast(world, x, y, z, block, renderer);
			}
			else if (dir == ForgeDirection.WEST)
			{
				this.renderToolrackWest(world, x, y, z, block, renderer);
			}
			else if (dir == ForgeDirection.SOUTH)
			{
				this.renderToolrackSouth(world, x, y, z, block, renderer);
			}
			else if (dir == ForgeDirection.NORTH)
			{
				this.renderToolrackNorth(world, x, y, z, block, renderer);
			}

			renderer.clearOverrideBlockTexture();
			return true;
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
		return Toolrack.RENDER_TYPE_TOOLRACK;
	}

	private void renderToolrackEast(IBlockAccess world, int x, int y, int z, Block block, RenderBlocks renderer)
	{
		int rgb = block.colorMultiplier(world, x, y, z);
		float r = (float)(rgb >> 16 & 255) / 255.0F;
		float g = (float)(rgb >>  8 & 255) / 255.0F;
		float b = (float)(rgb       & 255) / 255.0F;

		block.setBlockBounds(0.0F/16.0F, 4.0F/16.0F, 0.0F/16.0F, 1.0F/16.0F, 6.0F/16.0F, 16.0F/16.0F);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlockWithColorMultiplier(block, x, y, z, r, g, b);

		block.setBlockBounds(0.0F/16.0F, 10.0F/16.0F, 0.0F/16.0F, 1.0F/16.0F, 12.0F/16.0F, 16.0F/16.0F);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlockWithColorMultiplier(block, x, y, z, r, g, b);

		block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		renderer.setRenderBoundsFromBlock(block);
	}

	private void renderToolrackWest(IBlockAccess world, int x, int y, int z, Block block, RenderBlocks renderer)
	{
		int rgb = block.colorMultiplier(world, x, y, z);
		float r = (float)(rgb >> 16 & 255) / 255.0F;
		float g = (float)(rgb >>  8 & 255) / 255.0F;
		float b = (float)(rgb       & 255) / 255.0F;

		block.setBlockBounds(15.0F/16.0F, 4.0F/16.0F, 0.0F/16.0F, 16.0F/16.0F, 6.0F/16.0F, 16.0F/16.0F);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlockWithColorMultiplier(block, x, y, z, r, g, b);

		block.setBlockBounds(15.0F/16.0F, 10.0F/16.0F, 0.0F/16.0F, 16.0F/16.0F, 12.0F/16.0F, 16.0F/16.0F);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlockWithColorMultiplier(block, x, y, z, r, g, b);

		block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		renderer.setRenderBoundsFromBlock(block);
	}

	private void renderToolrackSouth(IBlockAccess world, int x, int y, int z, Block block, RenderBlocks renderer)
	{
		int rgb = block.colorMultiplier(world, x, y, z);
		float r = (float)(rgb >> 16 & 255) / 255.0F;
		float g = (float)(rgb >>  8 & 255) / 255.0F;
		float b = (float)(rgb       & 255) / 255.0F;

		block.setBlockBounds(0.0F/16.0F, 4.0F/16.0F, 0.0F/16.0F, 16.0F/16.0F, 6.0F/16.0F, 1.0F/16.0F);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlockWithColorMultiplier(block, x, y, z, r, g, b);

		block.setBlockBounds(0.0F/16.0F, 10.0F/16.0F, 0.0F/16.0F, 16.0F/16.0F, 12.0F/16.0F, 1.0F/16.0F);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlockWithColorMultiplier(block, x, y, z, r, g, b);

		block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		renderer.setRenderBoundsFromBlock(block);
	}

	private void renderToolrackNorth(IBlockAccess world, int x, int y, int z, Block block, RenderBlocks renderer)
	{
		int rgb = block.colorMultiplier(world, x, y, z);
		float r = (float)(rgb >> 16 & 255) / 255.0F;
		float g = (float)(rgb >>  8 & 255) / 255.0F;
		float b = (float)(rgb       & 255) / 255.0F;

		block.setBlockBounds(0.0F/16.0F, 4.0F/16.0F, 15.0F/16.0F, 16.0F/16.0F, 6.0F/16.0F, 16.0F/16.0F);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlockWithColorMultiplier(block, x, y, z, r, g, b);

		block.setBlockBounds(0.0F/16.0F, 10.0F/16.0F, 15.0F/16.0F, 16.0F/16.0F, 12.0F/16.0F, 16.0F/16.0F);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlockWithColorMultiplier(block, x, y, z, r, g, b);

		block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		renderer.setRenderBoundsFromBlock(block);
	}

	private void renderInvCuboid(RenderBlocks renderer, Block block, float minX, float minY, float minZ, float maxX, float maxY, float maxZ, Icon icons)
	{
		Tessellator tessellator = Tessellator.instance;
		block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		renderer.setRenderBoundsFromBlock(block);
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		block.setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
		renderer.setRenderBoundsFromBlock(block);
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, -1F, 0.0F);
		renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, icons);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 1.0F, 0.0F);
		renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, icons);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, -1F);
		renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, icons);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, 1.0F);
		renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, icons);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(-1F, 0.0F, 0.0F);
		renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, icons);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(1.0F, 0.0F, 0.0F);
		renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, icons);
		tessellator.draw();
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		renderer.setRenderBoundsFromBlock(block);
	}
}