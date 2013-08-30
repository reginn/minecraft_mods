package rgn.mods.dwarventools.client;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import rgn.mods.dwarventools.config.Config;
import rgn.mods.dwarventools.core.CommonProxy;
import rgn.mods.dwarventools.tileentity.TileEntityInfernalFurnace;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy
{
	private DwarvenEffectRenderer effectRenderer;

	public ClientProxy()
	{
		this.effectRenderer = new DwarvenEffectRenderer();
	}

	@Override
	public int addArmor(String armor)
	{
		return RenderingRegistry.addNewArmourRendererPrefix(armor);
	}

	@Override
	public World getClientWorld()
	{
		return FMLClientHandler.instance().getClient().theWorld;
	}

	@Override
	public void spawnCustomParticle(World world, Entity entity, int particleId)
	{
		this.effectRenderer.spawnCustomParticle(world, entity, particleId);
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntityInfernalFurnace tileentity = (TileEntityInfernalFurnace)world.getBlockTileEntity(x, y, z);
		if (tileentity != null && ID == Config.guiIdInfernalFurnace)
		{
			return (new GuiInfernalFurnace(player.inventory, tileentity));
		}
		return null;
	}

}