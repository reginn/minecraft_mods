package rgn.mods.mabicraft.entity;

import cpw.mods.fml.common.registry.EntityRegistry;

import rgn.mods.mabicraft.MabiCraft;
import rgn.mods.mabicraft.entity.monster.EntityGrayWolf;
import rgn.mods.mabicraft.entity.monster.EntityRedSpider;
import rgn.mods.mabicraft.entity.monster.EntityWhiteSpider;
import rgn.mods.mabicraft.entity.projectile.EntityMabiFishHook;

public class MabiCraftEntity
{
	public static void configure()
	{
		EntityRegistry.registerModEntity(EntityMabiFishHook.class, "MabiFishHook",    0, MabiCraft.instance, 250, 1, true);
		EntityRegistry.registerModEntity(EntityWhiteSpider.class,  "MabiWhiteSpider", 1, MabiCraft.instance, 250, 1, true);
		EntityRegistry.registerModEntity(EntityRedSpider.class,    "MabiRedSpider",   2, MabiCraft.instance, 250, 1, true);
		EntityRegistry.registerModEntity(EntityGrayWolf.class,     "MabiGrayWolf",    3, MabiCraft.instance, 250, 1, true);

		//EntityRegistry.addSpawn(EntityMabiSkeleton.class, 30, 1, 4, EnumCreatureType.monster, BiomeGenBase.plains);
		//EntityRegistry.addSpawn(EntityWhiteSpider.class, 1, 1, 4, EnumCreatureType.monster, BiomeGenBase.forest);
		//EntityRegistry.addSpawn(EntityRedSpider.class,   1, 1, 1, EnumCreatureType.monster, BiomeGenBase.forest);
		//EntityRegistry.addSpawn(EntityGrayWolf.class,    20, 1, 1, EnumCreatureType.monster, BiomeGenBase.plains);
	}
}
