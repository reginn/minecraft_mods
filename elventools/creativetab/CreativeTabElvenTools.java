package rgn.mods.elventools.creativetab;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.creativetab.CreativeTabs;

import rgn.mods.elventools.item.ElvenItem;

public class CreativeTabElvenTools extends CreativeTabs
{
	public CreativeTabElvenTools(String type)
	{
		super(type);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getTabIconItemIndex()
    {
        return ElvenItem.itemLeatherLongbow.itemID;
    }

	@Override
	@SideOnly(Side.CLIENT)
	public String getTranslatedTabLabel()
	{
		return "ElvenTools";
	}

}