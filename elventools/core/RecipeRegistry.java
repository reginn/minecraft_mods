package rgn.mods.elventools.core;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import com.google.common.collect.Lists;

import cpw.mods.fml.common.registry.GameRegistry;

public class RecipeRegistry
{
	public void addRecipe()
	{
		this.addRecipeEbony();
		this.addRecipeMithril();
		this.addRecipeNewBows();
		this.addRecipeArrow();
	}

	private void addRecipeNewBows()
	{
		GameRegistry.addRecipe(
			new ShapedOreRecipe(
				new ItemStack(ElvenItem.itemLeatherLongbow, 1),
					new Object[]
					{
						"SEL", "S E", "SEL",
						Character.valueOf('S'), Item.silk,
						Character.valueOf('L'), Item.leather,
						Character.valueOf('E'), "stickEbony"
					}));

		GameRegistry.addRecipe(
			new ItemStack(ElvenItem.itemCompositeLongbow, 1),
				new Object[]
				{
					"IRR", "RBI", "RI ",
					Character.valueOf('I'), Item.ingotIron,
					Character.valueOf('B'), ElvenItem.itemLeatherLongbow,
					Character.valueOf('R'), new ItemStack(Item.dyePowder, 1, 1)
				});

		GameRegistry.addRecipe(
			new ItemStack(ElvenItem.itemEnhancedLongbow, 1),
				new Object[]
				{
					"GOO", "DBG", "DG ",
					Character.valueOf('D'), Item.diamond,
					Character.valueOf('B'), ElvenItem.itemCompositeLongbow,
					Character.valueOf('G'), Item.ingotGold,
					Character.valueOf('O'), Block.obsidian
				});
	}

	private void addRecipeEbony()
	{
		GameRegistry.addRecipe(
			new ShapelessOreRecipe(
				new ItemStack(ElvenBlock.blockEbonyWood, 4),
					new Object[]
					{
						"woodLogEbony"
					}));

		GameRegistry.addRecipe(
			new ShapedOreRecipe(
				new ItemStack(ElvenItem.itemEbonyStick, 4),
					new Object[]
					{
						"E", "E",
						Character.valueOf('E'), "plankEbony"
					}));

		GameRegistry.addRecipe(
			new ShapedOreRecipe(
				new ItemStack(ElvenItem.itemEbonyBoat, 1),
					new Object[]
					{
						"E E", "EEE",
						Character.valueOf('E'), "plankEbony"
					}));

		GameRegistry.addRecipe(
			new ShapedOreRecipe(
				new ItemStack(Block.torchWood, 4),
					new Object[]
					{
						"C", "E",
						Character.valueOf('C'), new ItemStack(Item.coal, 1, 0),
						Character.valueOf('E'), "stickEbony"
					}));

		GameRegistry.addRecipe(
			new ShapedOreRecipe(
				new ItemStack(Block.torchWood, 4),
					new Object[]
					{
						"C", "E",
						Character.valueOf('C'), new ItemStack(Item.coal, 1, 1),
						Character.valueOf('E'), "stickEbony"
					}));

		GameRegistry.addRecipe(
			new ShapedOreRecipe(
				new ItemStack(ElvenItem.itemElvenSickle, 1),
					new Object[]
					{
						"MM ", " E ", " E ",
						Character.valueOf('M'), "ingotMithril",
						Character.valueOf('E'), "stickEbony"
					}));

		GameRegistry.addRecipe(
			new ShapedOreRecipe(
				new ItemStack(ElvenItem.itemElvenLumberAxe, 1),
					new Object[]
					{
						"MM ", "ME ", "LEL",
						Character.valueOf('M'), "ingotMithril",
						Character.valueOf('E'), "stickEbony",
						Character.valueOf('L'), Item.leather
					}));
	}

	private void addRecipeMithril()
	{
		final Item[] mithrilTools = new Item[]
			{
				ElvenItem.itemElvenShovelMithril,
				ElvenItem.itemElvenPickaxeMithril,
				ElvenItem.itemElvenAxeMithril,
				ElvenItem.itemElvenSwordMithril
			};

		final String[] shovelRecipe  = new String[] {  "M",   "S",   "S"};
		final String[] pickaxeRecipe = new String[] {"MMM", " S ", " S "};
		final String[] axeRecipe     = new String[] { "MM",  "MS",  " S"};
		final String[] swordRecipe   = new String[] {  "M",   "M",   "S"};

		List<String[]> recipeList = Lists.newArrayList(shovelRecipe, pickaxeRecipe, axeRecipe, swordRecipe);


		for (int i = 0; i < mithrilTools.length; ++i)
		{
			GameRegistry.addRecipe(
				new ShapedOreRecipe(
					new ItemStack(mithrilTools[i]),
						new Object[]
						{
							(recipeList.get(i))[0], (recipeList.get(i))[1], (recipeList.get(i))[2],
							Character.valueOf('M'), "ingotMithril",
							Character.valueOf('S'), "stickEbony"
						}));
		}
	}

	private void addRecipeArrow()
	{
		GameRegistry.addRecipe(
			new ItemStack(ElvenItem.itemRopeEstablisher, 1),
				new Object[]
				{
					"SS", "SS", "SS",
					Character.valueOf('S'), Item.silk
				});

		GameRegistry.addRecipe(
			new ItemStack(ElvenItem.itemTorchArrow, 1),
				new Object[]
				{
					" T", "A ",
					Character.valueOf('T'), Block.torchWood,
					Character.valueOf('A'), Item.arrow
				});

		GameRegistry.addRecipe(
			new ItemStack(ElvenItem.itemRopeArrow, 1),
				new Object[]
				{
					" R", "A ",
					Character.valueOf('R'), ElvenItem.itemRopeEstablisher,
					Character.valueOf('A'), Item.arrow
				});
	}
}