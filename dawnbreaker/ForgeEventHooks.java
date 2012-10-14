package rgn.mods.dawnbreaker;

import java.lang.Math;

import java.util.Set;

import com.google.common.collect.Sets;

import net.minecraft.src.*;

import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;

import cpw.mods.fml.relauncher.ReflectionHelper;

public class ForgeEventHooks
{
	private Set<Integer> targetSet = Sets.newHashSet();
	
	@ForgeSubscribe
	public void onPlayerAttacked(AttackEntityEvent event)
	{
		EntityPlayer player = event.entityPlayer;
		World         world = event.entityPlayer.worldObj;
		
		EntityLiving target = null;
		
		if (event.target instanceof EntityLiving)
		{
			target = (EntityLiving)event.target;
		}
		else
		{
			return ;
		}
		
		if (player.inventory.getCurrentItem().itemID == DawnBreaker.itemDawnBreaker.shiftedIndex)
		{
			if (target instanceof EntityMob)
			{
				EnumCreatureAttribute attribute = target.getCreatureAttribute();
				
				if (attribute == EnumCreatureAttribute.UNDEAD)
				{
					if (!world.isRemote)
					{
						if (target.getHealth() < DawnBreaker.itemDawnBreaker.getDamageVsEntity(target))
						{
							double addX = (double)(-MathHelper.sin(player.rotationYaw * (float)Math.PI / 180.0F) * (float)10 * 0.5F);
							double addZ = (double)( MathHelper.cos(player.rotationYaw * (float)Math.PI / 180.0F) * (float)10 * 0.5F);
							
							target.addVelocity(addX, 0.1D, addZ);
							
							this.targetSet.add(new Integer(target.entityId));
						}
					}
				}
			}
		}
	}
	
	@ForgeSubscribe
	public void onLivingFell(LivingFallEvent event)
	{
		EntityLiving target = event.entityLiving;
		World         world = event.entityLiving.worldObj;
		
		float[] powers = new float[] {0.1F, 1.0F, 3.0F, 6.0F};
		
		if (this.targetSet.isEmpty())
		{
			return ;
		}
		
		if (this.targetSet.contains(new Integer(target.entityId)))
		{
			if (!world.isRemote)
			{
				this.targetSet.remove(new Integer(target.entityId));
				
				if (DawnBreaker.explodePower >= 0 && DawnBreaker.explodePower <= 2)
				{
					world.createExplosion(target, target.posX, target.posY, target.posZ, powers[DawnBreaker.explodePower]);
				}
			}
		}
	}
	
}