package rgn.mods.mabicraft.enchant;

import net.minecraft.src.*;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

public class ItemEnchantScroll extends Item
{
	public ItemEnchantScroll(int itemId)
	{
		super(itemId);
		this.setMaxStackSize(1);
	}
	
	@Override
	public String getTextureFile()
	{
		return "/rgn/sprites/mabicraft/items.png";
	}
	
	@Override
	public boolean getShareTag()
	{
		return true;
	}
	
	@SideOnly(Side.CLIENT)
	public int getColorFromDamage(int damage, int renderPass)
	{
		int red   = (damage >>> 7)        % 0xFF;
		int green = ((damage << 3) >>> 7) % 0xFF;
		int blue  = (damage)              % 0xFF;
		int color = (red << 16) + (green << 8) + blue;
		return renderPass > 0 ? color : 0xEEEEEE;
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
