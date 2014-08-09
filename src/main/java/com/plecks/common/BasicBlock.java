package com.plecks.common;

import net.minecraft.block.Block;

import com.plecks.draftbuilder.EnumDirection;

/** Basic block info storage. */
public class BasicBlock {
	
	private Vector position;

	public EnumDirection direction; //Orientation of the block
	public final Block block;
	public final int blockID;
	public final int blockMeta;
	
	
	public BasicBlock(int posX, int posY, int posZ, int id, int meta)
	{
		position = new Vector(posX,posY,posZ);
		block = Block.getBlockById(id);
		blockID = id;
		blockMeta = meta;
	}
	
	
	public BasicBlock(int posX, int posY, int posZ)
	{
		this(posX, posY, posZ, -1, 0);
	}
	
	
	public BasicBlock(int ID, int meta)
	{
		this(0,0,0, ID, meta);
	}
	
	
	/** Returns block's position */
	public Vector getPosition()
	{
		return position;
	}
	
	
	public void setPosition(int posX, int posY, int posZ)
	{
		position = new Vector(posX,posY,posZ);
	}
	
	
	public boolean equalsPosition(int posX, int posY, int posZ)
	{
		return posX == position.getX() && posY == position.getY() && posZ == position.getZ();
	}
	
	
	public int getX()
	{
		return position.getBlockX();
	}
	
	
	public int getY()
	{
		return position.getBlockY();
	}
	
	
	public int getZ()
	{
		return position.getBlockZ();
	}
	
	
	@Override public boolean equals(Object object)
	{
		if (this == object) {return true;}
		
		if(object instanceof BasicBlock)
		{
			BasicBlock compareBlock = (BasicBlock)object;

			if(compareBlock.blockID == -1 || this.blockID == -1 || (compareBlock.blockID == blockID && compareBlock.blockMeta == blockMeta))
			{
				if(compareBlock.getX() == this.getX() && compareBlock.getY() == this.getY() && compareBlock.getZ() == this.getZ())
				{
					return true;
				}
			}
		}
		else if(object instanceof Vector)
		{
			return object.equals(this.position);
		}

		return false;
	}
	
	
	@Override public String toString()
	{
		return this.getX()+","+this.getY()+","+this.getZ()+(blockID > 0 ? ("BlockID " + blockID + " Meta " + blockMeta) : "");
	}
	
}
