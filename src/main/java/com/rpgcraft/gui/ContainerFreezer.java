package com.rpgcraft.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

import com.rpgcraft.tiles.TileEntityFreezer;
import com.rpgcraft.tiles.TileEntitySkillBlock;

/**
 * More tutorial code that will be soon outdated. YAY!!!
 * @author Nicholas Wollmann
 */
public class ContainerFreezer extends Container {
	
	public TileEntityFreezer freezer;
	
	public ContainerFreezer(InventoryPlayer player, TileEntityFreezer freezer){
		
		this.freezer = freezer;
		
		/*
		for(int i = 0; i < 9; i++){
			
			addSlotToContainer(new Slot(player, i, 7 + i * 18, 163));
			
		}
		
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 9; j++){
				addSlotToContainer(new Slot(player, 9 + j + i * 9, 8 + j * 18, 104 + i * 18));
			}
		} */
		
	}
	public ContainerFreezer(InventoryPlayer inventory) {
		// TODO Auto-generated constructor stub
		
		this.freezer = null;
	}

	@Override
	public boolean canInteractWith(EntityPlayer var1) {
		// TODO Auto-generated method stub
		return true;
	}

}
