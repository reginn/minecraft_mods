package rgn.mods.rum.network;

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

import rgn.mods.rum.RumCore;
import rgn.mods.rum.tileentity.TileEntityLockedChest;

public class FMLPacketHandler implements IPacketHandler
{
	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player)
	{
		if (packet.channel.equals("LockedChest"))
		{
			DataInputStream dis = new DataInputStream(new ByteArrayInputStream(packet.data));
			int x, y, z;
			boolean isLocked;
			try
			{
				x = dis.readInt();
				y = dis.readInt();
				z = dis.readInt();
				isLocked = dis.readBoolean();
			}
			catch (IOException e)
			{
				e.printStackTrace();
				return;
			}

			World world = RumCore.proxy.getClientWorld();
			TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
			if (tileEntity instanceof TileEntityLockedChest)
			{
				TileEntityLockedChest tileEntityLockedChest = (TileEntityLockedChest)tileEntity;
				if (isLocked)
				{
					tileEntityLockedChest.setLocked();
				}
				else
				{
					tileEntityLockedChest.setUnlocked();
				}
			}
		}
	}

	public static Packet getLockedChestPacket(TileEntityLockedChest tileEntity)
	{
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bos);
		int x = tileEntity.xCoord;
		int y = tileEntity.yCoord;
		int z = tileEntity.zCoord;
		boolean isLocked = tileEntity.isLocked();

		try
		{
			dos.writeInt(x);
			dos.writeInt(y);
			dos.writeInt(z);
			dos.writeBoolean(isLocked);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = "LockedChest";
		packet.data    = bos.toByteArray();
		packet.length  = bos.size();
		packet.isChunkDataPacket = true;

		return packet;
	}
}
