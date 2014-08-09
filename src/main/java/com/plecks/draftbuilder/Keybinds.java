package com.plecks.draftbuilder;

import net.minecraft.client.settings.KeyBinding;

import org.lwjgl.input.Keyboard;

import com.plecks.common.PlecksCore;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;

public class Keybinds{

	static KeyBinding toggleConsole = new KeyBinding("DraftBuilder Console", Keyboard.KEY_GRAVE, "DraftBuilder");
	
	public Keybinds()
	{
		ClientRegistry.registerKeyBinding(toggleConsole);
	}

	@SubscribeEvent
	public void keyDown(KeyInputEvent event) {
		if(toggleConsole.isPressed())
		{
			if(PlecksCore.mc.currentScreen == null){
				PlecksCore.mc.displayGuiScreen(new GuiConsole());
			}
		}
	}

}
