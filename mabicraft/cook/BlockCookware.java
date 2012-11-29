package rgn.mods.mabicraft.cook;

import java.util.List;
import java.util.Random;

import net.minecraft.src.*;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

import rgn.mods.mabicraft.MabiCraft;
import rgn.mods.mabicraft.config.Config;
import rgn.mods.mabicraft.core.EnumGuiID;

public class BlockCookware extends Block
{	
	public BlockCookware(int blockId, int terrainId)
	{
		super(blockId, Material.fire);
		this.blockIndexInTexture = terrainId;
		this.setCreativeTab(Config.tabMabiCraft);
	}
	
	@Override
	public int getRenderType()
	{
		return Config.RENDER_TYPE_COOKWARE;
	}
	
	@Override
	public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List list)
	{
		for (int i = 0; i < 3; i++)
		{
			list.add(new ItemStack(blockID, 1, i));
		}
	}
	
	@Override
	public int damageDropped(int i)
	{
		return i & 0x03;
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving entityliving)
	{
		int playerdir = MathHelper.floor_double((double)(entityliving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		
		int type = world.getBlockMetadata(x, y, z) & 0x03;
		int dir = (playerdir << 2) + type;
		
		world.setBlockMetadataWithNotify(x, y, z, dir);
	}
	
	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z)
	{
		int blockid = world.getBlockId(x, y - 1, z);
		
		return Block.blocksList[blockid] != null && Block.blocksList[blockid].canPlaceTorchOnTop(world, x, y - 1, z);
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int par6, float par7, float par8, float par9)
	{
		if (!world.isRemote)
		{
			int type = world.getBlockMetadata(x, y, z) & 0x03;
			
			if (type == 0)
			{
				entityPlayer.openGui(MabiCraft.instance, EnumGuiID.COOKING_TABLE.ordinal(), world, x, y, z);
			}
			else if (type == 1)
			{
				entityPlayer.openGui(MabiCraft.instance, EnumGuiID.COOKING_OVEN.ordinal(), world, x, y, z);
			}
			else if (type == 2)
			{
				entityPlayer.openGui(MabiCraft.instance, EnumGuiID.COOKING_POT.ordinal(), world, x, y, z);
			}
			
			
			return true;
		}
		return true;
	}
	
}