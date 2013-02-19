package rgn.mods.mabicraft.client.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import rgn.mods.mabicraft.inventory.ContainerCookware;

@SideOnly(Side.CLIENT)
public class GuiCookware extends GuiContainer
{
	private World             world;
	private ContainerCookware containerCookware;
	private String[]          titles;
	private int               type;
	private int[]             ticks;
	private boolean[]         isInputed;

	public GuiCookware(EntityPlayer player, World world, int x, int y, int z, int type)
	{
		super(new ContainerCookware(player, world, x, y, z, type));
		this.containerCookware = (ContainerCookware)inventorySlots;
		this.world              = world;
		this.titles             = new String[]{"Cooking Table", "Cooking Pot"};
		this.type               = type;
		this.ticks              = new int[]{0, 0, 0};
		this.isInputed          = new boolean[]{false, false, false};
	}

	@Override
	public void initGui()
	{
		super.initGui();
		int i = width  - xSize >> 1;
		int j = height - ySize >> 1;

		controlList.add(new GuiAddButton(0, i + 14, j + 38, 24, 20 , "Add"));
		controlList.add(new GuiAddButton(1, i + 50, j + 38, 24, 20 , "Add"));
		controlList.add(new GuiAddButton(2, i + 86, j + 38, 24, 20 , "Add"));
		controlList.add(new GuiButton(3, i + 122, j + 50, 40, 20 , "Cook"));

	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		fontRenderer.drawString(this.titles[this.type], 52, 6, 0x404040);
		fontRenderer.drawString("Inventory", 8, (ySize - 96) + 2, 0x404040);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
	{
		int k = this.mc.renderEngine.getTexture("/rgn/sprites/mabicraft/gui/GUI_Cook.png");
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(k);
		int l  =  width - xSize >> 1;
		int i1 = height - ySize >> 1;
		this.drawTexturedModalRect(l, i1, 0, 0, xSize, ySize);

		int progress = 0;

		for (int idx = 0; idx < controlList.size() - 1; ++idx)
		{
			GuiButton button = (GuiButton)controlList.get(idx);
			if (button instanceof GuiAddButton)
			{
				progress += this.ticks[idx];
			}
		}
		progress = progress >= 100 ? 100 : progress;
        this.drawTexturedModalRect(l + 11, i1 + 64, 0, 166, progress + 1, 6);
	}

	@Override
	public void updateScreen()
	{
		super.updateScreen();

		for (int idx = 0; idx < this.controlList.size() - 1; ++idx)
		{
			GuiButton button = (GuiButton)controlList.get(idx);
			if (button instanceof GuiAddButton)
			{
				GuiAddButton addButton = (GuiAddButton)button;
				addButton.setItemInSlot(containerCookware.getSlot(idx).getStack() != null);
				this.ticks[idx] = addButton.getPressedTick() >= 100 ? 100 : addButton.getPressedTick();
			}
		}
	}

	@Override
	protected void actionPerformed(GuiButton par1GuiButton)
	{
		super.actionPerformed(par1GuiButton);

		if (!par1GuiButton.enabled)
		{
			return ;
		}

		if (par1GuiButton.enabled && par1GuiButton.id == 3)
		{
			containerCookware.onClicked(par1GuiButton.id, this.ticks);
			this.reset();
		}
	}

	private void reset()
	{
		for (int tick : this.ticks)
		{
			tick = 0;
		}

		for (boolean b : this.isInputed)
		{
			b = false;
		}

		for (int idx = 0; idx < this.controlList.size() - 1; ++idx)
		{
			GuiButton button = (GuiButton)controlList.get(idx);
			if (button instanceof GuiAddButton)
			{
				GuiAddButton addButton = (GuiAddButton)button;
				addButton.setItemInSlot(containerCookware.getSlot(idx).getStack() != null);
				addButton.resetPressedTick();
				addButton.resetIsReleased();
			}
		}
	}
}