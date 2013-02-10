package rgn.mods.dwarventools.event;

import cpw.mods.fml.common.network.PacketDispatcher;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.AttackEntityEvent;

import rgn.mods.dwarventools.enchantment.DwarvenEnchantment;
import rgn.mods.dwarventools.enchantment.UniqueEnchantmentHelper;
import rgn.mods.dwarventools.network.PacketHandler;

public class CriticalStrikeEvent implements IForgeEvent
{
	private final int[]           prob = new int[]{30, 20, 10};
	private final float[]  damageRatio = new float[]{1.5F, 2.5F, 3.5F};
	private final Enchantment critical = DwarvenEnchantment.enchantmentCriticalStrike;
	
	@ForgeSubscribe
	public void doCriticalStrike(AttackEntityEvent event)
	{
		EntityPlayer    player = event.entityPlayer;
		Entity          target = event.target;
		ItemStack equippedItem = player.getCurrentEquippedItem();
		World            world = player.worldObj;

		if (equippedItem != null && UniqueEnchantmentHelper.isItemUniqueEnchanted(equippedItem, this.critical))
		{
			if (target instanceof EntityLiving)
			{
				EntityLiving entityLiving = (EntityLiving)target;

				int enchLv = UniqueEnchantmentHelper.getUniqueEnchantmentLv(equippedItem, this.critical);
				float baseDamage = equippedItem.getItem().getDamageVsEntity(target);

				enchLv = enchLv > this.critical.getMaxLevel() ? this.critical.getMaxLevel() : enchLv;
				
				if (world.rand.nextInt(100) <= prob[enchLv - 1])
				{
					if (!world.isRemote)
					{
						PacketDispatcher.sendPacketToAllPlayers(PacketHandler.getPacketCustomAnimation(target, 0));
						player.addChatMessage("Critical Strike!");
						target.attackEntityFrom(DamageSource.causePlayerDamage(player), (int)(baseDamage * damageRatio[enchLv - 1]));
					}
				}
			}
		}
	}
}
