package rgn.mods.mabicraft.client.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import rgn.mods.mabicraft.inventory.ContainerQuestBoard;

public class GuiQuestBoard extends GuiContainer
{
	EntityPlayer player;
	World world;

	public GuiQuestBoard(EntityPlayer player, World world, int x, int y, int z)
	{
		super(new ContainerQuestBoard(player, world, x, y, z));
		this.player = player;
		this.world  = world;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		fontRenderer.drawString("QuestBoard", 68, 6, 0x404040);
		fontRenderer.drawString("Inventory", 8, (ySize - 96) + 2, 0x404040);
		fontRenderer.drawString("Collect", 10, 40, 0x404040);
		fontRenderer.drawString("Evil Scroll", 10, 50, 0x404040);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture("/mods/rgn/mabicraft/textures/gui/GUI_QuestBoard.png");
		int l = width - xSize >> 1;
		int i1 = height - ySize >> 1;
		drawTexturedModalRect(l, i1, 0, 0, xSize, ySize);
	}


}
