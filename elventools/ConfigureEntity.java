package rgn.mods.elventools;

import net.minecraft.src.*;

import cpw.mods.fml.common.registry.EntityRegistry;

public class ConfigureEntity
{
	public static void init()
	{
		EntityRegistry.registerModEntity(EntityEbonyBoat.class,  "EbonyBoat",  0, ElvenTools.instance, 250, 5, true);
		EntityRegistry.registerModEntity(EntityTorchArrow.class, "TorchArrow", 1, ElvenTools.instance, 250, 5, true);
		EntityRegistry.registerModEntity(EntityRopeArrow.class,  "RopeArrow",  2, ElvenTools.instance, 250, 5, true);
	}
}