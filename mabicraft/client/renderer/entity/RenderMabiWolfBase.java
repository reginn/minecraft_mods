package rgn.mods.mabicraft.client.renderer.entity;

import net.minecraft.client.renderer.entity.RenderLiving;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import rgn.mods.mabicraft.client.model.ModelMabiWolf;

@SideOnly(Side.CLIENT)
public class RenderMabiWolfBase extends RenderLiving
{
	public RenderMabiWolfBase()
	{
		super(new ModelMabiWolf(), 1.0F);
	}

	/*
	protected float getTailRotation(EntityWolf par1EntityWolf, float par2)
	{
		return par1EntityWolf.getTailRotation();
	}

	protected int func_82447_a(EntityWolf par1EntityWolf, int par2, float par3)
	{
		float var4;

		if (par2 == 0 && par1EntityWolf.getWolfShaking())
		{
			var4 = par1EntityWolf.getBrightness(par3) * par1EntityWolf.getShadingWhileShaking(par3);
			this.loadTexture(par1EntityWolf.getTexture());
			GL11.glColor3f(var4, var4, var4);
			return 1;
		}
		else if (par2 == 1 && par1EntityWolf.isTamed())
		{
			this.loadTexture("/mob/wolf_collar.png");
			var4 = 1.0F;
			int var5 = par1EntityWolf.getCollarColor();
			GL11.glColor3f(var4 * EntitySheep.fleeceColorTable[var5][0], var4 * EntitySheep.fleeceColorTable[var5][1], var4 * EntitySheep.fleeceColorTable[var5][2]);
			return 1;
		}
		else
		{
			return -1;
		}
	}
	@Override
	protected int shouldRenderPass(EntityLiving par1EntityLiving, int par2, float par3)
	{
		return this.func_82447_a((EntityWolf)par1EntityLiving, par2, par3);
	}
	@Override
	protected float handleRotationFloat(EntityLiving par1EntityLiving, float par2)
	{
		return this.getTailRotation((EntityWolf)par1EntityLiving, par2);
	}
	*/
}
