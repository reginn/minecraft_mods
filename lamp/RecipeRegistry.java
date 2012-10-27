package rgn.mods.lamp;

import net.minecraft.src.*;

import cpw.mods.fml.common.registry.GameRegistry;

public class RecipeRegistry
{
	public void addRecipe()
	{
		int craftingNum = 1;
		if (Lamp.number >= 0 && Lamp.number <= 2)
		{
			craftingNum = Lamp.gen[Lamp.number];
		}
		
		GameRegistry.addRecipe(
			new ItemStack(Lamp.blockLamp, craftingNum, 0),
				new Object[]
				{
					"ITI", "TCT", "ITI",
					Character.valueOf('I'), Item.ingotIron,
					Character.valueOf('T'), Block.thinGlass,
					Character.valueOf('C'), new ItemStack(Item.coal, 1, 0)
				});
				
		GameRegistry.addRecipe(
			new ItemStack(Lamp.blockLamp, craftingNum, 1),
				new Object[]
				{
					"ITI", "TLT", "ITI",
					Character.valueOf('I'), Item.ingotIron,
					Character.valueOf('T'), Block.thinGlass,
					Character.valueOf('L'), Block.glowStone
				});
							
		GameRegistry.addRecipe(
			new ItemStack(Lamp.blockLamp, craftingNum, 2),
				new Object[]
				{
					"IGI", "GLG", "IGI",
					Character.valueOf('I'), Item.ingotIron,
					Character.valueOf('G'), Item.ingotGold,
					Character.valueOf('L'), Block.glowStone
				});
							
		GameRegistry.addRecipe(
			new ItemStack(Lamp.blockLamp, craftingNum, 3),
				new Object[]
				{
					"IDI", "DLD", "IDI",
					Character.valueOf('I'), Item.ingotIron,
					Character.valueOf('L'), Block.glowStone,
					Character.valueOf('D'), Item.diamond
				});
	}
}