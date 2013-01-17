package rgn.mods.woodbench;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import net.minecraft.entity.EntityLiving;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class PacketHandler implements IPacketHandler
{
	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player)
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
	}

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
}