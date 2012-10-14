package rgn.mods.woodbench;

import java.util.*;
import net.minecraft.src.*;

public class BlockWoodBench extends BlockContainer
{
	private final AxisAlignedBB boundingBox = AxisAlignedBB.getBoundingBox(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
	
	public BlockWoodBench(int blockId)
	{
		super(blockId, Block.planks.blockIndexInTexture , Block.planks.blockMaterial);
		this.setHardness(2.0F);
		this.setBlockName("WoodBench");
		this.setRequiresSelfNotify();
		this.setCreativeTab(CreativeTabs.tabDeco);
	}
	
	@Override
	public void onBlockEventReceived(World world, int x, int y, int z, int eventId, int eventParam)
	{
		super.onBlockEventReceived(world, x, y, z, eventId, eventParam);
		world.markBlockNeedsUpdate(x, y, z);
	}
	
	@Override
	public int getRenderBlockPass()
	{
		return 1;
	}

	@Override
	public int getRenderType()
	{
		return WoodBench.woodBenchRenderType;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity)
	{
		if(entity instanceof EntityPlayer) return;
		if(entity instanceof EntityDummy) return;
		if(!(entity instanceof EntityLiving)) return;
		
		EntityLiving entityLiving = (EntityLiving)entity;
		
		TileEntityWoodBench tileentity = (TileEntityWoodBench)world.getBlockTileEntity(i, j, k);
		tileentity.mountEntity(entityLiving);
		
		/*
		if(tileentity.getEntityId() == 0){
			EntityDummy entityDummy = new EntityDummy(world, i+0.5D, j, k+0.5D);
			world.spawnEntityInWorld(entityDummy);
			entity.mountEntity(entityDummy);
			
			tileentity.setEntityId(entityDummy.entityId);
		}
		*/
	}
	
	@Override
	public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, int par6, float par7, float par8, float par9)
	{
		TileEntityWoodBench tileentity = (TileEntityWoodBench)world.getBlockTileEntity(i, j, k);
		tileentity.mountEntityIfNull(entityplayer);
		/*
		if(tileentity.getEntityId() == 0){
			if(entityplayer.ridingEntity == null){
				EntityDummy entity = new EntityDummy(world, i+0.5D, j, k+0.5D);
				world.spawnEntityInWorld(entity);
				entityplayer.mountEntity(entity);

				tileentity.setEntityId(entity.entityId);
			}
		}else{
			Entity entity = getEntityDummy(world, i, j, k, tileentity.getEntityId());
			if(entity != null){
				world.setEntityDead(entity);
			}
			tileentity.setEntityId(0);
		}
		*/
		return true;
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, int par5, int par6)
	{
		TileEntityWoodBench tileentity = (TileEntityWoodBench)world.getBlockTileEntity(x, y, z);
		tileentity.unmountEntity();
		/*
		if(tileentity.getEntityId() != 0){
			Entity entity = getEntityDummy(world, i, j, k, tileentity.getEntityId());
			if(entity != null){
				world.setEntityDead(entity);
			}
		}
		*/
		super.breakBlock(world, x, y, z, par5, par6);
	}


	private Entity getEntityDummy(World world, int i, int j, int k, int id)
	{
		AxisAlignedBB axisalignedbb = boundingBox.addCoord(i, j, k);
		List list = world.getEntitiesWithinAABB(EntityDummy.class, axisalignedbb);
		Iterator iterator = list.iterator();
		while(iterator.hasNext())
		{
			Entity entity = (Entity)iterator.next();
			if(entity instanceof EntityDummy)
			{
				if(entity.entityId == id)
				{
					return entity;
				}
			}
		}
		return null;
	}

	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLiving par5EntityLiving)
	{
		super.onBlockPlacedBy(par1World, par2, par3, par4, par5EntityLiving);
		int i = MathHelper.floor_double((double)((par5EntityLiving.rotationYaw * 4F) / 360F) + 0.5D) & 3;
		int k = 0;

		if (i == 0 || i == 2)
		{
			k = 0;
		}
		else if (i == 1 || i == 3)
		{
			k = 1;
		}

		par1World.setBlockMetadataWithNotify(par2, par3, par4, k);
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess iblockaccess, int i, int j, int k)
	{
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
	}

	@Override
	public void setBlockBoundsForItemRender()
	{
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
	}

	@Override
	public TileEntity createNewTileEntity(World world)
	{
		return new TileEntityWoodBench();
	}
}