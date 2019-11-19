package com.eclipsekingdom.fractalforest.gui;

import com.eclipsekingdom.fractalforest.populator.TreePopulator;
import com.eclipsekingdom.fractalforest.populator.TreeSpawner;
import org.bukkit.block.Biome;

public class PopData {

    private TreePopulator populator;
    private boolean initialCreate;
    private Biome currentBiome;
    private TreeSpawner currentSpawner;

    public PopData(TreePopulator populator, boolean initialCreate) {
        this.populator = populator;
        this.initialCreate = initialCreate;
    }

    public TreePopulator getPopulator() {
        return populator;
    }

    public boolean isInitialCreate() {
        return initialCreate;
    }

    public boolean hasCurrentBiome() {
        return currentBiome != null;
    }

    public Biome getCurrentBiome() {
        return currentBiome;
    }

    public void setCurrentBiome(Biome currentBiome) {
        this.currentBiome = currentBiome;
    }

    public boolean hasCurrentSpawner() {
        return currentSpawner != null;
    }

    public TreeSpawner getCurrentSpawner() {
        return currentSpawner;
    }

    public void setCurrentSpawner(TreeSpawner currentSpawner) {
        this.currentSpawner = currentSpawner;
    }


}
