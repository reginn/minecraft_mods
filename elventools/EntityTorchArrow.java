package rgn.mods.elventools;

import java.util.Random;
import java.util.List;

import net.minecraft.src.*;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

public class EntityTorchArrow extends EntityArrowBase
{
	public EntityTorchArrow(World world)
	{
		super(world);
	}
	
	public EntityTorchArrow(World world, double x, double y, double z)
	{
		super(world, x, y, z);
	}
	
	public EntityTorchArrow(World world, EntityLiving par2EntityLiving, EntityLiving par3EntityLiving, float par4, float par5)
	{
		super(world, par2EntityLiving, par3EntityLiving, par4, par5);
	}
	
	public EntityTorchArrow(World world, EntityLiving player, float par3)
	{
		super(world, player, par3);
	}
	
	public boolean addItemStackToInventory(EntityPlayer player)
	{
		return player.inventory.addItemStackToInventory(new ItemStack(ElvenItem.itemTorchArrow, 1));
	}
	
	public boolean tryPlaceBlock()
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
			this.worldObj.setBlock(x, y, z, 50);
			this.worldObj.setBlockMetadataWithNotify(x, y, z, meta);
			return true;
		}
		
		return false;
	}
}