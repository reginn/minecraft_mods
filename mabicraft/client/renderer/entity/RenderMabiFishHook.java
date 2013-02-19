package rgn.mods.mabicraft.client.renderer.entity;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import rgn.mods.mabicraft.entity.projectile.EntityMabiFishHook;

@SideOnly(Side.CLIENT)
public class RenderMabiFishHook extends Render
{
	public void doRenderFishHook(EntityMabiFishHook entityMabiFishHook, double x, double y, double z, float par8, float par9)
	{
		GL11.glPushMatrix();
		GL11.glTranslatef((float)x, (float)y, (float)z);
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glScalef(0.5F, 0.5F, 0.5F);
		byte var10 = 1;
		byte var11 = 2;
		this.loadTexture("/particles.png");
		Tessellator tessellator = Tessellator.instance;
		float var13 = (float)(var10 * 8 + 0) / 128.0F;
		float var14 = (float)(var10 * 8 + 8) / 128.0F;
		float var15 = (float)(var11 * 8 + 0) / 128.0F;
		float var16 = (float)(var11 * 8 + 8) / 128.0F;
		float var17 = 1.0F;
		float var18 = 0.5F;
		float var19 = 0.5F;
		GL11.glRotatef(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 1.0F, 0.0F);
		tessellator.addVertexWithUV((double)(0.0F - var18), (double)(0.0F - var19), 0.0D, (double)var13, (double)var16);
		tessellator.addVertexWithUV((double)(var17 - var18), (double)(0.0F - var19), 0.0D, (double)var14, (double)var16);
		tessellator.addVertexWithUV((double)(var17 - var18), (double)(1.0F - var19), 0.0D, (double)var14, (double)var15);
		tessellator.addVertexWithUV((double)(0.0F - var18), (double)(1.0F - var19), 0.0D, (double)var13, (double)var15);
		tessellator.draw();
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();

		if (entityMabiFishHook.angler != null)
		{
			float var20 = entityMabiFishHook.angler.getSwingProgress(par9);
			float var21 = MathHelper.sin(MathHelper.sqrt_float(var20) * (float)Math.PI);
			Vec3 var22 = entityMabiFishHook.worldObj.getWorldVec3Pool().getVecFromPool(-0.5D, 0.03D, 0.8D);
			var22.rotateAroundX(-(entityMabiFishHook.angler.prevRotationPitch + (entityMabiFishHook.angler.rotationPitch - entityMabiFishHook.angler.prevRotationPitch) * par9) * (float)Math.PI / 180.0F);
			var22.rotateAroundY(-(entityMabiFishHook.angler.prevRotationYaw + (entityMabiFishHook.angler.rotationYaw - entityMabiFishHook.angler.prevRotationYaw) * par9) * (float)Math.PI / 180.0F);
			var22.rotateAroundY(var21 * 0.5F);
			var22.rotateAroundX(-var21 * 0.7F);
			double var23 = entityMabiFishHook.angler.prevPosX + (entityMabiFishHook.angler.posX - entityMabiFishHook.angler.prevPosX) * (double)par9 + var22.xCoord;
			double var25 = entityMabiFishHook.angler.prevPosY + (entityMabiFishHook.angler.posY - entityMabiFishHook.angler.prevPosY) * (double)par9 + var22.yCoord;
			double var27 = entityMabiFishHook.angler.prevPosZ + (entityMabiFishHook.angler.posZ - entityMabiFishHook.angler.prevPosZ) * (double)par9 + var22.zCoord;
			double var29 = entityMabiFishHook.angler != Minecraft.getMinecraft().thePlayer ? (double)entityMabiFishHook.angler.getEyeHeight() : 0.0D;

			if (this.renderManager.options.thirdPersonView > 0 || entityMabiFishHook.angler != Minecraft.getMinecraft().thePlayer)
			{
				float var31 = (entityMabiFishHook.angler.prevRenderYawOffset + (entityMabiFishHook.angler.renderYawOffset - entityMabiFishHook.angler.prevRenderYawOffset) * par9) * (float)Math.PI / 180.0F;
				double var32 = (double)MathHelper.sin(var31);
				double var34 = (double)MathHelper.cos(var31);
				var23 = entityMabiFishHook.angler.prevPosX + (entityMabiFishHook.angler.posX - entityMabiFishHook.angler.prevPosX) * (double)par9 - var34 * 0.35D - var32 * 0.85D;
				var25 = entityMabiFishHook.angler.prevPosY + var29 + (entityMabiFishHook.angler.posY - entityMabiFishHook.angler.prevPosY) * (double)par9 - 0.45D;
				var27 = entityMabiFishHook.angler.prevPosZ + (entityMabiFishHook.angler.posZ - entityMabiFishHook.angler.prevPosZ) * (double)par9 - var32 * 0.35D + var34 * 0.85D;
			}

			double var46 = entityMabiFishHook.prevPosX + (entityMabiFishHook.posX - entityMabiFishHook.prevPosX) * (double)par9;
			double var33 = entityMabiFishHook.prevPosY + (entityMabiFishHook.posY - entityMabiFishHook.prevPosY) * (double)par9 + 0.25D;
			double var35 = entityMabiFishHook.prevPosZ + (entityMabiFishHook.posZ - entityMabiFishHook.prevPosZ) * (double)par9;
			double var37 = (double)((float)(var23 - var46));
			double var39 = (double)((float)(var25 - var33));
			double var41 = (double)((float)(var27 - var35));
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glDisable(GL11.GL_LIGHTING);
			tessellator.startDrawing(3);
			tessellator.setColorOpaque_I(0);
			byte var43 = 16;

			for (int var44 = 0; var44 <= var43; ++var44)
			{
				float var45 = (float)var44 / (float)var43;
				tessellator.addVertex(x + var37 * (double)var45, y + var39 * (double)(var45 * var45 + var45) * 0.5D + 0.25D, z + var41 * (double)var45);
			}

			tessellator.draw();
			GL11.glEnable(GL11.GL_LIGHTING);
			GL11.glEnable(GL11.GL_TEXTURE_2D);
		}
	}

	public void doRender(Entity entity, double x, double y, double z, float par8, float par9)
	{
		this.doRenderFishHook((EntityMabiFishHook)entity, x, y, z, par8, par9);
	}
}
