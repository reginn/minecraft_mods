package rgn.mods.mabicraft.event;

import java.util.Set;

import com.google.common.collect.Sets;

import net.minecraftforge.common.MinecraftForge;

public class ForgeEventRegistry
{
	Set<IForgeEvent> events = Sets.newHashSet();

	public ForgeEventRegistry()
	{
		events.add(new CookingPotEvent());
		events.add(new EvilScrollDropEvent());
	}

	public void registerAll()
	{
		for(IForgeEvent event : this.events)
		{
			MinecraftForge.EVENT_BUS.register(event);
		}
	}
}
