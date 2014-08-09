package com.plecks.draftbuilder;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ConfigHandler {
	
	public static Configuration config;
	
	public static int toolID;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		
		toolID = config.get(Configuration.CATEGORY_GENERAL, "toolID", 280).getInt();
		
		config.save();
	}

}
