package com.rpgcraft.gui;

import com.rpgcraft.tiles.TileEntityFreezer;

import com.rpgcraft.RPGCraft;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiFreezer extends GuiContainer{
	
	public static ResourceLocation texture = new ResourceLocation(RPGCraft.MODID.toLowerCase(), "textures/gui/guiFreezer.png");

	public GuiFreezer(InventoryPlayer player, TileEntityFreezer freezer) {
		super(new ContainerFreezer(player, freezer));
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2,
			int var3) {
		// TODO Auto-generated method stub
		
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
	}
	
}
