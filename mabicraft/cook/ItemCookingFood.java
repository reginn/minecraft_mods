package rgn.mods.mabicraft.cook;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import rgn.mods.mabicraft.config.Config;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemCookingFood extends ItemFood
{
	private int healAmount;
	private float saturationModifier;

	private int potionId;
	private int potionDuration;
	private int potionAmplifier;
	private float potionEffectProbability;

	private List<PotionEffect> potionEffects;

	public ItemCookingFood(int itemId)
	{
		super(itemId, 0, true);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		this.setCreativeTab(Config.tabMabiCraft);
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
	public String getTextureFile()
	{
		return "/rgn/sprites/mabicraft/items.png";
	}

	@Override
	public int getIconFromDamage(int damage)
    {
		return this.iconIndex + damage;
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
	public String getItemNameIS(ItemStack itemstack)
	{
		return (new StringBuilder()).append(getItemName()).append(".").append(CookingFoodDictionary.getName(itemstack.getItemDamage())).toString();
	}

	@Override
	public ItemStack onFoodEaten(ItemStack itemstack, World world, EntityPlayer player)
	{
		this.setVarietyFromDamage(itemstack);
		--itemstack.stackSize;
		player.getFoodStats().addStats(this);
		world.playSoundAtEntity(player, "random.burp", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
		this.func_77849_c(itemstack, world, player);
		return itemstack;
	}

	@Override
	protected void func_77849_c(ItemStack itemstack, World world, EntityPlayer player)
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