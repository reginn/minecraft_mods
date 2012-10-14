package rgn.mods.woodbench;

import net.minecraft.src.*;

// FMLのログで使用
import java.util.logging.Level;

// FMLのログに出力するAPI
import cpw.mods.fml.common.FMLLog;

// Forge式コンフィグファイルを使うためのAPI
import net.minecraftforge.common.Configuration;

// FMLのイベントクラス
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

// FMLのアノテーションクラス
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.network.NetworkMod;

// 各レジストリクラス
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "WoodBench", name = "Wood Bench", version = "1.0.0")
@NetworkMod(
	clientSideRequired = true, serverSideRequired = false,
	channels = { "bench", "bench2", "entity" },
	packetHandler = PacketHandler.class
	)
public class WoodBench
{
	@SidedProxy(clientSide = "rgn.mods.woodbench.client.ClientProxy", serverSide = "rgn.mods.woodbench.CommonProxy")
	public static CommonProxy proxy;
	
	public static Block blockWoodBench;
	private int blockIdWoodBench;
	private int entityIdDummy;
	
	public static int woodBenchRenderType;
	
	@Mod.PreInit
	public void preInit(FMLPreInitializationEvent event)
	{
		Configuration cfg = new Configuration(event.getSuggestedConfigurationFile());
		try
		{
			cfg.load();
			blockIdWoodBench = cfg.getOrCreateIntProperty("WoodBench", Configuration.CATEGORY_BLOCK, 3000).getInt();
			entityIdDummy    = cfg.getOrCreateIntProperty("entity.id.dummy", Configuration.CATEGORY_GENERAL, 230).getInt();
		}
		catch (Exception e)
		{
			FMLLog.log(Level.SEVERE, e, "Error Massage");
		}
		finally
		{
			cfg.save();
		}
	}
	
	@Mod.Init
	public void init(FMLInitializationEvent event)
	{
		woodBenchRenderType = proxy.getBlockNewRenderType();
		
		blockWoodBench = new BlockWoodBench(blockIdWoodBench);
		GameRegistry.registerBlock(blockWoodBench);
		GameRegistry.registerTileEntity(TileEntityWoodBench.class, "woodbench");
		
		LanguageRegistry.addName(blockWoodBench, "WoodBench");
		
		EntityRegistry.registerGlobalEntityID(EntityDummy.class, "dummy", entityIdDummy);
		EntityRegistry.registerModEntity(EntityDummy.class, "dummny", 0, this, 250, 1, false);
		
		proxy.registerRenderers();
		
		GameRegistry.addRecipe(
			new ItemStack(blockWoodBench, 1),
				new Object[]
				{
					" X ", "X X", 
					Character.valueOf('X'), new ItemStack(Block.woodSingleSlab, 1)
				});
	}

}
