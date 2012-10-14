package rgn.mods.lamp;

import java.util.List;

import net.minecraft.src.*;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

public class BlockGlowFlower extends BlockFlower
{
	private byte[] textureIndex =
		{
			12, 13, 28, 29, 39, 56, 55
		};
		
	public BlockGlowFlower(int blockId, int terrainId)
	{
		super(blockId, terrainId);
		this.setLightValue(12.0F/16.0F);
		this.setHardness(0.0F);
		this.setStepSound(soundGrassFootstep);
		this.setCreativeTab(CreativeTabs.tabDeco);
	}

	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
	{
		for (int var4 = 0; var4 < this.textureIndex.length; ++var4)
		{
			par3List.add(new ItemStack(par1, 1, var4));
		}
	}
	
	@Override
	public int getBlockTextureFromSideAndMetadata(int side, int meta)
	{
		if (meta >= 0 && meta < this.textureIndex.length)
		{
			return this.textureIndex[meta];
		}
		
		return this.textureIndex[0];
	}
	
	@Override
	protected int damageDropped(int i)
	{
		return i;
	}
	
	@SideOnly(Side.CLIENT)
	public int getBlockColor()
	{
		double var1 = 0.5D;
		double var3 = 1.0D;
		return ColorizerGrass.getGrassColor(var1, var3);
	}
	
	@SideOnly(Side.CLIENT)
	public int getRenderColor(int par1)
	{
		return par1 == 4 || par1 == 5 ? ColorizerFoliage.getFoliageColorBasic() : 0xFFFFFF;
	}
	
	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);
		
		return meta == 4 || meta == 5 ? world.getBiomeGenForCoords(x, z).getBiomeGrassColor() : 0xFFFFFF;
	}
	
	@SideOnly(Side.CLIENT)
	public int getDamageValue(World world, int x, int y, int z)
	{
		return world.getBlockMetadata(x, y, z);
	}

}