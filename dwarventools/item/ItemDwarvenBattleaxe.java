package rgn.mods.dwarventools.item;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;

import rgn.mods.dwarventools.enchantment.DwarvenEnchantment;

public class ItemDwarvenBattleaxe extends ItemSword
{
	private int   weaponDamage;
	private final EnumToolMaterial toolMaterial;

	private Random random = new Random();

	public ItemDwarvenBattleaxe(int itemId, EnumToolMaterial material)
	{
		super(itemId, material);
		this.toolMaterial = material;
		this.maxStackSize = 1;
		this.weaponDamage = 4 + material.getDamageVsEntity();
		this.setMaxDamage(material.getMaxUses());
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
		itemstack.addEnchantment(DwarvenEnchantment.enchantmentExecutioner, 1);
	}

}
