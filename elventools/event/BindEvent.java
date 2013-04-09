package rgn.mods.elventools.event;

import java.util.Map;

import com.google.common.collect.Maps;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import cpw.mods.fml.common.network.PacketDispatcher;

import rgn.mods.elventools.item.ElvenItem;
import rgn.mods.elventools.network.PacketHandler;

public class BindEvent implements IForgeEvent
{
	public static Map<Integer, EntityPosition> bindEntityMap = Maps.newHashMap();

	private int ticks;

	@ForgeSubscribe
	public void onBindSet(LivingHurtEvent event)
	{
		EntityLiving       target = event.entityLiving;
		DamageSource damageSource = event.source;
		World               world = target.worldObj;

		if (damageSource.getEntity() instanceof EntityPlayer && damageSource.getSourceOfDamage() instanceof EntityArrow)
		{
			EntityPlayer player = (EntityPlayer)damageSource.getEntity();
			ItemStack equippedItem = player.getCurrentEquippedItem();

			if (equippedItem != null && equippedItem.getItem().itemID == ElvenItem.itemEndBow.itemID)
			{
				bindEntityMap.put(target.entityId, new EntityPosition(target.posX, target.posY, target.posZ));
				PacketDispatcher.sendPacketToAllInDimension(PacketHandler.getPacketBindInfo(target), target.dimension);
			}
		}
	}

	@ForgeSubscribe
	public void doBind(LivingEvent.LivingUpdateEvent event)
	{
		EntityLiving target = event.entityLiving;

		if (target != null && bindEntityMap.containsKey(target.entityId))
		{
			EntityPosition pos = bindEntityMap.get(target.entityId);
			if (++this.ticks < 20 * 5)
			{
				target.setPosition(pos.x, pos.y, pos.z);
				target.motionX = 0.0D;
				target.motionY = 0.0D;
				target.motionZ = 0.0D;

				PacketDispatcher.sendPacketToAllPlayers(PacketHandler.getPacketCustomAnimation(target, 0));
				event.setCanceled(true);
			}
			else
			{
				bindEntityMap.remove(target.entityId);
				this.ticks = 0;
				event.setCanceled(false);
			}
		}
	}

}