package rgn.mods.mabicraft;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

import net.minecraft.src.*;
import net.minecraftforge.common.*;

public class ItemHerb extends Item
{
	public ItemHerb(int itemId, EnumHerbType enumHerbType)
	{
		super(itemId);
		this.setPotionEffect(enumHerbType.getPotionEffect());
		this.setTabToDisplayOn(CreativeTabs.tabMaterials);
	}
	
	public String getTextureFile()
	{
		return "/rgn/sprites/mabicraft/items.png";
	}
}