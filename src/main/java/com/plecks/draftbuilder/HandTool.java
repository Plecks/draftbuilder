package com.plecks.draftbuilder;

import net.minecraft.item.Item;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;

import com.plecks.common.PlecksCore;


public class HandTool extends Item {

	private int point = 1;

	public void onClick(PlayerInteractEvent event)
	{
		if (event.action == Action.RIGHT_CLICK_AIR || event.action == Action.RIGHT_CLICK_BLOCK)
		{
			if (Item.getIdFromItem(PlecksCore.mc.thePlayer.getCurrentEquippedItem().getItem()) == ConfigHandler.toolID)
			{
				if(PlecksCore.mc.theWorld.isRemote)
				{
					if(point == 1){
						DraftBuilder.instance.selection.setPos1(event.x, event.y, event.z);
						PlecksCore.output("Position 1 set: " + DraftBuilder.instance.selection.pos1.getX() + "," + DraftBuilder.instance.selection.pos1.getY() + "," + DraftBuilder.instance.selection.pos1.getZ());
						point = 2;
					}
					else{
						DraftBuilder.instance.selection.setPos2(event.x, event.y, event.z);
						PlecksCore.output("Position 2 set: " + DraftBuilder.instance.selection.pos2.getX() + "," + DraftBuilder.instance.selection.pos2.getY() + "," + DraftBuilder.instance.selection.pos2.getZ());
						point = 1;
					}
				}
			}
		}
	}
	
}
