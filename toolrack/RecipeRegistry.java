package rgn.mods.toolrack;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import net.minecraftforge.oredict.ShapelessOreRecipe;

import cpw.mods.fml.common.registry.GameRegistry;

public class RecipeRegistry
{
	public void addRecipe()
	{
		String[] dye = new String[]
			{
				"dyeBlack", "dyeRed", "dyeWhite"
			};
			
		for (int i = 0; i < 4; ++i)
		{
			GameRegistry.addRecipe(
				new ItemStack(Toolrack.blockToolrack, 4, i),
					new Object[]
					{
						"PPP", "   ", "PPP",
						Character.valueOf('P'), new ItemStack(Block.woodSingleSlab, 1, i)
					});
					
			for (int j = 0; j < dye.length; ++j)
			{
				GameRegistry.addShapelessRecipe(
					new ItemStack(Toolrack.blockToolrack, 1, i),
						new Object[]
							{
								new ItemStack(Toolrack.blockToolrack, 1, j + 4)
							});
						
				GameRegistry.addRecipe(
					new ShapelessOreRecipe(
						new ItemStack(Toolrack.blockToolrack, 1, j + 4),
							new Object[]
							{
								dye[j], new ItemStack(Toolrack.blockToolrack, 1, i)
							}));
				
				GameRegistry.addRecipe(
					new ShapelessOreRecipe(
						new ItemStack(Toolrack.blockToolrack, 4, j + 4),
							new Object[]
							{
								dye[j],
								new ItemStack(Toolrack.blockToolrack, 1, i), 
								new ItemStack(Toolrack.blockToolrack, 1, i),
								new ItemStack(Toolrack.blockToolrack, 1, i),
								new ItemStack(Toolrack.blockToolrack, 1, i)
							}));
				
				GameRegistry.addRecipe(
					new ShapelessOreRecipe(
						new ItemStack(Toolrack.blockToolrack, 8, j + 4),
							new Object[]
							{
								dye[j],
								new ItemStack(Toolrack.blockToolrack, 1, i), 
								new ItemStack(Toolrack.blockToolrack, 1, i),
								new ItemStack(Toolrack.blockToolrack, 1, i),
								new ItemStack(Toolrack.blockToolrack, 1, i),
								new ItemStack(Toolrack.blockToolrack, 1, i), 
								new ItemStack(Toolrack.blockToolrack, 1, i),
								new ItemStack(Toolrack.blockToolrack, 1, i),
								new ItemStack(Toolrack.blockToolrack, 1, i)
							}));
			}
		}
	}
}