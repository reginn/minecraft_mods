package rgn.mods.elventools.item;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import rgn.mods.elventools.core.ElvenItem;
import rgn.mods.elventools.config.Config;

public class ItemElvenShovel extends ItemSpade
{
	public ItemElvenShovel(int itemId, EnumToolMaterial material)
	{
		super(itemId, material);
		this.setCreativeTab(Config.tabElvenTools);
	}

	@Override
	public String getTextureFile()
	{
		return "/rgn/sprites/elventools/items.png";
	}

	@Override
	public void onCreated(ItemStack itemstack, World world, EntityPlayer entityplayer)
	{
		super.onCreated(itemstack, world, entityplayer);
		if (itemstack.itemID == ElvenItem.itemElvenShovelMithril.shiftedIndex)
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

		if (itemstack.itemID == ElvenItem.itemElvenShovelMithril.shiftedIndex)
		{
			itemstack.addEnchantment(Enchantment.efficiency, 2);
		}
	}
}
