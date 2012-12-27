package rgn.mods.elventools.entity;

import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import rgn.mods.elventools.core.ElvenBlock;
import rgn.mods.elventools.core.ElvenItem;

public class EntityRopeArrow extends EntityArrowBase
{
	public EntityRopeArrow(World world)
	{
		super(world);
	}

	public EntityRopeArrow(World world, double x, double y, double z)
	{
		super(world, x, y, z);
	}

	public EntityRopeArrow(World world, EntityLiving par2EntityLiving, EntityLiving par3EntityLiving, float par4, float par5)
	{
		super(world, par2EntityLiving, par3EntityLiving, par4, par5);
	}

	public EntityRopeArrow(World world, EntityLiving player, float par3)
	{
		super(world, player, par3);
	}

	public boolean addItemStackToInventory(EntityPlayer player)
	{
		return player.inventory.addItemStackToInventory(new ItemStack(ElvenItem.itemRopeArrow, 1));
	}

	public boolean tryPlaceBlock()
	{
		int x = this.xTile;
		int y = this.yTile;
		int z = this.zTile;

		if (this.mop.sideHit == 0 && this.worldObj.isAirBlock(x, y - 1, z) && this.isEstablish(x, y, z))
		{
			this.worldObj.setBlockWithNotify(x, y - 1, z, ElvenBlock.blockRopeEstablisher.blockID);
			return true;
		}

		return false;
	}

	private boolean isEstablish(int x, int y, int z)
	{
		Material material = this.worldObj.getBlockMaterial(x, y, z);
		return material.isSolid();
	}
}