package com.rpgcraft.gui;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.rpgcraft.item.ItemStore;

public class SlotItemInv extends Slot{
	public SlotItemInv(IInventory inventory, int par2, int par3, int par4){
		super(inventory, par2, par3, par4);
	}
	
	@Override
	public boolean isItemValid(ItemStack itemstack){
		return !(itemstack.getItem() instanceof ItemStore);
	}
}
