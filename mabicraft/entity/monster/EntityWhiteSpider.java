package rgn.mods.mabicraft.entity.monster;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class EntityWhiteSpider extends EntityMabiSpiderBase
{
	public EntityWhiteSpider(World world)
	{
		super(world);
		this.texture = "/rgn/sprites/MabiCraft/entity/mob/white_spider.png";
	}

	@Override
	public int getMaxHealth()
	{
		return 12;
	}

	@Override
	protected Entity findPlayerToAttack()
	{
		return null;
	}

}
