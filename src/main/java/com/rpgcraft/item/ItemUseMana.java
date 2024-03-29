package com.rpgcraft.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.rpgcraft.data.ExtendedPlayer;

/**
 * Part of a tutorial which I never completed the code for... Should probably do that someday.
 * @author Nicholas Wollmann
 */
public class ItemUseMana extends Item
{
	public ItemUseMana() {
		super();
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player)
	{
		if (!world.isRemote)
		{
			/*
Due to the length of code needed to get extended entity properties, I always find it handy to create a local variable named 'props' for whatever properties I need.

Also, getExtendedProperties("name") returns the type 'IExtendedEntityProperties', so you need to cast it as your extended properties type for it to work.

Old, ugly method:
ExtendedPlayer props = (ExtendedPlayer) player.getExtendedProperties(ExtendedPlayer.EXT_PROP_NAME);

This is using Seigneur_Necron's slick method (will be used from here on):
			 */
			ExtendedPlayer props = ExtendedPlayer.get(player);

			// Here we'll use the method we made to see if the player has enough mana to do something
			// We'll print something to the console for debugging, but I'm sure you'll find a much
			// better action to perform.
			if (props.consumeMana(15))
			{
				System.out.println("[MANA ITEM] Player had enough mana. Do something awesome!");
			}
			else
			{
				System.out.println("[MANA ITEM] Player ran out of mana. Sad face.");
				props.replenishMana();
			}
		}
		return itemstack;
	}
}