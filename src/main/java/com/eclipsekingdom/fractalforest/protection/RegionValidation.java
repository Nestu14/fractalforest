package com.eclipsekingdom.fractalforest.protection;

import com.eclipsekingdom.fractalforest.sys.PluginBase;
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
            if (name.equals(PluginBase.worldGuardNameSpace)) {
                regionProtectors.add(new WorldGuardProtection(plugin));
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
