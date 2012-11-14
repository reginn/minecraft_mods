package rgn.mods.ozen;

import net.minecraft.src.*;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

public class CreativeTabOzen extends CreativeTabs
{
	public CreativeTabOzen(String type)
	{
		super(type);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getTabIconItemIndex()
    {
        return Ozen.blockOzen.blockID;
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public String getTranslatedTabLabel()
	{
		return "Ozen";
	}
}