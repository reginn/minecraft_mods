package rgn.mods.mabicraft.client.renderer.entity;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.entity.RenderBiped;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import rgn.mods.mabicraft.client.model.ModelMabiSkeleton;

@SideOnly(Side.CLIENT)
public class RenderMabiSkeleton extends RenderBiped
{
	public RenderMabiSkeleton()
	{
		super(new ModelMabiSkeleton(), 0.5F);
	}
	/*
	protected void func_82438_a(EntitySkeleton par1EntitySkeleton, float par2)
	{
		if (par1EntitySkeleton.getSkeletonType() == 1)
		{
			GL11.glScalef(1.2F, 1.2F, 1.2F);
		}
	}
	*/

	protected void func_82422_c()
	{
		GL11.glTranslatef(0.09375F, 0.1875F, 0.0F);
	}
	/*
	protected void preRenderCallback(EntityLiving par1EntityLiving, float par2)
	{
		this.func_82438_a((EntitySkeleton)par1EntityLiving, par2);
	}
	*/
}
