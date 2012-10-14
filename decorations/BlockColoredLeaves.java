package rgn.mods.decorations;

import java.util.List;
import java.util.ArrayList;

import java.util.Random;

import net.minecraft.src.*;

import net.minecraftforge.common.IShearable;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

public class BlockColoredLeaves extends BlockLeavesBase implements IShearable
{
	private int baseIndexInPNG;
	public static final String[] types = new String[] {"cherry", "yellow", "orange", "red"};
	public static final int[]   colors = new int[] {0xFFC0CB, 0xFFDC21, 0xEA840B, 0xE8380D};
	int[] adjacentTreeBlocks;
	
	public BlockColoredLeaves(int blockId, int terrainId)
	{
		super(blockId, terrainId, Material.leaves, true);
		this.baseIndexInPNG = terrainId;
		this.setTickRandomly(true);
		this.setLightValue(10.0F/16.0F);
		this.setStepSound(soundGrassFootstep);
		this.setCreativeTab(CreativeTabs.tabDeco);
		this.setHardness(0.2F);
		this.setLightOpacity(1);
		this.setRequiresSelfNotify();
	}
	
	@Override
	public String getTextureFile()
	{
		return "/rgn/sprites/decorations/blocks.png";
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderColor(int metadata)
	{
		return colors[metadata & 3];
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess world, int x, int y, int z)
	{
		int metadata = world.getBlockMetadata(x, y, z);
		
		return colors[metadata & 3];
	}
	
	@Override
	public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6)
	{
		byte var7 = 1;
		int var8 = var7 + 1;
		
		if (par1World.checkChunksExist(par2 - var8, par3 - var8, par4 - var8, par2 + var8, par3 + var8, par4 + var8))
		{
			for (int var9 = -var7; var9 <= var7; ++var9)
			{
				for (int var10 = -var7; var10 <= var7; ++var10)
				{
					for (int var11 = -var7; var11 <= var7; ++var11)
					{
						int var12 = par1World.getBlockId(par2 + var9, par3 + var10, par4 + var11);
						
						if (Block.blocksList[var12] != null)
						{
							Block.blocksList[var12].beginLeavesDecay(par1World, par2 + var9, par3 + var10, par4 + var11);
						}
					}
				}
			}
		}
	}
	
	@Override
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
	{
		if (!par1World.isRemote)
		{
			int var6 = par1World.getBlockMetadata(par2, par3, par4);
			
			if ((var6 & 8) != 0 && (var6 & 4) == 0)
			{
				byte var7 = 4;
				int var8 = var7 + 1;
				byte var9 = 32;
				int var10 = var9 * var9;
				int var11 = var9 / 2;
				
				if (this.adjacentTreeBlocks == null)
				{
					this.adjacentTreeBlocks = new int[var9 * var9 * var9];
				}
				
				int var12;
				
				if (par1World.checkChunksExist(par2 - var8, par3 - var8, par4 - var8, par2 + var8, par3 + var8, par4 + var8))
				{
					int var13;
					int var14;
					int var15;
					
					for (var12 = -var7; var12 <= var7; ++var12)
					{
						for (var13 = -var7; var13 <= var7; ++var13)
						{
							for (var14 = -var7; var14 <= var7; ++var14)
							{
								var15 = par1World.getBlockId(par2 + var12, par3 + var13, par4 + var14);
								
								Block block = Block.blocksList[var15];
								
								if (block != null && block.canSustainLeaves(par1World, par2 + var12, par3 + var13, par4 + var14))
								{
									this.adjacentTreeBlocks[(var12 + var11) * var10 + (var13 + var11) * var9 + var14 + var11] = 0;
								}
								else if (block != null && block.isLeaves(par1World, par2 + var12, par3 + var13, par4 + var14))
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
						for (var13 = -var7; var13 <= var7; ++var13)
						{
							for (var14 = -var7; var14 <= var7; ++var14)
							{
								for (var15 = -var7; var15 <= var7; ++var15)
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
					par1World.setBlockMetadata(par2, par3, par4, var6 & -9);
				}
				else
				{
					this.removeLeaves(par1World, par2, par3, par4);
				}
			}
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
	{
		if (par1World.canLightningStrikeAt(par2, par3 + 1, par4) && !par1World.doesBlockHaveSolidTopSurface(par2, par3 - 1, par4) && par5Random.nextInt(15) == 1)
		{
			double var6 = (double)((float)par2 + par5Random.nextFloat());
			double var8 = (double)par3 - 0.05D;
			double var10 = (double)((float)par4 + par5Random.nextFloat());
			par1World.spawnParticle("dripWater", var6, var8, var10, 0.0D, 0.0D, 0.0D);
		}
	}
	
	private void removeLeaves(World par1World, int par2, int par3, int par4)
	{
		this.dropBlockAsItem(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), 0);
		par1World.setBlockWithNotify(par2, par3, par4, 0);
	}
	
	@Override
	public int quantityDropped(Random par1Random)
	{
		return par1Random.nextInt(20) == 0 ? 1 : 0;
	}
	
	@Override
	public int idDropped(int par1, Random par2Random, int par3)
	{
		return Decorations.blockColoredSapling.blockID;
	}
	
	@Override
	public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7)
	{
		if (!par1World.isRemote)
		{
			byte var8 = 20;
			
			if ((par5 & 3) == 3)
			{
				var8 = 40;
			}
			
			if (par1World.rand.nextInt(var8) == 0)
			{
				int var9 = this.idDropped(par5, par1World.rand, par7);
				this.dropBlockAsItem_do(par1World, par2, par3, par4, new ItemStack(var9, 1, this.damageDropped(par5)));
			}
			
			if ((par5 & 3) == 0 && par1World.rand.nextInt(200) == 0)
			{
				this.dropBlockAsItem_do(par1World, par2, par3, par4, new ItemStack(Item.appleRed, 1, 0));
			}
		}
	}
	
	@Override
	public void harvestBlock(World par1World, EntityPlayer par2EntityPlayer, int par3, int par4, int par5, int par6)
	{
		super.harvestBlock(par1World, par2EntityPlayer, par3, par4, par5, par6);
	}
	
	@Override
	protected int damageDropped(int damage)
	{
		return damage & 3;
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	@Override
	public int getBlockTextureFromSideAndMetadata(int side, int meta)
	{
		return (meta & 3) == 0 ? this.blockIndexInTexture : this.blockIndexInTexture + 1;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
	{
		for (int i = 0; i < types.length; ++i)
		{
			par3List.add(new ItemStack(par1, 1, i));
		}
	}
	
	@Override
	public void beginLeavesDecay(World world, int x, int y, int z)
	{
		world.setBlockMetadata(x, y, z, world.getBlockMetadata(x, y, z) | 8);
	}
	
	@Override
	public boolean isLeaves(World world, int x, int y, int z)
	{
		return true;
	}
	
	@Override
	public boolean isShearable(ItemStack item, World world, int x, int y, int z) 
	{
		return true;
	}
	
	@Override
	public ArrayList<ItemStack> onSheared(ItemStack item, World world, int x, int y, int z, int fortune) 
	{
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		ret.add(new ItemStack(this, 1, world.getBlockMetadata(x, y, z) & 3));
		return ret;
	}

}