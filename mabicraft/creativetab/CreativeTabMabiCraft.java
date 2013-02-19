package rgn.mods.mabicraft.creativetab;

import net.minecraft.creativetab.CreativeTabs;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import rgn.mods.mabicraft.item.MabiCraftItem;

public class CreativeTabMabiCraft extends CreativeTabs
{
	public CreativeTabMabiCraft(String type)
	{
		super(type);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getTabIconItemIndex()
	{
		return MabiCraftItem.itemManaHerb.itemID;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getTranslatedTabLabel()
	{
		return "MabiCraft";
	}
}