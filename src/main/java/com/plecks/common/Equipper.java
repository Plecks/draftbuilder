package com.plecks.common;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;
import net.minecraftforge.common.ForgeHooks;
import cpw.mods.fml.common.ObfuscationReflectionHelper;

public class Equipper {
	
	protected Minecraft mc;
	
	protected int lastSlot = -1;
	protected long lastSwapTime = 0;
	protected int maxSwaps = 3; //Max hotbar spaces to use up for swapping //TODO Config
	protected int swapCounter = 0;
	protected int swapResetTime = 2000; //Time after last swap to reset swap counter //TODO Config
	protected int swapWaitTime = 500; //Min time to wait between swaps //TODO Config
	
	public Equipper()
	{
		mc = Minecraft.getMinecraft();
	}
	
	public int getEffectiveTool(Block block, int meta)
	{
		if(mc.playerController.isNotCreative()) //No tool needed in creative mode
		{
			for(int i = 0; i < mc.thePlayer.inventory.mainInventory.length; i++)
			{
				ItemStack stack = mc.thePlayer.inventory.mainInventory[i];
				
				if(stack != null && (stack./*getStrVsBlock*/func_150997_a(block) > 1 || ForgeHooks.isToolEffective(stack, block, meta)))
				{
					return i;
				}
			}
		}
		return -1;
	}
	
	public int getHarvestTool(Block block, int meta)	
	{

		if(mc.playerController.isNotCreative()) //No tool needed in creative mode
		{
			for(int i = 0; i < mc.thePlayer.inventory.mainInventory.length; i++)
			{
				ItemStack stack = mc.thePlayer.inventory.mainInventory[i];
				
				if(stack != null && (stack./*canHarvestBlock*/func_150998_b(block) || ForgeHooks.canToolHarvestBlock(block,meta,stack)))
				{
					return i;
				}
			}
		}
		return -1;
	}
	
	/** Returns the first slot that is empty or contains an item/block without durability */
	public int getNonTool()
	{
		if(mc.thePlayer.getCurrentEquippedItem() == null || !mc.thePlayer.getCurrentEquippedItem().isItemStackDamageable())
		{
			return mc.thePlayer.inventory.currentItem;

		}
		for(int i = 0; i < mc.thePlayer.inventory.mainInventory.length; i++)
		{
			ItemStack stack = mc.thePlayer.inventory.mainInventory[i];

			if(stack == null || !stack.isItemStackDamageable())
			{
				return i;
			}
		}
		return -1;
	}
	
	/** Returns first available tool for breaking a block. 
	 * Selects a slot that won't take durability loss if no tool is needed
	 * @param blockID
	 * @param meta
	 * @param hotbarOnly
	 * @return
	 */
	public int getBestTool(Block block, int meta, boolean hotbarOnly)
	{

		if(block != null)
		{
			if(block.getBlockHardness(mc.theWorld, 0, 0, 0) < .5 && 
					block.getMaterial().isToolNotRequired())
			{
				return getNonTool();
			}
			else
			{
				
				int slot = getEffectiveTool(block, meta);
				if(slot >= 0 && (!hotbarOnly || slot <= 8))
				{
					return slot;
				}
				else if(block.getMaterial().isToolNotRequired())
				{
					return getNonTool();
				}
				else
				{
					slot = getHarvestTool(block,meta);
					if(slot >= 0 && (!hotbarOnly || slot <= 8))
					{
						return slot;
					}
				}
			}
		}
		return getNonTool();
	}
	
	//Copypasta from InventoryPlayer
    public int getInventorySlotContainItem(Item item)
    {
        for (int i = 0; i < mc.thePlayer.inventory.mainInventory.length; ++i)
        {
            if ( mc.thePlayer.inventory.mainInventory[i] != null &&  mc.thePlayer.inventory.mainInventory[i].getItem() == item)
            {
                return i;
            }
        }

        return -1;
    }

    //Copypasta from InventoryPlayer
    public int getInventorySlotContainItemAndDamage(Item item, int meta)
    {
        for (int i = 0; i <  mc.thePlayer.inventory.mainInventory.length; ++i)
        {
            if ( mc.thePlayer.inventory.mainInventory[i] != null &&  mc.thePlayer.inventory.mainInventory[i].getItem() == item &&  mc.thePlayer.inventory.mainInventory[i].getItemDamage() == meta)
            {
                return i;
            }
        }

        return -1;
    }
    
    //XXX On Minecraft update, may need variable location update
    /** Gets the private PlayerControllerMP boolean value playerHittingBlock */
    public boolean getPlayerHittingBlock()
    {
    	return (Boolean)ObfuscationReflectionHelper.getPrivateValue(PlayerControllerMP.class, mc.playerController, 9);
    }

    /** Sets current item to slot, saves original slot */
	public void setSlot(int slot)
	{
		if(slot >= 0 && slot <= 8 && slot != mc.thePlayer.inventory.currentItem)
		{
			if(lastSlot == -1)
			{
				lastSlot = mc.thePlayer.inventory.currentItem;
			}

			mc.thePlayer.inventory.currentItem = slot;
			mc.playerController.updateController();
		}
	}
	
	/** Switches to originally selected hotbar slot */
	public void setCurrentItemBack()
	{
		if(lastSlot >= 0)
		{
			setSlot(lastSlot);
			lastSlot = -1;
		}
	}
	
	public Long getTimeSinceLastSwap()
	{
		return System.currentTimeMillis() - lastSwapTime;
	}
	
	public boolean canSwap()
	{
		if((swapCounter < maxSwaps || getTimeSinceLastSwap() > swapResetTime) && getTimeSinceLastSwap() > swapWaitTime)
		{
			return true;
		}
		return false;
	}
	
	public void swapItemToHotbar(ItemStack item)
	{
		swapItemToHotbar(item.getItem(),item.getItemDamage());
	}
	
	public void swapItemToHotbar(Item item, int itemMeta)
	{
		if(getTimeSinceLastSwap() > swapResetTime)
		{
			swapCounter = 0;
		}
		
		if(swapCounter < maxSwaps)
		{
			int slot = (itemMeta >= 0 ? getInventorySlotContainItemAndDamage(item,itemMeta):getInventorySlotContainItem(item));
			
			if(slot >= 0 && slot <= 8)
			{
				return;
			}
			if(mc.thePlayer.capabilities.isCreativeMode)
			{
				ItemStack itemstack = new ItemStack(item,1,itemMeta >= 0 ? itemMeta : 0);
				mc.thePlayer.sendQueue.addToSendQueue(new C10PacketCreativeInventoryAction(44 - swapCounter, itemstack));
				PlecksCore.getPlayer().inventory.setInventorySlotContents(8 - swapCounter, itemstack);
	           	swapCounter++;
	           	lastSwapTime = System.currentTimeMillis();
				
			}
			else
			{
				if(slot > 8)
				{
					int emptySlot = inventorySlotToWindowSlot(mc.thePlayer.inventory.getFirstEmptyStack());
					int windowSlot = inventorySlotToWindowSlot(slot);
					
					if(emptySlot >= 36) //Have open space on hotbar
					{
						mc.playerController.windowClick(0, windowSlot, 0, 0, mc.thePlayer); //Click the item in the inventory
						mc.playerController.windowClick(0, emptySlot, 0, 0, mc.thePlayer); //Click the empty space on the hotbar
					}
					else //Need to swap an item out from the hotbar
					{
						mc.playerController.windowClick(0, (44 - swapCounter), 0, 1, mc.thePlayer); //Shift-click item off the hotbar
						mc.playerController.windowClick(0, windowSlot, 0, 0, mc.thePlayer); //Click the item in inventory
						mc.playerController.windowClick(0, (44 - swapCounter), 0, 1, mc.thePlayer); // Click the empty space on hotbar
						swapCounter++;
						lastSwapTime = System.currentTimeMillis();
					}
				}
			}
		}
	}
	
	/*
	public ItemStack windowClick(int windowID, int windowSlot, int mouseClick, int shift, EntityPlayer player)
    {
        short transactionID = player.openContainer.getNextTransactionID(player.inventory);
        ItemStack itemstack = player.openContainer.slotClick(windowSlot, mouseClick, shift, player);
        mc.thePlayer.sendQueue.addToSendQueue(new Packet102WindowClick(windowID, windowSlot, mouseClick, shift, itemstack, transactionID));
        return itemstack;
    }
    */
	

	/** Convert from player mainInventory slotID to window slotID
	 * 	Player inventory is 0-8 for the hotbar, 9-35 is main inventory
	 * 	Window slots 0 is crafting output, 1-4 is crafting input, 5-8 is armor, 9-35 is main inventory, 36-44 is hotbar
	 */
    private static int inventorySlotToWindowSlot(int index)
    {
        if (index <= 8)
            index += 36;
        else if (index == 100)
            index = 8;
        else if (index == 101)
            index = 7;
        else if (index == 102)
            index = 6;
        else if (index == 103)
            index = 5;
        else if (index >= 80 && index <= 83)
            index -= 79;
        return index;
    }
}
