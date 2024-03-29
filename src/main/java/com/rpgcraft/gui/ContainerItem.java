package com.rpgcraft.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.rpgcraft.item.InventoryItem;

/**
 * Written as part of a tutorial. To be removed from the project before final version.
 * @author Nicholas Wollmann
 */
public class ContainerItem extends Container{
	final InventoryItem inventory;
	
	private static final int INV_START = InventoryItem.INV_SIZE;
	private static final int INV_END = INV_START + 26;
	private static final int HOTBAR_START = INV_END + 1;
	private static final int HOTBAR_END = HOTBAR_START + 8;
	
	public ContainerItem(EntityPlayer player, InventoryPlayer inventory, InventoryItem item){
		this.inventory = item;
		
		int i;
		
		for(i = 0; i < InventoryItem.INV_SIZE; i++)
			this.addSlotToContainer(new SlotItemInv(this.inventory, i, 80 + (18 * (int)(i/4)), 8 + (18 * (i % 4))));
		
		for(i = 0; i < 3; ++i){
			for(int j = 0; j < 9; j++){
				this.addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}
		
		for(i = 0; i < 9; ++i)
		{
			this.addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 142));
		}
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer player){
		return inventory.isUseableByPlayer(player);
	}
	
	public ItemStack transferStackInSlot(EntityPlayer player, int index){
		ItemStack itemstack = null;
		Slot slot = (Slot) this.inventorySlots.get(index);
		if(slot != null && slot.getHasStack()){
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			
			if(index < INV_START){
				if(!this.mergeItemStack(itemstack1, INV_START, HOTBAR_END + 1, true)) return null;
				slot.onSlotChange(itemstack1, itemstack);
			}else{
				if(index >= INV_START && index < HOTBAR_START){
					if(!this.mergeItemStack(itemstack1, HOTBAR_START, HOTBAR_END + 1, false)) return null;
				}else if(index >= HOTBAR_START && index < HOTBAR_END + 1){
					if(!this.mergeItemStack(itemstack1, INV_START, INV_END + 1, false)) return null;
				}
			}
			
			if(itemstack1.stackSize == 0) slot.putStack(null);
			else slot.onSlotChanged();
			
			if(itemstack1.stackSize == itemstack.stackSize) return null;
			
			slot.onPickupFromSlot(player, itemstack1);
		}
		return itemstack;
	}
	
	@Override
	public ItemStack slotClick(int slot, int button, int flag, EntityPlayer player){
		if(slot >= 0 && getSlot(slot) != null && getSlot(slot).getStack() == player.getHeldItem()) return null;
		return super.slotClick(slot, button, flag, player);
	}
	
}
