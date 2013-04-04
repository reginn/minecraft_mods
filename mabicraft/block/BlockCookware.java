package rgn.mods.mabicraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import rgn.mods.mabicraft.MabiCraft;
import rgn.mods.mabicraft.config.Config;
import rgn.mods.mabicraft.inventory.EnumGuiID;

public class BlockCookware extends Block
{
	public BlockCookware(int blockId)
	{
		super(blockId, Material.fire);
	}

	@Override
	@SideOnly(Side.CLIENT)

	/**
	 * When this method is called, your block should register all the icons it needs with the given IconRegister. This is
	 * the only chance you get to register icons.
	 */
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.blockIcon = Block.planks.getBlockTextureFromSideAndMetadata(0, 0);
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
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving entityliving, ItemStack itemstack)
	{
		int playerdir = MathHelper.floor_double((double)(entityliving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

		int type = world.getBlockMetadata(x, y, z) & 0x03;
		int dir = (playerdir << 2) + type;

		world.setBlockMetadataWithNotify(x, y, z, dir, 2);
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