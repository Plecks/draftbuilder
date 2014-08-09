package com.plecks.draftbuilder.commands;

import com.plecks.common.BasicBlock;
import com.plecks.common.PlecksCore;
import com.plecks.common.Vector;
import com.plecks.draftbuilder.DraftBuilder;
import com.plecks.draftbuilder.Clipboard;
import com.plecks.draftbuilder.EnumBlock;
import com.plecks.draftbuilder.EnumDirection;
import com.plecks.draftbuilder.Selection;

public class ClipboardCommands {
	
	private static Clipboard clipboard = DraftBuilder.instance.clipboard;

	
	
	@Command(
	        aliases = { "save" },
	        flags = "",
	        desc = "<filename>",
	        help = "Saves contents of clipboard to a .schematic file",
	        min = 1,
	        max = 1
	    )
	    public static void save(CommandContext args) {

			if(clipboard.blocks != null)
			{
				if(args.getString(0).matches("[_a-zA-Z0-9\\-\\.]+"))
				{
					String s = args.getString(0);
					
					if(!s.endsWith(".schematic"))
					{
						s = s + ".schematic";
					}
					
					boolean saved = clipboard.save(s);
					
					if(saved){
						PlecksCore.output("Clipboard: " + s + " saved.");
					}
					else{
						PlecksCore.output("Clipboard: Save failed");
					}
				}
				else
				{
					PlecksCore.output("Clipboard: Invalid filename");
				}
			}
			else
			{
				PlecksCore.output("Clipboard: Unable to save, clipboard is empty");
			}
	    }
	
	@Command(
	        aliases = { "load" },
	        flags = "",
	        desc = "<filename>",
	        help = "Loads given schematic file into the clipboard",
	        min = 1,
	        max = 1
	    )
	    public static void load(CommandContext args) {

			if(args.getString(0).matches("[_a-zA-Z0-9\\-\\.]+"))
			{
				String s = args.getString(0);
				
				if(!s.endsWith(".schematic"))
				{
					s = s + ".schematic";
				}
				
				boolean loaded = clipboard.load(s);
				
				if(loaded){
					PlecksCore.output("Clipboard: " + s + " loaded.");
				}
				else
				{
					PlecksCore.output("Clipboard: Loading of schematic failed");
				}
			}
	    }
	
	@Command(
	        aliases = { "copy" },
	        flags = "",
	        desc = "",
	        help = "Copies blocks within selection area to clipboard",
	        min = 0,
	        max = 0
	    )
	    public static void copy(CommandContext args) {

			Selection sel = DraftBuilder.instance.selection;
			
			if(sel.checkPoints())
			{
				clipboard = new Clipboard(Math.abs(sel.pos1.getBlockX() - sel.pos2.getBlockX()),
						Math.abs(sel.pos1.getBlockY() - sel.pos2.getBlockY()),
						Math.abs(sel.pos1.getBlockZ() - sel.pos2.getBlockZ()));
				clipboard.copy(sel);
				
				
				PlecksCore.output("Selection copied to clipboard");
			}
	    }
	
	@Command(
	        aliases = { "cut" },
	        flags = "",
	        desc = "",
	        help = "Copies blocks within selection area to clipboard,\n" +
	        		"and replaces them with air or optional replaceBlock",
	        min = 0,
	        max = 1
	    )
	    public static void cut(CommandContext args) {

			Selection sel = DraftBuilder.instance.selection;
			
			BasicBlock block = null;
			if(args.argsLength() > 0)
			{
				if(args.getString(0).matches("\\w+\\:\\d+")) //Check if metadata is being provided
				{
					String[] values = args.getString(0).split(":");
					block = new BasicBlock(EnumBlock.lookup(values[0]).getID(),Integer.parseInt(values[1]));
				}
				else
				{
					EnumBlock tempBlock = EnumBlock.lookup(args.getString(0));
	
					
					if(tempBlock != null){
						int meta = EnumBlock.addDirectionToMeta(tempBlock);
						block = new BasicBlock(tempBlock.getID(),meta);
					}
				}
			}
			else
			{
				block = new BasicBlock(0,0);
			}

			if(block != null && sel.checkPoints())
			{
				clipboard.cut(sel, block);
				PlecksCore.output("Cut selection to clipboard, leaving " + EnumBlock.fromIDAndMeta(block.blockID,block.blockMeta).getName().toLowerCase());
			}
			else
			{
				PlecksCore.output("Unknown block given");
			}
		
	    }
	
	@Command(
	        aliases = { "paste" },
	        flags = "",
	        desc = "",
	        help = "Copies blocks within selection area to clipboard",
	        min = 0,
	        max = 0
	    )
	    public static void paste(CommandContext args) {

			clipboard.paste();
			
			PlecksCore.output("Clipboard pasted.");
	    }
	
	@Command(
	        aliases = { "flip" },
	        flags = "",
	        desc = "",
	        help = "Flips the clipboard in a given direction\n"
	        		+ "Uses the direction player is facing if none given",
	        min = 0,
	        max = 1
	    )
	    public static void flip(CommandContext args) {

			Selection sel = DraftBuilder.instance.selection;
			Vector flipPoint = new Vector(sel.getSizeX() / 2, sel.getSizeY() / 2, sel.getSizeZ() / 2);
			EnumDirection dir;
			if(args.argsLength() > 0)
			{
				dir = EnumDirection.getDirection(args.getString(0));
			}
			else
			{
				dir = EnumDirection.getPlayerDirectionWithVertical();
			}
			
			if(dir != null)
			{
				clipboard.flip(dir, flipPoint);
			
				PlecksCore.output("Clipboard flipped " + dir.toString().toLowerCase() + "/" + dir.opposite().toString().toLowerCase());
			}
	    }
}
