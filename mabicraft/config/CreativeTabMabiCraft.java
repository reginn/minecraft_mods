package rgn.mods.mabicraft.config;

import net.minecraft.src.*;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

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
		return MabiCraftItem.itemManaHerb.shiftedIndex;
	}
 
	@Override
	@SideOnly(Side.CLIENT)
	public String getTranslatedTabLabel()
	{
		return "MabiCraft";
	}
}