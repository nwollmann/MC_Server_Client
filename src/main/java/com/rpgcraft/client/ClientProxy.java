package com.rpgcraft.client;

import com.rpgcraft.CommonProxy;

import cpw.mods.fml.common.FMLCommonHandler;

public class ClientProxy extends CommonProxy{
	@Override
	public void registerRenderers(){
		//actually register renderers here to draw custom mobs and such
		FMLCommonHandler.instance().bus().register(new KeyHandler());
		//FMLCommonHandler.instance().bus().register(new HUDOverlay(Minecraft.getMinecraft()));
	}
}
