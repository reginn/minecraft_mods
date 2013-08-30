package rgn.mods.dwarventools.item;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ItemDwarvenArmor extends ItemArmor
{
	private EnumArmorMaterial material;
	private Random random = new Random();

	private String[] mithrilArmorFilePath = new String[]
				{
					"rgn.dwarventools:textures/armor/mithril_1.png",
					"rgn.dwarventools:textures/armor/mithril_2.png"
				};

	private String[] ebonyArmorFilePath = new String[]
				{
					"rgn.dwarventools:textures/armor/ebony_1.png",
					"rgn.dwarventools:textures/armor/ebony_2.png"
				};

	public ItemDwarvenArmor(int itemId, EnumArmorMaterial enumArmorMaterial, int renderIndex, int armorType)
	{
		super(itemId, enumArmorMaterial, renderIndex, armorType);
		this.material = enumArmorMaterial;
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
		String[] filePath = ((ItemArmor)stack.getItem()).getArmorMaterial() == DwarvenItem.enumArmorMaterialMithril ? mithrilArmorFilePath : ebonyArmorFilePath;

		int kindOfArmor = slot != 2 ? 0 : 1;

		return filePath[kindOfArmor];
	}

}
