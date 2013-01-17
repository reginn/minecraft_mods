package rgn.mods.mabicraft.core;

import net.minecraft.item.Item;
import rgn.mods.mabicraft.config.Config;

public class ItemHerb extends Item
{
	public ItemHerb(int itemId, EnumHerbType enumHerbType)
	{
		super(itemId);
		this.setPotionEffect(enumHerbType.getPotionEffect());
		this.setCreativeTab(Config.tabMabiCraft);
	}

	public String getTextureFile()
	{
		return "/rgn/sprites/mabicraft/items.png";
	}
}