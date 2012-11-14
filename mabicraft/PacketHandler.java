package rgn.mods.mabicraft;

import java.io.*;
import com.google.common.io.*;

import net.minecraft.src.*;

import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

import rgn.mods.mabicraft.enchant.*;

public class PacketHandler implements IPacketHandler
{
	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player)
	{
		if (packet.channel.equals("Bonfire"))
		{
			ByteArrayDataInput data = ByteStreams.newDataInput(packet.data);
			
			Container container = ((EntityPlayerMP)player).craftingInventory;
			if(container != null && container instanceof ContainerBonfire)
			{
				((ContainerBonfire)container).readPacketData(data);
				((ContainerBonfire)container).onCraftMatrixChanged(((ContainerBonfire)container).inventoryBonfire);
			}
		}
		else if (packet.channel.equals("Enchanter"))
		{
			ByteArrayDataInput data = ByteStreams.newDataInput(packet.data);
			
			Container container = ((EntityPlayerMP)player).craftingInventory;
			if(container != null && container instanceof ContainerEnchanter)
			{
				((ContainerEnchanter)container).readPacketData(data);
				((ContainerEnchanter)container).onCraftMatrixChanged(((ContainerEnchanter)container).inventoryEnchanter);
			}
		}
	}
	
	
	public static Packet getPacket(ContainerBonfire containerBonfire)
	{
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bos);
		
		containerBonfire.writePacketData(dos);
		
		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = "Bonfire";
		packet.data    = bos.toByteArray();
		packet.length  = bos.size();
		packet.isChunkDataPacket = true;
		
		return packet;
	}
	
	public static Packet getPacket(ContainerEnchanter containerEnchanter)
	{
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bos);
		
		containerEnchanter.writePacketData(dos);
		
		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = "Enchanter";
		packet.data    = bos.toByteArray();
		packet.length  = bos.size();
		packet.isChunkDataPacket = true;
		
		return packet;
	}
}