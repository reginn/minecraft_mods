package rgn.mods.dwarventools.config;

import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraftforge.common.EnumHelper;

public class ConfigureEnum
{
	public static EnumToolMaterial enumToolMaterialObsidian    = EnumHelper.addToolMaterial("OBSIDIAN",    1, 4096,  4.0F, 1, 0);
	public static EnumToolMaterial enumToolMaterialLapislazuli = EnumHelper.addToolMaterial("LAPISLAZULI", 2,  512,  6.0F, 2, 0);
	public static EnumToolMaterial enumToolMaterialRedstone    = EnumHelper.addToolMaterial("REDSTONE",    3, 1024,  8.0F, 3, 0);
	public static EnumToolMaterial enumToolMaterialMithril     = EnumHelper.addToolMaterial("MITHRIL",     2, 1024,  6.0F, 3, 0);
	public static EnumToolMaterial enumToolMaterialEbony       = EnumHelper.addToolMaterial("EBONY",       3, 2048,  8.0F, 3, 0);

	public static EnumArmorMaterial enumArmorMaterialMithril = EnumHelper.addArmorMaterial("MITHRIL", 15, new int[] {2, 6, 5, 2}, 0);
	public static EnumArmorMaterial enumArmorMaterialEbony   = EnumHelper.addArmorMaterial("EBONY",   33, new int[] {3, 8, 6, 3}, 0);

}