package rgn.mods.elventools.item;

import org.bouncycastle.util.Strings;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemElvenParts extends Item
{
	protected String iconName;

	public ItemElvenParts(int itemId)
	{
		super(itemId);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void func_94581_a(IconRegister par1IconRegister)
	{
		this.iconIndex = par1IconRegister.func_94245_a(String.format("rgn/elventools:%s", Strings.split(this.getUnlocalizedName(), '.')[1]));
	}

}