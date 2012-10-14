package rgn.mods.mabicraft;

import net.minecraft.src.*;
import net.minecraftforge.common.*;

public class ItemBonfireKit extends Item
{
	public ItemBonfireKit(int itemId)
	{
		super(itemId);
		this.setTabToDisplayOn(CreativeTabs.tabTools);
	}
	
	@Override
	public String getTextureFile()
	{
		return "/rgn/sprites/mabicraft/items.png";
	}
	
	@Override
	public boolean tryPlaceIntoWorld(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int playerDir, float par8, float par9, float par10)
	{
		if (playerDir == 1 && MabiCraft.blockBonfire.canPlaceBlockAt(world, x, y, z))
		{
			world.setBlockWithNotify(x, y + 1, z, MabiCraft.blockBonfire.blockID);
			--itemstack.stackSize;
			return true;
		}
		return false;
    }
}