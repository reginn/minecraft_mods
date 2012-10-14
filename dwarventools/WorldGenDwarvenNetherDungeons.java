package rgn.mods.dwarventools;

import java.util.Random;

import net.minecraft.src.*;

import net.minecraftforge.common.DungeonHooks;
import net.minecraftforge.common.MinecraftForge;

public class WorldGenDwarvenNetherDungeons extends WorldGenerator
{
	public boolean generate(World world, Random random, int xCoord, int yCoord, int zCoord)
	{
		int yRange = 3;
		int xRange = random.nextInt(2) + 1;
		int zRange = random.nextInt(2) + 1;
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
						if (x != xCoord - xRange - 1 && y != yCoord - 1 && z != zCoord - zRange - 1 && x != xCoord + xRange + 1 && y != yCoord + yRange + 1 && z != zCoord + zRange + 1)
						{
							world.setBlockWithNotify(x, y, z, 0);
						}
						else if (y >= 0 && !world.getBlockMaterial(x, y - 1, z).isSolid())
						{
							world.setBlockWithNotify(x, y, z, 0);
						}
						else if (world.getBlockMaterial(x, y, z).isSolid())
						{
							if (y == yCoord - 1 && random.nextInt(4) != 0)
							{
								world.setBlockWithNotify(x, y, z, Block.slowSand.blockID);
							}
							else
							{
								world.setBlockWithNotify(x, y, z, Block.netherrack.blockID);
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
									world.setBlockWithNotify(z, yCoord, var14, Block.chest.blockID);
									TileEntityChest tileEntityChest = (TileEntityChest)world.getBlockTileEntity(z, yCoord, var14);
									
									if (tileEntityChest != null)
									{
										for (int itemNum = 0; itemNum < DungeonHooks.getDungeonLootTries(); ++itemNum)
										{
											ItemStack chestInItem = DungeonHooks.getRandomDungeonLoot(random);
											
											if (chestInItem != null)
											{
												tileEntityChest.setInventorySlotContents(random.nextInt(tileEntityChest.getSizeInventory()), chestInItem);
											}
										}
									}
									
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
			
			world.setBlockWithNotify(xCoord, yCoord, zCoord, Block.mobSpawner.blockID);
			TileEntityMobSpawner var19 = (TileEntityMobSpawner)world.getBlockTileEntity(xCoord, yCoord, zCoord);
			
			if (var19 != null)
			{
				var19.setMobID("Blaze");
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
}
