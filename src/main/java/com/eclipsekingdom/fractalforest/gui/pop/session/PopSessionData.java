package com.eclipsekingdom.fractalforest.gui.pop.session;

import com.eclipsekingdom.fractalforest.gui.pop.PopPage;
import com.eclipsekingdom.fractalforest.populator.TreePopulator;
import com.eclipsekingdom.fractalforest.populator.TreeSpawner;
import org.bukkit.block.Biome;

public class PopSessionData {

    private TreePopulator populator;
    private boolean initialCreate;
    private PopPage current;
    private boolean transitioning;
    private int pageOffsetX;
    private int pageOffsetY;
    private Biome currentBiome;
    private TreeSpawner currentSpawner;

    public PopSessionData(PopPage current, TreePopulator populator, boolean initialCreate) {
        this.pageOffsetX = 0;
        this.pageOffsetY = 0;
        this.current = current;
        this.populator = populator;
        this.transitioning = false;
        this.initialCreate = initialCreate;
    }

    public TreePopulator getPopulator() {
        return populator;
    }

    public boolean isInitialCreate() {
        return initialCreate;
    }

    public PopPage getCurrent() {
        return current;
    }

    public void setCurrent(PopPage current) {
        this.pageOffsetX = 0;
        this.pageOffsetY = 0;
        this.current = current;
    }

    public boolean isTransitioning() {
        return transitioning;
    }

    public void setTransitioning(boolean transitioning) {
        this.pageOffsetX = 0;
        this.pageOffsetY = 0;
        this.transitioning = transitioning;
    }

    public int getPageOffsetX() {
        return pageOffsetX;
    }


    public int getPageOffsetY() {
        return pageOffsetY;
    }


    public void scrollUp() {
        pageOffsetY--;
        if (pageOffsetY < 0) {
            pageOffsetY = 0;
        }
    }

    public void scrollDown() {
        pageOffsetY++;
    }

    public void scrollRight() {
        pageOffsetX++;
    }

    public void scrollLeft() {
        pageOffsetX--;
        if (pageOffsetX < 0) {
            pageOffsetX = 0;
        }
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
