package rgn.mods.elventools.item;

import java.util.Iterator;
import java.util.Set;

import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.world.World;

import net.minecraftforge.common.IPlantable;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemElvenSickle extends ItemTool
{
	public static Block[] blocksEffectiveAgainst =
		{
			Block.tallGrass, Block.plantRed, Block.plantYellow, Block.mushroomBrown,
			Block.mushroomRed, Block.crops, Block.reed, Block.netherStalk
		};

	private Set<Block> toolEffective = Sets.newHashSet(blocksEffectiveAgainst);

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
	@SideOnly(Side.CLIENT)
	public void func_94581_a(IconRegister par1IconRegister)
	{
		this.iconIndex = par1IconRegister.func_94245_a("rgn/elventools:elvenSickle");
	}

	@Override
	public boolean onBlockDestroyed(ItemStack itemstack, World world, int blockId, int x, int y, int z, EntityLiving entityliving)
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

	private void destroyAroundBlock(ItemStack itemstack, World world, int blockId, int x, int y, int z, EntityLiving entityliving)
	{
		int sumDamage = this.destroy(world, itemstack, blockId, x, y, z, entityliving);

		itemstack.damageItem(sumDamage, entityliving);
	}

	private int destroy(World world, ItemStack itemstack, int blockId, int x, int y, int z, EntityLiving entityliving)
	{
		Set<Coord> targetsSet;

		targetsSet = setPositionsConstantY(x, y, z);

		if (targetsSet.isEmpty())
		{
			return 0;
		}

		Iterator iter = targetsSet.iterator();
		Coord target;
		int targetBlockId;
		int targetBlockMetadata;
		int damage = 0;
		while(iter.hasNext())
		{
			target = (Coord)iter.next();
			targetBlockId = world.getBlockId(target.x, target.y, target.z);
			targetBlockMetadata = world.getBlockMetadata(target.x, target.y, target.z);

			if (Block.blocksList[targetBlockId] == Block.bedrock)
			{
				continue ;
			}

			if (!world.isAirBlock(target.x, target.y, target.z) && this.isToolEffective(Block.blocksList[targetBlockId]))
			{
				Block.blocksList[targetBlockId].dropBlockAsItemWithChance(world, target.x, target.y, target.z, targetBlockMetadata, 1.0F, 0);
				world.func_94575_c(target.x, target.y, target.z, 0);

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