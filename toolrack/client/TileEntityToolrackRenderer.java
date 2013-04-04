package rgn.mods.toolrack.client;

import java.lang.reflect.Method;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;

import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.ForgeDirection;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import rgn.mods.toolrack.TileEntityToolrack;

@SideOnly(Side.CLIENT)
public class TileEntityToolrackRenderer extends TileEntitySpecialRenderer
{
	private Minecraft mc = FMLClientHandler.instance().getClient();
	private RenderBlocks renderBlocks;
	private static Method method;

	static
	{
		try
		{
			String methodName = ObfuscationReflectionHelper.remapFieldNames("ItemRenderer.class", "renderItemIn2D")[0];
			method =
				(net.minecraft.client.renderer.ItemRenderer.class).getDeclaredMethod(
					methodName,
					new Class[]
					{
						net.minecraft.client.renderer.Tessellator.class,
						Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE, Integer.TYPE, Integer.TYPE, Float.TYPE
					});
			method.setAccessible(true);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public TileEntityToolrackRenderer()
	{
		renderBlocks = new RenderBlocks();
	}

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float f)
	{
		this.render((TileEntityToolrack)tileEntity, x, y, z, f);
	}

	private void render(TileEntityToolrack tileEntity, double x, double y, double z, float f)
	{

		ItemStack itemstack = tileEntity.getStackInSlot(0);
		if (itemstack == null)
		{
			return;
		}

		GL11.glPushMatrix();
		GL11.glTranslatef((float)x + 0.5F, (float)y + 0.8F, (float)z + 0.5F);
		int facing = tileEntity.getFacing();

		ForgeDirection dir = ForgeDirection.getOrientation(facing);
		float rot = 0.0F;

		if (dir == ForgeDirection.EAST) { rot = 90.0F; }
		if (dir == ForgeDirection.WEST) { rot = 270.0F;}
		if (dir == ForgeDirection.SOUTH){ rot = 0.0F;  }
		if (dir == ForgeDirection.NORTH){ rot = 180.0F;}

		GL11.glRotatef(rot, 0.0F, 1.0F, 0.0F);

		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glTranslatef(0.0F, -0.4F, this.isFancyGraphics() ? -0.3F : -0.4F);

		IItemRenderer itemRenderer = MinecraftForgeClient.getItemRenderer(itemstack, IItemRenderer.ItemRenderType.INVENTORY);
		Icon icon;

		if (itemRenderer != null)
		{
			bindTextureByName("/terrain.png");
			itemRenderer.renderItem(IItemRenderer.ItemRenderType.INVENTORY, itemstack, new Object[]{renderBlocks});
		}
		else
		{
			GL11.glPushMatrix();
			GL11.glScalef(2.0F/3.0F, 2.0F/3.0F, 2.0F/3.0F);


			icon = itemstack.getItem().getIconFromDamage(itemstack.getItemDamage());
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.mc.renderEngine.getTexture("/gui/items.png"));

			if (itemstack.getItem().requiresMultipleRenderPasses())
			{
				for (int i = 0; i <= 1; i++)
				{
					icon = itemstack.getItem().getIconFromDamageForRenderPass(itemstack.getItemDamage(), i);
					this.renderColor(itemstack, i);
					this.renderIcon(icon);
				}
			}
			else
			{
				this.renderColor(itemstack, 0);
				this.renderIcon(icon);
			}

			GL11.glScalef(3.0F/2.0F, 3.0F/2.0F, 3.0F/2.0F);
			GL11.glPopMatrix();
		}

		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();
	}


	private boolean isBlock(ItemStack itemstack)
	{
		return itemstack.itemID < Block.blocksList.length
			&& Block.blocksList[itemstack.itemID] != null
			&& Block.blocksList[itemstack.itemID].blockMaterial != Material.air
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
		float lightingRatio = this.isFancyGraphics() ? 1.5F : 0.6F;
		float red   = (float)(colorFromDamage >> 16 & 0xff) / 255.0F;
		float green = (float)(colorFromDamage >>  8 & 0xff) / 255.0F;
		float blue  = (float)(colorFromDamage       & 0xff) / 255.0F;
		GL11.glColor4f(red * lightingRatio, green * lightingRatio, blue * lightingRatio, 1.0F);
	}

	private boolean isFancyGraphics()
	{
		return this.mc.gameSettings.fancyGraphics;
	}

	private void renderIcon(Icon icon)
	{
		Tessellator tessellator = Tessellator.instance;
		float f3 = icon.getMinU();
		float f4 = icon.getMaxU();
		float f5 = icon.getMinV();
		float f6 = icon.getMaxV();
		float f7 = 1.0F;
		float f8 = 0.5F;
		float f9 = 0.25F;

		if (this.isFancyGraphics())
		{
			GL11.glPushMatrix();
			GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
			GL11.glTranslatef(-0.5F, -0.3F, 0.2F);
			try
			{
				method.invoke(
					RenderManager.instance.itemRenderer,
						new Object[]
						{
							tessellator, Float.valueOf(f4), Float.valueOf(f5), Float.valueOf(f3), Float.valueOf(f6),
							Integer.valueOf(icon.getSheetWidth()), Integer.valueOf(icon.getSheetHeight()), Float.valueOf(0.0625F)
						});
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			GL11.glPopMatrix();
		}
		else
		{
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


}
