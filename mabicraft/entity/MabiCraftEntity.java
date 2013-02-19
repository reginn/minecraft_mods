package rgn.mods.mabicraft.entity;

import cpw.mods.fml.common.registry.EntityRegistry;

import rgn.mods.mabicraft.MabiCraft;
import rgn.mods.mabicraft.entity.projectile.EntityMabiFishHook;

public class MabiCraftEntity
{
	public static void configure()
	{
		EntityRegistry.registerModEntity(EntityMabiFishHook.class, "MabiFishHook", 0, MabiCraft.instance, 250, 1, true);
	}
}
