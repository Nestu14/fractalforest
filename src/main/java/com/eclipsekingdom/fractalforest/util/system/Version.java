package com.eclipsekingdom.fractalforest.util.system;

import org.bukkit.Bukkit;

public enum Version {
    UNKNOWN, V1_13, V1_14
    ;

    public static Version current = getVersion();

    private static Version getVersion(){
        String versionString = Bukkit.getVersion();
        if(versionString.contains("1.14")){
            return V1_14;
        }else if(versionString.contains("1.13")){
            return V1_13;
        }else{
            return UNKNOWN;
        }
    }

}
