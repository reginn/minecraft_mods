package rgn.mods.dwarventools;

import java.util.Random;

import net.minecraft.src.*;

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
		if (itemstack.getItem() == DwarvenItem.itemDwarvenBattleaxeMithril)
		{
			itemstack.addEnchantment(Enchantment.knockback, random.nextInt(2) + 1);
		}
		if (itemstack.getItem() == DwarvenItem.itemDwarvenBattleaxeEbony)
		{
			itemstack.addEnchantment(Enchantment.knockback, random.nextInt(4) + 1);
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
		
		if (itemstack.getItem() == DwarvenItem.itemDwarvenBattleaxeMithril)
		{
			itemstack.addEnchantment(Enchantment.knockback, random.nextInt(2) + 1);
		}
		if (itemstack.getItem() == DwarvenItem.itemDwarvenBattleaxeEbony)
		{
			itemstack.addEnchantment(Enchantment.knockback, random.nextInt(4) + 1);
		}
	}
}
