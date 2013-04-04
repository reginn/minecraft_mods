package rgn.mods.mabicraft.event;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.world.World;

import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import cpw.mods.fml.common.network.PacketDispatcher;

import rgn.mods.mabicraft.network.PacketHandler;

public class QuestBoardEvent implements IForgeEvent
{
	@ForgeSubscribe
	public void openGui(PlayerInteractEvent event)
	{
		if (event.action == PlayerInteractEvent.Action.RIGHT_CLICK_AIR)
		{
			return ;
		}

		EntityPlayer player = event.entityPlayer;
		World         world = player.worldObj;

		int x = event.x;
		int y = event.y;
		int z = event.z;

		int blockId = world.getBlockId(event.x, event.y, event.z);

		if (blockId == Block.signWall.blockID)
		{
			TileEntitySign signTileEntity = (TileEntitySign)world.getBlockTileEntity(x, y, z);
			if (this.hasKeyword(signTileEntity.signText))
			{
				PacketDispatcher.sendPacketToServer(PacketHandler.getOpenGuiPacket(1, x, y, z));
				event.setCanceled(true);
			}
		}
	}

	private boolean hasKeyword(String[] str)
	{
		boolean isContain = false;
		for (String string : str)
		{
			isContain |= string.indexOf("QuestBoard") != -1;
		}
		return isContain;
	}
}
