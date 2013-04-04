package rgn.mods.mabicraft.entity.monster;

import net.minecraft.world.World;

public class EntityBlackWolf extends EntityMabiWolfBase
{
	public EntityBlackWolf(World world)
	{
		super(world);
		this.texture = "/rgn/sprites/MabiCraft/entity/mob/black_wolf.png";
	}

	@Override
	public int getMaxHealth()
	{
		return 20;
	}

}
