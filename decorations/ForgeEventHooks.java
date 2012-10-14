package rgn.mods.decorations;

import net.minecraft.src.*;

import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.BonemealEvent;

public class ForgeEventHooks
{
	@ForgeSubscribe
	public void onBonemealUsed(BonemealEvent event)
	{
		EntityPlayer player = event.entityPlayer;
		World         world = event.world;
		int         blockID = event.ID;
		int               x = event.X;
		int               y = event.Y;
		int               z = event.Z;
		
		if(blockID == Decorations.blockColoredSapling.blockID)
		{
			if (!world.isRemote)
			{
				((BlockColoredSapling)Decorations.blockColoredSapling).growTree(world, x, y, z, world.rand);
			}
			event.setHandeled();
		}
		
	}

}