package rgn.mods.elventools.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import rgn.mods.elventools.item.ElvenItem;

public class EntityTorchArrow extends EntityElvenArrow
{
	public EntityTorchArrow(World world)
	{
		super(world);
	}

	public EntityTorchArrow(World world, double x, double y, double z)
	{
		super(world, x, y, z);
	}

	public EntityTorchArrow(World world, EntityLivingBase player, float par3)
	{
		super(world, player, par3);
	}

	protected boolean addItemStackToInventory(EntityPlayer player)
	{
		return player.inventory.addItemStackToInventory(new ItemStack(ElvenItem.itemTorchArrow, 1));
	}

	protected boolean tryPlaceBlock()
	{
		int side = this.mop.sideHit;
		Block block = Block.torchWood;

		int x = this.xTile;
		int y = this.yTile;
		int z = this.zTile;
		int meta = 0;

		switch (side)
		{
			case 0 :
				break;

			case 1 :
				meta = 0;
				y++;
				break;

			case 2 :
				z--;
				meta = 4;
				break;

			case 3 :
				z++;
				meta = 3;
				break;

			case 4 :
				x--;
				meta = 2;
				break;

			case 5 :
				x++;
				meta = 1;
				break;
		}

		if (block.canPlaceBlockAt(this.worldObj, x, y, z) && this.worldObj.isAirBlock(x, y, z))
		{
			this.worldObj.setBlock(x, y, z, Block.torchWood.blockID, meta, 3);
			return true;
		}

		return false;
	}
}