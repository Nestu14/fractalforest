package com.eclipsekingdom.fractalforest.protection;

import com.eclipsekingdom.fractalforest.FractalForest;
import com.eclipsekingdom.fractalforest.util.PluginBase;
import me.angeschossen.lands.api.integration.LandsIntegration;
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

    private static boolean usingLands = false;
    private static LandsIntegration landsAddon;
    private static String landsKey;

    public static boolean isUsingLands() {
        return usingLands;
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
            } else if (name.equals(PluginBase.residenceNameSpace)) {
                regionProtectors.add(new ResidenceProtection(plugin));
            } else if (name.equals(PluginBase.landsNameSpace)) {
                this.landsAddon = new LandsIntegration(FractalForest.plugin, false);
                this.landsKey = landsAddon.initialize();
                regionProtectors.add(new LandsProtection(landsAddon));
                usingLands = true;
            } else if (name.equals(PluginBase.civsNameSpace)) {
                regionProtectors.add(new CivsProtection());
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

    public static void shutdown() {
        if (isUsingLands()) {
            landsAddon.disable(landsKey);
        }
    }

}
