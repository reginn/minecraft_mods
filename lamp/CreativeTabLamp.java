package rgn.mods.lamp;

import net.minecraft.src.*;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

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