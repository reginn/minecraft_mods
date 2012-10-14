package rgn.mods.woodbench;

import net.minecraft.src.*;

import java.io.*;

import java.io.DataOutputStream;
import java.io.ByteArrayOutputStream;

import java.io.DataInputStream;
import java.io.ByteArrayInputStream;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class PacketHandler implements IPacketHandler
{
	@Override
	public void onPacketData(NetworkManager network, Packet250CustomPayload packet, Player player)
	{
		if (packet.channel.equals("bench"))
		{
			ByteArrayDataInput data = ByteStreams.newDataInput(packet.data);
			int x, y, z;
			int entityId, rideEntityId;
			try
			{
				x = data.readInt();
				y = data.readInt();
				z = data.readInt();
				
				entityId = data.readInt();
				rideEntityId = data.readInt();
				
				World world = WoodBench.proxy.getClientWorld();
				TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
				
				if (tileEntity instanceof TileEntityWoodBench)
				{
					TileEntityWoodBench tileEntityWoodBench = (TileEntityWoodBench)tileEntity;
					tileEntityWoodBench.setEntityId(entityId);
					tileEntityWoodBench.setRideEntityId(rideEntityId);
				}
			} 
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		else if (packet.channel.equals("bench2"))
		{
			ByteArrayDataInput data = ByteStreams.newDataInput(packet.data);
			int x, y, z;
			int entityId, rideEntityId;
			float yaw, pitch;
			try
			{
				x = data.readInt();
				y = data.readInt();
				z = data.readInt();
				
				entityId = data.readInt();
				rideEntityId = data.readInt();
				
				yaw = data.readFloat();
				pitch = data.readFloat();
				
				World world = WoodBench.proxy.getClientWorld();
				TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
				
				if (tileEntity instanceof TileEntityWoodBench)
				{
					TileEntityWoodBench tileEntityWoodBench = (TileEntityWoodBench)tileEntity;
					tileEntityWoodBench.setEntityId(entityId);
					tileEntityWoodBench.setRideEntityId(rideEntityId);
					tileEntityWoodBench.setYaw(yaw);
					tileEntityWoodBench.setPitch(pitch);
				}
			} 
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	// パケットを生成するメソッド
	public static Packet getPacket(TileEntityWoodBench tileEntityWoodBench)
	{
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bos);
		
		int x = tileEntityWoodBench.xCoord;
		int y = tileEntityWoodBench.yCoord;
		int z = tileEntityWoodBench.zCoord;
		
		int entityId = tileEntityWoodBench.getEntityId();
		int rideEntityId = tileEntityWoodBench.getRideEntityId();
		
		try
		{
			dos.writeInt(x);
			dos.writeInt(y);
			dos.writeInt(z);
			dos.writeInt(entityId);
			dos.writeInt(rideEntityId);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = "bench"; 
		packet.data    = bos.toByteArray();
		packet.length  = bos.size();
		packet.isChunkDataPacket = true;
		
		return packet;
	}
	
	public static Packet getPacket(TileEntityWoodBench tileEntityWoodBench, EntityLiving entityLiving)
	{
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bos);
		
		int x = tileEntityWoodBench.xCoord;
		int y = tileEntityWoodBench.yCoord;
		int z = tileEntityWoodBench.zCoord;
		
		int entityId     = tileEntityWoodBench.getEntityId();
		int rideEntityId = tileEntityWoodBench.getRideEntityId();
		
		float yaw = entityLiving.rotationYaw;
		float pitch = entityLiving.rotationPitch;
		
		try
		{
			dos.writeInt(x);
			dos.writeInt(y);
			dos.writeInt(z);
			dos.writeInt(entityId);
			dos.writeInt(rideEntityId);
			dos.writeFloat(yaw);
			dos.writeFloat(pitch);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = "bench2"; 
		packet.data    = bos.toByteArray();
		packet.length  = bos.size();
		packet.isChunkDataPacket = true;
		
		return packet;
	}
}