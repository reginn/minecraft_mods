package rgn.mods.dwarventools.event;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;

import rgn.mods.dwarventools.item.DwarvenItem;

public class SwordDestroyedEvent implements IForgeEvent
{
	@ForgeSubscribe
	public void onSwordDestroyed(PlayerDestroyItemEvent event)
	{
		EntityPlayer  player = event.entityPlayer;
		ItemStack brokenItem = event.original;
		World          world = player.worldObj;

		ItemStack dropItem = this.getBrokenSwordFromDestoryedItem(brokenItem);

		if (dropItem != null && !world.isRemote)
		{
			world.spawnEntityInWorld(new EntityItem(world, player.posX, player.posY, player.posZ, dropItem));
		}
	}
	
	public ItemStack getBrokenSwordFromDestoryedItem(ItemStack brokenItem)
	{
		Item ret = brokenItem.itemID == DwarvenItem.itemDwarvenSwordMithril.itemID ? DwarvenItem.itemDwarvenBrokenSwordMithril
				 : brokenItem.itemID == DwarvenItem.itemDwarvenSwordEbony.itemID ? DwarvenItem.itemDwarvenBrokenSwordEbony
				 : null;
		
		return ret != null ? new ItemStack(ret, 1) : null;
	}
}