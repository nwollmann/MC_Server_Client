package com.rpgcraft.gui;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.rpgcraft.item.ItemStore;

/**
 * Acts as a single item slot inside of an inventory.
 * @author Nicholas Wollmann
 */
public class SlotItemInv extends Slot{
	/**
	 * Standard constructor - calls super.
	 */
	public SlotItemInv(IInventory inventory, int par2, int par3, int par4){
		super(inventory, par2, par3, par4);
	}
	
	/**
	 * Stops the backpack item from being placed inside another backpack.
	 */
	@Override
	public boolean isItemValid(ItemStack itemstack){
		return !(itemstack.getItem() instanceof ItemStore);
	}
}
