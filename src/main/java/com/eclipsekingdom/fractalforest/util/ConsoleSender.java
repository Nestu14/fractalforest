package com.eclipsekingdom.fractalforest.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class ConsoleSender {

    public static void sendMessage(String message){
        Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GREEN + "[FractalForest] " + ChatColor.RESET + message);
    }

}
