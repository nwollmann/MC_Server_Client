package com.rpgcraft;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;

import com.rpgcraft.api.HUDRegistry;
import com.rpgcraft.api.HudItem;

import net.minecraft.client.Minecraft;
import net.minecraft.crash.CrashReport;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ReportedException;

/**
 * Handles saving configuration for the player on the client side. Currently used for the HUD configuration.
 * @author Nicholas Wollmann
 */
public class SaveController {
    protected static final String dirName = Minecraft.getMinecraft().mcDataDir + File.separator + "config" + File.separator + "AdvancedHud";
    protected static File dir = new File(dirName);

    /**
     * Loads the configuration for a given player.
     * @param name The name of the player
     * @return Whether the configuration was properly loaded.
     */
    public static boolean loadConfig(String name) {
        return loadConfig(name, null);
    }

    /**
     * Loads the configuration for the given settings.
     * @param name The player name.
     * @param dirName The saving directory.
     * @return Whether the configuration was loaded properly.
     */
    public static boolean loadConfig(String name, String dirName) {
        if (dirName != null) {
            HUDRegistry.getMinecraftInstance();
            dir = new File(Minecraft.getMinecraft().mcDataDir + File.separator + dirName);
        }

        String fileName = name + ".dat";
        File file = new File(dir, fileName);

        if (!file.exists()) {
            RPGCraft.log.warn("Config load canceled, file does not exist. This is normal for first run.");
            return false;
        } else {
            RPGCraft.log.info("Config load successful.");
        }
        try {
            NBTTagCompound nbt = CompressedStreamTools.readCompressed(new FileInputStream(file));

            HUDRegistry.readFromNBT(nbt.getCompoundTag("global"));

            for (HudItem item : HUDRegistry.getHudItemList()) {
                NBTTagCompound itemNBT = nbt.getCompoundTag(item.getName());
                item.loadFromNBT(itemNBT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * Saves the configuration under the given player name.
     * @param name The player name.
     */
    public static void saveConfig(String name) {
        saveConfig(name, null);
    }

    /**
     * Saves the configuration in the given location under the given player name.
     * @param name The player name.
     * @param dirName The saving directory.
     */
    public static void saveConfig(String name, String dirName) {
        RPGCraft.log.info("Saving...");

        if (dirName != null) {
            HUDRegistry.getMinecraftInstance();
            dir = new File(Minecraft.getMinecraft().mcDataDir + File.separator + dirName);
        }

        if (!dir.exists() && !dir.mkdirs())
            throw new ReportedException(new CrashReport("Unable to create the configuration directories", new Throwable()));

        String fileName = name + ".dat";
        File file = new File(dir, fileName);

        try {
            NBTTagCompound nbt = new NBTTagCompound();
            FileOutputStream fileOutputStream = new FileOutputStream(file);

            NBTTagCompound globalNBT = new NBTTagCompound();
            HUDRegistry.writeToNBT(globalNBT);
            nbt.setTag("global", globalNBT);

            for (HudItem item : HUDRegistry.getHudItemList()) {
                NBTTagCompound itemNBT = new NBTTagCompound();
                item.saveToNBT(itemNBT);
                nbt.setTag(item.getName(), itemNBT);
            }

            CompressedStreamTools.writeCompressed(nbt, fileOutputStream);
            fileOutputStream.close();
        } catch (IOException e) {
            throw new ReportedException(new CrashReport("An error occured while saving", new Throwable()));
        }
    }

    /**
     * Gets the configurations saved in the directory.
     * @return The files in that directory.
     */
    public static File[] getConfigs() {
        return dir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String filename) {
                return filename.endsWith(".dat");
            }
        });
    }

    static {
        HUDRegistry.getMinecraftInstance();
    }
}