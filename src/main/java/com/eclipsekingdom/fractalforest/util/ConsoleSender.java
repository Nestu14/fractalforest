package com.eclipsekingdom.fractalforest.util;

import org.bukkit.Bukkit;

public class ConsoleSender {

    public static void sendMessage(String message){
        Bukkit.getConsoleSender().sendMessage("[FractalForest] " + message);
    }

}
