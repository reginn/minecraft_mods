package rgn.mods.elventools.item;

import java.util.Set;

import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.world.World;

import net.minecraftforge.common.IPlantable;

public class ItemElvenSickle extends ItemTool
{
	public static Block[] blocksEffectiveAgainst =
		{
			Block.tallGrass, Block.plantRed, Block.plantYellow, Block.mushroomBrown,
			Block.mushroomRed, Block.crops, Block.reed, Block.netherStalk
		};

	private Set<Block> toolEffective = Sets.newHashSet(blocksEffectiveAgainst);
	private Set<Block> ignores = Sets.newHashSet(Block.waterlily);

	public class Coord
	{
		int x;
		int y;
		int z;

		public Coord(int i, int j, int k)
		{
			x = i;
			y = j;
			z = k;
		}
	}

	public ItemElvenSickle(int itemId, EnumToolMaterial toolMaterial)
	{
		super(itemId, 0, toolMaterial, blocksEffectiveAgainst);
	}

	@Override
	public boolean onBlockDestroyed(ItemStack itemstack, World world, int blockId, int x, int y, int z, EntityLivingBase entityliving)
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
		return this.toolEffective.contains(block) || block instanceof IPlantable;
	}

	private boolean igonoreBlock(Block block)
	{
		return this.ignores.contains(block);
	}

	private void destroyAroundBlock(ItemStack itemstack, World world, int blockId, int x, int y, int z, EntityLivingBase entityliving)
	{
		int sumDamage = this.destroy(world, itemstack, blockId, x, y, z, entityliving);

		itemstack.damageItem(sumDamage, entityliving);
	}

	private int destroy(World world, ItemStack itemstack, int blockId, int x, int y, int z, EntityLivingBase entityliving)
	{
		Set<Coord> targetsSet;

		targetsSet = setPositionsConstantY(x, y, z);

		if (targetsSet.isEmpty())
		{
			return 0;
		}

		int damage = 0;

		for (Coord target : targetsSet)
		{
			Block block = Block.blocksList[world.getBlockId(target.x, target.y, target.z)];
			int metadata = world.getBlockMetadata(target.x, target.y, target.z);

			if (block == Block.bedrock)
			{
				continue ;
			}
			if (!world.isAirBlock(target.x, target.y, target.z) &&
				this.isToolEffective(block) &&
				!this.igonoreBlock(block))
			{
				block.dropBlockAsItemWithChance(world, target.x, target.y, target.z, metadata, 1.0F, 0);
				world.setBlockToAir(target.x, target.y, target.z);
				++damage;
			}
		}

		return damage;
	}

	private Set<Coord> setPositionsConstantY(int x, int y, int z)
	{
		Set<Coord> targets = Sets.newHashSet();

		for (int i = -2; i <= 2; ++i)
		{
			for (int j = -2; j <= 2; ++j)
			{
				targets.add((new Coord(x + i, y, z + j)));
			}
		}

		return targets;
	}
}