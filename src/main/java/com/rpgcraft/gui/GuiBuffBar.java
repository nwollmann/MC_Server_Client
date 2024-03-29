package com.rpgcraft.gui;

import java.util.Collection;
import java.util.Iterator;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;

import org.lwjgl.opengl.GL11;

import com.rpgcraft.RPGCraft;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

/**
 * Draws a buff bar in the upper-left corner of the screen. TO BE REMOVED.
 * @author Nicholas Wollmann
 */
public class GuiBuffBar extends Gui{
	public static ResourceLocation texture = new ResourceLocation(RPGCraft.MODID.toLowerCase(), "textures/gui/skillGui.png");
	
	private Minecraft mc;
	
	/**
	 * Standard constructor.
	 * @param mc The minecraft instance.
	 */
	public GuiBuffBar(Minecraft mc){
		super();
		
		//for render engine!
		this.mc = mc;
	}
	
	private static final int BUFF_ICON_SIZE = 18;
	private static final int BUFF_ICON_SPACING = BUFF_ICON_SIZE + 2;
	private static final int BUFF_ICON_BASE_U_OFFSET = 0;
	private static final int BUFF_ICON_BASE_V_OFFSET = 198;
	private static final int BUFF_ICONS_PER_ROW = 8;
	
	//called by GuiIngameForge each frame
	/**
	 * Triggers every time the experience bar is refreshed. Called by the engine.
	 */
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void onRenderExperienceBar(RenderGameOverlayEvent event){
		if(event.isCancelable() || event.type != ElementType.EXPERIENCE) return;
		
		int xPos = 2; //buff bar starting possition
		int yPos = 2;
		Collection collection = this.mc.thePlayer.getActivePotionEffects();
		if(!collection.isEmpty()){
			GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
			GL11.glDisable(GL11.GL_LIGHTING);
			this.mc.getTextureManager().bindTexture(texture);
			for(Iterator iterator = this.mc.thePlayer.getActivePotionEffects().iterator(); iterator.hasNext(); xPos += BUFF_ICON_SPACING){
				PotionEffect potioneffect = (PotionEffect) iterator.next();
				Potion potion = Potion.potionTypes[potioneffect.getPotionID()];
				
				if(potion.hasStatusIcon()){
					int iconIndex = potion.getStatusIconIndex();
					this.drawTexturedModalRect(xPos, yPos,
							BUFF_ICON_BASE_U_OFFSET + iconIndex % BUFF_ICONS_PER_ROW * BUFF_ICON_SIZE, BUFF_ICON_BASE_V_OFFSET + iconIndex / BUFF_ICONS_PER_ROW * BUFF_ICON_SIZE,
							BUFF_ICON_SIZE, BUFF_ICON_SIZE);
				}
			}
		}
	}
}
