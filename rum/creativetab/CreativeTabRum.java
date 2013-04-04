package rgn.mods.rum.creativetab;

import net.minecraft.creativetab.CreativeTabs;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import rgn.mods.rum.block.RumBlock;

public class CreativeTabRum extends CreativeTabs
{
	public CreativeTabRum()
	{
		super("rum");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getTabIconItemIndex()
	{
		return RumBlock.blockLockedChest.blockID;
	}
}
