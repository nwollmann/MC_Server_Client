package com.rpgcraft.tiles;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;

import com.rpgcraft.RPGCraft;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FreezerBlock extends Block {

	public FreezerBlock() {
		super(Material.circuits);
		
		setHardness(4.0F);
		setResistance(10);
	}

	public TileEntity createNewTileEntity(World var1, int var2){
		 
		
		return new TileEntityFreezer();
	}
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ){
		
		/*if(world.isRemote || player.isSneaking()){
			return false;
		}*/
		
		if(world.isRemote){
			Minecraft.getMinecraft().thePlayer.sendChatMessage("/say Hello There");
			player.addChatMessage(new ChatComponentTranslation("msg.click.txt"));
		
			return false;
		}else{
			//FMLNetworkHandler.openGui(player, RPGCraft.instance, RPGCraft.GUI_FREEZER, world, x, y, z);
			return true;
		}
	}
}
