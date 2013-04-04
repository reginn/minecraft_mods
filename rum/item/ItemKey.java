package rgn.mods.rum.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemKey extends Item
{
	@SideOnly(Side.CLIENT)
	private Icon unbreakKeyIcon;

	public ItemKey(int itemId)
	{
		super(itemId);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateIcons(IconRegister par1IconRegister)
	{
		this.iconIndex      = par1IconRegister.registerIcon("rgn/rum:key1");
		this.unbreakKeyIcon = par1IconRegister.registerIcon("rgn/rum:key2");
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void getSubItems(int itemID, CreativeTabs creativeTabs, List list)
	{
		for (int i = 0; i < 2; i++)
		{
			list.add(new ItemStack(itemID, 1, i));
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int damage)
	{
		return damage == 0 ? this.iconIndex : this.unbreakKeyIcon;
	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack)
	{
		String type = par1ItemStack.getItemDamage() == 0 ? "key1" : "key2";
		return (new StringBuilder()).append(this.getUnlocalizedName()).append(".").append(type).toString();
	}

}
