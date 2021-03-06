package rgn.mods.mabicraft.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import rgn.mods.mabicraft.MabiCraft;
import rgn.mods.mabicraft.config.Config;
import rgn.mods.mabicraft.inventory.EnumGuiID;

public class BlockBonfire extends Block
{
	public BlockBonfire(int blockId)
	{
		super(blockId, Material.fire);
		this.setLightValue(1.0F);
	}


	@Override
	@SideOnly(Side.CLIENT)

	/**
	 * When this method is called, your block should register all the icons it needs with the given IconRegister. This is
	 * the only chance you get to register icons.
	 */
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.blockIcon = Block.fire.getBlockTextureFromSide(0);
	}

	@Override
	public int getRenderType()
	{
		return Config.RENDER_TYPE_BONFIRE;
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
	public void onBlockAdded(World world, int x, int y, int z)
	{
		super.onBlockAdded(world, x, y, z);
		world.markBlockForUpdate(x, y, z);
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
			entityPlayer.openGui(MabiCraft.instance, EnumGuiID.BONFIRE.ordinal(), world, x, y, z);
		}
		return true;
	}

	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random random)
	{
		if (random.nextInt(24) == 0)
		{
			world.playSound((double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F),
							"fire.fire", 1.0F + random.nextFloat(), random.nextFloat() * 0.7F + 0.3F, false);
		}
		double X = (double)((float)x + world.rand.nextFloat());
		double Y = (double)((float)y + world.rand.nextFloat() * 0.5F + 0.5F);
		double Z = (double)((float)z + world.rand.nextFloat());

		world.spawnParticle("largesmoke", X, Y, Z, 0.0D, 0.0D, 0.0D);
	}
}