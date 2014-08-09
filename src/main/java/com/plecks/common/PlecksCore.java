package com.plecks.common;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class PlecksCore {
	
	public static Minecraft mc;
	
	public PlecksCore()
	{
		update();
	}
	
	public static void update()
	{
		mc = Minecraft.getMinecraft();
	}
	
	public static EntityClientPlayerMP getPlayer()
	{
		return mc.thePlayer;
	}
	
	public static World getWorld()
	{
		return mc.theWorld;
	}

	/** Outputs string to client chat */
	public static void output(String s)
	{
		if (mc.ingameGUI.getChatGUI().getSentMessages().size() == 0 || !((String)mc.ingameGUI.getChatGUI().getSentMessages().get(mc.ingameGUI.getChatGUI().getSentMessages().size() - 1)).equals(s))
        {
            mc.ingameGUI.getChatGUI().printChatMessage(new ChatComponentText(s));
        }
	}
	
	public static void debug(String s)
	{
		System.out.println("DraftBuilder debug: " + s);
		
	}
	
}
