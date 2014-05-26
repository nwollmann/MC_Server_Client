package com.rpgcraft.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

import com.rpgcraft.RPGCraft;
import com.rpgcraft.api.HUDRegistry;
import com.rpgcraft.api.HudItem;
import com.rpgcraft.gui.hud.items.HudItemCrosshairs;

/**
 * Acts as parent class to all of the HUD Items. Sourced from the Advanced HUD mod.
 * @author Nicholas Wollmann
 */
public class GuiScreenHudItem extends GuiScreen {

    private HudItem hudItem;
    private GuiScreen parentScreen;

    /**
     * Standard constructor. Nothing to see here...
     */
    public GuiScreenHudItem(GuiScreen parentScreen, HudItem hudItem) {
        this.hudItem = hudItem;
        this.parentScreen = parentScreen;
    }

    /**
     * The GUI Initialization.
     */
    @SuppressWarnings("unchecked")
    @Override
    public void initGui() {
        buttonList.clear();
        buttonList.add(new GuiButton(-1, HUDRegistry.screenWidth - 30, 10, 20, 20, "X"));
        if (hudItem.canRotate()) {
            buttonList.add(new GuiButton(100, HUDRegistry.screenWidth / 2 - 50, HUDRegistry.screenHeight / 2 - 10, 100, 20, "Rotate?"));
        } 
        if (hudItem instanceof HudItemCrosshairs) {
            buttonList.add(new GuiButtonIconGrid(3320, HUDRegistry.screenWidth / 2 - 64, 40, "Crosshair Selector"));
        }
    }

    /**
     * WRITE BETTER DOCUMENTATION SOMEDAY.
     */
    @Override
    public void drawScreen(int par1, int par2, float par3) {
        this.drawDefaultBackground();

        if (!HUDRegistry.checkForResize()) {
            initGui();
        }

        this.drawCenteredString(mc.fontRenderer, hudItem.getButtonLabel(), HUDRegistry.screenWidth / 2, 10, 0xFFFFFF);
        super.drawScreen(par1, par2, par3);
    }

    /**
     * WRITE BETTER DOCUMENTATION SOMEDAY.
     */
    @Override
    protected void keyTyped(char keyChar, int keyCode) {
        if (keyCode == 1) {
            mc.displayGuiScreen(parentScreen);
        }
    }

    /**
     * WRITE BETTER DOCUMENTATION SOMEDAY.
     */
    @Override
    protected void actionPerformed(GuiButton par1GuiButton) {
        if (par1GuiButton.id == -1) {
            mc.displayGuiScreen(parentScreen);
        } else if (par1GuiButton.id == 100) {
            hudItem.rotate();
        }
        RPGCraft.log.info("Clicked button " + par1GuiButton.id);
        super.actionPerformed(par1GuiButton);
    }

}
