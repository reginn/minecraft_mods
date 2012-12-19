package rgn.mods.lamp;

import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CreativeTabLamp extends CreativeTabs
{
	public CreativeTabLamp(String type)
	{
		super(type);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getTabIconItemIndex()
    {
        return Lamp.blockLamp.blockID;
    }

	@Override
	@SideOnly(Side.CLIENT)
	public String getTranslatedTabLabel()
	{
		return "Lamp";
	}
}