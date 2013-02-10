package rgn.mods.dwarventools.event;

import cpw.mods.fml.common.network.PacketDispatcher;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import rgn.mods.dwarventools.enchantment.DwarvenEnchantment;
import rgn.mods.dwarventools.enchantment.UniqueEnchantmentHelper;
import rgn.mods.dwarventools.network.PacketHandler;

public class VitalizeEvent implements IForgeEvent
{
	private final Enchantment vitalize = DwarvenEnchantment.enchantmentVitalize;
	@ForgeSubscribe
	public void doVitalize(LivingHurtEvent event)
	{
		Entity       entity = event.entity;
		DamageSource source = event.source;
		int         ammount = event.ammount;
		World         world = entity.worldObj; 

		if (entity instanceof EntityPlayer && source.getEntity() != null)
		{
			EntityPlayer player = (EntityPlayer)entity;
			ItemStack torso = player.getCurrentArmor(2);

			if (torso != null && UniqueEnchantmentHelper.isItemUniqueEnchanted(torso, this.vitalize))
			{
				int enchLv = UniqueEnchantmentHelper.getUniqueEnchantmentLv(torso, this.vitalize);
				enchLv = enchLv > this.vitalize.getMaxLevel() ? this.vitalize.getMaxLevel() : enchLv;
				
				if (player.getHealth() <= player.getMaxHealth() / 2)
				{
					if (world.rand.nextInt(100) < 35 - (enchLv * 5))
					{	
						if (!world.isRemote)
						{
							PacketDispatcher.sendPacketToAllPlayers(PacketHandler.getPacketCustomAnimation(player, 2));

							player.addChatMessage("Vitalize!");
							player.heal(ammount * (enchLv + 1));
						}
					}
				}
			}
		}
	}
}