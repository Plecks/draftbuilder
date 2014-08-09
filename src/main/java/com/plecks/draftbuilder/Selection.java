package com.plecks.draftbuilder;

import net.minecraft.block.Block;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;

import com.plecks.common.*;


/** Handles setting and manipulating the selection area */
public class Selection {
	
	public Vector pos1;
	public Vector pos2;
		
	public Selection()
	{
		//CommandHandler.register("contract", new SelectionContract());
		//CommandHandler.register("expand", new SelectionExpand());
	}
	
	/** Sets position 1 to the block at the player's feet */
	public void setPos1()
	{
		pos1 = new Vector((int)Math.floor(PlecksCore.mc.thePlayer.posX),(int)Math.floor(PlecksCore.mc.thePlayer.posY)-1,(int)Math.floor(PlecksCore.mc.thePlayer.posZ));
	}
	
	/** Sets position 2 to the block at the player's feet */
	public void setPos2()
	{
		pos2 = new Vector((int)Math.floor(PlecksCore.mc.thePlayer.posX),(int)Math.floor(PlecksCore.mc.thePlayer.posY)-1,(int)Math.floor(PlecksCore.mc.thePlayer.posZ));
	}
	
	public void setPos1(int x, int y, int z)
	{
		pos1 = new Vector(x, y, z);
	}
	
	public void setPos2(int x, int y, int z)
	{
		pos2 = new Vector(x, y, z);
	}
	
	/** Sets position 1 to the block under the target reticle */
	public void setMouseover1()
	{
		MovingObjectPosition target = PlecksCore.mc.thePlayer.rayTrace(1000, 0);
		if(target != null && target.typeOfHit == MovingObjectType.BLOCK)
		{
			pos1 = new Vector(target.blockX,target.blockY,target.blockZ);
		}
	}
	
	/** Sets position 2 to the block under the target reticle */
	public void setMouseover2()
	{
		MovingObjectPosition target = PlecksCore.mc.thePlayer.rayTrace(1000, 0);
		if(target != null && target.typeOfHit == MovingObjectType.BLOCK)
		{
			pos2 = new Vector(target.blockX,target.blockY,target.blockZ);
		}
	}
	
	/** Sets selection to the chunk player is currently in */
	public void setChunk()
	{
		// C+P from Minecraft.java. Uses bitshifting to turn player position into chunk position
		int chunkX = MathHelper.floor_float((int)PlecksCore.mc.thePlayer.posX) >> 4;
        int chunkZ = MathHelper.floor_float((int)PlecksCore.mc.thePlayer.posZ) >> 4;
        
        pos1 = new Vector(chunkX * 16, 0, chunkZ * 16);
        
        //Slightly awkward, using inline if statement so we add/subtract properly based on positive/negative position
        pos2 = new Vector(chunkX * 16 + 15, 255, chunkZ * 16 + 15);

	}
	
	public int getSizeX()
	{
		if(checkPoints()){
			return Math.abs(pos1.getBlockX() - pos2.getBlockX()) + 1;
		}
		return 0;
	}
	
	public int getSizeY()
	{
		if(checkPoints()){
			return Math.abs(pos1.getBlockY() - pos2.getBlockY()) + 1;
		}
		return 0;
	}
	
	public int getSizeZ()
	{
		if(checkPoints()){
			return Math.abs(pos1.getBlockZ() - pos2.getBlockZ()) + 1;
		}
		return 0;
	}
	
	public boolean expand(int amount, int reverse, EnumDirection direction)
	{
		if(!checkPoints())
		{
			PlecksCore.output("Expand: Must have at least one point set");
			return false;
		}
		switch(direction)
		{
			case NORTH:
				if(pos1.getZ() < pos2.getZ()) //Point 1 is further north
				{
					pos1 = pos1.subtract(0, 0, amount);
					pos2 = pos2.add(0, 0, reverse);
				}
				else
				{
					pos1 = pos1.add(0, 0, reverse);
					pos2 = pos2.subtract(0, 0, amount);
				}
				break;
			case SOUTH:
				if(pos1.getZ() > pos2.getZ()) //Point 1 is further south
				{
					pos1 = pos1.add(0, 0, amount);
					pos2 = pos2.subtract(0, 0, reverse);
				}
				else
				{
					pos1 = pos1.subtract(0, 0, reverse);
					pos2 = pos2.add(0, 0, amount);
				}
				break;
			case EAST:
				if(pos1.getX() > pos2.getX()) //Point 1 is further east
				{
					pos1 = pos1.add(amount, 0, 0);
					pos2 = pos2.subtract(reverse, 0, 0);
				}
				else
				{
					pos1 = pos1.subtract(reverse, 0, 0);
					pos2 = pos2.add(amount, 0, 0);
				}
				break;
			case WEST:
				if(pos1.getX() < pos2.getX()) //Point 1 is further west
				{
					pos1 = pos1.subtract(amount, 0, 0);
					pos2 = pos2.add(reverse, 0, 0);
				}
				else
				{
					pos1 = pos1.add(reverse, 0, 0);
					pos2 = pos2.subtract(amount, 0, 0);
				}
				break;
			case UP:
				if(pos1.getY() > pos2.getY()) //Point 1 is higher up
				{
					pos1 = pos1.add(0, amount, 0);
					pos2 = pos2.subtract(0, reverse, 0);
				}
				else
				{
					pos1 = pos1.subtract(0, reverse, 0);
					pos2 = pos2.add(0, amount, 0);
				}
				break;
			case DOWN:
				if(pos1.getY() < pos2.getY()) //Point 1 is lower down
				{
					pos1 = pos1.subtract(0, amount, 0);
					pos2 = pos2.add(0, reverse, 0);
				}
				else
				{
					pos1 = pos1.add(0, reverse, 0);
					pos2 = pos2.subtract(0, amount, 0);
				}
				break;
		}
		return true;
	}
	
	public boolean expand(int amount, int reverse)
	{
		return expand(amount, reverse, EnumDirection.getPlayerDirectionWithVertical());
	}
	
	public boolean expand(int amount, EnumDirection direction)
	{
		return expand(amount,0,direction);
	}
	
	public boolean expand(int amount)
	{
		return expand(amount,0,EnumDirection.getPlayerDirectionWithVertical());
	}
	
	
	public boolean contract(int amount, int reverse, EnumDirection direction)
	{
		if(!checkPoints())
		{
			PlecksCore.output("Contract: Must have at least one point set");
			return false;
		}
		switch(direction)
		{
			//Same code as expand, but switched which position gets the amount/reverse added to it
			case NORTH:
				if(pos1.getZ() < pos2.getZ()) //Position 1 is further north
				{
					pos1 = pos1.add(0, 0, reverse);
					pos2 = pos2.subtract(0, 0, amount);
				}
				else
				{
					pos1 = pos1.subtract(0, 0, amount);
					pos2 = pos2.add(0, 0, reverse);
				}
				break;
			case SOUTH:
				if(pos1.getZ() > pos2.getZ())
				{
					pos1 = pos1.subtract(0, 0, reverse);
					pos2 = pos2.add(0, 0, amount);
				}
				else
				{
					pos1 = pos1.add(0, 0, amount);
					pos2 = pos2.subtract(0, 0, reverse);
				}
				break;
			case EAST:
				if(pos1.getX() > pos2.getX())
				{
					pos1 = pos1.subtract(reverse, 0, 0);
					pos2 = pos2.add(amount, 0, 0);
				}
				else
				{
					pos1 = pos1.add(amount, 0, 0);
					pos2 = pos2.subtract(reverse, 0, 0);
				}
				break;
			case WEST:
				if(pos1.getX() < pos2.getX())
				{
					pos1 = pos1.add(reverse, 0, 0);
					pos2 = pos2.subtract(amount, 0, 0);
				}
				else
				{
					pos1 = pos1.subtract(amount, 0, 0);
					pos2 = pos2.add(reverse, 0, 0);
				}
				break;
			case UP:
				if(pos1.getY() > pos2.getY())
				{
					pos1 = pos1.subtract(0, reverse, 0);
					pos2 = pos2.add(0, amount, 0);
				}
				else
				{
					pos1 = pos1.add(0, amount, 0);
					pos2 = pos2.subtract(0, reverse, 0);
				}
				break;
			case DOWN:
				if(pos1.getY() < pos2.getY())
				{
					pos1 = pos1.add(0, reverse, 0);
					pos2 = pos2.subtract(0, amount, 0);
				}
				else
				{
					pos1 = pos1.subtract(0, amount, 0);
					pos2 = pos2.add(0, reverse, 0);
				}
				break;
		}
		return true;
		//PlecksCore.output("Contracted selection " + amount + " blocks " + direction.toString() + "." + (reverse > 0 ? " " + reverse + " blocks reverse." : ""));
	}
	
	public boolean contract(int amount, int reverse)
	{
		return contract(amount, reverse, EnumDirection.getPlayerDirectionWithVertical());
	}
	
	public boolean contract(int amount, EnumDirection direction)
	{
		return contract(amount,0,direction);
	}
	
	public boolean contract(int amount)
	{
		return contract(amount,0,EnumDirection.getPlayerDirectionWithVertical());
	}
	
	/** Outset selection by amount. Flag 0 = all directions, 1 = horizontal only, 2 = vertical only */
	public void outset(int amount, int flag)
	{
		if(!checkPoints())
		{
			PlecksCore.output("Outset: Must have at least one point set");
			return;
		}
		//Horizontal
		if(flag == 0 || flag == 1)
		{
			expand(amount, amount, EnumDirection.NORTH);
			expand(amount, amount, EnumDirection.EAST);
		}
		
		//Vertical
		if(flag == 0 || flag == 2)
		{
			expand(amount, amount, EnumDirection.UP);
		}
		
		//PlecksCore.output("Outset selection " + amount + " blocks" + (flag == 1 ? " horizontally." : flag == 2 ? " vertically." : "."));
	}
	
	/** Inset selection by amount. Flag 0 = all directions, 1 = horizontal only, 2 = vertical only */
	public void inset(int amount, int flag)
	{
		if(!checkPoints())
		{
			PlecksCore.output("Inset: Must have at least one point set");
			return;
		}
		//Horizontal
		if(flag == 0 || flag == 1)
		{
			contract(amount, amount, EnumDirection.NORTH);
			contract(amount, amount, EnumDirection.EAST);
		}
		
		//Vertical
		if(flag == 0 || flag == 2)
		{
			contract(amount, amount, EnumDirection.UP);
		}
		
	}
	
	/** Shift selection amount blocks in direction. */
	public void shift(int amount, EnumDirection direction)
	{
		if(!checkPoints())
		{
			PlecksCore.output("Shift: Must have at least one point set");
			return;
		}
		pos1 = pos1.add(amount * direction.vector().getBlockX(), amount * direction.vector().getBlockY(),amount * direction.vector().getBlockZ());
		pos2 = pos2.add(amount * direction.vector().getBlockX(), amount * direction.vector().getBlockY(),amount * direction.vector().getBlockZ());
		
	}
	
	public void shift(int amount)
	{
		shift(amount, EnumDirection.getPlayerDirectionWithVertical());
	}
	
	/** Return the size of the selection */
	public int size()
	{
		if(!checkPoints())
		{
			return 0;
		}
		int dX = Math.abs(pos1.getBlockX() - pos2.getBlockX()) + 1;
		int dY = Math.abs(pos1.getBlockY() - pos2.getBlockY()) + 1;
		int dZ = Math.abs(pos1.getBlockZ() - pos2.getBlockZ()) + 1;
		return (int) (dX * dY * dZ);
	}
	
	/** Return the number of blocks of type blockID in the selection */
	public int count(BasicBlock block)
	{
		if(!checkPoints())
		{
			return 0;
		}
		
		int total = 0;
		
		for(int x = minPoint().getBlockX(); x <= maxPoint().getX(); x++)
		{
			for(int y = minPoint().getBlockY(); y <= maxPoint().getY(); y++)
			{
				for(int z = minPoint().getBlockZ(); z <= maxPoint().getZ(); z++)
				{
					Block curBlock = PlecksCore.getWorld().getBlock(x, y, z);
					if((block == null && curBlock.isAir(PlecksCore.getWorld(), x, y, z)) || (block != null && Block.getIdFromBlock(curBlock) == block.blockID))
					{
						total++;
					}
				}
			}
		}
		
		return total;
	}
	
	/** Return the total of each type of block in the selection */
	public void distribution()
	{
		
	}
	
	/** Clear the selection points **/
	public void clear()
	{
		pos1 = null;
		pos2 = null;
	}
	
	/** Set all blocks in selection to given block **/
	public void set(BasicBlock block)
	{
		if(!checkPoints())
		{
			PlecksCore.output("Selection: Must have at least one point set");
			return;
		}
		
		int count = 0;
		for(int x = minPoint().getBlockX(); x <= maxPoint().getX(); x++)
		{
			for(int y = minPoint().getBlockY(); y <= maxPoint().getY(); y++)
			{
				for(int z = minPoint().getBlockZ(); z <= maxPoint().getZ(); z++)
				{
					Block curBlock = PlecksCore.getWorld().getBlock(x, y, z);
					if(curBlock != block.block || PlecksCore.getWorld().getBlockMetadata(x, y, z) != block.blockMeta)
					{
						DraftBuilder.instance.manipulator.setBlock(new BasicBlock(x,y,z,block.blockID,block.blockMeta));
						count++;
					}
					//Make sure location isn't on remove list, since we now want the block that is there
					else {DraftBuilder.instance.manipulator.listRemoveByPosition(x,y,z);}
				}
			}
		}
	}
	
	/** Replace all fromID blocks with toID.
	 * 
	 * @param block null for all non-air blocks
	 * @param block2.blockID
	 */
	public void replace(BasicBlock fromBlock, BasicBlock toBlock)
	{
		if(!checkPoints())
		{
			PlecksCore.output("Selection: Must have at least one point set");
			return;
		}
		
		for(int x = minPoint().getBlockX(); x <= maxPoint().getX(); x++)
		{
			for(int y = minPoint().getBlockY(); y <= maxPoint().getY(); y++)
			{
				for(int z = minPoint().getBlockZ(); z <= maxPoint().getZ(); z++)
				{
					Block curBlock = PlecksCore.getWorld().getBlock(x, y, z);
					int curMeta = PlecksCore.getWorld().getBlockMetadata(x, y, z);
					if((curBlock != toBlock.block || curMeta != toBlock.blockMeta) 
							&& ((fromBlock == null && curBlock.isAir(PlecksCore.getWorld(), x, y, z)) 
							||  (fromBlock != null && fromBlock.block == curBlock)))
					{
						
						DraftBuilder.instance.manipulator.setBlock(new BasicBlock(x,y,z,toBlock.blockID,toBlock.blockMeta));
					}
					//Make sure location isn't on remove list, since we now want the block that is there
					else {DraftBuilder.instance.manipulator.listRemoveByPosition(x,y,z);}
				}
			}
		}
	}
	
	/** Replace all non-air blocks with toID */
	public void replace(BasicBlock toBlock)
	{
		replace(null,toBlock);
	}
	
	/** Stack copies of selection in a given direction */
	public void stack(int count, EnumDirection direction, int spacing)
	{
		if(checkPoints()){
			
			for(int x = minPoint().getBlockX(); x <= maxPoint().getX(); x++)
			{
				for(int y = minPoint().getBlockY(); y <= maxPoint().getY(); y++)
				{
					for(int z = minPoint().getBlockZ(); z <= maxPoint().getZ(); z++)
					{
						Block curBlock = PlecksCore.getWorld().getBlock(x, y, z);
						int curMeta = PlecksCore.getWorld().getBlockMetadata(x, y, z);
						
						for(int i = 1; i <= count; i++)
						{
							BasicBlock block = new BasicBlock(x + direction.vector().getBlockX()*spacing*i, 
									y + direction.vector().getBlockY()*spacing*i, 
									z + direction.vector().getBlockZ()*spacing*i,
									Block.getIdFromBlock(curBlock),
									curMeta);
							DraftBuilder.instance.manipulator.setBlock(block);
						}
					}
				}
			}
		}
	}
	
	public void stack(int count, Vector stackVector)
	{
		if(checkPoints()){
			
			for(int x = minPoint().getBlockX(); x <= maxPoint().getX(); x++)
			{
				for(int y = minPoint().getBlockY(); y <= maxPoint().getY(); y++)
				{
					for(int z = minPoint().getBlockZ(); z <= maxPoint().getZ(); z++)
					{
						Block curBlock = PlecksCore.getWorld().getBlock(x, y, z);
						int curMeta = PlecksCore.getWorld().getBlockMetadata(x, y, z);
						
						for(int i = 1; i <= count; i++)
						{
							BasicBlock block = new BasicBlock(x + stackVector.getBlockX()*i,
									y + stackVector.getBlockY()*i,
									z + stackVector.getBlockZ()*i,
									Block.getIdFromBlock(curBlock),
									curMeta);
							DraftBuilder.instance.manipulator.setBlock(block);
						}
					}
				}
			}
		}
	}
	
	/** Returns false if no points have been set.
	 * If only one point is set, this will set the null
	 * point to the position of the set point.
	 * @return
	 */
	public boolean checkPoints()
	{
		if(pos1 == null && pos2 == null)
		{
			return false;
		}
		else if(pos1 != null && pos2 == null)
		{
			pos2 = new Vector(pos1.getX(),pos1.getY(),pos1.getZ());
		}
		else if(pos1 == null && pos2 != null)
		{
			pos1 = new Vector(pos2.getX(),pos2.getY(),pos2.getZ());
		}
		
		return true;
	}
	
	/** Returns a Vector made up of the smallest of each x,y,z component between the two positions */
	public Vector minPoint()
	{
		if(checkPoints())
		{
			return new Vector(Math.min(pos1.getX(), pos2.getX()),Math.min(pos1.getY(), pos2.getY()),Math.min(pos1.getZ(), pos2.getZ()));
		}
		return null;
	}
	
	/** Returns a Vector made up of the largest of each x,y,z component between the two positions */
	public Vector maxPoint()
	{
		if(checkPoints())
		{
			return new Vector(Math.max(pos1.getX(), pos2.getX()),Math.max(pos1.getY(), pos2.getY()),Math.max(pos1.getZ(), pos2.getZ()));
		}
		return null;
	}
}
