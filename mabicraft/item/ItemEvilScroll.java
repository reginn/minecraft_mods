package rgn.mods.mabicraft.item;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import rgn.mods.mabicraft.registry.EvilScrollRegistry;

public class ItemEvilScroll extends Item
{
	public ItemEvilScroll(int itemId)
	{
		super(itemId);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
	}

	@Override
	public String getTextureFile()
	{
		return "/rgn/sprites/mabicraft/items.png";
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void getSubItems(int itemID, CreativeTabs creativeTabs, List list)
	{
		for (int i = 0; i < EvilScrollRegistry.instance().getMetadataListSize(); i++)
		{
			list.add(new ItemStack(itemID, 1, EvilScrollRegistry.instance().getMetadata(i)));
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public String getItemDisplayName(ItemStack par1ItemStack)
	{
		String var2 = ("" + StatCollector.translateToLocal(this.getItemName() + ".name")).trim();
		String var3 = EvilScrollRegistry.instance().getEntityNameFromMetadata(par1ItemStack.getItemDamage());

		if (var3 != null)
		{
			var2 = StatCollector.translateToLocal("entity." + var3 + ".name") + " " + var2;
		}

		return var2;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int getColorFromItemStack(ItemStack itemstack, int renderPass)
	{
		int damage = itemstack.getItemDamage();
		int primaryColor = EvilScrollRegistry.instance().getPrimaryColorFromMetadata(damage);
		int secondaryColor = EvilScrollRegistry.instance().getSecondaryColorFromMetadata(damage);
		return renderPass > 0 ? secondaryColor : primaryColor;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public boolean requiresMultipleRenderPasses()
	{
		return true;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int getIconFromDamageForRenderPass(int damage, int renderPass)
	{
		return renderPass > 0 ? super.getIconFromDamageForRenderPass(damage, renderPass) + 1 : super.getIconFromDamageForRenderPass(damage, renderPass);
	}
}
