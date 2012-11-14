package rgn.mods.mabicraft.registry;

import java.util.logging.Level;

import java.util.Iterator;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import java.lang.reflect.Field;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import net.minecraft.src.*;

import cpw.mods.fml.common.FMLLog;

import cpw.mods.fml.relauncher.ReflectionHelper;

public final class EvilScrollRegistry
{
	protected class EvilScrollRegistration
	{
		private String name;
		private int rarity;
		private int number;
		private int primaryColor;
		private int secondaryColor;
		
		public EvilScrollRegistration(String _name, int _rarity, int _number, int _primaryColor, int _secondaryColor)
		{
			this.name           = _name;
			this.rarity         = _rarity;
			this.number         = _number;
			this.primaryColor   = _primaryColor;
			this.secondaryColor = _secondaryColor;
		}
		
		public String getName()
		{
			return this.name;
		}
		
		public int getRarity()
		{
			return this.rarity;
		}
		
		public int getNumber()
		{
			return this.number;
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
	
	private Map<Class, EvilScrollRegistration> classToRegistrationMapping = Maps.newHashMap();
	private BiMap<Integer, Class> metadataToClassBiMapping                = HashBiMap.create();

	private List<Class> entityClassList = Lists.newArrayList();
	private List<Integer> metadataList = Lists.newArrayList();
	
	private static EvilScrollRegistry INSTANCE = new EvilScrollRegistry();
	
	public static EvilScrollRegistry instance()
	{
		return INSTANCE;
	}
	
	protected static void registerEvilScroll(Class clazz, int rarity, int number)
	{	
		instance().doRegisterEvilScroll(clazz, rarity, number);
	}
	
	private void doRegisterEvilScroll(Class clazz, int rarity, int number)
	{
		try
		{
			Map classToIDMapping = ReflectionHelper.getPrivateValue(EntityList.class, null, "classToIDMapping");
			
			int entityTypeID      = ((Integer)classToIDMapping.get(clazz)).intValue();
			String name           = (String)EntityList.classToStringMapping.get(clazz);
			EntityEggInfo eggInfo = (EntityEggInfo)EntityList.entityEggs.get(entityTypeID);
			int primaryColor      = ((Integer)eggInfo.primaryColor).intValue();
			int secondaryColor    = ((Integer)eggInfo.secondaryColor).intValue();
			
			this.classToRegistrationMapping.put(clazz, new EvilScrollRegistration(name, rarity, number, primaryColor, secondaryColor));
			this.metadataToClassBiMapping.put(Integer.valueOf(entityTypeID), clazz);
			this.entityClassList.add(clazz);
			this.metadataList.add(Integer.valueOf(entityTypeID));
		}
		catch (Exception e)
		{
			FMLLog.log(Level.SEVERE, e, "Exception Occured in MabiCraft.EvilScrollMap");
		}
	}
	
	public static void registerEvilScroll(Class clazz, String name, int metadata, int rarity, int number, int primaryColor, int secondaryColor)
	{
		instance().doRegisterEvilScroll(clazz, name, metadata, rarity, number, primaryColor, secondaryColor);
	}
	
	private void doRegisterEvilScroll(Class clazz, String name, int metadata, int rarity, int number, int primaryColor, int secondaryColor)
	{
		this.classToRegistrationMapping.put(clazz, new EvilScrollRegistration(name, rarity, number, primaryColor, secondaryColor));
		this.metadataToClassBiMapping.put(Integer.valueOf(metadata), clazz);
		this.entityClassList.add(clazz);
		this.metadataList.add(metadata);
	}
	
	// usually interface
	public int getPrimaryColorFromMetadata(int metadata)
	{
		return classToRegistrationMapping.get(metadataToClassBiMapping.get(Integer.valueOf(metadata))).getPrimaryColor();
	}
	
	public int getSecondaryColorFromMetadata(int metadata)
	{
		return classToRegistrationMapping.get(metadataToClassBiMapping.get(Integer.valueOf(metadata))).getSecondaryColor();
	}
	
	public int getMetadataFromClass(Class clazz)
	{
		return metadataToClassBiMapping.inverse().get(clazz);
	}
	
	public String getEntityNameFromMetadata(int metadata)
	{
		return classToRegistrationMapping.get(metadataToClassBiMapping.get(Integer.valueOf(metadata))).getName();
	}
	
	public int getNumberFromClass(Class clazz)
	{
		return classToRegistrationMapping.get(clazz).getNumber();
	}
	
	public int getRarityFromClass(Class clazz)
	{
		return classToRegistrationMapping.get(clazz).getRarity();
	}
	
	public int getClassListSize()
	{
		return entityClassList.size();
	}
	
	public Class getEntityClass(int index)
	{
		return entityClassList.get(index);
	}
	
	public int getMetadataListSize()
	{
		return this.metadataList.size();
	}
	
	public int getMetadata(int index)
	{
		return this.metadataList.get(index).intValue();
	}
	
	static
	{
		registerEvilScroll(EntityZombie.class,   10, 5);
		registerEvilScroll(EntitySkeleton.class, 10, 5);
		registerEvilScroll(EntityCreeper.class,  10, 5);
		registerEvilScroll(EntitySpider.class,   10, 5);
		
		registerEvilScroll(EntitySlime.class,      10, 5);
		registerEvilScroll(EntityEnderman.class,   10, 5);
		registerEvilScroll(EntitySilverfish.class, 10, 5);
		registerEvilScroll(EntityCaveSpider.class, 10, 5);
		
		registerEvilScroll(EntityPigZombie.class, 10, 5);
		registerEvilScroll(EntityGhast.class,     10, 5);
		registerEvilScroll(EntityMagmaCube.class, 10, 5);
		registerEvilScroll(EntityBlaze.class,     10, 5);
	}
}