package com.eclipsekingdom.fractalforest.trees.gen.fractal.genome.type;

import com.eclipsekingdom.fractalforest.trees.gen.fractal.genome.Genome;
import com.eclipsekingdom.fractalforest.trees.gen.fractal.genome.gene.*;
import com.eclipsekingdom.fractalforest.util.math.functions.Exponential;
import com.eclipsekingdom.fractalforest.util.math.range.Bounds;

public class BloodBush extends Genome {
    public BloodBush() {
        super(new ClumpGene(0.119),
                new SplitGene(6,6),
                new AngleGene(new Bounds(0.64, 1.55)),
                new DecayGene(new Bounds(0.288,0.481)),
                new TrunkGene(new Bounds(2.33,4.4), new Bounds(0.43, 0.7), new Bounds(-0.26,0.26)),
                new LeafGene(1, 0.39),
                new RootGene(1,3,new Exponential(0.29,2.34), new Bounds(0.05,0.36),new Bounds(0.34,3.23)));
    }
}
