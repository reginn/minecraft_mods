package rgn.mods.dwarventools.event;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

import cpw.mods.fml.common.network.PacketDispatcher;

import rgn.mods.dwarventools.enchantment.DwarvenEnchantment;
import rgn.mods.dwarventools.enchantment.UniqueEnchantmentHelper;
import rgn.mods.dwarventools.network.PacketHandler;

public class LifeStealEvent implements IForgeEvent
{
	private final float[]     absorbRatio = new float[]{0.1F, 0.15F, 0.2F};
	private final Enchantment   lifeSteal = DwarvenEnchantment.enchantmentLifeSteal;

	@ForgeSubscribe
	public void doLifeSteal(LivingDeathEvent event)
	{
		Entity entity = event.source.getEntity();
		Entity target = event.entityLiving;

		if (entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)entity;

			ItemStack equippedItem = player.getCurrentEquippedItem();
			World            world = player.worldObj;

			if (equippedItem != null && UniqueEnchantmentHelper.isItemUniqueEnchanted(equippedItem, this.lifeSteal))
			{
				if (target instanceof EntityLiving)
				{
					EntityLiving entityLiving = (EntityLiving)target;

					int enchLv = UniqueEnchantmentHelper.getUniqueEnchantmentLv(equippedItem, this.lifeSteal);

					enchLv = enchLv > this.lifeSteal.getMaxLevel() ? this.lifeSteal.getMaxLevel() : enchLv;

					int absorbAmount = (int)(entityLiving.func_110138_aP() * this.absorbRatio[enchLv - 1]);

					if (!world.isRemote)
					{
						PacketDispatcher.sendPacketToAllInDimension(PacketHandler.getPacketCustomAnimation(player, 3), player.dimension);
						player.addChatMessage("Life Steal!");
						player.heal(absorbAmount);
					}
				}
			}
		}
	}
}
