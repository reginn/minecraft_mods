package rgn.mods.elventools.item;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import rgn.mods.elventools.config.Config;
import rgn.mods.elventools.block.ElvenBlock;

public class ItemRopeEstablisher extends Item
{
	public ItemRopeEstablisher(int itemId)
	{
		super(itemId);
		this.setCreativeTab(Config.tabElvenTools);
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