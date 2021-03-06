package rgn.mods.ozen;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemOzen extends ItemBlock
{
	public ItemOzen(int itemId)
	{
		super(itemId);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack)
	{
		int material = itemstack.getItemDamage() & 7;
		int type     = itemstack.getItemDamage() >>> 3;
		int k = 0;
		int l = 0;
		if (material >= 0 && material < ((BlockOzen)Ozen.blockOzen).names.length)
		{
			k = material;
		}

		if (type == 0 || type == 1)
		{
			l = type;
		}

		String mat = ((BlockOzen)Ozen.blockOzen).names[k];
		String typ = ((BlockOzen)Ozen.blockOzen).types[l];

		return (new StringBuilder()).append(this.getUnlocalizedName()).append(".").append(typ).append(".").append(mat).toString();
	}

	@Override
	public int getMetadata(int par1)
	{
		return par1;
	}

	@SideOnly(Side.CLIENT)
	public int getColorFromDamage(int par1, int par2)
	{
		return Ozen.blockOzen.getRenderColor(par1);
	}
}