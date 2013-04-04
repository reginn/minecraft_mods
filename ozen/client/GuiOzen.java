package rgn.mods.ozen.client;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.IInventory;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import rgn.mods.ozen.ContainerOzen;
import rgn.mods.ozen.TileEntityOzen;

@SideOnly(Side.CLIENT)
public class GuiOzen extends GuiContainer
{
	private IInventory inventory;
	private TileEntityOzen ozen;

	public GuiOzen(IInventory iinventory, TileEntityOzen tileEntityOzen)
	{
		super(new ContainerOzen(iinventory, tileEntityOzen));
		this.inventory = iinventory;
		this.ozen = tileEntityOzen;
		this.allowUserInput = false;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		fontRenderer.drawString(ozen.getInvName(), 76, 6, 0x404040);
		fontRenderer.drawString("Inventory", 8, (ySize - 96) + 2, 0x404040);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture("/mods/rgn/ozen/textures/gui/ozen.png");
		int l = width - xSize >> 1;
		int i1 = height - ySize >> 1;
		drawTexturedModalRect(l, i1, 0, 0, xSize, ySize);
	}
}
