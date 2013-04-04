package rgn.mods.mabicraft.client.renderer.entity;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelSpider;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.EntityLiving;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import rgn.mods.mabicraft.entity.monster.EntityMabiSpiderBase;

@SideOnly(Side.CLIENT)
public class RenderMabiSpiderBase extends RenderLiving
{

	public RenderMabiSpiderBase()
	{
		super(new ModelSpider(), 1.0F);
		this.setRenderPassModel(new ModelSpider());
	}

	protected float setSpiderDeathMaxRotation(EntityMabiSpiderBase entityMabiSpiderBase)
	{
		return 180.0F;
	}

	protected int setSpiderEyeBrightness(EntityMabiSpiderBase par1EntitySpider, int par2, float par3)
	{
		if (par2 != 0)
		{
			return -1;
		}
		else
		{
			this.loadTexture("/mob/spider_eyes.png");
			float var4 = 1.0F;
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glDisable(GL11.GL_ALPHA_TEST);
			GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);

			if (par1EntitySpider.getHasActivePotion())
			{
				GL11.glDepthMask(false);
			}
			else
			{
				GL11.glDepthMask(true);
			}

			char var5 = 61680;
			int var6 = var5 % 65536;
			int var7 = var5 / 65536;
			OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)var6 / 1.0F, (float)var7 / 1.0F);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, var4);
			return 1;
		}
	}

	protected void scaleSpider(EntityMabiSpiderBase par1EntitySpider, float par2)
	{
		float var3 = par1EntitySpider.spiderScaleAmount();
		GL11.glScalef(var3, var3, var3);
	}

	protected void preRenderCallback(EntityLiving par1EntityLiving, float par2)
	{
		this.scaleSpider((EntityMabiSpiderBase)par1EntityLiving, par2);
	}

	protected float getDeathMaxRotation(EntityLiving par1EntityLiving)
	{
		return this.setSpiderDeathMaxRotation((EntityMabiSpiderBase)par1EntityLiving);
	}

	protected int shouldRenderPass(EntityLiving par1EntityLiving, int par2, float par3)
	{
		return this.setSpiderEyeBrightness((EntityMabiSpiderBase)par1EntityLiving, par2, par3);
	}

}
