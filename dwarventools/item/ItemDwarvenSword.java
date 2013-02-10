package rgn.mods.dwarventools.item;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;

import rgn.mods.dwarventools.config.Config;
import rgn.mods.dwarventools.config.ConfigureEnum;
import rgn.mods.dwarventools.item.DwarvenItem;

public class ItemDwarvenSword extends ItemSword
{
	private int   weaponDamage;
	private final EnumToolMaterial toolMaterial;

	public ItemDwarvenSword(int itemId, EnumToolMaterial material)
	{
		super(itemId, material);
		this.toolMaterial = material;
		this.maxStackSize = 1;
		this.weaponDamage = 4 + material.getDamageVsEntity();
		this.setMaxDamage(material.getMaxUses());
		this.setCreativeTab(Config.tabDwarvenTools);
	}

	@Override
	public String getTextureFile()
	{
		return "/rgn/sprites/dwarventools/items.png";
	}

	@Override
	public void onCreated(ItemStack itemstack, World world, EntityPlayer entityplayer)
	{
		super.onCreated(itemstack, world, entityplayer);
		
		if (this.toolMaterial == ConfigureEnum.enumToolMaterialMithril)
		{
			itemstack.addEnchantment(Enchantment.smite,   3);
			itemstack.addEnchantment(Enchantment.looting, 1);
		}
		if (this.toolMaterial == ConfigureEnum.enumToolMaterialEbony)
		{
			itemstack.addEnchantment(Enchantment.sharpness, 3);
			itemstack.addEnchantment(Enchantment.looting,   2);
		}
	}
}
