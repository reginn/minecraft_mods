package rgn.mods.elventools.client;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBoat;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import rgn.mods.elventools.entity.EntityEbonyBoat;

public class RenderEbonyBoat extends Render
{
	protected ModelBase modelBoat;
	private static final ResourceLocation EBONY_BOAT_TEXTURE = new ResourceLocation("rgn.elventools", "/textures/model/EbonyBoat.png");

	public RenderEbonyBoat()
	{
		shadowSize = 0.5F;
		modelBoat = new ModelBoat();
	}

	private void renderBoat(EntityEbonyBoat entity, double d, double d1, double d2, float f, float f1)
	{
		GL11.glPushMatrix();
		GL11.glTranslatef((float)d, (float)d1, (float)d2);
		GL11.glRotatef(180F - f, 0.0F, 1.0F, 0.0F);
		float f2 = (float)entity.getTimeSinceHit() - f1;
		float f3 = (float)entity.getDamageTaken() - f1;
		if (f3 < 0.0F) {
			f3 = 0.0F;
		}
		if (f2 > 0.0F) {
			GL11.glRotatef(((MathHelper.sin(f2) * f2 * f3) / 10F) * (float)entity.getForwardDirection(), 1.0F, 0.0F, 0.0F);
		}
		float f4 = 0.75F;
		GL11.glScalef(f4, f4, f4);
		GL11.glScalef(1.0F / f4, 1.0F / f4, 1.0F / f4);
		this.func_110777_b(entity);
		GL11.glScalef(-1F, -1F, 1.0F);
		modelBoat.render(entity, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		GL11.glPopMatrix();
	}

	protected ResourceLocation func_110781_a(EntityEbonyBoat entityEbonyBoat)
	{
		return EBONY_BOAT_TEXTURE;
	}

	@Override
	protected ResourceLocation func_110775_a(Entity entity)
	{
		return this.func_110781_a((EntityEbonyBoat)entity);
	}

	public void doRender(Entity entity, double d, double d1, double d2, float f, float f1)
	{
		this.renderBoat((EntityEbonyBoat)entity, d, d1, d2, f, f1);
	}
}
