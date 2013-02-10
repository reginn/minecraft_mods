package rgn.mods.dwarventools.event;

import java.util.Set;
import com.google.common.collect.Sets;

import net.minecraftforge.common.MinecraftForge;

public class ForgeEventRegistry
{
	Set<IForgeEvent> events = Sets.newHashSet();
	
	public ForgeEventRegistry()
	{
		events.add(new SwordDestroyedEvent());
		events.add(new CriticalStrikeEvent());
		events.add(new ExecutionerEvent());
		events.add(new VitalizeEvent());
	}
	
	public void registerEvent()
	{
		for (IForgeEvent event : events)
		{
			MinecraftForge.EVENT_BUS.register(event);
		}
	}

}