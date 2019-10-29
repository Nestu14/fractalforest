package com.eclipsekingdom.fractalforest.populator.validation;

import org.bukkit.ChatColor;

import static com.eclipsekingdom.fractalforest.util.language.Message.*;

public enum NameStatus {
    VALID(STATUS_VALID.toString()),
    SPECIAL_CHARACTERS(ChatColor.RED + STATUS_SPECIAL_CHAR.toString()),
    TOO_LONG(ChatColor.RED + STATUS_TOO_LONG.toString()),
    ;

    public final String message;

    NameStatus(String message){
        this.message = message;
    }


}
