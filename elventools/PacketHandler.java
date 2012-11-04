package rgn.mods.elventools;

import java.io.*;
import java.util.logging.Level;

import net.minecraft.src.*;

import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class PacketHandler implements IPacketHandler
{
	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player)
	{
		// NOOP
	}
}