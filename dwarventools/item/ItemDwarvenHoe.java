package rgn.mods.dwarventools.item;

import org.bouncycastle.util.Strings;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemHoe;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemDwarvenHoe extends ItemHoe
{
	public ItemDwarvenHoe(int itemId, EnumToolMaterial material)
	{
		super(itemId, material);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateIcons(IconRegister par1IconRegister)
	{
		this.iconIndex = par1IconRegister.registerIcon(String.format("rgn/dwarventools:%s", Strings.split(this.getUnlocalizedName(), '.')[1]));
	}

}
