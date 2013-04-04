package rgn.mods.dawnbreaker;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemDawnBreaker extends ItemSword
{
	private int              weaponDamage;
	private EnumToolMaterial toolMaterial;

	public ItemDawnBreaker(int itemId, EnumToolMaterial _toolMaterial)
	{
		super(itemId, _toolMaterial);
		this.toolMaterial = _toolMaterial;
		this.maxStackSize = 1;
		this.weaponDamage = 4 + this.toolMaterial.getDamageVsEntity();
		this.setMaxDamage(this.toolMaterial.getMaxUses());
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateIcons(IconRegister par1IconRegister)
	{
		this.iconIndex = par1IconRegister.registerIcon("rgn/dawnbreaker:dawnbreaker");
	}

	@Override
	public int getItemEnchantability()
	{
		return 0;
	}

	@Override
	public void onCreated(ItemStack itemstack, World world, EntityPlayer entityplayer)
	{
		super.onCreated(itemstack, world, entityplayer);
		itemstack.addEnchantment(DawnBreaker.enchantmentDawn, 1);
	}

	@Override
	public void onUpdate(ItemStack itemstack, World world, Entity entity, int par4, boolean par5)
	{
		super.onUpdate(itemstack, world, entity, par4, par5);

		if (itemstack.isItemEnchanted())
		{
			return ;
		}

		itemstack.addEnchantment(DawnBreaker.enchantmentDawn, 1);
	}
}