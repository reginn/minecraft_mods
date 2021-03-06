package rgn.mods.elventools.item;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;

public class ItemElvenSword extends ItemSword
{
	private float weaponDamage;
	private final EnumToolMaterial toolMaterial;

	public ItemElvenSword(int itemId, EnumToolMaterial material)
	{
		super(itemId, material);
		this.toolMaterial = material;
		this.maxStackSize = 1;
		this.weaponDamage = 4.0f + material.getDamageVsEntity();
		this.setMaxDamage(material.getMaxUses());
	}

	@Override
	public void onCreated(ItemStack itemstack, World world, EntityPlayer entityplayer)
	{
		super.onCreated(itemstack, world, entityplayer);
		if (itemstack.itemID == ElvenItem.itemElvenSwordMithril.itemID)
		{
			itemstack.addEnchantment(Enchantment.smite, 2);
		}
	}

	@Override
	public void onUpdate(ItemStack itemstack, World world, Entity entity, int par4, boolean par5)
	{
		super.onUpdate(itemstack, world, entity, par4, par5);

		if (itemstack.isItemEnchanted())
		{
			return ;
		}

		if (itemstack.itemID == ElvenItem.itemElvenSwordMithril.itemID)
		{
			itemstack.addEnchantment(Enchantment.smite, 2);
		}
	}
}
