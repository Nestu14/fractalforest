package com.eclipsekingdom.fractalforest.worldgen.pop;

import com.eclipsekingdom.fractalforest.trees.Species;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TreeSpawner {

    private static Random random = new Random();

    private Species species;
    private double chance;
    private int min;
    private int max;
    private int overflow;

    public static List<TreeSpawner> defaultTreeSpawners() {
        return new ArrayList<>();
    }

    public static TreeSpawner defaultTreeSpawner(Species species) {
        return new TreeSpawner(species, 0, 0, 0, 0);
    }

    public TreeSpawner(Species species, double chance, int min, int max, int overflow) {
        this.species = species;
        this.chance = chance;
        this.min = min;
        this.max = max;
        this.overflow = overflow;
    }

    public TreeSpawner clone() {
        return new TreeSpawner(species, chance, min, max, overflow);
    }

    public int nextAmount() {
        return random.nextInt(max + 1 - min) + min;
    }

    public Species getSpecies() {
        return species;
    }

    public double getChance() {
        return chance;
    }

    public void setChance(double chance) {
        this.chance = chance;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getOverflow() {
        return overflow;
    }

    public void setOverflow(int overflow) {
        this.overflow = overflow;
    }

}
