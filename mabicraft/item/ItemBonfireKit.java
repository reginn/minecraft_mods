package rgn.mods.mabicraft.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import rgn.mods.mabicraft.block.MabiCraftBlock;

public class ItemBonfireKit extends Item
{
	public ItemBonfireKit(int itemId)
	{
		super(itemId);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateIcons(IconRegister par1IconRegister)
	{
		this.iconIndex = par1IconRegister.registerIcon("rgn/mabicraft:CampFireKit");
	}

	@Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int playerDir, float par8, float par9, float par10)
	{
		if (playerDir == 1 && MabiCraftBlock.blockBonfire.canPlaceBlockAt(world, x, y, z))
		{
			world.setBlock(x, y + 1, z, MabiCraftBlock.blockBonfire.blockID, 0, 2);
			--itemstack.stackSize;
			return true;
		}
		return false;
    }
}