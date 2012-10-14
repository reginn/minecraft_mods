package rgn.mods.lamp;

import net.minecraft.src.*;

public class ItemLamp extends ItemBlock
{
	private int blockID;
	
	public ItemLamp(int itemId)
	{
		super(itemId);
		setMaxDamage(0);
		setHasSubtypes(true);
	}
	
	@Override
	public String getItemNameIS(ItemStack itemstack)
	{
		String str = "";
		
		switch (itemstack.getItemDamage())
		{
			case 0 :
				str = "lantern";
				break;
				
			case 1 :
				str = "glowlantern";
				break;
				
			case 2 :
				str = "goldlantern";
				break;
			
			case 3 :
				str = "diamondlantern";
				break;
				
			default:
				str = "lantern";
				break;
		}
		
		return (new StringBuilder()).append(getItemName()).append(".").append(str).toString();
	}
	
	@Override
	public int getMetadata(int par1)
	{
		return par1;
	}


}