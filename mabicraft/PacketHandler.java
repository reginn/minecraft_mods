package rgn.mods.mabicraft;

import java.io.*;
import java.util.logging.Level;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import net.minecraft.src.*;

import cpw.mods.fml.common.*;
import cpw.mods.fml.common.network.*;

public class PacketHandler implements IPacketHandler
{
	@Override
	public void onPacketData(NetworkManager network, Packet250CustomPayload packet, Player player)
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