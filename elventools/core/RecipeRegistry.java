package rgn.mods.elventools.core;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import cpw.mods.fml.common.registry.GameRegistry;

import rgn.mods.elventools.block.ElvenBlock;
import rgn.mods.elventools.item.ElvenItem;

public class RecipeRegistry
{
	public void addRecipe()
	{
		this.addRecipeEbony();
		this.addRecipeMithril();
		this.addRecipeNewBows();
		this.addRecipeArrow();
		this.addRecipeOther();
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

		GameRegistry.addRecipe(
			new ShapedOreRecipe(
				new ItemStack(ElvenItem.itemBoneCompositeBow, 1),
					new Object[]
					{
						" BS", "E S", " BS",
						Character.valueOf('B'), Item.bone,
						Character.valueOf('S'), Item.silk,
						Character.valueOf('E'), "stickEbony"
					}));

		GameRegistry.addRecipe(
			new ShapedOreRecipe(
				new ItemStack(ElvenItem.itemShadowBow, 1),
					new Object[]
					{
						" PS", "EBS", " PS",
						Character.valueOf('P'), Item.enderPearl,
						Character.valueOf('S'), Item.silk,
						Character.valueOf('B'), ElvenItem.itemBoneCompositeBow,
						Character.valueOf('E'), "stickEbony"
					}));

		GameRegistry.addRecipe(
			new ItemStack(ElvenItem.itemEndBow, 1),
				new Object[]
				{
					" EE", "EBS", "ES ",
					Character.valueOf('E'), Item.eyeOfEnder,
					Character.valueOf('S'), Item.silk,
					Character.valueOf('B'), ElvenItem.itemShadowBow
				});

		GameRegistry.addRecipe(
			new ItemStack(ElvenItem.itemFeatherBow, 1),
				new Object[]
				{
					" FS", "GBS", " FS",
					Character.valueOf('S'), Item.silk,
					Character.valueOf('F'), Item.feather,
					Character.valueOf('G'), Item.ingotGold,
					Character.valueOf('B'), ElvenItem.itemBoneCompositeBow
				});

		GameRegistry.addRecipe(
			new ShapedOreRecipe(
				new ItemStack(ElvenItem.itemSteelBow, 1),
					new Object[]
					{
						"　IS", "EBS", " IS",
						Character.valueOf('I'), Item.ingotIron,
						Character.valueOf('S'), Item.silk,
						Character.valueOf('B'), ElvenItem.itemCompositeLongbow,
						Character.valueOf('E'), "stickEbony"
					}));

		GameRegistry.addRecipe(
			new ShapedOreRecipe(
				new ItemStack(ElvenItem.itemElvenBow, 1),
					new Object[]
					{
						" GS", "EBS", " GS",
						Character.valueOf('G'), Item.ingotGold,
						Character.valueOf('S'), Item.silk,
						Character.valueOf('B'), ElvenItem.itemCompositeLongbow,
						Character.valueOf('E'), "stickEbony"
					}));
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

	private void addRecipeOther()
	{
		GameRegistry.addRecipe(
			new ItemStack(ElvenItem.itemElvenSeedBag, 1),
				new Object[]
				{
					" P ", "P P", "PPP",
					Character.valueOf('P'), Item.paper
				});

		GameRegistry.addRecipe(
			new ShapedOreRecipe(
				new ItemStack(ElvenBlock.blockEbonyLadder, 3),
				new Object[]
				{
					"S S", "SSS", "S S",
					Character.valueOf('S'), "stickEbony"
				}));

	}
}