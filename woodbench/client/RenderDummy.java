package rgn.mods.woodbench.client;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import net.minecraft.src.*;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderDummy extends Render
{
	public RenderDummy()
	{
	}

	@Override
	public void doRender(Entity entity, double d, double d1, double d2, float f, float f1)
	{
	}
}