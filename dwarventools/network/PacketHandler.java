package rgn.mods.dwarventools.network;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.world.World;

import rgn.mods.dwarventools.DwarvenTools;

public class PacketHandler implements IPacketHandler
{
	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player)
	{
		if (packet.channel.equals("DwarvenTools"))
		{
			DataInputStream dis = new DataInputStream(new ByteArrayInputStream(packet.data));
			int entityId;
			byte typeId;
			
			try
			{
				entityId = dis.readInt();
				typeId   = dis.readByte();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				return ;
			}
			
			World world = DwarvenTools.proxy.getClientWorld();
			
			EntityPlayer entityPlayer = (EntityPlayer)player;
			Entity entity = world.getEntityByID(entityId);
			
			DwarvenTools.proxy.spawnCustomParticle(world, entityPlayer, entity, typeId);
		}
	}
	
	public static Packet getPacketCustomAnimation(Entity target, int type)
	{
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream dos      = new DataOutputStream(bos);

		int entityId = target.entityId;
		int typeId = type;
		
		try
		{
			dos.writeInt(entityId);
			dos.writeByte((byte)typeId);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel                = "DwarvenTools";
		packet.data                   = bos.toByteArray();
		packet.length                 = bos.size();
		packet.isChunkDataPacket      = true;

		return packet;
	}
}