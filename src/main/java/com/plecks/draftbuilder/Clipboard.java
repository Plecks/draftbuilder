package com.plecks.draftbuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

import net.minecraft.block.Block;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ReportedException;

import com.plecks.common.BasicBlock;
import com.plecks.common.PlecksCore;
import com.plecks.common.Vector;

public class Clipboard {
	
	public short width;
	public short height;
	public short length;
	public byte[] blocks;
	public byte[] data;
	public byte[][][] blockarray;
	public byte[][][] dataarray;
	public NBTTagList tileentities;
	public Vector origin;
	public Vector offset;

	public Clipboard(short w, short h, short l)
	{
		width = w;
		height = h;
		length = l;
		blocks = new byte[w*h*l];
		data = new byte[w*h*l];
	}
	
	public Clipboard(int w, int h, int l)	
	{
		this((short)w, (short)h, (short)l);
	}
	
	public boolean load(String schematic)
	{
		try {
	        if(!schematic.endsWith(".schematic"))
	        {
	        	schematic = schematic + ".schematic";
	        }
	        File f = new File("schematics/textSchematic.schematic");
	        System.out.println("Test schem: " + f.getAbsolutePath());
	        FileInputStream fis = new FileInputStream("schematics/" + schematic);

	        NBTTagCompound nbtdata = CompressedStreamTools.readCompressed(fis);;
	
	        
	        width = nbtdata.getShort("Width");
	        height = nbtdata.getShort("Height");
	        length = nbtdata.getShort("Length");
	
	        blockarray = convert1Dto3DArray(nbtdata.getByteArray("Blocks"), height, length, width);
	        dataarray = convert1Dto3DArray(nbtdata.getByteArray("Data"), height, length, width);
	
	        //tileentities = nbtdata.getTagList("TileEntities");
	        
	        //WorldEdit origin
	        try {
	            int originX = nbtdata.getInteger("WEOriginX");
	            int originY = nbtdata.getInteger("WEOriginY");
	            int originZ = nbtdata.getInteger("WEOriginZ");
	            origin = new Vector(originX, originY, originZ);
	        } catch (ReportedException e) {
	            // No origin data
	        }
	        //WorldEdit offset
	        try {
	            int offsetX = nbtdata.getInteger("WEOffsetX");
	            int offsetY = nbtdata.getInteger("WEOffsetY");
	            int offsetZ = nbtdata.getInteger("WEOffsetZ");
	            offset = new Vector(offsetX, offsetY, offsetZ);
	        } catch (ReportedException e) {
	            // No offset data
	        }
	        
	        fis.close();
	    } catch (Exception e) {
	    	e.printStackTrace();
	        return false;
	    }
		
		
		return true;
	}
	
	public boolean save(String schematic)
	{
		try {
			File file = new File("schematics/" + schematic);
			
			if (!file.exists()) {
				if(!file.getParentFile().exists()){
					file.getParentFile().mkdirs();
				}
				file.createNewFile();
			}
			System.out.println("Schematic file at: " + file.getAbsolutePath());

	        OutputStream fos = new FileOutputStream(file);

	        NBTTagCompound nbtdata = new NBTTagCompound();
	
	        nbtdata.setShort("Width", width);
	        nbtdata.setShort("Height", height);
	        nbtdata.setShort("Length", length);
	
	        nbtdata.setByteArray("Blocks", convert3Dto1DArray(blockarray));
	        nbtdata.setByteArray("Data", convert3Dto1DArray(dataarray));

	        //nbtdata.setTag("TileEntities",tileentities);
	        
	        CompressedStreamTools.writeCompressed(nbtdata, fos);
	        fos.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
		return true;
	}
	
	public void clear()
	{
		width = 0;
		height = 0;
		length = 0;
		blockarray = null;
		dataarray = null;
		tileentities = null;
	}

	public void copy(Selection sel) {
		
		if(sel.checkPoints())
		{
			blockarray = new byte[sel.getSizeY()][sel.getSizeZ()][sel.getSizeX()];
			dataarray = new byte[sel.getSizeY()][sel.getSizeZ()][sel.getSizeX()];
			width = (short)sel.getSizeX();
			height = (short)sel.getSizeY();
			length = (short)sel.getSizeZ();
			offset = new Vector(sel.minPoint().getBlockX() - (int)Math.floor(PlecksCore.mc.thePlayer.posX),sel.minPoint().getBlockY() - (int)Math.floor(PlecksCore.mc.thePlayer.posY),sel.minPoint().getBlockZ() - (int)Math.floor(PlecksCore.mc.thePlayer.posZ));
			origin = new Vector(sel.minPoint().getBlockX(),sel.minPoint().getBlockY(),sel.minPoint().getBlockZ());
			
			for(int y = (Math.min(sel.pos1.getBlockY(), sel.pos2.getBlockY())); y <= (Math.max(sel.pos1.getBlockY(), sel.pos2.getBlockY())); y++)
			{
				for(int z = (Math.min(sel.pos1.getBlockZ(), sel.pos2.getBlockZ())); z <= (Math.max(sel.pos1.getBlockZ(), sel.pos2.getBlockZ())); z++)
				{
					for(int x = (Math.min(sel.pos1.getBlockX(), sel.pos2.getBlockX())); x <= (Math.max(sel.pos1.getBlockX(), sel.pos2.getBlockX())); x++)
					{
						blockarray[y - origin.getBlockY()][z - origin.getBlockZ()][x - origin.getBlockX()] = (byte)Block.getIdFromBlock((PlecksCore.mc.theWorld.getBlock(x, y, z)));
						dataarray[y - origin.getBlockY()][z - origin.getBlockZ()][x - origin.getBlockX()] = (byte)PlecksCore.mc.theWorld.getBlockMetadata(x, y, z);
					}
				}
			}
		}
		
	}
	
	public void cut(Selection sel, BasicBlock leaveBlock) {
		
		if(sel.checkPoints())
		{
			blockarray = new byte[sel.getSizeY()][sel.getSizeZ()][sel.getSizeX()];
			dataarray = new byte[sel.getSizeY()][sel.getSizeZ()][sel.getSizeX()];
			width = (short)sel.getSizeX();
			height = (short)sel.getSizeY();
			length = (short)sel.getSizeZ();
			offset = new Vector(sel.minPoint().getBlockX() - (int)Math.floor(PlecksCore.mc.thePlayer.posX),sel.minPoint().getBlockY() - (int)Math.floor(PlecksCore.mc.thePlayer.posY),sel.minPoint().getBlockZ() - (int)Math.floor(PlecksCore.mc.thePlayer.posZ));
			origin = new Vector(sel.minPoint().getBlockX(),sel.minPoint().getBlockY(),sel.minPoint().getBlockZ());
			
			for(int y = (Math.min(sel.pos1.getBlockY(), sel.pos2.getBlockY())); y <= (Math.max(sel.pos1.getBlockY(), sel.pos2.getBlockY())); y++)
			{
				for(int z = (Math.min(sel.pos1.getBlockZ(), sel.pos2.getBlockZ())); z <= (Math.max(sel.pos1.getBlockZ(), sel.pos2.getBlockZ())); z++)
				{
					for(int x = (Math.min(sel.pos1.getBlockX(), sel.pos2.getBlockX())); x <= (Math.max(sel.pos1.getBlockX(), sel.pos2.getBlockX())); x++)
					{
						blockarray[y][z][x] = (byte)Block.getIdFromBlock((PlecksCore.mc.theWorld.getBlock(x, y, z)));
						dataarray[y][z][x] = (byte)PlecksCore.mc.theWorld.getBlockMetadata(x, y, z);
					}
				}
			}
			
			if(leaveBlock == null)
				leaveBlock = new BasicBlock(0,0);
			
			sel.set(leaveBlock);
		}
		
	}
	
	public void paste(){
		for(int y = 0; y < height; y++)
		{
			for(int z = 0; z < length; z++)
			{
				for(int x = 0; x < width; x++)
				{
					int placeX = x + (int)Math.floor(PlecksCore.mc.thePlayer.posX) + offset.getBlockX();
					int placeY = y + (int)Math.floor(PlecksCore.mc.thePlayer.posY) + offset.getBlockY();
					int placeZ = z + (int)Math.floor(PlecksCore.mc.thePlayer.posZ) + offset.getBlockZ();
					BasicBlock block = new BasicBlock(placeX, placeY, placeZ, blockarray[y][z][x], dataarray[y][z][x]);
					DraftBuilder.instance.manipulator.setBlock(block);
				}
			}
		}
	}
	
	//TODO: Flip needs to change the metadata so blocks are facing the right direction
	public void flip(EnumDirection dir, Vector flipAboutPoint)
	{
		switch(dir){
			//Flip up/down (across the horizontal x/z plane)
			//x/z values should stay the same, y values should flip
			//Move backwards through the y values, but normal through x/z.
			case UP:
			case DOWN:
				for(int y = 0; y < blockarray.length / 2; y++)
				{
					byte[][] temp = blockarray[y];
					blockarray[y] = blockarray[blockarray.length - y - 1];
					blockarray[blockarray.length - y - 1] = temp;
					
					byte[][] tempdata = dataarray[y];
					dataarray[y] = dataarray[dataarray.length - y - 1];
					dataarray[dataarray.length - y - 1] = tempdata;
				}
				
				int middleY = (int)(blockarray.length / 2);
				int shiftY = flipAboutPoint.getBlockY() - middleY;
				offset = offset.add(0, shiftY * 2, 0);
				break;
			case NORTH:
			case SOUTH:
				for(int y = 0; y < blockarray.length; y++)
				{
					for(int z = 0; z < blockarray[y].length / 2; z++)
					{
						byte[] temp = blockarray[y][z];
						blockarray[y][z] = blockarray[y][blockarray[y].length - z - 1];
						blockarray[y][blockarray[y].length - z - 1] = temp;
						
						byte[] tempdata = dataarray[y][z];
						dataarray[y][z] = dataarray[y][dataarray[y].length - z - 1];
						dataarray[y][dataarray[y].length - z - 1] = tempdata;
					}
					
				}
				
				int middleZ = (int)(blockarray[0].length / 2);
				int shiftZ = flipAboutPoint.getBlockZ() - middleZ;
				offset = offset.add(0, 0, shiftZ * 2);
				break;
			case EAST:
			case WEST:
				for(int y = 0; y < blockarray.length; y++)
				{
					for(int z = 0; z < blockarray[y].length; z++)
					{
						for(int x = 0; x < blockarray[y][z].length / 2; x++)
						{
							byte temp = blockarray[y][z][x];
							blockarray[y][z][x] = blockarray[y][z][blockarray[y][z].length - x - 1];
							blockarray[y][z][blockarray[y].length - x - 1] = temp;
							
							byte tempdata = dataarray[y][z][x];
							dataarray[y][z][x] = dataarray[y][z][dataarray[y][z].length - x - 1];
							dataarray[y][z][dataarray[y][z].length - x - 1] = tempdata;
						}
					}
				}
				int middleX = (int)(blockarray[0][0].length / 2);
				int shiftX = flipAboutPoint.getBlockX() - middleX;
				offset = offset.add(shiftX * 2, 0, 0);
				break;
		}
	}
	
	public void rotate()
	{
		
	}
	
	/** Converts an NBT-style single dimension byte array into a three dimensional byte array
	 *  Reverses the y/z/x (x changes the fastest) stacking pattern Minecraft uses to store blocks
	 * @param array
	 * @param height
	 * @param length
	 * @param width
	 * @return threeDArray
	 */
	public byte[][][] convert1Dto3DArray(byte[] array, int height, int length, int width)
	{
		byte[][][] threeDArray = new byte[height][length][width];
		int i = 0;
		for(int y = 0; y < height; y++)
		{
			for(int z = 0; z < length; z++)
			{
				for(int x = 0; x < width; x++)
				{
					threeDArray[y][z][x] = array[i];
					i++;
				}
			}
		}
		return threeDArray;
	}
	
	/** Converts a 3-dimension y/z/x array into an NBT-style one-dimensional array
	 * 
	 * @param threeDArray
	 * @return oneDArray
	 */
	public byte[] convert3Dto1DArray(byte[][][] threeDArray)
	{
		byte[] oneDArray = new byte[threeDArray.length*threeDArray[0].length*threeDArray[0][0].length];
		int i = 0;
		
		for(int y = 0; y < threeDArray.length; y++)
		{
			for(int z = 0; z < threeDArray[y].length; z++)
			{
				for(int x = 0; x < threeDArray[y][z].length; x++)
				{
					oneDArray[i] = threeDArray[y][z][x];
					i++;
				}
			}
		}
		
		return oneDArray;
	}
}
