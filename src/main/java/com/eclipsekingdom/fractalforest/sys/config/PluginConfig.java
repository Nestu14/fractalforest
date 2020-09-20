package com.eclipsekingdom.fractalforest.sys.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class PluginConfig {

    private static File file = new File("plugins/FractalForest", "config.yml");
    private static FileConfiguration config = YamlConfiguration.loadConfiguration(file);

    private static String languageFileString = "Language file";
    private static String languageFile = "en";

    private static String phasePeriodString = "Phase Period (seconds)";
    private static int phasePeriod = 1;

    private static String requireSaplingPermString = "Require Sapling Permissions";
    private static boolean requireSaplingPerm = false;

    public PluginConfig() {
        load();
    }

    private void load() {
        if (file.exists()) {
            try {
                languageFile = config.getString(languageFileString, languageFile);
                phasePeriod = config.getInt(phasePeriodString, phasePeriod);
                requireSaplingPerm = config.getBoolean(requireSaplingPermString, requireSaplingPerm);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static String getLanguageFile() {
        return languageFile;
    }

    public static int getPhasePeriod() {
        return phasePeriod * 20;
    }

    public static boolean isRequireSaplingPerm() {
        return requireSaplingPerm;
    }


}
