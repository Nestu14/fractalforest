package com.eclipsekingdom.fractalforest.populator.util;

import org.bukkit.ChatColor;

import static com.eclipsekingdom.fractalforest.util.language.Message.*;

public class NameValidation {

    public static Status clean(String name){
        if (!name.matches("^[a-zA-Z0-9\\_\\-]+$")) {
            return Status.SPECIAL_CHARACTERS;
        }else if(name.length() > 20){
            return Status.TOO_LONG;
        }else{
            return Status.VALID;
        }
    }

    public enum Status {
        VALID(STATUS_VALID.toString()),
        SPECIAL_CHARACTERS(ChatColor.RED + STATUS_SPECIAL_CHAR.toString()),
        TOO_LONG(ChatColor.RED + STATUS_TOO_LONG.toString()),
        ;

        public final String message;

        Status(String message){
            this.message = message;
        }


    }


}