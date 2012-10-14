package rgn.mods.lamp;

import java.io.*;
import java.util.logging.Level;

import net.minecraft.src.*;

import cpw.mods.fml.common.*;
import cpw.mods.fml.common.network.*;

public class PacketHandler implements IPacketHandler
{
	@Override
	public void onPacketData(NetworkManager network, Packet250CustomPayload packet, Player player)
	{
		// NOOP
	}
	
}