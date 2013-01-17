package rgn.mods.dwarventools.core;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;

public class DwarvenEventHooks
{
	@ForgeSubscribe
	public void swordDestroyed(PlayerDestroyItemEvent event)
	{
		EntityPlayer player = event.entityPlayer;
		ItemStack itemstack = event.original;
		World         world = player.worldObj;

		if (event.isCancelable())
		{
			event.setCanceled(true);
		}

		ItemStack dropItem = null;
		if (itemstack.itemID == DwarvenItem.itemDwarvenSwordMithril.itemID)
		{
			dropItem = new ItemStack(DwarvenItem.itemDwarvenBrokenSwordMithril, 1);
		}
		else if(itemstack.itemID == DwarvenItem.itemDwarvenSwordEbony.itemID)
		{
			dropItem = new ItemStack(DwarvenItem.itemDwarvenBrokenSwordEbony, 1);
		}

		if (dropItem != null)
		{
			if (!world.isRemote)
			{
				world.spawnEntityInWorld(new EntityItem(world, player.posX, player.posY, player.posZ, dropItem));
			}
		}
	}
}
