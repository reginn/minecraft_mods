package rgn.mods.dwarventools;

import java.io.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;

import net.minecraft.src.*;
import net.minecraftforge.common.*;

public class ItemDwarvenTool extends ItemTool
{
	private Block[] blocksEffectiveAgainst;
	
	protected int rangeLevel;
	
	protected ItemDwarvenTool(int itemId, int baseDamage, EnumToolMaterial material, Block[] harvestBlocks)
	{
		super(itemId, 0, material, harvestBlocks);
		this.rangeLevel = (material == DwarvenTools.enumToolMaterialMithril ? 1 : (material == DwarvenTools.enumToolMaterialEbony ? 1 : 0));
	}
	
	@Override
	public String getTextureFile()
	{
		return "/rgn/sprites/dwarventools/items.png";
	}
	
	@Override
	public boolean func_77660_a(ItemStack itemstack, World world, int blockId, int x, int y, int z, EntityLiving entityliving)
	{
		itemstack.damageItem(1, entityliving);
		int metadata = world.getBlockMetadata(x, y, z);
		
		if (this.rangeLevel == 0 || !this.canHarvestBlock(Block.blocksList[blockId]))
		{
			return true;
		}
		
		this.destroyAroundBlock(itemstack, world, blockId, x, y, z, entityliving);
		return true;
	}
	
	private void destroyAroundBlock(ItemStack itemstack, World world, int blockId, int x, int y, int z, EntityLiving entityliving)
	{
		int facing = BlockPistonBase.determineOrientation(world, x, y, z, (EntityPlayer)entityliving);
		
		int sumDamage = this.destroy(facing, world, itemstack, blockId, x, y, z, entityliving);
		
		itemstack.damageItem(sumDamage, entityliving);
	}
	
	private int destroy(int facing, World world, ItemStack itemstack, int blockId, int x, int y, int z, EntityLiving entityliving)
	{
		Set<ChunkPosition> targetsSet;
		
		switch (facing)
		{
			case 0 :
			case 1 :
				targetsSet = this.setPositionsConstantY(x, y, z);
				break;
				
			case 2 :
			case 3 :
				targetsSet = this.setPositionsConstantZ(x, y, z);
				break;
				
			case 4 :
			case 5 :
				targetsSet = this.setPositionsConstantX(x, y, z);
				break;
				
			default :
				return 0;
		}
		
		if (targetsSet.isEmpty())
		{
			return 0;
		}
		
		Iterator iter = targetsSet.iterator();
		ChunkPosition target;
		int targetBlockId;
		int targetBlockMetadata;
		int damage = 0;
		while(iter.hasNext())
		{
			target = (ChunkPosition)iter.next();
			targetBlockId = world.getBlockId(target.x, target.y, target.z);
			targetBlockMetadata = world.getBlockMetadata(target.x, target.y, target.z);
			
			if (Block.blocksList[targetBlockId] == Block.bedrock)
			{
				continue ;
			}
			
			if (!world.isAirBlock(target.x, target.y, target.z) && this.canHarvestBlock(Block.blocksList[targetBlockId]))
			{
				Block.blocksList[targetBlockId].dropBlockAsItemWithChance(world, target.x, target.y, target.z, targetBlockMetadata, 1.0F, 0);
				world.setBlockWithNotify(target.x, target.y, target.z, 0);
				
				++damage;
			}
		}
		return damage;
	}
	
	private Set<ChunkPosition> setPositionsConstantX(int x, int y, int z)
	{
		Set<ChunkPosition> targets = new HashSet<ChunkPosition>();
		
		int limit = this.rangeLevel;
		
		for (int i = -limit; i <= limit; i++)
		{
			for (int j = -limit; j <= limit; j++)
			{
				targets.add((new ChunkPosition(x, y + i, z + j)));
			}
		}
		
		return targets;
	}
	
	private Set<ChunkPosition> setPositionsConstantY(int x, int y, int z)
	{
		Set<ChunkPosition> targets = new HashSet<ChunkPosition>();
		
		int limit = this.rangeLevel;
		
		for (int i = -limit; i <= limit; i++)
		{
			for (int j = -limit; j <= limit; j++)
			{
				targets.add((new ChunkPosition(x + i, y, z + j)));
			}
		}
		
		return targets;
	}
	
	
	private Set<ChunkPosition> setPositionsConstantZ(int x, int y, int z)
	{
		Set<ChunkPosition> targets = new HashSet<ChunkPosition>();
		
		int limit = this.rangeLevel;
		
		for (int i = -limit; i <= limit; i++)
		{
			for (int j = -limit; j <= limit; j++)
			{
				targets.add((new ChunkPosition(x + i, y + j, z)));
			}
		}
		
		return targets;
	}
}
