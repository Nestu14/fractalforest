package com.eclipsekingdom.fractalforest.trees.fractal.genome.gene;

public class ClumpGene implements IClumpGene {

    private double clumpFactor;

    public ClumpGene(double clumpFactor){
        this.clumpFactor = clumpFactor;
    }

    @Override
    public double next() {
        return clumpFactor;
    }
}
