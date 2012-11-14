package rgn.mods.dwarventools;

import java.util.Random;

import net.minecraft.src.*;

import net.minecraftforge.common.IArmorTextureProvider;

public class ItemDwarvenArmor extends ItemArmor implements IArmorTextureProvider
{
	private EnumArmorMaterial material;
	private Random random = new Random();
	
	public ItemDwarvenArmor(int itemId, EnumArmorMaterial enumArmorMaterial, int renderIndex, int armorType)
	{
		super(itemId, enumArmorMaterial, renderIndex, armorType);
		this.material = enumArmorMaterial;
		this.setCreativeTab(Config.tabDwarvenTools);
	}
	
	@Override
	public String getTextureFile()
	{
		return "/rgn/sprites/dwarventools/items.png";
	}
	
	@Override
	public String getArmorTextureFile(ItemStack itemstack)
	{
		if (itemstack.itemID == DwarvenItem.itemDwarvenHelmetMithril.shiftedIndex || 
			itemstack.itemID == DwarvenItem.itemDwarvenPlateMithril.shiftedIndex || 
			itemstack.itemID == DwarvenItem.itemDwarvenBootMithril.shiftedIndex)
		{
			return "/rgn/sprites/dwarventools/armor/mithril_1.png";
		}
		
		if (itemstack.itemID == DwarvenItem.itemDwarvenLegsMithril.shiftedIndex)
		{
			return "/rgn/sprites/dwarventools/armor/mithril_2.png";
		}
		
		if (itemstack.itemID == DwarvenItem.itemDwarvenHelmetEbony.shiftedIndex || 
			itemstack.itemID == DwarvenItem.itemDwarvenPlateEbony.shiftedIndex || 
			itemstack.itemID == DwarvenItem.itemDwarvenBootEbony.shiftedIndex)
		{
			return "/rgn/sprites/dwarventools/armor/ebony_1.png";
		}
		
		if (itemstack.itemID == DwarvenItem.itemDwarvenLegsEbony.shiftedIndex)
		{
			return "/rgn/sprites/dwarventools/armor/ebony_2.png";
		}
		
		return "/rgn/sprites/dwarventools/armor/mithril_1.png";
	}
	
	@Override
	public void onCreated(ItemStack itemstack, World world, EntityPlayer entityplayer)
	{
		super.onCreated(itemstack, world, entityplayer);
		if (this.material == ConfigureEnum.enumArmorMaterialMithril)
		{
			switch (((ItemDwarvenArmor)itemstack.getItem()).armorType)
			{
				case 0 :
					itemstack.addEnchantment(Enchantment.respiration,  1);
					itemstack.addEnchantment(Enchantment.aquaAffinity, 1);
					break;
					
				case 1 :
					itemstack.addEnchantment(Enchantment.protection,           1);
					itemstack.addEnchantment(Enchantment.fireProtection,       1);
					itemstack.addEnchantment(Enchantment.blastProtection,      1);
					itemstack.addEnchantment(Enchantment.projectileProtection, 1);
					break;
					
				case 2 :
					itemstack.addEnchantment(Enchantment.protection,           1);
					itemstack.addEnchantment(Enchantment.fireProtection,       1);
					itemstack.addEnchantment(Enchantment.blastProtection,      1);
					itemstack.addEnchantment(Enchantment.projectileProtection, 1);
					break;
					
				case 3 :
					itemstack.addEnchantment(Enchantment.featherFalling, 1);
					break;
				
				default :
					break;
			}
		}
		
		if (this.material == ConfigureEnum.enumArmorMaterialEbony)
		{
			switch (((ItemDwarvenArmor)itemstack.getItem()).armorType)
			{
				case 0 :
					itemstack.addEnchantment(Enchantment.respiration,  3);
					itemstack.addEnchantment(Enchantment.aquaAffinity, 1);
					break;
					
				case 1 :
					itemstack.addEnchantment(Enchantment.protection,           3);
					itemstack.addEnchantment(Enchantment.fireProtection,       3);
					itemstack.addEnchantment(Enchantment.blastProtection,      3);
					itemstack.addEnchantment(Enchantment.projectileProtection, 3);
					break;
					
				case 2 :
					itemstack.addEnchantment(Enchantment.protection,           3);
					itemstack.addEnchantment(Enchantment.fireProtection,       3);
					itemstack.addEnchantment(Enchantment.blastProtection,      3);
					itemstack.addEnchantment(Enchantment.projectileProtection, 3);
					break;
					
				case 3 :
					itemstack.addEnchantment(Enchantment.featherFalling, 3);
					break;
				
				default :
					break;
			}
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
		
		if (this.material == ConfigureEnum.enumArmorMaterialMithril)
		{
			switch (((ItemDwarvenArmor)itemstack.getItem()).armorType)
			{
				case 0 :
					itemstack.addEnchantment(Enchantment.respiration,  1);
					itemstack.addEnchantment(Enchantment.aquaAffinity, 1);
					break;
					
				case 1 :
					itemstack.addEnchantment(Enchantment.protection,           1);
					itemstack.addEnchantment(Enchantment.fireProtection,       1);
					itemstack.addEnchantment(Enchantment.blastProtection,      1);
					itemstack.addEnchantment(Enchantment.projectileProtection, 1);
					break;
					
				case 2 :
					itemstack.addEnchantment(Enchantment.protection,           1);
					itemstack.addEnchantment(Enchantment.fireProtection,       1);
					itemstack.addEnchantment(Enchantment.blastProtection,      1);
					itemstack.addEnchantment(Enchantment.projectileProtection, 1);
					break;
					
				case 3 :
					itemstack.addEnchantment(Enchantment.featherFalling, 1);
					break;
				
				default :
					break;
			}
		}
		
		if (this.material == ConfigureEnum.enumArmorMaterialEbony)
		{
			switch (((ItemDwarvenArmor)itemstack.getItem()).armorType)
			{
				case 0 :
					itemstack.addEnchantment(Enchantment.respiration,  3);
					itemstack.addEnchantment(Enchantment.aquaAffinity, 1);
					break;
					
				case 1 :
					itemstack.addEnchantment(Enchantment.protection,           3);
					itemstack.addEnchantment(Enchantment.fireProtection,       3);
					itemstack.addEnchantment(Enchantment.blastProtection,      3);
					itemstack.addEnchantment(Enchantment.projectileProtection, 3);
					break;
					
				case 2 :
					itemstack.addEnchantment(Enchantment.protection,           3);
					itemstack.addEnchantment(Enchantment.fireProtection,       3);
					itemstack.addEnchantment(Enchantment.blastProtection,      3);
					itemstack.addEnchantment(Enchantment.projectileProtection, 3);
					break;
					
				case 3 :
					itemstack.addEnchantment(Enchantment.featherFalling, 3);
					break;
				
				default :
					break;
			}
		}
	}
}
