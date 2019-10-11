package com.eclipsekingdom.fractalforest.trees.fractal.genome.gene;

import com.eclipsekingdom.fractalforest.util.range.Bounds;

import java.util.Random;

public class AngleGene implements IAngleGene{

    private static Random random = new Random();
    private Bounds bounds;

    public AngleGene(Bounds bounds){
        this.bounds = bounds;
    }

    @Override
    public double next() {
        return bounds.nextValue();
    }

    @Override
    public Bounds getBounds() {
        return bounds;
    }

}
