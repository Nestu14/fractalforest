package com.eclipsekingdom.fractalforest.sys;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class ConsoleSender {

    public static void sendMessage(String message){
        Bukkit.getConsoleSender().sendMessage("[FractalForest] " + ChatColor.RESET + message);
    }

}
