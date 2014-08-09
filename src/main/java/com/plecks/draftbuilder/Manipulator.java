package com.plecks.draftbuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.plecks.common.BasicBlock;
import com.plecks.common.Equipper;
import com.plecks.common.PlecksCore;
import com.plecks.common.Vector;

import net.minecraft.block.Block;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C03PacketPlayer.C05PacketPlayerLook;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;

/** Handles manipulation of the world (placing/removing blocks) */
public class Manipulator {
	public boolean toggle = true; //Toggle for this class being ticked
	boolean fastBreak = false;
	
	private Equipper equipper;
	
	private List<BasicBlock> blockChangeList = new ArrayList<BasicBlock>();
	private List<EnumBlock> harvestList = new ArrayList<EnumBlock>();
	private BasicBlock hittingBlock; //Block we're currently breaking
	private int ticks = 0;
	private HashMap<Vector, Long> changeTimer = new HashMap<Vector, Long>(); //List of positions we've recently made a change to, and at what time
	private int changeWaitTime = 1500; //Time in milliseconds to wait after making a change (removing/placing a block) to verify the change
	
	
	public Manipulator() //AKA "politician"
	{
		equipper = new Equipper();
	}
	
	

	/** Returns true if did something */
	public boolean onTick()
	{
		if(hittingBlock == null && !equipper.getPlayerHittingBlock())
		{
			Iterator<BasicBlock> blockIterator = blockChangeList.iterator();
			ItemStack swapItem = null;
			int swapMeta = -1;
			
			while(blockIterator.hasNext())
			{
				BasicBlock block = blockIterator.next();
				
				Block curBlock = PlecksCore.getWorld().getBlock(block.getX(), block.getY(), block.getZ());
				int curBlockID = Block.getIdFromBlock(curBlock);
				int curBlockMeta = PlecksCore.getWorld().getBlockMetadata(block.getX(), block.getY(), block.getZ());
				
				if(changeTimer.containsKey(block.getPosition()))
				{
					if((curBlockID == block.blockID && (block.blockMeta == -1 || curBlockMeta == block.blockMeta)) && System.currentTimeMillis() - changeTimer.get(block.getPosition()) > changeWaitTime)
					{
						blockIterator.remove();
						changeTimer.remove(block.getPosition());
						continue;
					}
					else if(System.currentTimeMillis() - changeTimer.get(block.getPosition()) > changeWaitTime)
					{
						changeTimer.remove(block.getPosition());
						//PlecksCore.getPlayer().sendQueue.addToSendQueue(new Packet14BlockDig(3, block.getX(), block.getY(), block.getZ(),0));
					}
					else
					{
						continue;
					}
				}

				
				//Check that we can place a block there
				if(block.blockID != 0
						&& (curBlockID == 0 || Block.getBlockById(curBlockID).getMaterial().isReplaceable())
						&& isInReach(block.getX(), block.getY(), block.getZ()))
				{
					//Location of block in inventory, if we have it

					int slot = (EnumBlock.needsMetaForItem(block.blockID) ? 
							equipper.getInventorySlotContainItemAndDamage(Item.getItemFromBlock(block.block), EnumBlock.removeOrientationFromMeta(block.blockID, block.blockMeta)) 
							: equipper.getInventorySlotContainItem(Item.getItemFromBlock(block.block)));
				
					if(slot >= 0 && slot <= 8)
					{
						EnumDirection placeDirection = EnumBlock.getPlaceDirection(block);
						EnumDirection faceDirection = EnumBlock.getFacingDirection(block);
						
						if(placeDirection != null)
						{
							int x = block.getX();
							int y = block.getY();
							int z = block.getZ();
							int side = 1;
							
							switch(placeDirection)
							{
							case UP: //Click on bottom
								y++;
								side = 0;
								break;
							case DOWN: //Clicking on the top
								y--;
								side = 1;
								break;
							case SOUTH:
								z++;
								side = 2;
								break;
							case NORTH: //Click south side
								z--;
								side = 3;
								break;
							case EAST:
								x++;
								side = 4;
								break;
							case WEST:
								x--;
								side = 5;
								break;
							}
							
							Block clickedBlock = PlecksCore.getWorld().getBlock(x, y, z);
							int clickedID = Block.getIdFromBlock(clickedBlock);
							if(clickedID != 0 && EnumBlock.isBuildable(clickedID))
							{
								float currentPitch = PlecksCore.getPlayer().rotationPitch;
								float currentYaw = PlecksCore.getPlayer().rotationYaw;
								if(faceDirection != null)
								{
									switch(faceDirection)
									{
										case UP:
											PlecksCore.mc.getNetHandler().addToSendQueue(new C03PacketPlayer.C05PacketPlayerLook(PlecksCore.getPlayer().rotationYaw, -90, PlecksCore.getPlayer().onGround));
											break;
										case DOWN:
											PlecksCore.mc.getNetHandler().addToSendQueue(new C03PacketPlayer.C05PacketPlayerLook(PlecksCore.getPlayer().rotationYaw, 90, PlecksCore.getPlayer().onGround));
											break;
										case NORTH:
											PlecksCore.mc.getNetHandler().addToSendQueue(new C03PacketPlayer.C05PacketPlayerLook(180, PlecksCore.getPlayer().rotationPitch, PlecksCore.getPlayer().onGround));
											break;
										case EAST:
											PlecksCore.mc.getNetHandler().addToSendQueue(new C03PacketPlayer.C05PacketPlayerLook(270, PlecksCore.getPlayer().rotationPitch, PlecksCore.getPlayer().onGround));
											break;
										case SOUTH:
											PlecksCore.mc.getNetHandler().addToSendQueue(new C03PacketPlayer.C05PacketPlayerLook(0, PlecksCore.getPlayer().rotationPitch, PlecksCore.getPlayer().onGround));
											break;
										case WEST:
											PlecksCore.mc.getNetHandler().addToSendQueue(new C03PacketPlayer.C05PacketPlayerLook(90, PlecksCore.getPlayer().rotationPitch, PlecksCore.getPlayer().onGround));
											break;
									}
								}
								
								equipper.setSlot(slot);
								PlecksCore.mc.playerController.onPlayerRightClick(PlecksCore.getPlayer(), PlecksCore.getWorld(), PlecksCore.getPlayer().getCurrentEquippedItem(), x, y, z, side, Vec3.fakePool.getVecFromPool(x, y, z));
								changeTimer.put(block.getPosition(), System.currentTimeMillis());
								
								if(faceDirection != null)
								{
									PlecksCore.mc.getNetHandler().addToSendQueue(new C03PacketPlayer.C05PacketPlayerLook(currentYaw, currentPitch, PlecksCore.getPlayer().onGround));
								}
								return true;
							}
						}
						
					}
					else if(slot > 8 || PlecksCore.getPlayer().capabilities.isCreativeMode)
					{
						swapItem = new ItemStack(Item.getItemFromBlock(block.block), 1, EnumBlock.removeOrientationFromMeta(block.blockID, block.blockMeta));
					}
				}
				
				if((curBlockID != block.blockID || (curBlockMeta != block.blockMeta && block.blockMeta != -1))
						&& curBlockID != 0
						&& !curBlock.getMaterial().isLiquid() 
						&& isInReach(block.getX(), block.getY(), block.getZ()))
				{
					int toolSlot = equipper.getBestTool(curBlock, curBlockMeta, true);
					
					if(!PlecksCore.mc.playerController.isInCreativeMode() && toolSlot >= 0)
					{
						equipper.setSlot(toolSlot);
					}
					//Have the correct tool, or block is weak enough we can punch it
					if(PlecksCore.mc.playerController.isInCreativeMode() || toolSlot >= 0 ||  curBlock.getBlockHardness(PlecksCore.getWorld(), block.getX(), block.getY(), block.getZ()) <= 1)
					{
						PlecksCore.getPlayer().sendQueue.addToSendQueue(new C07PacketPlayerDigging(0, block.getX(), block.getY(), block.getZ(), 1));
						
						if(PlecksCore.mc.playerController.isInCreativeMode())
						{
							PlecksCore.mc.playerController.onPlayerDestroyBlock(block.getX(), block.getY(), block.getZ(), 1);
							changeTimer.put(block.getPosition(), System.currentTimeMillis());
						}
						else
						{
							hittingBlock = new BasicBlock(block.getX(), block.getY(), block.getZ(), curBlockID, 0);
							PlecksCore.mc.effectRenderer.addBlockHitEffects(hittingBlock.getX(), hittingBlock.getY(), hittingBlock.getZ(), 1);
							PlecksCore.getPlayer().swingItem();
						}
						return true;
						
						
					}
				}
			}
			
			if(swapItem != null && equipper.canSwap())
			{
				equipper.swapItemToHotbar(swapItem);
			}
		}
		else if(hittingBlock != null)
		{
			ticks++;
			
			Block curBlock = PlecksCore.getWorld().getBlock(hittingBlock.getX(), hittingBlock.getY(), hittingBlock.getZ());
			int curBlockID = Block.getIdFromBlock(curBlock);
			int curBlockMeta = PlecksCore.getWorld().getBlockMetadata(hittingBlock.getX(), hittingBlock.getY(), hittingBlock.getZ());
			
			if(curBlockID == 0 || !isInReach(hittingBlock.getX(), hittingBlock.getY(), hittingBlock.getZ()))
			{
				hittingBlock = null;
				ticks = 0;
				equipper.setCurrentItemBack();
				return false;
			}
			
			float curBlockDamage = ticks * curBlock.getPlayerRelativeBlockHardness(PlecksCore.getPlayer(), PlecksCore.getPlayer().worldObj, hittingBlock.getX(), hittingBlock.getY(), hittingBlock.getZ());
			
			if(ticks % 5 == 0)
			{
				PlecksCore.getWorld().destroyBlockInWorldPartially(PlecksCore.getPlayer().getEntityId(), hittingBlock.getX(), hittingBlock.getY(), hittingBlock.getZ(), (int)(curBlockDamage * 10.0F) - 1);
				PlecksCore.getPlayer().swingItem();
				if(ticks % 10 == 0)
				{
					PlecksCore.mc.effectRenderer.addBlockHitEffects(hittingBlock.getX(), hittingBlock.getY(), hittingBlock.getZ(), 1);
					
					if(ticks % 20 == 0)
					{
						PlecksCore.mc.getSoundHandler().playSound(new PositionedSoundRecord(new ResourceLocation(curBlock.stepSound.getStepResourcePath()), (curBlock.stepSound.getVolume() + 1.0F) / 8.0F, curBlock.stepSound.getPitch() * 0.5F, (float)hittingBlock.getX() + 0.5F, (float)hittingBlock.getY() + 0.5F, (float)hittingBlock.getZ() + 0.5F));
					}
				}
			}

			if(curBlockDamage >= (fastBreak ? 0.75 : 1.05))
			{
				PlecksCore.mc.getNetHandler().addToSendQueue(new C07PacketPlayerDigging(2, hittingBlock.getX(), hittingBlock.getY(), hittingBlock.getZ(), 1));
				PlecksCore.mc.playerController.onPlayerDestroyBlock(hittingBlock.getX(), hittingBlock.getY(), hittingBlock.getZ(), 0);
				changeTimer.put(hittingBlock.getPosition(), System.currentTimeMillis());
				hittingBlock = null;
				ticks = 0;
				equipper.setCurrentItemBack();
				return true;
			}
			
			int toolSlot = equipper.getBestTool(curBlock,curBlockMeta,true);
			if(toolSlot >= 0)
			{
				equipper.setSlot(toolSlot);
			}
		}
		return false;
	}
	
	public void placeBlock(int x, int y, int z, EnumDirection direction)
	{
		int side = 1;
		
		switch(direction)
		{
		case UP: //Click on bottom
			y++;
			side = 0;
			break;
		case DOWN: //Clicking on the top
			y--;
			side = 1;
			break;
		case SOUTH:
			z++;
			side = 2;
			break;
		case NORTH: //Click south side
			z--;
			side = 3;
			break;
		case EAST:
			x++;
			side = 4;
			break;
		case WEST:
			x--;
			side = 5;
			break;
		}
	}
	
	/** Adds block to changelist  */
	public void setBlock(BasicBlock block)
	{
		//Remove previous block on list at block location, if it exists
		listRemoveByPosition(block.getX(),block.getY(),block.getZ());
		
		if(PlecksCore.getWorld().getBlock(block.getX(),block.getY(),block.getZ()) != block.block
				|| PlecksCore.getWorld().getBlockMetadata(block.getX(),block.getY(),block.getZ()) != block.blockMeta)
		{
			blockChangeList.add(block);
		}
	}
	
	/** Adds blocks to harvestList */
	public void harvest(EnumBlock... blockList)
	{
		harvestList.clear();
		for(EnumBlock block : blockList)
		{
			harvestList.add(block);
		}
	}
	
	/** Removes location from changelist. Returns true if list contained a block at the location (and was then removed) */
	public boolean listRemoveByPosition(int x, int y, int z)
	{
		Iterator <BasicBlock>it = blockChangeList.iterator();
		while (it.hasNext())
		{
			if(it.next().equalsPosition(x,y,z))
			{
				it.remove();
				return true;
			}
		}
		return false;
	}
	
	/** Returns true if the change list contains block position (does not check block id)*/
	public boolean listContains(BasicBlock block)
	{
		Iterator <BasicBlock>it = blockChangeList.iterator();
		while (it.hasNext())
		{
			if(it.next().equalsPosition(block.getX(), block.getY(), block.getZ()))
			{
				return true;
			}
		}
		return false;
	}
	
	/** Returns whether location is within reach of the player */
	public boolean isInReach(int x, int y, int z)
	{
		double reach = 5; //Actually six, but it seems, possibly due to slightly differing client/server player position, that sometimes the server will think you're too far away
		double dX = (x + 0.5) - PlecksCore.getPlayer().posX;
		double dY = (y + 0.5) - PlecksCore.getPlayer().posY;
		double dZ = (z + 0.5) - PlecksCore.getPlayer().posZ;
		return ((reach * reach) > (dX * dX + dY * dY + dZ * dZ));
	}
	
	/** Clears change and harvest lists */
	public void clear()
	{
		blockChangeList.clear();
		harvestList.clear();
	}
	
	public List<BasicBlock>getChangeList()
	{
		return blockChangeList;
	}
}
