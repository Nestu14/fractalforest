package com.eclipsekingdom.fractalforest.util.config;

import com.eclipsekingdom.fractalforest.util.system.ConsoleSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class PluginConfig {

    private static File file = new File("plugins/FractalForest", "config.yml");
    private static FileConfiguration config = YamlConfiguration.loadConfiguration(file);

    private static String phasePeriodString = "Phase Period (seconds)";
    private static int phasePeriod = 1;

    private static String requireSaplingPermString = "Require Sapling Permissions";
    private static boolean requireSaplingPerm = false;

    private static String proceduralTreeLeafDecayString = "Procedural Tree Leaf Decay";
    private static boolean proceduralTreeLeafDecay = false;


    public PluginConfig() {
        load();
    }

    private void load() {
        if (file.exists()) {
            try {
                phasePeriod = config.getInt(phasePeriodString, phasePeriod);
                requireSaplingPerm = config.getBoolean(requireSaplingPermString, requireSaplingPerm);
                //proceduralTreeLeafDecay = config.getBoolean(proceduralTreeLeafDecayString, proceduralTreeLeafDecay);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            createDefault();
        }
    }

    private void createDefault() {
        config.set(phasePeriodString, phasePeriod);
        config.set(requireSaplingPermString, requireSaplingPerm);
        //config.set(proceduralTreeLeafDecayString, proceduralTreeLeafDecay);
        saveConfig();
    }

    private static void saveConfig() {
        try {
            config.save(file);
        } catch (Exception e) {
            ConsoleSender.sendMessage("Error saving " + file.getName());
        }
    }

    public static int getPhasePeriod() {
        return phasePeriod * 20;
    }

    public static boolean isRequreSaplingPerm() {
        return requireSaplingPerm;
    }

    public static boolean isProceduralTreeLeafDecay() {
        return proceduralTreeLeafDecay;
    }


}
