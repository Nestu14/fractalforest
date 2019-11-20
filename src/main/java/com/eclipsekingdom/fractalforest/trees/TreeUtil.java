package com.eclipsekingdom.fractalforest.trees;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;

public class TreeUtil {

    public static void callEvent(Event event) {
        Bukkit.getServer().getPluginManager().callEvent(event);
    }

}
