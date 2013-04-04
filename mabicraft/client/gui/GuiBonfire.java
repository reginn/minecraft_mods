package rgn.mods.mabicraft.client.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import rgn.mods.mabicraft.inventory.ContainerBonfire;

@SideOnly(Side.CLIENT)
public class GuiBonfire extends GuiContainer
{
	private World            world;

	private GuiButton        buttonBurn;
	private GuiButton        buttonExtract;
	private ContainerBonfire containerBonfire;

	public GuiBonfire(EntityPlayer player, World world, int x, int y, int z)
	{
		super(new ContainerBonfire(player, world, x, y, z));
		this.containerBonfire  = (ContainerBonfire)inventorySlots;
		this.world             = world;
	}

	@Override
	public void initGui()
	{
		super.initGui();
		int i = width  - xSize >> 1;
		int j = height - ySize >> 1;

		this.buttonList.add(new GuiButton(0, i + 66, j + 16, 44, 20 , "Extract"));
		this.buttonList.add(new GuiButton(1, i + 66, j + 51, 44, 20 , "Burn"));
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		fontRenderer.drawString("Bonfire", 70, 6, 0x404040);
		fontRenderer.drawString("Inventory", 8, (ySize - 96) + 2, 0x404040);

		int probability = this.containerBonfire.getProbability();
		fontRenderer.drawString("Probability : " + String.valueOf(probability) + "%", 50, 40, 0x404040);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture("/mods/rgn/mabicraft/textures/gui/GUI_Bonfire.png");
		int l = width - xSize >> 1;
		int i1 = height - ySize >> 1;
		drawTexturedModalRect(l, i1, 0, 0, xSize, ySize);

		mc.renderEngine.bindTexture("/mods/rgn/mabicraft/textures/gui/SlotBackGroundManaHerb.png");
		drawTexturedModalRect(l + 26, i1 + 35, 0, 0, 16, 16);
		mc.renderEngine.bindTexture("/mods/rgn/mabicraft/textures/gui/SlotBackGroundBlessedPotion.png");
		drawTexturedModalRect(l + 26, i1 + 53, 0, 0, 16, 16);
	}

	@Override
	protected void actionPerformed(GuiButton par1GuiButton)
	{
		if (!par1GuiButton.enabled)
		{
			return;
		}

		containerBonfire.onButtonPushed(par1GuiButton.id);
	}
}