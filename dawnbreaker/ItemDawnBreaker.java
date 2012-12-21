package rgn.mods.dawnbreaker;

import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemSword;

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
	public String getTextureFile()
	{
		return "/rgn/sprites/dawnbreaker/items.png";
	}

	@Override
	public int getItemEnchantability()
	{
		return 0;
	}
}