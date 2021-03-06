package com.eclipsekingdom.fractalforest.sys;

import com.eclipsekingdom.fractalforest.protection.CoreProtect;
import com.google.common.collect.ImmutableList;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.Map;

import static com.eclipsekingdom.fractalforest.sys.language.Message.CONSOLE_DETECT;


public class PluginBase {

    public static String worldGuardNameSpace = "WorldGuard";
    public static String coreProtectNameSpace = "CoreProtect";

    private static ImmutableList<String> possibleRegionDependencies = new ImmutableList.Builder<String>()
            .add(worldGuardNameSpace)
            .build();

    private static Map<String, Plugin> regionPlugins = new HashMap<>();
    private static CoreProtect coreProtect;
    private static boolean usingCoreProtect = false;

    public PluginBase() {
        loadDependencies();
    }

    public static Map<String, Plugin> getRegionPlugins() {
        return regionPlugins;
    }

    public void loadDependencies() {
        loadRegionDependencies();
        loadCoreProtect();
    }

    private void loadRegionDependencies() {
        for (String nameSpace : possibleRegionDependencies) {
            Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin(nameSpace);
            if (plugin != null && plugin.isEnabled()) {
                regionPlugins.put(nameSpace, plugin);
                ConsoleSender.sendMessage(CONSOLE_DETECT.fromPlugin(nameSpace));
            }
        }
    }

    private void loadCoreProtect() {
        Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin(coreProtectNameSpace);
        if (plugin != null && plugin.isEnabled()) {
            coreProtect = new CoreProtect(plugin);
            usingCoreProtect = coreProtect.isEnabled();
            if (usingCoreProtect) {
                ConsoleSender.sendMessage(CONSOLE_DETECT.fromPlugin(coreProtectNameSpace));
            }
        }
    }

    public static CoreProtect getCoreProtect() {
        return coreProtect;
    }

    public static boolean isUsingCoreProtect() {
        return usingCoreProtect;
    }
}
