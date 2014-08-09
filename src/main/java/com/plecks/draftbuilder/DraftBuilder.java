package com.plecks.draftbuilder;

import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;

import com.plecks.common.Equipper;
import com.plecks.common.PlecksCore;
import com.plecks.draftbuilder.commands.ClipboardCommands;
import com.plecks.draftbuilder.commands.CommandsManager;
import com.plecks.draftbuilder.commands.SelectionCommands;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;

@Mod(modid="DraftBuilder", name="DraftBuilder", version="1.0.0")
public class DraftBuilder {

	public CommandsManager commandHandler;
	public GUI gui;
	public Selection selection;
	public Manipulator manipulator;
	public Equipper equipper;
	public GuiConsole console;
	private Item selector;
	public Clipboard clipboard;
		
	@Instance("DraftBuilder")
	public static DraftBuilder instance;
	
	
	@EventHandler
	public void load(FMLInitializationEvent event)
	{
		PlecksCore.update();
		
		selection = new Selection();
		commandHandler = new CommandsManager();
		gui = new GUI();
		manipulator = new Manipulator();
		equipper = new Equipper();
		console = new GuiConsole();
		clipboard = new Clipboard(0,0,0);
		
		FMLCommonHandler.instance().bus().register(this);
		FMLCommonHandler.instance().bus().register(new Keybinds());
		MinecraftForge.EVENT_BUS.register(new HandTool());
		MinecraftForge.EVENT_BUS.register(gui);
		
		commandHandler.register(SelectionCommands.class);
		commandHandler.register(ClipboardCommands.class);
	}
	
	public String getLabel()
	{
		return "DraftBuilder";
	}
	
	@SubscribeEvent
	public void onTick(ClientTickEvent event)
	{
		
		if(event.phase == Phase.END && PlecksCore.mc.thePlayer != null)
		{
			if(manipulator.toggle)
			{
				manipulator.onTick();
			}
		}	
	}
	
	public int nextTickSpacing()
	{
		return 1;
	}
}
