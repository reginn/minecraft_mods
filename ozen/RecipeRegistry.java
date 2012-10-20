package rgn.mods.ozen;

import net.minecraft.src.*;

import cpw.mods.fml.common.registry.GameRegistry;

public class RecipeRegistry
{
	public RecipeRegistry()
	{
	}
	
	public void addRecipe()
	{
		for (int i = 0; i < 3; ++i)
		{
			GameRegistry.addRecipe(
				new ItemStack(Ozen.blockOzen, 1, i),
					new Object[]
					{
						" S ", "SWS", " S ",
						Character.valueOf('W'), new ItemStack(Block.woodSingleSlab, 1, i),
						Character.valueOf('S'), Item.stick
					});
		}
		
		ItemStack[] material = new ItemStack[]
			{
				new ItemStack(Item.dyePowder, 1, 0), new ItemStack(Item.dyePowder, 1, 1), new ItemStack(Item.ingotIron), new ItemStack(Item.ingotGold)
			};
		
		for (int i = 0; i < material.length; ++i)
		{
			for (int j = 0; j < 3; ++j)
			{
				GameRegistry.addShapelessRecipe(
					new ItemStack(Ozen.blockOzen, 1, 3 + i),
						new Object[]
						{
							new ItemStack(Ozen.blockOzen, 1, j), material[i]
						});
			}
		}
		
		for (int i = 0; i < 7; ++i)
		{
			GameRegistry.addShapelessRecipe(
				new ItemStack(Ozen.blockOzen, 1, i),
					new Object[]
					{
						new ItemStack(Ozen.blockOzen, 1, 8 + i),
					});
			
			GameRegistry.addShapelessRecipe(
				new ItemStack(Ozen.blockOzen, 1, 8 + i),
					new Object[]
					{
						new ItemStack(Ozen.blockOzen, 1, i),
					});
			
		}
	}
}