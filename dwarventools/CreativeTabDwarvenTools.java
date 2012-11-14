package rgn.mods.dwarventools;

import net.minecraft.src.*;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

public class CreativeTabDwarvenTools extends CreativeTabs
{
	public CreativeTabDwarvenTools(String type)
	{
		super(type);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getTabIconItemIndex()
    {
        return DwarvenBlock.blockInfernalFurnace.blockID;
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public String getTranslatedTabLabel()
	{
		return "DwarvenTools";
	}

}