/**
 * Client side utilities which are not needed by the server.
 */
package com.rpgcraft.client;

import com.rpgcraft.CommonProxy;

import cpw.mods.fml.common.FMLCommonHandler;

/**
 * The client side proxy - handles calls which the client needs but the server does not.
 * @author Nicholas Wollmann
 */
public class ClientProxy extends CommonProxy{
	@Override
	/**
	 * Registers the renderers. For now this just handles registering the KeyHandler.
	 */
	public void registerRenderers(){
		//actually register renderers here to draw custom mobs and such
		FMLCommonHandler.instance().bus().register(new KeyHandler());
		//FMLCommonHandler.instance().bus().register(new HUDOverlay(Minecraft.getMinecraft()));
	}
}
