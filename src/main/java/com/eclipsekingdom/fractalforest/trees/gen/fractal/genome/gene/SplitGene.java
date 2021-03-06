package com.eclipsekingdom.fractalforest.trees.gen.fractal.genome.gene;

import com.eclipsekingdom.fractalforest.util.math.TreeMath;

public class SplitGene implements ISplitGene{

    private int max;
    private int min;

    public SplitGene(int min, int max){
        this.min = min;
        this.max = max;
    }

    @Override
    public int next() {
        return TreeMath.randomInt(min, max);
    }

}
