package rgn.mods.mabicraft.cook.client;

import java.io.*;

import org.lwjgl.input.Mouse;

import net.minecraft.src.*;
import net.minecraft.client.Minecraft;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiAddButton extends GuiButton
{
	private int     pressedTick;
	private int     ticks;
	private boolean isReleased;
	private boolean isItemInSlot;
	
	public GuiAddButton(int par1, int par2, int par3, int par4, int par5, String par6Str)
	{
		super(par1, par2, par3, par4, par5, par6Str);
		this.pressedTick  = 0;
		this.isReleased   = false;
		this.isItemInSlot = false;
		this.ticks        = 0;
	}
	
	@Override
	protected void mouseDragged(Minecraft par1Minecraft, int mouseX, int mouseY)
	{
		// if (Mouse.getEventButtonState())
        if (Mouse.isButtonDown(0))
		{
			if ((mouseX > this.xPosition && mouseX < this.xPosition + this.width) 
			&& ( mouseY > this.yPosition && mouseY < this.yPosition + this.height))
			{
				if (!isReleased && isItemInSlot)
				{
					if (++ticks % 2 == 0)
					{
						++this.pressedTick;
					}
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