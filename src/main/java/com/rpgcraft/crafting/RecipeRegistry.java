package com.rpgcraft.crafting;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class RecipeRegistry {
	//Constants for block stacks
	public static final ItemStack DIRT_STACK = new ItemStack(Block.getBlockFromName("dirt"));
	
	//Constants for item id
	public static final int DIAMOND = 264;
	
	public static void registerRecipes(){
		//Sample recipe - remove before release!!!
		GameRegistry.addShapelessRecipe(new ItemStack(Item.getItemById(DIAMOND)), DIRT_STACK);
		
	}
}
