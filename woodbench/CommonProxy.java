package rgn.mods.woodbench;

import net.minecraft.world.World;

public class CommonProxy
{
	public int getBlockNewRenderType()
	{
		return -1;
	}

	public void registerRenderers()
	{
	}

	public World getClientWorld()
	{
		return null;
	}
}