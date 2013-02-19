package rgn.mods.mabicraft.network;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.world.World;

import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

import rgn.mods.mabicraft.MabiCraft;
import rgn.mods.mabicraft.entity.projectile.EntityMabiFishHook;
import rgn.mods.mabicraft.inventory.ContainerBonfire;
import rgn.mods.mabicraft.inventory.ContainerCookware;
import rgn.mods.mabicraft.inventory.ContainerEnchanter;
import rgn.mods.mabicraft.inventory.EnumGuiID;

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
				((ContainerBonfire)container).onCraftMatrixChanged(((ContainerBonfire)container).inventoryInput);
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
		else if (packet.channel.equals("Cookpot"))
		{
			ByteArrayDataInput data = ByteStreams.newDataInput(packet.data);

			int x, y, z;
			try
			{
				x = data.readInt();
				y = data.readInt();
				z = data.readInt();

				EntityPlayerMP entityPlayer = (EntityPlayerMP)player;
				entityPlayer.openGui(MabiCraft.instance, EnumGuiID.COOKING_POT.ordinal(), entityPlayer.worldObj, x, y, z);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		else if (packet.channel.equals("FishingRod"))
		{
			ByteArrayDataInput data = ByteStreams.newDataInput(packet.data);

			int playerId;
			double x, y, z;
			try
			{
				x = data.readDouble();
				y = data.readDouble();
				z = data.readDouble();

				World world = MabiCraft.proxy.getClientWorld();
				if (world != null)
				{
					EntityPlayer entityPlayer = (EntityPlayer)player;
					entityPlayer.fishEntity = new EntityMabiFishHook(world, x, y, z, entityPlayer);
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
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

	public static Packet getOpenGuiPacket(int x, int y, int z)
	{
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bos);

		try
		{
			dos.writeInt(x);
			dos.writeInt(y);
			dos.writeInt(z);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = "Cookpot";
		packet.data    = bos.toByteArray();
		packet.length  = bos.size();
		packet.isChunkDataPacket = true;

		return packet;
	}

	public static Packet getPacketFishingRod(EntityPlayer player, EntityMabiFishHook entity)
	{
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bos);

		try
		{
			dos.writeDouble(entity.posX);
			dos.writeDouble(entity.posY);
			dos.writeDouble(entity.posZ);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = "FishingRod";
		packet.data    = bos.toByteArray();
		packet.length  = bos.size();
		packet.isChunkDataPacket = true;

		return packet;
	}
}