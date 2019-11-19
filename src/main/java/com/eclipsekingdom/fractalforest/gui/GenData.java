package com.eclipsekingdom.fractalforest.gui;

import org.bukkit.World;

public class GenData {

    private World currentWorld;

    public boolean hasCurrentWorld() {
        return currentWorld != null;
    }

    public World getCurrentWorld() {
        return currentWorld;
    }

    public void setCurrentWorld(World currentWorld) {
        this.currentWorld = currentWorld;
    }

}
