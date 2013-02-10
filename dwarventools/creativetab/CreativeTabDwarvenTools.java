package rgn.mods.dwarventools.creativetab;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.creativetab.CreativeTabs;

import rgn.mods.dwarventools.block.DwarvenBlock;

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