package com.eclipsekingdom.fractalforest.util;

public enum  Scale {
    SHRUB, SMALL, MEDIUM, BIG, MASSIVE;

    public String getFormatted(){
        String string = toString();
        return string.charAt(0) + string.substring(1).toLowerCase();
    }
}
