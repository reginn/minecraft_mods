package rgn.mods.ozen.client;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import net.minecraft.src.*;

import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

import rgn.mods.ozen.TileEntityOzen;

@SideOnly(Side.CLIENT)
public class TileEntityOzenRenderer extends TileEntitySpecialRenderer
{
	private RenderBlocks blockrender;
	
	public TileEntityOzenRenderer()
	{
		blockrender = new RenderBlocks();
	}
	
	public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f)
	{
		this.render((TileEntityOzen)tileentity, x, y, z, f);
	}
	
	private void render(TileEntityOzen tileEntityOzen, double x, double y, double z, float f)
	{
		GL11.glPushMatrix();
		GL11.glTranslatef((float)x + 0.5F, (float)y + 0.8F, (float)z + 0.5F);
		
		int facing = tileEntityOzen.getFacing();
		
		float rot = 0.0F;
		if (facing == 2)
		{
			rot = 180.0F;
		}
		if (facing == 5)
		{
			rot = 90.0F;
		}
		if (facing == 3)
		{
			rot = 0.0F;
		}
		if (facing == 4)
		{
			rot = 270.0F;
		}
		
		GL11.glRotatef(rot, 0.0F, 1.0F, 0.0F);
		
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		
		int type = tileEntityOzen.getBlockMetadata() >>> 3;
		float bias = type == 0 ? -0.66F : -0.4F;
		for (int j = 0; j < tileEntityOzen.getSizeInventory(); j++)
		{
			switch (j)
			{
				case 0 :
					GL11.glTranslatef(-0.25F, bias, -0.125F);
					break;
					
				case 1 :
					GL11.glTranslatef(0.25F, 0.0F, 0.0F);
					break;
					
				case 2 :
					GL11.glTranslatef(0.25F, 0.0F, 0.0F);
					break;
					
				case 3 :
					GL11.glTranslatef(-0.5F, 0.0F, 0.25F);
					break;
					
				case 4 :
					GL11.glTranslatef(0.25F, 0.0F, 0.0F);
					break;
					
				case 5 :
					GL11.glTranslatef(0.25F, 0.0F, 0.0F);
					break;
			}
			
			ItemStack itemstack = tileEntityOzen.getStackInSlot(j);
			if (itemstack == null || Item.itemsList[itemstack.itemID] == null)
			{
				continue;
			}
			
			EntityItem customitem = new EntityItem(tileEntityRenderer.worldObj);
			IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer(itemstack, IItemRenderer.ItemRenderType.INVENTORY);
			int iconIndex;
			
			if (customRenderer != null)
			{
				customitem.item = itemstack;
				customRenderer.renderItem(IItemRenderer.ItemRenderType.INVENTORY, itemstack, blockrender, customitem);
			}
			else if (this.isBlock(itemstack))
			{
				GL11.glBindTexture(GL11.GL_TEXTURE_2D, ModLoader.getMinecraftInstance().renderEngine.getTexture(Block.blocksList[itemstack.itemID].getTextureFile()));
				float blockBias = 0.25F;
				int renderType = Block.blocksList[itemstack.itemID].getRenderType();
				if (renderType == 1 || renderType == 19 || renderType == 12 || renderType == 2)
				{
					blockBias = 0.5F;
				}
				GL11.glPushMatrix();
				GL11.glScalef(blockBias, blockBias, blockBias);
				GL11.glTranslatef(0.0F, 0.35F, 0.0F);
				blockrender.renderBlockAsItem(Block.blocksList[itemstack.itemID], itemstack.getItemDamage(), 1.0F);
				GL11.glPopMatrix();
			}
			else
			{
				GL11.glPushMatrix();
				GL11.glScalef(1.0F/3.0F, 1.0F/3.0F, 1.0F/3.0F);
				if (this.isItemBlock(itemstack))
				{
					iconIndex = Block.blocksList[itemstack.itemID].blockIndexInTexture;
					GL11.glBindTexture(GL11.GL_TEXTURE_2D, ModLoader.getMinecraftInstance().renderEngine.getTexture(Block.blocksList[itemstack.itemID].getTextureFile()));
				}
				else
				{
					iconIndex = itemstack.getItem().getIconFromDamage(itemstack.getItemDamage());
					GL11.glBindTexture(GL11.GL_TEXTURE_2D, ModLoader.getMinecraftInstance().renderEngine.getTexture(itemstack.getItem().getTextureFile()));
				}
				
				if (itemstack.getItem().requiresMultipleRenderPasses())
				{
					for (int i = 0; i <= 1; i++)
					{
						iconIndex = itemstack.getItem().getIconFromDamageForRenderPass(itemstack.getItemDamage(), i);
						this.renderColor(itemstack, i);
						this.renderIcon(iconIndex);
						
					}
				}
				else
				{
					this.renderColor(itemstack, 0);
					this.renderIcon(iconIndex);
				}
				GL11.glPopMatrix();
			}
			
			
		}
		
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();
	}
	
	private boolean isBlock(ItemStack itemstack)
	{
		return itemstack.itemID < Block.blocksList.length && Block.blocksList[itemstack.itemID] != null && Block.blocksList[itemstack.itemID].blockMaterial != Material.air
			&& RenderBlocks.renderItemIn3d(Block.blocksList[itemstack.itemID].getRenderType());
	}
	
	private boolean isItemBlock(ItemStack itemstack)
	{
		return (ItemBlock.class).isAssignableFrom(itemstack.getItem().getClass()) 
				&& RenderBlocks.renderItemIn3d(Block.blocksList[((ItemBlock)itemstack.getItem()).getBlockID()].getRenderType());
	}
	
	private void renderColor(ItemStack itemstack, int i)
	{
		int colorFromDamage = itemstack.getItem().getColorFromItemStack(itemstack, i);
		float red   = (float)(colorFromDamage >> 16 & 0xff) / 255.0F;
		float green = (float)(colorFromDamage >>  8 & 0xff) / 255.0F;
		float blue  = (float)(colorFromDamage       & 0xff) / 255.0F;
		GL11.glColor4f(red * 0.8F, green  * 0.8F, blue * 0.8F, 1.0F);
	}
	
	private void renderIcon(int iconIndex) 
	{	
		Tessellator tessellator = Tessellator.instance;
		float f3 = (float)((iconIndex % 16) * 16 +  0) / 256.0F;
		float f4 = (float)((iconIndex % 16) * 16 + 16) / 256.0F;
		float f5 = (float)((iconIndex / 16) * 16 +  0) / 256.0F;
		float f6 = (float)((iconIndex / 16) * 16 + 16) / 256.0F;
		float f7 = 1.0F;
		float f8 = 0.5F;
		float f9 = 0.25F;
		
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 1.0F, 0.0F);
		tessellator.addVertexWithUV(0.0F - f8, 0.0F - f9, 0.0D, f3, f6);
		tessellator.addVertexWithUV(  f7 - f8, 0.0F - f9, 0.0D, f4, f6);
		tessellator.addVertexWithUV(  f7 - f8, 1.0F - f9, 0.0D, f4, f5);
		tessellator.addVertexWithUV(0.0F - f8, 1.0F - f9, 0.0D, f3, f5);
		tessellator.addVertexWithUV(0.0F - f8, 1.0F - f9, 0.0D, f3, f5);
		tessellator.addVertexWithUV(  f7 - f8, 1.0F - f9, 0.0D, f4, f5);
		tessellator.addVertexWithUV(  f7 - f8, 0.0F - f9, 0.0D, f4, f6);
		tessellator.addVertexWithUV(0.0F - f8, 0.0F - f9, 0.0D, f3, f6);
		tessellator.draw();
	}
}