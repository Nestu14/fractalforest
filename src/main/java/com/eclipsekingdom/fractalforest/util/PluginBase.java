package com.eclipsekingdom.fractalforest.util;

import com.eclipsekingdom.fractalforest.protection.CoreProtect;
import com.google.common.collect.ImmutableList;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.Map;

import static com.eclipsekingdom.fractalforest.util.language.Message.CONSOLE_DETECT;


public class PluginBase {

    public static String playerPlotNameSpace = "PlayerPlot";
    public static String worldGuardNameSpace = "WorldGuard";
    public static String redProtectNameSpace = "RedProtect";
    public static String coreProtectNameSpace = "CoreProtect";
    public static String townyNameSpace = "Towny";
    public static String residenceNameSpace = "Residence";
    public static String griefPreventionNameSpace = "GriefPrevention";
    public static String landsNameSpace = "Lands";
    public static String civsNameSpace = "Civs";

    private static ImmutableList<String> possibleRegionDependencies = new ImmutableList.Builder<String>()
            .add(playerPlotNameSpace)
            .add(worldGuardNameSpace)
            .add(redProtectNameSpace)
            .add(townyNameSpace)
            .add(griefPreventionNameSpace)
            .add(residenceNameSpace)
            .add(landsNameSpace)
            .add(civsNameSpace)
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
                ConsoleSender.sendMessage(CONSOLE_DETECT.getFromPlugin(nameSpace));
            }
        }
    }

    private void loadCoreProtect() {
        Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin(coreProtectNameSpace);
        if (plugin != null && plugin.isEnabled()) {
            coreProtect = new CoreProtect(plugin);
            usingCoreProtect = coreProtect.isEnabled();
            if (usingCoreProtect) {
                ConsoleSender.sendMessage(CONSOLE_DETECT.getFromPlugin(coreProtectNameSpace));
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
