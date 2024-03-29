package com.rpgcraft.tiles;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import com.rpgcraft.RPGCraft;

public class GenericOre extends Block{

	public GenericOre(Material material){
		super(material);
		setHardness(4.0F); //33% harder than diamond
		setStepSound(Block.soundTypeStone);
		setBlockName("genericOre");
		setHarvestLevel("pickaxe", 3);
		
		setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public Item getItemDropped(int metadata, Random random, int fortune){
		//make it an item
		return RPGCraft.genericIngot;
		
		//make it a block
		//return Item.getItemFromBlock(Generic.genericBlock);
	}
	
}
