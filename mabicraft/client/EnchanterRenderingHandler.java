package rgn.mods.mabicraft.client;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import static net.minecraftforge.common.ForgeDirection.*;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

import net.minecraft.src.*;
import net.minecraftforge.client.*;

import rgn.mods.mabicraft.*;

@SideOnly(Side.CLIENT)
public class EnchanterRenderingHandler implements ISimpleBlockRenderingHandler
{
	private final int enchantmentTableTopTextureIndex  = 166;
	private final int enchantmentTableSideTextureIndex = 166 + 16;
	
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
	{
		renderInvCuboid(renderer, block, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F/16.0F, 1.0F, 166);
	}
	
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelID, RenderBlocks renderer)
	{
		renderer.overrideBlockTexture = enchantmentTableTopTextureIndex;
		block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F/16.0F, 1.0F);
		renderer.renderStandardBlock(block, x, y, z);
		
		renderer.overrideBlockTexture = -1;
		block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F/16.0F, 1.0F);
		return true;
	}
	
	public boolean shouldRender3DInInventory()
	{
		return true;
	}
	
	public int getRenderId()
	{
		return MabiCraft.enchanterRenderType;
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