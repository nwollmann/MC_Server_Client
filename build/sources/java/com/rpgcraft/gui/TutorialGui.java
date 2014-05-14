package com.rpgcraft.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.rpgcraft.RPGCraft;
import com.rpgcraft.tiles.TileEntityFreezer;

import cpw.mods.fml.common.network.IGuiHandler;

public class TutorialGui implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		
		switch(ID){
		case RPGCraft.GUI_FREEZER:
			//if(tileEntity instanceof TileEntityFreezer){
				
				return new ContainerFreezer(player.inventory,(TileEntityFreezer) tileEntity);
			//}
		}
		
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		// TODO Auto-generated method stub
		
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		
		switch(ID){
		case RPGCraft.GUI_FREEZER:
			//if(tileEntity instanceof TileEntityFreezer){
				
				return new GuiFreezer(player.inventory,(TileEntityFreezer) tileEntity);
			//}
		}
		
		return null;
	}

}
