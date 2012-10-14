package rgn.mods.mabicraft;

import java.io.*;

import net.minecraft.src.*;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

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
	public String getItemDisplayName(ItemStack par1ItemStack)
	{
		String var2 = ("" + StatCollector.translateToLocal(this.getItemName() + ".name")).trim();
		String var3 = EntityList.getStringFromID(par1ItemStack.getItemDamage());
		
		if (var3 != null)
		{
			var2 = StatCollector.translateToLocal("entity." + var3 + ".name") + " " + var2;
		}
		
		return var2;
	}
	
	@SideOnly(Side.CLIENT)
	public int getColorFromDamage(int damage, int renderPass)
	{
		EntityEggInfo var3 = (EntityEggInfo)EntityList.entityEggs.get(Integer.valueOf(damage));
		return var3 != null ? (renderPass > 0 ? var3.secondaryColor : var3.primaryColor) : 0xEEEEEE;
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
