package rgn.mods.mabicraft;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

import net.minecraft.src.*;
import net.minecraftforge.common.*;

public class ItemMabiCraftParts extends Item
{
	public ItemMabiCraftParts(int itemId)
	{
		super(itemId);
		this.setTabToDisplayOn(CreativeTabs.tabMaterials);
	}
	
	public String getTextureFile()
	{
		return "/rgn/sprites/mabicraft/items.png";
	}
}