package rgn.mods.decorations.client;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;

import net.minecraft.src.*;

import rgn.mods.decorations.ContainerOzen;
import rgn.mods.decorations.TileEntityOzen;

public class GuiOzen extends GuiContainer
{
	private IInventory inventory;
	private TileEntityOzen ozen;

	public GuiOzen(IInventory iinventory, TileEntityOzen tileEntityOzen)
	{
		super(new ContainerOzen(iinventory, tileEntityOzen));
		inventory = iinventory;
		ozen = tileEntityOzen;
		allowUserInput = false;
	}
	
	protected void drawGuiContainerForegroundLayer()
	{
		fontRenderer.drawString(ozen.getInvName(), 76, 6, 0x404040);
		fontRenderer.drawString("Inventory", 8, (ySize - 96) + 2, 0x404040);
	}
	
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
	{
		int k = mc.renderEngine.getTexture("/rgn/sprites/decorations/gui/ozen.png");
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(k);
		int l = width - xSize >> 1;
		int i1 = height - ySize >> 1;
		drawTexturedModalRect(l, i1, 0, 0, xSize, ySize);
	}
}
