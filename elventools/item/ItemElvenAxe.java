package rgn.mods.elventools.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemElvenAxe extends ItemAxe
{
	public ItemElvenAxe(int itemId, EnumToolMaterial material)
	{
		super(itemId, material);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void func_94581_a(IconRegister par1IconRegister)
	{
		this.iconIndex = par1IconRegister.func_94245_a("rgn/elventools:mithrilaxe");
	}

	@Override
	public void onCreated(ItemStack itemstack, World world, EntityPlayer entityplayer)
	{
		super.onCreated(itemstack, world, entityplayer);
		if (itemstack.itemID == ElvenItem.itemElvenAxeMithril.itemID)
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

		if (itemstack.itemID == ElvenItem.itemElvenAxeMithril.itemID)
		{
			itemstack.addEnchantment(Enchantment.efficiency, 2);
		}
	}
}
