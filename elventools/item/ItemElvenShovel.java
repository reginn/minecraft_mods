package rgn.mods.elventools.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemElvenShovel extends ItemSpade
{
	public ItemElvenShovel(int itemId, EnumToolMaterial material)
	{
		super(itemId, material);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateIcons(IconRegister par1IconRegister)
	{
		this.iconIndex = par1IconRegister.registerIcon("rgn/elventools:mithrilShovel");
	}
	@Override
	public void onCreated(ItemStack itemstack, World world, EntityPlayer entityplayer)
	{
		super.onCreated(itemstack, world, entityplayer);
		if (itemstack.itemID == ElvenItem.itemElvenShovelMithril.itemID)
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

		if (itemstack.itemID == ElvenItem.itemElvenShovelMithril.itemID)
		{
			itemstack.addEnchantment(Enchantment.efficiency, 2);
		}
	}
}
