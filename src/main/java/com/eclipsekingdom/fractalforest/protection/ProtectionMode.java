package com.eclipsekingdom.fractalforest.protection;

public enum  ProtectionMode {

    NONE("No protection"),
    LOW("Blocks will not be placed over obstructing blocks blocks"),
    MEDIUM("Tree segment and all descending segments will not grow if an obstruction is encountered"),
    HIGH("All growth will stop after encountering an obstruction"),
    MAX("Tree will not grow if any obstruction is encountered"),
    ;

    private String description;

    ProtectionMode(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
