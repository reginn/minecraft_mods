package rgn.mods.mabicraft.entity.monster;

import net.minecraft.world.World;

public class EntityRedSpider extends EntityMabiSpiderBase
{
	public EntityRedSpider(World world)
	{
		super(world);
		this.texture = "/rgn/sprites/MabiCraft/entity/mob/red_spider.png";
	}

	@Override
	public int getMaxHealth()
	{
		return 20;
	}
}
