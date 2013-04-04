package rgn.mods.mabicraft.entity.monster;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIFleeSun;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIRestrictSun;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;

public class EntityMabiSkeleton extends EntityMob implements IRangedAttackMob, IEntityAdditionalSpawnData
{
	private EntityAIArrowAttack         aiArrowAttack = new EntityAIArrowAttack(this, 0.25F, 60, 10.0F);
	private EntityAIAttackOnCollide aiAttackOnCollide = new EntityAIAttackOnCollide(this, EntityPlayer.class, 0.31F, false);

	protected boolean isRanged;

	public EntityMabiSkeleton(World world)
	{
		super(world);
		this.texture = "/rgn/sprites/MabiCraft/entity/mob/red_skeleton.png";
		this.moveSpeed = 0.25F;
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIRestrictSun(this));
		this.tasks.addTask(3, new EntityAIFleeSun(this, this.moveSpeed));
		this.tasks.addTask(5, new EntityAIWander(this, this.moveSpeed));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(6, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 16.0F, 0, true));

		if (world != null && !world.isRemote)
		{
			this.setCombatTask();
		}
	}

	/**
	 * sets this entity's combat AI.
	 */
	public void setCombatTask()
	{
		this.tasks.removeTask(this.aiAttackOnCollide);
		this.tasks.removeTask(this.aiArrowAttack);
		ItemStack var1 = this.getHeldItem();

		if (var1 != null && var1.itemID == Item.bow.itemID)
		{
			this.tasks.addTask(4, this.aiArrowAttack);
		}
		else
		{
			this.tasks.addTask(4, this.aiAttackOnCollide);
		}
	}

	public boolean isArcher()
	{
		return this.isRanged;
	}

	@Override
	public void initCreature()
	{
		this.isRanged = this.worldObj.rand.nextBoolean();

		if (!this.isRanged)
		{
			this.tasks.addTask(4, this.aiAttackOnCollide);
			this.setCurrentItemOrArmor(0, new ItemStack(Item.axeStone));
		}
		else
		{
			this.tasks.addTask(4, this.aiArrowAttack);
			this.addRandomArmor();
			this.func_82162_bC();
		}
	}

	@Override
	public boolean isAIEnabled()
	{
		return true;
	}

	@Override
	public int getMaxHealth()
	{
		return 20;
	}

	@Override
	protected String getLivingSound()
	{
		return "mob.skeleton.say";
	}

	@Override
	protected String getHurtSound()
	{
		return "mob.skeleton.hurt";
	}

	@Override
	protected String getDeathSound()
	{
		return "mob.skeleton.death";
	}

	@Override
	protected void playStepSound(int par1, int par2, int par3, int par4)
	{
		this.playSound("mob.skeleton.step", 0.15F, 1.0F);
	}

	@Override
	public int getAttackStrength(Entity par1Entity)
	{
		if (!this.isRanged)
		{
			ItemStack weapon = this.getHeldItem();
			int damage = 4;

			if (weapon != null)
			{
				damage+= weapon.getDamageVsEntity(this);
			}

			return damage;
		}
		else
		{
			return super.getAttackStrength(par1Entity);
		}
	}

	@Override
	public void attackEntityWithRangedAttack(EntityLiving par1EntityLiving, float f)
	{
		EntityArrow var2 = new EntityArrow(this.worldObj, this, par1EntityLiving, 1.6F, 12.0F);
		int var3 = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, this.getHeldItem());
		int var4 = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, this.getHeldItem());

		if (var3 > 0)
		{
			var2.setDamage(var2.getDamage() + (double)var3 * 0.5D + 0.5D);
		}

		if (var4 > 0)
		{
			var2.setKnockbackStrength(var4);
		}

		if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, this.getHeldItem()) > 0)
		{
			var2.setFire(100);
		}

		this.playSound("random.bow", 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
		this.worldObj.spawnEntityInWorld(var2);
	}

	@Override
	public void setCurrentItemOrArmor(int slotIndex, ItemStack itemStack)
	{
		super.setCurrentItemOrArmor(slotIndex, itemStack);

		if (!this.worldObj.isRemote && slotIndex == 0)
		{
			this.setCombatTask();
		}
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute()
	{
		return EnumCreatureAttribute.UNDEAD;
	}

	@Override
	protected int getDropItemId()
	{
		return Item.bone.itemID;
	}

	@Override
	protected void dropFewItems(boolean par1, int par2)
	{
		if (this.isRanged)
		{
			int dropNum = this.rand.nextInt(3 + par2);

			for (int i = 0; i < dropNum; ++i)
			{
				this.dropItem(Item.arrow.itemID, 1);
			}
		}
	}

	@Override

	/**
	 * Makes entity wear random armor based on difficulty
	 */
	protected void addRandomArmor()
	{
		super.addRandomArmor();
		this.setCurrentItemOrArmor(0, new ItemStack(Item.bow));
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.readEntityFromNBT(par1NBTTagCompound);
		if (par1NBTTagCompound.hasKey("isRanged"))
		{
			this.isRanged = par1NBTTagCompound.getBoolean("isRanged");
		}

		this.setCombatTask();
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.writeEntityToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setBoolean("isRanged", this.isRanged);
	}

	@Override
	public void writeSpawnData(ByteArrayDataOutput data)
	{
		try
		{
			data.writeBoolean(this.isRanged);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void readSpawnData(ByteArrayDataInput data)
	{
		try
		{
			this.isRanged = data.readBoolean();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		this.setCombatTask();
	}
}
