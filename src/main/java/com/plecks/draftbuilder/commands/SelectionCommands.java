package com.plecks.draftbuilder.commands;

import com.plecks.common.BasicBlock;
import com.plecks.common.PlecksCore;
import com.plecks.common.Vector;
import com.plecks.draftbuilder.DraftBuilder;
import com.plecks.draftbuilder.EnumBlock;
import com.plecks.draftbuilder.EnumDirection;
import com.plecks.draftbuilder.Selection;

public class SelectionCommands {
	
	private static Selection selection = DraftBuilder.instance.selection;
	
	@Command(
	        aliases = { "test" },
	        flags = "",
	        desc = "",
	        help = "Test command, for testing snippets of code",
	        min = 0,
	        max = 1
	    )
	    public static void test(CommandContext args) {

			
	    }
	
	@Command(
	        aliases = { "pos1" },
	        flags = "",
	        desc = "[position]",
	        help = "Sets the first selection point\n" +
	                "Uses either player's position or given x,y,z",
	        min = 0,
	        max = 1
	    )
	    public static void pos1(CommandContext args) {

			selection.setPos1();
			
			PlecksCore.output("Position 1 set: " + selection.pos1.getX() + "," + selection.pos1.getY() + "," + selection.pos1.getZ());
	    }
	
	@Command(
	        aliases = { "pos2" },
	        flags = "",
	        desc = "[position]",
	        help = "Sets the second selection point\n" +
	                "Uses either player's position or given x,y,z",
	        min = 0,
	        max = 1
	    )
	    public static void pos2(CommandContext args) {

			selection.setPos2();
			
			PlecksCore.output("Position 2 set: " + selection.pos2.getX() + "," + selection.pos2.getY() + "," + selection.pos2.getZ());
	    }
	
	@Command(
	        aliases = { "hpos1" },
	        flags = "",
	        desc = "",
	        help = "Sets the first selection point to block player is aiming at\n",
	        min = 0,
	        max = 0
	    )
	    public static void hpos1(CommandContext args) {

			selection.setMouseover1();
			
			PlecksCore.output("Position 1 set: " + selection.pos1.getX() + "," + selection.pos1.getY() + "," + selection.pos1.getZ());
	    }
	
	@Command(
	        aliases = { "hpos2" },
	        flags = "",
	        desc = "",
	        help = "Sets the second selection point to block player is aiming at\n",
	        min = 0,
	        max = 0
	    )
	    public static void hpos2(CommandContext args) {

			selection.setMouseover2();
			
			PlecksCore.output("Position 2 set: " + selection.pos2.getX() + "," + selection.pos2.getY() + "," + selection.pos2.getZ());
	    }
	
	@Command(
	        aliases = { "chunk" },
	        flags = "",
	        desc = "",
	        help = "Sets the selection area to the current chunk\n",
	        min = 0,
	        max = 0
	    )
	    public static void chunk(CommandContext args) {

			selection.setChunk();
			
			PlecksCore.output("Selection set to current chunk");
	        PlecksCore.output("Position 1 set: " + selection.pos1.getX() + "," + selection.pos1.getY() + "," + selection.pos1.getZ());
	        PlecksCore.output("Position 2 set: " + selection.pos2.getX() + "," + selection.pos2.getY() + "," + selection.pos2.getZ());
	    }
	
	@Command(
	        aliases = { "expand" },
	        flags = "",
	        desc = "<amount> [reverse] [direction]",
	        help = "Expands the selection the given amount" +
	        		"Uses player direction if none specified",
	        min = 1,
	        max = 3
	    )
	    public static void expand(CommandContext args) {

			switch(args.argsLength())
			{
				case 1:
					if(args.getString(0).matches("\\d+"))
					{
						selection.expand(args.getInteger(0));
						PlecksCore.output("Expanded selection " + args.getInteger(0) + " blocks " + EnumDirection.getPlayerDirectionWithVertical().toString() +".");
					}
					break;
				case 2:
					if(args.getString(0).matches("\\d+"))
					{
						int amount = args.getInteger(0);
						if(args.getString(1).matches("\\d+"))
						{
							selection.expand(amount, args.getInteger(1));
							PlecksCore.output("Expanded selection " + args.getInteger(0) + " blocks " + EnumDirection.getPlayerDirectionWithVertical().toString() +" and reverse " + args.getInteger(1) + " blocks.");
						}
						else if(EnumDirection.getDirection(args.getString(1)) != null)
						{
							selection.expand(amount, EnumDirection.getDirection(args.getString(1)));
							PlecksCore.output("Expanded selection " + args.getInteger(0) + " blocks " + EnumDirection.getDirection(args.getString(1)).toString() +".");
						}
						else
						{
							PlecksCore.output("Expand: Incorrect arguments.\n Syntax: expand [amount] <reverse> <direction>");
						}
					}
					break;
				case 3:
					if(args.getString(0).matches("\\d+") && args.getString(1).matches("\\d+") && EnumDirection.getDirection(args.getString(2)) != null)
					{
						selection.expand(args.getInteger(0), args.getInteger(1), EnumDirection.getDirection(args.getString(2)));
						PlecksCore.output("Expanded selection " + args.getInteger(0) + " blocks " + EnumDirection.getDirection(args.getString(2)) +" and reverse " + args.getInteger(1) + " blocks.");
					}
					else
					{
						PlecksCore.output("Expand: Incorrect arguments.\n Syntax: expand [amount] <reverse> <direction>");
					}
					break;
					
			}
		}
	
	@Command(
	        aliases = { "contract" },
	        flags = "",
	        desc = "<amount> [reverse] [direction]",
	        help = "Contracts the selection the given amount\n" +
	        		"Uses player direction if none specified",
	        min = 1,
	        max = 3
	    )
	    public static void contract(CommandContext args) {

			switch(args.argsLength())
			{
				case 1:
					if(args.getString(0).matches("\\d+"))
					{
						selection.contract(args.getInteger(0));
						PlecksCore.output("Contracted selection " + args.getInteger(0) + " blocks " + EnumDirection.getPlayerDirectionWithVertical().toString() +".");
					}
					break;
				case 2:
					if(args.getString(0).matches("\\d+"))
					{
						int amount = args.getInteger(0);
						if(args.getString(1).matches("\\d+"))
						{
							selection.contract(amount, args.getInteger(1));
							PlecksCore.output("Contracted selection " + args.getInteger(0) + " blocks " + EnumDirection.getPlayerDirectionWithVertical().toString() +" and reverse " + args.getInteger(1) + " blocks.");
						}
						else if(EnumDirection.getDirection(args.getString(1)) != null)
						{
							selection.contract(amount, EnumDirection.getDirection(args.getString(1)));
							PlecksCore.output("Contracted selection " + args.getInteger(0) + " blocks " + EnumDirection.getDirection(args.getString(1)).toString() +".");
						}
						else
						{
							PlecksCore.output("Contract: Incorrect arguments.\n Syntax: contract [amount] <reverse> <direction>");
						}
					}
					break;
				case 3:
					if(args.getString(0).matches("\\d+") && args.getString(1).matches("\\d+") && EnumDirection.getDirection(args.getString(2)) != null)
					{
						selection.contract(args.getInteger(0), args.getInteger(1), EnumDirection.getDirection(args.getString(2)));
						PlecksCore.output("Contracted selection " + args.getInteger(0) + " blocks " + EnumDirection.getDirection(args.getString(2)) +" and reverse " + args.getInteger(1) + " blocks.");
					}
					else
					{
						PlecksCore.output("Contract: Incorrect arguments.\n Syntax: contract [amount] <reverse> <direction>");
					}
					break;
					
			}
		}
	
	@Command(
	        aliases = { "inset" },
	        flags = "hv",
	        desc = "[-hv] <amount>",
	        help = "Insets the selection the given amount\n",
	        min = 1,
	        max = 2
	    )
	public static void inset(CommandContext args) {
		
		if(args.getString(0).matches("\\d+"))
		{
			int amount = args.getInteger(0);
			
			if(args.hasFlag('h'))
			{
				selection.inset(amount, 1);
				PlecksCore.output("Inset selection " + amount + " blocks horizontally.");
			}
			else if(args.hasFlag('v'))
			{
				selection.inset(amount, 2);
				PlecksCore.output("Inset selection " + amount + " blocks vertically.");
			}
			else
			{
				selection.inset(amount, 0);
				PlecksCore.output("Inset selection " + amount + " blocks");
			}
		}
		else
		{
			PlecksCore.output("Inset: Non-number amount given. Syntax: inset [-hv] <amount>");
		}
		
	}
	
	@Command(
	        aliases = { "outset" },
	        flags = "hv",
	        desc = "[-hv] <amount>",
	        help = "Outsets the selection the given amount\n",
	        min = 1,
	        max = 2
	    )
	public static void outset(CommandContext args) {
		
		if(args.getString(0).matches("\\d+"))
		{
			int amount = args.getInteger(0);
			
			if(args.hasFlag('h'))
			{
				selection.outset(amount, 1);
				PlecksCore.output("Outset selection " + amount + " blocks horizontally.");
			}
			else if(args.hasFlag('v'))
			{
				selection.outset(amount, 2);
				PlecksCore.output("Outset selection " + amount + " blocks vertically.");
			}
			else
			{
				selection.outset(amount, 0);
				PlecksCore.output("Outset selection " + amount + " blocks");
			}
		}
		else
		{
			PlecksCore.output("Outset: Non-number amount given. Syntax: inset [-hv] <amount>");
		}
	}

	@Command(
	        aliases = { "shift" },
	        flags = "",
	        desc = "<amount> [direction]",
	        help = "Shifts the selection the given amount\n",
	        min = 1,
	        max = 2
	    )
	public static void shift(CommandContext args) {
		
		if(args.getString(0).matches("\\d+"))
		{
			int amount = args.getInteger(0);
			
			if(args.argsLength() == 2)
			{
				if(EnumDirection.getDirection(args.getString(1)) != null)
				{
					selection.shift(amount, EnumDirection.getDirection(args.getString(1)));
					PlecksCore.output("Shifted selection " + amount + " blocks " + EnumDirection.getDirection(args.getString(1)) + ".");
				}
				else
				{
					PlecksCore.output("Shift: Invalid direction. North/east/south/west/up/down or n/e/s/w/u/d are valid directions.");
				}
			}
			else
			{
				selection.shift(amount, EnumDirection.getPlayerDirectionWithVertical());
				PlecksCore.output("Shifted selection " + amount + " blocks " + EnumDirection.getPlayerDirectionWithVertical().toString() + ".");
			}
			
		}
		else
		{
			PlecksCore.output("Shift: Non-number amount. Syntax: shift <amount> [direction]");
		}	
	}
	
	@Command(
	        aliases = { "size" },
	        flags = "",
	        desc = "",
	        help = "Reports the dimensions and total blocks within the selection\n",
	        min = 0,
	        max = 0
	    )
	public static void size(CommandContext args) {
		
		if(DraftBuilder.instance.selection.checkPoints())
		{
			int dX = Math.abs(selection.pos1.getBlockX() - selection.pos2.getBlockX()) + 1;
			int dY = Math.abs(selection.pos1.getBlockY() - selection.pos2.getBlockY()) + 1;
			int dZ = Math.abs(selection.pos1.getBlockZ() - selection.pos2.getBlockZ()) + 1;
			
			PlecksCore.output("Selection is " + dX + "x by " + dY + "y by " + dZ + "z, " + selection.size() + " blocks total.");
		}
	}
	
	@Command(
	        aliases = { "count" },
	        flags = "",
	        desc = "[block]",
	        help = "Reports the count of given block within selection\n",
	        min = 0,
	        max = 1
	    )
	public static void count(CommandContext args) {
		
		if(args.argsLength() == 0)
		{
			PlecksCore.output("Total non-air blocks in selection: " + selection.count(null));
		}
		else
		{
			EnumBlock block = EnumBlock.lookup(args.getString(0));
			
			if(block != null)
			{
				PlecksCore.output("Total " + block.getName() + " in selection: " + selection.count(new BasicBlock(block.getID(),0)));
			}
			else
			{
				PlecksCore.output("Unknown block given");
			}
		}
	}
	
	@Command(
			aliases = { "clearselection", "clearsel", "clear" },
			flags = "",
			desc = "",
			help = "Clears the selection points\n",
			min = 0,
			max = 0
		)
	public static void clear(CommandContext args) {
		DraftBuilder.instance.selection.clear();
	}
	
	@Command(
	        aliases = { "set" },
	        flags = "",
	        desc = "<block>",
	        help = "Sets all blocks in selection to given block\n",
	        min = 1,
	        max = 1
	    )
	public static void set(CommandContext args) {
		
		BasicBlock block = null;
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

		if(block != null)
		{
			DraftBuilder.instance.selection.set(block);
			PlecksCore.output("Selection set to " + EnumBlock.fromIDAndMeta(block.blockID,block.blockMeta).getName().toLowerCase());
		}
		else
		{
			PlecksCore.output("Unknown block given");
		}
	}
	
	@Command(
	        aliases = { "replace" },
	        flags = "",
	        desc = "[fromBlock] <toBlock>",
	        help = "Replaces all non-air blocks, or optional fromBlock, with toBlock\n",
	        min = 1,
	        max = 2
	    )
	public static void replace(CommandContext args) {

		BasicBlock block = null;
		if(args.getString(0).matches("\\w+\\:\\d+")) //Check if metadata is being provided
		{
			String[] values = args.getString(0).split(":");
			
			block = new BasicBlock(EnumBlock.lookup(values[0]).getID(),Integer.parseInt(values[1]));
		}
		else
		{
			EnumBlock tempBlock = EnumBlock.lookup(args.getString(0));
			int meta = EnumBlock.addDirectionToMeta(tempBlock);
			
			if(tempBlock != null){
				block = new BasicBlock(tempBlock.getID(),meta);
			}
		}
		
		if(block != null)
		{
			if(args.argsLength() == 2)
			{
				BasicBlock block2 = null;
				if(args.getString(1).matches("\\w+\\:\\d+")) //Check if metadata is being provided
				{
					String[] values = args.getString(1).split(":");
					
					block2 = new BasicBlock(EnumBlock.lookup(values[0]).getID(),Integer.parseInt(values[1]));
				}
				else
				{
					EnumBlock tempBlock = EnumBlock.lookup(args.getString(1));
					int meta = EnumBlock.addDirectionToMeta(tempBlock);
					
					if(tempBlock != null){
						block2 = new BasicBlock(tempBlock.getID(),meta);
					}
				}
				if(block2 != null)
				{
					DraftBuilder.instance.selection.replace(block,block2);
					PlecksCore.output("All " + EnumBlock.fromIDAndMeta(block.blockID,block.blockMeta).getName() + " blocks replaced with " + EnumBlock.fromIDAndMeta(block2.blockID,block2.blockMeta).getName());
				}
				else
				{
					PlecksCore.output("Unknown block given");
				}
			}
			else
			{
				DraftBuilder.instance.selection.replace(block);
				PlecksCore.output("All non-air blocks replaced with " + EnumBlock.fromIDAndMeta(block.blockID,block.blockMeta).getName());
			}
		}
		else
		{
			PlecksCore.output("Unknown block given");
		}
	}
	
	@Command(
	        aliases = { "stack" },
	        flags = "",
	        desc = "<count> [direction] [spacing]",
	        help = "Stack copies the selection a given number of times\n" + 
	        		"Direction defaults to current facing, and spacing to\n" +
	        		"the lenth of the side in that direction. Multiple\n" +
	        		"directions may be given",
	        min = 1,
	        max = 7
	    )
	public static void stack(CommandContext args) {
		if(selection.checkPoints())
		{
			if(args.getString(0).matches("^[1-9]\\d*$"))
			{
				int count = args.getInteger(0);
				EnumDirection direction;
				Vector stackVector = new Vector(0,0,0);
				if(args.argsLength() > 1)
				{
					//Iterate through the directions/spacings, building a 'stackVector' which contains the spacing
					//to move in each direction
					int i = 1;
					
					while(i < args.argsLength())
					{
						if((direction = EnumDirection.getDirection(args.getString(i))) != null)
						{
							if(i + 1 < args.argsLength() && args.getString(i + 1).matches("^[1-9]\\d*$"))
							{
								Vector dV = direction.vector().scale(args.getInteger(i + 1));
								stackVector = stackVector.add(dV);
								i++;
							}
							else
							{
								int spacing = 0;
								switch(direction)
								{
								
								case EAST:
								case WEST:
									spacing = selection.getSizeX();
									break;
								case NORTH:
								case SOUTH:
									spacing = selection.getSizeZ();
									break;
								case UP:
								case DOWN:
									spacing = selection.getSizeY();;
									break;
								}
								
								stackVector = stackVector.add(direction.vector().scale(spacing));
							}
						}
						i++;
					}
					selection.stack(count, stackVector);
					PlecksCore.output("Stacked selection " + count + " times with a vector of " + stackVector.toString() + ".");
				}
				else
				{
					direction = EnumDirection.getPlayerDirectionWithVertical();
					int spacing = 0;
					switch(direction)
					{
					
					case EAST:
					case WEST:
						spacing = selection.getSizeX();
						break;
					case NORTH:
					case SOUTH:
						spacing = selection.getSizeZ();
						break;
					case UP:
					case DOWN:
						spacing = selection.getSizeY();;
						break;
					}
					
					stackVector = stackVector.add(direction.vector().scale(spacing));
					selection.stack(count, stackVector);
					//selection.stack(count, direction, spacing);
					PlecksCore.output("Stacked selection " + count + " times with a vector of " + stackVector.toString() + ".");
				}
			}
			else 
			{
				PlecksCore.output("Stack count must be a number");
			}
		}
	}
	
}
