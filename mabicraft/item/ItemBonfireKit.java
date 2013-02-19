package rgn.mods.mabicraft.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import rgn.mods.mabicraft.block.MabiCraftBlock;

public class ItemBonfireKit extends Item
{
	public ItemBonfireKit(int itemId)
	{
		super(itemId);
	}

	@Override
	public String getTextureFile()
	{
		return "/rgn/sprites/mabicraft/items.png";
	}

	@Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int playerDir, float par8, float par9, float par10)
	{
		if (playerDir == 1 && MabiCraftBlock.blockBonfire.canPlaceBlockAt(world, x, y, z))
		{
			world.setBlockWithNotify(x, y + 1, z, MabiCraftBlock.blockBonfire.blockID);
			--itemstack.stackSize;
			return true;
		}
		return false;
    }
}