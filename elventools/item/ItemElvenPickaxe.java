package rgn.mods.elventools.item;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemElvenPickaxe extends ItemPickaxe
{
	public ItemElvenPickaxe(int itemId, EnumToolMaterial material)
	{
		super(itemId, material);
	}

	@Override
	public void onCreated(ItemStack itemstack, World world, EntityPlayer entityplayer)
	{
		super.onCreated(itemstack, world, entityplayer);
		if (itemstack.itemID == ElvenItem.itemElvenPickaxeMithril.itemID)
		{
			itemstack.addEnchantment(Enchantment.efficiency, 2);
		}
	}

	@Override
	public void onUpdate(ItemStack itemstack, World world, Entity entity, int par4, boolean par5)
	{
		super.onUpdate(itemstack, world, entity, par4, par5);

		if (itemstack.isItemEnchanted())
		{
			return ;
		}

		if (itemstack.itemID == ElvenItem.itemElvenPickaxeMithril.itemID)
		{
			itemstack.addEnchantment(Enchantment.efficiency, 2);
		}
	}
}
