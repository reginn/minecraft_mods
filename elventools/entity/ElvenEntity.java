package rgn.mods.elventools.entity;

import cpw.mods.fml.common.registry.EntityRegistry;

import rgn.mods.elventools.ElvenTools;

public class ElvenEntity
{
	public static void configure()
	{
		EntityRegistry.registerModEntity(EntityEbonyBoat.class,  "EbonyBoat",  0, ElvenTools.instance, 250, 5, true);
		EntityRegistry.registerModEntity(EntityTorchArrow.class, "TorchArrow", 1, ElvenTools.instance, 250, 1, true);
		EntityRegistry.registerModEntity(EntityRopeArrow.class,  "RopeArrow",  2, ElvenTools.instance, 250, 1, true);
	}
}
