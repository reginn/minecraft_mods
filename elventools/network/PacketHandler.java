package rgn.mods.elventools.network;

import java.io.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import rgn.mods.elventools.ElvenTools;
import rgn.mods.elventools.inventory.InventorySeedBag;
import rgn.mods.elventools.item.ItemElvenSeedBag;

public class PacketHandler implements IPacketHandler
{
	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player)
	{
		if (packet.channel.equals("ElvenPatricle"))
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
			
			World world = ElvenTools.proxy.getClientWorld();
			
			EntityPlayer entityPlayer = (EntityPlayer)player;
			Entity entity = world.getEntityByID(entityId);
			
			ElvenTools.proxy.spawnCustomParticle(world, entityPlayer, entity, typeId);
		}
		else if (packet.channel.equals("BindInfo"))
		{
			DataInputStream dis = new DataInputStream(new ByteArrayInputStream(packet.data));
			int entityId;
			double posX, posY, posZ;
			
			try
			{
				entityId = dis.readInt();
				posX = dis.readDouble();
				posY = dis.readDouble();
				posZ = dis.readDouble();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				return ;
			}
			
			World world = ElvenTools.proxy.getClientWorld();
			
			Entity entity = world.getEntityByID(entityId);
			
			ElvenTools.proxy.setBindInfo(entity);
		}
		else if (packet.channel.equals("SeedBag"))
		{
			DataInputStream dis = new DataInputStream(new ByteArrayInputStream(packet.data));
			
			ItemStack is;
			
			try
			{
				is = Packet.readItemStack(dis);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				return ;
			}
			
			World world = ElvenTools.proxy.getClientWorld();
			
			EntityPlayer entityPlayer = (EntityPlayer)player;
			
			ItemStack seedBag = entityPlayer.getCurrentEquippedItem();
			if (seedBag != null && seedBag.getItem() instanceof ItemElvenSeedBag)
			{
				seedBag.setTagCompound(is.getTagCompound());
			}
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
		packet.channel                = "ElvenPatricle";
		packet.data                   = bos.toByteArray();
		packet.length                 = bos.size();
		packet.isChunkDataPacket      = true;

		return packet;
	}
	
	public static Packet getPacketBindInfo(Entity target)
	{
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream dos      = new DataOutputStream(bos);

		int entityId = target.entityId;
		double posX = target.posX;
		double posY = target.posY;
		double posZ = target.posZ;
		
		try
		{
			dos.writeInt(entityId);
			dos.writeDouble(posX);
			dos.writeDouble(posY);
			dos.writeDouble(posZ);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel                = "BindInfo";
		packet.data                   = bos.toByteArray();
		packet.length                 = bos.size();
		packet.isChunkDataPacket      = true;

		return packet;
	}
	
	public static Packet getPacketSeedBagItems(InventorySeedBag inventory)
	{
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream dos      = new DataOutputStream(bos);

		ItemStack is = inventory.getSeedBag();
		
		try
		{
			Packet.writeItemStack(is, dos);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel                = "SeedBag";
		packet.data                   = bos.toByteArray();
		packet.length                 = bos.size();
		packet.isChunkDataPacket      = true;

		return packet;
	
	}
	
}