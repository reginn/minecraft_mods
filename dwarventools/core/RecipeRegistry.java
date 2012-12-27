package rgn.mods.dwarventools.core;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;

import com.google.common.collect.Lists;

import cpw.mods.fml.common.registry.GameRegistry;

public class RecipeRegistry
{
	public void addRecipe()
	{
		this.addRecipeUsedOreDict();
		this.addRecipeNewMaterialTools();
		this.addRecipeUsedOreDictForNewTools();
		this.addRecipeOthers();
	}

	private void addRecipeUsedOreDict()
	{
		Item[]   unstorageItemList = new Item[] {DwarvenItem.itemMithrilIngot, DwarvenItem.itemEbonyIngot};
		String[] unstorageNameList = new String[] {"ingotMithril", "ingotEbony"};

		for (int i = 0; i < unstorageItemList.length; i++)
		{

			GameRegistry.addRecipe(
				new ShapedOreRecipe(new ItemStack(DwarvenBlock.blockDwarvenOreStorage, 1, i),
					new Object[]
						{
							"XXX", "XXX", "XXX",
							Character.valueOf('X'), unstorageNameList[i]
						}));

			GameRegistry.addShapelessRecipe(
				new ItemStack(unstorageItemList[i], 9),
					new Object[]
					{
						new ItemStack(DwarvenBlock.blockDwarvenOreStorage, 1, i + 1)
					 });
		}

		GameRegistry.addRecipe(
			new ItemStack(DwarvenItem.itemIronBar, 4),
				new Object[]
				{
					"I","B",
					Character.valueOf('I'), Item.ingotIron,
					Character.valueOf('B'), Item.blazeRod
				});
	}

	private void addRecipeOthers()
	{
		GameRegistry.addRecipe(
			new ItemStack(Block.netherBrick, 1),
				new Object[]
				{
					"NN","NN",
					Character.valueOf('N'), Block.netherrack
				});

		GameRegistry.addRecipe(
			new ItemStack(DwarvenBlock.blockInfernalFurnace, 1),
				new Object[]
				{
					"NNN", "N N", "NNN",
					Character.valueOf('N'), Block.netherBrick
				});

		GameRegistry.addRecipe(
			new ItemStack(DwarvenBlock.blockDwarvenOreStorage, 1, 0),
				new Object[]
				{
					"XXX", "XXX", "XXX",
					Character.valueOf('X'), Item.redstone
				});

		GameRegistry.addShapelessRecipe(
			new ItemStack(Item.redstone, 9),
				 new Object[]
				 {
					new ItemStack(DwarvenBlock.blockDwarvenOreStorage, 1, 0)
				 });
	}

	private void addRecipeNewMaterialTools()
	{
		final Item[] shovelList  = new Item[] {DwarvenItem.itemDwarvenShovelObsidian,  DwarvenItem.itemDwarvenShovelLapislazuli,  DwarvenItem.itemDwarvenShovelRedstone};
		final Item[] pickaxeList = new Item[] {DwarvenItem.itemDwarvenPickaxeObsidian, DwarvenItem.itemDwarvenPickaxeLapislazuli, DwarvenItem.itemDwarvenPickaxeRedstone};
		final Item[] axeList     = new Item[] {DwarvenItem.itemDwarvenAxeObsidian,     DwarvenItem.itemDwarvenAxeLapislazuli,     DwarvenItem.itemDwarvenAxeRedstone};
		final Item[] hoeList     = new Item[] {DwarvenItem.itemDwarvenHoeObsidian,     DwarvenItem.itemDwarvenHoeLapislazuli,     DwarvenItem.itemDwarvenHoeRedstone};
		final Item[] swordList   = new Item[] {DwarvenItem.itemDwarvenSwordObsidian,   DwarvenItem.itemDwarvenSwordLapislazuli,   DwarvenItem.itemDwarvenSwordRedstone};

		final String[] shovelRecipe  = new String[] {  "M",   "S",   "S"};
		final String[] pickaxeRecipe = new String[] {"MMM", " S ", " S "};
		final String[] axeRecipe     = new String[] { "MM",  "MS",  " S"};
		final String[] hoeRecipe     = new String[] { "MM",  " S",  " S"};
		final String[] swordRecipe   = new String[] {  "M",   "M",   "S"};

		List<ItemStack> materialList = Lists.newArrayList(new ItemStack(Block.obsidian), new ItemStack(Block.blockLapis), new ItemStack(DwarvenBlock.blockDwarvenOreStorage, 1, 0));

		List<Item[]> itemList = Lists.newArrayList(shovelList, pickaxeList, axeList, hoeList, swordList);
		List<String[]> recipeList = Lists.newArrayList(shovelRecipe, pickaxeRecipe, axeRecipe, hoeRecipe, swordRecipe);

		for (int i = 0; i < itemList.size(); i++)
		{
			for (int j = 0; j < materialList.size(); j++ )
			{
				GameRegistry.addRecipe(
					new ItemStack((itemList.get(i))[j], 1),
						new Object[]
						{
							(recipeList.get(i))[0], (recipeList.get(i))[1], (recipeList.get(i))[2],
							Character.valueOf('M'), materialList.get(j),
							Character.valueOf('S'), Item.stick
						});
			}
		}
	}

	private void addRecipeUsedOreDictForNewTools()
	{
		final Item[] shovelList    = new Item[] {DwarvenItem.itemDwarvenShovelMithril,    DwarvenItem.itemDwarvenShovelEbony};
		final Item[] pickaxeList   = new Item[] {DwarvenItem.itemDwarvenPickaxeMithril,   DwarvenItem.itemDwarvenPickaxeEbony};
		final Item[] battleaxeList = new Item[] {DwarvenItem.itemDwarvenBattleaxeMithril, DwarvenItem.itemDwarvenBattleaxeEbony};

		final Item[] swordList     = new Item[] {DwarvenItem.itemDwarvenSwordMithril,     DwarvenItem.itemDwarvenSwordEbony};

		final Item[] helmetList    = new Item[] {DwarvenItem.itemDwarvenHelmetMithril,    DwarvenItem.itemDwarvenHelmetEbony};
		final Item[] plateList     = new Item[] {DwarvenItem.itemDwarvenPlateMithril,     DwarvenItem.itemDwarvenPlateEbony};
		final Item[] legsList      = new Item[] {DwarvenItem.itemDwarvenLegsMithril,      DwarvenItem.itemDwarvenLegsEbony};
		final Item[] bootList      = new Item[] {DwarvenItem.itemDwarvenBootMithril,      DwarvenItem.itemDwarvenBootEbony};

		final String[] shovelRecipe    = new String[] {  "M",   "S",   "S"};
		final String[] pickaxeRecipe   = new String[] {"MMM", " S ", " S "};
		final String[] battleaxeRecipe = new String[] {"MMM", "MSM", " S "};

		final String[] helmetRecipe = new String[] {"MMM", "M M"};
		final String[] plateRecipe  = new String[] {"M M", "MMM", "MMM"};
		final String[] legsRecipe   = new String[] {"MMM", "M M", "M M"};
		final String[] bootRecipe   = new String[] {"M M", "M M"};

		List<Item[]> itemList = Lists.newArrayList(shovelList, pickaxeList, battleaxeList);
		List<String[]> recipeList = Lists.newArrayList(shovelRecipe, pickaxeRecipe, battleaxeRecipe);

		String[] materialList = {"ingotMithril", "ingotEbony"};

		for (int i = 0; i < itemList.size(); i++)
		{
			for (int j = 0; j < materialList.length; j++ )
			{
				GameRegistry.addRecipe(
					new ShapedOreRecipe(
						new ItemStack((itemList.get(i))[j], 1),
							new Object[]
							{
								(recipeList.get(i))[0], (recipeList.get(i))[1], (recipeList.get(i))[2],
								Character.valueOf('M'), materialList[j],
								Character.valueOf('S'), DwarvenItem.itemIronBar
							}));
			}
		}

		Item[] brokenSword = new Item[] {DwarvenItem.itemDwarvenBrokenSwordMithril, DwarvenItem.itemDwarvenBrokenSwordEbony};

		for (int i = 0; i < materialList.length; i++)
		{
			GameRegistry.addRecipe(
				new ShapedOreRecipe(
					new ItemStack(swordList[i], 1),
						new Object[]
						{
							"M", "M", "B",
							Character.valueOf('M'), materialList[i],
							Character.valueOf('B'), brokenSword[i]
						}));
		}

		List<Item[]> helmetBootList = Lists.newArrayList(helmetList, bootList);
		List<Item[]> plateLegsList = Lists.newArrayList(plateList, legsList);
		List<String[]> helmetBootRecipeList = Lists.newArrayList(helmetRecipe, bootRecipe);
		List<String[]> plateLegsRecipeList = Lists.newArrayList(plateRecipe, legsRecipe);

		for (int i = 0; i < 2; i++)
		{
			for (int j = 0; j < materialList.length; j++)
			{
				GameRegistry.addRecipe(
					new ShapedOreRecipe(
						new ItemStack((helmetBootList.get(i))[j], 1),
							new Object[]
							{
								(helmetBootRecipeList.get(i))[0], (helmetBootRecipeList.get(i))[1],
								Character.valueOf('M'), materialList[j],
							}));

				GameRegistry.addRecipe(
					new ShapedOreRecipe(
						new ItemStack((plateLegsList.get(i))[j], 1),
							new Object[]
							{
								(plateLegsRecipeList.get(i))[0], (plateLegsRecipeList.get(i))[1], (plateLegsRecipeList.get(i))[2],
								Character.valueOf('M'), materialList[j],
							}));
			}
		}

	}

}