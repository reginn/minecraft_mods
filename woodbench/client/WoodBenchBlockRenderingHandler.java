package rgn.mods.woodbench.client;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import rgn.mods.woodbench.BlockWoodBench;
import rgn.mods.woodbench.WoodBench;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class WoodBenchBlockRenderingHandler implements ISimpleBlockRenderingHandler
{
	@Override
	public void renderInventoryBlock(Block block, int metadata, int renderType, RenderBlocks renderblocks)
	{
		BlockWoodBench CRBlock = (BlockWoodBench)block;
		RenderWoodBench renderBlocks = new RenderWoodBench(renderblocks.blockAccess);

		renderBlocks.useInventoryTint = false;
		renderBlocks.renderBlockAsItemCustom(block);
		renderBlocks.useInventoryTint = true;
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int renderType, RenderBlocks renderblocks)
	{
		Tessellator.instance.draw();

		RenderWoodBench renderBlocks = new RenderWoodBench(world);
		renderBlocks.overrideBlockTexture = renderblocks.overrideBlockTexture;

		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		Tessellator.instance.startDrawingQuads();

		renderBlocks.renderBlockByRenderTypeCustom((BlockWoodBench)block, x, y, z);

		Tessellator.instance.draw();

		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();
		Tessellator.instance.startDrawingQuads();
		return true;
	}

	@Override
	public boolean shouldRender3DInInventory()
	{
		return true;
	}

	@Override
	public int getRenderId()
	{
		return WoodBench.woodBenchRenderType;
	}
}