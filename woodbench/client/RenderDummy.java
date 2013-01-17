package rgn.mods.woodbench.client;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

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