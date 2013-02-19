package rgn.mods.mabicraft.item.cooking;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class Ingredient
{
	private List<ItemStack> items = Lists.newArrayList();
	private int ratio;

	public Ingredient(ItemStack _itemstack, int _ratio)
	{
		this.items.add(_itemstack);
		this.ratio = _ratio;
	}

	public Ingredient(String name, int _ratio)
	{
		this.items = OreDictionary.getOres(name);
		this.ratio = _ratio;
	}

	public List<ItemStack> getItems()
	{
		return this.items;
	}

	public int getRatio()
	{
		return ratio;
	}
}