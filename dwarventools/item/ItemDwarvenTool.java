package rgn.mods.dwarventools.item;

import java.util.Set;

import org.bouncycastle.util.Strings;

import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemDwarvenTool extends ItemTool
{
	private int rangeLevel;

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

	protected ItemDwarvenTool(int itemId, int baseDamage, EnumToolMaterial material, Block[] harvestBlocks)
	{
		super(itemId, 0, material, harvestBlocks);
		this.rangeLevel = (material == DwarvenItem.enumToolMaterialMithril ? 1 : (material == DwarvenItem.enumToolMaterialEbony ? 1 : 0));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void func_94581_a(IconRegister par1IconRegister)
	{
		this.iconIndex = par1IconRegister.func_94245_a(String.format("rgn/dwarventools:%s", Strings.split(this.getUnlocalizedName(), '.')[1]));
	}

	@Override
	public boolean onBlockDestroyed(ItemStack itemstack, World world, int blockId, int x, int y, int z, EntityLiving entityliving)
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

	protected boolean canEffectiveAgainst(Block block)
	{
		return false;
	}

	private void destroyAroundBlock(ItemStack itemstack, World world, int blockId, int x, int y, int z, EntityLiving entityliving)
	{
		int facing = BlockPistonBase.determineOrientation(world, x, y, z, (EntityPlayer)entityliving);

		int sumDamage = this.destroy(facing, world, itemstack, blockId, x, y, z, entityliving);

		itemstack.damageItem(sumDamage, entityliving);
	}

	private int destroy(int facing, World world, ItemStack itemstack, int blockId, int x, int y, int z, EntityLiving entityliving)
	{
		Set<Coord> targetsSet;

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

		int targetBlockId;
		int targetBlockMetadata;
		int damage = 0;

		for (Coord target : targetsSet)
		{
			targetBlockId = world.getBlockId(target.x, target.y, target.z);
			targetBlockMetadata = world.getBlockMetadata(target.x, target.y, target.z);

			if (Block.blocksList[targetBlockId] == Block.bedrock)
			{
				continue ;
			}

			if (!world.isAirBlock(target.x, target.y, target.z))
			{
				if (this.canHarvestBlock(Block.blocksList[targetBlockId]))
				{
					if (Block.blocksList[targetBlockId] == Block.silverfish)
					{
						Block.blocksList[targetBlockId].onBlockDestroyedByPlayer(world, target.x, target.y, target.z, targetBlockMetadata);
					}
					else
					{
						Block.blocksList[targetBlockId].dropBlockAsItemWithChance(world, target.x, target.y, target.z, targetBlockMetadata, 1.0F, 0);
					}
				}
				world.func_94571_i(target.x, target.y, target.z);
				++damage;
			}
		}

		return damage;
	}

	private Set<Coord> setPositionsConstantX(int x, int y, int z)
	{
		Set<Coord> targets = Sets.newHashSet();

		for (int i = -1; i <= 1; ++i)
		{
			for (int j = -1; j <= 1; ++j)
			{
				targets.add((new Coord(x, y + i, z + j)));
			}
		}

		return targets;
	}

	private Set<Coord> setPositionsConstantY(int x, int y, int z)
	{
		Set<Coord> targets = Sets.newHashSet();

		for (int i = -1; i <= 1; ++i)
		{
			for (int j = -1; j <= 1; ++j)
			{
				targets.add((new Coord(x + i, y, z + j)));
			}
		}

		return targets;
	}


	private Set<Coord> setPositionsConstantZ(int x, int y, int z)
	{
		Set<Coord> targets = Sets.newHashSet();

		for (int i = -1; i <= 1; ++i)
		{
			for (int j = -1; j <= 1; ++j)
			{
				targets.add((new Coord(x + i, y + j, z)));
			}
		}

		return targets;
	}
}
