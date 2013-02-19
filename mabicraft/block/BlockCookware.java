package rgn.mods.mabicraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import rgn.mods.mabicraft.MabiCraft;
import rgn.mods.mabicraft.config.Config;
import rgn.mods.mabicraft.inventory.EnumGuiID;

public class BlockCookware extends Block
{
	public BlockCookware(int blockId, int terrainId)
	{
		super(blockId, Material.fire);
		this.blockIndexInTexture = terrainId;
	}

	@Override
	public int getRenderType()
	{
		return Config.RENDER_TYPE_COOKWARE;
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
			entityPlayer.openGui(MabiCraft.instance, EnumGuiID.COOKING_TABLE.ordinal(), world, x, y, z);
			return true;
		}
		return true;
	}

}