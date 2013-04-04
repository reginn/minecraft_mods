package rgn.mods.rum.client.renderer;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import rgn.mods.rum.tileentity.TileEntityLockedChest;

public class TileEntityLockedChestRenderer extends TileEntitySpecialRenderer
{
	private ModelChest model;
	private static TileEntityLockedChestRenderer INSTANCE = new TileEntityLockedChestRenderer();

	public static TileEntityLockedChestRenderer instance()
	{
		return INSTANCE;
	}

	private TileEntityLockedChestRenderer()
	{
		model = new ModelChest();
	}

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float f)
	{
		this.render((TileEntityLockedChest)tileEntity, x, y, z, f, -1);
	}

	private void render(TileEntityLockedChest tileEntity, double x, double y, double z, float partialTick, int dir)
	{
		bindTextureByName("/mods/rgn/rum/textures/model/LockedChest.png");

		GL11.glPushMatrix();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glTranslatef((float)x, (float)y + 1.0F, (float)z + 1.0F);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		int rotate = -90;

		int direction = dir == -1 ? tileEntity.worldObj.getBlockMetadata(tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord) : dir;
		if (direction == 2)
		{
			rotate = 180;
		}
		if (direction == 3)
		{
			rotate = 0;
		}
		if (direction == 4)
		{
			rotate = 90;
		}
		if (direction == 5)
		{
			rotate = -90;
		}
		GL11.glRotatef(rotate, 0.0F, 1.0F, 0.0F);
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		float lidangle = tileEntity.prevLidAngle + (tileEntity.lidAngle - tileEntity.prevLidAngle) * partialTick;
		lidangle = 1.0F - lidangle;
		lidangle = 1.0F - lidangle * lidangle * lidangle;
		model.chestLid.rotateAngleX = -((lidangle * 3.141593F) / 2.0F);
		this.model.renderAll();
		GL11.glPopMatrix();

		bindTextureByName("/terrain.png");
	}

	public void renderTileEntityInInventory(TileEntity tileEntity, double x, double y, double z, float f)
	{
		this.render((TileEntityLockedChest)tileEntity, x, y, z, f, 4);
	}
}
