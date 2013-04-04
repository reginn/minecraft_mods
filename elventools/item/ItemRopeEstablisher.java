package rgn.mods.elventools.item;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import rgn.mods.elventools.block.ElvenBlock;

public class ItemRopeEstablisher extends Item
{
	public ItemRopeEstablisher(int itemId)
	{
		super(itemId);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateIcons(IconRegister par1IconRegister)
	{
		this.iconIndex = par1IconRegister.registerIcon("rgn/elventools:rope");
	}

	@Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int playerDir, float par8, float par9, float par10)
	{
		if (playerDir == 0 && isEstablish(world, x, y, z))
		{
			world.setBlock(x, y - 1, z, ElvenBlock.blockRopeEstablisher.blockID);
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