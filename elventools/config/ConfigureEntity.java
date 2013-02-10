package rgn.mods.elventools.config;

import rgn.mods.elventools.ElvenTools;
import rgn.mods.elventools.entity.EntityEbonyBoat;
import rgn.mods.elventools.entity.EntityRopeArrow;
import rgn.mods.elventools.entity.EntityTorchArrow;
import cpw.mods.fml.common.registry.EntityRegistry;

public class ConfigureEntity
{
	public static void init()
	{
		EntityRegistry.registerModEntity(EntityEbonyBoat.class,  "EbonyBoat",  0, ElvenTools.instance, 250, 5, true);
		EntityRegistry.registerModEntity(EntityTorchArrow.class, "TorchArrow", 1, ElvenTools.instance, 250, 1, true);
		EntityRegistry.registerModEntity(EntityRopeArrow.class,  "RopeArrow",  2, ElvenTools.instance, 250, 1, true);
	}
}