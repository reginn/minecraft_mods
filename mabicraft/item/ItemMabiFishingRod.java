package rgn.mods.mabicraft.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import rgn.mods.mabicraft.entity.projectile.EntityMabiFishHook;
import rgn.mods.mabicraft.network.PacketHandler;

public class ItemMabiFishingRod extends Item
{
	@SideOnly(Side.CLIENT)
	private Icon emptyIcon;

	public ItemMabiFishingRod(int itemId)
	{
		super(itemId);
		this.setMaxDamage(64);
		this.setMaxStackSize(1);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateIcons(IconRegister par1IconRegister)
	{
		this.iconIndex = par1IconRegister.registerIcon("fishingRod");
		this.emptyIcon = par1IconRegister.registerIcon("fishingRod_empty");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean isFull3D()
	{
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldRotateAroundWhenRendering()
	{
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining)
	{
		if (player.fishEntity != null)
		{
			return this.emptyIcon;
		}
		return getIconIndex(stack);
	}

	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
	{
		if (player.fishEntity != null)
		{
			int var4 = ((EntityMabiFishHook)player.fishEntity).catchFish();
			itemStack.damageItem(var4, player);
			player.swingItem();
		}
		else
		{
			world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

			if (!world.isRemote)
			{
				EntityMabiFishHook entity = new EntityMabiFishHook(world, player);
				world.spawnEntityInWorld(entity);
				PacketDispatcher.sendPacketToAllPlayers(PacketHandler.getPacketFishingRod(player, entity));
			}

			player.swingItem();
		}

		return itemStack;
	}
}
