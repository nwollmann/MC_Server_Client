package com.rpgcraft.client;

import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;

import org.lwjgl.input.Keyboard;

import com.rpgcraft.RPGCraft;
import com.rpgcraft.data.ExtendedPlayer;
import com.rpgcraft.packets.OpenGuiPacket;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;


public class KeyHandler {
	public static final int CUSTOM_INV = 0;
	
	public static final String[] desc = {"key.tut_inventory.desc"};
	public static final int[] keyValues = {Keyboard.KEY_P}; //can be changed via menu
	
	public final KeyBinding[] keys;
	
	public KeyHandler(){
		keys = new KeyBinding[desc.length];
		for(int i = 0; i < desc.length; i++){
			keys[i] = new KeyBinding(desc[i], keyValues[i], "key.tutorial.category");
			ClientRegistry.registerKeyBinding(keys[i]);
		}
	}
	
	@SubscribeEvent
	public void onKeyInput(KeyInputEvent event){
		if(!FMLClientHandler.instance().isGUIOpen(GuiChat.class));{
			int kb = Keyboard.getEventKey();
			boolean isDown = Keyboard.getEventKeyState();
			
			if(kb == keys[CUSTOM_INV].getKeyCode()){
				EntityPlayer player = FMLClientHandler.instance().getClient().thePlayer;
				//RPGCraft.packetPipeline.sendToServer(new OpenGuiPacket(RPGCraft.GUI_ITEM_INV));
				//player.setHealth(50);
				double valueToSet = 2.0;
				try{
					valueToSet = ((ExtendedPlayer) player.getExtendedProperties(ExtendedPlayer.EXT_PROP_NAME)).getMaxHealth() + 2.0;
					ExtendedPlayer exPlayer = (ExtendedPlayer) player.getExtendedProperties(ExtendedPlayer.EXT_PROP_NAME);
					exPlayer.setMaxHealth((int)valueToSet);
				}catch(Exception e){ System.out.println("PROBLEM"); e.printStackTrace();}
				
				//ExtendedPlayer exPlayer = (ExtendedPlayer) player.getExtendedProperties(ExtendedPlayer.EXT_PROP_NAME);
				//exPlayer.setMaxHealth((int)valueToSet);
				
			}
		}
	}
}
