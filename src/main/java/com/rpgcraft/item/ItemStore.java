package com.rpgcraft.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.rpgcraft.RPGCraft;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Item which opens up an inventory when you right click with it.
 * @author Nicholas Wollmann
 *
 */
public class ItemStore extends Item{
	
	/**
	 * Standard constructor.
	 */
	public ItemStore(){
		super();
		setMaxStackSize(1);
	}
	
	@Override
	public int getMaxItemUseDuration(ItemStack stack){
		return 1;
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player){
		if(!world.isRemote && !player.isSneaking()){
			player.openGui(RPGCraft.instance, RPGCraft.GUI_ITEM_INV, player.worldObj, (int) player.posX, (int) player.posY, (int) player.posZ);
		}
		return stack;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister){
		itemIcon = iconRegister.registerIcon("rpgcraft:" + getUnlocalizedName());
	}
	
}
