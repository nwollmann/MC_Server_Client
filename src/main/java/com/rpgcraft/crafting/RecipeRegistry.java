package com.rpgcraft.crafting;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Registers custom recipes into the game engine.
 * @author Nicholas Wollmann
 */
public class RecipeRegistry {
	//Constants for block stacks
	public static final ItemStack DIRT_STACK = new ItemStack(Block.getBlockFromName("dirt"));
	
	//Constants for item id
	public static final int DIAMOND = 264;
	
	/**
	 * Called by the core mod's init. Registers custom recipes.
	 */
	public static void registerRecipes(){
		//Sample recipe - remove before release!!!
		GameRegistry.addShapelessRecipe(new ItemStack(Item.getItemById(DIAMOND)), DIRT_STACK);
		
	}
}
