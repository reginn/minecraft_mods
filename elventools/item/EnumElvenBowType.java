package rgn.mods.elventools.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public enum EnumElvenBowType
{
	/*
		Type(SpriteID, durability, baseDamage, velocityRatio, enchantability)
	*/
	 //VANILA( 0,  384, 2.0F, 1.00F,  1),
	  LEATHER( 0,  512, 2.0F, 1.00F, 15),
	COMPOSITE(16,  768, 2.0F, 1.25F, 10),
	 ENHANCED(32, 2048, 2.0F, 1.50F,  1),
	     BONE( 8,   65, 4.0F, 1.00F,  0),
	   SHADOW(24,  128, 2.0F, 1.00F,  0),
	      END( 4,  256, 2.0F, 1.00F,  0),
	  FEATHER(40,  256, 2.0F, 1.00F,  0),
	    STEEL(20, 2048, 4.0F, 1.50F,  1),
	    ELVEN(36, 1024, 2.0F, 1.00F,  1);

	private int spriteId;
	private int durability;
	private float baseDamage;
	private float velocityRatio;
	private int enchantability;

	@SideOnly(Side.CLIENT)
	private Icon icon;
	@SideOnly(Side.CLIENT)
	private Icon animation[];

	private EnumElvenBowType(int spriteId, int durability, float baseDamage, float velocityRatio, int enchantability)
	{
		this.spriteId       = spriteId;
		this.durability     = durability;
		this.baseDamage     = baseDamage;
		this.velocityRatio  = velocityRatio;
		this.enchantability = enchantability;
	}

	public int getSpriteId()
	{
		return this.spriteId;
	}

	public int getDurability()
	{
		return this.durability;
	}

	public float getBaseDamage()
	{
		return this.baseDamage;
	}

	public float getVelocityRatio()
	{
		return this.velocityRatio;
	}

	public int getEnchantability()
	{
		return this.enchantability;
	}

	@SideOnly(Side.CLIENT)
	public void createIcon(IconRegister iconRegister)
	{
		this.animation = new Icon[3];
		this.icon = iconRegister.func_94245_a(String.format("rgn/elventools:%s%d", name().toLowerCase(), 0));
		for (int i = 0; i < 3; ++i)
		{
			this.animation[i] = iconRegister.func_94245_a(String.format("rgn/elventools:%s%d", name().toLowerCase(), i + 1));
		}
	}

	@SideOnly(Side.CLIENT)
	public Icon getBaseIcon()
	{
		return this.icon;
	}

	@SideOnly(Side.CLIENT)
	public Icon[] getAnimation()
	{
		return this.animation;
	}
}