package rgn.mods.mabicraft.registry;

import net.minecraft.src.*;

import cpw.mods.fml.common.registry.GameRegistry;

import rgn.mods.mabicraft.config.*;

public final class RecipeRegistry
{
	public void addRecipe()
	{
		GameRegistry.addRecipe(
			new ItemStack(MabiCraftItem.itemBonfireKit, 1),
				new Object[]
				{
					" F ", "LLL",
					Character.valueOf('F'), Item.flintAndSteel,
					Character.valueOf('L'), Block.wood
				});

		GameRegistry.addRecipe(
			new ItemStack(MabiCraftBlock.blockEnchanter, 1),
				new Object[]
				{
					"DRD", "BBB", "WWW",
					Character.valueOf('D'), Item.diamond,
					Character.valueOf('R'), new ItemStack(Block.cloth, 1, 14),
					Character.valueOf('B'), Item.book,
					Character.valueOf('W'), Block.planks
				});
	}
	
}