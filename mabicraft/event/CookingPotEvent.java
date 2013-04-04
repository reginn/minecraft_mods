package rgn.mods.mabicraft.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import cpw.mods.fml.common.network.PacketDispatcher;

import rgn.mods.mabicraft.block.MabiCraftBlock;
import rgn.mods.mabicraft.item.MabiCraftItem;
import rgn.mods.mabicraft.network.PacketHandler;

public class CookingPotEvent implements IForgeEvent
{
	@ForgeSubscribe
	public void openCookingPotGui(PlayerInteractEvent event)
	{
		if (event.action == PlayerInteractEvent.Action.RIGHT_CLICK_AIR)
		{
			return ;
		}
		EntityPlayer player = event.entityPlayer;
		World         world = player.worldObj;

		int blockId = world.getBlockId(event.x, event.y, event.z);
		ItemStack currentEquippedItem = player.getCurrentEquippedItem();

		if (currentEquippedItem != null &&
			currentEquippedItem.itemID == MabiCraftItem.itemCookingPot.itemID &&
			blockId == MabiCraftBlock.blockBonfire.blockID)
		{
			PacketDispatcher.sendPacketToServer(PacketHandler.getOpenGuiPacket(0, event.x, event.y, event.z));
			event.setCanceled(true);
		}
	}
}
