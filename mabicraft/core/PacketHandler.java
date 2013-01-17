package rgn.mods.mabicraft.core;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import rgn.mods.mabicraft.cook.ContainerCookware;
import rgn.mods.mabicraft.enchant.ContainerBonfire;
import rgn.mods.mabicraft.enchant.ContainerEnchanter;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class PacketHandler implements IPacketHandler
{
	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player)
	{
		if (packet.channel.equals("Bonfire"))
		{
			ByteArrayDataInput data = ByteStreams.newDataInput(packet.data);

			Container container = ((EntityPlayerMP)player).openContainer;
			if(container != null && container instanceof ContainerBonfire)
			{
				((ContainerBonfire)container).readPacketData(data);
				((ContainerBonfire)container).onCraftMatrixChanged(((ContainerBonfire)container).inventoryBonfire);
			}
		}
		else if (packet.channel.equals("Enchanter"))
		{
			ByteArrayDataInput data = ByteStreams.newDataInput(packet.data);

			Container container = ((EntityPlayerMP)player).openContainer;
			if(container != null && container instanceof ContainerEnchanter)
			{
				((ContainerEnchanter)container).readPacketData(data);
				((ContainerEnchanter)container).onCraftMatrixChanged(((ContainerEnchanter)container).inventoryEnchanter);
			}
		}
		else if (packet.channel.equals("Cookware"))
		{
			ByteArrayDataInput data = ByteStreams.newDataInput(packet.data);

			Container container = ((EntityPlayerMP)player).openContainer;
			if(container != null && container instanceof ContainerCookware)
			{
				((ContainerCookware)container).readPacketData(data);
				((ContainerCookware)container).onCraftMatrixChanged(((ContainerCookware)container).inventoryInput);
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

	public static Packet getPacket(ContainerCookware containerCookware)
	{
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bos);

		containerCookware.writePacketData(dos);

		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = "Cookware";
		packet.data    = bos.toByteArray();
		packet.length  = bos.size();
		packet.isChunkDataPacket = true;

		return packet;
	}
}