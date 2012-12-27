package rgn.mods.elventools.client;

import java.lang.reflect.Method;

import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import rgn.mods.elventools.item.ItemEnhancedBow;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class EnhancedBowRenderer implements IItemRenderer
{
	public boolean handleRenderType(ItemStack item, ItemRenderType type)
	{
		if (item.getItem() instanceof ItemEnhancedBow && type == ItemRenderType.EQUIPPED)
		{
			return true;
		}
		return false;
	}

    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
	{
		return false;
	}

    public void renderItem(ItemRenderType type, ItemStack item, Object... data)
	{
		if (data[0] != null && data[1] != null &&
			data[0] instanceof RenderBlocks && data[1] instanceof EntityLiving)
		{
			EntityLiving player = (EntityLiving)data[1];
			if (item.getItem() instanceof ItemEnhancedBow && type == ItemRenderType.EQUIPPED)
			{
				GL11.glTranslatef(0.0F, -0.15F, 0.0F);
				GL11.glScalef(1.125F, 1.125F, 1.125F);

				Tessellator tessellator = Tessellator.instance;
				int iconIndex = player.getItemIcon(item, 0);
				float var7  = ((float)(iconIndex % 16 * 16) +   0.0F) / 256.0F;
				float var8  = ((float)(iconIndex % 16 * 16) + 15.99F) / 256.0F;
				float var9  = ((float)(iconIndex / 16 * 16) +   0.0F) / 256.0F;
				float var10 = ((float)(iconIndex / 16 * 16) + 15.99F) / 256.0F;

				try
				{
					Method method;
					if (!ObfuscationReflectionHelper.obfuscation)
					{
					 	method = (net.minecraft.client.renderer.ItemRenderer.class).getDeclaredMethod("renderItemIn2D",
							new Class[]
							{
								net.minecraft.client.renderer.Tessellator.class, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE
							});
					}
					else
					{
						method = (net.minecraft.client.renderer.ItemRenderer.class).getDeclaredMethod("a",
							new Class[]
							{
								net.minecraft.client.renderer.Tessellator.class, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE
							});
					}
					method.setAccessible(true);

					method.invoke(RenderManager.instance.itemRenderer,
						new Object[]
						{
							tessellator, Float.valueOf(var8), Float.valueOf(var9), Float.valueOf(var7), Float.valueOf(var10)
						});
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}
	}
}