package rgn.mods.elventools.event;

import java.util.Set;
import com.google.common.collect.Sets;

import net.minecraftforge.common.MinecraftForge;

public class ForgeEventRegistry
{
	Set<IForgeEvent> events = Sets.newHashSet();
	
	public ForgeEventRegistry()
	{
		events.add(new EbonyTreeEvent());
		events.add(new ElvenArrowEvent());
		events.add(new BindEvent());
		events.add(new SlowFallEvent());
	}
	
	public void registerEvent()
	{
		for (IForgeEvent event : events)
		{
			MinecraftForge.EVENT_BUS.register(event);
		}
	}

}