package com.eclipsekingdom.fractalforest.util;

import org.bukkit.ChatColor;

public class ChatUtil {

    public static String translate(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static String format(String s) {
        String[] parts = s.split("_");
        String formatted = "";
        if (parts.length > 0) {
            String first = parts[0];
            if (first.length() > 1) {
                formatted += first.toUpperCase().charAt(0) + first.substring(1).toLowerCase();
            } else {
                formatted += first;
            }
            for (int i = 1; i < parts.length; i++) {
                String subString = parts[i];
                if (subString.length() > 1) {
                    formatted += " " + subString.toUpperCase().charAt(0) + subString.substring(1).toLowerCase();
                } else {
                    formatted += " " + subString;
                }
            }
            return formatted;
        } else {
            return s;
        }
    }

}
