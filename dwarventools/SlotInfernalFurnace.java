package rgn.mods.dwarventools;

import net.minecraft.src.*;
import cpw.mods.fml.common.registry.*;

public class SlotInfernalFurnace extends Slot
{
	private EntityPlayer thePlayer;
	private int field_75228_b;
	
	public SlotInfernalFurnace(EntityPlayer par1EntityPlayer, IInventory par2IInventory, int par3, int par4, int par5)
	{
		super(par2IInventory, par3, par4, par5);
		this.thePlayer = par1EntityPlayer;
	}
	
	@Override
	public boolean isItemValid(ItemStack par1ItemStack)
	{
		return false;
	}
	
	@Override
	public ItemStack decrStackSize(int par1)
	{
		if (this.getHasStack())
		{
			this.field_75228_b += Math.min(par1, this.getStack().stackSize);
		}
		
		return super.decrStackSize(par1);
	}
	
	@Override
	// public void onPickupFromSlot(ItemStack par1ItemStack)
	public void func_82870_a(EntityPlayer par1EntityPlayer, ItemStack par1ItemStack)
	{
		this.onCrafting(par1ItemStack);
		// super.onPickupFromSlot(par1ItemStack);
		super.func_82870_a(par1EntityPlayer, par1ItemStack);
	}
	
	@Override
	protected void onCrafting(ItemStack par1ItemStack, int par2)
	{
		this.field_75228_b += par2;
		this.onCrafting(par1ItemStack);
	}
	
	@Override
	protected void onCrafting(ItemStack par1ItemStack)
	{
		par1ItemStack.onCrafting(this.thePlayer.worldObj, this.thePlayer, this.field_75228_b);
		
		if (!this.thePlayer.worldObj.isRemote)
		{
			int var2 = this.field_75228_b;
			float var3 = FurnaceRecipes.smelting().getExperience(par1ItemStack.itemID);
			int var4;
			
			if (var3 == 0.0F)
			{
				var2 = 0;
			}
			else if (var3 < 1.0F)
			{
				var4 = MathHelper.floor_float((float)var2 * var3);
				
				if (var4 < MathHelper.ceiling_float_int((float)var2 * var3) && (float)Math.random() < (float)var2 * var3 - (float)var4)
				{
					++var4;
				}
				
				var2 = var4;
			}
			
			while (var2 > 0)
			{
				var4 = EntityXPOrb.getXPSplit(var2);
				var2 -= var4;
				this.thePlayer.worldObj.spawnEntityInWorld(new EntityXPOrb(this.thePlayer.worldObj, this.thePlayer.posX, this.thePlayer.posY + 0.5D, this.thePlayer.posZ + 0.5D, var4));
			}
		}
		
		this.field_75228_b = 0;
		
		GameRegistry.onItemSmelted(thePlayer, par1ItemStack);
		
		if (par1ItemStack.itemID == Item.ingotIron.shiftedIndex)
		{
			this.thePlayer.addStat(AchievementList.acquireIron, 1);
		}
		
		if (par1ItemStack.itemID == Item.fishCooked.shiftedIndex)
		{
			this.thePlayer.addStat(AchievementList.cookFish, 1);
		}
	}
}
