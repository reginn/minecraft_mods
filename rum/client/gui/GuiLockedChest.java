package rgn.mods.rum.client.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.IInventory;

import rgn.mods.rum.inventory.ContainerLockedChest;
import rgn.mods.rum.tileentity.TileEntityLockedChest;

public class GuiLockedChest extends GuiContainer
{
	private IInventory playerInventory;
	private TileEntityLockedChest tileEntity;

	public GuiLockedChest(IInventory playerInventory, TileEntityLockedChest tileEntityLockedChest)
	{
		super(new ContainerLockedChest(playerInventory, tileEntityLockedChest));
		this.playerInventory = playerInventory;
		this.tileEntity = tileEntityLockedChest;
		this.allowUserInput = false;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		fontRenderer.drawString("Locked Chest", 60, 6, 0x404040);
		fontRenderer.drawString("Inventory", 8, (ySize - 96) + 2, 0x404040);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture("/gui/trap.png");
		int l = width - xSize >> 1;
		int i1 = height - ySize >> 1;
		drawTexturedModalRect(l, i1, 0, 0, xSize, ySize);
	}
}
