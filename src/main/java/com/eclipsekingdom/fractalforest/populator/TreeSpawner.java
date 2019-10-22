package com.eclipsekingdom.fractalforest.populator;

import com.eclipsekingdom.fractalforest.phylo.Species;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TreeSpawner {

    private static Random random = new Random();

    private Species species;
    private double chance;
    private int min;
    private int max;


    public static List<TreeSpawner> defaultTreeSpawners(){
        List<TreeSpawner> treeSpawners = new ArrayList<>();
        treeSpawners.add(new TreeSpawner(Species.MAGNOLIA, 0.2, 1, 2));
        return treeSpawners;
    }
    public static TreeSpawner defaultTreeSpawner(Species species){
        return new TreeSpawner(species, 0.2, 1, 2);
    }

    public TreeSpawner(Species species, double chance, int min, int max) {
        this.species = species;
        this.chance = chance;
        this.min = min;
        this.max = max;
    }

    public int nextAmount(){
        return random.nextInt(max+1-min) + min;
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

}
