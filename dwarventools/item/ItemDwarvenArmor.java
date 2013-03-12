package rgn.mods.dwarventools.item;

import java.util.Random;

import org.bouncycastle.util.Strings;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import net.minecraftforge.common.IArmorTextureProvider;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import rgn.mods.dwarventools.enchantment.DwarvenEnchantment;

public class ItemDwarvenArmor extends ItemArmor implements IArmorTextureProvider
{
	private EnumArmorMaterial material;
	private Random random = new Random();

	public ItemDwarvenArmor(int itemId, EnumArmorMaterial enumArmorMaterial, int renderIndex, int armorType)
	{
		super(itemId, enumArmorMaterial, renderIndex, armorType);
		this.material = enumArmorMaterial;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void func_94581_a(IconRegister par1IconRegister)
	{
		this.iconIndex = par1IconRegister.func_94245_a(String.format("rgn/dwarventools:%s", Strings.split(this.getUnlocalizedName(), '.')[1]));
	}

	@Override
	public String getArmorTextureFile(ItemStack itemstack)
	{
		if (itemstack.itemID == DwarvenItem.itemDwarvenHelmetMithril.itemID ||
			itemstack.itemID == DwarvenItem.itemDwarvenPlateMithril.itemID ||
			itemstack.itemID == DwarvenItem.itemDwarvenBootMithril.itemID)
		{
			return "/mods/rgn/dwarventools/textures/armor/mithril_1.png";
		}

		if (itemstack.itemID == DwarvenItem.itemDwarvenLegsMithril.itemID)
		{
			return "/mods/rgn/dwarventools/textures/armor/mithril_2.png";
		}

		if (itemstack.itemID == DwarvenItem.itemDwarvenHelmetEbony.itemID ||
			itemstack.itemID == DwarvenItem.itemDwarvenPlateEbony.itemID ||
			itemstack.itemID == DwarvenItem.itemDwarvenBootEbony.itemID)
		{
			return "/mods/rgn/dwarventools/textures/armor/ebony_1.png";
		}

		if (itemstack.itemID == DwarvenItem.itemDwarvenLegsEbony.itemID)
		{
			return "/mods/rgn/dwarventools/textures/armor/ebony_2.png";
		}

		return "/mods/rgn/dwarventools/textures/armor/mithril_1.png";
	}

	@Override
	public void onCreated(ItemStack itemstack, World world, EntityPlayer entityplayer)
	{
		super.onCreated(itemstack, world, entityplayer);
		if (this.material == DwarvenItem.enumArmorMaterialMithril)
		{
			switch (((ItemDwarvenArmor)itemstack.getItem()).armorType)
			{
				case 0 :
					itemstack.addEnchantment(Enchantment.respiration,  1);
					itemstack.addEnchantment(Enchantment.aquaAffinity, 1);
					break;

				case 1 :
					itemstack.addEnchantment(DwarvenEnchantment.enchantmentVitalize, 1);
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

		if (this.material == DwarvenItem.enumArmorMaterialEbony)
		{
			switch (((ItemDwarvenArmor)itemstack.getItem()).armorType)
			{
				case 0 :
					itemstack.addEnchantment(Enchantment.respiration,  3);
					itemstack.addEnchantment(Enchantment.aquaAffinity, 1);
					break;

				case 1 :
					itemstack.addEnchantment(DwarvenEnchantment.enchantmentVitalize, 2);

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
