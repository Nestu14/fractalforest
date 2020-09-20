package com.eclipsekingdom.fractalforest.trees.gen.fractal.genome.type;

import com.eclipsekingdom.fractalforest.trees.gen.fractal.genome.Genome;
import com.eclipsekingdom.fractalforest.trees.gen.fractal.genome.gene.*;
import com.eclipsekingdom.fractalforest.util.math.functions.Exponential;
import com.eclipsekingdom.fractalforest.util.math.range.Bounds;

public class FlameTreeGenome extends Genome {
    public FlameTreeGenome() {
        super(new ClumpGene(0.414), new SplitGene(2, 5), new AngleGene(new Bounds(0.85, 2.10)),
                new DecayGene(new Bounds(0.348, 0.601)), new TrunkGene(new Bounds(8.39, 12.36), new Bounds(1.39, 1.51), new Bounds(0, 0)),
                new LeafGene(1.12, 0.3), new RootGene(6, 9, new Exponential(0.63, 1.5), new Bounds(0.5, 0.56), new Bounds(5.77, 5.95)));
    }
}
