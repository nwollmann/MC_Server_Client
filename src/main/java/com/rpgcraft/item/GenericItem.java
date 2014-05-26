package com.rpgcraft.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * A generic item with no purpose but to prove I can make items.
 * @author Nicholas Wollmann
 */
public class GenericItem extends Item {
	
	/**
	 * Sets up an instance of the item with its default properties.
	 */
	public GenericItem(){
		setMaxStackSize(64);
		setCreativeTab(CreativeTabs.tabMisc);
		setUnlocalizedName("genericItem");
	}
	
}
