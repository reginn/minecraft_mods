package rgn.mods.woodbench;

import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.packet.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class TileEntityWoodBench extends TileEntity
{
	private int entityId;
	private int rideEntityId;
	private Entity benchEntity;
	private final AxisAlignedBB boundingBox = AxisAlignedBB.getBoundingBox(0.0D, 0.0D, 0.0D, 5.0D, 5.0D, 5.0D);
	private int dimensionID;
	private int ticks;
	protected Entity ridingEntity;

	public TileEntityWoodBench()
	{
		this.entityId     = 0;
		this.rideEntityId = 0;
		this.benchEntity  = null;
	}

	public void mountEntityIfNull(EntityLiving entityLiving)
	{
		if (this.entityId == 0)
		{
			this.mountEntity(entityLiving);
		}
		else
		{
			this.unmountEntity();
		}
	}

	public void mountEntity()
	{
		if (this.entityId == 0 && this.rideEntityId == 0)
		{
			return;
		}

		if (this.entityId != 0 && this.benchEntity == null)
		{
			this.benchEntity = this.getEntity(worldObj, xCoord, yCoord, zCoord, entityId);
		}

		if (this.benchEntity != null)
		{
			if (this.rideEntityId != 0 && this.benchEntity.riddenByEntity == null)
			{
				Entity rideEntity = getEntity(worldObj, xCoord, yCoord, zCoord, this.rideEntityId);
				if (rideEntity != null)
				{
					if (!this.worldObj.isRemote)
					{
						rideEntity.mountEntity(this.benchEntity);
						this.ridingEntity = rideEntity;
					}
				}
			}
		}
		else
		{
			this.entityId = 0;
			this.rideEntityId = 0;
			this.benchEntity = null;
		}
	}

	public void mountEntity(EntityLiving entityLiving)
	{
		if (this.entityId == 0)
		{
			if (entityLiving.ridingEntity == null)
			{
				this.benchEntity = new EntityDummy(worldObj, xCoord + 0.5D, yCoord, zCoord + 0.5D, this);
				if (!this.worldObj.isRemote)
				{
					this.worldObj.spawnEntityInWorld(this.benchEntity);
					entityLiving.mountEntity(this.benchEntity);
					this.entityId = this.benchEntity.entityId;
					this.rideEntityId = entityLiving.entityId;
					this.ridingEntity = entityLiving;
				}

			}
		}
	}

	public void unmountEntity()
	{
		if (this.entityId != 0)
		{
			if (this.entityId != 0 && this.benchEntity == null)
			{
				this.benchEntity = getEntity(worldObj, xCoord, yCoord, zCoord, entityId);
			}

			if (this.benchEntity != null)
			{
				if(this.rideEntityId != 0 && this.benchEntity.riddenByEntity == null)
				{
					Entity rideEntity = getEntity(worldObj, xCoord, yCoord, zCoord, rideEntityId);
					if(rideEntity != null)
					{
						rideEntity.mountEntity(null);
						rideEntity.posX = rideEntity.posX + (5 * Math.cos(rideEntity.rotationYaw/ 180.0F * (float)Math.PI));
						rideEntity.posZ = rideEntity.posZ + (5 * Math.sin(rideEntity.rotationYaw/ 180.0F * (float)Math.PI));
						rideEntity.posY = rideEntity.posY - 0.5;
					}
				}
				this.worldObj.setEntityDead(this.benchEntity);
			}

			this.entityId = 0;
			this.rideEntityId = 0;
			this.benchEntity = null;
			this.ridingEntity = null;
		}
	}

	@Override
	public void updateEntity()
	{
		super.updateEntity();
		this.mountEntity();
		if (this.rideEntityId != 0 )
		{
			if (this.benchEntity != null && (this.benchEntity.riddenByEntity == null || this.benchEntity.riddenByEntity.isDead))
			{
				this.unmountEntity();
			}
		}
	}

	private Entity getEntity(World world, int i, int j, int k, int id)
	{
		AxisAlignedBB axisalignedbb = this.boundingBox.addCoord(i, j, k);
		List                   list = world.getEntitiesWithinAABB(EntityDummy.class, axisalignedbb);
		Iterator           iterator = list.iterator();

		while(iterator.hasNext())
		{
			Entity entity = (Entity)iterator.next();
			if (entity.entityId == id)
			{
				return entity;
			}
		}
		return null;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbttagcompound)
	{
		super.readFromNBT(nbttagcompound);

		try
		{
			this.entityId = nbttagcompound.getInteger("entityId");
		}
		catch (Exception e)
		{
			this.entityId = 0;
		}

		try
		{
			this.rideEntityId = nbttagcompound.getInteger("rideEntityId");
		}
		catch (Exception e)
		{
			this.rideEntityId = 0;
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound nbttagcompound)
	{
		super.writeToNBT(nbttagcompound);
		NBTTagList nbtIcon = new NBTTagList();
		nbttagcompound.setInteger("entityId", this.entityId);
		nbttagcompound.setInteger("rideEntityId", this.rideEntityId);
	}
	
	@Override
	public Packet getDescriptionPacket()
	{
		return PacketHandler.getPacket(this);
	}

	public int getEntityId()
	{
		return this.entityId;
	}

	public int getRideEntityId()
	{
		return this.rideEntityId;
	}

	public void setEntityId(int _entityId)
	{
		this.entityId = _entityId;
	}

	public void setRideEntityId(int _rideEntityId)
	{
		this.rideEntityId = _rideEntityId;
	}

}
