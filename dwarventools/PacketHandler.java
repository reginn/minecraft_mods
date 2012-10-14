package rgn.mods.dwarventools;

import java.io.*;
import java.util.logging.Level;

import net.minecraft.src.*;

import cpw.mods.fml.common.*;
import cpw.mods.fml.common.network.*;

public class PacketHandler implements IPacketHandler
{
	@Override
	public void onPacketData(NetworkManager network, Packet250CustomPayload packet, Player player)
	{
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(packet.data));
		int x, y, z;
		boolean hasStacks;
		int furnaceBurnTime;
		int[] items = null;
		try
		{
			x = dis.readInt();
			y = dis.readInt();
			z = dis.readInt();
			furnaceBurnTime = dis.readInt();
			hasStacks = dis.readByte() != 0;
			if (hasStacks)
			{
				items = new int[9];
				for (int i = 0; i < items.length; i++)
				{
					items[i] = dis.readInt();
				}
			}
		} catch (IOException e) {
			return;
		}
		
		World world = DwarvenTools.proxy.getClientWorld();
		TileEntity tileentity = world.getBlockTileEntity(x, y, z);
		if (tileentity instanceof TileEntityInfernalFurnace) {
			TileEntityInfernalFurnace tileentityInfernalFurnace = (TileEntityInfernalFurnace)tileentity;
			tileentityInfernalFurnace.handlePacketData(items);
			tileentityInfernalFurnace.furnaceBurnTime = furnaceBurnTime;
		}
	}
	
	public static Packet getPacket(TileEntityInfernalFurnace tileentityInfernalFurnace)
	{
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bos);
		int x = tileentityInfernalFurnace.xCoord;
		int y = tileentityInfernalFurnace.yCoord;
		int z = tileentityInfernalFurnace.zCoord;
		int furnaceBurnTime = tileentityInfernalFurnace.furnaceBurnTime;
		
		int[] items = tileentityInfernalFurnace.buildIntDataList();
		boolean hasStacks = (items != null);
		try
		{
			dos.writeInt(x);
			dos.writeInt(y);
			dos.writeInt(z);
			dos.writeInt(furnaceBurnTime);
			dos.writeByte(hasStacks? 1 : 0);
			if (hasStacks)
			{
				for (int i = 0; i < 9; i++)
				{
					dos.writeInt(items[i]);
				}
			}
		}
		catch (IOException e)
		{
			FMLLog.log(Level.SEVERE, e, "Shelf has a problem loading it's PacketHandler");
		}
		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = "DwarvenTools";
		packet.data    = bos.toByteArray();
		packet.length  = bos.size();
		packet.isChunkDataPacket = true;
		
		return packet;
	}
	
}