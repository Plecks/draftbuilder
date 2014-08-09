package com.plecks.draftbuilder;

import net.minecraft.client.Minecraft;
import net.minecraft.util.MathHelper;

import com.plecks.common.Vector;

public enum EnumDirection{
	//Cardinal directions
	//South is +Z, North -Z
	//East is +X, West -X
	/*public static final Vector NORTH = new Vector(0,0,-1);
	public static final Vector SOUTH = new Vector(0,0,1);
	public static final Vector EAST = new Vector(1,0,0);
	public static final Vector WEST = new Vector(-1,0,0);
	public static final Vector UP = new Vector(0,1,0);
	public static final Vector DOWN = new Vector(0,-1,0);*/
	SOUTH (new Vector(0,0,1)),
	WEST (new Vector(-1,0,0)),
	NORTH (new Vector(0,0,-1)),
	EAST (new Vector(1,0,0)),
	UP (new Vector(0,1,0)),
	DOWN (new Vector(0,-1,0));
	
	//private final int direction;
	private final Vector vector;
	
	EnumDirection(Vector v)
	{
		vector = v;
	}
	
	public Vector vector()
	{
		return vector;
	}
	
	public static EnumDirection getDirection(int facing)
	{
		switch(facing)
		{
			case 0:
				return SOUTH;
			case 1:
				return WEST;
			case 2:
				return NORTH;
			case 3:
				return EAST;
			case 4:
				return UP;
			case 5:
				return DOWN;
			default:
				return SOUTH;
		}
	}
	
	public static EnumDirection getDirection(String dir)
	{
		if(dir.toLowerCase().equals("south") || dir.toLowerCase().equals("s"))
		{
			return SOUTH;
		}
		else if(dir.toLowerCase().equals("west") || dir.toLowerCase().equals("w"))
		{
			return WEST;
		}
		else if(dir.toLowerCase().equals("north") || dir.toLowerCase().equals("n"))
		{
			return NORTH;
		}
		else if(dir.toLowerCase().equals("east") || dir.toLowerCase().equals("e"))
		{
			return EAST;
		}
		else if(dir.toLowerCase().equals("up") || dir.toLowerCase().equals("u"))
		{
			return UP;
		}
		else if(dir.toLowerCase().equals("down") || dir.toLowerCase().equals("d"))
		{
			return DOWN;
		}
		return null;
	}
	
	public static EnumDirection getDirection(Vector vec)
	{
		for(EnumDirection dir : EnumDirection.values())
		{
			if(vec.equals(dir.vector))
			{
				return dir;
			}
		}
		
		return null;
	}
	
	public static EnumDirection getPlayerDirection()
	{
		return getDirection(MathHelper.floor_double((double)((Minecraft.getMinecraft().thePlayer.rotationYaw * 4F) / 360F) + 0.5D) & 3);
	}
	
	public static EnumDirection getPlayerDirectionWithVertical()
	{
		if(Minecraft.getMinecraft().thePlayer.rotationPitch < -60)
		{
			return UP;
		}
		else if(Minecraft.getMinecraft().thePlayer.rotationPitch > 60)
		{
			return DOWN;
		}
		
		return getPlayerDirection();
	}
	
	public EnumDirection opposite()
	{
		return EnumDirection.getDirection(this.vector.scale(-1));
	}
	
	@Override
	public String toString() 
	{
		switch (this) 
		{
			case SOUTH:
				return "south";
			case WEST:
				return "west";
			case NORTH:
				return "north";
			case EAST:
				return "east";
			case UP:
				return "up";
			case DOWN:
				return "down";
		}
		return "";
	}
}
