package rgn.mods.mabicraft.entity.monster;

import net.minecraft.block.Block;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public abstract class EntityMabiWolfBase extends EntityMob
{
	public EntityMabiWolfBase(World world)
	{
		super(world);
		this.setSize(0.6F, 0.8F);
		this.moveSpeed = 0.3F;
		this.tasks.addTask(1, new EntityAIWander(this, this.moveSpeed));
		this.tasks.addTask(1, new EntityAIAttackOnCollide(this, EntitySheep.class, this.moveSpeed, false));
		this.tasks.addTask(1, new EntityAIAttackOnCollide(this, EntityCow.class, this.moveSpeed, false));
		this.tasks.addTask(1, new EntityAIAttackOnCollide(this, EntityPlayer.class, this.moveSpeed, false));
		this.tasks.addTask(2, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(2, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntitySheep.class,  16.0F, 0, true));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityCow.class,    16.0F, 0, true));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 16.0F, 0, true));
	}

	@Override
	public boolean isAIEnabled()
	{
		return true;
	}

	@Override
	protected void playStepSound(int par1, int par2, int par3, int par4)
	{
		this.playSound("mob.wolf.step", 0.15F, 1.0F);
	}

	@Override
	protected String getLivingSound()
	{
		return "mob.wolf.growl";
		//return "mob.wolf.whine";
		//return "mob.wolf.panting";
		//return "mob.wolf.bark";
	}

	@Override
	protected String getHurtSound()
	{
		return "mob.wolf.hurt";
	}

	@Override
	protected String getDeathSound()
	{
		return "mob.wolf.death";
	}

	@Override
	protected int getDropItemId()
	{
		return Item.stick.itemID;
	}

	@Override
	protected float getSoundVolume()
	{
		return 0.3F;
	}

	@Override
	protected void dropFewItems(boolean par1, int par2)
	{
		if (par1 && (this.rand.nextInt(3) == 0 || this.rand.nextInt(1 + par2) > 0))
		{
			this.dropItem(Item.arrow.itemID, this.rand.nextInt(3));
		}
	}

	@Override
	protected void dropRareDrop(int par1)
	{
		switch (this.rand.nextInt(3))
		{
			case 0 :
				this.dropItem(Item.leather.itemID, this.rand.nextInt(3));
				break;

			case 1 :
				this.dropItem(Item.feather.itemID, this.rand.nextInt(3));
				break;

			case 2 :
				this.dropItem(Block.cloth.blockID, this.rand.nextInt(3));
				break;
		}
	}



}
