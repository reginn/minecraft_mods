package rgn.mods.mabicraft.item;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import rgn.mods.mabicraft.item.cooking.CookingFoodDictionary;
import rgn.mods.mabicraft.item.cooking.FoodProperty;

public class ItemCookingFood extends ItemFood
{
	private int healAmount;
	private float saturationModifier;

	private int potionId;
	private int potionDuration;
	private int potionAmplifier;
	private float potionEffectProbability;

	private List<PotionEffect> potionEffects;

	@SideOnly(Side.CLIENT)
	private Map<Integer, Icon> icons;

	public ItemCookingFood(int itemId)
	{
		super(itemId, 0, true);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateIcons(IconRegister par1IconRegister)
	{
		this.icons = Maps.newHashMap();
		for (String str : CookingFoodDictionary.getNames())
		{
			this.icons.put(CookingFoodDictionary.getDamage(str), par1IconRegister.registerIcon(String.format("rgn/mabicraft:%s", str)));
		}
		this.iconIndex = null;
	}

	private void setVarietyFromDamage(ItemStack itemstack)
	{
		int damage = itemstack.getItemDamage();

		FoodProperty foodProperty = CookingFoodDictionary.getFoodPropertyFromDamage(damage);

		this.healAmount         = foodProperty.getHealAmount();
		this.saturationModifier = foodProperty.getSaturationModifier();

		this.potionEffects = CookingFoodDictionary.getPotionEffectsFromDamage(damage);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int damage)
	{
		return this.icons.get(damage);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void getSubItems(int itemID, CreativeTabs creativeTabs, List list)
	{
		for (String str : CookingFoodDictionary.getNames())
		{
			list.add(CookingFoodDictionary.getCookingFood(str));
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public String getUnlocalizedName(ItemStack itemstack)
	{
		return (new StringBuilder()).append(this.getUnlocalizedName()).append(".").append(CookingFoodDictionary.getName(itemstack.getItemDamage())).toString();
	}

	@Override
	public ItemStack onEaten(ItemStack itemstack, World world, EntityPlayer player)
	{
		this.setVarietyFromDamage(itemstack);
		--itemstack.stackSize;
		player.getFoodStats().addStats(this);
		world.playSoundAtEntity(player, "random.burp", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
		this.onFoodEaten(itemstack, world, player);
		return itemstack;
	}

	@Override
	protected void onFoodEaten(ItemStack itemstack, World world, EntityPlayer player)
	{
		if (!world.isRemote && this.potionEffects != null && this.potionEffects.size() > 0)
		{
			for (PotionEffect potionEffect : this.potionEffects)
			{
				player.addPotionEffect(potionEffect);
			}
		}
	}

	@Override
	public int getHealAmount()
	{
		return this.healAmount;
	}

	@Override
	public float getSaturationModifier()
	{
		return this.saturationModifier;
	}

	@Override
	public boolean isWolfsFavoriteMeat()
	{
		return false;
	}
}