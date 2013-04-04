package rgn.mods.mabicraft.item;

import java.util.List;
import java.util.Random;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.Icon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemEnchantScroll extends Item
{
	private Random random = new Random();

	@SideOnly(Side.CLIENT)
	private Icon secondaryIcon;

	public ItemEnchantScroll(int itemId)
	{
		super(itemId);
		this.setMaxDamage(100);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateIcons(IconRegister par1IconRegister)
	{
		this.iconIndex     = par1IconRegister.registerIcon("rgn/mabicraft:EnchantScroll1");
		this.secondaryIcon = par1IconRegister.registerIcon("rgn/mabicraft:EnchantScroll2");
	}

	@Override
	public boolean getShareTag()
	{
		return true;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public boolean hasEffect(ItemStack itemStack)
	{
		return true;
	}

	@Override
	public boolean isItemTool(ItemStack itemtack)
	{
		return false;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean par4)
	{
		super.addInformation(itemStack, player, list, par4);
		NBTTagList nbtTagList = null;

		if (itemStack.hasTagCompound() && itemStack.getTagCompound().hasKey("scroll"))
		{
			nbtTagList = itemStack.getTagCompound().getTagList("scroll");
		}

		if (nbtTagList != null)
		{
			for (int idx = 0; idx < nbtTagList.tagCount(); ++idx)
			{
				short id  = ((NBTTagCompound)nbtTagList.tagAt(idx)).getShort("id");
				short lvl = ((NBTTagCompound)nbtTagList.tagAt(idx)).getShort("lvl");
				if (Enchantment.enchantmentsList[id] != null)
				{
					list.add(Enchantment.enchantmentsList[id].getTranslatedName(lvl));
				}
			}
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public EnumRarity getRarity(ItemStack par1ItemStack)
	{
		return EnumRarity.uncommon;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int getColorFromItemStack(ItemStack itemStack, int renderPass)
	{
		int color = 0xEEEEEE;
		if (itemStack.hasTagCompound())
		{
			NBTTagCompound nbtTagCompound = itemStack.getTagCompound().getCompoundTag("colorInfo");

			if (nbtTagCompound != null)
			{
				int red   = (int)nbtTagCompound.getShort("red")   & 0xFF;
				int green = (int)nbtTagCompound.getShort("green") & 0xFF;
				int blue  = (int)nbtTagCompound.getShort("blue")  & 0xFF;
				color = (red << 16) + (green << 8) + blue;
			}
		}
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
	public Icon getIconFromDamageForRenderPass(int damage, int renderPass)
	{
		return renderPass > 0 ? this.secondaryIcon : this.iconIndex;
	}

	public void setColorInfoToNBT(ItemStack itemStack)
	{
		NBTTagCompound nbtTagCompound = new NBTTagCompound();
		int red   = random.nextInt(255);
		int green = random.nextInt(255);
		int blue  = random.nextInt(255);
		nbtTagCompound.setShort("red",   (short)red);
		nbtTagCompound.setShort("green", (short)green);
		nbtTagCompound.setShort("blue",  (short)blue);
		itemStack.setTagInfo("colorInfo", nbtTagCompound);
	}
}
