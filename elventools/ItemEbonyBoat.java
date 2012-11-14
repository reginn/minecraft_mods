package rgn.mods.elventools;

import java.util.Iterator;
import java.util.List;

import net.minecraft.src.*;

public class ItemEbonyBoat extends Item
{
	public ItemEbonyBoat(int itemId)
	{
		super(itemId);
		this.maxStackSize = 3;
		this.setCreativeTab(Config.tabElvenTools);
	}
	
	@Override
	public String getTextureFile()
	{
		return "/rgn/sprites/elventools/items.png";
	}
	
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		float var4 = 1.0F;
		float var5 = par3EntityPlayer.prevRotationPitch + (par3EntityPlayer.rotationPitch - par3EntityPlayer.prevRotationPitch) * var4;
		float var6 = par3EntityPlayer.prevRotationYaw + (par3EntityPlayer.rotationYaw - par3EntityPlayer.prevRotationYaw) * var4;
		double var7 = par3EntityPlayer.prevPosX + (par3EntityPlayer.posX - par3EntityPlayer.prevPosX) * (double)var4;
		double var9 = par3EntityPlayer.prevPosY + (par3EntityPlayer.posY - par3EntityPlayer.prevPosY) * (double)var4 + 1.62D - (double)par3EntityPlayer.yOffset;
		double var11 = par3EntityPlayer.prevPosZ + (par3EntityPlayer.posZ - par3EntityPlayer.prevPosZ) * (double)var4;
		Vec3 var13 = par2World.getWorldVec3Pool().getVecFromPool(var7, var9, var11);
		float var14 = MathHelper.cos(-var6 * 0.017453292F - (float)Math.PI);
		float var15 = MathHelper.sin(-var6 * 0.017453292F - (float)Math.PI);
		float var16 = -MathHelper.cos(-var5 * 0.017453292F);
		float var17 = MathHelper.sin(-var5 * 0.017453292F);
		float var18 = var15 * var16;
		float var20 = var14 * var16;
		double var21 = 5.0D;
		Vec3 var23 = var13.addVector((double)var18 * var21, (double)var17 * var21, (double)var20 * var21);
		MovingObjectPosition var24 = par2World.rayTraceBlocks_do(var13, var23, true);

		if (var24 == null)
		{
			return par1ItemStack;
		}
		else
		{
			Vec3 var25 = par3EntityPlayer.getLook(var4);
			boolean var26 = false;
			float var27 = 1.0F;
			List var28 = par2World.getEntitiesWithinAABBExcludingEntity(par3EntityPlayer, par3EntityPlayer.boundingBox.addCoord(var25.xCoord * var21, var25.yCoord * var21, var25.zCoord * var21).expand((double)var27, (double)var27, (double)var27));
			Iterator var29 = var28.iterator();
			
			while (var29.hasNext())
			{
				Entity var30 = (Entity)var29.next();
				
				if (var30.canBeCollidedWith())
				{
					float var31 = var30.getCollisionBorderSize();
					AxisAlignedBB var32 = var30.boundingBox.expand((double)var31, (double)var31, (double)var31);
				
					if (var32.isVecInside(var13))
					{
						var26 = true;
					}
				}
			}
			
			if (var26)
			{
				return par1ItemStack;
			}
			else
			{
				if (var24.typeOfHit == EnumMovingObjectType.TILE)
				{
					int var33 = var24.blockX;
					int var34 = var24.blockY;
					int var35 = var24.blockZ;
					
					if (!par2World.isRemote)
					{
						if (par2World.getBlockId(var33, var34, var35) == Block.snow.blockID)
						{
							--var34;
						}
						
						par2World.spawnEntityInWorld(new EntityEbonyBoat(par2World, (double)((float)var33 + 0.5F), (double)((float)var34 + 1.0F), (double)((float)var35 + 0.5F)));
					}
					
					if (!par3EntityPlayer.capabilities.isCreativeMode)
					{
						--par1ItemStack.stackSize;
					}
				}
			
			return par1ItemStack;
			}
		}
	}
}
