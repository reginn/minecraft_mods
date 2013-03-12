package rgn.mods.dwarventools.generate;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import net.minecraftforge.common.ChestGenHooks;

public class WorldGenDungeonsBase extends WorldGenerator
{
	protected final String DUNGEON_CHEST = ChestGenHooks.DUNGEON_CHEST;

	protected String mobType;
	protected int xRange;
	protected int zRange;

	protected WorldGenDungeonsBase(String _mobType, int _xRange, int _zRange)
	{
		this.mobType = _mobType;
		this.xRange  = _xRange;
		this.zRange  = _zRange;
	}

	@Override
	public boolean generate(World world, Random random, int xCoord, int yCoord, int zCoord)
	{
		int yRange = 3;
		int xRange = random.nextInt(2) + this.xRange;
		int zRange = random.nextInt(2) + this.zRange;
		int var9 = 0;
		int x, y, z;

		for (x = xCoord - xRange - 1; x <= xCoord + xRange + 1; ++x)
		{
			for (y = yCoord - 1; y <= yCoord + yRange + 1; ++y)
			{
				for (z = zCoord - zRange - 1; z <= zCoord + zRange + 1; ++z)
				{
					Material var13 = world.getBlockMaterial(x, y, z);

					if (y == yCoord - 1 && !var13.isSolid())
					{
						return false;
					}

					if (y == yCoord + yRange + 1 && !var13.isSolid())
					{
						return false;
					}

					if ((x == xCoord - xRange - 1 || x == xCoord + xRange + 1
					  || z == zCoord - zRange - 1 || z == zCoord + zRange + 1)
					  && y == yCoord && world.isAirBlock(x, y, z) && world.isAirBlock(x, y + 1, z))
					{
						++var9;
					}
				}
			}
		}

		if (var9 >= 1 && var9 <= 5)
		{
			for (x = xCoord - xRange - 1; x <= xCoord + xRange + 1; ++x)
			{
				for (y = yCoord + yRange; y >= yCoord - 1; --y)
				{
					for (z = zCoord - zRange - 1; z <= zCoord + zRange + 1; ++z)
					{
						if (x != xCoord - xRange - 1 && y != yCoord - 1 && z != zCoord - zRange - 1 &&
							x != xCoord + xRange + 1 && y != yCoord + yRange + 1 && z != zCoord + zRange + 1)
						{
							world.func_94571_i(x, y, z);
						}
						else if (y >= 0 && !world.getBlockMaterial(x, y - 1, z).isSolid())
						{
							world.func_94571_i(x, y, z);
						}
						else if (world.getBlockMaterial(x, y, z).isSolid())
						{
							if (y == yCoord - 1 && random.nextInt(4) != 0)
							{

								this.setRandomBlock(world, x, y, z);
							}
							else
							{

								this.setBaseBlock(world, x, y, z);
							}
						}
					}
				}
			}

			x = 0;

			while (x < 2)
			{
				y = 0;

				while (true)
				{
					if (y < 3)
					{
						label210:
						{
							z = xCoord + random.nextInt(xRange * 2 + 1) - xRange;
							int var14 = zCoord + random.nextInt(zRange * 2 + 1) - zRange;

							if (world.isAirBlock(z, yCoord, var14))
							{
								int var15 = 0;

								if (world.getBlockMaterial(z - 1, yCoord, var14).isSolid())
								{
									++var15;
								}

								if (world.getBlockMaterial(z + 1, yCoord, var14).isSolid())
								{
									++var15;
								}

								if (world.getBlockMaterial(z, yCoord, var14 - 1).isSolid())
								{
									++var15;
								}

								if (world.getBlockMaterial(z, yCoord, var14 + 1).isSolid())
								{
									++var15;
								}

								if (var15 == 1)
								{
									this.generateChest(world, z, yCoord, var14, random);
									break label210;
								}
							}

						++y;
						continue;
						}
					}

					++x;
					break;
				}
			}

			world.func_94575_c(xCoord, yCoord, zCoord, Block.mobSpawner.blockID);
			TileEntityMobSpawner tileEntityMobSpawner = (TileEntityMobSpawner)world.getBlockTileEntity(xCoord, yCoord, zCoord);

			if (tileEntityMobSpawner != null)
			{
				tileEntityMobSpawner.func_98049_a().func_98272_a(this.mobType);
			}
			else
			{
				System.err.println("Failed to fetch mob spawner entity at (" + xCoord + ", " + yCoord + ", " + zCoord + ")");
			}

			return true;
		}
		else
		{
			return false;
		}
	}

	protected void setRandomBlock(World world, int x, int y, int z)
	{
		world.func_94575_c(x, y, z, Block.stone.blockID);
	}

	protected void setBaseBlock(World world, int x, int y, int z)
	{
		world.func_94575_c(x, y, z, Block.stone.blockID);
	}

	protected void generateChest(World world, int x, int y, int z, Random random)
	{
		world.func_94575_c(x, y, z, Block.chest.blockID);
		TileEntityChest tileEntityChest = (TileEntityChest)world.getBlockTileEntity(x, y, z);

		if (tileEntityChest != null)
		{
			for (int itemNum = 0; itemNum < ChestGenHooks.getInfo(DUNGEON_CHEST).getMax(); ++itemNum)
			{
				ItemStack chestInItem = ChestGenHooks.getOneItem(DUNGEON_CHEST, random);

				if (chestInItem != null)
				{
					tileEntityChest.setInventorySlotContents(random.nextInt(tileEntityChest.getSizeInventory()), chestInItem);
				}
			}
		}
	}
}
