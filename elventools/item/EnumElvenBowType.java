package rgn.mods.elventools.item;

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
}