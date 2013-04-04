package rgn.mods.mabicraft.entity.monster;

import net.minecraft.world.World;

public class EntityWhiteWolf extends EntityMabiWolfBase
{
	public EntityWhiteWolf(World world)
	{
		super(world);
		this.texture = "/rgn/sprites/MabiCraft/entity/mob/white_wolf.png";
	}

	@Override
	public int getMaxHealth()
	{
		return 24;
	}

}
