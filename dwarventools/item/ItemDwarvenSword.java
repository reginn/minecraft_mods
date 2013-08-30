package rgn.mods.dwarventools.item;

import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemSword;

public class ItemDwarvenSword extends ItemSword
{
	private float weaponDamage;
	private final EnumToolMaterial toolMaterial;

	public ItemDwarvenSword(int itemId, EnumToolMaterial material)
	{
		super(itemId, material);
		this.toolMaterial = material;
		this.maxStackSize = 1;
		this.weaponDamage = 4.0F + material.getDamageVsEntity();
		this.setMaxDamage(material.getMaxUses());
	}

	public EnumToolMaterial getToolMaterial()
	{
		return this.toolMaterial;
	}
}
