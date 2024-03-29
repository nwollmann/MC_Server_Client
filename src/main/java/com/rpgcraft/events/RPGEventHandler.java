package com.rpgcraft.events;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.living.LivingFallEvent;

import com.rpgcraft.data.ExtendedPlayer;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;

/**
 * A generic event handler for anything our mod might have as a trigger. Will likely get refractored into multiple handlers as the project grows.
 * @author Nicholas Wollmann
 */
public class RPGEventHandler
{
	/**
	 * Called by engine on entity construction.
	 */
	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing event){
		/*
Be sure to check if the entity being constructed is the correct type for the extended properties you're about to add! The null check may not be necessary - I only use it to make sure properties are only registered once per entity
		 */
		System.out.println("CONSTRUCTION CALLED");
		if (event.entity instanceof EntityPlayer && ExtendedPlayer.get((EntityPlayer) event.entity) == null)
		{
			// This is how extended properties are registered using our convenient method from earlier
			ExtendedPlayer.register((EntityPlayer) event.entity);
			((EntityPlayer) event.entity).getAttributeMap().getAttributeInstance(SharedMonsterAttributes.maxHealth).setBaseValue(40);
		}
		// That will call the constructor as well as cause the init() method
		// to be called automatically

		// If you didn't make the two convenient methods from earlier, your code would be
		// much uglier:
		if (event.entity instanceof EntityPlayer && event.entity.getExtendedProperties(ExtendedPlayer.EXT_PROP_NAME) == null)
			event.entity.registerExtendedProperties(ExtendedPlayer.EXT_PROP_NAME, new ExtendedPlayer((EntityPlayer) event.entity));
	}

	
	/**
	 * Called by engine when a player logs in.
	 */
	@SubscribeEvent
	public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event){
		event.player.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.maxHealth).setBaseValue(40);
		System.out.println("LOGGED IN");
		event.player.setHealth(35);
	}

	/**
	 * Called by engine when some creature falls some blocks.
	 */
	@SubscribeEvent
	public void onLivingFallEvent(LivingFallEvent event)
	{
		// Remember that so far we have only added ExtendedPlayer properties
		// so check if it's the right kind of entity first
		if (event.entity instanceof EntityPlayer)
		{
			ExtendedPlayer props = ExtendedPlayer.get((EntityPlayer) event.entity);

			// This 'if' statement just saves a little processing time and
			// makes it so we only deplete mana from a fall that would injure the player
			if (event.distance > 3.0F && props.getCurrentMana() > 0)
			{
				// Some debugging statements so you can see what's happening
				System.out.println("[EVENT] Fall distance: " + event.distance);
				System.out.println("[EVENT] Current mana: " + props.getCurrentMana());

				/*
	We need to make a local variable to store the amount to reduce both the distance and mana, otherwise when we reduce one, we have no way to tell by how much to reduce the other

	Alternatively, you could just try to consumeMana for the amount of the fall distance and, if it returns true, set the fall distance to 0, but today we're going for a cushioning effect instead.

	If you want mana to be used efficiently, you would only reduce the fall distance by enough to reduce it to 3.0F (3 blocks), thus ensuring the player will take no damage while minimizing mana consumed.

	Be sure you put (event.distance - 3.0F) in parentheses or you'll have a nasty bug with your mana! It has to do with the way "x < y ? a : b" parses parameters.
				 */
				float reduceby = props.getCurrentMana() < (event.distance - 3.0F) ? props.getCurrentMana() : (event.distance - 3.0F);
				event.distance -= reduceby;

				// Cast reduceby to 'int' to match our method parameter
				props.consumeMana((int) reduceby);

				System.out.println("[EVENT] Adjusted fall distance: " + event.distance);
			}
		}
	}
}