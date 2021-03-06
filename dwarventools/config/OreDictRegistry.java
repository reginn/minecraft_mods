package rgn.mods.dwarventools.config;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

import net.minecraftforge.oredict.OreDictionary;

import rgn.mods.dwarventools.block.DwarvenBlock;
import rgn.mods.dwarventools.item.DwarvenItem;

public class OreDictRegistry
{
	public void register()
	{
		OreDictionary.registerOre("oreMithril", new ItemStack(DwarvenBlock.blockDwarvenOre, 1, 0));
		OreDictionary.registerOre("oreEbony",   new ItemStack(DwarvenBlock.blockDwarvenOre, 1, 1));
		OreDictionary.registerOre("ingotMithril", DwarvenItem.itemMithrilIngot);
		OreDictionary.registerOre("ingotEbony",   DwarvenItem.itemEbonyIngot);

		FurnaceRecipes.smelting().addSmelting(DwarvenBlock.blockDwarvenOre.blockID, 0, new ItemStack(DwarvenItem.itemMithrilIngot), 1.0F);
		FurnaceRecipes.smelting().addSmelting(DwarvenBlock.blockDwarvenOre.blockID, 1, new ItemStack(DwarvenItem.itemEbonyIngot), 1.0F);
	}
}