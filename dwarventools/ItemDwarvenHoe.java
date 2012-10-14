package rgn.mods.dwarventools;

import net.minecraft.src.*;

public class ItemDwarvenHoe extends ItemHoe
{
	public ItemDwarvenHoe(int itemId, EnumToolMaterial material)
	{
		super(itemId, material);
	}
	
	@Override
	public String getTextureFile()
	{
		return "/rgn/sprites/dwarventools/items.png";
	}
}
