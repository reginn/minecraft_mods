package rgn.mods.dwarventools.item;

import java.util.Random;

import org.bouncycastle.util.Strings;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

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
	@SideOnly(Side.CLIENT)
	public void updateIcons(IconRegister par1IconRegister)
	{
		this.iconIndex = par1IconRegister.registerIcon(String.format("rgn/dwarventools:%s", Strings.split(this.getUnlocalizedName(), '.')[1]));
	}

	@Override
	public void onCreated(ItemStack itemstack, World world, EntityPlayer entityplayer)
	{
		super.onCreated(itemstack, world, entityplayer);
		itemstack.addEnchantment(DwarvenEnchantment.enchantmentExecutioner, 1);
	}

}
