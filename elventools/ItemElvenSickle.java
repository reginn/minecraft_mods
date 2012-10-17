package rgn.mods.elventools;

import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;

import net.minecraft.src.*;

public class ItemElvenSickle extends ItemTool
{
	public static Block[] blocksEffectiveAgainst = 
		{
			Block.tallGrass, Block.plantRed, Block.plantYellow, Block.mushroomBrown, 
			Block.mushroomRed, Block.crops, Block.reed, Block.netherStalk
		};
	
	private Set<Block> toolEffective = new HashSet<Block>();
	
	public ItemElvenSickle(int itemId, EnumToolMaterial toolMaterial)
	{
		super(itemId, 0, toolMaterial, blocksEffectiveAgainst);
		
		for (int i = 0; i < blocksEffectiveAgainst.length; ++i)
		{
			this.toolEffective.add(blocksEffectiveAgainst[i]);
		}
	}
	
	@Override
	public String getTextureFile()
	{
		return "/rgn/sprites/elventools/items.png";
	}
	
	@Override
	public boolean func_77660_a(ItemStack itemstack, World world, int blockId, int x, int y, int z, EntityLiving entityliving)
	{
		itemstack.damageItem(1, entityliving);
		
		if (!this.isToolEffective(Block.blocksList[blockId]))
		{
			return true;
		}
		
		this.destroyAroundBlock(itemstack, world, blockId, x, y, z, entityliving);
		return true;
	}
	
	private boolean isToolEffective(Block block)
	{
		if (!this.toolEffective.isEmpty())
		{
			return this.toolEffective.contains(block);
		}
		return false;
	}
	
	private void destroyAroundBlock(ItemStack itemstack, World world, int blockId, int x, int y, int z, EntityLiving entityliving)
	{
		int sumDamage = this.destroy(world, itemstack, blockId, x, y, z, entityliving);
		
		itemstack.damageItem(sumDamage, entityliving);
	}
	
	private int destroy(World world, ItemStack itemstack, int blockId, int x, int y, int z, EntityLiving entityliving)
	{
		Set<ChunkPosition> targetsSet;
		
		targetsSet = setPositionsConstantY(x, y, z);
		
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
			
			if (!world.isAirBlock(target.x, target.y, target.z) && this.isToolEffective(Block.blocksList[targetBlockId]))
			{
				Block.blocksList[targetBlockId].dropBlockAsItemWithChance(world, target.x, target.y, target.z, targetBlockMetadata, 1.0F, 0);
				world.setBlockWithNotify(target.x, target.y, target.z, 0);
				
				++damage;
			}
		}
		return damage;
	}
	
	private Set<ChunkPosition> setPositionsConstantY(int x, int y, int z)
	{
		Set<ChunkPosition> targets = new HashSet<ChunkPosition>();
		
		int limit = 2;
		
		for (int i = -limit; i <= limit; i++)
		{
			for (int j = -limit; j <= limit; j++)
			{
				targets.add((new ChunkPosition(x + i, y, z + j)));
			}
		}
		
		return targets;
	}
}