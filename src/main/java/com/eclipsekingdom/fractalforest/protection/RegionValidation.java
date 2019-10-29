package com.eclipsekingdom.fractalforest.protection;

import com.eclipsekingdom.fractalforest.util.PluginBase;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RegionValidation {

    private static List<IRegionProtector> regionProtectors = new ArrayList<>();

    public RegionValidation(PluginBase pluginBase) {
        loadProtections(pluginBase);
    }

    private void loadProtections(PluginBase pluginBase) {
        Map<String, Plugin> map = pluginBase.getRegionPlugins();
        for (String name : map.keySet()) {
            Plugin plugin = map.get(name);
            if (name.equals(PluginBase.playerPlotNameSpace)) {
                regionProtectors.add(new PlayerPlotProtection(plugin));
            } else if (name.equals(PluginBase.redProtectNameSpace)) {
                regionProtectors.add(new RedProtectProtection(plugin));
            } else if (name.equals(PluginBase.worldGuardNameSpace)) {
                regionProtectors.add(new WorldGuardProtection(plugin));
            } else if (name.equals(PluginBase.townyNameSpace)) {
                regionProtectors.add(new TownyProtection(plugin));
            } else if (name.equals(PluginBase.griefPreventionNameSpace)) {
                regionProtectors.add(new GriefPreventionProtection(plugin));
            }
        }
    }

    public static boolean isValidLocation(Player player, Location location) {
        for (IRegionProtector regionProtector : regionProtectors) {
            if (!regionProtector.isAllowed(player, location)) {
                return false;
            }
        }
        return true;
    }

}
