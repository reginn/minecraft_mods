package rgn.mods.elventools.client;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import rgn.mods.elventools.inventory.ContainerSeedBag;

@SideOnly(Side.CLIENT)
public class GuiSeedBag extends GuiContainer
{
	private EntityPlayer player;

	public GuiSeedBag(World world, EntityPlayer player, ItemStack itemStack)
	{
		super(new ContainerSeedBag(world, player, itemStack));
		this.player = player;
	}

	@Override
	public void onGuiClosed()
	{
		super.onGuiClosed();
		((ContainerSeedBag)this.inventorySlots).onGuiClosed();
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		fontRenderer.drawString(((ContainerSeedBag)this.inventorySlots).inventorySeedBag.getInvName(), 60, 6, 0x404040);
		fontRenderer.drawString("Inventory", 8, (ySize - 96) + 2, 0x404040);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.func_98187_b("/gui/trap.png");
		int l = width - xSize >> 1;
		int i1 = height - ySize >> 1;
		drawTexturedModalRect(l, i1, 0, 0, xSize, ySize);
	}
}