package rgn.mods.mabicraft.item;

import net.minecraft.item.Item;

public class ItemHerb extends Item
{
	public ItemHerb(int itemId, EnumHerbType enumHerbType)
	{
		super(itemId);
		this.setPotionEffect(enumHerbType.getPotionEffect());
	}

	public String getTextureFile()
	{
		return "/rgn/sprites/mabicraft/items.png";
	}
}