package com.rpgcraft.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import com.rpgcraft.RPGCraft;
import com.rpgcraft.tiles.TileEntityFreezer;
import com.sun.prism.paint.Color;

/**
 * The popup window for class selection - TO BE REFRACTORED.
 * @author Nicholas Wollmann
 */
public class SkillGui extends GuiContainer{
	
	public static ResourceLocation texture = new ResourceLocation(RPGCraft.MODID.toLowerCase(), "textures/gui/skillGui.png");
	
	/**
	 * Initializes the GUI.
	 */
	@Override
	public void initGui(){
		super.initGui();
		
		
		
		guiLeft -= (256 - xSize)/2;
		guiTop -= (256 - ySize)/2;
		xSize = 256;
		ySize = 256;
		
		//guiTop = guiTop - (190 - ySize)/2;
		
		//id, x, y, width, height, text
		this.buttonList.add(new GuiButton(1, 5 + guiLeft, 225 + guiTop, 120, 20, "Switch"));
		this.buttonList.add(new GuiButton(2, 129 + guiLeft, 225 + guiTop, 120, 20, "Close"));
	}

	/**
	 * Creates an instance of the view.
	 * @param player
	 * @param freezer
	 */
	public SkillGui(InventoryPlayer player, TileEntityFreezer freezer) {
		super(new ContainerFreezer(player, freezer));
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Creates an instance of the view.
	 * @param inventory
	 */
	public SkillGui(InventoryPlayer inventory) {
		super(new ContainerFreezer(inventory));
		// TODO Auto-generated constructor stub
	}

	/**
	 * Triggered by a button click in the view.
	 */
	@Override
	protected void actionPerformed(GuiButton button){
		switch(button.id){
		case 1:
			
			break;
		case 2:
			Minecraft.getMinecraft().displayGuiScreen((GuiScreen)null);
			
		}
	}
	
	/**
	 * Draws the GUI container's foreground.
	 */
	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2){
		this.drawRect(10, 9, 246, 45, 0xFF00FF00);
		
		this.drawRect(10, 53, 224, 89, 0xFF888800);
		this.drawRect(10, 96, 224, 132, 0xFF888800);
		this.drawRect(10, 139, 224, 175, 0xFF888800);
		this.drawRect(10, 182, 224, 218, 0xFF888800);
	}

	/**
	 * Draws the TUI container's background.
	 */
	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2,
			int var3) {
		// TODO Auto-generated method stub
		try{
			//this.drawRect(guiLeft + 6, guiTop + 5, guiLeft + 184, guiTop + 35, 0xFF0000);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		
		
		//the top box!
		//this.drawRect(guiLeft + 6, guiTop + 5, guiLeft + 184, guiTop + 35, 0xFF0000);
	}
	
}
