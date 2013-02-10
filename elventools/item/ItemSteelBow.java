package rgn.mods.elventools.item;

import net.minecraft.item.ItemStack;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;


public class ItemSteelBow extends ItemElvenBow
{
	public ItemSteelBow(int itemId, EnumElvenBowType type)
	{
		super(itemId, type);
	}
	
	@Override
	public void onCreated(ItemStack itemstack, World world, EntityPlayer entityplayer)
	{
		super.onCreated(itemstack, world, entityplayer);

		itemstack.addEnchantment(Enchantment.punch, 2);
	}
}