package rgn.mods.elventools.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import rgn.mods.elventools.core.ElvenItem;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

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