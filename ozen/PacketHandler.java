package rgn.mods.ozen;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class PacketHandler implements IPacketHandler
{
	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player)
	{
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(packet.data));
		int x, y, z;
		byte facing;
		boolean hasStacks;
		int[] items = null;
		try
		{
			x = dis.readInt();
			y = dis.readInt();
			z = dis.readInt();
			facing = dis.readByte();
			hasStacks = dis.readByte() != 0;
			if (hasStacks)
			{
				items = new int[18];
				for (int i = 0; i < items.length; i++)
				{
					items[i] = dis.readInt();
				}
			}
		}
		catch (IOException e)
		{
			return;
		}

		World world = Ozen.proxy.getClientWorld();
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		if (tileEntity instanceof TileEntityOzen)
		{
			TileEntityOzen tileEntityOzen = (TileEntityOzen)tileEntity;
			tileEntityOzen.handlePacketData(items);
			tileEntityOzen.setFacing(facing);
		}
	}

	public static Packet getPacket(TileEntityOzen tileEntityOzen)
	{
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream dos      = new DataOutputStream(bos);

		int x = tileEntityOzen.xCoord;
		int y = tileEntityOzen.yCoord;
		int z = tileEntityOzen.zCoord;
		byte facing = tileEntityOzen.getFacing();

		int[] items = tileEntityOzen.buildIntDataList();
		boolean hasStacks = (items != null);

		try
		{
			dos.writeInt(x);
			dos.writeInt(y);
			dos.writeInt(z);
			dos.writeByte(facing);
			dos.writeByte(hasStacks? 1 : 0);
			if (hasStacks)
			{
				for (int i = 0; i < 18; i++)
				{
					dos.writeInt(items[i]);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel                = "ozen";
		packet.data                   = bos.toByteArray();
		packet.length                 = bos.size();
		packet.isChunkDataPacket      = true;

		return packet;
	}

}