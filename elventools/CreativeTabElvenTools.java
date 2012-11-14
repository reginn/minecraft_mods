package rgn.mods.elventools;

import net.minecraft.src.*;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

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
        return ElvenItem.itemLeatherLongbow.shiftedIndex;
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public String getTranslatedTabLabel()
	{
		return "ElvenTools";
	}

}