package rgn.mods.dwarventools.core;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import cpw.mods.fml.common.ICraftingHandler;
import cpw.mods.fml.common.network.IGuiHandler;

import rgn.mods.dwarventools.config.Config;
import rgn.mods.dwarventools.enchantment.DwarvenEnchantment;
import rgn.mods.dwarventools.inventory.ContainerInfernalFurnace;
import rgn.mods.dwarventools.item.DwarvenItem;
import rgn.mods.dwarventools.item.ItemDwarvenSword;
import rgn.mods.dwarventools.tileentity.TileEntityInfernalFurnace;

public class CommonProxy implements IGuiHandler, ICraftingHandler
{
	public int addArmor(String armor)
	{
		return 0;
	}

	public World getClientWorld()
	{
		return null;
	}

	public void spawnCustomParticle(World world, Entity entity, int particleId)
	{
	}

	// implements IGuiHandler
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return null;
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntityInfernalFurnace tileentity = (TileEntityInfernalFurnace)world.getBlockTileEntity(x, y, z);
		if (tileentity != null && ID == Config.guiIdInfernalFurnace)
		{
			return (new ContainerInfernalFurnace(player.inventory, tileentity));
		}
		return null;
	}

	@Override
	public void onCrafting(EntityPlayer player, ItemStack item, IInventory craftMatrix)
	{
		if (item.getItem() instanceof ItemDwarvenSword)
		{
			if (((ItemDwarvenSword)item.getItem()).getToolMaterial() == DwarvenItem.enumToolMaterialMithril)
			{
				item.addEnchantment(DwarvenEnchantment.enchantmentCriticalStrike, 1);
				item.addEnchantment(Enchantment.smite,   3);
				item.addEnchantment(Enchantment.looting, 1);
			}
			if (((ItemDwarvenSword)item.getItem()).getToolMaterial() == DwarvenItem.enumToolMaterialEbony)
			{
				item.addEnchantment(DwarvenEnchantment.enchantmentLifeSteal, 1);
				item.addEnchantment(Enchantment.sharpness, 3);
				item.addEnchantment(Enchantment.looting,   2);
			}
		}
	}

	@Override
	public void onSmelting(EntityPlayer player, ItemStack item) {
		// TODO 自動生成されたメソッド・スタブ

	}
}