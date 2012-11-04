package rgn.mods.elventools;

import net.minecraft.src.*;

public class ItemRopeEstablisher extends Item
{
	public ItemRopeEstablisher(int itemId)
	{
		super(itemId);
		this.setCreativeTab(CreativeTabs.tabTools);
	}
	
	@Override
	public String getTextureFile()
	{
		return "/rgn/sprites/elventools/items.png";
	}
	
	@Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int playerDir, float par8, float par9, float par10)
	{
		if (playerDir == 0 && isEstablish(world, x, y, z))
		{
			world.setBlockWithNotify(x, y - 1, z, ElvenBlock.blockRopeEstablisher.blockID);
			--itemstack.stackSize;
			return true;
		}
		return false;
    }
	
	private boolean isEstablish(World world, int x, int y, int z)
	{
		Material material = world.getBlockMaterial(x, y, z);
		return material.isSolid();
	}
	
}