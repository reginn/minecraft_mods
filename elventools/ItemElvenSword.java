package rgn.mods.elventools;

import java.util.Random;

import net.minecraft.src.*;

public class ItemElvenSword extends ItemSword
{
	private int   weaponDamage;
	private final EnumToolMaterial toolMaterial;
	
	private Random random = new Random();
	
	public ItemElvenSword(int itemId, EnumToolMaterial material)
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
		return "/rgn/sprites/elventools/items.png";
	}
	
	@Override
	public void onCreated(ItemStack itemstack, World world, EntityPlayer entityplayer)
	{
		super.onCreated(itemstack, world, entityplayer);
		if (itemstack.itemID == ElvenTools.itemElvenSwordMithril.shiftedIndex)
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
		
		if (itemstack.itemID == ElvenTools.itemElvenSwordMithril.shiftedIndex)
		{
			itemstack.addEnchantment(Enchantment.smite, 2);
		}
	}
}