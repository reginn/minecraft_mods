package rgn.mods.elventools.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import net.minecraftforge.event.Event;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.BonemealEvent;

import rgn.mods.elventools.block.BlockEbonySapling;
import rgn.mods.elventools.block.ElvenBlock;

public class EbonyTreeEvent implements IForgeEvent
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

		if (blockID == ElvenBlock.blockEbonySapling.blockID)
		{
			if (!world.isRemote)
			{
				((BlockEbonySapling)ElvenBlock.blockEbonySapling).growTree(world, x, y, z, world.rand);
				event.setResult(Event.Result.ALLOW);
			}
		}
		event.setResult(Event.Result.DENY);
	}	
}
