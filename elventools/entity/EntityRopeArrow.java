package rgn.mods.elventools.entity;

import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import rgn.mods.elventools.block.ElvenBlock;
import rgn.mods.elventools.item.ElvenItem;

public class EntityRopeArrow extends EntityElvenArrow
{
	public EntityRopeArrow(World world)
	{
		super(world);
	}

	public EntityRopeArrow(World world, double x, double y, double z)
	{
		super(world, x, y, z);
	}

	public EntityRopeArrow(World world, EntityLivingBase player, float par3)
	{
		super(world, player, par3);
	}

	@Override
	protected boolean addItemStackToInventory(EntityPlayer player)
	{
		return player.inventory.addItemStackToInventory(new ItemStack(ElvenItem.itemRopeArrow, 1));
	}

	@Override
	protected boolean tryPlaceBlock()
	{
		int x = this.xTile;
		int y = this.yTile;
		int z = this.zTile;

		if (this.mop.sideHit == 0 && this.worldObj.isAirBlock(x, y - 1, z) && this.isEstablish(x, y, z))
		{
			this.worldObj.setBlock(x, y - 1, z, ElvenBlock.blockRopeEstablisher.blockID);
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