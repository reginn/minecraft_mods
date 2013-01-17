package rgn.mods.toolrack;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.logging.Level;

import net.minecraft.item.ItemStack;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class PacketHandler implements IPacketHandler
{
	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player)
	{
		if (packet.channel.equals("Toolrack"))
		{
			DataInputStream dis = new DataInputStream(new ByteArrayInputStream(packet.data));
			int x, y, z;
			byte facing;
			boolean hasStacks;
			ItemStack[] items = null;
			try
			{
				x = dis.readInt();
				y = dis.readInt();
				z = dis.readInt();
				facing = dis.readByte();
				hasStacks = dis.readByte() != 0;
				if (hasStacks)
				{
					items = new ItemStack[1];
					for (int i = 0; i < items.length; ++i)
					{
						items[i] = Packet.readItemStack(dis);
					}
				}
			}
			catch (IOException e)
			{
				return;
			}

			World world = Toolrack.proxy.getClientWorld();
			TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
			if (tileEntity instanceof TileEntityToolrack)
			{
				TileEntityToolrack tileEntityToolrack = (TileEntityToolrack)tileEntity;
				tileEntityToolrack.setItems(items);
				tileEntityToolrack.setFacing(facing);
			}
		}
	}

	public static Packet getPacket(TileEntityToolrack tileEntityToolrack)
	{
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream dos      = new DataOutputStream(bos);

		int x = tileEntityToolrack.xCoord;
		int y = tileEntityToolrack.yCoord;
		int z = tileEntityToolrack.zCoord;
		byte facing = tileEntityToolrack.getFacing();
		ItemStack[] items = tileEntityToolrack.getItems();
		
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
				for (int i = 0; i < items.length; i++)
				{
					Packet.writeItemStack(items[i], dos);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel                = "Toolrack";
		packet.data                   = bos.toByteArray();
		packet.length                 = bos.size();
		packet.isChunkDataPacket      = true;

		return packet;
	}
}