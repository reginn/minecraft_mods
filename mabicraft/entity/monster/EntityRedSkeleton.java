package rgn.mods.mabicraft.entity.monster;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import net.minecraft.world.World;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;

public class EntityRedSkeleton extends EntityMabiSkeleton implements IEntityAdditionalSpawnData
{
	public EntityRedSkeleton(World world)
	{
		super(world);
		this.texture = "/rgn/sprites/MabiCraft/entity/mob/red_skeleton.png";
	}

	@Override
	public void writeSpawnData(ByteArrayDataOutput data)
	{
		try
		{
			data.writeBoolean(this.isRanged);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void readSpawnData(ByteArrayDataInput data)
	{
		try
		{
			this.isRanged = data.readBoolean();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
