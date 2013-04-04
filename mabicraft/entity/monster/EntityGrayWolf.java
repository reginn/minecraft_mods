package rgn.mods.mabicraft.entity.monster;

import net.minecraft.world.World;

public class EntityGrayWolf extends EntityMabiWolfBase
{
	public EntityGrayWolf(World world)
	{
		super(world);
		this.texture = "/rgn/sprites/MabiCraft/entity/mob/gray_wolf.png";
	}

	@Override
	public int getMaxHealth()
	{
		return 16;
	}

}
