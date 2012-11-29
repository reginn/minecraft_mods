package rgn.mods.mabicraft.core;

import net.minecraft.src.*;

import rgn.mods.mabicraft.config.*;

public class ItemMabiCraftParts extends Item
{
	public ItemMabiCraftParts(int itemId)
	{
		super(itemId);
		this.setCreativeTab(Config.tabMabiCraft);
	}
	
	public String getTextureFile()
	{
		return "/rgn/sprites/mabicraft/items.png";
	}
}