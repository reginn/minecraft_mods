package rgn.mods.woodbench;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.src.ModLoader;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import cpw.mods.fml.common.ObfuscationReflectionHelper;

public class EntityDummy extends Entity
{
	private double field_9388_j;
	private double field_9387_k;
	private double field_9386_l;

	private TileEntityWoodBench tileEntity;
	private int dimensionID;

	public EntityDummy(World world)
	{
		super(world);
		setSize(0.1F, 0.4F);
		yOffset = 0.0F;
		preventEntitySpawning = true;
	}

	public EntityDummy(World world, double x, double y, double z, TileEntityWoodBench _tileEntity)
	{
		this(world);
		setPosition(x, y + (double)yOffset, z);
		motionX = 0.0D;
		motionY = 0.0D;
		motionZ = 0.0D;
		prevPosX = x;
		prevPosY = y;
		prevPosZ = z;
		this.tileEntity = _tileEntity;
	}

	@Override
	protected boolean canTriggerWalking()
	{
		return false;
	}

	@Override
	public AxisAlignedBB getCollisionBox(Entity entity)
	{
		return entity.boundingBox;
	}

	@Override
	public AxisAlignedBB getBoundingBox()
	{
		return boundingBox;
	}

	@Override
	public boolean canBePushed()
	{
		return false;
	}

	@Override
	public boolean canBeCollidedWith()
	{
		return false;
	}

	@Override
	public double getMountedYOffset()
	{
		if (riddenByEntity instanceof EntityPlayer)
		{
			return (double)height * 0.0D + 0.3D;
		}
		if (riddenByEntity instanceof EntitySpider)
		{
			return (double)height * 0.0D - 0.1D;
		}
		if (riddenByEntity instanceof EntitySkeleton || riddenByEntity instanceof EntityZombie || riddenByEntity instanceof EntityEnderman)
		{
			return (double)height * 0.0D - 0.4D;
		}

		if (riddenByEntity.getClass().getSimpleName().compareTo("Melo_e") == 0)
		{
			return (double)height * 0.0D - 0.4D;
		}

		return (double)height * 0.0D + 0.1D;
	}

	@Override
	public String getTexture()
	{
		return "";
	}

	@Override
	public boolean attackEntityFrom(DamageSource damagesource, int i)
	{
		Entity entity = damagesource.getEntity();
		if(worldObj.isRemote || isDead)
		{
			return true;
		}
		setBeenAttacked();
		if (entity instanceof EntityPlayer)
		{
			if (riddenByEntity != null)
			{
				riddenByEntity.mountEntity(null);
			}
		}
		setDead();
		return true;
	}


	@Override
	public void setPositionAndRotation2(double d, double d1, double d2, float f, float f1, int i)
	{
		motionX = field_9388_j;
		motionY = field_9387_k;
		motionZ = field_9386_l;
	}

	@Override
	public void setVelocity(double d, double d1, double d2)
	{
		field_9388_j = motionX = d;
		field_9387_k = motionY = d1;
		field_9386_l = motionZ = d2;
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();

		List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.expand(1.0D, 0.0D, 1.0D));
		if(list != null && list.size() > 0)
		{
			for(int j1 = 0; j1 < list.size(); j1++)
			{
				Entity entity = (Entity)list.get(j1);
				if(entity != riddenByEntity && entity.canBePushed() && (entity instanceof EntityDummy))
				{
					entity.applyEntityCollision(this);
				}
			}
		}

		if (riddenByEntity != null)
		{
			if (riddenByEntity instanceof EntityLiving)
			{
				EntityLiving entity = ((EntityLiving)riddenByEntity);
				try
				{
					String fieldName = ObfuscationReflectionHelper.obfuscation ? "bI" : "numTicksToChaseTarget";
					ObfuscationReflectionHelper.setPrivateValue(EntityLiving.class, entity, Integer.valueOf(0), new String[]{fieldName});
					// ModLoader.setPrivateValue(EntityLiving.class, entity, 71, 0);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
			if (riddenByEntity.isDead)
			{
				riddenByEntity = null;
				setDead();
			}
		}
		else
		{
			setDead();
		}
	}

	@Override
	public void applyEntityCollision(Entity entity)
	{
		if(worldObj.isRemote)
		{
			return;
		}

		if(entity == riddenByEntity)
		{
			return;
		}

		if((entity instanceof EntityLiving) && !(entity instanceof EntityPlayer) && riddenByEntity == null && entity.ridingEntity == null)
		{
			entity.mountEntity(this);
		}
		super.applyEntityCollision(entity);
	}

	@Override
	public boolean interact(EntityPlayer entityplayer)
	{
		return false;
	}

	@Override
	protected void entityInit()
	{
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbttagcompound)
	{
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbttagcompound)
	{
	}
}
