package rgn.mods.elventools.entity;

import java.util.Iterator;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import cpw.mods.fml.common.registry.IThrowableEntity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityElvenArrow extends Entity implements IProjectile, IThrowableEntity
{
	protected int xTile = -1;
	protected int yTile = -1;
	protected int zTile = -1;
	protected int inTile = 0;
	protected int inData = 0;
	protected boolean inGround = false;

	protected MovingObjectPosition mop = null;

	public int canBePickedUp = 0;
	public int arrowShake    = 0;

	public Entity shootingEntity;
	protected int ticksInGround;
	protected int ticksInAir = 0;
	protected double damage = 2.0D;

	protected int knockbackStrength;

	protected EntityElvenArrow(World world)
	{
		super(world);
		this.setSize(0.5F, 0.5F);
	}

	public EntityElvenArrow(World world, double x, double y, double z)
	{
		super(world);
		this.renderDistanceWeight = 10.0D;
		this.setSize(0.5F, 0.5F);
		this.setPosition(x, y, z);
		this.yOffset = 0.0F;
	}

	public EntityElvenArrow(World world, EntityLivingBase player, float par3)
	{
		super(world);
		this.shootingEntity = player;

		if (player instanceof EntityPlayer)
		{
			this.canBePickedUp = 1;
		}

		this.setSize(0.5F, 0.5F);
		this.setLocationAndAngles(player.posX, player.posY + (double)player.getEyeHeight(), player.posZ, player.rotationYaw, player.rotationPitch);
		this.posX -= (double)(MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * 0.16F);
		this.posY -= 0.10000000149011612D;
		this.posZ -= (double)(MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * 0.16F);
		this.setPosition(this.posX, this.posY, this.posZ);
		this.yOffset = 0.0F;
		this.motionX = (double)(-MathHelper.sin(this.rotationYaw   / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI));
		this.motionZ = (double)( MathHelper.cos(this.rotationYaw   / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI));
		this.motionY = (double)(-MathHelper.sin(this.rotationPitch / 180.0F * (float)Math.PI));
		this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, par3 * 1.5F, 1.0F);
	}

	@Override
	protected void entityInit()
	{
		this.dataWatcher.addObject(16, Byte.valueOf((byte)0));
	}

	// implements IProjectile
	@Override
	public void setThrowableHeading(double motionX, double motionY, double motionZ, float par7, float par8)
	{
		float var9 = MathHelper.sqrt_double(motionX * motionX + motionY * motionY + motionZ * motionZ);
		motionX /= (double)var9;
		motionY /= (double)var9;
		motionZ /= (double)var9;
		motionX += this.rand.nextGaussian() * 0.007499999832361937D * (double)par8;
		motionY += this.rand.nextGaussian() * 0.007499999832361937D * (double)par8;
		motionZ += this.rand.nextGaussian() * 0.007499999832361937D * (double)par8;
		motionX *= (double)par7;
		motionY *= (double)par7;
		motionZ *= (double)par7;
		this.motionX = motionX;
		this.motionY = motionY;
		this.motionZ = motionZ;
		float var10 = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
		this.prevRotationYaw   = this.rotationYaw   = (float)(Math.atan2(motionX,       motionZ) * 180.0D / Math.PI);
		this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(motionY, (double)var10) * 180.0D / Math.PI);
		this.ticksInGround = 0;
	}

	// implements IThrowableEntity
	public Entity getThrower()
	{
		return this.shootingEntity;
	}

	public void setThrower(Entity entity)
	{
		if (entity != null && entity instanceof EntityPlayer)
		{
			this.shootingEntity = entity;
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void setPositionAndRotation2(double x, double y, double z, float yaw, float pitch, int par9)
	{
		this.setPosition(x, y, z);
		this.setRotation(yaw, pitch);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void setVelocity(double vx, double vy, double vz)
	{
		this.motionX = vx;
		this.motionY = vy;
		this.motionZ = vz;

		if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F)
		{
			float var7 = MathHelper.sqrt_double(vx * vx + vz * vz);
			this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(vx, vz) * 180.0D / Math.PI);
			this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(vy, (double)var7) * 180.0D / Math.PI);
			this.prevRotationPitch = this.rotationPitch;
			this.prevRotationYaw = this.rotationYaw;
			this.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
			this.ticksInGround = 0;
		}
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();

		if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F)
		{
			float var1 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
			this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);
			this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(this.motionY, (double)var1) * 180.0D / Math.PI);
		}

		int var16 = this.worldObj.getBlockId(this.xTile, this.yTile, this.zTile);

		if (var16 > 0)
		{
			Block.blocksList[var16].setBlockBoundsBasedOnState(this.worldObj, this.xTile, this.yTile, this.zTile);
			AxisAlignedBB var2 = Block.blocksList[var16].getCollisionBoundingBoxFromPool(this.worldObj, this.xTile, this.yTile, this.zTile);

			if (var2 != null && var2.isVecInside(this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX, this.posY, this.posZ)))
			{
				this.inGround = true;
			}
		}

		if (this.arrowShake > 0)
		{
			--this.arrowShake;
		}

		if (this.inGround)
		{
			int var18 = this.worldObj.getBlockId(this.xTile, this.yTile, this.zTile);
			int var19 = this.worldObj.getBlockMetadata(this.xTile, this.yTile, this.zTile);

			this.onHit(var18, var19);
		}
		else
		{
			++this.ticksInAir;
			Vec3 var17 = this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX, this.posY, this.posZ);
			Vec3 var3 = this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
			MovingObjectPosition var4 = this.worldObj.rayTraceBlocks_do_do(var17, var3, false, true);
			var17 = this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX, this.posY, this.posZ);
			var3 = this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);

			if (var4 != null)
			{
				var3 = this.worldObj.getWorldVec3Pool().getVecFromPool(var4.hitVec.xCoord, var4.hitVec.yCoord, var4.hitVec.zCoord);
			}

			Entity var5 = null;
			List var6 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
			double var7 = 0.0D;
			Iterator var9 = var6.iterator();
			float var11;

			while (var9.hasNext())
			{
				Entity var10 = (Entity)var9.next();

				if (var10.canBeCollidedWith() && (var10 != this.shootingEntity || this.ticksInAir >= 5))
				{
					var11 = 0.3F;
					AxisAlignedBB var12 = var10.boundingBox.expand((double)var11, (double)var11, (double)var11);
					MovingObjectPosition var13 = var12.calculateIntercept(var17, var3);

					if (var13 != null)
					{
						double var14 = var17.distanceTo(var13.hitVec);

						if (var14 < var7 || var7 == 0.0D)
						{
							var5 = var10;
							var7 = var14;
						}
					}
				}
			}

			if (var5 != null)
			{
				var4 = new MovingObjectPosition(var5);
			}

			float var20;

			if (var4 != null)
			{
				if (var4.entityHit != null)
				{
					var20 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
					int var24 = MathHelper.ceiling_double_int((double)var20 * this.damage);

					if (this.getIsCritical())
					{
						var24 += this.rand.nextInt(var24 / 2 + 2);
					}

					DamageSource var22 = null;

					if (this.shootingEntity == null)
					{
						var22 = DamageSource.causeThrownDamage(this, this);
					}
					else
					{
						var22 = DamageSource.causeThrownDamage(this, this.shootingEntity);
					}

					if (this.isBurning())
					{
						var4.entityHit.setFire(5);
					}

					if (var4.entityHit.attackEntityFrom(var22, var24))
					{
						if (var4.entityHit instanceof EntityLiving)
						{
							if (!this.worldObj.isRemote)
							{
								EntityLiving var242 = (EntityLiving)var4.entityHit;
								var242.setArrowCountInEntity(var242.getArrowCountInEntity() + 1);
							}

							if (this.knockbackStrength > 0)
							{
								float var25 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);

								if (var25 > 0.0F)
								{
									var4.entityHit.addVelocity(this.motionX * (double)this.knockbackStrength * 0.6000000238418579D / (double)var25, 0.1D, this.motionZ * (double)this.knockbackStrength * 0.6000000238418579D / (double)var25);
								}
							}

						}

						this.worldObj.playSoundAtEntity(this, "random.bowhit", 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
						this.setDead();
					}
					else
					{
						this.motionX *= -0.10000000149011612D;
						this.motionY *= -0.10000000149011612D;
						this.motionZ *= -0.10000000149011612D;
						this.rotationYaw += 180.0F;
						this.prevRotationYaw += 180.0F;
						this.ticksInAir = 0;
					}
				}
				else
				{
					this.xTile = var4.blockX;
					this.yTile = var4.blockY;
					this.zTile = var4.blockZ;
					this.inTile = this.worldObj.getBlockId(this.xTile, this.yTile, this.zTile);
					this.inData = this.worldObj.getBlockMetadata(this.xTile, this.yTile, this.zTile);
					this.motionX = (double)((float)(var4.hitVec.xCoord - this.posX));
					this.motionY = (double)((float)(var4.hitVec.yCoord - this.posY));
					this.motionZ = (double)((float)(var4.hitVec.zCoord - this.posZ));
					var20 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
					this.posX -= this.motionX / (double)var20 * 0.05000000074505806D;
					this.posY -= this.motionY / (double)var20 * 0.05000000074505806D;
					this.posZ -= this.motionZ / (double)var20 * 0.05000000074505806D;
					this.worldObj.playSoundAtEntity(this, "random.bowhit", 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
					this.inGround = true;
					this.arrowShake = 7;
					this.setIsCritical(false);
					this.mop = var4;
				}
			}

			if (this.getIsCritical())
			{
				for (int var21 = 0; var21 < 4; ++var21)
				{
					this.worldObj.spawnParticle("crit", this.posX + this.motionX * (double)var21 / 4.0D, this.posY + this.motionY * (double)var21 / 4.0D, this.posZ + this.motionZ * (double)var21 / 4.0D, -this.motionX, -this.motionY + 0.2D, -this.motionZ);
				}
			}

			this.posX += this.motionX;
			this.posY += this.motionY;
			this.posZ += this.motionZ;
			var20 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);

			this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);

			for (this.rotationPitch = (float)(Math.atan2(this.motionY, (double)var20) * 180.0D / Math.PI); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F)
			{
				;
			}

			while (this.rotationPitch - this.prevRotationPitch >= 180.0F)
			{
				this.prevRotationPitch += 360.0F;
			}

			while (this.rotationYaw - this.prevRotationYaw < -180.0F)
			{
				this.prevRotationYaw -= 360.0F;
			}

			while (this.rotationYaw - this.prevRotationYaw >= 180.0F)
			{
				this.prevRotationYaw += 360.0F;
			}

			this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
			this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;

			float var23 = 0.99F;
			var11 = 0.05F;

			if (this.isInWater())
			{
				for (int var26 = 0; var26 < 4; ++var26)
				{
					float var27 = 0.25F;
					this.worldObj.spawnParticle("bubble", this.posX - this.motionX * (double)var27, this.posY - this.motionY * (double)var27, this.posZ - this.motionZ * (double)var27, this.motionX, this.motionY, this.motionZ);
				}

				var23 = 0.8F;
			}

			this.motionX *= (double)var23;
			this.motionY *= (double)var23;
			this.motionZ *= (double)var23;
			this.motionY -= (double)var11;
			this.setPosition(this.posX, this.posY, this.posZ);
			this.doBlockCollisions();
		}
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
	{
		par1NBTTagCompound.setShort("xTile", (short)this.xTile);
		par1NBTTagCompound.setShort("yTile", (short)this.yTile);
		par1NBTTagCompound.setShort("zTile", (short)this.zTile);
		par1NBTTagCompound.setByte("inTile", (byte)this.inTile);
		par1NBTTagCompound.setByte("inData", (byte)this.inData);
		par1NBTTagCompound.setByte("shake", (byte)this.arrowShake);
		par1NBTTagCompound.setByte("inGround", (byte)(this.inGround ? 1 : 0));
		par1NBTTagCompound.setByte("pickup", (byte)this.canBePickedUp);
		par1NBTTagCompound.setDouble("damage", this.damage);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		this.xTile = par1NBTTagCompound.getShort("xTile");
		this.yTile = par1NBTTagCompound.getShort("yTile");
		this.zTile = par1NBTTagCompound.getShort("zTile");
		this.inTile = par1NBTTagCompound.getByte("inTile") & 255;
		this.inData = par1NBTTagCompound.getByte("inData") & 255;
		this.arrowShake = par1NBTTagCompound.getByte("shake") & 255;
		this.inGround = par1NBTTagCompound.getByte("inGround") == 1;

		if (par1NBTTagCompound.hasKey("damage"))
		{
			this.damage = par1NBTTagCompound.getDouble("damage");
		}

		if (par1NBTTagCompound.hasKey("pickup"))
		{
			this.canBePickedUp = par1NBTTagCompound.getByte("pickup");
		}
		else if (par1NBTTagCompound.hasKey("player"))
		{
			this.canBePickedUp = par1NBTTagCompound.getBoolean("player") ? 1 : 0;
		}
	}

	@Override
	public void onCollideWithPlayer(EntityPlayer par1EntityPlayer)
	{
		if (!this.worldObj.isRemote && this.inGround && this.arrowShake <= 0)
		{
			boolean var2 = this.canBePickedUp == 1 || this.canBePickedUp == 2 && par1EntityPlayer.capabilities.isCreativeMode;

			if (this.canBePickedUp == 1 && !this.addItemStackToInventory(par1EntityPlayer))
			{
				var2 = false;
			}

			if (var2)
			{
				this.worldObj.playSoundAtEntity(this, "random.pop", 0.2F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
				par1EntityPlayer.onItemPickup(this, 1);
				this.setDead();
			}
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public float getShadowSize()
	{
		return 0.0F;
	}

	public void setDamage(double par1)
	{
		this.damage = par1;
	}

	public double getDamage()
	{
		return this.damage;
	}

	public void setKnockbackStrength(int par1)
	{
		this.knockbackStrength = par1;
	}

	public boolean canAttackWithItem()
	{
		return false;
	}

	/**
	 * Whether the arrow has a stream of critical hit particles flying behind it.
	 */
	public void setIsCritical(boolean par1)
	{

		byte var2 = this.dataWatcher.getWatchableObjectByte(16);

		if (par1)
		{
			this.dataWatcher.updateObject(16, Byte.valueOf((byte)(var2 | 1)));
		}
		else
		{
			this.dataWatcher.updateObject(16, Byte.valueOf((byte)(var2 & -2)));
		}
	}


	public boolean getIsCritical()
	{
		byte var1 = this.dataWatcher.getWatchableObjectByte(16);
		return (var1 & 1) != 0;
	}

	// add
	protected void onHit(int tileId, int metadata)
	{
		if (tileId == this.inTile && metadata == this.inData && this.mop != null)
		{
			++this.ticksInGround;

			if (this.tryPlaceBlock() || this.ticksInGround == 1200)
			{
				this.setDead();
			}
		}
		else
		{
			this.inGround = false;
			this.motionX *= (double)(this.rand.nextFloat() * 0.2F);
			this.motionY *= (double)(this.rand.nextFloat() * 0.2F);
			this.motionZ *= (double)(this.rand.nextFloat() * 0.2F);
			this.ticksInGround = 0;
			this.ticksInAir = 0;
		}
	}

	protected boolean tryPlaceBlock()
	{
		return true;
	}

	protected boolean addItemStackToInventory(EntityPlayer player)
	{
		return true;
	}

}