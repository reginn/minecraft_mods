package rgn.mods.mabicraft.registry;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import net.minecraft.entity.EntityEggInfo;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.ObfuscationReflectionHelper;

public final class EvilScrollRegistry
{
	protected class EvilScrollRegistration
	{
		private String entityName;
		private int dropRate;
		private int numberOfEmerald;
		private int primaryColor;
		private int secondaryColor;

		public EvilScrollRegistration(String _entityName, int _dropRate, int _numberOfEmerald, int _primaryColor, int _secondaryColor)
		{
			this.entityName      = _entityName;
			this.dropRate        = _dropRate;
			this.numberOfEmerald = _numberOfEmerald;
			this.primaryColor    = _primaryColor;
			this.secondaryColor  = _secondaryColor;
		}

		public String getEntityName()
		{
			return this.entityName;
		}

		public int getDropRate()
		{
			return this.dropRate;
		}

		public int getNumberOfEmerald()
		{
			return this.numberOfEmerald;
		}

		public int getPrimaryColor()
		{
			return this.primaryColor;
		}

		public int getSecondaryColor()
		{
			return this.secondaryColor;
		}
	}

	private Map<Integer, EvilScrollRegistration> evilScrolls = Maps.newHashMap();

	private Map<Class, EvilScrollRegistration> classToRegistrationMapping = Maps.newHashMap();
	private BiMap<Integer, Class> metadataToClassBiMapping                = HashBiMap.create();

	private List<Class> entityClassList = Lists.newArrayList();
	private List<Integer> metadataList = Lists.newArrayList();

	private int nextMetadata = 0;

	private static EvilScrollRegistry INSTANCE = new EvilScrollRegistry();

	public static EvilScrollRegistry instance()
	{
		return INSTANCE;
	}

	protected static void registerEvilScroll(Class clazz, int rarity, int number)
	{
		instance().doRegisterEvilScroll(clazz, rarity, number);
	}

	private void doRegisterEvilScroll(Class clazz, int dropRate, int numberOfEmerald)
	{
		try
		{
			String fieldName = ObfuscationReflectionHelper.remapFieldNames("EntityList.class", "classToIDMapping")[0];
			Map classToIDMapping = ObfuscationReflectionHelper.getPrivateValue(net.minecraft.entity.EntityList.class, null, fieldName);

			int entityTypeID      = ((Integer)classToIDMapping.get(clazz)).intValue();
			String entityName     = (String)EntityList.classToStringMapping.get(clazz);
			EntityEggInfo eggInfo = (EntityEggInfo)EntityList.entityEggs.get(entityTypeID);
			int primaryColor      = ((Integer)eggInfo.primaryColor).intValue();
			int secondaryColor    = ((Integer)eggInfo.secondaryColor).intValue();

			this.evilScrolls.put(entityTypeID, new EvilScrollRegistration(entityName, dropRate, numberOfEmerald, primaryColor, secondaryColor));
		}
		catch (Exception e)
		{
			FMLLog.log(Level.SEVERE, e, "Exception Occured in MabiCraft.EvilScrollMap");
		}
	}

	public static void registerEvilScroll(String entityName, int dropRete, int numberOfEmerald, int primaryColor, int secondaryColor)
	{
		instance().doRegisterEvilScroll(entityName, dropRete, numberOfEmerald, primaryColor, secondaryColor);
	}

	private void doRegisterEvilScroll(String entityName, int dropRate, int numberOfEmerald, int primaryColor, int secondaryColor)
	{
		while(this.evilScrolls.containsKey(this.nextMetadata))
		{
			++this.nextMetadata;
		}
		this.evilScrolls.put(this.nextMetadata, new EvilScrollRegistration(entityName, dropRate, numberOfEmerald, primaryColor, secondaryColor));
	}

	// usually interface
	public int getPrimaryColorFromMetadata(int metadata)
	{
		return this.evilScrolls.get(metadata).getPrimaryColor();
	}

	public int getSecondaryColorFromMetadata(int metadata)
	{
		return this.evilScrolls.get(metadata).getSecondaryColor();
	}

	public String getEntityNameFromMetadata(int metadata)
	{
		return this.evilScrolls.get(metadata).getEntityName();
	}

	public int getNumberOfEmeraldFromMetadata(int metadata)
	{
		return this.evilScrolls.get(metadata).getNumberOfEmerald();
	}

	public int getDropRateFromMetadata(int metadata)
	{
		return this.evilScrolls.get(metadata).getDropRate();
	}

	public Set<Integer> getAllMetadata()
	{
		return this.evilScrolls.keySet();
	}

	static
	{
		registerEvilScroll(EntityZombie.class,   20, 4);
		registerEvilScroll(EntitySkeleton.class, 20, 4);
		registerEvilScroll(EntityCreeper.class,  20, 4);
		registerEvilScroll(EntitySpider.class,   20, 4);

		registerEvilScroll(EntitySlime.class,      20, 4);
		registerEvilScroll(EntityEnderman.class,   20, 4);
		registerEvilScroll(EntitySilverfish.class, 20, 4);
		registerEvilScroll(EntityCaveSpider.class, 20, 4);

		registerEvilScroll(EntityPigZombie.class, 20, 4);
		registerEvilScroll(EntityGhast.class,     20, 4);
		registerEvilScroll(EntityMagmaCube.class, 20, 4);
		registerEvilScroll(EntityBlaze.class,     20, 4);
	}
}