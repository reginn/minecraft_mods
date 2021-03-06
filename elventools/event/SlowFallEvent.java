package rgn.mods.elventools.event;

import java.util.Set;

import com.google.common.collect.Sets;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;

import cpw.mods.fml.common.network.PacketDispatcher;

import rgn.mods.elventools.item.ElvenItem;
import rgn.mods.elventools.network.PacketHandler;

public class SlowFallEvent implements IForgeEvent
{
	Set<Integer> slowFallEntitySet = Sets.newHashSet();

	@ForgeSubscribe
	public void doSlowFall(LivingEvent.LivingUpdateEvent event)
	{
		EntityLivingBase entityLiving = event.entityLiving;

		if (entityLiving instanceof EntityPlayer)
		{
			EntityPlayer    player = (EntityPlayer)entityLiving;
			ItemStack equippedItem = player.getCurrentEquippedItem();

			if (equippedItem != null && equippedItem.getItem().itemID == ElvenItem.itemFeatherBow.itemID)
			{
				if (player.isInWater())
				{
					return ;
				}
				if (!player.onGround && player.motionY < 0.0D)
				{
					player.motionY *= 0.6D;
					PacketDispatcher.sendPacketToAllInDimension(PacketHandler.getPacketCustomAnimation(player, 1), player.dimension);
					this.slowFallEntitySet.add(player.entityId);
				}
			}
		}
	}

	@ForgeSubscribe
	public void canceledDamage(LivingFallEvent event)
	{
		EntityLivingBase entityLiving = event.entityLiving;

		if (this.slowFallEntitySet.contains(entityLiving.entityId))
		{
			this.slowFallEntitySet.remove(entityLiving.entityId);
			event.setCanceled(true);
		}
	}
}