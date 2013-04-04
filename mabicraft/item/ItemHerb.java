package rgn.mods.mabicraft.item;

import org.bouncycastle.util.Strings;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemHerb extends Item
{
	public ItemHerb(int itemId, EnumHerbType enumHerbType)
	{
		super(itemId);
		this.setPotionEffect(enumHerbType.getPotionEffect());
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateIcons(IconRegister par1IconRegister)
	{
		this.iconIndex = par1IconRegister.registerIcon(String.format("rgn/mabicraft:%s", Strings.split(this.getUnlocalizedName(), '.')[1]));
	}
}