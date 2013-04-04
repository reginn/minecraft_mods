package rgn.mods.rum.block;

import net.minecraft.block.Block;

import cpw.mods.fml.common.registry.GameRegistry;

import rgn.mods.rum.RumCore;
import rgn.mods.rum.config.Config;
import rgn.mods.rum.tileentity.TileEntityLockedChest;

public class RumBlock
{
	public static Block blockLockedChest;

	public static int LOCKED_CHEST_RENDER_TYPE;

	public static void configure(Config config)
	{
		LOCKED_CHEST_RENDER_TYPE = RumCore.proxy.getUniqueRenderType();

		blockLockedChest = (new BlockLockedChest(config.blockIdLockedChest)).setUnlocalizedName("LockedChest").setCreativeTab(Config.tabRum);

		GameRegistry.registerBlock(blockLockedChest, "LockedChest");
		GameRegistry.registerTileEntity(TileEntityLockedChest.class, "LockedChest");

	}
}
