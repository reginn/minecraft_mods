package rgn.mods.mabicraft.enchant.client;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import rgn.mods.mabicraft.enchant.ContainerEnchanter;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiEnchanter extends GuiContainer
{
	private World              world;
	private GuiButton          buttonEnchant;
	private ContainerEnchanter containerEnchanter;

	public GuiEnchanter(EntityPlayer player, World world, int x, int y, int z)
	{
		super(new ContainerEnchanter(player, world, x, y, z));
		this.containerEnchanter = (ContainerEnchanter)inventorySlots;
		this.world              = world;
	}

	@Override
	public void initGui()
	{
		super.initGui();
		int i = width  - xSize >> 1;
		int j = height - ySize >> 1;

		controlList.add(new GuiButton(0, i + 62, j + 24, 48, 20 , "Enchant"));
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		fontRenderer.drawString("Enchanter", 61, 6, 0x404040);
		fontRenderer.drawString("Inventory", 8, (ySize - 96) + 2, 0x404040);

		int probability = this.containerEnchanter.getProbability();
		fontRenderer.drawString("Probability : " + String.valueOf(probability) + "%", 50, 50, 0x404040);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
	{
		int k = mc.renderEngine.getTexture("/rgn/sprites/mabicraft/gui/GUI_Enchanter.png");
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(k);
		int l = width - xSize >> 1;
		int i1 = height - ySize >> 1;
		drawTexturedModalRect(l, i1, 0, 0, xSize, ySize);

		int slotBackGound = mc.renderEngine.getTexture("/rgn/sprites/mabicraft/items.png");
		mc.renderEngine.bindTexture(slotBackGound);

		drawTexturedModalRect(l + 26, i1 + 30, 224,  0, 16, 16);
		drawTexturedModalRect(l + 26, i1 + 48, 224, 16, 16, 16);
	}

	@Override
	protected void actionPerformed(GuiButton par1GuiButton)
	{
		if (!par1GuiButton.enabled)
		{
			return;
		}

		containerEnchanter.onButtonPushed(par1GuiButton.id);
	}
}