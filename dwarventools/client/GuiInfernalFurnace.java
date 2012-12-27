package rgn.mods.dwarventools.client;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import rgn.mods.dwarventools.inventory.ContainerInfernalFurnace;
import rgn.mods.dwarventools.tileentity.TileEntityInfernalFurnace;

public class GuiInfernalFurnace extends GuiContainer
{
	private TileEntityInfernalFurnace furnaceInventory;

	public GuiInfernalFurnace(InventoryPlayer par1InventoryPlayer, TileEntityInfernalFurnace par2TileEntityInfernalFurnace)
	{
		super(new ContainerInfernalFurnace(par1InventoryPlayer, par2TileEntityInfernalFurnace));
		this.furnaceInventory = par2TileEntityInfernalFurnace;
	}

	protected void drawGuiContainerForegroundLayer()
	{
		this.fontRenderer.drawString("Infernal Furnace", 45, 6, 4210752);
		this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
	}

	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
	{
		int var4 = this.mc.renderEngine.getTexture("/gui/furnace.png");
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(var4);
		int var5 = (this.width - this.xSize) / 2;
		int var6 = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(var5, var6, 0, 0, this.xSize, this.ySize);
		int var7;

		if (this.furnaceInventory.isBurning())
		{
			var7 = this.furnaceInventory.getBurnTimeRemainingScaled(12);
			this.drawTexturedModalRect(var5 + 56, var6 + 36 + 12 - var7, 176, 12 - var7, 14, var7 + 2);
		}
		var7 = this.furnaceInventory.getCookProgressScaled(24);
		this.drawTexturedModalRect(var5 + 79, var6 + 34, 176, 14, var7 + 1, 16);
	}
}
