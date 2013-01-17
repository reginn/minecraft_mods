package rgn.mods.mabicraft.cook.client;

import java.io.*;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicLong;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

import org.lwjgl.input.Mouse;

import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiAddButton extends GuiButton
{
	private int        pressedTick;
	private long       dTick;
	private long       startTick;
	private boolean    isReleased;
	private boolean    isItemInSlot;
	private Field      field;
	private AtomicLong clientTick;

	public GuiAddButton(int par1, int par2, int par3, int par4, int par5, String par6Str)
	{
		super(par1, par2, par3, par4, par5, par6Str);
		try
		{
			clientTick = ReflectionHelper.getPrivateValue(TickRegistry.class, null, "clientTickCounter");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	protected void mouseDragged(Minecraft par1Minecraft, int mouseX, int mouseY)
	{
		if (Mouse.isButtonDown(0) && this.mousePressed(par1Minecraft, mouseX, mouseY))
		{
			if (!isReleased && isItemInSlot)
			{
				if (this.clientTick.incrementAndGet() % 3 == 0)
				{
					++this.pressedTick;
				}
			}
        }
		else
		{
			if (this.pressedTick > 0)
			{
				this.isReleased = true;
			}
		}
	}
	
	public int getPressedTick()
	{
		return this.pressedTick > 100 ? 100 : this.pressedTick;
	}

	public void setItemInSlot(boolean par1)
	{
		this.isItemInSlot = par1;
	}

	public void resetPressedTick()
	{
		this.pressedTick = 0;
	}

	public void resetIsReleased()
	{
		this.isReleased = false;
	}

}