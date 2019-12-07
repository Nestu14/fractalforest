package com.eclipsekingdom.fractalforest.encyclopedia;

public enum  Scale {
    UNCLASSIFIED, SHRUB, SMALL, MEDIUM, BIG, MASSIVE
    ;

    public String getFormatted(){
        String string = toString();
        return string.charAt(0) + string.substring(1).toLowerCase();
    }
}
