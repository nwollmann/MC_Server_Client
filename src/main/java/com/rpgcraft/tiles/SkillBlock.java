package com.rpgcraft.tiles;

import com.rpgcraft.RPGCraft;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;

public class SkillBlock extends BlockContainer {

	public SkillBlock(){
		super(Material.circuits);
		
		setHardness(4.0F);
		setResistance(10);
	}
	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		// TODO Auto-generated method stub
		return new TileEntitySkillBlock();
	}
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ){
		
		if(world.isRemote){
		
			return false;
		}else{
			//update all the classes down to change over from the old structure
			FMLNetworkHandler.openGui(player, RPGCraft.instance, RPGCraft.GUI_SKILL, world, x, y, z);
			
			return true;
		}
	}
}
