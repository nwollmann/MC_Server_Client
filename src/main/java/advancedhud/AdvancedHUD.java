package advancedhud;

import org.apache.logging.log4j.Logger;

import com.rpgcraft.gui.hud.items.HudItemAir;
import com.rpgcraft.gui.hud.items.HudItemArmor;
import com.rpgcraft.gui.hud.items.HudItemBossBar;
import com.rpgcraft.gui.hud.items.HudItemCrosshairs;
import com.rpgcraft.gui.hud.items.HudItemExperienceBar;
import com.rpgcraft.gui.hud.items.HudItemFood;
import com.rpgcraft.gui.hud.items.HudItemHealth;
import com.rpgcraft.gui.hud.items.HudItemHealthMount;
import com.rpgcraft.gui.hud.items.HudItemHotbar;
import com.rpgcraft.gui.hud.items.HudItemJumpBar;
import com.rpgcraft.gui.hud.items.HudItemRecordDisplay;
import com.rpgcraft.gui.hud.items.HudItemTooltips;

import advancedhud.api.HUDRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "AdvancedHUD", name = "AdvancedHUD", version = "Version [@VERSION@] for @MCVERSION@")
public class AdvancedHUD {

    public static String MC_VERSION = "@MCVERSION@";
    public static String ADVHUD_VERSION = "@VERSION@";
    public static Logger log;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        log = event.getModLog();
    }
    @EventHandler
    public void onInit(FMLInitializationEvent event) {
        FMLCommonHandler.instance().bus().register(new TickHandler());
        FMLCommonHandler.instance().bus().register(new KeyRegister());

        registerHUDItems();
    }

    private void registerHUDItems() {
        HUDRegistry.registerHudItem(new HudItemHotbar());
        HUDRegistry.registerHudItem(new HudItemHealth());
        HUDRegistry.registerHudItem(new HudItemAir());
        HUDRegistry.registerHudItem(new HudItemFood());
        HUDRegistry.registerHudItem(new HudItemArmor());
        HUDRegistry.registerHudItem(new HudItemBossBar());
        HUDRegistry.registerHudItem(new HudItemJumpBar());
        HUDRegistry.registerHudItem(new HudItemHealthMount());
        HUDRegistry.registerHudItem(new HudItemExperienceBar());
        HUDRegistry.registerHudItem(new HudItemCrosshairs());
        HUDRegistry.registerHudItem(new HudItemTooltips());
        HUDRegistry.registerHudItem(new HudItemRecordDisplay());
        HUDRegistry.setInitialLoadComplete(true);
    }
}
