package rgn.mods.elventools.core;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import cpw.mods.fml.common.network.IGuiHandler;

import rgn.mods.elventools.client.GuiSeedBag;
import rgn.mods.elventools.config.Config;
import rgn.mods.elventools.inventory.ContainerSeedBag;
import rgn.mods.elventools.item.ItemElvenSeedBag;

public class CommonProxy implements IGuiHandler
{
	public void registerRenderers()
	{
	}

	public void spawnCustomParticle(World world, EntityPlayer player, Entity entity, int type)
	{
	}

	public World getClientWorld()
	{
		return null;
	}

	public void setBindInfo(Entity entity)
	{
	}

	// implements IGuiHandler
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		ItemStack currentEquippedItem = player.getCurrentEquippedItem();
		if (currentEquippedItem != null && currentEquippedItem.getItem() instanceof ItemElvenSeedBag && ID == Config.guiIdSeedBag)
		{
			return new GuiSeedBag(world, player, currentEquippedItem);
		}
		else
		{
			return null;
		}
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		ItemStack currentEquippedItem = player.getCurrentEquippedItem();
		if (currentEquippedItem != null && currentEquippedItem.getItem() instanceof ItemElvenSeedBag && ID == Config.guiIdSeedBag)
		{
			return new ContainerSeedBag(world, player, currentEquippedItem);
		}
		else
		{
			return null;
		}
	}
}