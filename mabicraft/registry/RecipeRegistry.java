package rgn.mods.mabicraft.registry;

import net.minecraft.src.*;

import net.minecraftforge.oredict.ShapedOreRecipe;

import cpw.mods.fml.common.registry.GameRegistry;

import rgn.mods.mabicraft.config.*;
import rgn.mods.mabicraft.core.*;

public final class RecipeRegistry
{
	public void addRecipe()
	{
		GameRegistry.addRecipe(
			new ShapedOreRecipe(
				new ItemStack(MabiCraftItem.itemBonfireKit, 1),
					new Object[]
					{
						" F ", "LLL",
						Character.valueOf('F'), Item.flintAndSteel,
						Character.valueOf('L'), "logWood"
					}));

		GameRegistry.addRecipe(
			new ShapedOreRecipe(
				new ItemStack(MabiCraftBlock.blockEnchanter, 1),
					new Object[]
					{
						"DRD", "BBB", "WWW",
						Character.valueOf('D'), Item.diamond,
						Character.valueOf('R'), new ItemStack(Block.cloth, 1, 14),
						Character.valueOf('B'), Item.book,
						Character.valueOf('W'), "plankWood"
					}));
		
		GameRegistry.addRecipe(
			new ShapedOreRecipe(
				new ItemStack(MabiCraftBlock.blockCookware, 1, 0),
					new Object[]
					{
						"WWW", "I I",
						Character.valueOf('W'), "plankWood",
						Character.valueOf('I'), Item.ingotIron
					}));
	}
	
}