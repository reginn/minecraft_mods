package rgn.mods.elventools.block;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import net.minecraftforge.common.IShearable;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockEbonyLeaves extends BlockLeavesBase implements IShearable
{
	int[] adjacentTreeBlocks;

	public BlockEbonyLeaves(int blockId)
	{
		super(blockId, Material.leaves, true);
		this.setHardness(0.2F);
		this.setLightOpacity(1);
		this.setStepSound(soundGrassFootstep);
		this.setTickRandomly(true);
	}


	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.blockIcon = par1IconRegister.registerIcon("rgn/elventools:ebonyLeaves");
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, int par5, int par6)
	{
		if (world.checkChunksExist(x - 2, y - 2, z - 2, x + 2, y + 2, z + 2))
		{
			for (int i = -1; i <= 1; i++)
			{
				for (int j = -1; j <= 1; j++)
				{
					for (int k = -1; k <= 1; k++)
					{
						int neighborBlockId = world.getBlockId(x + i, y + j, z + k);
						if (Block.blocksList[neighborBlockId] != null)
						{
							Block.blocksList[neighborBlockId].beginLeavesDecay(world, x + i, y + j, z + k);
						}
					}
				}
			}
		}
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random random)
	{
		if (!world.isRemote)
		{
			int metadata = world.getBlockMetadata(x, y, z);

			if ((metadata & 8) != 0 && (metadata & 4) == 0)
			{
				int sustainableRange = 4;
				int unsustainableRange = sustainableRange + 1;
				byte var9 = 32;
				int var10 = var9 * var9;
				int var11 = var9 / 2;

				if (this.adjacentTreeBlocks == null)
				{
					this.adjacentTreeBlocks = new int[var9 * var9 * var9];
				}

				int var12;

				if (world.checkChunksExist( x - unsustainableRange, y - unsustainableRange, z - unsustainableRange,
											x + unsustainableRange, y + unsustainableRange, z + unsustainableRange))
				{
					int var13;
					int var14;
					int var15;

					for (var12 = -sustainableRange; var12 <= sustainableRange; ++var12)
					{
						for (var13 = -sustainableRange; var13 <= sustainableRange; ++var13)
						{
							for (var14 = -sustainableRange; var14 <= sustainableRange; ++var14)
							{
								int ebonyLogBlockId = world.getBlockId(x + var12, y + var13, z + var14);

								Block block = Block.blocksList[ebonyLogBlockId];

								if (block != null && block.canSustainLeaves(world, x + var12, y + var13, z + var14))
								{
									this.adjacentTreeBlocks[(var12 + var11) * var10 + (var13 + var11) * var9 + var14 + var11] = 0;
								}
								else if (block != null && block.isLeaves(world, x + var12, y + var13, z + var14))
								{
									this.adjacentTreeBlocks[(var12 + var11) * var10 + (var13 + var11) * var9 + var14 + var11] = -2;
								}
								else
								{
									this.adjacentTreeBlocks[(var12 + var11) * var10 + (var13 + var11) * var9 + var14 + var11] = -1;
								}
							}
						}
					}

					for (var12 = 1; var12 <= 4; ++var12)
					{
						for (var13 = -sustainableRange; var13 <= sustainableRange; ++var13)
						{
							for (var14 = -sustainableRange; var14 <= sustainableRange; ++var14)
							{
								for (var15 = -sustainableRange; var15 <= sustainableRange; ++var15)
								{
									if (this.adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11) * var9 + var15 + var11] == var12 - 1)
									{
										if (this.adjacentTreeBlocks[(var13 + var11 - 1) * var10 + (var14 + var11) * var9 + var15 + var11] == -2)
										{
											this.adjacentTreeBlocks[(var13 + var11 - 1) * var10 + (var14 + var11) * var9 + var15 + var11] = var12;
										}

										if (this.adjacentTreeBlocks[(var13 + var11 + 1) * var10 + (var14 + var11) * var9 + var15 + var11] == -2)
										{
											this.adjacentTreeBlocks[(var13 + var11 + 1) * var10 + (var14 + var11) * var9 + var15 + var11] = var12;
										}

										if (this.adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11 - 1) * var9 + var15 + var11] == -2)
										{
											this.adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11 - 1) * var9 + var15 + var11] = var12;
										}

										if (this.adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11 + 1) * var9 + var15 + var11] == -2)
										{
											this.adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11 + 1) * var9 + var15 + var11] = var12;
										}

										if (this.adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11) * var9 + (var15 + var11 - 1)] == -2)
										{
											this.adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11) * var9 + (var15 + var11 - 1)] = var12;
										}

										if (this.adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11) * var9 + var15 + var11 + 1] == -2)
										{
											this.adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11) * var9 + var15 + var11 + 1] = var12;
										}
									}
								}
							}
						}
					}
				}

				var12 = this.adjacentTreeBlocks[var11 * var10 + var11 * var9 + var11];

				if (var12 >= 0)
				{
					world.setBlockMetadataWithNotify(x, y, z, metadata & -9, 3);
				}
				else
				{
					this.removeLeaves(world, x, y, z);
				}
			}
		}
	}

	private void removeLeaves(World world, int x, int y, int z)
	{
		dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
		world.setBlockToAir(x, y, z);
	}

	@Override
	public int quantityDropped(Random random)
	{
		return random.nextInt(20) != 0 ? 0 : 1;
	}

	@Override
	public int idDropped(int i, Random random, int j)
	{
		return ElvenBlock.blockEbonySapling.blockID;
	}

	@Override
	public void harvestBlock(World world, EntityPlayer entityplayer, int i, int j, int k, int l)
	{
		super.harvestBlock(world, entityplayer, i, j, k, l);
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public void onEntityWalking(World world, int x, int y, int z, Entity entity)
	{
		super.onEntityWalking(world, x, y, z, entity);
	}

	@Override
	public void beginLeavesDecay(World world, int x, int y, int z)
	{
		world.setBlockMetadataWithNotify(x, y, z, world.getBlockMetadata(x, y, z) | 8, 3);
	}

	@Override
	public boolean isLeaves(World world, int x, int y, int z)
	{
		return true;
	}

	// implements IShearable
	@Override
	public boolean isShearable(ItemStack item, World world, int X, int Y, int Z)
	{
		return true;
	}

	@Override
	public ArrayList<ItemStack> onSheared(ItemStack item, World world, int X, int Y, int Z, int fortune)
	{
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		ret.add(new ItemStack(ElvenBlock.blockEbonyLeaves.blockID, 1, world.getBlockMetadata(X, Y, Z)));
		return ret;
	}

}