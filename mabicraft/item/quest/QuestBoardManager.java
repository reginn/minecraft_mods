package rgn.mods.mabicraft.item.quest;

import java.util.Map;

import com.google.common.collect.Maps;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import rgn.mods.mabicraft.registry.EvilScrollRegistry;

public class QuestBoardManager
{
	private Map<Integer, Integer> collectQuests = Maps.newHashMap();

	private static QuestBoardManager INSTANCE = new QuestBoardManager();

	private QuestBoardManager()
	{
		for (Integer metadata : EvilScrollRegistry.instance().getAllMetadata())
		{
			collectQuests.put(
					metadata,
					EvilScrollRegistry.instance().getNumberOfEmeraldFromMetadata(metadata));
		}
	}

	public static QuestBoardManager instance()
	{
		return INSTANCE;
	}

	public static ItemStack findQuest(IInventory inventory, World world)
	{
		ItemStack collect = inventory.getStackInSlot(0);

		if (collect != null && collect.stackSize >= 10)
		{
			int type = collect.getItemDamage();

			if (instance().collectQuests.containsKey(type))
			{
				ItemStack emerald = new ItemStack(Item.emerald, instance().collectQuests.get(type));
				return emerald;
			}
		}
		return null;
	}

}
