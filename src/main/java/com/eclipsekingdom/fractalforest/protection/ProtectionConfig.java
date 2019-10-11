package com.eclipsekingdom.fractalforest.protection;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class ProtectionConfig {


    private static File file = new File("plugins/FractalForest", "protection.yml");
    private static FileConfiguration config = YamlConfiguration.loadConfiguration(file);

    private static String protectionModeString = "Protection Mode";
    private static ProtectionMode protectionMode = ProtectionMode.MEDIUM;

    /*
    public ProtectionConfig() {
        load();
    }
    private void load() {
        if (file.exists()) {
            try {
                forceLoadWorld = config.getBoolean(forceLoadWorldString, forceLoadWorld);
                List<String> forcedWorlds = new ArrayList<>();
                if(config.contains(forcedWorldsString)){
                    for(String worldName: config.getConfigurationSection(forcedWorldsString).getKeys(false)){
                        forcedWorlds.add(worldName);
                    }
                }
                this.forcedWorlds = forcedWorlds;
            } catch (Exception e) {
            }
        } else {
            createDefault();
        }
        if(forceLoadWorld){
            forceLoadWorlds();
        }
    }

    private void createDefault() {
        config.set(forceLoadWorldString, forceLoadWorld);
        config.set(forcedWorldsString, forcedWorlds);
        saveConfig();
    }

    private static void saveConfig() {
        try {
            config.save(file);
        } catch (Exception e) {
            Bukkit.getConsoleSender().sendMessage(CONSOLE_FILE_ERROR.getFromFile(file.getName()));
        }
    }

    private static void forceLoadWorlds(){
        for(String worldName: forcedWorlds){
            if(Bukkit.getWorld(worldName)  == null){
                World world = Bukkit.getServer().createWorld(new WorldCreator(worldName));
                Bukkit.getServer().getWorlds().add(world);
                ConsoleSender.sendMessage(CONSOLE_WORLD_LOADED.getFromWorld(worldName));
            }
        }
    }
    */


}
