package rgn.mods.dwarventools.item;

import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemHoe;

import rgn.mods.dwarventools.config.Config;

public class ItemDwarvenHoe extends ItemHoe
{
	public ItemDwarvenHoe(int itemId, EnumToolMaterial material)
	{
		super(itemId, material);
		this.setCreativeTab(Config.tabDwarvenTools);
	}

	@Override
	public String getTextureFile()
	{
		return "/rgn/sprites/dwarventools/items.png";
	}
}
